package com.box.sdkgen.managers.folderlocks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderLocksHeaders {

  public Map<String, String> extraHeaders;

  public GetFolderLocksHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderLocksHeaders(Builder builder) {
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

    public GetFolderLocksHeaders build() {
      return new GetFolderLocksHeaders(this);
    }
  }
}
