package com.box.sdkgen.managers.fileversions;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateFileVersionByIdRequestBody extends SerializableObject {

  @JsonProperty("trashed_at")
  protected String trashedAt;

  public UpdateFileVersionByIdRequestBody() {
    super();
  }

  protected UpdateFileVersionByIdRequestBody(UpdateFileVersionByIdRequestBodyBuilder builder) {
    super();
    this.trashedAt = builder.trashedAt;
  }

  public String getTrashedAt() {
    return trashedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFileVersionByIdRequestBody casted = (UpdateFileVersionByIdRequestBody) o;
    return Objects.equals(trashedAt, casted.trashedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trashedAt);
  }

  @Override
  public String toString() {
    return "UpdateFileVersionByIdRequestBody{" + "trashedAt='" + trashedAt + '\'' + "}";
  }

  public static class UpdateFileVersionByIdRequestBodyBuilder {

    protected String trashedAt;

    public UpdateFileVersionByIdRequestBodyBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public UpdateFileVersionByIdRequestBody build() {
      return new UpdateFileVersionByIdRequestBody(this);
    }
  }
}
