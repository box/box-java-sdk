package com.box.sdkgen.schemas.legalholdpolicymini;

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

public enum LegalHoldPolicyMiniTypeField implements Valuable {
  LEGAL_HOLD_POLICY("legal_hold_policy");

  private final String value;

  LegalHoldPolicyMiniTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class LegalHoldPolicyMiniTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<LegalHoldPolicyMiniTypeField>> {

    public LegalHoldPolicyMiniTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<LegalHoldPolicyMiniTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(LegalHoldPolicyMiniTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<LegalHoldPolicyMiniTypeField>(value));
    }
  }

  public static class LegalHoldPolicyMiniTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<LegalHoldPolicyMiniTypeField>> {

    public LegalHoldPolicyMiniTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<LegalHoldPolicyMiniTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
