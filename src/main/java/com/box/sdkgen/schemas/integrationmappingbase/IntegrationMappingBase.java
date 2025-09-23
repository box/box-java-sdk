package com.box.sdkgen.schemas.integrationmappingbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingBase extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using = IntegrationMappingBaseTypeField.IntegrationMappingBaseTypeFieldDeserializer.class)
  @JsonSerialize(
      using = IntegrationMappingBaseTypeField.IntegrationMappingBaseTypeFieldSerializer.class)
  protected EnumWrapper<IntegrationMappingBaseTypeField> type;

  public IntegrationMappingBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<IntegrationMappingBaseTypeField>(
            IntegrationMappingBaseTypeField.INTEGRATION_MAPPING);
  }

  protected IntegrationMappingBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<IntegrationMappingBaseTypeField> getType() {
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
    IntegrationMappingBase casted = (IntegrationMappingBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "IntegrationMappingBase{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<IntegrationMappingBaseTypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<IntegrationMappingBaseTypeField>(
              IntegrationMappingBaseTypeField.INTEGRATION_MAPPING);
    }

    public Builder type(IntegrationMappingBaseTypeField type) {
      this.type = new EnumWrapper<IntegrationMappingBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<IntegrationMappingBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public IntegrationMappingBase build() {
      return new IntegrationMappingBase(this);
    }
  }
}
