package com.box.sdkgen.managers.search;

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

public enum SearchForContentQueryParamsContentTypesField implements Valuable {
  NAME("name"),
  DESCRIPTION("description"),
  FILE_CONTENT("file_content"),
  COMMENTS("comments"),
  TAG("tag");

  private final String value;

  SearchForContentQueryParamsContentTypesField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SearchForContentQueryParamsContentTypesFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SearchForContentQueryParamsContentTypesField>> {

    public SearchForContentQueryParamsContentTypesFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SearchForContentQueryParamsContentTypesField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SearchForContentQueryParamsContentTypesField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SearchForContentQueryParamsContentTypesField>(value));
    }
  }

  public static class SearchForContentQueryParamsContentTypesFieldSerializer
      extends JsonSerializer<EnumWrapper<SearchForContentQueryParamsContentTypesField>> {

    public SearchForContentQueryParamsContentTypesFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SearchForContentQueryParamsContentTypesField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
