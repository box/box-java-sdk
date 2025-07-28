package com.box.sdkgen.schemas.completionrulevariable;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CompletionRuleVariable extends SerializableObject {

  @JsonDeserialize(
      using = CompletionRuleVariableTypeField.CompletionRuleVariableTypeFieldDeserializer.class)
  @JsonSerialize(
      using = CompletionRuleVariableTypeField.CompletionRuleVariableTypeFieldSerializer.class)
  protected EnumWrapper<CompletionRuleVariableTypeField> type;

  @JsonDeserialize(
      using =
          CompletionRuleVariableVariableTypeField
              .CompletionRuleVariableVariableTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CompletionRuleVariableVariableTypeField.CompletionRuleVariableVariableTypeFieldSerializer
              .class)
  @JsonProperty("variable_type")
  protected EnumWrapper<CompletionRuleVariableVariableTypeField> variableType;

  @JsonDeserialize(
      using =
          CompletionRuleVariableVariableValueField
              .CompletionRuleVariableVariableValueFieldDeserializer.class)
  @JsonSerialize(
      using =
          CompletionRuleVariableVariableValueField
              .CompletionRuleVariableVariableValueFieldSerializer.class)
  @JsonProperty("variable_value")
  protected final EnumWrapper<CompletionRuleVariableVariableValueField> variableValue;

  public CompletionRuleVariable(CompletionRuleVariableVariableValueField variableValue) {
    super();
    this.variableValue = new EnumWrapper<CompletionRuleVariableVariableValueField>(variableValue);
    this.type =
        new EnumWrapper<CompletionRuleVariableTypeField>(CompletionRuleVariableTypeField.VARIABLE);
    this.variableType =
        new EnumWrapper<CompletionRuleVariableVariableTypeField>(
            CompletionRuleVariableVariableTypeField.TASK_COMPLETION_RULE);
  }

  public CompletionRuleVariable(
      @JsonProperty("variable_value")
          EnumWrapper<CompletionRuleVariableVariableValueField> variableValue) {
    super();
    this.variableValue = variableValue;
    this.type =
        new EnumWrapper<CompletionRuleVariableTypeField>(CompletionRuleVariableTypeField.VARIABLE);
    this.variableType =
        new EnumWrapper<CompletionRuleVariableVariableTypeField>(
            CompletionRuleVariableVariableTypeField.TASK_COMPLETION_RULE);
  }

  protected CompletionRuleVariable(Builder builder) {
    super();
    this.type = builder.type;
    this.variableType = builder.variableType;
    this.variableValue = builder.variableValue;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CompletionRuleVariableTypeField> getType() {
    return type;
  }

  public EnumWrapper<CompletionRuleVariableVariableTypeField> getVariableType() {
    return variableType;
  }

  public EnumWrapper<CompletionRuleVariableVariableValueField> getVariableValue() {
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
    CompletionRuleVariable casted = (CompletionRuleVariable) o;
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
    return "CompletionRuleVariable{"
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

    protected EnumWrapper<CompletionRuleVariableTypeField> type;

    protected EnumWrapper<CompletionRuleVariableVariableTypeField> variableType;

    protected final EnumWrapper<CompletionRuleVariableVariableValueField> variableValue;

    public Builder(CompletionRuleVariableVariableValueField variableValue) {
      super();
      this.variableValue = new EnumWrapper<CompletionRuleVariableVariableValueField>(variableValue);
      this.type =
          new EnumWrapper<CompletionRuleVariableTypeField>(
              CompletionRuleVariableTypeField.VARIABLE);
      this.variableType =
          new EnumWrapper<CompletionRuleVariableVariableTypeField>(
              CompletionRuleVariableVariableTypeField.TASK_COMPLETION_RULE);
    }

    public Builder(EnumWrapper<CompletionRuleVariableVariableValueField> variableValue) {
      super();
      this.variableValue = variableValue;
      this.type =
          new EnumWrapper<CompletionRuleVariableTypeField>(
              CompletionRuleVariableTypeField.VARIABLE);
      this.variableType =
          new EnumWrapper<CompletionRuleVariableVariableTypeField>(
              CompletionRuleVariableVariableTypeField.TASK_COMPLETION_RULE);
    }

    public Builder type(CompletionRuleVariableTypeField type) {
      this.type = new EnumWrapper<CompletionRuleVariableTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CompletionRuleVariableTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder variableType(CompletionRuleVariableVariableTypeField variableType) {
      this.variableType = new EnumWrapper<CompletionRuleVariableVariableTypeField>(variableType);
      return this;
    }

    public Builder variableType(EnumWrapper<CompletionRuleVariableVariableTypeField> variableType) {
      this.variableType = variableType;
      return this;
    }

    public CompletionRuleVariable build() {
      return new CompletionRuleVariable(this);
    }
  }
}
