package com.box.sdkgen.managers.trashedfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTrashedFolderByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetTrashedFolderByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTrashedFolderByIdHeaders(GetTrashedFolderByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetTrashedFolderByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetTrashedFolderByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetTrashedFolderByIdHeaders build() {
      return new GetTrashedFolderByIdHeaders(this);
    }
  }
}
