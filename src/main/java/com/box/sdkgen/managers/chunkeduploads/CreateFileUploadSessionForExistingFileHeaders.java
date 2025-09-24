package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFileUploadSessionForExistingFileHeaders {

  public Map<String, String> extraHeaders;

  public CreateFileUploadSessionForExistingFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateFileUploadSessionForExistingFileHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateFileUploadSessionForExistingFileHeaders build() {
      return new CreateFileUploadSessionForExistingFileHeaders(this);
    }
  }
}
