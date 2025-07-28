package com.box.sdkgen.managers.termsofserviceuserstatuses;

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
import com.box.sdkgen.schemas.termsofserviceuserstatus.TermsOfServiceUserStatus;
import com.box.sdkgen.schemas.termsofserviceuserstatuses.TermsOfServiceUserStatuses;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TermsOfServiceUserStatusesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TermsOfServiceUserStatusesManager() {
    this.networkSession = new NetworkSession();
  }

  protected TermsOfServiceUserStatusesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public TermsOfServiceUserStatuses getTermsOfServiceUserStatuses(
      GetTermsOfServiceUserStatusesQueryParams queryParams) {
    return getTermsOfServiceUserStatuses(queryParams, new GetTermsOfServiceUserStatusesHeaders());
  }

  public TermsOfServiceUserStatuses getTermsOfServiceUserStatuses(
      GetTermsOfServiceUserStatusesQueryParams queryParams,
      GetTermsOfServiceUserStatusesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("tos_id", convertToString(queryParams.getTosId())),
                entryOf("user_id", convertToString(queryParams.getUserId()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/terms_of_service_user_statuses"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TermsOfServiceUserStatuses.class);
  }

  public TermsOfServiceUserStatus createTermsOfServiceStatusForUser(
      CreateTermsOfServiceStatusForUserRequestBody requestBody) {
    return createTermsOfServiceStatusForUser(
        requestBody, new CreateTermsOfServiceStatusForUserHeaders());
  }

  public TermsOfServiceUserStatus createTermsOfServiceStatusForUser(
      CreateTermsOfServiceStatusForUserRequestBody requestBody,
      CreateTermsOfServiceStatusForUserHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/terms_of_service_user_statuses"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TermsOfServiceUserStatus.class);
  }

  public TermsOfServiceUserStatus updateTermsOfServiceStatusForUserById(
      String termsOfServiceUserStatusId,
      UpdateTermsOfServiceStatusForUserByIdRequestBody requestBody) {
    return updateTermsOfServiceStatusForUserById(
        termsOfServiceUserStatusId,
        requestBody,
        new UpdateTermsOfServiceStatusForUserByIdHeaders());
  }

  public TermsOfServiceUserStatus updateTermsOfServiceStatusForUserById(
      String termsOfServiceUserStatusId,
      UpdateTermsOfServiceStatusForUserByIdRequestBody requestBody,
      UpdateTermsOfServiceStatusForUserByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/terms_of_service_user_statuses/",
                            convertToString(termsOfServiceUserStatusId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TermsOfServiceUserStatus.class);
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

    public TermsOfServiceUserStatusesManager build() {
      return new TermsOfServiceUserStatusesManager(this);
    }
  }
}
