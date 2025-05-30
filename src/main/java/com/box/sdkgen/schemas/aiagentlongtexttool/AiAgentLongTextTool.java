package com.box.sdkgen.schemas.aiagentlongtexttool;

import com.box.sdkgen.schemas.aiagentbasictexttool.AiAgentBasicTextTool;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import java.util.Objects;

public class AiAgentLongTextTool extends AiAgentBasicTextTool {

  protected AiAgentLongTextToolEmbeddingsField embeddings;

  public AiAgentLongTextTool() {
    super();
  }

  protected AiAgentLongTextTool(AiAgentLongTextToolBuilder builder) {
    super(builder);
    this.embeddings = builder.embeddings;
  }

  public AiAgentLongTextToolEmbeddingsField getEmbeddings() {
    return embeddings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentLongTextTool casted = (AiAgentLongTextTool) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate)
        && Objects.equals(embeddings, casted.embeddings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        model,
        numTokensForCompletion,
        llmEndpointParams,
        systemMessage,
        promptTemplate,
        embeddings);
  }

  @Override
  public String toString() {
    return "AiAgentLongTextTool{"
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
        + "}";
  }

  public static class AiAgentLongTextToolBuilder extends AiAgentBasicTextToolBuilder {

    protected AiAgentLongTextToolEmbeddingsField embeddings;

    public AiAgentLongTextToolBuilder embeddings(AiAgentLongTextToolEmbeddingsField embeddings) {
      this.embeddings = embeddings;
      return this;
    }

    @Override
    public AiAgentLongTextToolBuilder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public AiAgentLongTextToolBuilder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public AiAgentLongTextToolBuilder llmEndpointParams(AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    @Override
    public AiAgentLongTextToolBuilder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    @Override
    public AiAgentLongTextToolBuilder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    public AiAgentLongTextTool build() {
      return new AiAgentLongTextTool(this);
    }
  }
}
