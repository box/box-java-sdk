package com.box.sdkgen.schemas.enterprisebase;

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

public enum EnterpriseBaseTypeField implements Valuable {
  ENTERPRISE("enterprise");

  private final String value;

  EnterpriseBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class EnterpriseBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<EnterpriseBaseTypeField>> {

    public EnterpriseBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<EnterpriseBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(EnterpriseBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<EnterpriseBaseTypeField>(value));
    }
  }

  public static class EnterpriseBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<EnterpriseBaseTypeField>> {

    public EnterpriseBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<EnterpriseBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
