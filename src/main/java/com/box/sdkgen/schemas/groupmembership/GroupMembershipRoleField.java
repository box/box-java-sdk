package com.box.sdkgen.schemas.groupmembership;

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

public enum GroupMembershipRoleField implements Valuable {
  MEMBER("member"),
  ADMIN("admin");

  private final String value;

  GroupMembershipRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GroupMembershipRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GroupMembershipRoleField>> {

    public GroupMembershipRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GroupMembershipRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GroupMembershipRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GroupMembershipRoleField>(value));
    }
  }

  public static class GroupMembershipRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<GroupMembershipRoleField>> {

    public GroupMembershipRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GroupMembershipRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
