package com.box.sdkgen.schemas.templatesignerverificationmethodlogin;

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

public enum TemplateSignerVerificationMethodLoginTypeField implements Valuable {
  LOGIN("login");

  private final String value;

  TemplateSignerVerificationMethodLoginTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class TemplateSignerVerificationMethodLoginTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TemplateSignerVerificationMethodLoginTypeField>> {

    public TemplateSignerVerificationMethodLoginTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TemplateSignerVerificationMethodLoginTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TemplateSignerVerificationMethodLoginTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TemplateSignerVerificationMethodLoginTypeField>(value));
    }
  }

  public static class TemplateSignerVerificationMethodLoginTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TemplateSignerVerificationMethodLoginTypeField>> {

    public TemplateSignerVerificationMethodLoginTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TemplateSignerVerificationMethodLoginTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
