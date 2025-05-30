package com.box.sdkgen.managers.filemetadata;

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

public enum GetFileMetadataByIdScope implements Valuable {
  GLOBAL("global"),
  ENTERPRISE("enterprise");

  private final String value;

  GetFileMetadataByIdScope(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetFileMetadataByIdScopeDeserializer
      extends JsonDeserializer<EnumWrapper<GetFileMetadataByIdScope>> {

    public GetFileMetadataByIdScopeDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetFileMetadataByIdScope> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetFileMetadataByIdScope.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetFileMetadataByIdScope>(value));
    }
  }

  public static class GetFileMetadataByIdScopeSerializer
      extends JsonSerializer<EnumWrapper<GetFileMetadataByIdScope>> {

    public GetFileMetadataByIdScopeSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetFileMetadataByIdScope> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
