package com.box.sdkgen.managers.folderwatermarks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFolderWatermarkRequestBody extends SerializableObject {

  /** The watermark to imprint on the folder. */
  protected final UpdateFolderWatermarkRequestBodyWatermarkField watermark;

  public UpdateFolderWatermarkRequestBody(
      @JsonProperty("watermark") UpdateFolderWatermarkRequestBodyWatermarkField watermark) {
    super();
    this.watermark = watermark;
  }

  public UpdateFolderWatermarkRequestBodyWatermarkField getWatermark() {
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
    UpdateFolderWatermarkRequestBody casted = (UpdateFolderWatermarkRequestBody) o;
    return Objects.equals(watermark, casted.watermark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(watermark);
  }

  @Override
  public String toString() {
    return "UpdateFolderWatermarkRequestBody{" + "watermark='" + watermark + '\'' + "}";
  }
}
