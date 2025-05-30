package com.box.sdkgen.managers.storagepolicyassignments;

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

public enum CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField implements Valuable {
  USER("user"),
  ENTERPRISE("enterprise");

  private final String value;

  CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateStoragePolicyAssignmentRequestBodyAssignedToTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField>> {

    public CreateStoragePolicyAssignmentRequestBodyAssignedToTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField>(value));
    }
  }

  public static class CreateStoragePolicyAssignmentRequestBodyAssignedToTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField>> {

    public CreateStoragePolicyAssignmentRequestBodyAssignedToTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
