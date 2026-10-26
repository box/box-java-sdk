package com.box.sdkgen.schemas.v2026r0.shuttlejobpermissionconflictstrategyv2026r0;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum ShuttleJobPermissionConflictStrategyV2026R0 implements Valuable {
  PRESERVE_STRUCTURE_RESTRICT_SHARING("preserve_structure_restrict_sharing"),
  PRESERVE_STRUCTURE_EXPAND_SHARING("preserve_structure_expand_sharing"),
  PRESERVE_SHARING_MODIFY_STRUCTURE("preserve_sharing_modify_structure"),
  WARN_PERMISSIONS_CONFLICTS("warn_permissions_conflicts");

  private final String value;

  ShuttleJobPermissionConflictStrategyV2026R0(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleJobPermissionConflictStrategyV2026R0Deserializer
      extends JsonDeserializer<EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0>> {

    public ShuttleJobPermissionConflictStrategyV2026R0Deserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleJobPermissionConflictStrategyV2026R0.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0>(value));
    }
  }

  public static class ShuttleJobPermissionConflictStrategyV2026R0Serializer
      extends JsonSerializer<EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0>> {

    public ShuttleJobPermissionConflictStrategyV2026R0Serializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleJobPermissionConflictStrategyV2026R0> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
