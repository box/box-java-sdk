package com.box.sdkgen.schemas.signtemplate;

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

public enum SignTemplateAdditionalInfoNonEditableField implements Valuable {
  EMAIL_SUBJECT("email_subject"),
  EMAIL_MESSAGE("email_message"),
  NAME("name"),
  DAYS_VALID("days_valid"),
  SIGNERS("signers"),
  SOURCE_FILES("source_files");

  private final String value;

  SignTemplateAdditionalInfoNonEditableField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignTemplateAdditionalInfoNonEditableFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>> {

    public SignTemplateAdditionalInfoNonEditableFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignTemplateAdditionalInfoNonEditableField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignTemplateAdditionalInfoNonEditableField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignTemplateAdditionalInfoNonEditableField>(value));
    }
  }

  public static class SignTemplateAdditionalInfoNonEditableFieldSerializer
      extends JsonSerializer<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>> {

    public SignTemplateAdditionalInfoNonEditableFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignTemplateAdditionalInfoNonEditableField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
