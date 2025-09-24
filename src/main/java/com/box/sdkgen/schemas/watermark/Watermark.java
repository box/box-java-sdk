package com.box.sdkgen.schemas.watermark;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class Watermark extends SerializableObject {

  protected WatermarkWatermarkField watermark;

  public Watermark() {
    super();
  }

  protected Watermark(Builder builder) {
    super();
    this.watermark = builder.watermark;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public WatermarkWatermarkField getWatermark() {
    return watermark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Watermark casted = (Watermark) o;
    return Objects.equals(watermark, casted.watermark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(watermark);
  }

  @Override
  public String toString() {
    return "Watermark{" + "watermark='" + watermark + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected WatermarkWatermarkField watermark;

    public Builder watermark(WatermarkWatermarkField watermark) {
      this.watermark = watermark;
      return this;
    }

    public Watermark build() {
      return new Watermark(this);
    }
  }
}
