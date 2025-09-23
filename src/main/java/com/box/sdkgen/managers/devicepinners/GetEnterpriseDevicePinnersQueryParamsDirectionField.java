package com.box.sdkgen.managers.devicepinners;

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

public enum GetEnterpriseDevicePinnersQueryParamsDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  GetEnterpriseDevicePinnersQueryParamsDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetEnterpriseDevicePinnersQueryParamsDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField>> {

    public GetEnterpriseDevicePinnersQueryParamsDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetEnterpriseDevicePinnersQueryParamsDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField>(value));
    }
  }

  public static class GetEnterpriseDevicePinnersQueryParamsDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField>> {

    public GetEnterpriseDevicePinnersQueryParamsDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
