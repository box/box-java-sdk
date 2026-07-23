package com.box.sdkgen.schemas.v2026r0.queryorderbyv2026r0;

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

public enum QueryOrderByV2026R0DirectionField implements Valuable {
  ASC("asc"),
  DESC("desc");

  private final String value;

  QueryOrderByV2026R0DirectionField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class QueryOrderByV2026R0DirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<QueryOrderByV2026R0DirectionField>> {

    public QueryOrderByV2026R0DirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<QueryOrderByV2026R0DirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(QueryOrderByV2026R0DirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<QueryOrderByV2026R0DirectionField>(value));
    }
  }

  public static class QueryOrderByV2026R0DirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<QueryOrderByV2026R0DirectionField>> {

    public QueryOrderByV2026R0DirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<QueryOrderByV2026R0DirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
