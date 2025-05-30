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

public enum CreateClassificationTemplateRequestBodyScopeField implements Valuable {
  ENTERPRISE("enterprise");

  private final String value;

  CreateClassificationTemplateRequestBodyScopeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateClassificationTemplateRequestBodyScopeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateClassificationTemplateRequestBodyScopeField>> {

    public CreateClassificationTemplateRequestBodyScopeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateClassificationTemplateRequestBodyScopeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateClassificationTemplateRequestBodyScopeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateClassificationTemplateRequestBodyScopeField>(value));
    }
  }

  public static class CreateClassificationTemplateRequestBodyScopeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateClassificationTemplateRequestBodyScopeField>> {

    public CreateClassificationTemplateRequestBodyScopeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateClassificationTemplateRequestBodyScopeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
