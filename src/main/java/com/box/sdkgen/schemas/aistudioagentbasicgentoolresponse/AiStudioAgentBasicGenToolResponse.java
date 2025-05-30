package com.box.sdkgen.schemas.aistudioagentbasicgentoolresponse;

import com.box.sdkgen.schemas.aiagentlongtexttooltextgen.AiAgentLongTextToolTextGenEmbeddingsField;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.box.sdkgen.schemas.aistudioagentbasicgentool.AiStudioAgentBasicGenTool;
import java.util.List;
import java.util.Objects;

public class AiStudioAgentBasicGenToolResponse extends AiStudioAgentBasicGenTool {

  protected List<String> warnings;

  public AiStudioAgentBasicGenToolResponse() {
    super();
  }

  protected AiStudioAgentBasicGenToolResponse(AiStudioAgentBasicGenToolResponseBuilder builder) {
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
    AiStudioAgentBasicGenToolResponse casted = (AiStudioAgentBasicGenToolResponse) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate)
        && Objects.equals(embeddings, casted.embeddings)
        && Objects.equals(contentTemplate, casted.contentTemplate)
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
        contentTemplate,
        isCustomInstructionsIncluded,
        warnings);
  }

  @Override
  public String toString() {
    return "AiStudioAgentBasicGenToolResponse{"
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
        + "contentTemplate='"
        + contentTemplate
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

  public static class AiStudioAgentBasicGenToolResponseBuilder
      extends AiStudioAgentBasicGenToolBuilder {

    protected List<String> warnings;

    public AiStudioAgentBasicGenToolResponseBuilder warnings(List<String> warnings) {
      this.warnings = warnings;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolResponseBuilder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolResponseBuilder numTokensForCompletion(
        Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolResponseBuilder llmEndpointParams(
        AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolResponseBuilder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolResponseBuilder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolResponseBuilder embeddings(
        AiAgentLongTextToolTextGenEmbeddingsField embeddings) {
      this.embeddings = embeddings;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolResponseBuilder contentTemplate(String contentTemplate) {
      this.contentTemplate = contentTemplate;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolResponseBuilder isCustomInstructionsIncluded(
        Boolean isCustomInstructionsIncluded) {
      this.isCustomInstructionsIncluded = isCustomInstructionsIncluded;
      return this;
    }

    public AiStudioAgentBasicGenToolResponse build() {
      return new AiStudioAgentBasicGenToolResponse(this);
    }
  }
}
