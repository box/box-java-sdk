package com.box.sdkgen.managers.hubitems;

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
import com.box.sdkgen.schemas.v2025r0.hubitemsmanagerequestv2025r0.HubItemsManageRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemsmanageresponsev2025r0.HubItemsManageResponseV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemsv2025r0.HubItemsV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class HubItemsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public HubItemsManager() {
    this.networkSession = new NetworkSession();
  }

  protected HubItemsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public HubItemsV2025R0 getHubItemsV2025R0(GetHubItemsV2025R0QueryParams queryParams) {
    return getHubItemsV2025R0(queryParams, new GetHubItemsV2025R0Headers());
  }

  public HubItemsV2025R0 getHubItemsV2025R0(
      GetHubItemsV2025R0QueryParams queryParams, GetHubItemsV2025R0Headers headers) {
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
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/hub_items"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubItemsV2025R0.class);
  }

  public HubItemsManageResponseV2025R0 manageHubItemsV2025R0(
      String hubId, HubItemsManageRequestV2025R0 requestBody) {
    return manageHubItemsV2025R0(hubId, requestBody, new ManageHubItemsV2025R0Headers());
  }

  public HubItemsManageResponseV2025R0 manageHubItemsV2025R0(
      String hubId,
      HubItemsManageRequestV2025R0 requestBody,
      ManageHubItemsV2025R0Headers headers) {
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
                            "/2.0/hubs/",
                            convertToString(hubId),
                            "/manage_items"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubItemsManageResponseV2025R0.class);
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

    public HubItemsManager build() {
      return new HubItemsManager(this);
    }
  }
}
