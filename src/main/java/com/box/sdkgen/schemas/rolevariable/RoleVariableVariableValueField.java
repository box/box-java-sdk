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

public enum RoleVariableVariableValueField implements Valuable {
  EDITOR("editor"),
  VIEWER("viewer"),
  PREVIEWER("previewer"),
  UPLOADER("uploader"),
  PREVIEWER_UPLOADER("previewer uploader"),
  VIEWER_UPLOADER("viewer uploader"),
  CO_OWNER("co-owner");

  private final String value;

  RoleVariableVariableValueField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class RoleVariableVariableValueFieldDeserializer
      extends JsonDeserializer<EnumWrapper<RoleVariableVariableValueField>> {

    public RoleVariableVariableValueFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RoleVariableVariableValueField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RoleVariableVariableValueField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RoleVariableVariableValueField>(value));
    }
  }

  public static class RoleVariableVariableValueFieldSerializer
      extends JsonSerializer<EnumWrapper<RoleVariableVariableValueField>> {

    public RoleVariableVariableValueFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RoleVariableVariableValueField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
