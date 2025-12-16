package com.box.sdkgen.internal;

import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class OneOfNine<T0, T1, T2, T3, T4, T5, T6, T7, T8> extends SerializableObject {
  protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  protected T0 value0;
  protected T1 value1;
  protected T2 value2;
  protected T3 value3;
  protected T4 value4;
  protected T5 value5;
  protected T6 value6;
  protected T7 value7;
  protected T8 value8;

  public OneOfNine(
      T0 value0,
      T1 value1,
      T2 value2,
      T3 value3,
      T4 value4,
      T5 value5,
      T6 value6,
      T7 value7,
      T8 value8) {
    this.value0 = value0;
    this.value1 = value1;
    this.value2 = value2;
    this.value3 = value3;
    this.value4 = value4;
    this.value5 = value5;
    this.value6 = value6;
    this.value7 = value7;
    this.value8 = value8;
  }

  public static class OneOfNineSerializer
      extends JsonSerializer<OneOfNine<?, ?, ?, ?, ?, ?, ?, ?, ?>> {

    public OneOfNineSerializer() {
      super();
    }

    @Override
    public void serialize(
        OneOfNine<?, ?, ?, ?, ?, ?, ?, ?, ?> itemEntryField,
        JsonGenerator gen,
        SerializerProvider provider)
        throws IOException {
      if (itemEntryField.value0 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value0);
      }
      if (itemEntryField.value1 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value1);
      }
      if (itemEntryField.value2 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value2);
      }
      if (itemEntryField.value3 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value3);
      }
      if (itemEntryField.value4 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value4);
      }
      if (itemEntryField.value5 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value5);
      }
      if (itemEntryField.value6 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value6);
      }
      if (itemEntryField.value7 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value7);
      }
      if (itemEntryField.value8 != null) {
        JsonManager.WRITER.writeValue(gen, itemEntryField.value8);
      }
    }
  }
}
