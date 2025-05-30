package com.box.sdkgen.schemas.appitemassociation;

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

public enum AppItemAssociationTypeField implements Valuable {
  APP_ITEM_ASSOCIATION("app_item_association");

  private final String value;

  AppItemAssociationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AppItemAssociationTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AppItemAssociationTypeField>> {

    public AppItemAssociationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AppItemAssociationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AppItemAssociationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AppItemAssociationTypeField>(value));
    }
  }

  public static class AppItemAssociationTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AppItemAssociationTypeField>> {

    public AppItemAssociationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AppItemAssociationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
