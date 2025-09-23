package com.box.sdkgen.schemas.filebase;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileBase extends SerializableObject {

  protected final String id;

  @Nullable protected String etag;

  @JsonDeserialize(using = FileBaseTypeField.FileBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = FileBaseTypeField.FileBaseTypeFieldSerializer.class)
  protected EnumWrapper<FileBaseTypeField> type;

  public FileBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<FileBaseTypeField>(FileBaseTypeField.FILE);
  }

  protected FileBase(Builder builder) {
    super();
    this.id = builder.id;
    this.etag = builder.etag;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getEtag() {
    return etag;
  }

  public EnumWrapper<FileBaseTypeField> getType() {
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
    FileBase casted = (FileBase) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, etag, type);
  }

  @Override
  public String toString() {
    return "FileBase{"
        + "id='"
        + id
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected String etag;

    protected EnumWrapper<FileBaseTypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<FileBaseTypeField>(FileBaseTypeField.FILE);
    }

    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    public Builder type(FileBaseTypeField type) {
      this.type = new EnumWrapper<FileBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FileBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FileBase build() {
      return new FileBase(this);
    }
  }
}
