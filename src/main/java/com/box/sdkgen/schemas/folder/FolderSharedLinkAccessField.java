package com.box.sdkgen.schemas.folder;

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

public enum FolderSharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  FolderSharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderSharedLinkAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderSharedLinkAccessField>> {

    public FolderSharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderSharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderSharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderSharedLinkAccessField>(value));
    }
  }

  public static class FolderSharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderSharedLinkAccessField>> {

    public FolderSharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderSharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
