package com.box.sdkgen.schemas.collaboratorvariable;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class CollaboratorVariable extends SerializableObject {

  @JsonDeserialize(
      using = CollaboratorVariableTypeField.CollaboratorVariableTypeFieldDeserializer.class)
  @JsonSerialize(
      using = CollaboratorVariableTypeField.CollaboratorVariableTypeFieldSerializer.class)
  protected EnumWrapper<CollaboratorVariableTypeField> type;

  @JsonDeserialize(
      using =
          CollaboratorVariableVariableTypeField.CollaboratorVariableVariableTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          CollaboratorVariableVariableTypeField.CollaboratorVariableVariableTypeFieldSerializer
              .class)
  @JsonProperty("variable_type")
  protected EnumWrapper<CollaboratorVariableVariableTypeField> variableType;

  @JsonProperty("variable_value")
  protected final List<CollaboratorVariableVariableValueField> variableValue;

  public CollaboratorVariable(
      @JsonProperty("variable_value") List<CollaboratorVariableVariableValueField> variableValue) {
    super();
    this.variableValue = variableValue;
    this.type =
        new EnumWrapper<CollaboratorVariableTypeField>(CollaboratorVariableTypeField.VARIABLE);
    this.variableType =
        new EnumWrapper<CollaboratorVariableVariableTypeField>(
            CollaboratorVariableVariableTypeField.USER_LIST);
  }

  protected CollaboratorVariable(CollaboratorVariableBuilder builder) {
    super();
    this.type = builder.type;
    this.variableType = builder.variableType;
    this.variableValue = builder.variableValue;
  }

  public EnumWrapper<CollaboratorVariableTypeField> getType() {
    return type;
  }

  public EnumWrapper<CollaboratorVariableVariableTypeField> getVariableType() {
    return variableType;
  }

  public List<CollaboratorVariableVariableValueField> getVariableValue() {
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
    CollaboratorVariable casted = (CollaboratorVariable) o;
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
    return "CollaboratorVariable{"
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

  public static class CollaboratorVariableBuilder {

    protected EnumWrapper<CollaboratorVariableTypeField> type;

    protected EnumWrapper<CollaboratorVariableVariableTypeField> variableType;

    protected final List<CollaboratorVariableVariableValueField> variableValue;

    public CollaboratorVariableBuilder(List<CollaboratorVariableVariableValueField> variableValue) {
      this.variableValue = variableValue;
      this.type =
          new EnumWrapper<CollaboratorVariableTypeField>(CollaboratorVariableTypeField.VARIABLE);
      this.variableType =
          new EnumWrapper<CollaboratorVariableVariableTypeField>(
              CollaboratorVariableVariableTypeField.USER_LIST);
    }

    public CollaboratorVariableBuilder type(CollaboratorVariableTypeField type) {
      this.type = new EnumWrapper<CollaboratorVariableTypeField>(type);
      return this;
    }

    public CollaboratorVariableBuilder type(EnumWrapper<CollaboratorVariableTypeField> type) {
      this.type = type;
      return this;
    }

    public CollaboratorVariableBuilder variableType(
        CollaboratorVariableVariableTypeField variableType) {
      this.variableType = new EnumWrapper<CollaboratorVariableVariableTypeField>(variableType);
      return this;
    }

    public CollaboratorVariableBuilder variableType(
        EnumWrapper<CollaboratorVariableVariableTypeField> variableType) {
      this.variableType = variableType;
      return this;
    }

    public CollaboratorVariable build() {
      return new CollaboratorVariable(this);
    }
  }
}
