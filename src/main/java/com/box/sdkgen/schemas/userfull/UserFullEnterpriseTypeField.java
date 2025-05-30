package com.box.sdkgen.schemas.userfull;

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

public enum UserFullEnterpriseTypeField implements Valuable {
  ENTERPRISE("enterprise");

  private final String value;

  UserFullEnterpriseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UserFullEnterpriseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UserFullEnterpriseTypeField>> {

    public UserFullEnterpriseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UserFullEnterpriseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UserFullEnterpriseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UserFullEnterpriseTypeField>(value));
    }
  }

  public static class UserFullEnterpriseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<UserFullEnterpriseTypeField>> {

    public UserFullEnterpriseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UserFullEnterpriseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
