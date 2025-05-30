package com.box.sdkgen.managers.transfer;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class TransferOwnedFolderHeaders {

  public Map<String, String> extraHeaders;

  public TransferOwnedFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected TransferOwnedFolderHeaders(TransferOwnedFolderHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class TransferOwnedFolderHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public TransferOwnedFolderHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public TransferOwnedFolderHeaders build() {
      return new TransferOwnedFolderHeaders(this);
    }
  }
}
