package com.box.sdkgen.managers.chunkeduploads;

public class GetFileUploadSessionPartsQueryParams {

  public Long offset;

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
