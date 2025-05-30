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

public enum CreateRetentionPolicyRequestBodyRetentionTypeField implements Valuable {
  MODIFIABLE("modifiable"),
  NON_MODIFIABLE("non_modifiable");

  private final String value;

  CreateRetentionPolicyRequestBodyRetentionTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateRetentionPolicyRequestBodyRetentionTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField>> {

    public CreateRetentionPolicyRequestBodyRetentionTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateRetentionPolicyRequestBodyRetentionTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField>(value));
    }
  }

  public static class CreateRetentionPolicyRequestBodyRetentionTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField>> {

    public CreateRetentionPolicyRequestBodyRetentionTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateRetentionPolicyRequestBodyRetentionTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
