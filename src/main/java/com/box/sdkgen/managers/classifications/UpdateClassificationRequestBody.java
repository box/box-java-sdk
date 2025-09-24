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
public class UpdateClassificationRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateClassificationRequestBodyOpField.UpdateClassificationRequestBodyOpFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          UpdateClassificationRequestBodyOpField.UpdateClassificationRequestBodyOpFieldSerializer
              .class)
  protected EnumWrapper<UpdateClassificationRequestBodyOpField> op;

  @JsonDeserialize(
      using =
          UpdateClassificationRequestBodyFieldKeyField
              .UpdateClassificationRequestBodyFieldKeyFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateClassificationRequestBodyFieldKeyField
              .UpdateClassificationRequestBodyFieldKeyFieldSerializer.class)
  protected EnumWrapper<UpdateClassificationRequestBodyFieldKeyField> fieldKey;

  protected final String enumOptionKey;

  protected final UpdateClassificationRequestBodyDataField data;

  public UpdateClassificationRequestBody(
      @JsonProperty("enumOptionKey") String enumOptionKey,
      @JsonProperty("data") UpdateClassificationRequestBodyDataField data) {
    super();
    this.enumOptionKey = enumOptionKey;
    this.data = data;
    this.op =
        new EnumWrapper<UpdateClassificationRequestBodyOpField>(
            UpdateClassificationRequestBodyOpField.EDITENUMOPTION);
    this.fieldKey =
        new EnumWrapper<UpdateClassificationRequestBodyFieldKeyField>(
            UpdateClassificationRequestBodyFieldKeyField.BOX__SECURITY__CLASSIFICATION__KEY);
  }

  protected UpdateClassificationRequestBody(Builder builder) {
    super();
    this.op = builder.op;
    this.fieldKey = builder.fieldKey;
    this.enumOptionKey = builder.enumOptionKey;
    this.data = builder.data;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateClassificationRequestBodyOpField> getOp() {
    return op;
  }

  public EnumWrapper<UpdateClassificationRequestBodyFieldKeyField> getFieldKey() {
    return fieldKey;
  }

  public String getEnumOptionKey() {
    return enumOptionKey;
  }

  public UpdateClassificationRequestBodyDataField getData() {
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
    UpdateClassificationRequestBody casted = (UpdateClassificationRequestBody) o;
    return Objects.equals(op, casted.op)
        && Objects.equals(fieldKey, casted.fieldKey)
        && Objects.equals(enumOptionKey, casted.enumOptionKey)
        && Objects.equals(data, casted.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(op, fieldKey, enumOptionKey, data);
  }

  @Override
  public String toString() {
    return "UpdateClassificationRequestBody{"
        + "op='"
        + op
        + '\''
        + ", "
        + "fieldKey='"
        + fieldKey
        + '\''
        + ", "
        + "enumOptionKey='"
        + enumOptionKey
        + '\''
        + ", "
        + "data='"
        + data
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateClassificationRequestBodyOpField> op;

    protected EnumWrapper<UpdateClassificationRequestBodyFieldKeyField> fieldKey;

    protected final String enumOptionKey;

    protected final UpdateClassificationRequestBodyDataField data;

    public Builder(String enumOptionKey, UpdateClassificationRequestBodyDataField data) {
      super();
      this.enumOptionKey = enumOptionKey;
      this.data = data;
      this.op =
          new EnumWrapper<UpdateClassificationRequestBodyOpField>(
              UpdateClassificationRequestBodyOpField.EDITENUMOPTION);
      this.fieldKey =
          new EnumWrapper<UpdateClassificationRequestBodyFieldKeyField>(
              UpdateClassificationRequestBodyFieldKeyField.BOX__SECURITY__CLASSIFICATION__KEY);
    }

    public Builder op(UpdateClassificationRequestBodyOpField op) {
      this.op = new EnumWrapper<UpdateClassificationRequestBodyOpField>(op);
      return this;
    }

    public Builder op(EnumWrapper<UpdateClassificationRequestBodyOpField> op) {
      this.op = op;
      return this;
    }

    public Builder fieldKey(UpdateClassificationRequestBodyFieldKeyField fieldKey) {
      this.fieldKey = new EnumWrapper<UpdateClassificationRequestBodyFieldKeyField>(fieldKey);
      return this;
    }

    public Builder fieldKey(EnumWrapper<UpdateClassificationRequestBodyFieldKeyField> fieldKey) {
      this.fieldKey = fieldKey;
      return this;
    }

    public UpdateClassificationRequestBody build() {
      return new UpdateClassificationRequestBody(this);
    }
  }
}
