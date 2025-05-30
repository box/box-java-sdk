package com.box.sdkgen.managers.folderclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class AddClassificationToFolderHeaders {

  public Map<String, String> extraHeaders;

  public AddClassificationToFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected AddClassificationToFolderHeaders(AddClassificationToFolderHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class AddClassificationToFolderHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public AddClassificationToFolderHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public AddClassificationToFolderHeaders build() {
      return new AddClassificationToFolderHeaders(this);
    }
  }
}
