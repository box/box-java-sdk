package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateClassificationRequestBodyDataField extends SerializableObject {

  protected final String key;

  protected UpdateClassificationRequestBodyDataStaticConfigField staticConfig;

  public UpdateClassificationRequestBodyDataField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected UpdateClassificationRequestBodyDataField(
      UpdateClassificationRequestBodyDataFieldBuilder builder) {
    super();
    this.key = builder.key;
    this.staticConfig = builder.staticConfig;
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

  public static class UpdateClassificationRequestBodyDataFieldBuilder {

    protected final String key;

    protected UpdateClassificationRequestBodyDataStaticConfigField staticConfig;

    public UpdateClassificationRequestBodyDataFieldBuilder(String key) {
      this.key = key;
    }

    public UpdateClassificationRequestBodyDataFieldBuilder staticConfig(
        UpdateClassificationRequestBodyDataStaticConfigField staticConfig) {
      this.staticConfig = staticConfig;
      return this;
    }

    public UpdateClassificationRequestBodyDataField build() {
      return new UpdateClassificationRequestBodyDataField(this);
    }
  }
}
