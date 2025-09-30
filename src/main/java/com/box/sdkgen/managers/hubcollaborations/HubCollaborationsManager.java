package com.box.sdkgen.managers.hubcollaborations;

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
import com.box.sdkgen.schemas.v2025r0.hubcollaborationcreaterequestv2025r0.HubCollaborationCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationsv2025r0.HubCollaborationsV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationupdaterequestv2025r0.HubCollaborationUpdateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationv2025r0.HubCollaborationV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class HubCollaborationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public HubCollaborationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected HubCollaborationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public HubCollaborationsV2025R0 getHubCollaborationsV2025R0(
      GetHubCollaborationsV2025R0QueryParams queryParams) {
    return getHubCollaborationsV2025R0(queryParams, new GetHubCollaborationsV2025R0Headers());
  }

  public HubCollaborationsV2025R0 getHubCollaborationsV2025R0(
      GetHubCollaborationsV2025R0QueryParams queryParams,
      GetHubCollaborationsV2025R0Headers headers) {
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
                            "/2.0/hub_collaborations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubCollaborationsV2025R0.class);
  }

  public HubCollaborationV2025R0 createHubCollaborationV2025R0(
      HubCollaborationCreateRequestV2025R0 requestBody) {
    return createHubCollaborationV2025R0(requestBody, new CreateHubCollaborationV2025R0Headers());
  }

  public HubCollaborationV2025R0 createHubCollaborationV2025R0(
      HubCollaborationCreateRequestV2025R0 requestBody,
      CreateHubCollaborationV2025R0Headers headers) {
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
                            "/2.0/hub_collaborations"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubCollaborationV2025R0.class);
  }

  public HubCollaborationV2025R0 getHubCollaborationByIdV2025R0(String hubCollaborationId) {
    return getHubCollaborationByIdV2025R0(
        hubCollaborationId, new GetHubCollaborationByIdV2025R0Headers());
  }

  public HubCollaborationV2025R0 getHubCollaborationByIdV2025R0(
      String hubCollaborationId, GetHubCollaborationByIdV2025R0Headers headers) {
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
                            "/2.0/hub_collaborations/",
                            convertToString(hubCollaborationId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubCollaborationV2025R0.class);
  }

  public HubCollaborationV2025R0 updateHubCollaborationByIdV2025R0(
      String hubCollaborationId, HubCollaborationUpdateRequestV2025R0 requestBody) {
    return updateHubCollaborationByIdV2025R0(
        hubCollaborationId, requestBody, new UpdateHubCollaborationByIdV2025R0Headers());
  }

  public HubCollaborationV2025R0 updateHubCollaborationByIdV2025R0(
      String hubCollaborationId,
      HubCollaborationUpdateRequestV2025R0 requestBody,
      UpdateHubCollaborationByIdV2025R0Headers headers) {
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
                            "/2.0/hub_collaborations/",
                            convertToString(hubCollaborationId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubCollaborationV2025R0.class);
  }

  public void deleteHubCollaborationByIdV2025R0(String hubCollaborationId) {
    deleteHubCollaborationByIdV2025R0(
        hubCollaborationId, new DeleteHubCollaborationByIdV2025R0Headers());
  }

  public void deleteHubCollaborationByIdV2025R0(
      String hubCollaborationId, DeleteHubCollaborationByIdV2025R0Headers headers) {
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
                            "/2.0/hub_collaborations/",
                            convertToString(hubCollaborationId)),
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

    public HubCollaborationsManager build() {
      return new HubCollaborationsManager(this);
    }
  }
}
