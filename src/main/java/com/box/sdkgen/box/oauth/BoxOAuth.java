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

  public final OAuthConfig config;

  public final TokenStorage tokenStorage;

  public BoxOAuth(OAuthConfig config) {
    this.config = config;
    this.tokenStorage = this.config.getTokenStorage();
  }

  public String getAuthorizeUrl() {
    return getAuthorizeUrl(new GetAuthorizeUrlOptions());
  }

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

  public AccessToken getTokensAuthorizationCodeGrant(String authorizationCode) {
    return getTokensAuthorizationCodeGrant(authorizationCode, null);
  }

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

  public AccessToken retrieveToken() {
    return retrieveToken(null);
  }

  @Override
  public AccessToken retrieveToken(NetworkSession networkSession) {
    AccessToken token = this.tokenStorage.get();
    if (token == null) {
      throw new BoxSDKError(
          "Access and refresh tokens not available. Authenticate before making any API call first.");
    }
    return token;
  }

  public AccessToken refreshToken() {
    return refreshToken(null);
  }

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

  public void revokeToken() {
    revokeToken(null);
  }

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
