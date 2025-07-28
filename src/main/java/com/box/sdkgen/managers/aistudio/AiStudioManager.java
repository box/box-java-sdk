package com.box.sdkgen.managers.aistudio;

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
import com.box.sdkgen.schemas.aimultipleagentresponse.AiMultipleAgentResponse;
import com.box.sdkgen.schemas.aisingleagentresponsefull.AiSingleAgentResponseFull;
import com.box.sdkgen.schemas.createaiagent.CreateAiAgent;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class AiStudioManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public AiStudioManager() {
    this.networkSession = new NetworkSession();
  }

  protected AiStudioManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public AiMultipleAgentResponse getAiAgents() {
    return getAiAgents(new GetAiAgentsQueryParams(), new GetAiAgentsHeaders());
  }

  public AiMultipleAgentResponse getAiAgents(GetAiAgentsQueryParams queryParams) {
    return getAiAgents(queryParams, new GetAiAgentsHeaders());
  }

  public AiMultipleAgentResponse getAiAgents(GetAiAgentsHeaders headers) {
    return getAiAgents(new GetAiAgentsQueryParams(), headers);
  }

  public AiMultipleAgentResponse getAiAgents(
      GetAiAgentsQueryParams queryParams, GetAiAgentsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("mode", convertToString(queryParams.getMode())),
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("agent_state", convertToString(queryParams.getAgentState())),
                entryOf("include_box_default", convertToString(queryParams.getIncludeBoxDefault())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/ai_agents"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiMultipleAgentResponse.class);
  }

  public AiSingleAgentResponseFull createAiAgent(CreateAiAgent requestBody) {
    return createAiAgent(requestBody, new CreateAiAgentHeaders());
  }

  public AiSingleAgentResponseFull createAiAgent(
      CreateAiAgent requestBody, CreateAiAgentHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/ai_agents"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiSingleAgentResponseFull.class);
  }

  public AiSingleAgentResponseFull updateAiAgentById(String agentId, CreateAiAgent requestBody) {
    return updateAiAgentById(agentId, requestBody, new UpdateAiAgentByIdHeaders());
  }

  public AiSingleAgentResponseFull updateAiAgentById(
      String agentId, CreateAiAgent requestBody, UpdateAiAgentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/ai_agents/",
                            convertToString(agentId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiSingleAgentResponseFull.class);
  }

  public AiSingleAgentResponseFull getAiAgentById(String agentId) {
    return getAiAgentById(agentId, new GetAiAgentByIdQueryParams(), new GetAiAgentByIdHeaders());
  }

  public AiSingleAgentResponseFull getAiAgentById(
      String agentId, GetAiAgentByIdQueryParams queryParams) {
    return getAiAgentById(agentId, queryParams, new GetAiAgentByIdHeaders());
  }

  public AiSingleAgentResponseFull getAiAgentById(String agentId, GetAiAgentByIdHeaders headers) {
    return getAiAgentById(agentId, new GetAiAgentByIdQueryParams(), headers);
  }

  public AiSingleAgentResponseFull getAiAgentById(
      String agentId, GetAiAgentByIdQueryParams queryParams, GetAiAgentByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/ai_agents/",
                            convertToString(agentId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiSingleAgentResponseFull.class);
  }

  public void deleteAiAgentById(String agentId) {
    deleteAiAgentById(agentId, new DeleteAiAgentByIdHeaders());
  }

  public void deleteAiAgentById(String agentId, DeleteAiAgentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/ai_agents/",
                            convertToString(agentId)),
                        "DELETE")
                    .headers(headersMap)
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

    public AiStudioManager build() {
      return new AiStudioManager(this);
    }
  }
}
