package com.box.sdkgen.schemas.v2025r0.filereferencev2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class FileReferenceV2025R0 extends SerializableObject {

  @JsonDeserialize(
      using = FileReferenceV2025R0TypeField.FileReferenceV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = FileReferenceV2025R0TypeField.FileReferenceV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<FileReferenceV2025R0TypeField> type;

  protected final String id;

  public FileReferenceV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<FileReferenceV2025R0TypeField>(FileReferenceV2025R0TypeField.FILE);
  }

  protected FileReferenceV2025R0(FileReferenceV2025R0Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public EnumWrapper<FileReferenceV2025R0TypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileReferenceV2025R0 casted = (FileReferenceV2025R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "FileReferenceV2025R0{" + "type='" + type + '\'' + ", " + "id='" + id + '\'' + "}";
  }

  public static class FileReferenceV2025R0Builder {

    protected EnumWrapper<FileReferenceV2025R0TypeField> type;

    protected final String id;

    public FileReferenceV2025R0Builder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<FileReferenceV2025R0TypeField>(FileReferenceV2025R0TypeField.FILE);
    }

    public FileReferenceV2025R0Builder type(FileReferenceV2025R0TypeField type) {
      this.type = new EnumWrapper<FileReferenceV2025R0TypeField>(type);
      return this;
    }

    public FileReferenceV2025R0Builder type(EnumWrapper<FileReferenceV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public FileReferenceV2025R0 build() {
      return new FileReferenceV2025R0(this);
    }
  }
}
