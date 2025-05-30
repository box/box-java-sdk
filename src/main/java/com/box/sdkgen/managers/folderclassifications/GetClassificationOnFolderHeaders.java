package com.box.sdkgen.managers.folderclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetClassificationOnFolderHeaders {

  public Map<String, String> extraHeaders;

  public GetClassificationOnFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetClassificationOnFolderHeaders(GetClassificationOnFolderHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetClassificationOnFolderHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetClassificationOnFolderHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetClassificationOnFolderHeaders build() {
      return new GetClassificationOnFolderHeaders(this);
    }
  }
}
