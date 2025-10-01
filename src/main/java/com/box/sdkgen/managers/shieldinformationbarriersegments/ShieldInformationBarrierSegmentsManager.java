package com.box.sdkgen.managers.shieldinformationbarriersegments;

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
import com.box.sdkgen.schemas.shieldinformationbarriersegment.ShieldInformationBarrierSegment;
import com.box.sdkgen.schemas.shieldinformationbarriersegments.ShieldInformationBarrierSegments;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ShieldInformationBarrierSegmentsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ShieldInformationBarrierSegmentsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ShieldInformationBarrierSegmentsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves shield information barrier segment based on provided ID..
   *
   * @param shieldInformationBarrierSegmentId The ID of the shield information barrier segment.
   *     Example: "3423"
   */
  public ShieldInformationBarrierSegment getShieldInformationBarrierSegmentById(
      String shieldInformationBarrierSegmentId) {
    return getShieldInformationBarrierSegmentById(
        shieldInformationBarrierSegmentId, new GetShieldInformationBarrierSegmentByIdHeaders());
  }

  /**
   * Retrieves shield information barrier segment based on provided ID..
   *
   * @param shieldInformationBarrierSegmentId The ID of the shield information barrier segment.
   *     Example: "3423"
   * @param headers Headers of getShieldInformationBarrierSegmentById method
   */
  public ShieldInformationBarrierSegment getShieldInformationBarrierSegmentById(
      String shieldInformationBarrierSegmentId,
      GetShieldInformationBarrierSegmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segments/",
                            convertToString(shieldInformationBarrierSegmentId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierSegment.class);
  }

  /**
   * Deletes the shield information barrier segment based on provided ID.
   *
   * @param shieldInformationBarrierSegmentId The ID of the shield information barrier segment.
   *     Example: "3423"
   */
  public void deleteShieldInformationBarrierSegmentById(String shieldInformationBarrierSegmentId) {
    deleteShieldInformationBarrierSegmentById(
        shieldInformationBarrierSegmentId, new DeleteShieldInformationBarrierSegmentByIdHeaders());
  }

  /**
   * Deletes the shield information barrier segment based on provided ID.
   *
   * @param shieldInformationBarrierSegmentId The ID of the shield information barrier segment.
   *     Example: "3423"
   * @param headers Headers of deleteShieldInformationBarrierSegmentById method
   */
  public void deleteShieldInformationBarrierSegmentById(
      String shieldInformationBarrierSegmentId,
      DeleteShieldInformationBarrierSegmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segments/",
                            convertToString(shieldInformationBarrierSegmentId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Updates the shield information barrier segment based on provided ID..
   *
   * @param shieldInformationBarrierSegmentId The ID of the shield information barrier segment.
   *     Example: "3423"
   */
  public ShieldInformationBarrierSegment updateShieldInformationBarrierSegmentById(
      String shieldInformationBarrierSegmentId) {
    return updateShieldInformationBarrierSegmentById(
        shieldInformationBarrierSegmentId,
        new UpdateShieldInformationBarrierSegmentByIdRequestBody(),
        new UpdateShieldInformationBarrierSegmentByIdHeaders());
  }

  /**
   * Updates the shield information barrier segment based on provided ID..
   *
   * @param shieldInformationBarrierSegmentId The ID of the shield information barrier segment.
   *     Example: "3423"
   * @param requestBody Request body of updateShieldInformationBarrierSegmentById method
   */
  public ShieldInformationBarrierSegment updateShieldInformationBarrierSegmentById(
      String shieldInformationBarrierSegmentId,
      UpdateShieldInformationBarrierSegmentByIdRequestBody requestBody) {
    return updateShieldInformationBarrierSegmentById(
        shieldInformationBarrierSegmentId,
        requestBody,
        new UpdateShieldInformationBarrierSegmentByIdHeaders());
  }

  /**
   * Updates the shield information barrier segment based on provided ID..
   *
   * @param shieldInformationBarrierSegmentId The ID of the shield information barrier segment.
   *     Example: "3423"
   * @param headers Headers of updateShieldInformationBarrierSegmentById method
   */
  public ShieldInformationBarrierSegment updateShieldInformationBarrierSegmentById(
      String shieldInformationBarrierSegmentId,
      UpdateShieldInformationBarrierSegmentByIdHeaders headers) {
    return updateShieldInformationBarrierSegmentById(
        shieldInformationBarrierSegmentId,
        new UpdateShieldInformationBarrierSegmentByIdRequestBody(),
        headers);
  }

  /**
   * Updates the shield information barrier segment based on provided ID..
   *
   * @param shieldInformationBarrierSegmentId The ID of the shield information barrier segment.
   *     Example: "3423"
   * @param requestBody Request body of updateShieldInformationBarrierSegmentById method
   * @param headers Headers of updateShieldInformationBarrierSegmentById method
   */
  public ShieldInformationBarrierSegment updateShieldInformationBarrierSegmentById(
      String shieldInformationBarrierSegmentId,
      UpdateShieldInformationBarrierSegmentByIdRequestBody requestBody,
      UpdateShieldInformationBarrierSegmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segments/",
                            convertToString(shieldInformationBarrierSegmentId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierSegment.class);
  }

  /**
   * Retrieves a list of shield information barrier segment objects for the specified Information
   * Barrier ID.
   *
   * @param queryParams Query parameters of getShieldInformationBarrierSegments method
   */
  public ShieldInformationBarrierSegments getShieldInformationBarrierSegments(
      GetShieldInformationBarrierSegmentsQueryParams queryParams) {
    return getShieldInformationBarrierSegments(
        queryParams, new GetShieldInformationBarrierSegmentsHeaders());
  }

  /**
   * Retrieves a list of shield information barrier segment objects for the specified Information
   * Barrier ID.
   *
   * @param queryParams Query parameters of getShieldInformationBarrierSegments method
   * @param headers Headers of getShieldInformationBarrierSegments method
   */
  public ShieldInformationBarrierSegments getShieldInformationBarrierSegments(
      GetShieldInformationBarrierSegmentsQueryParams queryParams,
      GetShieldInformationBarrierSegmentsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf(
                    "shield_information_barrier_id",
                    convertToString(queryParams.getShieldInformationBarrierId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segments"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierSegments.class);
  }

  /**
   * Creates a shield information barrier segment.
   *
   * @param requestBody Request body of createShieldInformationBarrierSegment method
   */
  public ShieldInformationBarrierSegment createShieldInformationBarrierSegment(
      CreateShieldInformationBarrierSegmentRequestBody requestBody) {
    return createShieldInformationBarrierSegment(
        requestBody, new CreateShieldInformationBarrierSegmentHeaders());
  }

  /**
   * Creates a shield information barrier segment.
   *
   * @param requestBody Request body of createShieldInformationBarrierSegment method
   * @param headers Headers of createShieldInformationBarrierSegment method
   */
  public ShieldInformationBarrierSegment createShieldInformationBarrierSegment(
      CreateShieldInformationBarrierSegmentRequestBody requestBody,
      CreateShieldInformationBarrierSegmentHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segments"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierSegment.class);
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

    public ShieldInformationBarrierSegmentsManager build() {
      return new ShieldInformationBarrierSegmentsManager(this);
    }
  }
}
