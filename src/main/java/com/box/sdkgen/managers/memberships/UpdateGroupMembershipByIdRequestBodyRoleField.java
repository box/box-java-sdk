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

public enum UpdateGroupMembershipByIdRequestBodyRoleField implements Valuable {
  MEMBER("member"),
  ADMIN("admin");

  private final String value;

  UpdateGroupMembershipByIdRequestBodyRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateGroupMembershipByIdRequestBodyRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField>> {

    public UpdateGroupMembershipByIdRequestBodyRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateGroupMembershipByIdRequestBodyRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField>(value));
    }
  }

  public static class UpdateGroupMembershipByIdRequestBodyRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField>> {

    public UpdateGroupMembershipByIdRequestBodyRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
