package com.box.sdkgen.managers.collaborationallowlistentries;

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

public enum CreateCollaborationWhitelistEntryRequestBodyDirectionField implements Valuable {
  INBOUND("inbound"),
  OUTBOUND("outbound"),
  BOTH("both");

  private final String value;

  CreateCollaborationWhitelistEntryRequestBodyDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateCollaborationWhitelistEntryRequestBodyDirectionFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField>> {

    public CreateCollaborationWhitelistEntryRequestBodyDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateCollaborationWhitelistEntryRequestBodyDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField>(value));
    }
  }

  public static class CreateCollaborationWhitelistEntryRequestBodyDirectionFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField>> {

    public CreateCollaborationWhitelistEntryRequestBodyDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
