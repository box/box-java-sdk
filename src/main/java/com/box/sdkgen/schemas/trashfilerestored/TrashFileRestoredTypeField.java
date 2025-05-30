package com.box.sdkgen.schemas.trashfilerestored;

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

public enum TrashFileRestoredTypeField implements Valuable {
  FILE("file");

  private final String value;

  TrashFileRestoredTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashFileRestoredTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashFileRestoredTypeField>> {

    public TrashFileRestoredTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashFileRestoredTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashFileRestoredTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashFileRestoredTypeField>(value));
    }
  }

  public static class TrashFileRestoredTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashFileRestoredTypeField>> {

    public TrashFileRestoredTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashFileRestoredTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
