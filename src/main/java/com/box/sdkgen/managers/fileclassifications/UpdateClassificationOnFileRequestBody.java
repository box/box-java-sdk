package com.box.sdkgen.managers.fileclassifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateClassificationOnFileRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateClassificationOnFileRequestBodyOpField
              .UpdateClassificationOnFileRequestBodyOpFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateClassificationOnFileRequestBodyOpField
              .UpdateClassificationOnFileRequestBodyOpFieldSerializer.class)
  protected EnumWrapper<UpdateClassificationOnFileRequestBodyOpField> op;

  @JsonDeserialize(
      using =
          UpdateClassificationOnFileRequestBodyPathField
              .UpdateClassificationOnFileRequestBodyPathFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateClassificationOnFileRequestBodyPathField
              .UpdateClassificationOnFileRequestBodyPathFieldSerializer.class)
  protected EnumWrapper<UpdateClassificationOnFileRequestBodyPathField> path;

  protected final String value;

  public UpdateClassificationOnFileRequestBody(@JsonProperty("value") String value) {
    super();
    this.value = value;
    this.op =
        new EnumWrapper<UpdateClassificationOnFileRequestBodyOpField>(
            UpdateClassificationOnFileRequestBodyOpField.REPLACE);
    this.path =
        new EnumWrapper<UpdateClassificationOnFileRequestBodyPathField>(
            UpdateClassificationOnFileRequestBodyPathField._BOX__SECURITY__CLASSIFICATION__KEY);
  }

  protected UpdateClassificationOnFileRequestBody(Builder builder) {
    super();
    this.op = builder.op;
    this.path = builder.path;
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateClassificationOnFileRequestBodyOpField> getOp() {
    return op;
  }

  public EnumWrapper<UpdateClassificationOnFileRequestBodyPathField> getPath() {
    return path;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateClassificationOnFileRequestBody casted = (UpdateClassificationOnFileRequestBody) o;
    return Objects.equals(op, casted.op)
        && Objects.equals(path, casted.path)
        && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(op, path, value);
  }

  @Override
  public String toString() {
    return "UpdateClassificationOnFileRequestBody{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateClassificationOnFileRequestBodyOpField> op;

    protected EnumWrapper<UpdateClassificationOnFileRequestBodyPathField> path;

    protected final String value;

    public Builder(String value) {
      super();
      this.value = value;
      this.op =
          new EnumWrapper<UpdateClassificationOnFileRequestBodyOpField>(
              UpdateClassificationOnFileRequestBodyOpField.REPLACE);
      this.path =
          new EnumWrapper<UpdateClassificationOnFileRequestBodyPathField>(
              UpdateClassificationOnFileRequestBodyPathField._BOX__SECURITY__CLASSIFICATION__KEY);
    }

    public Builder op(UpdateClassificationOnFileRequestBodyOpField op) {
      this.op = new EnumWrapper<UpdateClassificationOnFileRequestBodyOpField>(op);
      return this;
    }

    public Builder op(EnumWrapper<UpdateClassificationOnFileRequestBodyOpField> op) {
      this.op = op;
      return this;
    }

    public Builder path(UpdateClassificationOnFileRequestBodyPathField path) {
      this.path = new EnumWrapper<UpdateClassificationOnFileRequestBodyPathField>(path);
      return this;
    }

    public Builder path(EnumWrapper<UpdateClassificationOnFileRequestBodyPathField> path) {
      this.path = path;
      return this;
    }

    public UpdateClassificationOnFileRequestBody build() {
      return new UpdateClassificationOnFileRequestBody(this);
    }
  }
}
