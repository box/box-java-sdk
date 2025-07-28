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

public enum CreateClassificationTemplateRequestBodyDisplayNameField implements Valuable {
  CLASSIFICATION("Classification");

  private final String value;

  CreateClassificationTemplateRequestBodyDisplayNameField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateClassificationTemplateRequestBodyDisplayNameFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField>> {

    public CreateClassificationTemplateRequestBodyDisplayNameFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateClassificationTemplateRequestBodyDisplayNameField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField>(value));
    }
  }

  public static class CreateClassificationTemplateRequestBodyDisplayNameFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField>> {

    public CreateClassificationTemplateRequestBodyDisplayNameFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
