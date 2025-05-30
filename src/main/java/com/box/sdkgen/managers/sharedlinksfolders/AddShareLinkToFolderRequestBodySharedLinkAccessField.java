package com.box.sdkgen.managers.sharedlinksfolders;

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

public enum AddShareLinkToFolderRequestBodySharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  AddShareLinkToFolderRequestBodySharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AddShareLinkToFolderRequestBodySharedLinkAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField>> {

    public AddShareLinkToFolderRequestBodySharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AddShareLinkToFolderRequestBodySharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField>(value));
    }
  }

  public static class AddShareLinkToFolderRequestBodySharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField>> {

    public AddShareLinkToFolderRequestBodySharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
