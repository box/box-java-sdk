package com.box.sdkgen.managers.devicepinners;

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
import com.box.sdkgen.schemas.devicepinner.DevicePinner;
import com.box.sdkgen.schemas.devicepinners.DevicePinners;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class DevicePinnersManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public DevicePinnersManager() {
    this.networkSession = new NetworkSession();
  }

  protected DevicePinnersManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves information about an individual device pin.
   *
   * @param devicePinnerId The ID of the device pin. Example: "2324234"
   */
  public DevicePinner getDevicePinnerById(String devicePinnerId) {
    return getDevicePinnerById(devicePinnerId, new GetDevicePinnerByIdHeaders());
  }

  /**
   * Retrieves information about an individual device pin.
   *
   * @param devicePinnerId The ID of the device pin. Example: "2324234"
   * @param headers Headers of getDevicePinnerById method
   */
  public DevicePinner getDevicePinnerById(
      String devicePinnerId, GetDevicePinnerByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/device_pinners/",
                            convertToString(devicePinnerId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DevicePinner.class);
  }

  /**
   * Deletes an individual device pin.
   *
   * @param devicePinnerId The ID of the device pin. Example: "2324234"
   */
  public void deleteDevicePinnerById(String devicePinnerId) {
    deleteDevicePinnerById(devicePinnerId, new DeleteDevicePinnerByIdHeaders());
  }

  /**
   * Deletes an individual device pin.
   *
   * @param devicePinnerId The ID of the device pin. Example: "2324234"
   * @param headers Headers of deleteDevicePinnerById method
   */
  public void deleteDevicePinnerById(String devicePinnerId, DeleteDevicePinnerByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/device_pinners/",
                            convertToString(devicePinnerId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Retrieves all the device pins within an enterprise.
   *
   * <p>The user must have admin privileges, and the application needs the "manage enterprise" scope
   * to make this call.
   *
   * @param enterpriseId The ID of the enterprise. Example: "3442311"
   */
  public DevicePinners getEnterpriseDevicePinners(String enterpriseId) {
    return getEnterpriseDevicePinners(
        enterpriseId,
        new GetEnterpriseDevicePinnersQueryParams(),
        new GetEnterpriseDevicePinnersHeaders());
  }

  /**
   * Retrieves all the device pins within an enterprise.
   *
   * <p>The user must have admin privileges, and the application needs the "manage enterprise" scope
   * to make this call.
   *
   * @param enterpriseId The ID of the enterprise. Example: "3442311"
   * @param queryParams Query parameters of getEnterpriseDevicePinners method
   */
  public DevicePinners getEnterpriseDevicePinners(
      String enterpriseId, GetEnterpriseDevicePinnersQueryParams queryParams) {
    return getEnterpriseDevicePinners(
        enterpriseId, queryParams, new GetEnterpriseDevicePinnersHeaders());
  }

  /**
   * Retrieves all the device pins within an enterprise.
   *
   * <p>The user must have admin privileges, and the application needs the "manage enterprise" scope
   * to make this call.
   *
   * @param enterpriseId The ID of the enterprise. Example: "3442311"
   * @param headers Headers of getEnterpriseDevicePinners method
   */
  public DevicePinners getEnterpriseDevicePinners(
      String enterpriseId, GetEnterpriseDevicePinnersHeaders headers) {
    return getEnterpriseDevicePinners(
        enterpriseId, new GetEnterpriseDevicePinnersQueryParams(), headers);
  }

  /**
   * Retrieves all the device pins within an enterprise.
   *
   * <p>The user must have admin privileges, and the application needs the "manage enterprise" scope
   * to make this call.
   *
   * @param enterpriseId The ID of the enterprise. Example: "3442311"
   * @param queryParams Query parameters of getEnterpriseDevicePinners method
   * @param headers Headers of getEnterpriseDevicePinners method
   */
  public DevicePinners getEnterpriseDevicePinners(
      String enterpriseId,
      GetEnterpriseDevicePinnersQueryParams queryParams,
      GetEnterpriseDevicePinnersHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("direction", convertToString(queryParams.getDirection()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/enterprises/",
                            convertToString(enterpriseId),
                            "/device_pinners"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DevicePinners.class);
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

    public DevicePinnersManager build() {
      return new DevicePinnersManager(this);
    }
  }
}
