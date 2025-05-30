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

public enum GetTrashedItemsQueryParamsDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  GetTrashedItemsQueryParamsDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetTrashedItemsQueryParamsDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetTrashedItemsQueryParamsDirectionField>> {

    public GetTrashedItemsQueryParamsDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetTrashedItemsQueryParamsDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetTrashedItemsQueryParamsDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetTrashedItemsQueryParamsDirectionField>(value));
    }
  }

  public static class GetTrashedItemsQueryParamsDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<GetTrashedItemsQueryParamsDirectionField>> {

    public GetTrashedItemsQueryParamsDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetTrashedItemsQueryParamsDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
