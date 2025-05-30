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

public enum GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField implements Valuable {
  USER("user"),
  ENTERPRISE("enterprise");

  private final String value;

  GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetStoragePolicyAssignmentsQueryParamsResolvedForTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField>> {

    public GetStoragePolicyAssignmentsQueryParamsResolvedForTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField>(value));
    }
  }

  public static class GetStoragePolicyAssignmentsQueryParamsResolvedForTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField>> {

    public GetStoragePolicyAssignmentsQueryParamsResolvedForTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
