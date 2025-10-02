package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AddClassificationRequestBody extends SerializableObject {

  /** The type of change to perform on the classification object. */
  @JsonDeserialize(
      using =
          AddClassificationRequestBodyOpField.AddClassificationRequestBodyOpFieldDeserializer.class)
  @JsonSerialize(
      using =
          AddClassificationRequestBodyOpField.AddClassificationRequestBodyOpFieldSerializer.class)
  protected EnumWrapper<AddClassificationRequestBodyOpField> op;

  /** Defines classifications available in the enterprise. */
  @JsonDeserialize(
      using =
          AddClassificationRequestBodyFieldKeyField
              .AddClassificationRequestBodyFieldKeyFieldDeserializer.class)
  @JsonSerialize(
      using =
          AddClassificationRequestBodyFieldKeyField
              .AddClassificationRequestBodyFieldKeyFieldSerializer.class)
  protected EnumWrapper<AddClassificationRequestBodyFieldKeyField> fieldKey;

  /** The details of the classification to add. */
  protected final AddClassificationRequestBodyDataField data;

  public AddClassificationRequestBody(
      @JsonProperty("data") AddClassificationRequestBodyDataField data) {
    super();
    this.data = data;
    this.op =
        new EnumWrapper<AddClassificationRequestBodyOpField>(
            AddClassificationRequestBodyOpField.ADDENUMOPTION);
    this.fieldKey =
        new EnumWrapper<AddClassificationRequestBodyFieldKeyField>(
            AddClassificationRequestBodyFieldKeyField.BOX__SECURITY__CLASSIFICATION__KEY);
  }

  protected AddClassificationRequestBody(Builder builder) {
    super();
    this.op = builder.op;
    this.fieldKey = builder.fieldKey;
    this.data = builder.data;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AddClassificationRequestBodyOpField> getOp() {
    return op;
  }

  public EnumWrapper<AddClassificationRequestBodyFieldKeyField> getFieldKey() {
    return fieldKey;
  }

  public AddClassificationRequestBodyDataField getData() {
    return data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddClassificationRequestBody casted = (AddClassificationRequestBody) o;
    return Objects.equals(op, casted.op)
        && Objects.equals(fieldKey, casted.fieldKey)
        && Objects.equals(data, casted.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(op, fieldKey, data);
  }

  @Override
  public String toString() {
    return "AddClassificationRequestBody{"
        + "op='"
        + op
        + '\''
        + ", "
        + "fieldKey='"
        + fieldKey
        + '\''
        + ", "
        + "data='"
        + data
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AddClassificationRequestBodyOpField> op;

    protected EnumWrapper<AddClassificationRequestBodyFieldKeyField> fieldKey;

    protected final AddClassificationRequestBodyDataField data;

    public Builder(AddClassificationRequestBodyDataField data) {
      super();
      this.data = data;
      this.op =
          new EnumWrapper<AddClassificationRequestBodyOpField>(
              AddClassificationRequestBodyOpField.ADDENUMOPTION);
      this.fieldKey =
          new EnumWrapper<AddClassificationRequestBodyFieldKeyField>(
              AddClassificationRequestBodyFieldKeyField.BOX__SECURITY__CLASSIFICATION__KEY);
    }

    public Builder op(AddClassificationRequestBodyOpField op) {
      this.op = new EnumWrapper<AddClassificationRequestBodyOpField>(op);
      return this;
    }

    public Builder op(EnumWrapper<AddClassificationRequestBodyOpField> op) {
      this.op = op;
      return this;
    }

    public Builder fieldKey(AddClassificationRequestBodyFieldKeyField fieldKey) {
      this.fieldKey = new EnumWrapper<AddClassificationRequestBodyFieldKeyField>(fieldKey);
      return this;
    }

    public Builder fieldKey(EnumWrapper<AddClassificationRequestBodyFieldKeyField> fieldKey) {
      this.fieldKey = fieldKey;
      return this;
    }

    public AddClassificationRequestBody build() {
      return new AddClassificationRequestBody(this);
    }
  }
}
