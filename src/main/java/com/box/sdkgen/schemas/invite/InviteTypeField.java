package com.box.sdkgen.schemas.invite;

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

public enum InviteTypeField implements Valuable {
  INVITE("invite");

  private final String value;

  InviteTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class InviteTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<InviteTypeField>> {

    public InviteTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<InviteTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(InviteTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<InviteTypeField>(value));
    }
  }

  public static class InviteTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<InviteTypeField>> {

    public InviteTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<InviteTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
