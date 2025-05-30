package com.box.sdkgen.schemas.folderfull;

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

public enum FolderFullSyncStateField implements Valuable {
  SYNCED("synced"),
  NOT_SYNCED("not_synced"),
  PARTIALLY_SYNCED("partially_synced");

  private final String value;

  FolderFullSyncStateField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderFullSyncStateFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderFullSyncStateField>> {

    public FolderFullSyncStateFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderFullSyncStateField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderFullSyncStateField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderFullSyncStateField>(value));
    }
  }

  public static class FolderFullSyncStateFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderFullSyncStateField>> {

    public FolderFullSyncStateFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderFullSyncStateField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
