package com.box.sdkgen.internal.utils;

import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeFromString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeToString;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.OffsetDateTime;

public class DateTimeUtils {

  public static class DateTimeSerializer extends JsonSerializer<OffsetDateTime> {
    @Override
    public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      String formattedDate = dateTimeToString(value);
      gen.writeString(formattedDate);
    }
  }

  public static class DateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
    @Override
    public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
      String dateString = p.getValueAsString();
      OffsetDateTime d = dateTimeFromString(dateString);
      if (d == null) {
        throw new IOException("Invalid date time format: " + dateString);
      }
      return d;
    }
  }
}
