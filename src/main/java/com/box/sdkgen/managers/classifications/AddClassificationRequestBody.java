package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AddClassificationRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          AddClassificationRequestBodyOpField.AddClassificationRequestBodyOpFieldDeserializer.class)
  @JsonSerialize(
      using =
          AddClassificationRequestBodyOpField.AddClassificationRequestBodyOpFieldSerializer.class)
  protected EnumWrapper<AddClassificationRequestBodyOpField> op;

  @JsonDeserialize(
      using =
          AddClassificationRequestBodyFieldKeyField
              .AddClassificationRequestBodyFieldKeyFieldDeserializer.class)
  @JsonSerialize(
      using =
          AddClassificationRequestBodyFieldKeyField
              .AddClassificationRequestBodyFieldKeyFieldSerializer.class)
  protected EnumWrapper<AddClassificationRequestBodyFieldKeyField> fieldKey;

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

  protected AddClassificationRequestBody(AddClassificationRequestBodyBuilder builder) {
    super();
    this.op = builder.op;
    this.fieldKey = builder.fieldKey;
    this.data = builder.data;
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

  public static class AddClassificationRequestBodyBuilder {

    protected EnumWrapper<AddClassificationRequestBodyOpField> op;

    protected EnumWrapper<AddClassificationRequestBodyFieldKeyField> fieldKey;

    protected final AddClassificationRequestBodyDataField data;

    public AddClassificationRequestBodyBuilder(AddClassificationRequestBodyDataField data) {
      this.data = data;
      this.op =
          new EnumWrapper<AddClassificationRequestBodyOpField>(
              AddClassificationRequestBodyOpField.ADDENUMOPTION);
      this.fieldKey =
          new EnumWrapper<AddClassificationRequestBodyFieldKeyField>(
              AddClassificationRequestBodyFieldKeyField.BOX__SECURITY__CLASSIFICATION__KEY);
    }

    public AddClassificationRequestBodyBuilder op(AddClassificationRequestBodyOpField op) {
      this.op = new EnumWrapper<AddClassificationRequestBodyOpField>(op);
      return this;
    }

    public AddClassificationRequestBodyBuilder op(
        EnumWrapper<AddClassificationRequestBodyOpField> op) {
      this.op = op;
      return this;
    }

    public AddClassificationRequestBodyBuilder fieldKey(
        AddClassificationRequestBodyFieldKeyField fieldKey) {
      this.fieldKey = new EnumWrapper<AddClassificationRequestBodyFieldKeyField>(fieldKey);
      return this;
    }

    public AddClassificationRequestBodyBuilder fieldKey(
        EnumWrapper<AddClassificationRequestBodyFieldKeyField> fieldKey) {
      this.fieldKey = fieldKey;
      return this;
    }

    public AddClassificationRequestBody build() {
      return new AddClassificationRequestBody(this);
    }
  }
}
