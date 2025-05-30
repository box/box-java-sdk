package com.box.sdkgen.managers.trasheditems;

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

public enum GetTrashedItemsQueryParamsSortField implements Valuable {
  NAME("name"),
  DATE("date"),
  SIZE("size");

  private final String value;

  GetTrashedItemsQueryParamsSortField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetTrashedItemsQueryParamsSortFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetTrashedItemsQueryParamsSortField>> {

    public GetTrashedItemsQueryParamsSortFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetTrashedItemsQueryParamsSortField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetTrashedItemsQueryParamsSortField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetTrashedItemsQueryParamsSortField>(value));
    }
  }

  public static class GetTrashedItemsQueryParamsSortFieldSerializer
      extends JsonSerializer<EnumWrapper<GetTrashedItemsQueryParamsSortField>> {

    public GetTrashedItemsQueryParamsSortFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetTrashedItemsQueryParamsSortField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
