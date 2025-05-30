package com.box.sdkgen.managers.metadatacascadepolicies;

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

public enum ApplyMetadataCascadePolicyRequestBodyConflictResolutionField implements Valuable {
  NONE("none"),
  OVERWRITE("overwrite");

  private final String value;

  ApplyMetadataCascadePolicyRequestBodyConflictResolutionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ApplyMetadataCascadePolicyRequestBodyConflictResolutionFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField>> {

    public ApplyMetadataCascadePolicyRequestBodyConflictResolutionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ApplyMetadataCascadePolicyRequestBodyConflictResolutionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField>(value));
    }
  }

  public static class ApplyMetadataCascadePolicyRequestBodyConflictResolutionFieldSerializer
      extends JsonSerializer<
          EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField>> {

    public ApplyMetadataCascadePolicyRequestBodyConflictResolutionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
