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

public enum FolderFolderUploadEmailAccessField implements Valuable {
  OPEN("open"),
  COLLABORATORS("collaborators");

  private final String value;

  FolderFolderUploadEmailAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderFolderUploadEmailAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderFolderUploadEmailAccessField>> {

    public FolderFolderUploadEmailAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderFolderUploadEmailAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderFolderUploadEmailAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderFolderUploadEmailAccessField>(value));
    }
  }

  public static class FolderFolderUploadEmailAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderFolderUploadEmailAccessField>> {

    public FolderFolderUploadEmailAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderFolderUploadEmailAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
