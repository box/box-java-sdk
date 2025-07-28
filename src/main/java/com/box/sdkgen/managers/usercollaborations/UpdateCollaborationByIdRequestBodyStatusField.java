package com.box.sdkgen.managers.usercollaborations;

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

public enum UpdateCollaborationByIdRequestBodyStatusField implements Valuable {
  PENDING("pending"),
  ACCEPTED("accepted"),
  REJECTED("rejected");

  private final String value;

  UpdateCollaborationByIdRequestBodyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateCollaborationByIdRequestBodyStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField>> {

    public UpdateCollaborationByIdRequestBodyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateCollaborationByIdRequestBodyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField>(value));
    }
  }

  public static class UpdateCollaborationByIdRequestBodyStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField>> {

    public UpdateCollaborationByIdRequestBodyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
