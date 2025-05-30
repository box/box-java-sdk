package com.box.sdkgen.schemas.aistudioagentbasictexttool;

import com.box.sdkgen.schemas.aiagentbasictexttool.AiAgentBasicTextTool;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AiStudioAgentBasicTextTool extends AiAgentBasicTextTool {

  @JsonProperty("is_custom_instructions_included")
  protected Boolean isCustomInstructionsIncluded;

  public AiStudioAgentBasicTextTool() {
    super();
  }

  protected AiStudioAgentBasicTextTool(AiStudioAgentBasicTextToolBuilder builder) {
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
    AiStudioAgentBasicTextTool casted = (AiStudioAgentBasicTextTool) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate)
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
        isCustomInstructionsIncluded);
  }

  @Override
  public String toString() {
    return "AiStudioAgentBasicTextTool{"
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
        + "isCustomInstructionsIncluded='"
        + isCustomInstructionsIncluded
        + '\''
        + "}";
  }

  public static class AiStudioAgentBasicTextToolBuilder extends AiAgentBasicTextToolBuilder {

    protected Boolean isCustomInstructionsIncluded;

    public AiStudioAgentBasicTextToolBuilder isCustomInstructionsIncluded(
        Boolean isCustomInstructionsIncluded) {
      this.isCustomInstructionsIncluded = isCustomInstructionsIncluded;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolBuilder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolBuilder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolBuilder llmEndpointParams(
        AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolBuilder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolBuilder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    public AiStudioAgentBasicTextTool build() {
      return new AiStudioAgentBasicTextTool(this);
    }
  }
}
