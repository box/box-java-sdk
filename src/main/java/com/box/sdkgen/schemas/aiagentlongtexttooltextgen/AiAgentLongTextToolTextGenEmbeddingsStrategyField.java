package com.box.sdkgen.schemas.aiagentlongtexttooltextgen;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiAgentLongTextToolTextGenEmbeddingsStrategyField extends SerializableObject {

  protected String id;

  @JsonProperty("num_tokens_per_chunk")
  protected Long numTokensPerChunk;

  public AiAgentLongTextToolTextGenEmbeddingsStrategyField() {
    super();
  }

  protected AiAgentLongTextToolTextGenEmbeddingsStrategyField(Builder builder) {
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

    public AiAgentLongTextToolTextGenEmbeddingsStrategyField build() {
      return new AiAgentLongTextToolTextGenEmbeddingsStrategyField(this);
    }
  }
}
