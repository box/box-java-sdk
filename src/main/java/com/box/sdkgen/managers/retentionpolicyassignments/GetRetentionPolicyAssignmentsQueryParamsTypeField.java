package com.box.sdkgen.managers.retentionpolicyassignments;

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

public enum GetRetentionPolicyAssignmentsQueryParamsTypeField implements Valuable {
  FOLDER("folder"),
  ENTERPRISE("enterprise"),
  METADATA_TEMPLATE("metadata_template");

  private final String value;

  GetRetentionPolicyAssignmentsQueryParamsTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetRetentionPolicyAssignmentsQueryParamsTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField>> {

    public GetRetentionPolicyAssignmentsQueryParamsTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetRetentionPolicyAssignmentsQueryParamsTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField>(value));
    }
  }

  public static class GetRetentionPolicyAssignmentsQueryParamsTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField>> {

    public GetRetentionPolicyAssignmentsQueryParamsTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
