package com.box.sdkgen.schemas.templatesignerverificationmethodphonesms;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum TemplateSignerVerificationMethodPhoneSmsTypeField implements Valuable {
  PHONE("phone");

  private final String value;

  TemplateSignerVerificationMethodPhoneSmsTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class TemplateSignerVerificationMethodPhoneSmsTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField>> {

    public TemplateSignerVerificationMethodPhoneSmsTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TemplateSignerVerificationMethodPhoneSmsTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField>(value));
    }
  }

  public static class TemplateSignerVerificationMethodPhoneSmsTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField>> {

    public TemplateSignerVerificationMethodPhoneSmsTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
