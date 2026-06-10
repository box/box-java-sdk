package com.box.sdkgen.schemas.aitaxonomyfilereference;

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

public enum AiTaxonomyFileReferenceTypeField implements Valuable {
  FILE("file");

  private final String value;

  AiTaxonomyFileReferenceTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class AiTaxonomyFileReferenceTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiTaxonomyFileReferenceTypeField>> {

    public AiTaxonomyFileReferenceTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiTaxonomyFileReferenceTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiTaxonomyFileReferenceTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiTaxonomyFileReferenceTypeField>(value));
    }
  }

  public static class AiTaxonomyFileReferenceTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiTaxonomyFileReferenceTypeField>> {

    public AiTaxonomyFileReferenceTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiTaxonomyFileReferenceTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
