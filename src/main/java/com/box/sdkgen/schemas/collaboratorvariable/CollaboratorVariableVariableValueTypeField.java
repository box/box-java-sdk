package com.box.sdkgen.schemas.collaboratorvariable;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum CollaboratorVariableVariableValueTypeField implements Valuable {
  USER("user");

  private final String value;

  CollaboratorVariableVariableValueTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollaboratorVariableVariableValueTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollaboratorVariableVariableValueTypeField>> {

    public CollaboratorVariableVariableValueTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollaboratorVariableVariableValueTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollaboratorVariableVariableValueTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollaboratorVariableVariableValueTypeField>(value));
    }
  }

  public static class CollaboratorVariableVariableValueTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CollaboratorVariableVariableValueTypeField>> {

    public CollaboratorVariableVariableValueTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollaboratorVariableVariableValueTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
