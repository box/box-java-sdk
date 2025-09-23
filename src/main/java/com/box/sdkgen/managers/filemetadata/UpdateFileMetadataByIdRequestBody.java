package com.box.sdkgen.managers.filemetadata;

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
public class UpdateFileMetadataByIdRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateFileMetadataByIdRequestBodyOpField
              .UpdateFileMetadataByIdRequestBodyOpFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFileMetadataByIdRequestBodyOpField
              .UpdateFileMetadataByIdRequestBodyOpFieldSerializer.class)
  protected EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField> op;

  protected String path;

  protected MetadataInstanceValue value;

  protected String from;

  public UpdateFileMetadataByIdRequestBody() {
    super();
  }

  protected UpdateFileMetadataByIdRequestBody(Builder builder) {
    super();
    this.op = builder.op;
    this.path = builder.path;
    this.value = builder.value;
    this.from = builder.from;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField> getOp() {
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
    UpdateFileMetadataByIdRequestBody casted = (UpdateFileMetadataByIdRequestBody) o;
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
    return "UpdateFileMetadataByIdRequestBody{"
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

    protected EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField> op;

    protected String path;

    protected MetadataInstanceValue value;

    protected String from;

    public Builder op(UpdateFileMetadataByIdRequestBodyOpField op) {
      this.op = new EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField>(op);
      return this;
    }

    public Builder op(EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField> op) {
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

    public UpdateFileMetadataByIdRequestBody build() {
      return new UpdateFileMetadataByIdRequestBody(this);
    }
  }
}
