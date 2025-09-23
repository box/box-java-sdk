package com.box.sdkgen.managers.chunkeduploads;

public class GetFileUploadSessionPartsByUrlQueryParams {

  public Long offset;

  public Long limit;

  public GetFileUploadSessionPartsByUrlQueryParams() {}

  protected GetFileUploadSessionPartsByUrlQueryParams(Builder builder) {
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

    public GetFileUploadSessionPartsByUrlQueryParams build() {
      return new GetFileUploadSessionPartsByUrlQueryParams(this);
    }
  }
}
