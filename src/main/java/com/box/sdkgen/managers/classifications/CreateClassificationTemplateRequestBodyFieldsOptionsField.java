package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateClassificationTemplateRequestBodyFieldsOptionsField extends SerializableObject {

  protected final String key;

  protected CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField staticConfig;

  public CreateClassificationTemplateRequestBodyFieldsOptionsField(
      @JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected CreateClassificationTemplateRequestBodyFieldsOptionsField(Builder builder) {
    super();
    this.key = builder.key;
    this.staticConfig = builder.staticConfig;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected final String key;

    protected CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField staticConfig;

    public Builder(String key) {
      super();
      this.key = key;
    }

    public Builder staticConfig(
        CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField staticConfig) {
      this.staticConfig = staticConfig;
      return this;
    }

    public CreateClassificationTemplateRequestBodyFieldsOptionsField build() {
      return new CreateClassificationTemplateRequestBodyFieldsOptionsField(this);
    }
  }
}
