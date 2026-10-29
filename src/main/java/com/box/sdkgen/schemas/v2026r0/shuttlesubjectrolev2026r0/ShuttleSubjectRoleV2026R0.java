package com.box.sdkgen.schemas.v2026r0.shuttlesubjectrolev2026r0;

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

public enum ShuttleSubjectRoleV2026R0 implements Valuable {
  OWNER("owner"),
  CREATOR("creator"),
  UPDATER("updater"),
  NAMESPACE_ACCOUNT("namespace_account"),
  SHAREE("sharee"),
  ACCOUNT_HOLDER("account_holder"),
  UNKNOWN("unknown");

  private final String value;

  ShuttleSubjectRoleV2026R0(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleSubjectRoleV2026R0Deserializer
      extends JsonDeserializer<EnumWrapper<ShuttleSubjectRoleV2026R0>> {

    public ShuttleSubjectRoleV2026R0Deserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleSubjectRoleV2026R0> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleSubjectRoleV2026R0.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleSubjectRoleV2026R0>(value));
    }
  }

  public static class ShuttleSubjectRoleV2026R0Serializer
      extends JsonSerializer<EnumWrapper<ShuttleSubjectRoleV2026R0>> {

    public ShuttleSubjectRoleV2026R0Serializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleSubjectRoleV2026R0> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
