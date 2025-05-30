package com.box.sdkgen.managers.folders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFolderHeaders {

  public Map<String, String> extraHeaders;

  public CreateFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateFolderHeaders(CreateFolderHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateFolderHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateFolderHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateFolderHeaders build() {
      return new CreateFolderHeaders(this);
    }
  }
}
