package com.box.sdkgen.managers.folderclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class AddClassificationToFolderHeaders {

  public Map<String, String> extraHeaders;

  public AddClassificationToFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected AddClassificationToFolderHeaders(Builder builder) {
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

    public AddClassificationToFolderHeaders build() {
      return new AddClassificationToFolderHeaders(this);
    }
  }
}
