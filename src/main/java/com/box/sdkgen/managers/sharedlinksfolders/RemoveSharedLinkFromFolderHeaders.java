package com.box.sdkgen.managers.sharedlinksfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RemoveSharedLinkFromFolderHeaders {

  public Map<String, String> extraHeaders;

  public RemoveSharedLinkFromFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RemoveSharedLinkFromFolderHeaders(RemoveSharedLinkFromFolderHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class RemoveSharedLinkFromFolderHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public RemoveSharedLinkFromFolderHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public RemoveSharedLinkFromFolderHeaders build() {
      return new RemoveSharedLinkFromFolderHeaders(this);
    }
  }
}
