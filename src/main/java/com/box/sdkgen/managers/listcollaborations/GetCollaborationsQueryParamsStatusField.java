package com.box.sdkgen.managers.listcollaborations;

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

public enum GetCollaborationsQueryParamsStatusField implements Valuable {
  PENDING("pending");

  private final String value;

  GetCollaborationsQueryParamsStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetCollaborationsQueryParamsStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetCollaborationsQueryParamsStatusField>> {

    public GetCollaborationsQueryParamsStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetCollaborationsQueryParamsStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetCollaborationsQueryParamsStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetCollaborationsQueryParamsStatusField>(value));
    }
  }

  public static class GetCollaborationsQueryParamsStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<GetCollaborationsQueryParamsStatusField>> {

    public GetCollaborationsQueryParamsStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetCollaborationsQueryParamsStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
