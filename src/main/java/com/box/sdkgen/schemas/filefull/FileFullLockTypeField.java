package com.box.sdkgen.schemas.filefull;

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

public enum FileFullLockTypeField implements Valuable {
  LOCK("lock");

  private final String value;

  FileFullLockTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileFullLockTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileFullLockTypeField>> {

    public FileFullLockTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileFullLockTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileFullLockTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileFullLockTypeField>(value));
    }
  }

  public static class FileFullLockTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<FileFullLockTypeField>> {

    public FileFullLockTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileFullLockTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
