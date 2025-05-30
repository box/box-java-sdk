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

public enum SearchForContentQueryParamsScopeField implements Valuable {
  USER_CONTENT("user_content"),
  ENTERPRISE_CONTENT("enterprise_content");

  private final String value;

  SearchForContentQueryParamsScopeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SearchForContentQueryParamsScopeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SearchForContentQueryParamsScopeField>> {

    public SearchForContentQueryParamsScopeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SearchForContentQueryParamsScopeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SearchForContentQueryParamsScopeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SearchForContentQueryParamsScopeField>(value));
    }
  }

  public static class SearchForContentQueryParamsScopeFieldSerializer
      extends JsonSerializer<EnumWrapper<SearchForContentQueryParamsScopeField>> {

    public SearchForContentQueryParamsScopeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SearchForContentQueryParamsScopeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
