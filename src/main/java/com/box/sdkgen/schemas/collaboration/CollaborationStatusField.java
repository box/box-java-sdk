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

public enum CollaborationStatusField implements Valuable {
  ACCEPTED("accepted"),
  PENDING("pending"),
  REJECTED("rejected");

  private final String value;

  CollaborationStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollaborationStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollaborationStatusField>> {

    public CollaborationStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollaborationStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollaborationStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollaborationStatusField>(value));
    }
  }

  public static class CollaborationStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<CollaborationStatusField>> {

    public CollaborationStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollaborationStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
