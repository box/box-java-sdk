package com.box.sdkgen.managers.fileclassifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AddClassificationToFileRequestBody extends SerializableObject {

  @JsonProperty("Box__Security__Classification__Key")
  protected String boxSecurityClassificationKey;

  public AddClassificationToFileRequestBody() {
    super();
  }

  protected AddClassificationToFileRequestBody(Builder builder) {
    super();
    this.boxSecurityClassificationKey = builder.boxSecurityClassificationKey;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getBoxSecurityClassificationKey() {
    return boxSecurityClassificationKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddClassificationToFileRequestBody casted = (AddClassificationToFileRequestBody) o;
    return Objects.equals(boxSecurityClassificationKey, casted.boxSecurityClassificationKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boxSecurityClassificationKey);
  }

  @Override
  public String toString() {
    return "AddClassificationToFileRequestBody{"
        + "boxSecurityClassificationKey='"
        + boxSecurityClassificationKey
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String boxSecurityClassificationKey;

    public Builder boxSecurityClassificationKey(String boxSecurityClassificationKey) {
      this.boxSecurityClassificationKey = boxSecurityClassificationKey;
      return this;
    }

    public AddClassificationToFileRequestBody build() {
      return new AddClassificationToFileRequestBody(this);
    }
  }
}
