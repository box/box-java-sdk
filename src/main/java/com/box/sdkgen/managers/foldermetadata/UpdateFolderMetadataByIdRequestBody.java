package com.box.sdkgen.managers.foldermetadata;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatainstancevalue.MetadataInstanceValue;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFolderMetadataByIdRequestBody extends SerializableObject {

  /**
   * The type of change to perform on the template. Some of these are hazardous as they will change
   * existing templates.
   */
  @JsonDeserialize(
      using =
          UpdateFolderMetadataByIdRequestBodyOpField
              .UpdateFolderMetadataByIdRequestBodyOpFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFolderMetadataByIdRequestBodyOpField
              .UpdateFolderMetadataByIdRequestBodyOpFieldSerializer.class)
  protected EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField> op;

  /**
   * The location in the metadata JSON object to apply the changes to, in the format of a
   * [JSON-Pointer](https://tools.ietf.org/html/rfc6901).
   *
   * <p>The path must always be prefixed with a `/` to represent the root of the template. The
   * characters `~` and `/` are reserved characters and must be escaped in the key.
   */
  protected String path;

  protected MetadataInstanceValue value;

  /**
   * The location in the metadata JSON object to move or copy a value from. Required for `move` or
   * `copy` operations and must be in the format of a
   * [JSON-Pointer](https://tools.ietf.org/html/rfc6901).
   */
  protected String from;

  public UpdateFolderMetadataByIdRequestBody() {
    super();
  }

  protected UpdateFolderMetadataByIdRequestBody(Builder builder) {
    super();
    this.op = builder.op;
    this.path = builder.path;
    this.value = builder.value;
    this.from = builder.from;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField> getOp() {
    return op;
  }

  public String getPath() {
    return path;
  }

  public MetadataInstanceValue getValue() {
    return value;
  }

  public String getFrom() {
    return from;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFolderMetadataByIdRequestBody casted = (UpdateFolderMetadataByIdRequestBody) o;
    return Objects.equals(op, casted.op)
        && Objects.equals(path, casted.path)
        && Objects.equals(value, casted.value)
        && Objects.equals(from, casted.from);
  }

  @Override
  public int hashCode() {
    return Objects.hash(op, path, value, from);
  }

  @Override
  public String toString() {
    return "UpdateFolderMetadataByIdRequestBody{"
        + "op='"
        + op
        + '\''
        + ", "
        + "path='"
        + path
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + ", "
        + "from='"
        + from
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField> op;

    protected String path;

    protected MetadataInstanceValue value;

    protected String from;

    public Builder op(UpdateFolderMetadataByIdRequestBodyOpField op) {
      this.op = new EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField>(op);
      return this;
    }

    public Builder op(EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField> op) {
      this.op = op;
      return this;
    }

    public Builder path(String path) {
      this.path = path;
      return this;
    }

    public Builder value(String value) {
      this.value = new MetadataInstanceValue(value);
      return this;
    }

    public Builder value(long value) {
      this.value = new MetadataInstanceValue(value);
      return this;
    }

    public Builder value(double value) {
      this.value = new MetadataInstanceValue(value);
      return this;
    }

    public Builder value(List<String> value) {
      this.value = new MetadataInstanceValue(value);
      return this;
    }

    public Builder value(MetadataInstanceValue value) {
      this.value = value;
      return this;
    }

    public Builder from(String from) {
      this.from = from;
      return this;
    }

    public UpdateFolderMetadataByIdRequestBody build() {
      return new UpdateFolderMetadataByIdRequestBody(this);
    }
  }
}
