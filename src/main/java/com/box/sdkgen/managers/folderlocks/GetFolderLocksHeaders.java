package com.box.sdkgen.managers.folderlocks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderLocksHeaders {

  public Map<String, String> extraHeaders;

  public GetFolderLocksHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderLocksHeaders(GetFolderLocksHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFolderLocksHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFolderLocksHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFolderLocksHeaders build() {
      return new GetFolderLocksHeaders(this);
    }
  }
}
