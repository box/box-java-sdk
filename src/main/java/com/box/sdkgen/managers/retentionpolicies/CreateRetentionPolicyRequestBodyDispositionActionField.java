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

public enum CreateRetentionPolicyRequestBodyDispositionActionField implements Valuable {
  PERMANENTLY_DELETE("permanently_delete"),
  REMOVE_RETENTION("remove_retention");

  private final String value;

  CreateRetentionPolicyRequestBodyDispositionActionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateRetentionPolicyRequestBodyDispositionActionFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>> {

    public CreateRetentionPolicyRequestBodyDispositionActionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateRetentionPolicyRequestBodyDispositionActionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>(value));
    }
  }

  public static class CreateRetentionPolicyRequestBodyDispositionActionFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField>> {

    public CreateRetentionPolicyRequestBodyDispositionActionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateRetentionPolicyRequestBodyDispositionActionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
