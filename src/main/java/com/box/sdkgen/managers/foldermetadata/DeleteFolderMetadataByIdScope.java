package com.box.sdkgen.managers.foldermetadata;

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

public enum DeleteFolderMetadataByIdScope implements Valuable {
  GLOBAL("global"),
  ENTERPRISE("enterprise");

  private final String value;

  DeleteFolderMetadataByIdScope(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class DeleteFolderMetadataByIdScopeDeserializer
      extends JsonDeserializer<EnumWrapper<DeleteFolderMetadataByIdScope>> {

    public DeleteFolderMetadataByIdScopeDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DeleteFolderMetadataByIdScope> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DeleteFolderMetadataByIdScope.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<DeleteFolderMetadataByIdScope>(value));
    }
  }

  public static class DeleteFolderMetadataByIdScopeSerializer
      extends JsonSerializer<EnumWrapper<DeleteFolderMetadataByIdScope>> {

    public DeleteFolderMetadataByIdScopeSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DeleteFolderMetadataByIdScope> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
