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

  public TermsOfServices getTermsOfService() {
    return getTermsOfService(new GetTermsOfServiceQueryParams(), new GetTermsOfServiceHeaders());
  }

  public TermsOfServices getTermsOfService(GetTermsOfServiceQueryParams queryParams) {
    return getTermsOfService(queryParams, new GetTermsOfServiceHeaders());
  }

  public TermsOfServices getTermsOfService(GetTermsOfServiceHeaders headers) {
    return getTermsOfService(new GetTermsOfServiceQueryParams(), headers);
  }

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

  public TermsOfService createTermsOfService(CreateTermsOfServiceRequestBody requestBody) {
    return createTermsOfService(requestBody, new CreateTermsOfServiceHeaders());
  }

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

  public TermsOfService getTermsOfServiceById(String termsOfServiceId) {
    return getTermsOfServiceById(termsOfServiceId, new GetTermsOfServiceByIdHeaders());
  }

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

  public TermsOfService updateTermsOfServiceById(
      String termsOfServiceId, UpdateTermsOfServiceByIdRequestBody requestBody) {
    return updateTermsOfServiceById(
        termsOfServiceId, requestBody, new UpdateTermsOfServiceByIdHeaders());
  }

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
