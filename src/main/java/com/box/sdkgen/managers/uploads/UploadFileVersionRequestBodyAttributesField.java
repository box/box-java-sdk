package com.box.sdkgen.managers.uploads;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UploadFileVersionRequestBodyAttributesField extends SerializableObject {

  /**
   * An optional new name for the file. If specified, the file will be renamed when the new version
   * is uploaded.
   */
  protected final String name;

  /**
   * Defines the time the file was last modified at.
   *
   * <p>If not set, the upload time will be used.
   */
  @JsonProperty("content_modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime contentModifiedAt;

  public UploadFileVersionRequestBodyAttributesField(@JsonProperty("name") String name) {
    super();
    this.name = name;
  }

  protected UploadFileVersionRequestBodyAttributesField(Builder builder) {
    super();
    this.name = builder.name;
    this.contentModifiedAt = builder.contentModifiedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public OffsetDateTime getContentModifiedAt() {
    return contentModifiedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadFileVersionRequestBodyAttributesField casted =
        (UploadFileVersionRequestBodyAttributesField) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(contentModifiedAt, casted.contentModifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, contentModifiedAt);
  }

  @Override
  public String toString() {
    return "UploadFileVersionRequestBodyAttributesField{"
        + "name='"
        + name
        + '\''
        + ", "
        + "contentModifiedAt='"
        + contentModifiedAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected OffsetDateTime contentModifiedAt;

    public Builder(String name) {
      super();
      this.name = name;
    }

    public Builder contentModifiedAt(OffsetDateTime contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public UploadFileVersionRequestBodyAttributesField build() {
      return new UploadFileVersionRequestBodyAttributesField(this);
    }
  }
}
