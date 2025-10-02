package com.box.sdkgen.managers.fileversions;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFileVersionByIdRequestBody extends SerializableObject {

  /** Set this to `null` to clear the date and restore the file. */
  @JsonProperty("trashed_at")
  @Nullable
  protected String trashedAt;

  public UpdateFileVersionByIdRequestBody() {
    super();
  }

  protected UpdateFileVersionByIdRequestBody(Builder builder) {
    super();
    this.trashedAt = builder.trashedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String trashedAt;

    public Builder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      this.markNullableFieldAsSet("trashed_at");
      return this;
    }

    public UpdateFileVersionByIdRequestBody build() {
      return new UpdateFileVersionByIdRequestBody(this);
    }
  }
}
