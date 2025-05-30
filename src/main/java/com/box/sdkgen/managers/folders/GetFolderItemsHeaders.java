package com.box.sdkgen.managers.folders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderItemsHeaders {

  public String boxapi;

  public Map<String, String> extraHeaders;

  public GetFolderItemsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderItemsHeaders(GetFolderItemsHeadersBuilder builder) {
    this.boxapi = builder.boxapi;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getBoxapi() {
    return boxapi;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFolderItemsHeadersBuilder {

    protected String boxapi;

    protected Map<String, String> extraHeaders;

    public GetFolderItemsHeadersBuilder boxapi(String boxapi) {
      this.boxapi = boxapi;
      return this;
    }

    public GetFolderItemsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFolderItemsHeaders build() {
      return new GetFolderItemsHeaders(this);
    }
  }
}
