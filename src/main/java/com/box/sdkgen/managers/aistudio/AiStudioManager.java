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

  /** Lists AI agents based on the provided parameters. */
  public AiMultipleAgentResponse getAiAgents() {
    return getAiAgents(new GetAiAgentsQueryParams(), new GetAiAgentsHeaders());
  }

  /**
   * Lists AI agents based on the provided parameters.
   *
   * @param queryParams Query parameters of getAiAgents method
   */
  public AiMultipleAgentResponse getAiAgents(GetAiAgentsQueryParams queryParams) {
    return getAiAgents(queryParams, new GetAiAgentsHeaders());
  }

  /**
   * Lists AI agents based on the provided parameters.
   *
   * @param headers Headers of getAiAgents method
   */
  public AiMultipleAgentResponse getAiAgents(GetAiAgentsHeaders headers) {
    return getAiAgents(new GetAiAgentsQueryParams(), headers);
  }

  /**
   * Lists AI agents based on the provided parameters.
   *
   * @param queryParams Query parameters of getAiAgents method
   * @param headers Headers of getAiAgents method
   */
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

  /**
   * Creates an AI agent. At least one of the following capabilities must be provided: `ask`,
   * `text_gen`, `extract`.
   *
   * @param requestBody Request body of createAiAgent method
   */
  public AiSingleAgentResponseFull createAiAgent(CreateAiAgent requestBody) {
    return createAiAgent(requestBody, new CreateAiAgentHeaders());
  }

  /**
   * Creates an AI agent. At least one of the following capabilities must be provided: `ask`,
   * `text_gen`, `extract`.
   *
   * @param requestBody Request body of createAiAgent method
   * @param headers Headers of createAiAgent method
   */
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

  /**
   * Updates an AI agent.
   *
   * @param agentId The ID of the agent to update. Example: "1234"
   * @param requestBody Request body of updateAiAgentById method
   */
  public AiSingleAgentResponseFull updateAiAgentById(String agentId, CreateAiAgent requestBody) {
    return updateAiAgentById(agentId, requestBody, new UpdateAiAgentByIdHeaders());
  }

  /**
   * Updates an AI agent.
   *
   * @param agentId The ID of the agent to update. Example: "1234"
   * @param requestBody Request body of updateAiAgentById method
   * @param headers Headers of updateAiAgentById method
   */
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

  /**
   * Gets an AI Agent using the `agent_id` parameter.
   *
   * @param agentId The agent id to get. Example: "1234"
   */
  public AiSingleAgentResponseFull getAiAgentById(String agentId) {
    return getAiAgentById(agentId, new GetAiAgentByIdQueryParams(), new GetAiAgentByIdHeaders());
  }

  /**
   * Gets an AI Agent using the `agent_id` parameter.
   *
   * @param agentId The agent id to get. Example: "1234"
   * @param queryParams Query parameters of getAiAgentById method
   */
  public AiSingleAgentResponseFull getAiAgentById(
      String agentId, GetAiAgentByIdQueryParams queryParams) {
    return getAiAgentById(agentId, queryParams, new GetAiAgentByIdHeaders());
  }

  /**
   * Gets an AI Agent using the `agent_id` parameter.
   *
   * @param agentId The agent id to get. Example: "1234"
   * @param headers Headers of getAiAgentById method
   */
  public AiSingleAgentResponseFull getAiAgentById(String agentId, GetAiAgentByIdHeaders headers) {
    return getAiAgentById(agentId, new GetAiAgentByIdQueryParams(), headers);
  }

  /**
   * Gets an AI Agent using the `agent_id` parameter.
   *
   * @param agentId The agent id to get. Example: "1234"
   * @param queryParams Query parameters of getAiAgentById method
   * @param headers Headers of getAiAgentById method
   */
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

  /**
   * Deletes an AI agent using the provided parameters.
   *
   * @param agentId The ID of the agent to delete. Example: "1234"
   */
  public void deleteAiAgentById(String agentId) {
    deleteAiAgentById(agentId, new DeleteAiAgentByIdHeaders());
  }

  /**
   * Deletes an AI agent using the provided parameters.
   *
   * @param agentId The ID of the agent to delete. Example: "1234"
   * @param headers Headers of deleteAiAgentById method
   */
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public AiStudioManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new AiStudioManager(this);
    }
  }
}
