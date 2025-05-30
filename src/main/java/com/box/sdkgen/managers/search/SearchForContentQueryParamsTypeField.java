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

public enum SearchForContentQueryParamsTypeField implements Valuable {
  FILE("file"),
  FOLDER("folder"),
  WEB_LINK("web_link");

  private final String value;

  SearchForContentQueryParamsTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SearchForContentQueryParamsTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SearchForContentQueryParamsTypeField>> {

    public SearchForContentQueryParamsTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SearchForContentQueryParamsTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SearchForContentQueryParamsTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SearchForContentQueryParamsTypeField>(value));
    }
  }

  public static class SearchForContentQueryParamsTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SearchForContentQueryParamsTypeField>> {

    public SearchForContentQueryParamsTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SearchForContentQueryParamsTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
