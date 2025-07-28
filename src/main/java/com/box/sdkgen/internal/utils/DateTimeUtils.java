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
import java.util.Date;

public class DateTimeUtils {

  public static class DateTimeSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      String formattedDate = dateTimeToString(value);
      gen.writeString(formattedDate);
    }
  }

  public static class DateTimeDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
      String dateString = p.getValueAsString();
      Date d = dateTimeFromString(dateString);
      if (d == null) {
        throw new IOException("Invalid date time format: " + dateString);
      }
      return d;
    }
  }
}
