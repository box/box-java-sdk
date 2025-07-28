package com.box.sdkgen.schemas.aistudioagentlongtexttool;

import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextTool;
import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextToolEmbeddingsField;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.box.sdkgen.schemas.aillmendpointparamsaws.AiLlmEndpointParamsAws;
import com.box.sdkgen.schemas.aillmendpointparamsgoogle.AiLlmEndpointParamsGoogle;
import com.box.sdkgen.schemas.aillmendpointparamsibm.AiLlmEndpointParamsIbm;
import com.box.sdkgen.schemas.aillmendpointparamsopenai.AiLlmEndpointParamsOpenAi;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiStudioAgentLongTextTool extends AiAgentLongTextTool {

  @JsonProperty("is_custom_instructions_included")
  protected Boolean isCustomInstructionsIncluded;

  public AiStudioAgentLongTextTool() {
    super();
  }

  protected AiStudioAgentLongTextTool(Builder builder) {
    super(builder);
    this.isCustomInstructionsIncluded = builder.isCustomInstructionsIncluded;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getIsCustomInstructionsIncluded() {
    return isCustomInstructionsIncluded;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiStudioAgentLongTextTool casted = (AiStudioAgentLongTextTool) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate)
        && Objects.equals(embeddings, casted.embeddings)
        && Objects.equals(isCustomInstructionsIncluded, casted.isCustomInstructionsIncluded);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        model,
        numTokensForCompletion,
        llmEndpointParams,
        systemMessage,
        promptTemplate,
        embeddings,
        isCustomInstructionsIncluded);
  }

  @Override
  public String toString() {
    return "AiStudioAgentLongTextTool{"
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
        + ", "
        + "embeddings='"
        + embeddings
        + '\''
        + ", "
        + "isCustomInstructionsIncluded='"
        + isCustomInstructionsIncluded
        + '\''
        + "}";
  }

  public static class Builder extends AiAgentLongTextTool.Builder {

    protected Boolean isCustomInstructionsIncluded;

    public Builder isCustomInstructionsIncluded(Boolean isCustomInstructionsIncluded) {
      this.isCustomInstructionsIncluded = isCustomInstructionsIncluded;
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

    @Override
    public Builder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    @Override
    public Builder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    @Override
    public Builder embeddings(AiAgentLongTextToolEmbeddingsField embeddings) {
      this.embeddings = embeddings;
      return this;
    }

    public AiStudioAgentLongTextTool build() {
      return new AiStudioAgentLongTextTool(this);
    }
  }
}
