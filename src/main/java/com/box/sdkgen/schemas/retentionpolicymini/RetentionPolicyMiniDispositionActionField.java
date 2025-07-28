package com.box.sdkgen.schemas.retentionpolicymini;

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

public enum RetentionPolicyMiniDispositionActionField implements Valuable {
  PERMANENTLY_DELETE("permanently_delete"),
  REMOVE_RETENTION("remove_retention");

  private final String value;

  RetentionPolicyMiniDispositionActionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class RetentionPolicyMiniDispositionActionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<RetentionPolicyMiniDispositionActionField>> {

    public RetentionPolicyMiniDispositionActionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RetentionPolicyMiniDispositionActionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RetentionPolicyMiniDispositionActionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RetentionPolicyMiniDispositionActionField>(value));
    }
  }

  public static class RetentionPolicyMiniDispositionActionFieldSerializer
      extends JsonSerializer<EnumWrapper<RetentionPolicyMiniDispositionActionField>> {

    public RetentionPolicyMiniDispositionActionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RetentionPolicyMiniDispositionActionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
