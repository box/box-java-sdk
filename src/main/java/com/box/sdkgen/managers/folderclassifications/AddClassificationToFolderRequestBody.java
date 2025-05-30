package com.box.sdkgen.managers.folderclassifications;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AddClassificationToFolderRequestBody extends SerializableObject {

  @JsonProperty("Box__Security__Classification__Key")
  protected String boxSecurityClassificationKey;

  public AddClassificationToFolderRequestBody() {
    super();
  }

  protected AddClassificationToFolderRequestBody(
      AddClassificationToFolderRequestBodyBuilder builder) {
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
    AddClassificationToFolderRequestBody casted = (AddClassificationToFolderRequestBody) o;
    return Objects.equals(boxSecurityClassificationKey, casted.boxSecurityClassificationKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boxSecurityClassificationKey);
  }

  @Override
  public String toString() {
    return "AddClassificationToFolderRequestBody{"
        + "boxSecurityClassificationKey='"
        + boxSecurityClassificationKey
        + '\''
        + "}";
  }

  public static class AddClassificationToFolderRequestBodyBuilder {

    protected String boxSecurityClassificationKey;

    public AddClassificationToFolderRequestBodyBuilder boxSecurityClassificationKey(
        String boxSecurityClassificationKey) {
      this.boxSecurityClassificationKey = boxSecurityClassificationKey;
      return this;
    }

    public AddClassificationToFolderRequestBody build() {
      return new AddClassificationToFolderRequestBody(this);
    }
  }
}
