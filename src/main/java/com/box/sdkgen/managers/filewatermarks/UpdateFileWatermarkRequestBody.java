package com.box.sdkgen.managers.filewatermarks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFileWatermarkRequestBody extends SerializableObject {

  /** The watermark to imprint on the file. */
  protected final UpdateFileWatermarkRequestBodyWatermarkField watermark;

  public UpdateFileWatermarkRequestBody(
      @JsonProperty("watermark") UpdateFileWatermarkRequestBodyWatermarkField watermark) {
    super();
    this.watermark = watermark;
  }

  public UpdateFileWatermarkRequestBodyWatermarkField getWatermark() {
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
    UpdateFileWatermarkRequestBody casted = (UpdateFileWatermarkRequestBody) o;
    return Objects.equals(watermark, casted.watermark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(watermark);
  }

  @Override
  public String toString() {
    return "UpdateFileWatermarkRequestBody{" + "watermark='" + watermark + '\'' + "}";
  }
}
