package com.box.sdkgen.managers.hubs;

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
import com.box.sdkgen.schemas.v2025r0.hubcopyrequestv2025r0.HubCopyRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcreaterequestv2025r0.HubCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubsv2025r0.HubsV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubupdaterequestv2025r0.HubUpdateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubv2025r0.HubV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class HubsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public HubsManager() {
    this.networkSession = new NetworkSession();
  }

  protected HubsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /** Retrieves all Box Hubs for requesting user. */
  public HubsV2025R0 getHubsV2025R0() {
    return getHubsV2025R0(new GetHubsV2025R0QueryParams(), new GetHubsV2025R0Headers());
  }

  /**
   * Retrieves all Box Hubs for requesting user.
   *
   * @param queryParams Query parameters of getHubsV2025R0 method
   */
  public HubsV2025R0 getHubsV2025R0(GetHubsV2025R0QueryParams queryParams) {
    return getHubsV2025R0(queryParams, new GetHubsV2025R0Headers());
  }

  /**
   * Retrieves all Box Hubs for requesting user.
   *
   * @param headers Headers of getHubsV2025R0 method
   */
  public HubsV2025R0 getHubsV2025R0(GetHubsV2025R0Headers headers) {
    return getHubsV2025R0(new GetHubsV2025R0QueryParams(), headers);
  }

  /**
   * Retrieves all Box Hubs for requesting user.
   *
   * @param queryParams Query parameters of getHubsV2025R0 method
   * @param headers Headers of getHubsV2025R0 method
   */
  public HubsV2025R0 getHubsV2025R0(
      GetHubsV2025R0QueryParams queryParams, GetHubsV2025R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("query", convertToString(queryParams.getQuery())),
                entryOf("scope", convertToString(queryParams.getScope())),
                entryOf("sort", convertToString(queryParams.getSort())),
                entryOf("direction", convertToString(queryParams.getDirection())),
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
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/hubs"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubsV2025R0.class);
  }

  /**
   * Creates a new Box Hub.
   *
   * @param requestBody Request body of createHubV2025R0 method
   */
  public HubV2025R0 createHubV2025R0(HubCreateRequestV2025R0 requestBody) {
    return createHubV2025R0(requestBody, new CreateHubV2025R0Headers());
  }

  /**
   * Creates a new Box Hub.
   *
   * @param requestBody Request body of createHubV2025R0 method
   * @param headers Headers of createHubV2025R0 method
   */
  public HubV2025R0 createHubV2025R0(
      HubCreateRequestV2025R0 requestBody, CreateHubV2025R0Headers headers) {
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
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/hubs"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubV2025R0.class);
  }

  /**
   * Retrieves all Box Hubs for a given enterprise.
   *
   * <p>Admins or Hub Co-admins of an enterprise with GCM scope can make this call.
   */
  public HubsV2025R0 getEnterpriseHubsV2025R0() {
    return getEnterpriseHubsV2025R0(
        new GetEnterpriseHubsV2025R0QueryParams(), new GetEnterpriseHubsV2025R0Headers());
  }

  /**
   * Retrieves all Box Hubs for a given enterprise.
   *
   * <p>Admins or Hub Co-admins of an enterprise with GCM scope can make this call.
   *
   * @param queryParams Query parameters of getEnterpriseHubsV2025R0 method
   */
  public HubsV2025R0 getEnterpriseHubsV2025R0(GetEnterpriseHubsV2025R0QueryParams queryParams) {
    return getEnterpriseHubsV2025R0(queryParams, new GetEnterpriseHubsV2025R0Headers());
  }

  /**
   * Retrieves all Box Hubs for a given enterprise.
   *
   * <p>Admins or Hub Co-admins of an enterprise with GCM scope can make this call.
   *
   * @param headers Headers of getEnterpriseHubsV2025R0 method
   */
  public HubsV2025R0 getEnterpriseHubsV2025R0(GetEnterpriseHubsV2025R0Headers headers) {
    return getEnterpriseHubsV2025R0(new GetEnterpriseHubsV2025R0QueryParams(), headers);
  }

  /**
   * Retrieves all Box Hubs for a given enterprise.
   *
   * <p>Admins or Hub Co-admins of an enterprise with GCM scope can make this call.
   *
   * @param queryParams Query parameters of getEnterpriseHubsV2025R0 method
   * @param headers Headers of getEnterpriseHubsV2025R0 method
   */
  public HubsV2025R0 getEnterpriseHubsV2025R0(
      GetEnterpriseHubsV2025R0QueryParams queryParams, GetEnterpriseHubsV2025R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("query", convertToString(queryParams.getQuery())),
                entryOf("sort", convertToString(queryParams.getSort())),
                entryOf("direction", convertToString(queryParams.getDirection())),
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
                            "/2.0/enterprise_hubs"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubsV2025R0.class);
  }

  /**
   * Retrieves details for a Box Hub by its ID.
   *
   * @param hubId The unique identifier that represent a hub.
   *     <p>The ID for any hub can be determined by visiting this hub in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   *     `hub_id` is `123`. Example: "12345"
   */
  public HubV2025R0 getHubByIdV2025R0(String hubId) {
    return getHubByIdV2025R0(hubId, new GetHubByIdV2025R0Headers());
  }

  /**
   * Retrieves details for a Box Hub by its ID.
   *
   * @param hubId The unique identifier that represent a hub.
   *     <p>The ID for any hub can be determined by visiting this hub in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   *     `hub_id` is `123`. Example: "12345"
   * @param headers Headers of getHubByIdV2025R0 method
   */
  public HubV2025R0 getHubByIdV2025R0(String hubId, GetHubByIdV2025R0Headers headers) {
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
                            convertToString(hubId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubV2025R0.class);
  }

  /**
   * Updates a Box Hub. Can be used to change title, description, or Box Hub settings.
   *
   * @param hubId The unique identifier that represent a hub.
   *     <p>The ID for any hub can be determined by visiting this hub in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   *     `hub_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateHubByIdV2025R0 method
   */
  public HubV2025R0 updateHubByIdV2025R0(String hubId, HubUpdateRequestV2025R0 requestBody) {
    return updateHubByIdV2025R0(hubId, requestBody, new UpdateHubByIdV2025R0Headers());
  }

  /**
   * Updates a Box Hub. Can be used to change title, description, or Box Hub settings.
   *
   * @param hubId The unique identifier that represent a hub.
   *     <p>The ID for any hub can be determined by visiting this hub in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   *     `hub_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateHubByIdV2025R0 method
   * @param headers Headers of updateHubByIdV2025R0 method
   */
  public HubV2025R0 updateHubByIdV2025R0(
      String hubId, HubUpdateRequestV2025R0 requestBody, UpdateHubByIdV2025R0Headers headers) {
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
                            convertToString(hubId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubV2025R0.class);
  }

  /**
   * Deletes a single Box Hub.
   *
   * @param hubId The unique identifier that represent a hub.
   *     <p>The ID for any hub can be determined by visiting this hub in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   *     `hub_id` is `123`. Example: "12345"
   */
  public void deleteHubByIdV2025R0(String hubId) {
    deleteHubByIdV2025R0(hubId, new DeleteHubByIdV2025R0Headers());
  }

  /**
   * Deletes a single Box Hub.
   *
   * @param hubId The unique identifier that represent a hub.
   *     <p>The ID for any hub can be determined by visiting this hub in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   *     `hub_id` is `123`. Example: "12345"
   * @param headers Headers of deleteHubByIdV2025R0 method
   */
  public void deleteHubByIdV2025R0(String hubId, DeleteHubByIdV2025R0Headers headers) {
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
                            convertToString(hubId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Creates a copy of a Box Hub.
   *
   * <p>The original Box Hub will not be modified.
   *
   * @param hubId The unique identifier that represent a hub.
   *     <p>The ID for any hub can be determined by visiting this hub in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   *     `hub_id` is `123`. Example: "12345"
   * @param requestBody Request body of copyHubV2025R0 method
   */
  public HubV2025R0 copyHubV2025R0(String hubId, HubCopyRequestV2025R0 requestBody) {
    return copyHubV2025R0(hubId, requestBody, new CopyHubV2025R0Headers());
  }

  /**
   * Creates a copy of a Box Hub.
   *
   * <p>The original Box Hub will not be modified.
   *
   * @param hubId The unique identifier that represent a hub.
   *     <p>The ID for any hub can be determined by visiting this hub in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   *     `hub_id` is `123`. Example: "12345"
   * @param requestBody Request body of copyHubV2025R0 method
   * @param headers Headers of copyHubV2025R0 method
   */
  public HubV2025R0 copyHubV2025R0(
      String hubId, HubCopyRequestV2025R0 requestBody, CopyHubV2025R0Headers headers) {
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
                            "/copy"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), HubV2025R0.class);
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

    public HubsManager build() {
      return new HubsManager(this);
    }
  }
}
