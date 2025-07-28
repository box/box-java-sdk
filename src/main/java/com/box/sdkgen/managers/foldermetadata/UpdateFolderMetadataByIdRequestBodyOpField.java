package com.box.sdkgen.managers.foldermetadata;

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

public enum UpdateFolderMetadataByIdRequestBodyOpField implements Valuable {
  ADD("add"),
  REPLACE("replace"),
  REMOVE("remove"),
  TEST("test"),
  MOVE("move"),
  COPY("copy");

  private final String value;

  UpdateFolderMetadataByIdRequestBodyOpField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFolderMetadataByIdRequestBodyOpFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField>> {

    public UpdateFolderMetadataByIdRequestBodyOpFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFolderMetadataByIdRequestBodyOpField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField>(value));
    }
  }

  public static class UpdateFolderMetadataByIdRequestBodyOpFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField>> {

    public UpdateFolderMetadataByIdRequestBodyOpFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFolderMetadataByIdRequestBodyOpField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
