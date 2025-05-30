package com.box.sdkgen.managers.files;

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

public enum UpdateFileByIdRequestBodyLockAccessField implements Valuable {
  LOCK("lock");

  private final String value;

  UpdateFileByIdRequestBodyLockAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFileByIdRequestBodyLockAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFileByIdRequestBodyLockAccessField>> {

    public UpdateFileByIdRequestBodyLockAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFileByIdRequestBodyLockAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFileByIdRequestBodyLockAccessField>(value));
    }
  }

  public static class UpdateFileByIdRequestBodyLockAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateFileByIdRequestBodyLockAccessField>> {

    public UpdateFileByIdRequestBodyLockAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
