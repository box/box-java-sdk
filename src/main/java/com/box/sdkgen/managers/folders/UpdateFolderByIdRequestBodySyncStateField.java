package com.box.sdkgen.managers.folders;

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

public enum UpdateFolderByIdRequestBodySyncStateField implements Valuable {
  SYNCED("synced"),
  NOT_SYNCED("not_synced"),
  PARTIALLY_SYNCED("partially_synced");

  private final String value;

  UpdateFolderByIdRequestBodySyncStateField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFolderByIdRequestBodySyncStateFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFolderByIdRequestBodySyncStateField>> {

    public UpdateFolderByIdRequestBodySyncStateFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFolderByIdRequestBodySyncStateField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFolderByIdRequestBodySyncStateField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFolderByIdRequestBodySyncStateField>(value));
    }
  }

  public static class UpdateFolderByIdRequestBodySyncStateFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateFolderByIdRequestBodySyncStateField>> {

    public UpdateFolderByIdRequestBodySyncStateFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFolderByIdRequestBodySyncStateField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
