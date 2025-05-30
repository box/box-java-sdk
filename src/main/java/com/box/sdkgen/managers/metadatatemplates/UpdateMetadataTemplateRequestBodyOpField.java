package com.box.sdkgen.managers.metadatatemplates;

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

public enum UpdateMetadataTemplateRequestBodyOpField implements Valuable {
  EDITTEMPLATE("editTemplate"),
  ADDFIELD("addField"),
  REORDERFIELDS("reorderFields"),
  ADDENUMOPTION("addEnumOption"),
  REORDERENUMOPTIONS("reorderEnumOptions"),
  REORDERMULTISELECTOPTIONS("reorderMultiSelectOptions"),
  ADDMULTISELECTOPTION("addMultiSelectOption"),
  EDITFIELD("editField"),
  REMOVEFIELD("removeField"),
  EDITENUMOPTION("editEnumOption"),
  REMOVEENUMOPTION("removeEnumOption"),
  EDITMULTISELECTOPTION("editMultiSelectOption"),
  REMOVEMULTISELECTOPTION("removeMultiSelectOption");

  private final String value;

  UpdateMetadataTemplateRequestBodyOpField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateMetadataTemplateRequestBodyOpFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateMetadataTemplateRequestBodyOpField>> {

    public UpdateMetadataTemplateRequestBodyOpFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateMetadataTemplateRequestBodyOpField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateMetadataTemplateRequestBodyOpField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateMetadataTemplateRequestBodyOpField>(value));
    }
  }

  public static class UpdateMetadataTemplateRequestBodyOpFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateMetadataTemplateRequestBodyOpField>> {

    public UpdateMetadataTemplateRequestBodyOpFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateMetadataTemplateRequestBodyOpField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
