package com.box.sdkgen.schemas.collaborationallowlistentry;

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

public enum CollaborationAllowlistEntryTypeField implements Valuable {
  COLLABORATION_WHITELIST_ENTRY("collaboration_whitelist_entry");

  private final String value;

  CollaborationAllowlistEntryTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollaborationAllowlistEntryTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollaborationAllowlistEntryTypeField>> {

    public CollaborationAllowlistEntryTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollaborationAllowlistEntryTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollaborationAllowlistEntryTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollaborationAllowlistEntryTypeField>(value));
    }
  }

  public static class CollaborationAllowlistEntryTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CollaborationAllowlistEntryTypeField>> {

    public CollaborationAllowlistEntryTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollaborationAllowlistEntryTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
