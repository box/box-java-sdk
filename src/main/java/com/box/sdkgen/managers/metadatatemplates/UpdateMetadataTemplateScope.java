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

public enum UpdateMetadataTemplateScope implements Valuable {
  GLOBAL("global"),
  ENTERPRISE("enterprise");

  private final String value;

  UpdateMetadataTemplateScope(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateMetadataTemplateScopeDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateMetadataTemplateScope>> {

    public UpdateMetadataTemplateScopeDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateMetadataTemplateScope> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateMetadataTemplateScope.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateMetadataTemplateScope>(value));
    }
  }

  public static class UpdateMetadataTemplateScopeSerializer
      extends JsonSerializer<EnumWrapper<UpdateMetadataTemplateScope>> {

    public UpdateMetadataTemplateScopeSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateMetadataTemplateScope> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
