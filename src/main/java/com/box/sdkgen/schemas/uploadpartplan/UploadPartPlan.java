package com.box.sdkgen.schemas.uploadpartplan;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** Represents a planned upload part with `SHA-512` hash for upload session planning. */
@JsonFilter("nullablePropertyFilter")
public class UploadPartPlan extends SerializableObject {

  /**
   * The offset of the chunk within the file in bytes. The lower bound of the position of the chunk
   * within the file.
   */
  protected final long offset;

  /** The size of the chunk in bytes. */
  protected final long size;

  /** The `SHA-512` hash of the chunk. */
  protected final String sha512;

  public UploadPartPlan(
      @JsonProperty("offset") long offset,
      @JsonProperty("size") long size,
      @JsonProperty("sha512") String sha512) {
    super();
    this.offset = offset;
    this.size = size;
    this.sha512 = sha512;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadPartPlan casted = (UploadPartPlan) o;
    return Objects.equals(offset, casted.offset)
        && Objects.equals(size, casted.size)
        && Objects.equals(sha512, casted.sha512);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset, size, sha512);
  }

  @Override
  public String toString() {
    return "UploadPartPlan{"
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
        + "}";
  }
}
