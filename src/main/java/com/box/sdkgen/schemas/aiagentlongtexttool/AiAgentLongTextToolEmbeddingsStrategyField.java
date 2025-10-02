package com.box.sdkgen.schemas.aiagentlongtexttool;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiAgentLongTextToolEmbeddingsStrategyField extends SerializableObject {

  /** The strategy used for the AI agent for calculating embeddings. */
  protected String id;

  /** The number of tokens per chunk. */
  @JsonProperty("num_tokens_per_chunk")
  protected Long numTokensPerChunk;

  public AiAgentLongTextToolEmbeddingsStrategyField() {
    super();
  }

  protected AiAgentLongTextToolEmbeddingsStrategyField(Builder builder) {
    super();
    this.id = builder.id;
    this.numTokensPerChunk = builder.numTokensPerChunk;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected Long numTokensPerChunk;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder numTokensPerChunk(Long numTokensPerChunk) {
      this.numTokensPerChunk = numTokensPerChunk;
      return this;
    }

    public AiAgentLongTextToolEmbeddingsStrategyField build() {
      return new AiAgentLongTextToolEmbeddingsStrategyField(this);
    }
  }
}
