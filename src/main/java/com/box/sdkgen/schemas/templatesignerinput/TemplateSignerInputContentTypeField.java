package com.box.sdkgen.schemas.templatesignerinput;

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

public enum TemplateSignerInputContentTypeField implements Valuable {
  SIGNATURE("signature"),
  INITIAL("initial"),
  STAMP("stamp"),
  DATE("date"),
  CHECKBOX("checkbox"),
  TEXT("text"),
  FULL_NAME("full_name"),
  FIRST_NAME("first_name"),
  LAST_NAME("last_name"),
  COMPANY("company"),
  TITLE("title"),
  EMAIL("email"),
  ATTACHMENT("attachment"),
  RADIO("radio"),
  DROPDOWN("dropdown");

  private final String value;

  TemplateSignerInputContentTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TemplateSignerInputContentTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TemplateSignerInputContentTypeField>> {

    public TemplateSignerInputContentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TemplateSignerInputContentTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TemplateSignerInputContentTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TemplateSignerInputContentTypeField>(value));
    }
  }

  public static class TemplateSignerInputContentTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TemplateSignerInputContentTypeField>> {

    public TemplateSignerInputContentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TemplateSignerInputContentTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
