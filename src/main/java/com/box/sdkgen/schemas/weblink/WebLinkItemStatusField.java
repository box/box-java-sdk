package com.box.sdkgen.schemas.weblink;

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

public enum WebLinkItemStatusField implements Valuable {
  ACTIVE("active"),
  TRASHED("trashed"),
  DELETED("deleted");

  private final String value;

  WebLinkItemStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WebLinkItemStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WebLinkItemStatusField>> {

    public WebLinkItemStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WebLinkItemStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WebLinkItemStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WebLinkItemStatusField>(value));
    }
  }

  public static class WebLinkItemStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<WebLinkItemStatusField>> {

    public WebLinkItemStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WebLinkItemStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
