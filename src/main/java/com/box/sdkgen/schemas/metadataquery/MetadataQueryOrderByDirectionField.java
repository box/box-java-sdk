package com.box.sdkgen.schemas.metadataquery;

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

public enum MetadataQueryOrderByDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  MetadataQueryOrderByDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class MetadataQueryOrderByDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<MetadataQueryOrderByDirectionField>> {

    public MetadataQueryOrderByDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<MetadataQueryOrderByDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(MetadataQueryOrderByDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<MetadataQueryOrderByDirectionField>(value));
    }
  }

  public static class MetadataQueryOrderByDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<MetadataQueryOrderByDirectionField>> {

    public MetadataQueryOrderByDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<MetadataQueryOrderByDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
