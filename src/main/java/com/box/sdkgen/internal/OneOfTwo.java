package com.box.sdkgen.internal;

import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class OneOfTwo<T0, T1> extends SerializableObject {
  protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  protected T0 value0;
  protected T1 value1;

  public OneOfTwo(T0 value0, T1 value1) {
    this.value0 = value0;
    this.value1 = value1;
  }

  public static class OneOfTwoSerializer extends JsonSerializer<OneOfTwo<?, ?>> {

    public OneOfTwoSerializer() {
      super();
    }

    @Override
    public void serialize(
        OneOfTwo<?, ?> itemEntryField, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      if (itemEntryField.value0 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value0);
      }
      if (itemEntryField.value1 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value1);
      }
    }
  }
}
