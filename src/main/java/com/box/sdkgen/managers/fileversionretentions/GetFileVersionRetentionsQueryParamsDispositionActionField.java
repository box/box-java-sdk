package com.box.sdkgen.managers.fileversionretentions;

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

public enum GetFileVersionRetentionsQueryParamsDispositionActionField implements Valuable {
  PERMANENTLY_DELETE("permanently_delete"),
  REMOVE_RETENTION("remove_retention");

  private final String value;

  GetFileVersionRetentionsQueryParamsDispositionActionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetFileVersionRetentionsQueryParamsDispositionActionFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField>> {

    public GetFileVersionRetentionsQueryParamsDispositionActionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetFileVersionRetentionsQueryParamsDispositionActionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField>(value));
    }
  }

  public static class GetFileVersionRetentionsQueryParamsDispositionActionFieldSerializer
      extends JsonSerializer<
          EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField>> {

    public GetFileVersionRetentionsQueryParamsDispositionActionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
