package com.box.sdkgen.schemas.weblinkbase;

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

public enum WebLinkBaseTypeField implements Valuable {
  WEB_LINK("web_link");

  private final String value;

  WebLinkBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WebLinkBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WebLinkBaseTypeField>> {

    public WebLinkBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WebLinkBaseTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WebLinkBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WebLinkBaseTypeField>(value));
    }
  }

  public static class WebLinkBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WebLinkBaseTypeField>> {

    public WebLinkBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WebLinkBaseTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
