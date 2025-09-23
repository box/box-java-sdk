package com.box.sdkgen.managers.sharedlinksweblinks;

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
import com.box.sdkgen.schemas.weblink.WebLink;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class SharedLinksWebLinksManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public SharedLinksWebLinksManager() {
    this.networkSession = new NetworkSession();
  }

  protected SharedLinksWebLinksManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public WebLink findWebLinkForSharedLink(FindWebLinkForSharedLinkHeaders headers) {
    return findWebLinkForSharedLink(new FindWebLinkForSharedLinkQueryParams(), headers);
  }

  public WebLink findWebLinkForSharedLink(
      FindWebLinkForSharedLinkQueryParams queryParams, FindWebLinkForSharedLinkHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("if-none-match", convertToString(headers.getIfNoneMatch())),
                    entryOf("boxapi", convertToString(headers.getBoxapi()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shared_items#web_links"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), WebLink.class);
  }

  public WebLink getSharedLinkForWebLink(
      String webLinkId, GetSharedLinkForWebLinkQueryParams queryParams) {
    return getSharedLinkForWebLink(webLinkId, queryParams, new GetSharedLinkForWebLinkHeaders());
  }

  public WebLink getSharedLinkForWebLink(
      String webLinkId,
      GetSharedLinkForWebLinkQueryParams queryParams,
      GetSharedLinkForWebLinkHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/web_links/",
                            convertToString(webLinkId),
                            "#get_shared_link"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), WebLink.class);
  }

  public WebLink addShareLinkToWebLink(
      String webLinkId, AddShareLinkToWebLinkQueryParams queryParams) {
    return addShareLinkToWebLink(
        webLinkId,
        new AddShareLinkToWebLinkRequestBody(),
        queryParams,
        new AddShareLinkToWebLinkHeaders());
  }

  public WebLink addShareLinkToWebLink(
      String webLinkId,
      AddShareLinkToWebLinkRequestBody requestBody,
      AddShareLinkToWebLinkQueryParams queryParams) {
    return addShareLinkToWebLink(
        webLinkId, requestBody, queryParams, new AddShareLinkToWebLinkHeaders());
  }

  public WebLink addShareLinkToWebLink(
      String webLinkId,
      AddShareLinkToWebLinkQueryParams queryParams,
      AddShareLinkToWebLinkHeaders headers) {
    return addShareLinkToWebLink(
        webLinkId, new AddShareLinkToWebLinkRequestBody(), queryParams, headers);
  }

  public WebLink addShareLinkToWebLink(
      String webLinkId,
      AddShareLinkToWebLinkRequestBody requestBody,
      AddShareLinkToWebLinkQueryParams queryParams,
      AddShareLinkToWebLinkHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/web_links/",
                            convertToString(webLinkId),
                            "#add_shared_link"),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), WebLink.class);
  }

  public WebLink updateSharedLinkOnWebLink(
      String webLinkId, UpdateSharedLinkOnWebLinkQueryParams queryParams) {
    return updateSharedLinkOnWebLink(
        webLinkId,
        new UpdateSharedLinkOnWebLinkRequestBody(),
        queryParams,
        new UpdateSharedLinkOnWebLinkHeaders());
  }

  public WebLink updateSharedLinkOnWebLink(
      String webLinkId,
      UpdateSharedLinkOnWebLinkRequestBody requestBody,
      UpdateSharedLinkOnWebLinkQueryParams queryParams) {
    return updateSharedLinkOnWebLink(
        webLinkId, requestBody, queryParams, new UpdateSharedLinkOnWebLinkHeaders());
  }

  public WebLink updateSharedLinkOnWebLink(
      String webLinkId,
      UpdateSharedLinkOnWebLinkQueryParams queryParams,
      UpdateSharedLinkOnWebLinkHeaders headers) {
    return updateSharedLinkOnWebLink(
        webLinkId, new UpdateSharedLinkOnWebLinkRequestBody(), queryParams, headers);
  }

  public WebLink updateSharedLinkOnWebLink(
      String webLinkId,
      UpdateSharedLinkOnWebLinkRequestBody requestBody,
      UpdateSharedLinkOnWebLinkQueryParams queryParams,
      UpdateSharedLinkOnWebLinkHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/web_links/",
                            convertToString(webLinkId),
                            "#update_shared_link"),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), WebLink.class);
  }

  public WebLink removeSharedLinkFromWebLink(
      String webLinkId, RemoveSharedLinkFromWebLinkQueryParams queryParams) {
    return removeSharedLinkFromWebLink(
        webLinkId,
        new RemoveSharedLinkFromWebLinkRequestBody(),
        queryParams,
        new RemoveSharedLinkFromWebLinkHeaders());
  }

  public WebLink removeSharedLinkFromWebLink(
      String webLinkId,
      RemoveSharedLinkFromWebLinkRequestBody requestBody,
      RemoveSharedLinkFromWebLinkQueryParams queryParams) {
    return removeSharedLinkFromWebLink(
        webLinkId, requestBody, queryParams, new RemoveSharedLinkFromWebLinkHeaders());
  }

  public WebLink removeSharedLinkFromWebLink(
      String webLinkId,
      RemoveSharedLinkFromWebLinkQueryParams queryParams,
      RemoveSharedLinkFromWebLinkHeaders headers) {
    return removeSharedLinkFromWebLink(
        webLinkId, new RemoveSharedLinkFromWebLinkRequestBody(), queryParams, headers);
  }

  public WebLink removeSharedLinkFromWebLink(
      String webLinkId,
      RemoveSharedLinkFromWebLinkRequestBody requestBody,
      RemoveSharedLinkFromWebLinkQueryParams queryParams,
      RemoveSharedLinkFromWebLinkHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/web_links/",
                            convertToString(webLinkId),
                            "#remove_shared_link"),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), WebLink.class);
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

    public SharedLinksWebLinksManager build() {
      return new SharedLinksWebLinksManager(this);
    }
  }
}
