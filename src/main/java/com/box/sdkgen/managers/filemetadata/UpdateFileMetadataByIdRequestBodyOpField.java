package com.box.sdkgen.managers.filemetadata;

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

public enum UpdateFileMetadataByIdRequestBodyOpField implements Valuable {
  ADD("add"),
  REPLACE("replace"),
  REMOVE("remove"),
  TEST("test"),
  MOVE("move"),
  COPY("copy");

  private final String value;

  UpdateFileMetadataByIdRequestBodyOpField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFileMetadataByIdRequestBodyOpFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField>> {

    public UpdateFileMetadataByIdRequestBodyOpFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFileMetadataByIdRequestBodyOpField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField>(value));
    }
  }

  public static class UpdateFileMetadataByIdRequestBodyOpFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField>> {

    public UpdateFileMetadataByIdRequestBodyOpFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFileMetadataByIdRequestBodyOpField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
