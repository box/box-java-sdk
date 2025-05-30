package com.box.sdkgen.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class OneOfThree<T0, T1, T2> extends SerializableObject {
  protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  protected T0 value0;
  protected T1 value1;
  protected T2 value2;

  public OneOfThree(T0 value0, T1 value1, T2 value2) {
    this.value0 = value0;
    this.value1 = value1;
    this.value2 = value2;
  }

  public static class OneOfThreeSerializer extends JsonSerializer<OneOfThree<?, ?, ?>> {

    public OneOfThreeSerializer() {
      super();
    }

    @Override
    public void serialize(
        OneOfThree<?, ?, ?> itemEntryField, JsonGenerator gen, SerializerProvider provider)
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
    }
  }
}
