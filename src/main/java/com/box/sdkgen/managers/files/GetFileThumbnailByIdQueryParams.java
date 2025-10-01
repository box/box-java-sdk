package com.box.sdkgen.managers.files;

public class GetFileThumbnailByIdQueryParams {

  /** The minimum height of the thumbnail. */
  public Long minHeight;

  /** The minimum width of the thumbnail. */
  public Long minWidth;

  /** The maximum height of the thumbnail. */
  public Long maxHeight;

  /** The maximum width of the thumbnail. */
  public Long maxWidth;

  public GetFileThumbnailByIdQueryParams() {}

  protected GetFileThumbnailByIdQueryParams(Builder builder) {
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

  public static class Builder {

    protected Long minHeight;

    protected Long minWidth;

    protected Long maxHeight;

    protected Long maxWidth;

    public Builder minHeight(Long minHeight) {
      this.minHeight = minHeight;
      return this;
    }

    public Builder minWidth(Long minWidth) {
      this.minWidth = minWidth;
      return this;
    }

    public Builder maxHeight(Long maxHeight) {
      this.maxHeight = maxHeight;
      return this;
    }

    public Builder maxWidth(Long maxWidth) {
      this.maxWidth = maxWidth;
      return this;
    }

    public GetFileThumbnailByIdQueryParams build() {
      return new GetFileThumbnailByIdQueryParams(this);
    }
  }
}
