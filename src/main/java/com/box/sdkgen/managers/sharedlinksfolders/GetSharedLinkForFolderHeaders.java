package com.box.sdkgen.managers.sharedlinksfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSharedLinkForFolderHeaders {

  public Map<String, String> extraHeaders;

  public GetSharedLinkForFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSharedLinkForFolderHeaders(Builder builder) {
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

    public GetSharedLinkForFolderHeaders build() {
      return new GetSharedLinkForFolderHeaders(this);
    }
  }
}
