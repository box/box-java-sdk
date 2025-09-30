package com.box.sdkgen.schemas.classificationtemplate;

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

public enum ClassificationTemplateFieldsKeyField implements Valuable {
  BOX__SECURITY__CLASSIFICATION__KEY("Box__Security__Classification__Key");

  private final String value;

  ClassificationTemplateFieldsKeyField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ClassificationTemplateFieldsKeyFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ClassificationTemplateFieldsKeyField>> {

    public ClassificationTemplateFieldsKeyFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ClassificationTemplateFieldsKeyField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ClassificationTemplateFieldsKeyField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ClassificationTemplateFieldsKeyField>(value));
    }
  }

  public static class ClassificationTemplateFieldsKeyFieldSerializer
      extends JsonSerializer<EnumWrapper<ClassificationTemplateFieldsKeyField>> {

    public ClassificationTemplateFieldsKeyFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ClassificationTemplateFieldsKeyField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
