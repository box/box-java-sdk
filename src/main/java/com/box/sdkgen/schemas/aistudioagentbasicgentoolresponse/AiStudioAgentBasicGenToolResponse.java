package com.box.sdkgen.schemas.aistudioagentbasicgentoolresponse;

import com.box.sdkgen.schemas.aiagentlongtexttooltextgen.AiAgentLongTextToolTextGenEmbeddingsField;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.box.sdkgen.schemas.aillmendpointparamsaws.AiLlmEndpointParamsAws;
import com.box.sdkgen.schemas.aillmendpointparamsgoogle.AiLlmEndpointParamsGoogle;
import com.box.sdkgen.schemas.aillmendpointparamsibm.AiLlmEndpointParamsIbm;
import com.box.sdkgen.schemas.aillmendpointparamsopenai.AiLlmEndpointParamsOpenAi;
import com.box.sdkgen.schemas.aistudioagentbasicgentool.AiStudioAgentBasicGenTool;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiStudioAgentBasicGenToolResponse extends AiStudioAgentBasicGenTool {

  protected List<String> warnings;

  public AiStudioAgentBasicGenToolResponse() {
    super();
  }

  protected AiStudioAgentBasicGenToolResponse(Builder builder) {
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

  public static class Builder extends AiStudioAgentBasicGenTool.Builder {

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
    public Builder embeddings(AiAgentLongTextToolTextGenEmbeddingsField embeddings) {
      this.embeddings = embeddings;
      return this;
    }

    @Override
    public Builder contentTemplate(String contentTemplate) {
      this.contentTemplate = contentTemplate;
      return this;
    }

    @Override
    public Builder isCustomInstructionsIncluded(Boolean isCustomInstructionsIncluded) {
      this.isCustomInstructionsIncluded = isCustomInstructionsIncluded;
      return this;
    }

    public AiStudioAgentBasicGenToolResponse build() {
      return new AiStudioAgentBasicGenToolResponse(this);
    }
  }
}
