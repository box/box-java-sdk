package com.box.sdkgen.managers.authorization;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.accesstoken.AccessToken;
import com.box.sdkgen.schemas.postoauth2revoke.PostOAuth2Revoke;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2Token;
import com.box.sdkgen.schemas.postoauth2tokenrefreshaccesstoken.PostOAuth2TokenRefreshAccessToken;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class AuthorizationManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public AuthorizationManager() {
    this.networkSession = new NetworkSession();
  }

  protected AuthorizationManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Authorize a user by sending them through the [Box](https://box.com) website and request their
   * permission to act on their behalf.
   *
   * <p>This is the first step when authenticating a user using OAuth 2.0. To request a user's
   * authorization to use the Box APIs on their behalf you will need to send a user to the URL with
   * this format.
   *
   * @param queryParams Query parameters of authorizeUser method
   */
  public void authorizeUser(AuthorizeUserQueryParams queryParams) {
    authorizeUser(queryParams, new AuthorizeUserHeaders());
  }

  /**
   * Authorize a user by sending them through the [Box](https://box.com) website and request their
   * permission to act on their behalf.
   *
   * <p>This is the first step when authenticating a user using OAuth 2.0. To request a user's
   * authorization to use the Box APIs on their behalf you will need to send a user to the URL with
   * this format.
   *
   * @param queryParams Query parameters of authorizeUser method
   * @param headers Headers of authorizeUser method
   */
  public void authorizeUser(AuthorizeUserQueryParams queryParams, AuthorizeUserHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("response_type", convertToString(queryParams.getResponseType())),
                entryOf("client_id", convertToString(queryParams.getClientId())),
                entryOf("redirect_uri", convertToString(queryParams.getRedirectUri())),
                entryOf("state", convertToString(queryParams.getState())),
                entryOf("scope", convertToString(queryParams.getScope()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getOauth2Url(), "/authorize"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Request an Access Token using either a client-side obtained OAuth 2.0 authorization code or a
   * server-side JWT assertion.
   *
   * <p>An Access Token is a string that enables Box to verify that a request belongs to an
   * authorized session. In the normal order of operations you will begin by requesting
   * authentication from the [authorize](#get-authorize) endpoint and Box will send you an
   * authorization code.
   *
   * <p>You will then send this code to this endpoint to exchange it for an Access Token. The
   * returned Access Token can then be used to to make Box API calls.
   *
   * @param requestBody Request body of requestAccessToken method
   */
  public AccessToken requestAccessToken(PostOAuth2Token requestBody) {
    return requestAccessToken(requestBody, new RequestAccessTokenHeaders());
  }

  /**
   * Request an Access Token using either a client-side obtained OAuth 2.0 authorization code or a
   * server-side JWT assertion.
   *
   * <p>An Access Token is a string that enables Box to verify that a request belongs to an
   * authorized session. In the normal order of operations you will begin by requesting
   * authentication from the [authorize](#get-authorize) endpoint and Box will send you an
   * authorization code.
   *
   * <p>You will then send this code to this endpoint to exchange it for an Access Token. The
   * returned Access Token can then be used to to make Box API calls.
   *
   * @param requestBody Request body of requestAccessToken method
   * @param headers Headers of requestAccessToken method
   */
  public AccessToken requestAccessToken(
      PostOAuth2Token requestBody, RequestAccessTokenHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/oauth2/token"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/x-www-form-urlencoded")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AccessToken.class);
  }

  /**
   * Refresh an Access Token using its client ID, secret, and refresh token.
   *
   * @param requestBody Request body of refreshAccessToken method
   */
  public AccessToken refreshAccessToken(PostOAuth2TokenRefreshAccessToken requestBody) {
    return refreshAccessToken(requestBody, new RefreshAccessTokenHeaders());
  }

  /**
   * Refresh an Access Token using its client ID, secret, and refresh token.
   *
   * @param requestBody Request body of refreshAccessToken method
   * @param headers Headers of refreshAccessToken method
   */
  public AccessToken refreshAccessToken(
      PostOAuth2TokenRefreshAccessToken requestBody, RefreshAccessTokenHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/oauth2/token#refresh"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/x-www-form-urlencoded")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AccessToken.class);
  }

  /**
   * Revoke an active Access Token, effectively logging a user out that has been previously
   * authenticated.
   *
   * @param requestBody Request body of revokeAccessToken method
   */
  public void revokeAccessToken(PostOAuth2Revoke requestBody) {
    revokeAccessToken(requestBody, new RevokeAccessTokenHeaders());
  }

  /**
   * Revoke an active Access Token, effectively logging a user out that has been previously
   * authenticated.
   *
   * @param requestBody Request body of revokeAccessToken method
   * @param headers Headers of revokeAccessToken method
   */
  public void revokeAccessToken(PostOAuth2Revoke requestBody, RevokeAccessTokenHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/oauth2/revoke"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/x-www-form-urlencoded")
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public static class Builder {

    protected Authentication auth;

    protected NetworkSession networkSession;

    public Builder() {
      this.networkSession = new NetworkSession();
    }

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public AuthorizationManager build() {
      return new AuthorizationManager(this);
    }
  }
}
