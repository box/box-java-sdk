package com.box.sdkgen.schemas.uploadpartmini;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UploadPartMini extends SerializableObject {

  @JsonProperty("part_id")
  protected String partId;

  protected Long offset;

  protected Long size;

  public UploadPartMini() {
    super();
  }

  protected UploadPartMini(UploadPartMiniBuilder builder) {
    super();
    this.partId = builder.partId;
    this.offset = builder.offset;
    this.size = builder.size;
  }

  public String getPartId() {
    return partId;
  }

  public Long getOffset() {
    return offset;
  }

  public Long getSize() {
    return size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadPartMini casted = (UploadPartMini) o;
    return Objects.equals(partId, casted.partId)
        && Objects.equals(offset, casted.offset)
        && Objects.equals(size, casted.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partId, offset, size);
  }

  @Override
  public String toString() {
    return "UploadPartMini{"
        + "partId='"
        + partId
        + '\''
        + ", "
        + "offset='"
        + offset
        + '\''
        + ", "
        + "size='"
        + size
        + '\''
        + "}";
  }

  public static class UploadPartMiniBuilder {

    protected String partId;

    protected Long offset;

    protected Long size;

    public UploadPartMiniBuilder partId(String partId) {
      this.partId = partId;
      return this;
    }

    public UploadPartMiniBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public UploadPartMiniBuilder size(Long size) {
      this.size = size;
      return this;
    }

    public UploadPartMini build() {
      return new UploadPartMini(this);
    }
  }
}
