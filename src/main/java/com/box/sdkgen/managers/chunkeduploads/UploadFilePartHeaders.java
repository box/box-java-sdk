package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UploadFilePartHeaders {

  /**
   * The [RFC3230][1] message digest of the chunk uploaded.
   *
   * <p>Only SHA1 is supported. The SHA1 digest must be base64 encoded. The format of this header is
   * as `sha=BASE64_ENCODED_DIGEST`.
   *
   * <p>To get the value for the `SHA` digest, use the openSSL command to encode the file part:
   * `openssl sha1 -binary &lt;FILE_PART_NAME&gt; | base64`.
   *
   * <p>[1]: https://tools.ietf.org/html/rfc3230
   */
  public final String digest;

  /**
   * The byte range of the chunk.
   *
   * <p>Must not overlap with the range of a part already uploaded this session. Each part’s size
   * must be exactly equal in size to the part size specified in the upload session that you
   * created. One exception is the last part of the file, as this can be smaller.
   *
   * <p>When providing the value for `content-range`, remember that:
   *
   * <p>* The lower bound of each part's byte range must be a multiple of the part size. * The
   * higher bound must be a multiple of the part size - 1.
   */
  public final String contentRange;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public UploadFilePartHeaders(String digest, String contentRange) {
    this.digest = digest;
    this.contentRange = contentRange;
    this.extraHeaders = mapOf();
  }

  protected UploadFilePartHeaders(Builder builder) {
    this.digest = builder.digest;
    this.contentRange = builder.contentRange;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getDigest() {
    return digest;
  }

  public String getContentRange() {
    return contentRange;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected final String digest;

    protected final String contentRange;

    protected Map<String, String> extraHeaders;

    public Builder(String digest, String contentRange) {
      this.digest = digest;
      this.contentRange = contentRange;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UploadFilePartHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new UploadFilePartHeaders(this);
    }
  }
}
