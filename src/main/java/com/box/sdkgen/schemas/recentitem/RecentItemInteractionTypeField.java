package com.box.sdkgen.schemas.recentitem;

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

public enum RecentItemInteractionTypeField implements Valuable {
  ITEM_PREVIEW("item_preview"),
  ITEM_UPLOAD("item_upload"),
  ITEM_COMMENT("item_comment"),
  ITEM_OPEN("item_open"),
  ITEM_MODIFY("item_modify");

  private final String value;

  RecentItemInteractionTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class RecentItemInteractionTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<RecentItemInteractionTypeField>> {

    public RecentItemInteractionTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RecentItemInteractionTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RecentItemInteractionTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RecentItemInteractionTypeField>(value));
    }
  }

  public static class RecentItemInteractionTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<RecentItemInteractionTypeField>> {

    public RecentItemInteractionTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RecentItemInteractionTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
