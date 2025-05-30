package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFileUploadSessionCommitHeaders {

  public final String digest;

  public String ifMatch;

  public String ifNoneMatch;

  public Map<String, String> extraHeaders;

  public CreateFileUploadSessionCommitHeaders(String digest) {
    this.digest = digest;
    this.extraHeaders = mapOf();
  }

  protected CreateFileUploadSessionCommitHeaders(
      CreateFileUploadSessionCommitHeadersBuilder builder) {
    this.digest = builder.digest;
    this.ifMatch = builder.ifMatch;
    this.ifNoneMatch = builder.ifNoneMatch;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getDigest() {
    return digest;
  }

  public String getIfMatch() {
    return ifMatch;
  }

  public String getIfNoneMatch() {
    return ifNoneMatch;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateFileUploadSessionCommitHeadersBuilder {

    protected final String digest;

    protected String ifMatch;

    protected String ifNoneMatch;

    protected Map<String, String> extraHeaders;

    public CreateFileUploadSessionCommitHeadersBuilder(String digest) {
      this.digest = digest;
      this.extraHeaders = mapOf();
    }

    public CreateFileUploadSessionCommitHeadersBuilder ifMatch(String ifMatch) {
      this.ifMatch = ifMatch;
      return this;
    }

    public CreateFileUploadSessionCommitHeadersBuilder ifNoneMatch(String ifNoneMatch) {
      this.ifNoneMatch = ifNoneMatch;
      return this;
    }

    public CreateFileUploadSessionCommitHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateFileUploadSessionCommitHeaders build() {
      return new CreateFileUploadSessionCommitHeaders(this);
    }
  }
}
