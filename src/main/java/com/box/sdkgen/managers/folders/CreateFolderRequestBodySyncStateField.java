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

public enum CreateFolderRequestBodySyncStateField implements Valuable {
  SYNCED("synced"),
  NOT_SYNCED("not_synced"),
  PARTIALLY_SYNCED("partially_synced");

  private final String value;

  CreateFolderRequestBodySyncStateField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateFolderRequestBodySyncStateFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateFolderRequestBodySyncStateField>> {

    public CreateFolderRequestBodySyncStateFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateFolderRequestBodySyncStateField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateFolderRequestBodySyncStateField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateFolderRequestBodySyncStateField>(value));
    }
  }

  public static class CreateFolderRequestBodySyncStateFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateFolderRequestBodySyncStateField>> {

    public CreateFolderRequestBodySyncStateFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateFolderRequestBodySyncStateField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
