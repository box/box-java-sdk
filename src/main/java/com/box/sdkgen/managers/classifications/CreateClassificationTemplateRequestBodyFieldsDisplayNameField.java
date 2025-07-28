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

public enum CreateClassificationTemplateRequestBodyFieldsDisplayNameField implements Valuable {
  CLASSIFICATION("Classification");

  private final String value;

  CreateClassificationTemplateRequestBodyFieldsDisplayNameField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateClassificationTemplateRequestBodyFieldsDisplayNameFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField>> {

    public CreateClassificationTemplateRequestBodyFieldsDisplayNameFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateClassificationTemplateRequestBodyFieldsDisplayNameField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField>(
                  value));
    }
  }

  public static class CreateClassificationTemplateRequestBodyFieldsDisplayNameFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField>> {

    public CreateClassificationTemplateRequestBodyFieldsDisplayNameFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
