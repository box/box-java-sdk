package com.box.sdkgen.managers.transfer;

import java.util.List;

public class TransferOwnedFolderQueryParams {

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   */
  public List<String> fields;

  /** Determines if users should receive email notification for the action performed. */
  public Boolean notify;

  public TransferOwnedFolderQueryParams() {}

  protected TransferOwnedFolderQueryParams(Builder builder) {
    this.fields = builder.fields;
    this.notify = builder.notify;
  }

  public List<String> getFields() {
    return fields;
  }

  public Boolean getNotify() {
    return notify;
  }

  public static class Builder {

    protected List<String> fields;

    protected Boolean notify;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder notify(Boolean notify) {
      this.notify = notify;
      return this;
    }

    public TransferOwnedFolderQueryParams build() {
      return new TransferOwnedFolderQueryParams(this);
    }
  }
}
