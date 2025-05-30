package com.box.sdkgen.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class OneOfFive<T0, T1, T2, T3, T4> extends SerializableObject {
  protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  protected T0 value0;
  protected T1 value1;
  protected T2 value2;
  protected T3 value3;
  protected T4 value4;

  public OneOfFive(T0 value0, T1 value1, T2 value2, T3 value3, T4 value4) {
    this.value0 = value0;
    this.value1 = value1;
    this.value2 = value2;
    this.value3 = value3;
    this.value4 = value4;
  }

  public static class OneOfFiveSerializer extends JsonSerializer<OneOfFive<?, ?, ?, ?, ?>> {

    public OneOfFiveSerializer() {
      super();
    }

    @Override
    public void serialize(
        OneOfFive<?, ?, ?, ?, ?> itemEntryField, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      if (itemEntryField.value0 != null) {
        gen.writeObject(OBJECT_MAPPER.valueToTree(itemEntryField.value0));
      }
      if (itemEntryField.value1 != null) {
        gen.writeObject(OBJECT_MAPPER.valueToTree(itemEntryField.value1));
      }
      if (itemEntryField.value2 != null) {
        gen.writeObject(OBJECT_MAPPER.valueToTree(itemEntryField.value2));
      }
      if (itemEntryField.value3 != null) {
        gen.writeObject(OBJECT_MAPPER.valueToTree(itemEntryField.value3));
      }
      if (itemEntryField.value4 != null) {
        gen.writeObject(OBJECT_MAPPER.valueToTree(itemEntryField.value4));
      }
    }
  }
}
