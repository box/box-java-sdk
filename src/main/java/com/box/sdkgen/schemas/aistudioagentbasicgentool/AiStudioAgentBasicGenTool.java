package com.box.sdkgen.schemas.aistudioagentbasicgentool;

import com.box.sdkgen.schemas.aiagentbasicgentool.AiAgentBasicGenTool;
import com.box.sdkgen.schemas.aiagentlongtexttooltextgen.AiAgentLongTextToolTextGenEmbeddingsField;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AiStudioAgentBasicGenTool extends AiAgentBasicGenTool {

  @JsonProperty("is_custom_instructions_included")
  protected Boolean isCustomInstructionsIncluded;

  public AiStudioAgentBasicGenTool() {
    super();
  }

  protected AiStudioAgentBasicGenTool(AiStudioAgentBasicGenToolBuilder builder) {
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
    AiStudioAgentBasicGenTool casted = (AiStudioAgentBasicGenTool) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate)
        && Objects.equals(embeddings, casted.embeddings)
        && Objects.equals(contentTemplate, casted.contentTemplate)
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
        contentTemplate,
        isCustomInstructionsIncluded);
  }

  @Override
  public String toString() {
    return "AiStudioAgentBasicGenTool{"
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
        + "}";
  }

  public static class AiStudioAgentBasicGenToolBuilder extends AiAgentBasicGenToolBuilder {

    protected Boolean isCustomInstructionsIncluded;

    public AiStudioAgentBasicGenToolBuilder isCustomInstructionsIncluded(
        Boolean isCustomInstructionsIncluded) {
      this.isCustomInstructionsIncluded = isCustomInstructionsIncluded;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolBuilder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolBuilder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolBuilder llmEndpointParams(
        AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolBuilder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolBuilder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolBuilder embeddings(
        AiAgentLongTextToolTextGenEmbeddingsField embeddings) {
      this.embeddings = embeddings;
      return this;
    }

    @Override
    public AiStudioAgentBasicGenToolBuilder contentTemplate(String contentTemplate) {
      this.contentTemplate = contentTemplate;
      return this;
    }

    public AiStudioAgentBasicGenTool build() {
      return new AiStudioAgentBasicGenTool(this);
    }
  }
}
