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

public enum WebLinkSharedLinkEffectivePermissionField implements Valuable {
  CAN_EDIT("can_edit"),
  CAN_DOWNLOAD("can_download"),
  CAN_PREVIEW("can_preview"),
  NO_ACCESS("no_access");

  private final String value;

  WebLinkSharedLinkEffectivePermissionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WebLinkSharedLinkEffectivePermissionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WebLinkSharedLinkEffectivePermissionField>> {

    public WebLinkSharedLinkEffectivePermissionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WebLinkSharedLinkEffectivePermissionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WebLinkSharedLinkEffectivePermissionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WebLinkSharedLinkEffectivePermissionField>(value));
    }
  }

  public static class WebLinkSharedLinkEffectivePermissionFieldSerializer
      extends JsonSerializer<EnumWrapper<WebLinkSharedLinkEffectivePermissionField>> {

    public WebLinkSharedLinkEffectivePermissionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WebLinkSharedLinkEffectivePermissionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
