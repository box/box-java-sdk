package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;

/** Represents the AI agent basic tool used to generate text. */
public class BoxAIAgentTextGenBasicGen extends BoxJSONObject {
  /**
   * How the content should be included in a request to the LLM. Input for {content} is optional,
   * depending on the use.
   */
  private String contentTemplate;
  /** Embeddings used by the AI agent. */
  private BoxAIAgentEmbeddings embeddings;
  /** The parameters for the LLM endpoint specific to OpenAI / Google models. */
  private BoxAIAgentLLMEndpointParams llmEndpointParams;
  /** The model used for the AI Agent for generating text. */
  private String model;
  /** The number of tokens for completion. */
  private int numTokensForCompletion;
  /**
   * The prompt template contains contextual information of the request and the user prompt. When
   * passing prompt_template parameters, you must include inputs for {user_question} and {content}.
   * Input for {current_date} is optional, depending on the use.
   */
  private String promptTemplate;
  /** System messages try to help the LLM "understand" its role and what it is supposed to do. */
  private String systemMessage;

  /**
   * Constructs an AI agent with default settings.
   *
   * @param contentTemplate How the content should be included in a request to the LLM. Input for
   *     {content} is optional, depending on the use.
   * @param embeddings Embeddings used by the AI agent.
   * @param llmEndpointParams The parameters for the LLM endpoint specific to OpenAI / Google
   *     models.
   * @param model The model used for the AI Agent for generating text.
   * @param numTokensForCompletion The number of tokens for completion.
   * @param promptTemplate The prompt template contains contextual information of the request and
   *     the user prompt. When passing prompt_template parameters, you must include inputs for
   *     {user_question} and {content}. Input for {current_date} is optional, depending on the use.
   * @param systemMessage System messages try to help the LLM "understand" its role and what it is
   *     supposed to do.
   */
  public BoxAIAgentTextGenBasicGen(
      String contentTemplate,
      BoxAIAgentEmbeddings embeddings,
      BoxAIAgentLLMEndpointParamsOpenAI llmEndpointParams,
      String model,
      int numTokensForCompletion,
      String promptTemplate,
      String systemMessage) {
    this.contentTemplate = contentTemplate;
    this.embeddings = embeddings;
    this.llmEndpointParams = llmEndpointParams;
    this.model = model;
    this.numTokensForCompletion = numTokensForCompletion;
    this.promptTemplate = promptTemplate;
    this.systemMessage = systemMessage;
  }

  /**
   * Constructs an AI agent with default settings.
   *
   * @param jsonObject JSON object representing the AI agent.
   */
  public BoxAIAgentTextGenBasicGen(JsonObject jsonObject) {
    super(jsonObject);
  }

  /**
   * Gets how the content should be included in a request to the LLM. Input for {content} is
   * optional, depending on the use.
   *
   * @return How the content should be included in a request to the LLM. Input for {content} is
   *     optional, depending on the use.
   */
  public String getContentTemplate() {
    return contentTemplate;
  }

  /**
   * Sets how the content should be included in a request to the LLM. Input for {content} is
   * optional, depending on the use.
   *
   * @param contentTemplate How the content should be included in a request to the LLM. Input for
   *     {content} is optional, depending on the use.
   */
  public void setContentTemplate(String contentTemplate) {
    this.contentTemplate = contentTemplate;
  }

  /**
   * Gets the embeddings used by the AI agent.
   *
   * @return The embeddings used by the AI agent.
   */
  public BoxAIAgentEmbeddings getEmbeddings() {
    return embeddings;
  }

  /**
   * Sets the embeddings used by the AI agent.
   *
   * @param embeddings The embeddings used by the AI agent.
   */
  public void setEmbeddings(BoxAIAgentEmbeddings embeddings) {
    this.embeddings = embeddings;
  }

  /**
   * Gets the parameters for the LLM endpoint specific to OpenAI / Google models.
   *
   * @return The parameters for the LLM endpoint specific to OpenAI / Google models.
   */
  public BoxAIAgentLLMEndpointParams getLlmEndpointParams() {
    return llmEndpointParams;
  }

  /**
   * Sets the parameters for the LLM endpoint specific to OpenAI / Google models.
   *
   * @param llmEndpointParams The parameters for the LLM endpoint specific to OpenAI / Google
   *     models.
   */
  public void setLlmEndpointParams(BoxAIAgentLLMEndpointParamsOpenAI llmEndpointParams) {
    this.llmEndpointParams = llmEndpointParams;
  }

  /**
   * Gets the model used for the AI Agent for generating text.
   *
   * @return The model used for the AI Agent for generating text.
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets the model used for the AI Agent for generating text.
   *
   * @param model The model used for the AI Agent for generating text.
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Gets the number of tokens for completion.
   *
   * @return The number of tokens for completion.
   */
  public int getNumTokensForCompletion() {
    return numTokensForCompletion;
  }

  /**
   * Sets the number of tokens for completion.
   *
   * @param numTokensForCompletion The number of tokens for completion.
   */
  public void setNumTokensForCompletion(int numTokensForCompletion) {
    this.numTokensForCompletion = numTokensForCompletion;
  }

  /**
   * Gets the prompt template contains contextual information of the request and the user prompt.
   * When passing prompt_template parameters, you must include inputs for {user_question} and
   * {content}. Input for {current_date} is optional, depending on the use.
   *
   * @return The prompt template contains contextual information of the request and the user prompt.
   */
  public String getPromptTemplate() {
    return promptTemplate;
  }

  /**
   * Sets the prompt template contains contextual information of the request and the user prompt.
   * When passing prompt_template parameters, you must include inputs for {user_question} and
   * {content}. Input for {current_date} is optional, depending on the use.
   *
   * @param promptTemplate The prompt template contains contextual information of the request and
   *     the user prompt.
   */
  public void setPromptTemplate(String promptTemplate) {
    this.promptTemplate = promptTemplate;
  }

  /**
   * Gets the system messages try to help the LLM "understand" its role and what it is supposed to
   * do.
   *
   * @return The system messages try to help the LLM "understand" its role and what it is supposed
   *     to do.
   */
  public String getSystemMessage() {
    return systemMessage;
  }

  /**
   * Sets the system messages try to help the LLM "understand" its role and what it is supposed to
   * do.
   *
   * @param systemMessage The system messages try to help the LLM "understand" its role and what it
   *     is supposed to do.
   */
  public void setSystemMessage(String systemMessage) {
    this.systemMessage = systemMessage;
  }

  @Override
  void parseJSONMember(JsonObject.Member member) {
    super.parseJSONMember(member);
    String memberName = member.getName();
    switch (memberName) {
      case "content_template":
        this.contentTemplate = member.getValue().asString();
        break;
      case "embeddings":
        this.embeddings = new BoxAIAgentEmbeddings(member.getValue().asObject());
        break;
      case "llm_endpoint_params":
        this.llmEndpointParams = BoxAIAgentLLMEndpointParams.parse(member.getValue().asObject());
        break;
      case "model":
        this.model = member.getValue().asString();
        break;
      case "num_tokens_for_completion":
        this.numTokensForCompletion = member.getValue().asInt();
        break;
      case "prompt_template":
        this.promptTemplate = member.getValue().asString();
        break;
      case "system_message":
        this.systemMessage = member.getValue().asString();
        break;
      default:
        break;
    }
  }

  public JsonObject getJSONObject() {
    JsonObject jsonObject = new JsonObject();
    JsonUtils.addIfNotNull(jsonObject, "content_template", this.contentTemplate);
    if (this.embeddings != null) {
      jsonObject.add("embeddings", this.embeddings.getJSONObject());
    }
    if (this.llmEndpointParams != null) {
      jsonObject.add("llm_endpoint_params", this.llmEndpointParams.getJSONObject());
    }
    JsonUtils.addIfNotNull(jsonObject, "model", this.model);
    JsonUtils.addIfNotNull(jsonObject, "num_tokens_for_completion", this.numTokensForCompletion);
    JsonUtils.addIfNotNull(jsonObject, "prompt_template", this.promptTemplate);
    JsonUtils.addIfNotNull(jsonObject, "system_message", this.systemMessage);
    return jsonObject;
  }
}
