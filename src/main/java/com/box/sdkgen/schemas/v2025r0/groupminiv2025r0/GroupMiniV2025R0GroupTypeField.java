package com.box.sdkgen.schemas.v2025r0.groupminiv2025r0;

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

public enum GroupMiniV2025R0GroupTypeField implements Valuable {
  MANAGED_GROUP("managed_group"),
  ALL_USERS_GROUP("all_users_group");

  private final String value;

  GroupMiniV2025R0GroupTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GroupMiniV2025R0GroupTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GroupMiniV2025R0GroupTypeField>> {

    public GroupMiniV2025R0GroupTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GroupMiniV2025R0GroupTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GroupMiniV2025R0GroupTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GroupMiniV2025R0GroupTypeField>(value));
    }
  }

  public static class GroupMiniV2025R0GroupTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GroupMiniV2025R0GroupTypeField>> {

    public GroupMiniV2025R0GroupTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GroupMiniV2025R0GroupTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
