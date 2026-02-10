package com.box.sdkgen.managers.shieldinformationbarriers;

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
import com.box.sdkgen.schemas.shieldinformationbarrier.ShieldInformationBarrier;
import com.box.sdkgen.schemas.shieldinformationbarriers.ShieldInformationBarriers;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ShieldInformationBarriersManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ShieldInformationBarriersManager() {
    this.networkSession = new NetworkSession();
  }

  protected ShieldInformationBarriersManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Get shield information barrier based on provided ID.
   *
   * @param shieldInformationBarrierId The ID of the shield information barrier. Example: "1910967"
   */
  public ShieldInformationBarrier getShieldInformationBarrierById(
      String shieldInformationBarrierId) {
    return getShieldInformationBarrierById(
        shieldInformationBarrierId, new GetShieldInformationBarrierByIdHeaders());
  }

  /**
   * Get shield information barrier based on provided ID.
   *
   * @param shieldInformationBarrierId The ID of the shield information barrier. Example: "1910967"
   * @param headers Headers of getShieldInformationBarrierById method
   */
  public ShieldInformationBarrier getShieldInformationBarrierById(
      String shieldInformationBarrierId, GetShieldInformationBarrierByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barriers/",
                            convertToString(shieldInformationBarrierId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrier.class);
  }

  /**
   * Change status of shield information barrier with the specified ID.
   *
   * @param requestBody Request body of updateShieldInformationBarrierStatus method
   */
  public ShieldInformationBarrier updateShieldInformationBarrierStatus(
      UpdateShieldInformationBarrierStatusRequestBody requestBody) {
    return updateShieldInformationBarrierStatus(
        requestBody, new UpdateShieldInformationBarrierStatusHeaders());
  }

  /**
   * Change status of shield information barrier with the specified ID.
   *
   * @param requestBody Request body of updateShieldInformationBarrierStatus method
   * @param headers Headers of updateShieldInformationBarrierStatus method
   */
  public ShieldInformationBarrier updateShieldInformationBarrierStatus(
      UpdateShieldInformationBarrierStatusRequestBody requestBody,
      UpdateShieldInformationBarrierStatusHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barriers/change_status"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrier.class);
  }

  /** Retrieves a list of shield information barrier objects for the enterprise of JWT. */
  public ShieldInformationBarriers getShieldInformationBarriers() {
    return getShieldInformationBarriers(
        new GetShieldInformationBarriersQueryParams(), new GetShieldInformationBarriersHeaders());
  }

  /**
   * Retrieves a list of shield information barrier objects for the enterprise of JWT.
   *
   * @param queryParams Query parameters of getShieldInformationBarriers method
   */
  public ShieldInformationBarriers getShieldInformationBarriers(
      GetShieldInformationBarriersQueryParams queryParams) {
    return getShieldInformationBarriers(queryParams, new GetShieldInformationBarriersHeaders());
  }

  /**
   * Retrieves a list of shield information barrier objects for the enterprise of JWT.
   *
   * @param headers Headers of getShieldInformationBarriers method
   */
  public ShieldInformationBarriers getShieldInformationBarriers(
      GetShieldInformationBarriersHeaders headers) {
    return getShieldInformationBarriers(new GetShieldInformationBarriersQueryParams(), headers);
  }

  /**
   * Retrieves a list of shield information barrier objects for the enterprise of JWT.
   *
   * @param queryParams Query parameters of getShieldInformationBarriers method
   * @param headers Headers of getShieldInformationBarriers method
   */
  public ShieldInformationBarriers getShieldInformationBarriers(
      GetShieldInformationBarriersQueryParams queryParams,
      GetShieldInformationBarriersHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
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
                            "/2.0/shield_information_barriers"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarriers.class);
  }

  /**
   * Creates a shield information barrier to separate individuals/groups within the same firm and
   * prevents confidential information passing between them.
   *
   * @param requestBody Request body of createShieldInformationBarrier method
   */
  public ShieldInformationBarrier createShieldInformationBarrier(
      CreateShieldInformationBarrierRequestBody requestBody) {
    return createShieldInformationBarrier(requestBody, new CreateShieldInformationBarrierHeaders());
  }

  /**
   * Creates a shield information barrier to separate individuals/groups within the same firm and
   * prevents confidential information passing between them.
   *
   * @param requestBody Request body of createShieldInformationBarrier method
   * @param headers Headers of createShieldInformationBarrier method
   */
  public ShieldInformationBarrier createShieldInformationBarrier(
      CreateShieldInformationBarrierRequestBody requestBody,
      CreateShieldInformationBarrierHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barriers"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrier.class);
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

    public ShieldInformationBarriersManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new ShieldInformationBarriersManager(this);
    }
  }
}
