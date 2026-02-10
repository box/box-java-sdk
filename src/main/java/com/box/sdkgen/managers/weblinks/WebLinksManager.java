package com.box.sdkgen.managers.weblinks;

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

public class WebLinksManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public WebLinksManager() {
    this.networkSession = new NetworkSession();
  }

  protected WebLinksManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Creates a web link object within a folder.
   *
   * @param requestBody Request body of createWebLink method
   */
  public WebLink createWebLink(CreateWebLinkRequestBody requestBody) {
    return createWebLink(requestBody, new CreateWebLinkHeaders());
  }

  /**
   * Creates a web link object within a folder.
   *
   * @param requestBody Request body of createWebLink method
   * @param headers Headers of createWebLink method
   */
  public WebLink createWebLink(CreateWebLinkRequestBody requestBody, CreateWebLinkHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/web_links"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), WebLink.class);
  }

  /**
   * Retrieve information about a web link.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   */
  public WebLink getWebLinkById(String webLinkId) {
    return getWebLinkById(webLinkId, new GetWebLinkByIdHeaders());
  }

  /**
   * Retrieve information about a web link.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param headers Headers of getWebLinkById method
   */
  public WebLink getWebLinkById(String webLinkId, GetWebLinkByIdHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("boxapi", convertToString(headers.getBoxapi()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/web_links/",
                            convertToString(webLinkId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), WebLink.class);
  }

  /**
   * Updates a web link object.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   */
  public WebLink updateWebLinkById(String webLinkId) {
    return updateWebLinkById(
        webLinkId, new UpdateWebLinkByIdRequestBody(), new UpdateWebLinkByIdHeaders());
  }

  /**
   * Updates a web link object.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param requestBody Request body of updateWebLinkById method
   */
  public WebLink updateWebLinkById(String webLinkId, UpdateWebLinkByIdRequestBody requestBody) {
    return updateWebLinkById(webLinkId, requestBody, new UpdateWebLinkByIdHeaders());
  }

  /**
   * Updates a web link object.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param headers Headers of updateWebLinkById method
   */
  public WebLink updateWebLinkById(String webLinkId, UpdateWebLinkByIdHeaders headers) {
    return updateWebLinkById(webLinkId, new UpdateWebLinkByIdRequestBody(), headers);
  }

  /**
   * Updates a web link object.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param requestBody Request body of updateWebLinkById method
   * @param headers Headers of updateWebLinkById method
   */
  public WebLink updateWebLinkById(
      String webLinkId,
      UpdateWebLinkByIdRequestBody requestBody,
      UpdateWebLinkByIdHeaders headers) {
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
                            convertToString(webLinkId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), WebLink.class);
  }

  /**
   * Deletes a web link.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   */
  public void deleteWebLinkById(String webLinkId) {
    deleteWebLinkById(webLinkId, new DeleteWebLinkByIdHeaders());
  }

  /**
   * Deletes a web link.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param headers Headers of deleteWebLinkById method
   */
  public void deleteWebLinkById(String webLinkId, DeleteWebLinkByIdHeaders headers) {
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
                            convertToString(webLinkId)),
                        "DELETE")
                    .headers(headersMap)
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public WebLinksManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new WebLinksManager(this);
    }
  }
}
