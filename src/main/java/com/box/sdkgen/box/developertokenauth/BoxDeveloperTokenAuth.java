package com.box.sdkgen.box.developertokenauth;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.box.tokenstorage.TokenStorage;
import com.box.sdkgen.managers.authorization.AuthorizationManager;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.accesstoken.AccessToken;
import com.box.sdkgen.schemas.postoauth2revoke.PostOAuth2Revoke;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2Token;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenGrantTypeField;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenSubjectTokenTypeField;
import java.util.List;

public class BoxDeveloperTokenAuth implements Authentication {

  public final String token;

  /** Configuration object of DeveloperTokenAuth. */
  public DeveloperTokenConfig config;

  /**
   * An object responsible for storing token. If no custom implementation provided, the token will
   * be stored in memory.
   */
  public final TokenStorage tokenStorage;

  public BoxDeveloperTokenAuth(String token) {
    this.token = token;
    this.config = new DeveloperTokenConfig();
    this.tokenStorage =
        new InMemoryTokenStorage.Builder()
            .token(new AccessToken.Builder().accessToken(this.token).build())
            .build();
  }

  protected BoxDeveloperTokenAuth(Builder builder) {
    this.token = builder.token;
    this.config = builder.config;
    this.tokenStorage =
        new InMemoryTokenStorage.Builder()
            .token(new AccessToken.Builder().accessToken(this.token).build())
            .build();
  }

  /** Retrieves stored developer token */
  public AccessToken retrieveToken() {
    return retrieveToken(null);
  }

  /**
   * Retrieves stored developer token
   *
   * @param networkSession An object to keep network session state
   */
  @Override
  public AccessToken retrieveToken(NetworkSession networkSession) {
    AccessToken token = this.tokenStorage.get();
    if (token == null) {
      throw new BoxSDKError("No access token is available.");
    }
    return token;
  }

  /** Developer token cannot be refreshed */
  public AccessToken refreshToken() {
    return refreshToken(null);
  }

  /**
   * Developer token cannot be refreshed
   *
   * @param networkSession An object to keep network session state
   */
  @Override
  public AccessToken refreshToken(NetworkSession networkSession) {
    throw new BoxSDKError("Developer token has expired. Please provide a new one.");
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
    this.tokenStorage.clear();
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

  public static class Builder {

    protected final String token;

    protected DeveloperTokenConfig config;

    public Builder(String token) {
      this.token = token;
    }

    public Builder config(DeveloperTokenConfig config) {
      this.config = config;
      return this;
    }

    public BoxDeveloperTokenAuth build() {
      if (this.config == null) {
        this.config = new DeveloperTokenConfig();
      }
      return new BoxDeveloperTokenAuth(this);
    }
  }
}
