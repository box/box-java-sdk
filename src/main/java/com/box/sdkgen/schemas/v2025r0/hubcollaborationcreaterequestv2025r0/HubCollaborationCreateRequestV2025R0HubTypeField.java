package com.box.sdkgen.schemas.v2025r0.hubcollaborationcreaterequestv2025r0;

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

public enum HubCollaborationCreateRequestV2025R0HubTypeField implements Valuable {
  HUBS("hubs");

  private final String value;

  HubCollaborationCreateRequestV2025R0HubTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class HubCollaborationCreateRequestV2025R0HubTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField>> {

    public HubCollaborationCreateRequestV2025R0HubTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(HubCollaborationCreateRequestV2025R0HubTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField>(value));
    }
  }

  public static class HubCollaborationCreateRequestV2025R0HubTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField>> {

    public HubCollaborationCreateRequestV2025R0HubTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
