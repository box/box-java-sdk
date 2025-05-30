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

public enum UpdateClassificationOnFolderRequestBodyOpField implements Valuable {
  REPLACE("replace");

  private final String value;

  UpdateClassificationOnFolderRequestBodyOpField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateClassificationOnFolderRequestBodyOpFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField>> {

    public UpdateClassificationOnFolderRequestBodyOpFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateClassificationOnFolderRequestBodyOpField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField>(value));
    }
  }

  public static class UpdateClassificationOnFolderRequestBodyOpFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField>> {

    public UpdateClassificationOnFolderRequestBodyOpFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateClassificationOnFolderRequestBodyOpField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
