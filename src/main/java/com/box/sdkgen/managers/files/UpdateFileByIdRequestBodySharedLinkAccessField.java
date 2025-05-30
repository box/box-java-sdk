package com.box.sdkgen.managers.files;

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

public enum UpdateFileByIdRequestBodySharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  UpdateFileByIdRequestBodySharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFileByIdRequestBodySharedLinkAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField>> {

    public UpdateFileByIdRequestBodySharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFileByIdRequestBodySharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField>(value));
    }
  }

  public static class UpdateFileByIdRequestBodySharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField>> {

    public UpdateFileByIdRequestBodySharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
