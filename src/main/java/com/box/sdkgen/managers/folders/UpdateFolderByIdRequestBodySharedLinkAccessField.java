package com.box.sdkgen.managers.folders;

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

public enum UpdateFolderByIdRequestBodySharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  UpdateFolderByIdRequestBodySharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFolderByIdRequestBodySharedLinkAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField>> {

    public UpdateFolderByIdRequestBodySharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFolderByIdRequestBodySharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField>(value));
    }
  }

  public static class UpdateFolderByIdRequestBodySharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField>> {

    public UpdateFolderByIdRequestBodySharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
