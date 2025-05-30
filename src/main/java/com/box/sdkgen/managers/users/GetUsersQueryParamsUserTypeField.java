package com.box.sdkgen.managers.users;

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

public enum GetUsersQueryParamsUserTypeField implements Valuable {
  ALL("all"),
  MANAGED("managed"),
  EXTERNAL("external");

  private final String value;

  GetUsersQueryParamsUserTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetUsersQueryParamsUserTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetUsersQueryParamsUserTypeField>> {

    public GetUsersQueryParamsUserTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetUsersQueryParamsUserTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetUsersQueryParamsUserTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetUsersQueryParamsUserTypeField>(value));
    }
  }

  public static class GetUsersQueryParamsUserTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GetUsersQueryParamsUserTypeField>> {

    public GetUsersQueryParamsUserTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetUsersQueryParamsUserTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
