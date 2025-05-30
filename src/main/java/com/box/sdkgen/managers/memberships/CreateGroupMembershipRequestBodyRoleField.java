package com.box.sdkgen.managers.memberships;

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

public enum CreateGroupMembershipRequestBodyRoleField implements Valuable {
  MEMBER("member"),
  ADMIN("admin");

  private final String value;

  CreateGroupMembershipRequestBodyRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateGroupMembershipRequestBodyRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateGroupMembershipRequestBodyRoleField>> {

    public CreateGroupMembershipRequestBodyRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateGroupMembershipRequestBodyRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateGroupMembershipRequestBodyRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateGroupMembershipRequestBodyRoleField>(value));
    }
  }

  public static class CreateGroupMembershipRequestBodyRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateGroupMembershipRequestBodyRoleField>> {

    public CreateGroupMembershipRequestBodyRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateGroupMembershipRequestBodyRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
