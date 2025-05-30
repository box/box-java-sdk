package com.box.sdkgen.schemas.groupfull;

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

public enum GroupFullInvitabilityLevelField implements Valuable {
  ADMINS_ONLY("admins_only"),
  ADMINS_AND_MEMBERS("admins_and_members"),
  ALL_MANAGED_USERS("all_managed_users");

  private final String value;

  GroupFullInvitabilityLevelField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GroupFullInvitabilityLevelFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GroupFullInvitabilityLevelField>> {

    public GroupFullInvitabilityLevelFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GroupFullInvitabilityLevelField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GroupFullInvitabilityLevelField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GroupFullInvitabilityLevelField>(value));
    }
  }

  public static class GroupFullInvitabilityLevelFieldSerializer
      extends JsonSerializer<EnumWrapper<GroupFullInvitabilityLevelField>> {

    public GroupFullInvitabilityLevelFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GroupFullInvitabilityLevelField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
