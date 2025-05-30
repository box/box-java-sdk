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

public enum UpdateFolderMetadataByIdScope implements Valuable {
  GLOBAL("global"),
  ENTERPRISE("enterprise");

  private final String value;

  UpdateFolderMetadataByIdScope(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFolderMetadataByIdScopeDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFolderMetadataByIdScope>> {

    public UpdateFolderMetadataByIdScopeDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFolderMetadataByIdScope> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFolderMetadataByIdScope.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFolderMetadataByIdScope>(value));
    }
  }

  public static class UpdateFolderMetadataByIdScopeSerializer
      extends JsonSerializer<EnumWrapper<UpdateFolderMetadataByIdScope>> {

    public UpdateFolderMetadataByIdScopeSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFolderMetadataByIdScope> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
