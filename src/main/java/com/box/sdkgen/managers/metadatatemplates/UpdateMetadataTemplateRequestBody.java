package com.box.sdkgen.managers.metadatatemplates;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateMetadataTemplateRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateMetadataTemplateRequestBodyOpField
              .UpdateMetadataTemplateRequestBodyOpFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateMetadataTemplateRequestBodyOpField
              .UpdateMetadataTemplateRequestBodyOpFieldSerializer.class)
  protected final EnumWrapper<UpdateMetadataTemplateRequestBodyOpField> op;

  protected Map<String, Object> data;

  protected String fieldKey;

  protected List<String> fieldKeys;

  protected String enumOptionKey;

  protected List<String> enumOptionKeys;

  protected String multiSelectOptionKey;

  protected List<String> multiSelectOptionKeys;

  public UpdateMetadataTemplateRequestBody(UpdateMetadataTemplateRequestBodyOpField op) {
    super();
    this.op = new EnumWrapper<UpdateMetadataTemplateRequestBodyOpField>(op);
  }

  public UpdateMetadataTemplateRequestBody(
      @JsonProperty("op") EnumWrapper<UpdateMetadataTemplateRequestBodyOpField> op) {
    super();
    this.op = op;
  }

  protected UpdateMetadataTemplateRequestBody(Builder builder) {
    super();
    this.op = builder.op;
    this.data = builder.data;
    this.fieldKey = builder.fieldKey;
    this.fieldKeys = builder.fieldKeys;
    this.enumOptionKey = builder.enumOptionKey;
    this.enumOptionKeys = builder.enumOptionKeys;
    this.multiSelectOptionKey = builder.multiSelectOptionKey;
    this.multiSelectOptionKeys = builder.multiSelectOptionKeys;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateMetadataTemplateRequestBodyOpField> getOp() {
    return op;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public String getFieldKey() {
    return fieldKey;
  }

  public List<String> getFieldKeys() {
    return fieldKeys;
  }

  public String getEnumOptionKey() {
    return enumOptionKey;
  }

  public List<String> getEnumOptionKeys() {
    return enumOptionKeys;
  }

  public String getMultiSelectOptionKey() {
    return multiSelectOptionKey;
  }

  public List<String> getMultiSelectOptionKeys() {
    return multiSelectOptionKeys;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateMetadataTemplateRequestBody casted = (UpdateMetadataTemplateRequestBody) o;
    return Objects.equals(op, casted.op)
        && Objects.equals(data, casted.data)
        && Objects.equals(fieldKey, casted.fieldKey)
        && Objects.equals(fieldKeys, casted.fieldKeys)
        && Objects.equals(enumOptionKey, casted.enumOptionKey)
        && Objects.equals(enumOptionKeys, casted.enumOptionKeys)
        && Objects.equals(multiSelectOptionKey, casted.multiSelectOptionKey)
        && Objects.equals(multiSelectOptionKeys, casted.multiSelectOptionKeys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        op,
        data,
        fieldKey,
        fieldKeys,
        enumOptionKey,
        enumOptionKeys,
        multiSelectOptionKey,
        multiSelectOptionKeys);
  }

  @Override
  public String toString() {
    return "UpdateMetadataTemplateRequestBody{"
        + "op='"
        + op
        + '\''
        + ", "
        + "data='"
        + data
        + '\''
        + ", "
        + "fieldKey='"
        + fieldKey
        + '\''
        + ", "
        + "fieldKeys='"
        + fieldKeys
        + '\''
        + ", "
        + "enumOptionKey='"
        + enumOptionKey
        + '\''
        + ", "
        + "enumOptionKeys='"
        + enumOptionKeys
        + '\''
        + ", "
        + "multiSelectOptionKey='"
        + multiSelectOptionKey
        + '\''
        + ", "
        + "multiSelectOptionKeys='"
        + multiSelectOptionKeys
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<UpdateMetadataTemplateRequestBodyOpField> op;

    protected Map<String, Object> data;

    protected String fieldKey;

    protected List<String> fieldKeys;

    protected String enumOptionKey;

    protected List<String> enumOptionKeys;

    protected String multiSelectOptionKey;

    protected List<String> multiSelectOptionKeys;

    public Builder(UpdateMetadataTemplateRequestBodyOpField op) {
      super();
      this.op = new EnumWrapper<UpdateMetadataTemplateRequestBodyOpField>(op);
    }

    public Builder(EnumWrapper<UpdateMetadataTemplateRequestBodyOpField> op) {
      super();
      this.op = op;
    }

    public Builder data(Map<String, Object> data) {
      this.data = data;
      return this;
    }

    public Builder fieldKey(String fieldKey) {
      this.fieldKey = fieldKey;
      return this;
    }

    public Builder fieldKeys(List<String> fieldKeys) {
      this.fieldKeys = fieldKeys;
      return this;
    }

    public Builder enumOptionKey(String enumOptionKey) {
      this.enumOptionKey = enumOptionKey;
      return this;
    }

    public Builder enumOptionKeys(List<String> enumOptionKeys) {
      this.enumOptionKeys = enumOptionKeys;
      return this;
    }

    public Builder multiSelectOptionKey(String multiSelectOptionKey) {
      this.multiSelectOptionKey = multiSelectOptionKey;
      return this;
    }

    public Builder multiSelectOptionKeys(List<String> multiSelectOptionKeys) {
      this.multiSelectOptionKeys = multiSelectOptionKeys;
      return this;
    }

    public UpdateMetadataTemplateRequestBody build() {
      return new UpdateMetadataTemplateRequestBody(this);
    }
  }
}
