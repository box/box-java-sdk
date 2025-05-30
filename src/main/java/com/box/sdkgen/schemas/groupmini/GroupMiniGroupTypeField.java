package com.box.sdkgen.schemas.groupmini;

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

public enum GroupMiniGroupTypeField implements Valuable {
  MANAGED_GROUP("managed_group"),
  ALL_USERS_GROUP("all_users_group");

  private final String value;

  GroupMiniGroupTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GroupMiniGroupTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GroupMiniGroupTypeField>> {

    public GroupMiniGroupTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GroupMiniGroupTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GroupMiniGroupTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GroupMiniGroupTypeField>(value));
    }
  }

  public static class GroupMiniGroupTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GroupMiniGroupTypeField>> {

    public GroupMiniGroupTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GroupMiniGroupTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
