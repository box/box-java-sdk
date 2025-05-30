package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class FileFullRepresentationsEntriesField extends SerializableObject {

  protected FileFullRepresentationsEntriesContentField content;

  protected FileFullRepresentationsEntriesInfoField info;

  protected FileFullRepresentationsEntriesPropertiesField properties;

  protected String representation;

  protected FileFullRepresentationsEntriesStatusField status;

  public FileFullRepresentationsEntriesField() {
    super();
  }

  protected FileFullRepresentationsEntriesField(
      FileFullRepresentationsEntriesFieldBuilder builder) {
    super();
    this.content = builder.content;
    this.info = builder.info;
    this.properties = builder.properties;
    this.representation = builder.representation;
    this.status = builder.status;
  }

  public FileFullRepresentationsEntriesContentField getContent() {
    return content;
  }

  public FileFullRepresentationsEntriesInfoField getInfo() {
    return info;
  }

  public FileFullRepresentationsEntriesPropertiesField getProperties() {
    return properties;
  }

  public String getRepresentation() {
    return representation;
  }

  public FileFullRepresentationsEntriesStatusField getStatus() {
    return status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullRepresentationsEntriesField casted = (FileFullRepresentationsEntriesField) o;
    return Objects.equals(content, casted.content)
        && Objects.equals(info, casted.info)
        && Objects.equals(properties, casted.properties)
        && Objects.equals(representation, casted.representation)
        && Objects.equals(status, casted.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, info, properties, representation, status);
  }

  @Override
  public String toString() {
    return "FileFullRepresentationsEntriesField{"
        + "content='"
        + content
        + '\''
        + ", "
        + "info='"
        + info
        + '\''
        + ", "
        + "properties='"
        + properties
        + '\''
        + ", "
        + "representation='"
        + representation
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + "}";
  }

  public static class FileFullRepresentationsEntriesFieldBuilder {

    protected FileFullRepresentationsEntriesContentField content;

    protected FileFullRepresentationsEntriesInfoField info;

    protected FileFullRepresentationsEntriesPropertiesField properties;

    protected String representation;

    protected FileFullRepresentationsEntriesStatusField status;

    public FileFullRepresentationsEntriesFieldBuilder content(
        FileFullRepresentationsEntriesContentField content) {
      this.content = content;
      return this;
    }

    public FileFullRepresentationsEntriesFieldBuilder info(
        FileFullRepresentationsEntriesInfoField info) {
      this.info = info;
      return this;
    }

    public FileFullRepresentationsEntriesFieldBuilder properties(
        FileFullRepresentationsEntriesPropertiesField properties) {
      this.properties = properties;
      return this;
    }

    public FileFullRepresentationsEntriesFieldBuilder representation(String representation) {
      this.representation = representation;
      return this;
    }

    public FileFullRepresentationsEntriesFieldBuilder status(
        FileFullRepresentationsEntriesStatusField status) {
      this.status = status;
      return this;
    }

    public FileFullRepresentationsEntriesField build() {
      return new FileFullRepresentationsEntriesField(this);
    }
  }
}
