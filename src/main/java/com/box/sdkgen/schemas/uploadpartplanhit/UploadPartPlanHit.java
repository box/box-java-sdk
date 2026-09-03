package com.box.sdkgen.schemas.uploadpartplanhit;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** Represents a planned upload part that already exists on the server (cache hit). */
@JsonFilter("nullablePropertyFilter")
public class UploadPartPlanHit extends SerializableObject {

  /**
   * The offset of the chunk within the file in bytes. The lower bound of the position of the chunk
   * within the file.
   */
  protected final long offset;

  /** The size of the chunk in bytes. */
  protected final long size;

  /** The `SHA-512` hash of the chunk. */
  protected final String sha512;

  /** The unique ID of the chunk. */
  @JsonProperty("part_id")
  protected final String partId;

  public UploadPartPlanHit(
      @JsonProperty("offset") long offset,
      @JsonProperty("size") long size,
      @JsonProperty("sha512") String sha512,
      @JsonProperty("part_id") String partId) {
    super();
    this.offset = offset;
    this.size = size;
    this.sha512 = sha512;
    this.partId = partId;
  }

  public long getOffset() {
    return offset;
  }

  public long getSize() {
    return size;
  }

  public String getSha512() {
    return sha512;
  }

  public String getPartId() {
    return partId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadPartPlanHit casted = (UploadPartPlanHit) o;
    return Objects.equals(offset, casted.offset)
        && Objects.equals(size, casted.size)
        && Objects.equals(sha512, casted.sha512)
        && Objects.equals(partId, casted.partId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset, size, sha512, partId);
  }

  @Override
  public String toString() {
    return "UploadPartPlanHit{"
        + "offset='"
        + offset
        + '\''
        + ", "
        + "size='"
        + size
        + '\''
        + ", "
        + "sha512='"
        + sha512
        + '\''
        + ", "
        + "partId='"
        + partId
        + '\''
        + "}";
  }
}
