package com.box.sdkgen.managers.folderclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateClassificationOnFolderHeaders {

  public Map<String, String> extraHeaders;

  public UpdateClassificationOnFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateClassificationOnFolderHeaders(
      UpdateClassificationOnFolderHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateClassificationOnFolderHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateClassificationOnFolderHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateClassificationOnFolderHeaders build() {
      return new UpdateClassificationOnFolderHeaders(this);
    }
  }
}
