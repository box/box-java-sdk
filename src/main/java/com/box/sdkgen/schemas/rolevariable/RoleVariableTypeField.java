package com.box.sdkgen.schemas.rolevariable;

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

public enum RoleVariableTypeField implements Valuable {
  VARIABLE("variable");

  private final String value;

  RoleVariableTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class RoleVariableTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<RoleVariableTypeField>> {

    public RoleVariableTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RoleVariableTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RoleVariableTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RoleVariableTypeField>(value));
    }
  }

  public static class RoleVariableTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<RoleVariableTypeField>> {

    public RoleVariableTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RoleVariableTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
