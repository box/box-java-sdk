package com.box.sdkgen.managers.transfer;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class TransferOwnedFolderHeaders {

  public Map<String, String> extraHeaders;

  public TransferOwnedFolderHeaders() {
    this.extraHeaders = mapOf();
  }

  protected TransferOwnedFolderHeaders(Builder builder) {
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

    public TransferOwnedFolderHeaders build() {
      return new TransferOwnedFolderHeaders(this);
    }
  }
}
