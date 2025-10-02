package com.box.sdkgen.schemas.aiagentbasictexttooltextgen;

import com.box.sdkgen.schemas.aiagentbasictexttoolbase.AiAgentBasicTextToolBase;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.box.sdkgen.schemas.aillmendpointparamsaws.AiLlmEndpointParamsAws;
import com.box.sdkgen.schemas.aillmendpointparamsgoogle.AiLlmEndpointParamsGoogle;
import com.box.sdkgen.schemas.aillmendpointparamsibm.AiLlmEndpointParamsIbm;
import com.box.sdkgen.schemas.aillmendpointparamsopenai.AiLlmEndpointParamsOpenAi;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** AI agent processor used to handle basic text. */
@JsonFilter("nullablePropertyFilter")
public class AiAgentBasicTextToolTextGen extends AiAgentBasicTextToolBase {

  /**
   * System messages aim at helping the LLM understand its role and what it is supposed to do. The
   * input for `{current_date}` is optional, depending on the use.
   */
  @JsonProperty("system_message")
  protected String systemMessage;

  /**
   * The prompt template contains contextual information of the request and the user prompt.
   *
   * <p>When using the `prompt_template` parameter, you **must include** input for
   * `{user_question}`. Inputs for `{current_date}` and `{content}` are optional, depending on the
   * use.
   */
  @JsonProperty("prompt_template")
  protected String promptTemplate;

  public AiAgentBasicTextToolTextGen() {
    super();
  }

  protected AiAgentBasicTextToolTextGen(Builder builder) {
    super(builder);
    this.systemMessage = builder.systemMessage;
    this.promptTemplate = builder.promptTemplate;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getSystemMessage() {
    return systemMessage;
  }

  public String getPromptTemplate() {
    return promptTemplate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentBasicTextToolTextGen casted = (AiAgentBasicTextToolTextGen) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        model, numTokensForCompletion, llmEndpointParams, systemMessage, promptTemplate);
  }

  @Override
  public String toString() {
    return "AiAgentBasicTextToolTextGen{"
        + "model='"
        + model
        + '\''
        + ", "
        + "numTokensForCompletion='"
        + numTokensForCompletion
        + '\''
        + ", "
        + "llmEndpointParams='"
        + llmEndpointParams
        + '\''
        + ", "
        + "systemMessage='"
        + systemMessage
        + '\''
        + ", "
        + "promptTemplate='"
        + promptTemplate
        + '\''
        + "}";
  }

  public static class Builder extends AiAgentBasicTextToolBase.Builder {

    protected String systemMessage;

    protected String promptTemplate;

    public Builder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    public Builder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    @Override
    public Builder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public Builder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParamsOpenAi llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParamsGoogle llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParamsAws llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParamsIbm llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    public AiAgentBasicTextToolTextGen build() {
      return new AiAgentBasicTextToolTextGen(this);
    }
  }
}
