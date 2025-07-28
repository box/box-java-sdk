package com.box.sdkgen.managers.sharedlinksweblinks;

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

public enum UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField>> {

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField>(value));
    }
  }

  public static class UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessFieldSerializer
      extends JsonSerializer<
          EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField>> {

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
