package com.box.sdkgen.managers.chunkeduploads;

public class GetFileUploadSessionPartsByUrlQueryParams {

  public Long offset;

  public Long limit;

  public GetFileUploadSessionPartsByUrlQueryParams() {}

  protected GetFileUploadSessionPartsByUrlQueryParams(
      GetFileUploadSessionPartsByUrlQueryParamsBuilder builder) {
    this.offset = builder.offset;
    this.limit = builder.limit;
  }

  public Long getOffset() {
    return offset;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetFileUploadSessionPartsByUrlQueryParamsBuilder {

    protected Long offset;

    protected Long limit;

    public GetFileUploadSessionPartsByUrlQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetFileUploadSessionPartsByUrlQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFileUploadSessionPartsByUrlQueryParams build() {
      return new GetFileUploadSessionPartsByUrlQueryParams(this);
    }
  }
}
