package com.box.sdkgen.schemas.classificationtemplate;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class ClassificationTemplateFieldsOptionsField extends SerializableObject {

  protected final String id;

  protected final String key;

  protected ClassificationTemplateFieldsOptionsStaticConfigField staticConfig;

  public ClassificationTemplateFieldsOptionsField(
      @JsonProperty("id") String id, @JsonProperty("key") String key) {
    super();
    this.id = id;
    this.key = key;
  }

  protected ClassificationTemplateFieldsOptionsField(
      ClassificationTemplateFieldsOptionsFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.key = builder.key;
    this.staticConfig = builder.staticConfig;
  }

  public String getId() {
    return id;
  }

  public String getKey() {
    return key;
  }

  public ClassificationTemplateFieldsOptionsStaticConfigField getStaticConfig() {
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
    ClassificationTemplateFieldsOptionsField casted = (ClassificationTemplateFieldsOptionsField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(key, casted.key)
        && Objects.equals(staticConfig, casted.staticConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, staticConfig);
  }

  @Override
  public String toString() {
    return "ClassificationTemplateFieldsOptionsField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "key='"
        + key
        + '\''
        + ", "
        + "staticConfig='"
        + staticConfig
        + '\''
        + "}";
  }

  public static class ClassificationTemplateFieldsOptionsFieldBuilder {

    protected final String id;

    protected final String key;

    protected ClassificationTemplateFieldsOptionsStaticConfigField staticConfig;

    public ClassificationTemplateFieldsOptionsFieldBuilder(String id, String key) {
      this.id = id;
      this.key = key;
    }

    public ClassificationTemplateFieldsOptionsFieldBuilder staticConfig(
        ClassificationTemplateFieldsOptionsStaticConfigField staticConfig) {
      this.staticConfig = staticConfig;
      return this;
    }

    public ClassificationTemplateFieldsOptionsField build() {
      return new ClassificationTemplateFieldsOptionsField(this);
    }
  }
}
