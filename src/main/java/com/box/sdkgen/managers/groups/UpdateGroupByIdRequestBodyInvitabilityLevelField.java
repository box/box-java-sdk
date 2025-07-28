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

public enum UpdateGroupByIdRequestBodyInvitabilityLevelField implements Valuable {
  ADMINS_ONLY("admins_only"),
  ADMINS_AND_MEMBERS("admins_and_members"),
  ALL_MANAGED_USERS("all_managed_users");

  private final String value;

  UpdateGroupByIdRequestBodyInvitabilityLevelField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateGroupByIdRequestBodyInvitabilityLevelFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateGroupByIdRequestBodyInvitabilityLevelField>> {

    public UpdateGroupByIdRequestBodyInvitabilityLevelFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateGroupByIdRequestBodyInvitabilityLevelField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateGroupByIdRequestBodyInvitabilityLevelField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateGroupByIdRequestBodyInvitabilityLevelField>(value));
    }
  }

  public static class UpdateGroupByIdRequestBodyInvitabilityLevelFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateGroupByIdRequestBodyInvitabilityLevelField>> {

    public UpdateGroupByIdRequestBodyInvitabilityLevelFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateGroupByIdRequestBodyInvitabilityLevelField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
