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
public class UploadWithPreflightCheckRequestBodyAttributesField extends SerializableObject {

  protected final String name;

  protected final UploadWithPreflightCheckRequestBodyAttributesParentField parent;

  @JsonProperty("content_created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime contentCreatedAt;

  @JsonProperty("content_modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime contentModifiedAt;

  protected final int size;

  public UploadWithPreflightCheckRequestBodyAttributesField(
      @JsonProperty("name") String name,
      @JsonProperty("parent") UploadWithPreflightCheckRequestBodyAttributesParentField parent,
      @JsonProperty("size") int size) {
    super();
    this.name = name;
    this.parent = parent;
    this.size = size;
  }

  protected UploadWithPreflightCheckRequestBodyAttributesField(Builder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    this.contentCreatedAt = builder.contentCreatedAt;
    this.contentModifiedAt = builder.contentModifiedAt;
    this.size = builder.size;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public UploadWithPreflightCheckRequestBodyAttributesParentField getParent() {
    return parent;
  }

  public OffsetDateTime getContentCreatedAt() {
    return contentCreatedAt;
  }

  public OffsetDateTime getContentModifiedAt() {
    return contentModifiedAt;
  }

  public int getSize() {
    return size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadWithPreflightCheckRequestBodyAttributesField casted =
        (UploadWithPreflightCheckRequestBodyAttributesField) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(contentCreatedAt, casted.contentCreatedAt)
        && Objects.equals(contentModifiedAt, casted.contentModifiedAt)
        && Objects.equals(size, casted.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent, contentCreatedAt, contentModifiedAt, size);
  }

  @Override
  public String toString() {
    return "UploadWithPreflightCheckRequestBodyAttributesField{"
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
        + ", "
        + "size='"
        + size
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected final UploadWithPreflightCheckRequestBodyAttributesParentField parent;

    protected OffsetDateTime contentCreatedAt;

    protected OffsetDateTime contentModifiedAt;

    protected final int size;

    public Builder(
        String name, UploadWithPreflightCheckRequestBodyAttributesParentField parent, int size) {
      super();
      this.name = name;
      this.parent = parent;
      this.size = size;
    }

    public Builder contentCreatedAt(OffsetDateTime contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public Builder contentModifiedAt(OffsetDateTime contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public UploadWithPreflightCheckRequestBodyAttributesField build() {
      return new UploadWithPreflightCheckRequestBodyAttributesField(this);
    }
  }
}
