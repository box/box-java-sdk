package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AddClassificationRequestBodyDataField extends SerializableObject {

  protected final String key;

  protected AddClassificationRequestBodyDataStaticConfigField staticConfig;

  public AddClassificationRequestBodyDataField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  protected AddClassificationRequestBodyDataField(
      AddClassificationRequestBodyDataFieldBuilder builder) {
    super();
    this.key = builder.key;
    this.staticConfig = builder.staticConfig;
  }

  public String getKey() {
    return key;
  }

  public AddClassificationRequestBodyDataStaticConfigField getStaticConfig() {
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
    AddClassificationRequestBodyDataField casted = (AddClassificationRequestBodyDataField) o;
    return Objects.equals(key, casted.key) && Objects.equals(staticConfig, casted.staticConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, staticConfig);
  }

  @Override
  public String toString() {
    return "AddClassificationRequestBodyDataField{"
        + "key='"
        + key
        + '\''
        + ", "
        + "staticConfig='"
        + staticConfig
        + '\''
        + "}";
  }

  public static class AddClassificationRequestBodyDataFieldBuilder {

    protected final String key;

    protected AddClassificationRequestBodyDataStaticConfigField staticConfig;

    public AddClassificationRequestBodyDataFieldBuilder(String key) {
      this.key = key;
    }

    public AddClassificationRequestBodyDataFieldBuilder staticConfig(
        AddClassificationRequestBodyDataStaticConfigField staticConfig) {
      this.staticConfig = staticConfig;
      return this;
    }

    public AddClassificationRequestBodyDataField build() {
      return new AddClassificationRequestBodyDataField(this);
    }
  }
}
