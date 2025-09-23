package com.box.sdkgen.managers.groups;

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

public enum CreateGroupRequestBodyMemberViewabilityLevelField implements Valuable {
  ADMINS_ONLY("admins_only"),
  ADMINS_AND_MEMBERS("admins_and_members"),
  ALL_MANAGED_USERS("all_managed_users");

  private final String value;

  CreateGroupRequestBodyMemberViewabilityLevelField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateGroupRequestBodyMemberViewabilityLevelFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField>> {

    public CreateGroupRequestBodyMemberViewabilityLevelFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateGroupRequestBodyMemberViewabilityLevelField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField>(value));
    }
  }

  public static class CreateGroupRequestBodyMemberViewabilityLevelFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField>> {

    public CreateGroupRequestBodyMemberViewabilityLevelFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
