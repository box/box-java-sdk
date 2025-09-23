package com.box.sdkgen.managers.hubs;

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

public enum GetHubsV2025R0QueryParamsDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  GetHubsV2025R0QueryParamsDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetHubsV2025R0QueryParamsDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetHubsV2025R0QueryParamsDirectionField>> {

    public GetHubsV2025R0QueryParamsDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetHubsV2025R0QueryParamsDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetHubsV2025R0QueryParamsDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetHubsV2025R0QueryParamsDirectionField>(value));
    }
  }

  public static class GetHubsV2025R0QueryParamsDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<GetHubsV2025R0QueryParamsDirectionField>> {

    public GetHubsV2025R0QueryParamsDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetHubsV2025R0QueryParamsDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
