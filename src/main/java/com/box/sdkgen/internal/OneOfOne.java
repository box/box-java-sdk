package com.box.sdkgen.internal;

import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class OneOfOne<T0> extends SerializableObject implements OneOfUnion {
  protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  protected T0 value0;

  public OneOfOne(T0 value0) {
    this.value0 = value0;
  }

  @Override
  public boolean hasAnyNonNullValue() {
    return value0 != null;
  }

  public static class OneOfOneSerializer extends JsonSerializer<OneOfOne<?>> {

    public OneOfOneSerializer() {
      super();
    }

    @Override
    public void serialize(
        OneOfOne<?> itemEntryField, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      if (itemEntryField.value0 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value0);
        return;
      }
      gen.writeNull();
    }
  }
}
