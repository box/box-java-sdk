package com.box.sdkgen.managers.termsofservices;

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
import com.box.sdkgen.schemas.termsofservice.TermsOfService;
import com.box.sdkgen.schemas.termsofservices.TermsOfServices;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TermsOfServicesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TermsOfServicesManager() {
    this.networkSession = new NetworkSession();
  }

  protected TermsOfServicesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /** Returns the current terms of service text and settings for the enterprise. */
  public TermsOfServices getTermsOfService() {
    return getTermsOfService(new GetTermsOfServiceQueryParams(), new GetTermsOfServiceHeaders());
  }

  /**
   * Returns the current terms of service text and settings for the enterprise.
   *
   * @param queryParams Query parameters of getTermsOfService method
   */
  public TermsOfServices getTermsOfService(GetTermsOfServiceQueryParams queryParams) {
    return getTermsOfService(queryParams, new GetTermsOfServiceHeaders());
  }

  /**
   * Returns the current terms of service text and settings for the enterprise.
   *
   * @param headers Headers of getTermsOfService method
   */
  public TermsOfServices getTermsOfService(GetTermsOfServiceHeaders headers) {
    return getTermsOfService(new GetTermsOfServiceQueryParams(), headers);
  }

  /**
   * Returns the current terms of service text and settings for the enterprise.
   *
   * @param queryParams Query parameters of getTermsOfService method
   * @param headers Headers of getTermsOfService method
   */
  public TermsOfServices getTermsOfService(
      GetTermsOfServiceQueryParams queryParams, GetTermsOfServiceHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("tos_type", convertToString(queryParams.getTosType()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/terms_of_services"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TermsOfServices.class);
  }

  /**
   * Creates a terms of service for a given enterprise and type of user.
   *
   * @param requestBody Request body of createTermsOfService method
   */
  public TermsOfService createTermsOfService(CreateTermsOfServiceRequestBody requestBody) {
    return createTermsOfService(requestBody, new CreateTermsOfServiceHeaders());
  }

  /**
   * Creates a terms of service for a given enterprise and type of user.
   *
   * @param requestBody Request body of createTermsOfService method
   * @param headers Headers of createTermsOfService method
   */
  public TermsOfService createTermsOfService(
      CreateTermsOfServiceRequestBody requestBody, CreateTermsOfServiceHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/terms_of_services"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TermsOfService.class);
  }

  /**
   * Fetches a specific terms of service.
   *
   * @param termsOfServiceId The ID of the terms of service. Example: "324234"
   */
  public TermsOfService getTermsOfServiceById(String termsOfServiceId) {
    return getTermsOfServiceById(termsOfServiceId, new GetTermsOfServiceByIdHeaders());
  }

  /**
   * Fetches a specific terms of service.
   *
   * @param termsOfServiceId The ID of the terms of service. Example: "324234"
   * @param headers Headers of getTermsOfServiceById method
   */
  public TermsOfService getTermsOfServiceById(
      String termsOfServiceId, GetTermsOfServiceByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/terms_of_services/",
                            convertToString(termsOfServiceId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TermsOfService.class);
  }

  /**
   * Updates a specific terms of service.
   *
   * @param termsOfServiceId The ID of the terms of service. Example: "324234"
   * @param requestBody Request body of updateTermsOfServiceById method
   */
  public TermsOfService updateTermsOfServiceById(
      String termsOfServiceId, UpdateTermsOfServiceByIdRequestBody requestBody) {
    return updateTermsOfServiceById(
        termsOfServiceId, requestBody, new UpdateTermsOfServiceByIdHeaders());
  }

  /**
   * Updates a specific terms of service.
   *
   * @param termsOfServiceId The ID of the terms of service. Example: "324234"
   * @param requestBody Request body of updateTermsOfServiceById method
   * @param headers Headers of updateTermsOfServiceById method
   */
  public TermsOfService updateTermsOfServiceById(
      String termsOfServiceId,
      UpdateTermsOfServiceByIdRequestBody requestBody,
      UpdateTermsOfServiceByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/terms_of_services/",
                            convertToString(termsOfServiceId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TermsOfService.class);
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

    public TermsOfServicesManager build() {
      return new TermsOfServicesManager(this);
    }
  }
}
