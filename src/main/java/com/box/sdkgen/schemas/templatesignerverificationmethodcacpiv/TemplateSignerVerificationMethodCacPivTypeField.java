package com.box.sdkgen.schemas.templatesignerverificationmethodcacpiv;

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

public enum TemplateSignerVerificationMethodCacPivTypeField implements Valuable {
  CAC_PIV("cac_piv");

  private final String value;

  TemplateSignerVerificationMethodCacPivTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class TemplateSignerVerificationMethodCacPivTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField>> {

    public TemplateSignerVerificationMethodCacPivTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TemplateSignerVerificationMethodCacPivTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField>(value));
    }
  }

  public static class TemplateSignerVerificationMethodCacPivTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField>> {

    public TemplateSignerVerificationMethodCacPivTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
