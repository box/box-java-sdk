package com.box.sdkgen.managers.workflows;

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
import com.box.sdkgen.schemas.workflows.Workflows;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class WorkflowsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public WorkflowsManager() {
    this.networkSession = new NetworkSession();
  }

  protected WorkflowsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public Workflows getWorkflows(GetWorkflowsQueryParams queryParams) {
    return getWorkflows(queryParams, new GetWorkflowsHeaders());
  }

  public Workflows getWorkflows(GetWorkflowsQueryParams queryParams, GetWorkflowsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("folder_id", convertToString(queryParams.getFolderId())),
                entryOf("trigger_type", convertToString(queryParams.getTriggerType())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/workflows"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Workflows.class);
  }

  public void startWorkflow(String workflowId, StartWorkflowRequestBody requestBody) {
    startWorkflow(workflowId, requestBody, new StartWorkflowHeaders());
  }

  public void startWorkflow(
      String workflowId, StartWorkflowRequestBody requestBody, StartWorkflowHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/workflows/",
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

    public WorkflowsManager build() {
      return new WorkflowsManager(this);
    }
  }
}
