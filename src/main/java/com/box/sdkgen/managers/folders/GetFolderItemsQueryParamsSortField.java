package com.box.sdkgen.managers.folders;

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

public enum GetFolderItemsQueryParamsSortField implements Valuable {
  ID("id"),
  NAME("name"),
  DATE("date"),
  SIZE("size");

  private final String value;

  GetFolderItemsQueryParamsSortField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetFolderItemsQueryParamsSortFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetFolderItemsQueryParamsSortField>> {

    public GetFolderItemsQueryParamsSortFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetFolderItemsQueryParamsSortField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetFolderItemsQueryParamsSortField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetFolderItemsQueryParamsSortField>(value));
    }
  }

  public static class GetFolderItemsQueryParamsSortFieldSerializer
      extends JsonSerializer<EnumWrapper<GetFolderItemsQueryParamsSortField>> {

    public GetFolderItemsQueryParamsSortFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetFolderItemsQueryParamsSortField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
