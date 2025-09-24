package com.box.sdkgen.schemas.legalholdpolicyassignmentbase;

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

public enum LegalHoldPolicyAssignmentBaseTypeField implements Valuable {
  LEGAL_HOLD_POLICY_ASSIGNMENT("legal_hold_policy_assignment");

  private final String value;

  LegalHoldPolicyAssignmentBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class LegalHoldPolicyAssignmentBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField>> {

    public LegalHoldPolicyAssignmentBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(LegalHoldPolicyAssignmentBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField>(value));
    }
  }

  public static class LegalHoldPolicyAssignmentBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField>> {

    public LegalHoldPolicyAssignmentBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
