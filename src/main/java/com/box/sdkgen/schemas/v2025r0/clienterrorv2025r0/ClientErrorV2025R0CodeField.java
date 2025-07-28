package com.box.sdkgen.schemas.v2025r0.clienterrorv2025r0;

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

public enum ClientErrorV2025R0CodeField implements Valuable {
  CREATED("created"),
  ACCEPTED("accepted"),
  NO_CONTENT("no_content"),
  REDIRECT("redirect"),
  NOT_MODIFIED("not_modified"),
  BAD_REQUEST("bad_request"),
  UNAUTHORIZED("unauthorized"),
  FORBIDDEN("forbidden"),
  NOT_FOUND("not_found"),
  METHOD_NOT_ALLOWED("method_not_allowed"),
  CONFLICT("conflict"),
  PRECONDITION_FAILED("precondition_failed"),
  TOO_MANY_REQUESTS("too_many_requests"),
  INTERNAL_SERVER_ERROR("internal_server_error"),
  UNAVAILABLE("unavailable"),
  ITEM_NAME_INVALID("item_name_invalid"),
  INSUFFICIENT_SCOPE("insufficient_scope");

  private final String value;

  ClientErrorV2025R0CodeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ClientErrorV2025R0CodeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ClientErrorV2025R0CodeField>> {

    public ClientErrorV2025R0CodeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ClientErrorV2025R0CodeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ClientErrorV2025R0CodeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ClientErrorV2025R0CodeField>(value));
    }
  }

  public static class ClientErrorV2025R0CodeFieldSerializer
      extends JsonSerializer<EnumWrapper<ClientErrorV2025R0CodeField>> {

    public ClientErrorV2025R0CodeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ClientErrorV2025R0CodeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
