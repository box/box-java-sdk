package com.box.sdkgen.schemas.classification;

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

public enum ClassificationTemplateField implements Valuable {
  SECURITYCLASSIFICATION_6VMVOCHWUWO("securityClassification-6VMVochwUWo");

  private final String value;

  ClassificationTemplateField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ClassificationTemplateFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ClassificationTemplateField>> {

    public ClassificationTemplateFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ClassificationTemplateField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ClassificationTemplateField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ClassificationTemplateField>(value));
    }
  }

  public static class ClassificationTemplateFieldSerializer
      extends JsonSerializer<EnumWrapper<ClassificationTemplateField>> {

    public ClassificationTemplateFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ClassificationTemplateField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
