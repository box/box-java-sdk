package com.box.sdkgen.schemas.uploadsessionplanrequest;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.uploadpartplan.UploadPartPlan;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * Request body for planning an upload session. This allows checking which parts already exist on
 * the server before uploading.
 */
@JsonFilter("nullablePropertyFilter")
public class UploadSessionPlanRequest extends SerializableObject {

  /** The list of parts to check for existence. */
  protected final List<UploadPartPlan> parts;

  public UploadSessionPlanRequest(@JsonProperty("parts") List<UploadPartPlan> parts) {
    super();
    this.parts = parts;
  }

  public List<UploadPartPlan> getParts() {
    return parts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadSessionPlanRequest casted = (UploadSessionPlanRequest) o;
    return Objects.equals(parts, casted.parts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parts);
  }

  @Override
  public String toString() {
    return "UploadSessionPlanRequest{" + "parts='" + parts + '\'' + "}";
  }
}
