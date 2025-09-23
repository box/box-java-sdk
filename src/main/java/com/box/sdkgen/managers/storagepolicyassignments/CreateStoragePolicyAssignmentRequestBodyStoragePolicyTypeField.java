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

public enum CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeField implements Valuable {
  STORAGE_POLICY("storage_policy");

  private final String value;

  CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeField>> {

    public CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeField>(
                  value));
    }
  }

  public static class CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeField>> {

    public CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateStoragePolicyAssignmentRequestBodyStoragePolicyTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
