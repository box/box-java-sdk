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

  /** Configuration object of Client Credentials Grant auth. */
  public final CCGConfig config;

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

  /** Get a new access token using CCG auth */
  public AccessToken refreshToken() {
    return refreshToken(null);
  }

  /**
   * Get a new access token using CCG auth
   *
   * @param networkSession An object to keep network session state
   */
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

  /** Return a current token or get a new one when not available. */
  public AccessToken retrieveToken() {
    return retrieveToken(null);
  }

  /**
   * Return a current token or get a new one when not available.
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
   * Create a new BoxCCGAuth instance that uses the provided user ID as the subject ID. May be one
   * of this application's created App User. Depending on the configured User Access Level, may also
   * be any other App User or Managed User in the enterprise.
   * &lt;https://developer.box.com/en/guides/applications/&gt;
   * &lt;https://developer.box.com/en/guides/authentication/select/&gt;
   *
   * @param userId The id of the user to authenticate
   */
  public BoxCCGAuth withUserSubject(String userId) {
    return withUserSubject(userId, new InMemoryTokenStorage());
  }

  /**
   * Create a new BoxCCGAuth instance that uses the provided user ID as the subject ID. May be one
   * of this application's created App User. Depending on the configured User Access Level, may also
   * be any other App User or Managed User in the enterprise.
   * &lt;https://developer.box.com/en/guides/applications/&gt;
   * &lt;https://developer.box.com/en/guides/authentication/select/&gt;
   *
   * @param userId The id of the user to authenticate
   * @param tokenStorage Object responsible for storing token in newly created BoxCCGAuth. If no
   *     custom implementation provided, the token will be stored in memory.
   */
  public BoxCCGAuth withUserSubject(String userId, TokenStorage tokenStorage) {
    CCGConfig newConfig =
        new CCGConfig.Builder(this.config.getClientId(), this.config.getClientSecret())
            .enterpriseId(this.config.getEnterpriseId())
            .userId(userId)
            .tokenStorage(tokenStorage)
            .build();
    return new BoxCCGAuth(newConfig);
  }

  /**
   * Create a new BoxCCGAuth instance that uses the provided enterprise ID as the subject ID.
   *
   * @param enterpriseId The id of the enterprise to authenticate
   */
  public BoxCCGAuth withEnterpriseSubject(String enterpriseId) {
    return withEnterpriseSubject(enterpriseId, new InMemoryTokenStorage());
  }

  /**
   * Create a new BoxCCGAuth instance that uses the provided enterprise ID as the subject ID.
   *
   * @param enterpriseId The id of the enterprise to authenticate
   * @param tokenStorage Object responsible for storing token in newly created BoxCCGAuth. If no
   *     custom implementation provided, the token will be stored in memory.
   */
  public BoxCCGAuth withEnterpriseSubject(String enterpriseId, TokenStorage tokenStorage) {
    CCGConfig newConfig =
        new CCGConfig.Builder(this.config.getClientId(), this.config.getClientSecret())
            .enterpriseId(enterpriseId)
            .userId(null)
            .tokenStorage(tokenStorage)
            .build();
    return new BoxCCGAuth(newConfig);
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

  public TokenStorage getTokenStorage() {
    return tokenStorage;
  }
}
