package com.box.sdkgen.schemas.groupmemberships;

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

public enum GroupMembershipsOrderDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  GroupMembershipsOrderDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GroupMembershipsOrderDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GroupMembershipsOrderDirectionField>> {

    public GroupMembershipsOrderDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GroupMembershipsOrderDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GroupMembershipsOrderDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GroupMembershipsOrderDirectionField>(value));
    }
  }

  public static class GroupMembershipsOrderDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<GroupMembershipsOrderDirectionField>> {

    public GroupMembershipsOrderDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GroupMembershipsOrderDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
