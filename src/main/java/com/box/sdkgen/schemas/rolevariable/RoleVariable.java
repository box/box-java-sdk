package com.box.sdkgen.schemas.rolevariable;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RoleVariable extends SerializableObject {

  @JsonDeserialize(using = RoleVariableTypeField.RoleVariableTypeFieldDeserializer.class)
  @JsonSerialize(using = RoleVariableTypeField.RoleVariableTypeFieldSerializer.class)
  protected EnumWrapper<RoleVariableTypeField> type;

  @JsonDeserialize(
      using = RoleVariableVariableTypeField.RoleVariableVariableTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RoleVariableVariableTypeField.RoleVariableVariableTypeFieldSerializer.class)
  @JsonProperty("variable_type")
  protected EnumWrapper<RoleVariableVariableTypeField> variableType;

  @JsonDeserialize(
      using = RoleVariableVariableValueField.RoleVariableVariableValueFieldDeserializer.class)
  @JsonSerialize(
      using = RoleVariableVariableValueField.RoleVariableVariableValueFieldSerializer.class)
  @JsonProperty("variable_value")
  protected final EnumWrapper<RoleVariableVariableValueField> variableValue;

  public RoleVariable(RoleVariableVariableValueField variableValue) {
    super();
    this.variableValue = new EnumWrapper<RoleVariableVariableValueField>(variableValue);
    this.type = new EnumWrapper<RoleVariableTypeField>(RoleVariableTypeField.VARIABLE);
    this.variableType =
        new EnumWrapper<RoleVariableVariableTypeField>(
            RoleVariableVariableTypeField.COLLABORATOR_ROLE);
  }

  public RoleVariable(
      @JsonProperty("variable_value") EnumWrapper<RoleVariableVariableValueField> variableValue) {
    super();
    this.variableValue = variableValue;
    this.type = new EnumWrapper<RoleVariableTypeField>(RoleVariableTypeField.VARIABLE);
    this.variableType =
        new EnumWrapper<RoleVariableVariableTypeField>(
            RoleVariableVariableTypeField.COLLABORATOR_ROLE);
  }

  protected RoleVariable(Builder builder) {
    super();
    this.type = builder.type;
    this.variableType = builder.variableType;
    this.variableValue = builder.variableValue;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<RoleVariableTypeField> getType() {
    return type;
  }

  public EnumWrapper<RoleVariableVariableTypeField> getVariableType() {
    return variableType;
  }

  public EnumWrapper<RoleVariableVariableValueField> getVariableValue() {
    return variableValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleVariable casted = (RoleVariable) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(variableType, casted.variableType)
        && Objects.equals(variableValue, casted.variableValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, variableType, variableValue);
  }

  @Override
  public String toString() {
    return "RoleVariable{"
        + "type='"
        + type
        + '\''
        + ", "
        + "variableType='"
        + variableType
        + '\''
        + ", "
        + "variableValue='"
        + variableValue
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<RoleVariableTypeField> type;

    protected EnumWrapper<RoleVariableVariableTypeField> variableType;

    protected final EnumWrapper<RoleVariableVariableValueField> variableValue;

    public Builder(RoleVariableVariableValueField variableValue) {
      super();
      this.variableValue = new EnumWrapper<RoleVariableVariableValueField>(variableValue);
      this.type = new EnumWrapper<RoleVariableTypeField>(RoleVariableTypeField.VARIABLE);
      this.variableType =
          new EnumWrapper<RoleVariableVariableTypeField>(
              RoleVariableVariableTypeField.COLLABORATOR_ROLE);
    }

    public Builder(EnumWrapper<RoleVariableVariableValueField> variableValue) {
      super();
      this.variableValue = variableValue;
      this.type = new EnumWrapper<RoleVariableTypeField>(RoleVariableTypeField.VARIABLE);
      this.variableType =
          new EnumWrapper<RoleVariableVariableTypeField>(
              RoleVariableVariableTypeField.COLLABORATOR_ROLE);
    }

    public Builder type(RoleVariableTypeField type) {
      this.type = new EnumWrapper<RoleVariableTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<RoleVariableTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder variableType(RoleVariableVariableTypeField variableType) {
      this.variableType = new EnumWrapper<RoleVariableVariableTypeField>(variableType);
      return this;
    }

    public Builder variableType(EnumWrapper<RoleVariableVariableTypeField> variableType) {
      this.variableType = variableType;
      return this;
    }

    public RoleVariable build() {
      return new RoleVariable(this);
    }
  }
}
