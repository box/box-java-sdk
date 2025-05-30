package com.box.sdkgen.schemas.aistudioagentbasictexttoolresponse;

import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.box.sdkgen.schemas.aistudioagentbasictexttool.AiStudioAgentBasicTextTool;
import java.util.List;
import java.util.Objects;

public class AiStudioAgentBasicTextToolResponse extends AiStudioAgentBasicTextTool {

  protected List<String> warnings;

  public AiStudioAgentBasicTextToolResponse() {
    super();
  }

  protected AiStudioAgentBasicTextToolResponse(AiStudioAgentBasicTextToolResponseBuilder builder) {
    super(builder);
    this.warnings = builder.warnings;
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
    AiStudioAgentBasicTextToolResponse casted = (AiStudioAgentBasicTextToolResponse) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate)
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
        isCustomInstructionsIncluded,
        warnings);
  }

  @Override
  public String toString() {
    return "AiStudioAgentBasicTextToolResponse{"
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
        + ", "
        + "warnings='"
        + warnings
        + '\''
        + "}";
  }

  public static class AiStudioAgentBasicTextToolResponseBuilder
      extends AiStudioAgentBasicTextToolBuilder {

    protected List<String> warnings;

    public AiStudioAgentBasicTextToolResponseBuilder warnings(List<String> warnings) {
      this.warnings = warnings;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolResponseBuilder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolResponseBuilder numTokensForCompletion(
        Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolResponseBuilder llmEndpointParams(
        AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolResponseBuilder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolResponseBuilder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    @Override
    public AiStudioAgentBasicTextToolResponseBuilder isCustomInstructionsIncluded(
        Boolean isCustomInstructionsIncluded) {
      this.isCustomInstructionsIncluded = isCustomInstructionsIncluded;
      return this;
    }

    public AiStudioAgentBasicTextToolResponse build() {
      return new AiStudioAgentBasicTextToolResponse(this);
    }
  }
}
