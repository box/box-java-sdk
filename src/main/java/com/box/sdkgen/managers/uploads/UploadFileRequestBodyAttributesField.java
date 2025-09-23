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
public class UploadFileRequestBodyAttributesField extends SerializableObject {

  protected final String name;

  protected final UploadFileRequestBodyAttributesParentField parent;

  @JsonProperty("content_created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime contentCreatedAt;

  @JsonProperty("content_modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime contentModifiedAt;

  public UploadFileRequestBodyAttributesField(
      @JsonProperty("name") String name,
      @JsonProperty("parent") UploadFileRequestBodyAttributesParentField parent) {
    super();
    this.name = name;
    this.parent = parent;
  }

  protected UploadFileRequestBodyAttributesField(Builder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    this.contentCreatedAt = builder.contentCreatedAt;
    this.contentModifiedAt = builder.contentModifiedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public UploadFileRequestBodyAttributesParentField getParent() {
    return parent;
  }

  public OffsetDateTime getContentCreatedAt() {
    return contentCreatedAt;
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
    UploadFileRequestBodyAttributesField casted = (UploadFileRequestBodyAttributesField) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(contentCreatedAt, casted.contentCreatedAt)
        && Objects.equals(contentModifiedAt, casted.contentModifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent, contentCreatedAt, contentModifiedAt);
  }

  @Override
  public String toString() {
    return "UploadFileRequestBodyAttributesField{"
        + "name='"
        + name
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "contentCreatedAt='"
        + contentCreatedAt
        + '\''
        + ", "
        + "contentModifiedAt='"
        + contentModifiedAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected final UploadFileRequestBodyAttributesParentField parent;

    protected OffsetDateTime contentCreatedAt;

    protected OffsetDateTime contentModifiedAt;

    public Builder(String name, UploadFileRequestBodyAttributesParentField parent) {
      super();
      this.name = name;
      this.parent = parent;
    }

    public Builder contentCreatedAt(OffsetDateTime contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public Builder contentModifiedAt(OffsetDateTime contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public UploadFileRequestBodyAttributesField build() {
      return new UploadFileRequestBodyAttributesField(this);
    }
  }
}
