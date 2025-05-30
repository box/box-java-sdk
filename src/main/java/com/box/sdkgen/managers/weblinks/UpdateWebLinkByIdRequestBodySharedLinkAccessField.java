package com.box.sdkgen.managers.weblinks;

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

public enum UpdateWebLinkByIdRequestBodySharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  UpdateWebLinkByIdRequestBodySharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateWebLinkByIdRequestBodySharedLinkAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField>> {

    public UpdateWebLinkByIdRequestBodySharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateWebLinkByIdRequestBodySharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField>(value));
    }
  }

  public static class UpdateWebLinkByIdRequestBodySharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField>> {

    public UpdateWebLinkByIdRequestBodySharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
