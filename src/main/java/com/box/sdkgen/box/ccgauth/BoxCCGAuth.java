package com.box.sdkgen.box.ccgauth;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.box.tokenstorage.TokenStorage;
import com.box.sdkgen.managers.authorization.AuthorizationManager;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.accesstoken.AccessToken;
import com.box.sdkgen.schemas.postoauth2revoke.PostOAuth2Revoke;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2Token;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenBoxSubjectTypeField;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenGrantTypeField;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenSubjectTokenTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class BoxCCGAuth implements Authentication {

  public final CCGConfig config;

  public final TokenStorage tokenStorage;

  public String subjectId;

  public EnumWrapper<PostOAuth2TokenBoxSubjectTypeField> subjectType;

  public BoxCCGAuth(CCGConfig config) {
    this.config = config;
    this.tokenStorage = this.config.getTokenStorage();
    this.subjectId =
        (!(this.config.getUserId() == null)
            ? this.config.getUserId()
            : this.config.getEnterpriseId());
    this.subjectType =
        new EnumWrapper<PostOAuth2TokenBoxSubjectTypeField>(
            (!(this.config.getUserId() == null)
                ? PostOAuth2TokenBoxSubjectTypeField.USER
                : PostOAuth2TokenBoxSubjectTypeField.ENTERPRISE));
  }

  public AccessToken refreshToken() {
    return refreshToken(null);
  }

  @Override
  public AccessToken refreshToken(NetworkSession networkSession) {
    AuthorizationManager authManager =
        new AuthorizationManager.Builder()
            .networkSession((!(networkSession == null) ? networkSession : new NetworkSession()))
            .build();
    AccessToken token =
        authManager.requestAccessToken(
            new PostOAuth2Token.Builder(PostOAuth2TokenGrantTypeField.CLIENT_CREDENTIALS)
                .clientId(this.config.getClientId())
                .clientSecret(this.config.getClientSecret())
                .boxSubjectType(this.subjectType)
                .boxSubjectId(this.subjectId)
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

  public BoxCCGAuth withUserSubject(String userId) {
    return withUserSubject(userId, new InMemoryTokenStorage());
  }

  public BoxCCGAuth withUserSubject(String userId, TokenStorage tokenStorage) {
    CCGConfig newConfig =
        new CCGConfig.Builder(this.config.getClientId(), this.config.getClientSecret())
            .enterpriseId(this.config.getEnterpriseId())
            .userId(userId)
            .tokenStorage(tokenStorage)
            .build();
    return new BoxCCGAuth(newConfig);
  }

  public BoxCCGAuth withEnterpriseSubject(String enterpriseId) {
    return withEnterpriseSubject(enterpriseId, new InMemoryTokenStorage());
  }

  public BoxCCGAuth withEnterpriseSubject(String enterpriseId, TokenStorage tokenStorage) {
    CCGConfig newConfig =
        new CCGConfig.Builder(this.config.getClientId(), this.config.getClientSecret())
            .enterpriseId(enterpriseId)
            .userId(null)
            .tokenStorage(tokenStorage)
            .build();
    return new BoxCCGAuth(newConfig);
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

  public TokenStorage getTokenStorage() {
    return tokenStorage;
  }
}
