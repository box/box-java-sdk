package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFileUploadSessionCommitByUrlHeaders {

  public final String digest;

  public String ifMatch;

  public String ifNoneMatch;

  public Map<String, String> extraHeaders;

  public CreateFileUploadSessionCommitByUrlHeaders(String digest) {
    this.digest = digest;
    this.extraHeaders = mapOf();
  }

  protected CreateFileUploadSessionCommitByUrlHeaders(Builder builder) {
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

  public static class Builder {

    protected final String digest;

    protected String ifMatch;

    protected String ifNoneMatch;

    protected Map<String, String> extraHeaders;

    public Builder(String digest) {
      this.digest = digest;
      this.extraHeaders = mapOf();
    }

    public Builder ifMatch(String ifMatch) {
      this.ifMatch = ifMatch;
      return this;
    }

    public Builder ifNoneMatch(String ifNoneMatch) {
      this.ifNoneMatch = ifNoneMatch;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateFileUploadSessionCommitByUrlHeaders build() {
      return new CreateFileUploadSessionCommitByUrlHeaders(this);
    }
  }
}
