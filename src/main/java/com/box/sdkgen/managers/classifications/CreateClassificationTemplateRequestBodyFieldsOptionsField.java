package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class CreateClassificationTemplateRequestBodyFieldsOptionsField extends SerializableObject {

  protected final String key;

  protected CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField staticConfig;

  public CreateClassificationTemplateRequestBodyFieldsOptionsField(
      @JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected CreateClassificationTemplateRequestBodyFieldsOptionsField(
      CreateClassificationTemplateRequestBodyFieldsOptionsFieldBuilder builder) {
    super();
    this.key = builder.key;
    this.staticConfig = builder.staticConfig;
  }

  public String getKey() {
    return key;
  }

  public CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField getStaticConfig() {
    return staticConfig;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateClassificationTemplateRequestBodyFieldsOptionsField casted =
        (CreateClassificationTemplateRequestBodyFieldsOptionsField) o;
    return Objects.equals(key, casted.key) && Objects.equals(staticConfig, casted.staticConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, staticConfig);
  }

  @Override
  public String toString() {
    return "CreateClassificationTemplateRequestBodyFieldsOptionsField{"
        + "key='"
        + key
        + '\''
        + ", "
        + "staticConfig='"
        + staticConfig
        + '\''
        + "}";
  }

  public static class CreateClassificationTemplateRequestBodyFieldsOptionsFieldBuilder {

    protected final String key;

    protected CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField staticConfig;

    public CreateClassificationTemplateRequestBodyFieldsOptionsFieldBuilder(String key) {
      this.key = key;
    }

    public CreateClassificationTemplateRequestBodyFieldsOptionsFieldBuilder staticConfig(
        CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField staticConfig) {
      this.staticConfig = staticConfig;
      return this;
    }

    public CreateClassificationTemplateRequestBodyFieldsOptionsField build() {
      return new CreateClassificationTemplateRequestBodyFieldsOptionsField(this);
    }
  }
}
