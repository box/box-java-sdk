package com.box.sdkgen.schemas.folderbase;

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

public enum FolderBaseTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  FolderBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderBaseTypeField>> {

    public FolderBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderBaseTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderBaseTypeField>(value));
    }
  }

  public static class FolderBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderBaseTypeField>> {

    public FolderBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderBaseTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
