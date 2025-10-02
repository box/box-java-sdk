package com.box.sdkgen.managers.metadatatemplates;

public class GetEnterpriseMetadataTemplatesQueryParams {

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetEnterpriseMetadataTemplatesQueryParams() {}

  protected GetEnterpriseMetadataTemplatesQueryParams(Builder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected String marker;

    protected Long limit;

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetEnterpriseMetadataTemplatesQueryParams build() {
      return new GetEnterpriseMetadataTemplatesQueryParams(this);
    }
  }
}
