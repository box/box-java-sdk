package com.box.sdkgen.schemas.uploadpart;

import com.box.sdkgen.schemas.uploadpartmini.UploadPartMini;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** The representation of an upload session chunk. */
@JsonFilter("nullablePropertyFilter")
public class UploadPart extends UploadPartMini {

  /** The SHA1 hash of the chunk. */
  protected String sha1;

  /** The `SHA-512` hash of the chunk, as a hex string of 128 lower case characters. */
  protected String sha512;

  public UploadPart() {
    super();
  }

  protected UploadPart(Builder builder) {
    super(builder);
    this.sha1 = builder.sha1;
    this.sha512 = builder.sha512;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getSha1() {
    return sha1;
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
    UploadPart casted = (UploadPart) o;
    return Objects.equals(partId, casted.partId)
        && Objects.equals(offset, casted.offset)
        && Objects.equals(size, casted.size)
        && Objects.equals(sha1, casted.sha1)
        && Objects.equals(sha512, casted.sha512);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partId, offset, size, sha1, sha512);
  }

  @Override
  public String toString() {
    return "UploadPart{"
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
        + ", "
        + "sha1='"
        + sha1
        + '\''
        + ", "
        + "sha512='"
        + sha512
        + '\''
        + "}";
  }

  public static class Builder extends UploadPartMini.Builder {

    protected String sha1;

    protected String sha512;

    public Builder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    public Builder sha512(String sha512) {
      this.sha512 = sha512;
      return this;
    }

    @Override
    public Builder partId(String partId) {
      this.partId = partId;
      return this;
    }

    @Override
    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    @Override
    public Builder size(Long size) {
      this.size = size;
      return this;
    }

    public UploadPart build() {
      return new UploadPart(this);
    }
  }
}
