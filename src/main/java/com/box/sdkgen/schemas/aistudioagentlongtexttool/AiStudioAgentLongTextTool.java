package com.box.sdkgen.schemas.aistudioagentlongtexttool;

import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextTool;
import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextToolEmbeddingsField;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AiStudioAgentLongTextTool extends AiAgentLongTextTool {

  @JsonProperty("is_custom_instructions_included")
  protected Boolean isCustomInstructionsIncluded;

  public AiStudioAgentLongTextTool() {
    super();
  }

  protected AiStudioAgentLongTextTool(AiStudioAgentLongTextToolBuilder builder) {
    super(builder);
    this.isCustomInstructionsIncluded = builder.isCustomInstructionsIncluded;
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

  public static class AiStudioAgentLongTextToolBuilder extends AiAgentLongTextToolBuilder {

    protected Boolean isCustomInstructionsIncluded;

    public AiStudioAgentLongTextToolBuilder isCustomInstructionsIncluded(
        Boolean isCustomInstructionsIncluded) {
      this.isCustomInstructionsIncluded = isCustomInstructionsIncluded;
      return this;
    }

    @Override
    public AiStudioAgentLongTextToolBuilder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public AiStudioAgentLongTextToolBuilder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public AiStudioAgentLongTextToolBuilder llmEndpointParams(
        AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    @Override
    public AiStudioAgentLongTextToolBuilder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    @Override
    public AiStudioAgentLongTextToolBuilder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    @Override
    public AiStudioAgentLongTextToolBuilder embeddings(
        AiAgentLongTextToolEmbeddingsField embeddings) {
      this.embeddings = embeddings;
      return this;
    }

    public AiStudioAgentLongTextTool build() {
      return new AiStudioAgentLongTextTool(this);
    }
  }
}
