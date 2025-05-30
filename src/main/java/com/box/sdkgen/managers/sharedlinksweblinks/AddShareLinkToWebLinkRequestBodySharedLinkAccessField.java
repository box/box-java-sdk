package com.box.sdkgen.managers.sharedlinksweblinks;

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

public enum AddShareLinkToWebLinkRequestBodySharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  AddShareLinkToWebLinkRequestBodySharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AddShareLinkToWebLinkRequestBodySharedLinkAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField>> {

    public AddShareLinkToWebLinkRequestBodySharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AddShareLinkToWebLinkRequestBodySharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField>(value));
    }
  }

  public static class AddShareLinkToWebLinkRequestBodySharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField>> {

    public AddShareLinkToWebLinkRequestBodySharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
