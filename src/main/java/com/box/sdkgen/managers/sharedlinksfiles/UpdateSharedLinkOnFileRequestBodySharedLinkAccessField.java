package com.box.sdkgen.managers.sharedlinksfiles;

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

public enum UpdateSharedLinkOnFileRequestBodySharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  UpdateSharedLinkOnFileRequestBodySharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateSharedLinkOnFileRequestBodySharedLinkAccessFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField>> {

    public UpdateSharedLinkOnFileRequestBodySharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateSharedLinkOnFileRequestBodySharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField>(value));
    }
  }

  public static class UpdateSharedLinkOnFileRequestBodySharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField>> {

    public UpdateSharedLinkOnFileRequestBodySharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
