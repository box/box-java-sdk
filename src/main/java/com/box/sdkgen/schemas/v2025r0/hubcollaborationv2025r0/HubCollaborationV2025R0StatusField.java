package com.box.sdkgen.schemas.v2025r0.hubcollaborationv2025r0;

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

public enum HubCollaborationV2025R0StatusField implements Valuable {
  ACCEPTED("accepted"),
  PENDING("pending"),
  REJECTED("rejected");

  private final String value;

  HubCollaborationV2025R0StatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class HubCollaborationV2025R0StatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<HubCollaborationV2025R0StatusField>> {

    public HubCollaborationV2025R0StatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<HubCollaborationV2025R0StatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(HubCollaborationV2025R0StatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<HubCollaborationV2025R0StatusField>(value));
    }
  }

  public static class HubCollaborationV2025R0StatusFieldSerializer
      extends JsonSerializer<EnumWrapper<HubCollaborationV2025R0StatusField>> {

    public HubCollaborationV2025R0StatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<HubCollaborationV2025R0StatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
