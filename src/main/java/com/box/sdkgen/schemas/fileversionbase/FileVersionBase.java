package com.box.sdkgen.schemas.fileversionbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * The bare basic representation of a file version, the minimal amount of fields returned when using
 * the `fields` query parameter.
 */
@JsonFilter("nullablePropertyFilter")
public class FileVersionBase extends SerializableObject {

  /** The unique identifier that represent a file version. */
  protected final String id;

  /** The value will always be `file_version`. */
  @JsonDeserialize(using = FileVersionBaseTypeField.FileVersionBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = FileVersionBaseTypeField.FileVersionBaseTypeFieldSerializer.class)
  protected EnumWrapper<FileVersionBaseTypeField> type;

  public FileVersionBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<FileVersionBaseTypeField>(FileVersionBaseTypeField.FILE_VERSION);
  }

  protected FileVersionBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<FileVersionBaseTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileVersionBase casted = (FileVersionBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "FileVersionBase{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<FileVersionBaseTypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<FileVersionBaseTypeField>(FileVersionBaseTypeField.FILE_VERSION);
    }

    public Builder type(FileVersionBaseTypeField type) {
      this.type = new EnumWrapper<FileVersionBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FileVersionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FileVersionBase build() {
      return new FileVersionBase(this);
    }
  }
}
