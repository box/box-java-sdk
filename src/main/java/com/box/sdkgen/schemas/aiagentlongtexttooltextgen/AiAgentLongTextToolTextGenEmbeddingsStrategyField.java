package com.box.sdkgen.schemas.aiagentlongtexttooltextgen;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AiAgentLongTextToolTextGenEmbeddingsStrategyField extends SerializableObject {

  protected String id;

  @JsonProperty("num_tokens_per_chunk")
  protected Long numTokensPerChunk;

  public AiAgentLongTextToolTextGenEmbeddingsStrategyField() {
    super();
  }

  protected AiAgentLongTextToolTextGenEmbeddingsStrategyField(
      AiAgentLongTextToolTextGenEmbeddingsStrategyFieldBuilder builder) {
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
    AiAgentLongTextToolTextGenEmbeddingsStrategyField casted =
        (AiAgentLongTextToolTextGenEmbeddingsStrategyField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(numTokensPerChunk, casted.numTokensPerChunk);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numTokensPerChunk);
  }

  @Override
  public String toString() {
    return "AiAgentLongTextToolTextGenEmbeddingsStrategyField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "numTokensPerChunk='"
        + numTokensPerChunk
        + '\''
        + "}";
  }

  public static class AiAgentLongTextToolTextGenEmbeddingsStrategyFieldBuilder {

    protected String id;

    protected Long numTokensPerChunk;

    public AiAgentLongTextToolTextGenEmbeddingsStrategyFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public AiAgentLongTextToolTextGenEmbeddingsStrategyFieldBuilder numTokensPerChunk(
        Long numTokensPerChunk) {
      this.numTokensPerChunk = numTokensPerChunk;
      return this;
    }

    public AiAgentLongTextToolTextGenEmbeddingsStrategyField build() {
      return new AiAgentLongTextToolTextGenEmbeddingsStrategyField(this);
    }
  }
}
