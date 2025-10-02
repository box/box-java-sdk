package com.box.sdkgen.box.oauth;

import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;
import static com.box.sdkgen.serialization.json.JsonManager.sdToUrlParams;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.box.tokenstorage.TokenStorage;
import com.box.sdkgen.managers.authorization.AuthorizationManager;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.accesstoken.AccessToken;
import com.box.sdkgen.schemas.postoauth2revoke.PostOAuth2Revoke;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2Token;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenGrantTypeField;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenSubjectTokenTypeField;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.List;
import java.util.Map;

public class BoxOAuth implements Authentication {

  /** Configuration object of OAuth. */
  public final OAuthConfig config;

  /**
   * An object responsible for storing token. If no custom implementation provided, the token will
   * be stored in memory.
   */
  public final TokenStorage tokenStorage;

  public BoxOAuth(OAuthConfig config) {
    this.config = config;
    this.tokenStorage = this.config.getTokenStorage();
  }

  /** Get the authorization URL for the app user. */
  public String getAuthorizeUrl() {
    return getAuthorizeUrl(new GetAuthorizeUrlOptions());
  }

  /**
   * Get the authorization URL for the app user.
   *
   * @param options The options parameter
   */
  public String getAuthorizeUrl(GetAuthorizeUrlOptions options) {
    Map<String, String> paramsMap =
        prepareParams(
            mapOf(
                entryOf(
                    "client_id",
                    (!(options.getClientId() == null)
                        ? options.getClientId()
                        : this.config.getClientId())),
                entryOf(
                    "response_type",
                    (!(options.getResponseType() == null) ? options.getResponseType() : "code")),
                entryOf("redirect_uri", options.getRedirectUri()),
                entryOf("state", options.getState()),
                entryOf("scope", options.getScope())));
    return String.join(
        "",
        "https://account.box.com/api/oauth2/authorize?",
        sdToUrlParams(JsonManager.serialize(paramsMap)));
  }

  /**
   * Acquires token info using an authorization code.
   *
   * @param authorizationCode The authorization code to use to get tokens.
   */
  public AccessToken getTokensAuthorizationCodeGrant(String authorizationCode) {
    return getTokensAuthorizationCodeGrant(authorizationCode, null);
  }

  /**
   * Acquires token info using an authorization code.
   *
   * @param authorizationCode The authorization code to use to get tokens.
   * @param networkSession An object to keep network session state
   */
  public AccessToken getTokensAuthorizationCodeGrant(
      String authorizationCode, NetworkSession networkSession) {
    AuthorizationManager authManager =
        new AuthorizationManager.Builder()
            .networkSession((!(networkSession == null) ? networkSession : new NetworkSession()))
            .build();
    AccessToken token =
        authManager.requestAccessToken(
            new PostOAuth2Token.Builder(PostOAuth2TokenGrantTypeField.AUTHORIZATION_CODE)
                .code(authorizationCode)
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
    AccessToken token = this.tokenStorage.get();
    if (token == null) {
      throw new BoxSDKError(
          "Access and refresh tokens not available. Authenticate before making any API call first.");
    }
    return token;
  }

  /** Get a new access token for the platform app user. */
  public AccessToken refreshToken() {
    return refreshToken(null);
  }

  /**
   * Get a new access token for the platform app user.
   *
   * @param networkSession An object to keep network session state
   */
  @Override
  public AccessToken refreshToken(NetworkSession networkSession) {
    AccessToken oldToken = this.tokenStorage.get();
    String tokenUsedForRefresh = (!(oldToken == null) ? oldToken.getRefreshToken() : null);
    AuthorizationManager authManager =
        new AuthorizationManager.Builder()
            .networkSession((!(networkSession == null) ? networkSession : new NetworkSession()))
            .build();
    AccessToken token =
        authManager.requestAccessToken(
            new PostOAuth2Token.Builder(PostOAuth2TokenGrantTypeField.REFRESH_TOKEN)
                .clientId(this.config.getClientId())
                .clientSecret(this.config.getClientSecret())
                .refreshToken(tokenUsedForRefresh)
                .build());
    this.tokenStorage.store(token);
    return token;
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
   * Revoke an active Access Token, effectively logging a user out that has been previously
   * authenticated.
   */
  public void revokeToken() {
    revokeToken(null);
  }

  /**
   * Revoke an active Access Token, effectively logging a user out that has been previously
   * authenticated.
   *
   * @param networkSession An object to keep network session state
   */
  @Override
  public void revokeToken(NetworkSession networkSession) {
    AccessToken token = this.tokenStorage.get();
    if (token == null) {
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
            .token(token.getAccessToken())
            .build());
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
    if (token == null || token.getAccessToken() == null) {
      throw new BoxSDKError("No access token is available.");
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

  public TokenStorage getTokenStorage() {
    return tokenStorage;
  }
}
