package com.box.sdkgen.managers.sharedlinksfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class AddShareLinkToFolderHeaders {

  public Map<String, String> extraHeaders;

  public AddShareLinkToFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected AddShareLinkToFolderHeaders(Builder builder) {
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

    public AddShareLinkToFolderHeaders build() {
      return new AddShareLinkToFolderHeaders(this);
    }
  }
}
