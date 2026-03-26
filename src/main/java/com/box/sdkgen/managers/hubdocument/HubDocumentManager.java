package com.box.sdkgen.managers.hubdocument;

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
import com.box.sdkgen.schemas.v2025r0.hubdocumentblocksv2025r0.HubDocumentBlocksV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubdocumentpagesv2025r0.HubDocumentPagesV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class HubDocumentManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public HubDocumentManager() {
    this.networkSession = new NetworkSession();
  }

  protected HubDocumentManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves a list of Hub Document Pages for the specified hub. Includes both root-level pages
   * and sub pages.
   *
   * @param queryParams Query parameters of getHubDocumentPagesV2025R0 method
   */
  public HubDocumentPagesV2025R0 getHubDocumentPagesV2025R0(
      GetHubDocumentPagesV2025R0QueryParams queryParams) {
    return getHubDocumentPagesV2025R0(queryParams, new GetHubDocumentPagesV2025R0Headers());
  }

  /**
   * Retrieves a list of Hub Document Pages for the specified hub. Includes both root-level pages
   * and sub pages.
   *
   * @param queryParams Query parameters of getHubDocumentPagesV2025R0 method
   * @param headers Headers of getHubDocumentPagesV2025R0 method
   */
  public HubDocumentPagesV2025R0 getHubDocumentPagesV2025R0(
      GetHubDocumentPagesV2025R0QueryParams queryParams,
      GetHubDocumentPagesV2025R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("hub_id", convertToString(queryParams.getHubId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/hub_document_pages"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubDocumentPagesV2025R0.class);
  }

  /**
   * Retrieves a sorted list of all Hub Document Blocks on a specified page in the hub document,
   * excluding items. Blocks are hierarchically organized by their `parent_id`. Blocks are sorted in
   * order based on user specification in the user interface. The response will only include content
   * blocks that belong to the specified page. This will not include sub pages or sub page content
   * blocks.
   *
   * @param queryParams Query parameters of getHubDocumentBlocksV2025R0 method
   */
  public HubDocumentBlocksV2025R0 getHubDocumentBlocksV2025R0(
      GetHubDocumentBlocksV2025R0QueryParams queryParams) {
    return getHubDocumentBlocksV2025R0(queryParams, new GetHubDocumentBlocksV2025R0Headers());
  }

  /**
   * Retrieves a sorted list of all Hub Document Blocks on a specified page in the hub document,
   * excluding items. Blocks are hierarchically organized by their `parent_id`. Blocks are sorted in
   * order based on user specification in the user interface. The response will only include content
   * blocks that belong to the specified page. This will not include sub pages or sub page content
   * blocks.
   *
   * @param queryParams Query parameters of getHubDocumentBlocksV2025R0 method
   * @param headers Headers of getHubDocumentBlocksV2025R0 method
   */
  public HubDocumentBlocksV2025R0 getHubDocumentBlocksV2025R0(
      GetHubDocumentBlocksV2025R0QueryParams queryParams,
      GetHubDocumentBlocksV2025R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("hub_id", convertToString(queryParams.getHubId())),
                entryOf("page_id", convertToString(queryParams.getPageId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/hub_document_blocks"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubDocumentBlocksV2025R0.class);
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

    public HubDocumentManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new HubDocumentManager(this);
    }
  }
}
