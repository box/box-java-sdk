package com.box.sdkgen.schemas.aitaxonomyreference;

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

public enum AiTaxonomyReferenceTypeField implements Valuable {
  TAXONOMY("taxonomy");

  private final String value;

  AiTaxonomyReferenceTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class AiTaxonomyReferenceTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiTaxonomyReferenceTypeField>> {

    public AiTaxonomyReferenceTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiTaxonomyReferenceTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiTaxonomyReferenceTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiTaxonomyReferenceTypeField>(value));
    }
  }

  public static class AiTaxonomyReferenceTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiTaxonomyReferenceTypeField>> {

    public AiTaxonomyReferenceTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiTaxonomyReferenceTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
