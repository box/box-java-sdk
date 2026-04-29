package com.box.sdkgen.managers.automateworkflows;

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
import com.box.sdkgen.schemas.v2026r0.automateworkflowstartrequestv2026r0.AutomateWorkflowStartRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.automateworkflowsv2026r0.AutomateWorkflowsV2026R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class AutomateWorkflowsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public AutomateWorkflowsManager() {
    this.networkSession = new NetworkSession();
  }

  protected AutomateWorkflowsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Returns workflow actions from Automate for a folder, using the `WORKFLOW` action category.
   *
   * @param queryParams Query parameters of getAutomateWorkflowsV2026R0 method
   */
  public AutomateWorkflowsV2026R0 getAutomateWorkflowsV2026R0(
      GetAutomateWorkflowsV2026R0QueryParams queryParams) {
    return getAutomateWorkflowsV2026R0(queryParams, new GetAutomateWorkflowsV2026R0Headers());
  }

  /**
   * Returns workflow actions from Automate for a folder, using the `WORKFLOW` action category.
   *
   * @param queryParams Query parameters of getAutomateWorkflowsV2026R0 method
   * @param headers Headers of getAutomateWorkflowsV2026R0 method
   */
  public AutomateWorkflowsV2026R0 getAutomateWorkflowsV2026R0(
      GetAutomateWorkflowsV2026R0QueryParams queryParams,
      GetAutomateWorkflowsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("folder_id", convertToString(queryParams.getFolderId())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
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
                            "/2.0/automate_workflows"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AutomateWorkflowsV2026R0.class);
  }

  /**
   * Starts an Automate workflow manually by using a workflow action ID and file IDs.
   *
   * @param workflowId The ID of the workflow. Example: "12345"
   * @param requestBody Request body of createAutomateWorkflowStartV2026R0 method
   */
  public void createAutomateWorkflowStartV2026R0(
      String workflowId, AutomateWorkflowStartRequestV2026R0 requestBody) {
    createAutomateWorkflowStartV2026R0(
        workflowId, requestBody, new CreateAutomateWorkflowStartV2026R0Headers());
  }

  /**
   * Starts an Automate workflow manually by using a workflow action ID and file IDs.
   *
   * @param workflowId The ID of the workflow. Example: "12345"
   * @param requestBody Request body of createAutomateWorkflowStartV2026R0 method
   * @param headers Headers of createAutomateWorkflowStartV2026R0 method
   */
  public void createAutomateWorkflowStartV2026R0(
      String workflowId,
      AutomateWorkflowStartRequestV2026R0 requestBody,
      CreateAutomateWorkflowStartV2026R0Headers headers) {
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
                            "/2.0/automate_workflows/",
                            convertToString(workflowId),
                            "/start"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public AutomateWorkflowsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new AutomateWorkflowsManager(this);
    }
  }
}
