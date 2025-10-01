package com.box.sdkgen.managers.externalusers;

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
import com.box.sdkgen.schemas.v2025r0.externaluserssubmitdeletejobrequestv2025r0.ExternalUsersSubmitDeleteJobRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.externaluserssubmitdeletejobresponsev2025r0.ExternalUsersSubmitDeleteJobResponseV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ExternalUsersManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ExternalUsersManager() {
    this.networkSession = new NetworkSession();
  }

  protected ExternalUsersManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Delete external users from current user enterprise. This will remove each external user from
   * all invited collaborations within the current enterprise.
   *
   * @param requestBody Request body of submitJobToDeleteExternalUsersV2025R0 method
   */
  public ExternalUsersSubmitDeleteJobResponseV2025R0 submitJobToDeleteExternalUsersV2025R0(
      ExternalUsersSubmitDeleteJobRequestV2025R0 requestBody) {
    return submitJobToDeleteExternalUsersV2025R0(
        requestBody, new SubmitJobToDeleteExternalUsersV2025R0Headers());
  }

  /**
   * Delete external users from current user enterprise. This will remove each external user from
   * all invited collaborations within the current enterprise.
   *
   * @param requestBody Request body of submitJobToDeleteExternalUsersV2025R0 method
   * @param headers Headers of submitJobToDeleteExternalUsersV2025R0 method
   */
  public ExternalUsersSubmitDeleteJobResponseV2025R0 submitJobToDeleteExternalUsersV2025R0(
      ExternalUsersSubmitDeleteJobRequestV2025R0 requestBody,
      SubmitJobToDeleteExternalUsersV2025R0Headers headers) {
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
                            "/2.0/external_users/submit_delete_job"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(
        response.getData(), ExternalUsersSubmitDeleteJobResponseV2025R0.class);
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

    public ExternalUsersManager build() {
      return new ExternalUsersManager(this);
    }
  }
}
