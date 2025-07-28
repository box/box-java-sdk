package com.box.sdkgen.managers.folderlocks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFolderLockHeaders {

  public Map<String, String> extraHeaders;

  public CreateFolderLockHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateFolderLockHeaders(Builder builder) {
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

    public CreateFolderLockHeaders build() {
      return new CreateFolderLockHeaders(this);
    }
  }
}
