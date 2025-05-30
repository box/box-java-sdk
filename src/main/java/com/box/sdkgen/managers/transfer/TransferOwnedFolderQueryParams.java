package com.box.sdkgen.managers.transfer;

import java.util.List;

public class TransferOwnedFolderQueryParams {

  public List<String> fields;

  public Boolean notify;

  public TransferOwnedFolderQueryParams() {}

  protected TransferOwnedFolderQueryParams(TransferOwnedFolderQueryParamsBuilder builder) {
    this.fields = builder.fields;
    this.notify = builder.notify;
  }

  public List<String> getFields() {
    return fields;
  }

  public Boolean getNotify() {
    return notify;
  }

  public static class TransferOwnedFolderQueryParamsBuilder {

    protected List<String> fields;

    protected Boolean notify;

    public TransferOwnedFolderQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public TransferOwnedFolderQueryParamsBuilder notify(Boolean notify) {
      this.notify = notify;
      return this;
    }

    public TransferOwnedFolderQueryParams build() {
      return new TransferOwnedFolderQueryParams(this);
    }
  }
}
