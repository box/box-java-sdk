package com.box.sdkgen.schemas.filerequestupdaterequest;

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

public enum FileRequestUpdateRequestStatusField implements Valuable {
  ACTIVE("active"),
  INACTIVE("inactive");

  private final String value;

  FileRequestUpdateRequestStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileRequestUpdateRequestStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileRequestUpdateRequestStatusField>> {

    public FileRequestUpdateRequestStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileRequestUpdateRequestStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileRequestUpdateRequestStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileRequestUpdateRequestStatusField>(value));
    }
  }

  public static class FileRequestUpdateRequestStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<FileRequestUpdateRequestStatusField>> {

    public FileRequestUpdateRequestStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileRequestUpdateRequestStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
