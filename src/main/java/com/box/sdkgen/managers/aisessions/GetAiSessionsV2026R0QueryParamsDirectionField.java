package com.box.sdkgen.managers.aisessions;

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

public enum GetAiSessionsV2026R0QueryParamsDirectionField implements Valuable {
  ASC("asc"),
  DESC("desc");

  private final String value;

  GetAiSessionsV2026R0QueryParamsDirectionField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class GetAiSessionsV2026R0QueryParamsDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField>> {

    public GetAiSessionsV2026R0QueryParamsDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetAiSessionsV2026R0QueryParamsDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField>(value));
    }
  }

  public static class GetAiSessionsV2026R0QueryParamsDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField>> {

    public GetAiSessionsV2026R0QueryParamsDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
