package com.box.sdkgen.schemas.legalholdpolicyassigneditem;

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

public enum LegalHoldPolicyAssignedItemTypeField implements Valuable {
  FILE("file"),
  FILE_VERSION("file_version"),
  FOLDER("folder"),
  USER("user"),
  OWNERSHIP("ownership"),
  INTERACTIONS("interactions");

  private final String value;

  LegalHoldPolicyAssignedItemTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class LegalHoldPolicyAssignedItemTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<LegalHoldPolicyAssignedItemTypeField>> {

    public LegalHoldPolicyAssignedItemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<LegalHoldPolicyAssignedItemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(LegalHoldPolicyAssignedItemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<LegalHoldPolicyAssignedItemTypeField>(value));
    }
  }

  public static class LegalHoldPolicyAssignedItemTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<LegalHoldPolicyAssignedItemTypeField>> {

    public LegalHoldPolicyAssignedItemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<LegalHoldPolicyAssignedItemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
