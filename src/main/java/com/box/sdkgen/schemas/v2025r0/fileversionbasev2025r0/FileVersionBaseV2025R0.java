package com.box.sdkgen.schemas.v2025r0.fileversionbasev2025r0;

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
public class FileVersionBaseV2025R0 extends SerializableObject {

  /** The unique identifier that represent a file version. */
  protected final String id;

  /** The value will always be `file_version`. */
  @JsonDeserialize(
      using = FileVersionBaseV2025R0TypeField.FileVersionBaseV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = FileVersionBaseV2025R0TypeField.FileVersionBaseV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<FileVersionBaseV2025R0TypeField> type;

  public FileVersionBaseV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<FileVersionBaseV2025R0TypeField>(
            FileVersionBaseV2025R0TypeField.FILE_VERSION);
  }

  protected FileVersionBaseV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<FileVersionBaseV2025R0TypeField> getType() {
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
    FileVersionBaseV2025R0 casted = (FileVersionBaseV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "FileVersionBaseV2025R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<FileVersionBaseV2025R0TypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(FileVersionBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<FileVersionBaseV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FileVersionBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public FileVersionBaseV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<FileVersionBaseV2025R0TypeField>(
                FileVersionBaseV2025R0TypeField.FILE_VERSION);
      }
      return new FileVersionBaseV2025R0(this);
    }
  }
}
