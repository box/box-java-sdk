package com.box.sdkgen.managers.uploads;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UploadFileRequestBodyAttributesField extends SerializableObject {

  protected final String name;

  protected final UploadFileRequestBodyAttributesParentField parent;

  @JsonProperty("content_created_at")
  protected String contentCreatedAt;

  @JsonProperty("content_modified_at")
  protected String contentModifiedAt;

  public UploadFileRequestBodyAttributesField(
      @JsonProperty("name") String name,
      @JsonProperty("parent") UploadFileRequestBodyAttributesParentField parent) {
    super();
    this.name = name;
    this.parent = parent;
  }

  protected UploadFileRequestBodyAttributesField(
      UploadFileRequestBodyAttributesFieldBuilder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    this.contentCreatedAt = builder.contentCreatedAt;
    this.contentModifiedAt = builder.contentModifiedAt;
  }

  public String getName() {
    return name;
  }

  public UploadFileRequestBodyAttributesParentField getParent() {
    return parent;
  }

  public String getContentCreatedAt() {
    return contentCreatedAt;
  }

  public String getContentModifiedAt() {
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

  public static class UploadFileRequestBodyAttributesFieldBuilder {

    protected final String name;

    protected final UploadFileRequestBodyAttributesParentField parent;

    protected String contentCreatedAt;

    protected String contentModifiedAt;

    public UploadFileRequestBodyAttributesFieldBuilder(
        String name, UploadFileRequestBodyAttributesParentField parent) {
      this.name = name;
      this.parent = parent;
    }

    public UploadFileRequestBodyAttributesFieldBuilder contentCreatedAt(String contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public UploadFileRequestBodyAttributesFieldBuilder contentModifiedAt(String contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public UploadFileRequestBodyAttributesField build() {
      return new UploadFileRequestBodyAttributesField(this);
    }
  }
}
