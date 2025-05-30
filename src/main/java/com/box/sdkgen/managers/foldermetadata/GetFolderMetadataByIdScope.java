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

public enum GetFolderMetadataByIdScope implements Valuable {
  GLOBAL("global"),
  ENTERPRISE("enterprise");

  private final String value;

  GetFolderMetadataByIdScope(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetFolderMetadataByIdScopeDeserializer
      extends JsonDeserializer<EnumWrapper<GetFolderMetadataByIdScope>> {

    public GetFolderMetadataByIdScopeDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetFolderMetadataByIdScope> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetFolderMetadataByIdScope.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetFolderMetadataByIdScope>(value));
    }
  }

  public static class GetFolderMetadataByIdScopeSerializer
      extends JsonSerializer<EnumWrapper<GetFolderMetadataByIdScope>> {

    public GetFolderMetadataByIdScopeSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetFolderMetadataByIdScope> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
