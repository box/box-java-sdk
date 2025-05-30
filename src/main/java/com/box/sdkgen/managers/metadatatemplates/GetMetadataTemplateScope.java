package com.box.sdkgen.managers.metadatatemplates;

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

public enum GetMetadataTemplateScope implements Valuable {
  GLOBAL("global"),
  ENTERPRISE("enterprise");

  private final String value;

  GetMetadataTemplateScope(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetMetadataTemplateScopeDeserializer
      extends JsonDeserializer<EnumWrapper<GetMetadataTemplateScope>> {

    public GetMetadataTemplateScopeDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetMetadataTemplateScope> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetMetadataTemplateScope.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetMetadataTemplateScope>(value));
    }
  }

  public static class GetMetadataTemplateScopeSerializer
      extends JsonSerializer<EnumWrapper<GetMetadataTemplateScope>> {

    public GetMetadataTemplateScopeSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetMetadataTemplateScope> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
