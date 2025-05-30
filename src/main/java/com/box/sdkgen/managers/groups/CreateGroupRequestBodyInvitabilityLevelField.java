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

public enum CreateGroupRequestBodyInvitabilityLevelField implements Valuable {
  ADMINS_ONLY("admins_only"),
  ADMINS_AND_MEMBERS("admins_and_members"),
  ALL_MANAGED_USERS("all_managed_users");

  private final String value;

  CreateGroupRequestBodyInvitabilityLevelField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateGroupRequestBodyInvitabilityLevelFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField>> {

    public CreateGroupRequestBodyInvitabilityLevelFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateGroupRequestBodyInvitabilityLevelField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField>(value));
    }
  }

  public static class CreateGroupRequestBodyInvitabilityLevelFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField>> {

    public CreateGroupRequestBodyInvitabilityLevelFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
