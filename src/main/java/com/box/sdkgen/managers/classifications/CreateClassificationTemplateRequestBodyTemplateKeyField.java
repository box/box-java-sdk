package com.box.sdkgen.managers.classifications;

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

public enum CreateClassificationTemplateRequestBodyTemplateKeyField implements Valuable {
  SECURITYCLASSIFICATION_6VMVOCHWUWO("securityClassification-6VMVochwUWo");

  private final String value;

  CreateClassificationTemplateRequestBodyTemplateKeyField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateClassificationTemplateRequestBodyTemplateKeyFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField>> {

    public CreateClassificationTemplateRequestBodyTemplateKeyFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateClassificationTemplateRequestBodyTemplateKeyField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField>(value));
    }
  }

  public static class CreateClassificationTemplateRequestBodyTemplateKeyFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField>> {

    public CreateClassificationTemplateRequestBodyTemplateKeyFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
