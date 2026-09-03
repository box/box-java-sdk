package com.box.sdkgen.schemas.uploadsessionplanresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.uploadpartplan.UploadPartPlan;
import com.box.sdkgen.schemas.uploadpartplanhit.UploadPartPlanHit;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * Response from planning an upload session. Contains information about which parts already exist
 * (hits) and which need to be uploaded (misses).
 */
@JsonFilter("nullablePropertyFilter")
public class UploadSessionPlanResponse extends SerializableObject {

  /** The unique identifier for this upload session. */
  @JsonProperty("upload_session_id")
  protected final String uploadSessionId;

  /** Parts that already exist on the server and do not need to be uploaded again. */
  protected final List<UploadPartPlanHit> hits;

  /** Parts that do not exist on the server and need to be uploaded. */
  protected final List<UploadPartPlan> misses;

  public UploadSessionPlanResponse(
      @JsonProperty("upload_session_id") String uploadSessionId,
      @JsonProperty("hits") List<UploadPartPlanHit> hits,
      @JsonProperty("misses") List<UploadPartPlan> misses) {
    super();
    this.uploadSessionId = uploadSessionId;
    this.hits = hits;
    this.misses = misses;
  }

  public String getUploadSessionId() {
    return uploadSessionId;
  }

  public List<UploadPartPlanHit> getHits() {
    return hits;
  }

  public List<UploadPartPlan> getMisses() {
    return misses;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadSessionPlanResponse casted = (UploadSessionPlanResponse) o;
    return Objects.equals(uploadSessionId, casted.uploadSessionId)
        && Objects.equals(hits, casted.hits)
        && Objects.equals(misses, casted.misses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uploadSessionId, hits, misses);
  }

  @Override
  public String toString() {
    return "UploadSessionPlanResponse{"
        + "uploadSessionId='"
        + uploadSessionId
        + '\''
        + ", "
        + "hits='"
        + hits
        + '\''
        + ", "
        + "misses='"
        + misses
        + '\''
        + "}";
  }
}
