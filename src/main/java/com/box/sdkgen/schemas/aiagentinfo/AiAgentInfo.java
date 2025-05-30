package com.box.sdkgen.schemas.aiagentinfo;

import com.box.sdkgen.internal.SerializableObject;
import java.util.List;
import java.util.Objects;

public class AiAgentInfo extends SerializableObject {

  protected List<AiAgentInfoModelsField> models;

  protected String processor;

  public AiAgentInfo() {
    super();
  }

  protected AiAgentInfo(AiAgentInfoBuilder builder) {
    super();
    this.models = builder.models;
    this.processor = builder.processor;
  }

  public List<AiAgentInfoModelsField> getModels() {
    return models;
  }

  public String getProcessor() {
    return processor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentInfo casted = (AiAgentInfo) o;
    return Objects.equals(models, casted.models) && Objects.equals(processor, casted.processor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(models, processor);
  }

  @Override
  public String toString() {
    return "AiAgentInfo{"
        + "models='"
        + models
        + '\''
        + ", "
        + "processor='"
        + processor
        + '\''
        + "}";
  }

  public static class AiAgentInfoBuilder {

    protected List<AiAgentInfoModelsField> models;

    protected String processor;

    public AiAgentInfoBuilder models(List<AiAgentInfoModelsField> models) {
      this.models = models;
      return this;
    }

    public AiAgentInfoBuilder processor(String processor) {
      this.processor = processor;
      return this;
    }

    public AiAgentInfo build() {
      return new AiAgentInfo(this);
    }
  }
}
