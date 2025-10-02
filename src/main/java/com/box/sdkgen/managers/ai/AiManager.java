package com.box.sdkgen.managers.ai;

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
import com.box.sdkgen.schemas.aiagent.AiAgent;
import com.box.sdkgen.schemas.aiask.AiAsk;
import com.box.sdkgen.schemas.aiextract.AiExtract;
import com.box.sdkgen.schemas.aiextractstructured.AiExtractStructured;
import com.box.sdkgen.schemas.aiextractstructuredresponse.AiExtractStructuredResponse;
import com.box.sdkgen.schemas.airesponse.AiResponse;
import com.box.sdkgen.schemas.airesponsefull.AiResponseFull;
import com.box.sdkgen.schemas.aitextgen.AiTextGen;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class AiManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public AiManager() {
    this.networkSession = new NetworkSession();
  }

  protected AiManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Sends an AI request to supported LLMs and returns an answer specifically focused on the user's
   * question given the provided context.
   *
   * @param requestBody Request body of createAiAsk method
   */
  public AiResponseFull createAiAsk(AiAsk requestBody) {
    return createAiAsk(requestBody, new CreateAiAskHeaders());
  }

  /**
   * Sends an AI request to supported LLMs and returns an answer specifically focused on the user's
   * question given the provided context.
   *
   * @param requestBody Request body of createAiAsk method
   * @param headers Headers of createAiAsk method
   */
  public AiResponseFull createAiAsk(AiAsk requestBody, CreateAiAskHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/ai/ask"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    if (convertToString(response.getStatus()).equals("204")) {
      return null;
    }
    return JsonManager.deserialize(response.getData(), AiResponseFull.class);
  }

  /**
   * Sends an AI request to supported Large Language Models (LLMs) and returns generated text based
   * on the provided prompt.
   *
   * @param requestBody Request body of createAiTextGen method
   */
  public AiResponse createAiTextGen(AiTextGen requestBody) {
    return createAiTextGen(requestBody, new CreateAiTextGenHeaders());
  }

  /**
   * Sends an AI request to supported Large Language Models (LLMs) and returns generated text based
   * on the provided prompt.
   *
   * @param requestBody Request body of createAiTextGen method
   * @param headers Headers of createAiTextGen method
   */
  public AiResponse createAiTextGen(AiTextGen requestBody, CreateAiTextGenHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/ai/text_gen"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiResponse.class);
  }

  /**
   * Get the AI agent default config.
   *
   * @param queryParams Query parameters of getAiAgentDefaultConfig method
   */
  public AiAgent getAiAgentDefaultConfig(GetAiAgentDefaultConfigQueryParams queryParams) {
    return getAiAgentDefaultConfig(queryParams, new GetAiAgentDefaultConfigHeaders());
  }

  /**
   * Get the AI agent default config.
   *
   * @param queryParams Query parameters of getAiAgentDefaultConfig method
   * @param headers Headers of getAiAgentDefaultConfig method
   */
  public AiAgent getAiAgentDefaultConfig(
      GetAiAgentDefaultConfigQueryParams queryParams, GetAiAgentDefaultConfigHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("mode", convertToString(queryParams.getMode())),
                entryOf("language", convertToString(queryParams.getLanguage())),
                entryOf("model", convertToString(queryParams.getModel()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/ai_agent_default"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiAgent.class);
  }

  /**
   * Sends an AI request to supported Large Language Models (LLMs) and extracts metadata in form of
   * key-value pairs. In this request, both the prompt and the output can be freeform. Metadata
   * template setup before sending the request is not required.
   *
   * @param requestBody Request body of createAiExtract method
   */
  public AiResponse createAiExtract(AiExtract requestBody) {
    return createAiExtract(requestBody, new CreateAiExtractHeaders());
  }

  /**
   * Sends an AI request to supported Large Language Models (LLMs) and extracts metadata in form of
   * key-value pairs. In this request, both the prompt and the output can be freeform. Metadata
   * template setup before sending the request is not required.
   *
   * @param requestBody Request body of createAiExtract method
   * @param headers Headers of createAiExtract method
   */
  public AiResponse createAiExtract(AiExtract requestBody, CreateAiExtractHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/ai/extract"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiResponse.class);
  }

  /**
   * Sends an AI request to supported Large Language Models (LLMs) and returns extracted metadata as
   * a set of key-value pairs. For this request, you either need a metadata template or a list of
   * fields you want to extract. Input is **either** a metadata template or a list of fields to
   * ensure the structure. To learn more about creating templates, see [Creating metadata templates
   * in the Admin
   * Console](https://support.box.com/hc/en-us/articles/360044194033-Customizing-Metadata-Templates)
   * or use the [metadata template API](g://metadata/templates/create). This endpoint also supports
   * [Enhanced Extract
   * Agent](g://box-ai/ai-tutorials/extract-metadata-structured/#enhanced-extract-agent).
   *
   * @param requestBody Request body of createAiExtractStructured method
   */
  public AiExtractStructuredResponse createAiExtractStructured(AiExtractStructured requestBody) {
    return createAiExtractStructured(requestBody, new CreateAiExtractStructuredHeaders());
  }

  /**
   * Sends an AI request to supported Large Language Models (LLMs) and returns extracted metadata as
   * a set of key-value pairs. For this request, you either need a metadata template or a list of
   * fields you want to extract. Input is **either** a metadata template or a list of fields to
   * ensure the structure. To learn more about creating templates, see [Creating metadata templates
   * in the Admin
   * Console](https://support.box.com/hc/en-us/articles/360044194033-Customizing-Metadata-Templates)
   * or use the [metadata template API](g://metadata/templates/create). This endpoint also supports
   * [Enhanced Extract
   * Agent](g://box-ai/ai-tutorials/extract-metadata-structured/#enhanced-extract-agent).
   *
   * @param requestBody Request body of createAiExtractStructured method
   * @param headers Headers of createAiExtractStructured method
   */
  public AiExtractStructuredResponse createAiExtractStructured(
      AiExtractStructured requestBody, CreateAiExtractStructuredHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/ai/extract_structured"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiExtractStructuredResponse.class);
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

    public AiManager build() {
      return new AiManager(this);
    }
  }
}
