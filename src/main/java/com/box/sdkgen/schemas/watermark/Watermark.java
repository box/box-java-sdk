package com.box.sdkgen.schemas.watermark;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class Watermark extends SerializableObject {

  protected WatermarkWatermarkField watermark;

  public Watermark() {
    super();
  }

  protected Watermark(WatermarkBuilder builder) {
    super();
    this.watermark = builder.watermark;
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

  public static class WatermarkBuilder {

    protected WatermarkWatermarkField watermark;

    public WatermarkBuilder watermark(WatermarkWatermarkField watermark) {
      this.watermark = watermark;
      return this;
    }

    public Watermark build() {
      return new Watermark(this);
    }
  }
}
