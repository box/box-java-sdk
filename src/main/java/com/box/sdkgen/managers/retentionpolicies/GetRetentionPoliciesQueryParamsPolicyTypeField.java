package com.box.sdkgen.managers.retentionpolicies;

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

public enum GetRetentionPoliciesQueryParamsPolicyTypeField implements Valuable {
  FINITE("finite"),
  INDEFINITE("indefinite");

  private final String value;

  GetRetentionPoliciesQueryParamsPolicyTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetRetentionPoliciesQueryParamsPolicyTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField>> {

    public GetRetentionPoliciesQueryParamsPolicyTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetRetentionPoliciesQueryParamsPolicyTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField>(value));
    }
  }

  public static class GetRetentionPoliciesQueryParamsPolicyTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField>> {

    public GetRetentionPoliciesQueryParamsPolicyTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
