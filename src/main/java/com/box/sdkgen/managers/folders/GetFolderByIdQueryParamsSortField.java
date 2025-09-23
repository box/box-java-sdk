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

public enum GetFolderByIdQueryParamsSortField implements Valuable {
  ID("id"),
  NAME("name"),
  DATE("date"),
  SIZE("size");

  private final String value;

  GetFolderByIdQueryParamsSortField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetFolderByIdQueryParamsSortFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetFolderByIdQueryParamsSortField>> {

    public GetFolderByIdQueryParamsSortFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetFolderByIdQueryParamsSortField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetFolderByIdQueryParamsSortField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetFolderByIdQueryParamsSortField>(value));
    }
  }

  public static class GetFolderByIdQueryParamsSortFieldSerializer
      extends JsonSerializer<EnumWrapper<GetFolderByIdQueryParamsSortField>> {

    public GetFolderByIdQueryParamsSortFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetFolderByIdQueryParamsSortField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
