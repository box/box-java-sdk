package com.box.sdkgen.managers.folders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderItemsHeaders {

  public String boxapi;

  public Map<String, String> extraHeaders;

  public GetFolderItemsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderItemsHeaders(Builder builder) {
    this.boxapi = builder.boxapi;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getBoxapi() {
    return boxapi;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected String boxapi;

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder boxapi(String boxapi) {
      this.boxapi = boxapi;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFolderItemsHeaders build() {
      return new GetFolderItemsHeaders(this);
    }
  }
}
