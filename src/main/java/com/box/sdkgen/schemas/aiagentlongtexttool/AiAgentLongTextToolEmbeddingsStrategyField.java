package com.box.sdkgen.schemas.aiagentlongtexttool;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AiAgentLongTextToolEmbeddingsStrategyField extends SerializableObject {

  protected String id;

  @JsonProperty("num_tokens_per_chunk")
  protected Long numTokensPerChunk;

  public AiAgentLongTextToolEmbeddingsStrategyField() {
    super();
  }

  protected AiAgentLongTextToolEmbeddingsStrategyField(
      AiAgentLongTextToolEmbeddingsStrategyFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.numTokensPerChunk = builder.numTokensPerChunk;
  }

  public String getId() {
    return id;
  }

  public Long getNumTokensPerChunk() {
    return numTokensPerChunk;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentLongTextToolEmbeddingsStrategyField casted =
        (AiAgentLongTextToolEmbeddingsStrategyField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(numTokensPerChunk, casted.numTokensPerChunk);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numTokensPerChunk);
  }

  @Override
  public String toString() {
    return "AiAgentLongTextToolEmbeddingsStrategyField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "numTokensPerChunk='"
        + numTokensPerChunk
        + '\''
        + "}";
  }

  public static class AiAgentLongTextToolEmbeddingsStrategyFieldBuilder {

    protected String id;

    protected Long numTokensPerChunk;

    public AiAgentLongTextToolEmbeddingsStrategyFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public AiAgentLongTextToolEmbeddingsStrategyFieldBuilder numTokensPerChunk(
        Long numTokensPerChunk) {
      this.numTokensPerChunk = numTokensPerChunk;
      return this;
    }

    public AiAgentLongTextToolEmbeddingsStrategyField build() {
      return new AiAgentLongTextToolEmbeddingsStrategyField(this);
    }
  }
}
