package com.box.sdkgen.schemas.aiagentlongtexttooltextgen;

import com.box.sdkgen.schemas.aiagentbasictexttooltextgen.AiAgentBasicTextToolTextGen;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import java.util.Objects;

public class AiAgentLongTextToolTextGen extends AiAgentBasicTextToolTextGen {

  protected AiAgentLongTextToolTextGenEmbeddingsField embeddings;

  public AiAgentLongTextToolTextGen() {
    super();
  }

  protected AiAgentLongTextToolTextGen(AiAgentLongTextToolTextGenBuilder builder) {
    super(builder);
    this.embeddings = builder.embeddings;
  }

  public AiAgentLongTextToolTextGenEmbeddingsField getEmbeddings() {
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
    AiAgentLongTextToolTextGen casted = (AiAgentLongTextToolTextGen) o;
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
    return "AiAgentLongTextToolTextGen{"
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

  public static class AiAgentLongTextToolTextGenBuilder extends AiAgentBasicTextToolTextGenBuilder {

    protected AiAgentLongTextToolTextGenEmbeddingsField embeddings;

    public AiAgentLongTextToolTextGenBuilder embeddings(
        AiAgentLongTextToolTextGenEmbeddingsField embeddings) {
      this.embeddings = embeddings;
      return this;
    }

    @Override
    public AiAgentLongTextToolTextGenBuilder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public AiAgentLongTextToolTextGenBuilder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public AiAgentLongTextToolTextGenBuilder llmEndpointParams(
        AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    @Override
    public AiAgentLongTextToolTextGenBuilder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    @Override
    public AiAgentLongTextToolTextGenBuilder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    public AiAgentLongTextToolTextGen build() {
      return new AiAgentLongTextToolTextGen(this);
    }
  }
}
