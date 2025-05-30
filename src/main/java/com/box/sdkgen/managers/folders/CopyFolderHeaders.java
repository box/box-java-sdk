package com.box.sdkgen.managers.folders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CopyFolderHeaders {

  public Map<String, String> extraHeaders;

  public CopyFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CopyFolderHeaders(CopyFolderHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CopyFolderHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CopyFolderHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CopyFolderHeaders build() {
      return new CopyFolderHeaders(this);
    }
  }
}
