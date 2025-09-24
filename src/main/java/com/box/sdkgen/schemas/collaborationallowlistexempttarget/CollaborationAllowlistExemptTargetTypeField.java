package com.box.sdkgen.schemas.collaborationallowlistexempttarget;

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

public enum CollaborationAllowlistExemptTargetTypeField implements Valuable {
  COLLABORATION_WHITELIST_EXEMPT_TARGET("collaboration_whitelist_exempt_target");

  private final String value;

  CollaborationAllowlistExemptTargetTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollaborationAllowlistExemptTargetTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollaborationAllowlistExemptTargetTypeField>> {

    public CollaborationAllowlistExemptTargetTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollaborationAllowlistExemptTargetTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollaborationAllowlistExemptTargetTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollaborationAllowlistExemptTargetTypeField>(value));
    }
  }

  public static class CollaborationAllowlistExemptTargetTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CollaborationAllowlistExemptTargetTypeField>> {

    public CollaborationAllowlistExemptTargetTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollaborationAllowlistExemptTargetTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
