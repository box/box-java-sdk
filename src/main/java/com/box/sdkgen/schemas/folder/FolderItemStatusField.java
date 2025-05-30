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

public enum FolderItemStatusField implements Valuable {
  ACTIVE("active"),
  TRASHED("trashed"),
  DELETED("deleted");

  private final String value;

  FolderItemStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderItemStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderItemStatusField>> {

    public FolderItemStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderItemStatusField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderItemStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderItemStatusField>(value));
    }
  }

  public static class FolderItemStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderItemStatusField>> {

    public FolderItemStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderItemStatusField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
