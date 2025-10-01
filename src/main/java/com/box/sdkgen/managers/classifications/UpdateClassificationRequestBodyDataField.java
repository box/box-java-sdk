package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateClassificationRequestBodyDataField extends SerializableObject {

  /** A new label for the classification, as it will be shown in the web and mobile interfaces. */
  protected final String key;

  /** A static configuration for the classification. */
  protected UpdateClassificationRequestBodyDataStaticConfigField staticConfig;

  public UpdateClassificationRequestBodyDataField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected UpdateClassificationRequestBodyDataField(Builder builder) {
    super();
    this.key = builder.key;
    this.staticConfig = builder.staticConfig;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getKey() {
    return key;
  }

  public UpdateClassificationRequestBodyDataStaticConfigField getStaticConfig() {
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
    UpdateClassificationRequestBodyDataField casted = (UpdateClassificationRequestBodyDataField) o;
    return Objects.equals(key, casted.key) && Objects.equals(staticConfig, casted.staticConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, staticConfig);
  }

  @Override
  public String toString() {
    return "UpdateClassificationRequestBodyDataField{"
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

    protected UpdateClassificationRequestBodyDataStaticConfigField staticConfig;

    public Builder(String key) {
      super();
      this.key = key;
    }

    public Builder staticConfig(UpdateClassificationRequestBodyDataStaticConfigField staticConfig) {
      this.staticConfig = staticConfig;
      return this;
    }

    public UpdateClassificationRequestBodyDataField build() {
      return new UpdateClassificationRequestBodyDataField(this);
    }
  }
}
