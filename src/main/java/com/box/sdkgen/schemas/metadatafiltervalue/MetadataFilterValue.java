package com.box.sdkgen.schemas.metadatafiltervalue;

import com.box.sdkgen.internal.OneOfFive;
import com.box.sdkgen.schemas.metadatafieldfilterdaterange.MetadataFieldFilterDateRange;
import com.box.sdkgen.schemas.metadatafieldfilterfloatrange.MetadataFieldFilterFloatRange;
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

@JsonDeserialize(using = MetadataFilterValue.MetadataFilterValueDeserializer.class)
@JsonSerialize(using = OneOfFive.OneOfFiveSerializer.class)
public class MetadataFilterValue
    extends OneOfFive<
        String, Double, List<String>, MetadataFieldFilterFloatRange, MetadataFieldFilterDateRange> {

  public MetadataFilterValue(String string) {
    super(string, null, null, null, null);
  }

  public MetadataFilterValue(Double doubleNumber) {
    super(null, doubleNumber, null, null, null);
  }

  public MetadataFilterValue(List<String> listOfString) {
    super(null, null, listOfString, null, null);
  }

  public MetadataFilterValue(MetadataFieldFilterFloatRange metadataFieldFilterFloatRange) {
    super(null, null, null, metadataFieldFilterFloatRange, null);
  }

  public MetadataFilterValue(MetadataFieldFilterDateRange metadataFieldFilterDateRange) {
    super(null, null, null, null, metadataFieldFilterDateRange);
  }

  public boolean isString() {
    return value0 != null;
  }

  public String getString() {
    return value0;
  }

  public boolean isDoubleNumber() {
    return value1 != null;
  }

  public Double getDoubleNumber() {
    return value1;
  }

  public boolean isListOfString() {
    return value2 != null;
  }

  public List<String> getListOfString() {
    return value2;
  }

  public boolean isMetadataFieldFilterFloatRange() {
    return value3 != null;
  }

  public MetadataFieldFilterFloatRange getMetadataFieldFilterFloatRange() {
    return value3;
  }

  public boolean isMetadataFieldFilterDateRange() {
    return value4 != null;
  }

  public MetadataFieldFilterDateRange getMetadataFieldFilterDateRange() {
    return value4;
  }

  static class MetadataFilterValueDeserializer extends JsonDeserializer<MetadataFilterValue> {

    public MetadataFilterValueDeserializer() {
      super();
    }

    @Override
    public MetadataFilterValue deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      try {
        return new MetadataFilterValue(
            OneOfFive.OBJECT_MAPPER.convertValue(node, MetadataFieldFilterFloatRange.class));
      } catch (Exception ignored) {
      }
      try {
        return new MetadataFilterValue(
            OneOfFive.OBJECT_MAPPER.convertValue(node, MetadataFieldFilterDateRange.class));
      } catch (Exception ignored) {
      }
      try {
        return new MetadataFilterValue(OneOfFive.OBJECT_MAPPER.convertValue(node, Double.class));
      } catch (Exception ignored) {
      }
      try {
        return new MetadataFilterValue(OneOfFive.OBJECT_MAPPER.convertValue(node, List.class));
      } catch (Exception ignored) {
      }
      try {
        return new MetadataFilterValue(OneOfFive.OBJECT_MAPPER.convertValue(node, String.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(jp, "Unable to deserialize MetadataFilterValue");
    }
  }
}
