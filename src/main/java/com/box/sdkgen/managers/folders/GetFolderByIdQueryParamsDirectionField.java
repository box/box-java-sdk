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

public enum GetFolderByIdQueryParamsDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  GetFolderByIdQueryParamsDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetFolderByIdQueryParamsDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetFolderByIdQueryParamsDirectionField>> {

    public GetFolderByIdQueryParamsDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetFolderByIdQueryParamsDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetFolderByIdQueryParamsDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetFolderByIdQueryParamsDirectionField>(value));
    }
  }

  public static class GetFolderByIdQueryParamsDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<GetFolderByIdQueryParamsDirectionField>> {

    public GetFolderByIdQueryParamsDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetFolderByIdQueryParamsDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
