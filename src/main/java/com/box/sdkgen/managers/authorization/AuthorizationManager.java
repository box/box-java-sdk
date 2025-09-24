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

  public void authorizeUser(AuthorizeUserQueryParams queryParams) {
    authorizeUser(queryParams, new AuthorizeUserHeaders());
  }

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

  public AccessToken requestAccessToken(PostOAuth2Token requestBody) {
    return requestAccessToken(requestBody, new RequestAccessTokenHeaders());
  }

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

  public AccessToken refreshAccessToken(PostOAuth2TokenRefreshAccessToken requestBody) {
    return refreshAccessToken(requestBody, new RefreshAccessTokenHeaders());
  }

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

  public void revokeAccessToken(PostOAuth2Revoke requestBody) {
    revokeAccessToken(requestBody, new RevokeAccessTokenHeaders());
  }

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
