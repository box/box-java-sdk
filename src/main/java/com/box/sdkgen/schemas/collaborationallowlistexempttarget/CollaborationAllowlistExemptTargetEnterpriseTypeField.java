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

public enum CollaborationAllowlistExemptTargetEnterpriseTypeField implements Valuable {
  ENTERPRISE("enterprise");

  private final String value;

  CollaborationAllowlistExemptTargetEnterpriseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollaborationAllowlistExemptTargetEnterpriseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField>> {

    public CollaborationAllowlistExemptTargetEnterpriseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollaborationAllowlistExemptTargetEnterpriseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField>(value));
    }
  }

  public static class CollaborationAllowlistExemptTargetEnterpriseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField>> {

    public CollaborationAllowlistExemptTargetEnterpriseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
