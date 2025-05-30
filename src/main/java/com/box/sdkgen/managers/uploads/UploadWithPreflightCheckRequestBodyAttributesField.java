package com.box.sdkgen.managers.uploads;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UploadWithPreflightCheckRequestBodyAttributesField extends SerializableObject {

  protected final String name;

  protected final UploadWithPreflightCheckRequestBodyAttributesParentField parent;

  @JsonProperty("content_created_at")
  protected String contentCreatedAt;

  @JsonProperty("content_modified_at")
  protected String contentModifiedAt;

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

  protected UploadWithPreflightCheckRequestBodyAttributesField(
      UploadWithPreflightCheckRequestBodyAttributesFieldBuilder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    this.contentCreatedAt = builder.contentCreatedAt;
    this.contentModifiedAt = builder.contentModifiedAt;
    this.size = builder.size;
  }

  public String getName() {
    return name;
  }

  public UploadWithPreflightCheckRequestBodyAttributesParentField getParent() {
    return parent;
  }

  public String getContentCreatedAt() {
    return contentCreatedAt;
  }

  public String getContentModifiedAt() {
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

  public static class UploadWithPreflightCheckRequestBodyAttributesFieldBuilder {

    protected final String name;

    protected final UploadWithPreflightCheckRequestBodyAttributesParentField parent;

    protected String contentCreatedAt;

    protected String contentModifiedAt;

    protected final int size;

    public UploadWithPreflightCheckRequestBodyAttributesFieldBuilder(
        String name, UploadWithPreflightCheckRequestBodyAttributesParentField parent, int size) {
      this.name = name;
      this.parent = parent;
      this.size = size;
    }

    public UploadWithPreflightCheckRequestBodyAttributesFieldBuilder contentCreatedAt(
        String contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public UploadWithPreflightCheckRequestBodyAttributesFieldBuilder contentModifiedAt(
        String contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public UploadWithPreflightCheckRequestBodyAttributesField build() {
      return new UploadWithPreflightCheckRequestBodyAttributesField(this);
    }
  }
}
