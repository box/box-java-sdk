package com.box.sdkgen.managers.folderclassifications;

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

public enum UpdateClassificationOnFolderRequestBodyPathField implements Valuable {
  _BOX__SECURITY__CLASSIFICATION__KEY("/Box__Security__Classification__Key");

  private final String value;

  UpdateClassificationOnFolderRequestBodyPathField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateClassificationOnFolderRequestBodyPathFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField>> {

    public UpdateClassificationOnFolderRequestBodyPathFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateClassificationOnFolderRequestBodyPathField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField>(value));
    }
  }

  public static class UpdateClassificationOnFolderRequestBodyPathFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField>> {

    public UpdateClassificationOnFolderRequestBodyPathFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateClassificationOnFolderRequestBodyPathField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
