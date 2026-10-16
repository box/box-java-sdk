package com.box.sdkgen.schemas.v2026r0.filereferencev2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** File reference. */
@JsonFilter("nullablePropertyFilter")
public class FileReferenceV2026R0 extends SerializableObject {

  /** The value will always be `file`. */
  @JsonDeserialize(
      using = FileReferenceV2026R0TypeField.FileReferenceV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = FileReferenceV2026R0TypeField.FileReferenceV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<FileReferenceV2026R0TypeField> type;

  /** ID of the file. */
  protected final String id;

  public FileReferenceV2026R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<FileReferenceV2026R0TypeField>(FileReferenceV2026R0TypeField.FILE);
  }

  protected FileReferenceV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<FileReferenceV2026R0TypeField> getType() {
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
    FileReferenceV2026R0 casted = (FileReferenceV2026R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "FileReferenceV2026R0{" + "type='" + type + '\'' + ", " + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<FileReferenceV2026R0TypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(FileReferenceV2026R0TypeField type) {
      this.type = new EnumWrapper<FileReferenceV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FileReferenceV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public FileReferenceV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<FileReferenceV2026R0TypeField>(FileReferenceV2026R0TypeField.FILE);
      }
      return new FileReferenceV2026R0(this);
    }
  }
}
