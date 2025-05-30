package com.box.sdkgen.schemas.folderreference;

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

public enum FolderReferenceTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  FolderReferenceTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderReferenceTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderReferenceTypeField>> {

    public FolderReferenceTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderReferenceTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderReferenceTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderReferenceTypeField>(value));
    }
  }

  public static class FolderReferenceTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderReferenceTypeField>> {

    public FolderReferenceTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderReferenceTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
