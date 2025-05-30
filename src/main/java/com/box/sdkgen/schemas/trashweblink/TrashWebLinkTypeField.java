package com.box.sdkgen.schemas.trashweblink;

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

public enum TrashWebLinkTypeField implements Valuable {
  WEB_LINK("web_link");

  private final String value;

  TrashWebLinkTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashWebLinkTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashWebLinkTypeField>> {

    public TrashWebLinkTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashWebLinkTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashWebLinkTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashWebLinkTypeField>(value));
    }
  }

  public static class TrashWebLinkTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashWebLinkTypeField>> {

    public TrashWebLinkTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashWebLinkTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
