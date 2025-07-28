package com.box.sdkgen.box.jwtauth;

import static com.box.sdkgen.internal.utils.UtilsManager.createJwtAssertion;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.getEpochTimeInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.isBrowser;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.box.tokenstorage.TokenStorage;
import com.box.sdkgen.internal.utils.JwtKey;
import com.box.sdkgen.internal.utils.JwtSignOptions;
import com.box.sdkgen.managers.authorization.AuthorizationManager;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.accesstoken.AccessToken;
import com.box.sdkgen.schemas.postoauth2revoke.PostOAuth2Revoke;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2Token;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenGrantTypeField;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenSubjectTokenTypeField;
import java.util.List;
import java.util.Map;

public class BoxJWTAuth implements Authentication {

  public final JWTConfig config;

  public final TokenStorage tokenStorage;

  public String subjectId;

  public String subjectType;

  public BoxJWTAuth(JWTConfig config) {
    this.config = config;
    this.tokenStorage = this.config.getTokenStorage();
    this.subjectId =
        (!(this.config.getEnterpriseId() == null)
            ? this.config.getEnterpriseId()
            : this.config.getUserId());
    this.subjectType = (!(this.config.getEnterpriseId() == null) ? "enterprise" : "user");
  }

  public AccessToken refreshToken() {
    return refreshToken(null);
  }

  @Override
  public AccessToken refreshToken(NetworkSession networkSession) {
    if (isBrowser()) {
      throw new BoxSDKError("JWT auth is not supported in browser environment.");
    }
    Map<String, Object> claims =
        mapOf(
            entryOf("exp", getEpochTimeInSeconds() + 30),
            entryOf("box_sub_type", this.subjectType));
    JwtSignOptions jwtOptions =
        new JwtSignOptions(
            this.config.getAlgorithm(),
            "https://api.box.com/oauth2/token",
            this.config.getClientId(),
            this.subjectId,
            getUuid(),
            this.config.getJwtKeyId(),
            this.config.getPrivateKeyDecryptor());
    JwtKey jwtKey = new JwtKey(this.config.getPrivateKey(), this.config.getPrivateKeyPassphrase());
    String assertion = createJwtAssertion(claims, jwtKey, jwtOptions);
    AuthorizationManager authManager =
        new AuthorizationManager.Builder()
            .networkSession((!(networkSession == null) ? networkSession : new NetworkSession()))
            .build();
    AccessToken token =
        authManager.requestAccessToken(
            new PostOAuth2Token.Builder(
                    PostOAuth2TokenGrantTypeField.URN_IETF_PARAMS_OAUTH_GRANT_TYPE_JWT_BEARER)
                .assertion(assertion)
                .clientId(this.config.getClientId())
                .clientSecret(this.config.getClientSecret())
                .build());
    this.tokenStorage.store(token);
    return token;
  }

  public AccessToken retrieveToken() {
    return retrieveToken(null);
  }

  @Override
  public AccessToken retrieveToken(NetworkSession networkSession) {
    AccessToken oldToken = this.tokenStorage.get();
    if (oldToken == null) {
      AccessToken newToken = this.refreshToken(networkSession);
      return newToken;
    }
    return oldToken;
  }

  public String retrieveAuthorizationHeader() {
    return retrieveAuthorizationHeader(null);
  }

  @Override
  public String retrieveAuthorizationHeader(NetworkSession networkSession) {
    AccessToken token = this.retrieveToken(networkSession);
    return String.join("", "Bearer ", token.getAccessToken());
  }

  public BoxJWTAuth withUserSubject(String userId) {
    return withUserSubject(userId, new InMemoryTokenStorage());
  }

  public BoxJWTAuth withUserSubject(String userId, TokenStorage tokenStorage) {
    JWTConfig newConfig =
        new JWTConfig.Builder(
                this.config.getClientId(),
                this.config.getClientSecret(),
                this.config.getJwtKeyId(),
                this.config.getPrivateKey(),
                this.config.getPrivateKeyPassphrase())
            .enterpriseId(null)
            .userId(userId)
            .tokenStorage(tokenStorage)
            .build();
    BoxJWTAuth newAuth = new BoxJWTAuth(newConfig);
    return newAuth;
  }

  public BoxJWTAuth withEnterpriseSubject(String enterpriseId) {
    return withEnterpriseSubject(enterpriseId, new InMemoryTokenStorage());
  }

  public BoxJWTAuth withEnterpriseSubject(String enterpriseId, TokenStorage tokenStorage) {
    JWTConfig newConfig =
        new JWTConfig.Builder(
                this.config.getClientId(),
                this.config.getClientSecret(),
                this.config.getJwtKeyId(),
                this.config.getPrivateKey(),
                this.config.getPrivateKeyPassphrase())
            .enterpriseId(enterpriseId)
            .userId(null)
            .tokenStorage(tokenStorage)
            .build();
    BoxJWTAuth newAuth = new BoxJWTAuth(newConfig);
    return newAuth;
  }

  @Override
  public AccessToken downscopeToken(
      List<String> scopes, String resource, String sharedLink, NetworkSession networkSession) {
    AccessToken token = this.retrieveToken(networkSession);
    if (token == null) {
      throw new BoxSDKError(
          "No access token is available. Make an API call to retrieve a token before calling this method.");
    }
    AuthorizationManager authManager =
        new AuthorizationManager.Builder()
            .networkSession((!(networkSession == null) ? networkSession : new NetworkSession()))
            .build();
    AccessToken downscopedToken =
        authManager.requestAccessToken(
            new PostOAuth2Token.Builder(
                    PostOAuth2TokenGrantTypeField.URN_IETF_PARAMS_OAUTH_GRANT_TYPE_TOKEN_EXCHANGE)
                .subjectToken(token.getAccessToken())
                .subjectTokenType(
                    PostOAuth2TokenSubjectTokenTypeField
                        .URN_IETF_PARAMS_OAUTH_TOKEN_TYPE_ACCESS_TOKEN)
                .resource(resource)
                .scope(String.join(" ", scopes))
                .boxSharedLink(sharedLink)
                .build());
    return downscopedToken;
  }

  public void revokeToken() {
    revokeToken(null);
  }

  @Override
  public void revokeToken(NetworkSession networkSession) {
    AccessToken oldToken = this.tokenStorage.get();
    if (oldToken == null) {
      return;
    }
    AuthorizationManager authManager =
        new AuthorizationManager.Builder()
            .networkSession((!(networkSession == null) ? networkSession : new NetworkSession()))
            .build();
    authManager.revokeAccessToken(
        new PostOAuth2Revoke.Builder()
            .clientId(this.config.getClientId())
            .clientSecret(this.config.getClientSecret())
            .token(oldToken.getAccessToken())
            .build());
    this.tokenStorage.clear();
  }

  public JWTConfig getConfig() {
    return config;
  }

  public TokenStorage getTokenStorage() {
    return tokenStorage;
  }
}
