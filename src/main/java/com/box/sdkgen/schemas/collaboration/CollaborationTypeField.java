package com.box.sdkgen.schemas.collaboration;

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

public enum CollaborationTypeField implements Valuable {
  COLLABORATION("collaboration");

  private final String value;

  CollaborationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollaborationTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollaborationTypeField>> {

    public CollaborationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollaborationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollaborationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollaborationTypeField>(value));
    }
  }

  public static class CollaborationTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CollaborationTypeField>> {

    public CollaborationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollaborationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
