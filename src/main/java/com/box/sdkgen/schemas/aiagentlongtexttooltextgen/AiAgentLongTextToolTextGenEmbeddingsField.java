package com.box.sdkgen.schemas.aiagentlongtexttooltextgen;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class AiAgentLongTextToolTextGenEmbeddingsField extends SerializableObject {

  protected String model;

  protected AiAgentLongTextToolTextGenEmbeddingsStrategyField strategy;

  public AiAgentLongTextToolTextGenEmbeddingsField() {
    super();
  }

  protected AiAgentLongTextToolTextGenEmbeddingsField(
      AiAgentLongTextToolTextGenEmbeddingsFieldBuilder builder) {
    super();
    this.model = builder.model;
    this.strategy = builder.strategy;
  }

  public String getModel() {
    return model;
  }

  public AiAgentLongTextToolTextGenEmbeddingsStrategyField getStrategy() {
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
    AiAgentLongTextToolTextGenEmbeddingsField casted =
        (AiAgentLongTextToolTextGenEmbeddingsField) o;
    return Objects.equals(model, casted.model) && Objects.equals(strategy, casted.strategy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, strategy);
  }

  @Override
  public String toString() {
    return "AiAgentLongTextToolTextGenEmbeddingsField{"
        + "model='"
        + model
        + '\''
        + ", "
        + "strategy='"
        + strategy
        + '\''
        + "}";
  }

  public static class AiAgentLongTextToolTextGenEmbeddingsFieldBuilder {

    protected String model;

    protected AiAgentLongTextToolTextGenEmbeddingsStrategyField strategy;

    public AiAgentLongTextToolTextGenEmbeddingsFieldBuilder model(String model) {
      this.model = model;
      return this;
    }

    public AiAgentLongTextToolTextGenEmbeddingsFieldBuilder strategy(
        AiAgentLongTextToolTextGenEmbeddingsStrategyField strategy) {
      this.strategy = strategy;
      return this;
    }

    public AiAgentLongTextToolTextGenEmbeddingsField build() {
      return new AiAgentLongTextToolTextGenEmbeddingsField(this);
    }
  }
}
