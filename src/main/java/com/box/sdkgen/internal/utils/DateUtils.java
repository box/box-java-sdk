package com.box.sdkgen.internal.utils;

import static com.box.sdkgen.internal.utils.UtilsManager.dateFromString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateToString;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.OffsetDateTime;

public class DateUtils {
  public static class DateSerializer extends JsonSerializer<OffsetDateTime> {
    @Override
    public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      String formattedDate = dateToString(value);
      gen.writeString(formattedDate);
    }
  }

  public static class DateDeserializer extends JsonDeserializer<OffsetDateTime> {
    @Override
    public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
      String dateString = p.getValueAsString();
      OffsetDateTime d = dateFromString(dateString);
      if (d == null) {
        throw new IOException("Invalid date format: " + dateString);
      }
      return d;
    }
  }
}
