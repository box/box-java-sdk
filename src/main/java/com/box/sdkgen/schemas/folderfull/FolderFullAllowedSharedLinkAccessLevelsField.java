package com.box.sdkgen.schemas.folderfull;

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

public enum FolderFullAllowedSharedLinkAccessLevelsField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  FolderFullAllowedSharedLinkAccessLevelsField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderFullAllowedSharedLinkAccessLevelsFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>> {

    public FolderFullAllowedSharedLinkAccessLevelsFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderFullAllowedSharedLinkAccessLevelsField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>(value));
    }
  }

  public static class FolderFullAllowedSharedLinkAccessLevelsFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>> {

    public FolderFullAllowedSharedLinkAccessLevelsFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
