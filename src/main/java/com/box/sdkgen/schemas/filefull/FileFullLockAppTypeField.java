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

public enum FileFullLockAppTypeField implements Valuable {
  GSUITE("gsuite"),
  OFFICE_WOPI("office_wopi"),
  OFFICE_WOPIPLUS("office_wopiplus"),
  OTHER("other");

  private final String value;

  FileFullLockAppTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileFullLockAppTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileFullLockAppTypeField>> {

    public FileFullLockAppTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileFullLockAppTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileFullLockAppTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileFullLockAppTypeField>(value));
    }
  }

  public static class FileFullLockAppTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<FileFullLockAppTypeField>> {

    public FileFullLockAppTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileFullLockAppTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
