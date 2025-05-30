package com.box.sdkgen.managers.chunkeduploads;

public class GetFileUploadSessionPartsQueryParams {

  public Long offset;

  public Long limit;

  public GetFileUploadSessionPartsQueryParams() {}

  protected GetFileUploadSessionPartsQueryParams(
      GetFileUploadSessionPartsQueryParamsBuilder builder) {
    this.offset = builder.offset;
    this.limit = builder.limit;
  }

  public Long getOffset() {
    return offset;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetFileUploadSessionPartsQueryParamsBuilder {

    protected Long offset;

    protected Long limit;

    public GetFileUploadSessionPartsQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetFileUploadSessionPartsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFileUploadSessionPartsQueryParams build() {
      return new GetFileUploadSessionPartsQueryParams(this);
    }
  }
}
