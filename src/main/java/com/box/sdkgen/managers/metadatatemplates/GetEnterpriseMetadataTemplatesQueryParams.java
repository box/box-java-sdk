package com.box.sdkgen.managers.metadatatemplates;

public class GetEnterpriseMetadataTemplatesQueryParams {

  public String marker;

  public Long limit;

  public GetEnterpriseMetadataTemplatesQueryParams() {}

  protected GetEnterpriseMetadataTemplatesQueryParams(
      GetEnterpriseMetadataTemplatesQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetEnterpriseMetadataTemplatesQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    public GetEnterpriseMetadataTemplatesQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetEnterpriseMetadataTemplatesQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetEnterpriseMetadataTemplatesQueryParams build() {
      return new GetEnterpriseMetadataTemplatesQueryParams(this);
    }
  }
}
