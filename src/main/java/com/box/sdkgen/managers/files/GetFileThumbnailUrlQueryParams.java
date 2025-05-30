package com.box.sdkgen.managers.files;

public class GetFileThumbnailUrlQueryParams {

  public Long minHeight;

  public Long minWidth;

  public Long maxHeight;

  public Long maxWidth;

  public GetFileThumbnailUrlQueryParams() {}

  protected GetFileThumbnailUrlQueryParams(GetFileThumbnailUrlQueryParamsBuilder builder) {
    this.minHeight = builder.minHeight;
    this.minWidth = builder.minWidth;
    this.maxHeight = builder.maxHeight;
    this.maxWidth = builder.maxWidth;
  }

  public Long getMinHeight() {
    return minHeight;
  }

  public Long getMinWidth() {
    return minWidth;
  }

  public Long getMaxHeight() {
    return maxHeight;
  }

  public Long getMaxWidth() {
    return maxWidth;
  }

  public static class GetFileThumbnailUrlQueryParamsBuilder {

    protected Long minHeight;

    protected Long minWidth;

    protected Long maxHeight;

    protected Long maxWidth;

    public GetFileThumbnailUrlQueryParamsBuilder minHeight(Long minHeight) {
      this.minHeight = minHeight;
      return this;
    }

    public GetFileThumbnailUrlQueryParamsBuilder minWidth(Long minWidth) {
      this.minWidth = minWidth;
      return this;
    }

    public GetFileThumbnailUrlQueryParamsBuilder maxHeight(Long maxHeight) {
      this.maxHeight = maxHeight;
      return this;
    }

    public GetFileThumbnailUrlQueryParamsBuilder maxWidth(Long maxWidth) {
      this.maxWidth = maxWidth;
      return this;
    }

    public GetFileThumbnailUrlQueryParams build() {
      return new GetFileThumbnailUrlQueryParams(this);
    }
  }
}
