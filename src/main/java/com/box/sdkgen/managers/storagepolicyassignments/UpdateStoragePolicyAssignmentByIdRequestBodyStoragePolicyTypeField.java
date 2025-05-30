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

public enum UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField implements Valuable {
  STORAGE_POLICY("storage_policy");

  private final String value;

  UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>> {

    public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>(
                  value));
    }
  }

  public static class UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField>> {

    public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
