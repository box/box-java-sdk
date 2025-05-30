package com.box.sdkgen.managers.sharedlinksfiles;

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

public enum AddShareLinkToFileRequestBodySharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  AddShareLinkToFileRequestBodySharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AddShareLinkToFileRequestBodySharedLinkAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField>> {

    public AddShareLinkToFileRequestBodySharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AddShareLinkToFileRequestBodySharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField>(value));
    }
  }

  public static class AddShareLinkToFileRequestBodySharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField>> {

    public AddShareLinkToFileRequestBodySharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
