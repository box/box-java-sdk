package com.box.sdkgen.managers.fileclassifications;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AddClassificationToFileRequestBody extends SerializableObject {

  @JsonProperty("Box__Security__Classification__Key")
  protected String boxSecurityClassificationKey;

  public AddClassificationToFileRequestBody() {
    super();
  }

  protected AddClassificationToFileRequestBody(AddClassificationToFileRequestBodyBuilder builder) {
    super();
    this.boxSecurityClassificationKey = builder.boxSecurityClassificationKey;
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

  public static class AddClassificationToFileRequestBodyBuilder {

    protected String boxSecurityClassificationKey;

    public AddClassificationToFileRequestBodyBuilder boxSecurityClassificationKey(
        String boxSecurityClassificationKey) {
      this.boxSecurityClassificationKey = boxSecurityClassificationKey;
      return this;
    }

    public AddClassificationToFileRequestBody build() {
      return new AddClassificationToFileRequestBody(this);
    }
  }
}
