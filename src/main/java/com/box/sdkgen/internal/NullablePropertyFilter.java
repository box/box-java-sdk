package com.box.sdkgen.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class NullablePropertyFilter extends SimpleBeanPropertyFilter {
  @Override
  public void serializeAsField(
      Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer)
      throws Exception {

    Object value = writer.getMember().getValue(pojo);
    boolean isNullable = writer.getMember().hasAnnotation(Nullable.class);

    if (isNullable) {
      // Always serialize if the field is explicitly set to null
      if (value != null) {
        writer.serializeAsField(pojo, jgen, provider);
        return;
      }
      // If the field is explicitly set to null, serialize it
      if (pojo instanceof NullableFieldTracker
          && ((NullableFieldTracker) pojo).isFieldExplicitlySet(writer.getName())) {
        writer.serializeAsField(pojo, jgen, provider);
        return;
      }
      // If the field is not explicitly set and is null, skip serialization
      return;
    }

    // For non-nullable fields, serialize only if the value is not null
    if (value != null) {
      writer.serializeAsField(pojo, jgen, provider);
    }
  }
}
