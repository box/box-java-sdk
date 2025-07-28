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

public enum FolderSharedLinkEffectiveAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  FolderSharedLinkEffectiveAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderSharedLinkEffectiveAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderSharedLinkEffectiveAccessField>> {

    public FolderSharedLinkEffectiveAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderSharedLinkEffectiveAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderSharedLinkEffectiveAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderSharedLinkEffectiveAccessField>(value));
    }
  }

  public static class FolderSharedLinkEffectiveAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderSharedLinkEffectiveAccessField>> {

    public FolderSharedLinkEffectiveAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderSharedLinkEffectiveAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
