package com.box.sdkgen.managers.chunkeduploads;

public class GetFileUploadSessionPartsQueryParams {

  /**
   * The offset of the item at which to begin the response.
   *
   * <p>Queries with offset parameter value exceeding 10000 will be rejected with a 400 response.
   */
  public Long offset;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetFileUploadSessionPartsQueryParams() {}

  protected GetFileUploadSessionPartsQueryParams(Builder builder) {
    this.offset = builder.offset;
    this.limit = builder.limit;
  }

  public Long getOffset() {
    return offset;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected Long offset;

    protected Long limit;

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFileUploadSessionPartsQueryParams build() {
      return new GetFileUploadSessionPartsQueryParams(this);
    }
  }
}
