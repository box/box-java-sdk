package com.box.sdkgen.schemas.watermark;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class WatermarkWatermarkField extends SerializableObject {

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  public WatermarkWatermarkField() {
    super();
  }

  protected WatermarkWatermarkField(WatermarkWatermarkFieldBuilder builder) {
    super();
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WatermarkWatermarkField casted = (WatermarkWatermarkField) o;
    return Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdAt, modifiedAt);
  }

  @Override
  public String toString() {
    return "WatermarkWatermarkField{"
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + "}";
  }

  public static class WatermarkWatermarkFieldBuilder {

    protected String createdAt;

    protected String modifiedAt;

    public WatermarkWatermarkFieldBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public WatermarkWatermarkFieldBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public WatermarkWatermarkField build() {
      return new WatermarkWatermarkField(this);
    }
  }
}
