package com.box.sdkgen.schemas.groupbase;

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

public enum GroupBaseTypeField implements Valuable {
  GROUP("group");

  private final String value;

  GroupBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GroupBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GroupBaseTypeField>> {

    public GroupBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GroupBaseTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GroupBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GroupBaseTypeField>(value));
    }
  }

  public static class GroupBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GroupBaseTypeField>> {

    public GroupBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GroupBaseTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
