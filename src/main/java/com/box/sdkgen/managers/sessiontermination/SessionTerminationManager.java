package com.box.sdkgen.managers.sessiontermination;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.sessionterminationmessage.SessionTerminationMessage;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class SessionTerminationManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public SessionTerminationManager() {
    this.networkSession = new NetworkSession();
  }

  protected SessionTerminationManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Validates the roles and permissions of the user, and creates asynchronous jobs to terminate the
   * user's sessions. Returns the status for the POST request.
   *
   * @param requestBody Request body of terminateUsersSessions method
   */
  public SessionTerminationMessage terminateUsersSessions(
      TerminateUsersSessionsRequestBody requestBody) {
    return terminateUsersSessions(requestBody, new TerminateUsersSessionsHeaders());
  }

  /**
   * Validates the roles and permissions of the user, and creates asynchronous jobs to terminate the
   * user's sessions. Returns the status for the POST request.
   *
   * @param requestBody Request body of terminateUsersSessions method
   * @param headers Headers of terminateUsersSessions method
   */
  public SessionTerminationMessage terminateUsersSessions(
      TerminateUsersSessionsRequestBody requestBody, TerminateUsersSessionsHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/users/terminate_sessions"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SessionTerminationMessage.class);
  }

  /**
   * Validates the roles and permissions of the group, and creates asynchronous jobs to terminate
   * the group's sessions. Returns the status for the POST request.
   *
   * @param requestBody Request body of terminateGroupsSessions method
   */
  public SessionTerminationMessage terminateGroupsSessions(
      TerminateGroupsSessionsRequestBody requestBody) {
    return terminateGroupsSessions(requestBody, new TerminateGroupsSessionsHeaders());
  }

  /**
   * Validates the roles and permissions of the group, and creates asynchronous jobs to terminate
   * the group's sessions. Returns the status for the POST request.
   *
   * @param requestBody Request body of terminateGroupsSessions method
   * @param headers Headers of terminateGroupsSessions method
   */
  public SessionTerminationMessage terminateGroupsSessions(
      TerminateGroupsSessionsRequestBody requestBody, TerminateGroupsSessionsHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/groups/terminate_sessions"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SessionTerminationMessage.class);
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

    public SessionTerminationManager build() {
      return new SessionTerminationManager(this);
    }
  }
}
