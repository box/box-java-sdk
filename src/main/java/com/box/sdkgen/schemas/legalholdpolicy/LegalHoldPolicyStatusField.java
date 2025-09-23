package com.box.sdkgen.schemas.legalholdpolicy;

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

public enum LegalHoldPolicyStatusField implements Valuable {
  ACTIVE("active"),
  APPLYING("applying"),
  RELEASING("releasing"),
  RELEASED("released");

  private final String value;

  LegalHoldPolicyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class LegalHoldPolicyStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<LegalHoldPolicyStatusField>> {

    public LegalHoldPolicyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<LegalHoldPolicyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(LegalHoldPolicyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<LegalHoldPolicyStatusField>(value));
    }
  }

  public static class LegalHoldPolicyStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<LegalHoldPolicyStatusField>> {

    public LegalHoldPolicyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<LegalHoldPolicyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
