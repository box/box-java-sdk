package com.box.sdkgen.schemas.metadatainstancevalue;

import com.box.sdkgen.internal.OneOfFour;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.List;

@JsonDeserialize(using = MetadataInstanceValue.MetadataInstanceValueDeserializer.class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class MetadataInstanceValue extends OneOfFour<String, Long, Double, List<String>> {

  public MetadataInstanceValue(String string) {
    super(string, null, null, null);
  }

  public MetadataInstanceValue(Long longNumber) {
    super(null, longNumber, null, null);
  }

  public MetadataInstanceValue(Double doubleNumber) {
    super(null, null, doubleNumber, null);
  }

  public MetadataInstanceValue(List<String> listOfString) {
    super(null, null, null, listOfString);
  }

  public boolean isString() {
    return value0 != null;
  }

  public String getString() {
    return value0;
  }

  public boolean isLongNumber() {
    return value1 != null;
  }

  public Long getLongNumber() {
    return value1;
  }

  public boolean isDoubleNumber() {
    return value2 != null;
  }

  public Double getDoubleNumber() {
    return value2;
  }

  public boolean isListOfString() {
    return value3 != null;
  }

  public List<String> getListOfString() {
    return value3;
  }

  static class MetadataInstanceValueDeserializer extends JsonDeserializer<MetadataInstanceValue> {

    public MetadataInstanceValueDeserializer() {
      super();
    }

    @Override
    public MetadataInstanceValue deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      try {
        return new MetadataInstanceValue(OneOfFour.OBJECT_MAPPER.convertValue(node, Long.class));
      } catch (Exception ignored) {
      }
      try {
        return new MetadataInstanceValue(OneOfFour.OBJECT_MAPPER.convertValue(node, Double.class));
      } catch (Exception ignored) {
      }
      try {
        return new MetadataInstanceValue(OneOfFour.OBJECT_MAPPER.convertValue(node, List.class));
      } catch (Exception ignored) {
      }
      try {
        return new MetadataInstanceValue(OneOfFour.OBJECT_MAPPER.convertValue(node, String.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(jp, "Unable to deserialize MetadataInstanceValue");
    }
  }
}
