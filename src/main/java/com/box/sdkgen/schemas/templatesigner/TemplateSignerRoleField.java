package com.box.sdkgen.schemas.templatesigner;

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

public enum TemplateSignerRoleField implements Valuable {
  SIGNER("signer"),
  APPROVER("approver"),
  FINAL_COPY_READER("final_copy_reader");

  private final String value;

  TemplateSignerRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TemplateSignerRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TemplateSignerRoleField>> {

    public TemplateSignerRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TemplateSignerRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TemplateSignerRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TemplateSignerRoleField>(value));
    }
  }

  public static class TemplateSignerRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<TemplateSignerRoleField>> {

    public TemplateSignerRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TemplateSignerRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
