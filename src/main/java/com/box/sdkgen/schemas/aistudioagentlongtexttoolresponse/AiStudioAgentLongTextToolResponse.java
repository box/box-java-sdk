package com.box.sdkgen.schemas.aistudioagentlongtexttoolresponse;

import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextToolEmbeddingsField;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.box.sdkgen.schemas.aillmendpointparamsaws.AiLlmEndpointParamsAws;
import com.box.sdkgen.schemas.aillmendpointparamsgoogle.AiLlmEndpointParamsGoogle;
import com.box.sdkgen.schemas.aillmendpointparamsibm.AiLlmEndpointParamsIbm;
import com.box.sdkgen.schemas.aillmendpointparamsopenai.AiLlmEndpointParamsOpenAi;
import com.box.sdkgen.schemas.aistudioagentlongtexttool.AiStudioAgentLongTextTool;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

/** AI agent processor used to to handle longer text. */
@JsonFilter("nullablePropertyFilter")
public class AiStudioAgentLongTextToolResponse extends AiStudioAgentLongTextTool {

  /** Warnings concerning tool. */
  protected List<String> warnings;

  public AiStudioAgentLongTextToolResponse() {
    super();
  }

  protected AiStudioAgentLongTextToolResponse(Builder builder) {
    super(builder);
    this.warnings = builder.warnings;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<String> getWarnings() {
    return warnings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiStudioAgentLongTextToolResponse casted = (AiStudioAgentLongTextToolResponse) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate)
        && Objects.equals(embeddings, casted.embeddings)
        && Objects.equals(isCustomInstructionsIncluded, casted.isCustomInstructionsIncluded)
        && Objects.equals(warnings, casted.warnings);
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
        isCustomInstructionsIncluded,
        warnings);
  }

  @Override
  public String toString() {
    return "AiStudioAgentLongTextToolResponse{"
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
        + ", "
        + "warnings='"
        + warnings
        + '\''
        + "}";
  }

  public static class Builder extends AiStudioAgentLongTextTool.Builder {

    protected List<String> warnings;

    public Builder warnings(List<String> warnings) {
      this.warnings = warnings;
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

    @Override
    public Builder isCustomInstructionsIncluded(Boolean isCustomInstructionsIncluded) {
      this.isCustomInstructionsIncluded = isCustomInstructionsIncluded;
      return this;
    }

    public AiStudioAgentLongTextToolResponse build() {
      return new AiStudioAgentLongTextToolResponse(this);
    }
  }
}
