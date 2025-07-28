package com.box.sdkgen.schemas.aiagentlongtexttool;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiAgentLongTextToolEmbeddingsField extends SerializableObject {

  protected String model;

  protected AiAgentLongTextToolEmbeddingsStrategyField strategy;

  public AiAgentLongTextToolEmbeddingsField() {
    super();
  }

  protected AiAgentLongTextToolEmbeddingsField(Builder builder) {
    super();
    this.model = builder.model;
    this.strategy = builder.strategy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getModel() {
    return model;
  }

  public AiAgentLongTextToolEmbeddingsStrategyField getStrategy() {
    return strategy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentLongTextToolEmbeddingsField casted = (AiAgentLongTextToolEmbeddingsField) o;
    return Objects.equals(model, casted.model) && Objects.equals(strategy, casted.strategy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, strategy);
  }

  @Override
  public String toString() {
    return "AiAgentLongTextToolEmbeddingsField{"
        + "model='"
        + model
        + '\''
        + ", "
        + "strategy='"
        + strategy
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String model;

    protected AiAgentLongTextToolEmbeddingsStrategyField strategy;

    public Builder model(String model) {
      this.model = model;
      return this;
    }

    public Builder strategy(AiAgentLongTextToolEmbeddingsStrategyField strategy) {
      this.strategy = strategy;
      return this;
    }

    public AiAgentLongTextToolEmbeddingsField build() {
      return new AiAgentLongTextToolEmbeddingsField(this);
    }
  }
}
