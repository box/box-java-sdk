package com.box.sdkgen.schemas.fileorfolderscope;

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

public enum FileOrFolderScopeScopeField implements Valuable {
  ANNOTATION_EDIT("annotation_edit"),
  ANNOTATION_VIEW_ALL("annotation_view_all"),
  ANNOTATION_VIEW_SELF("annotation_view_self"),
  BASE_EXPLORER("base_explorer"),
  BASE_PICKER("base_picker"),
  BASE_PREVIEW("base_preview"),
  BASE_UPLOAD("base_upload"),
  ITEM_DELETE("item_delete"),
  ITEM_DOWNLOAD("item_download"),
  ITEM_PREVIEW("item_preview"),
  ITEM_RENAME("item_rename"),
  ITEM_SHARE("item_share"),
  ITEM_UPLOAD("item_upload"),
  ITEM_READ("item_read");

  private final String value;

  FileOrFolderScopeScopeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileOrFolderScopeScopeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileOrFolderScopeScopeField>> {

    public FileOrFolderScopeScopeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileOrFolderScopeScopeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileOrFolderScopeScopeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileOrFolderScopeScopeField>(value));
    }
  }

  public static class FileOrFolderScopeScopeFieldSerializer
      extends JsonSerializer<EnumWrapper<FileOrFolderScopeScopeField>> {

    public FileOrFolderScopeScopeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileOrFolderScopeScopeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
