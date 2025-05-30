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

public enum SearchForContentQueryParamsDirectionField implements Valuable {
  DESC("DESC"),
  ASC("ASC");

  private final String value;

  SearchForContentQueryParamsDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SearchForContentQueryParamsDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SearchForContentQueryParamsDirectionField>> {

    public SearchForContentQueryParamsDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SearchForContentQueryParamsDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SearchForContentQueryParamsDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SearchForContentQueryParamsDirectionField>(value));
    }
  }

  public static class SearchForContentQueryParamsDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<SearchForContentQueryParamsDirectionField>> {

    public SearchForContentQueryParamsDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SearchForContentQueryParamsDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
