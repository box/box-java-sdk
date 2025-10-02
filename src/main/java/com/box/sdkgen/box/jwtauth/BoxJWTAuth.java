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

  /** An object containing all JWT configuration to use for authentication */
  public final JWTConfig config;

  /**
   * An object responsible for storing token. If no custom implementation provided, the token will
   * be stored in memory.
   */
  public final TokenStorage tokenStorage;

  /**
   * The ID of the user or enterprise to authenticate as. If not provided, defaults to the
   * enterprise ID if set, otherwise defaults to the user ID.
   */
  public String subjectId;

  /** The type of the subject ID provided. Must be either 'user' or 'enterprise'. */
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

  /** Get new access token using JWT auth. */
  public AccessToken refreshToken() {
    return refreshToken(null);
  }

  /**
   * Get new access token using JWT auth.
   *
   * @param networkSession An object to keep network session state
   */
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

  /**
   * Get the current access token. If the current access token is expired or not found, this method
   * will attempt to refresh the token.
   */
  public AccessToken retrieveToken() {
    return retrieveToken(null);
  }

  /**
   * Get the current access token. If the current access token is expired or not found, this method
   * will attempt to refresh the token.
   *
   * @param networkSession An object to keep network session state
   */
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

  /**
   * Create a new BoxJWTAuth instance that uses the provided user ID as the subject of the JWT
   * assertion. May be one of this application's created App User. Depending on the configured User
   * Access Level, may also be any other App User or Managed User in the enterprise.
   * &lt;https://developer.box.com/en/guides/applications/&gt;
   * &lt;https://developer.box.com/en/guides/authentication/select/&gt;
   *
   * @param userId The id of the user to authenticate
   */
  public BoxJWTAuth withUserSubject(String userId) {
    return withUserSubject(userId, new InMemoryTokenStorage());
  }

  /**
   * Create a new BoxJWTAuth instance that uses the provided user ID as the subject of the JWT
   * assertion. May be one of this application's created App User. Depending on the configured User
   * Access Level, may also be any other App User or Managed User in the enterprise.
   * &lt;https://developer.box.com/en/guides/applications/&gt;
   * &lt;https://developer.box.com/en/guides/authentication/select/&gt;
   *
   * @param userId The id of the user to authenticate
   * @param tokenStorage Object responsible for storing token in newly created BoxJWTAuth. If no
   *     custom implementation provided, the token will be stored in memory.
   */
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

  /**
   * Create a new BoxJWTAuth instance that uses the provided enterprise ID as the subject of the JWT
   * assertion.
   *
   * @param enterpriseId The id of the enterprise to authenticate
   */
  public BoxJWTAuth withEnterpriseSubject(String enterpriseId) {
    return withEnterpriseSubject(enterpriseId, new InMemoryTokenStorage());
  }

  /**
   * Create a new BoxJWTAuth instance that uses the provided enterprise ID as the subject of the JWT
   * assertion.
   *
   * @param enterpriseId The id of the enterprise to authenticate
   * @param tokenStorage Object responsible for storing token in newly created BoxJWTAuth. If no
   *     custom implementation provided, the token will be stored in memory.
   */
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

  /**
   * Downscope access token to the provided scopes. Returning a new access token with the provided
   * scopes, with the original access token unchanged.
   *
   * @param scopes The scope(s) to apply to the resulting token.
   * @param resource The file or folder to get a downscoped token for. If None and shared_link None,
   *     the resulting token will not be scoped down to just a single item. The resource should be a
   *     full URL to an item, e.g. https://api.box.com/2.0/files/123456.
   * @param sharedLink The shared link to get a downscoped token for. If None and item None, the
   *     resulting token will not be scoped down to just a single item.
   * @param networkSession An object to keep network session state
   */
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

  /** Revoke the current access token and remove it from token storage. */
  public void revokeToken() {
    revokeToken(null);
  }

  /**
   * Revoke the current access token and remove it from token storage.
   *
   * @param networkSession An object to keep network session state
   */
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
