package com.box.sdkgen.schemas.signrequestcreatesigner;

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

public enum SignRequestCreateSignerRoleField implements Valuable {
  SIGNER("signer"),
  APPROVER("approver"),
  FINAL_COPY_READER("final_copy_reader");

  private final String value;

  SignRequestCreateSignerRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignRequestCreateSignerRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignRequestCreateSignerRoleField>> {

    public SignRequestCreateSignerRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestCreateSignerRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestCreateSignerRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestCreateSignerRoleField>(value));
    }
  }

  public static class SignRequestCreateSignerRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<SignRequestCreateSignerRoleField>> {

    public SignRequestCreateSignerRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestCreateSignerRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
