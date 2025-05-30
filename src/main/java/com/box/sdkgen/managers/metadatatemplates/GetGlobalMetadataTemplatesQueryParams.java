package com.box.sdkgen.managers.metadatatemplates;

public class GetGlobalMetadataTemplatesQueryParams {

  public String marker;

  public Long limit;

  public GetGlobalMetadataTemplatesQueryParams() {}

  protected GetGlobalMetadataTemplatesQueryParams(
      GetGlobalMetadataTemplatesQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetGlobalMetadataTemplatesQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    public GetGlobalMetadataTemplatesQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetGlobalMetadataTemplatesQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetGlobalMetadataTemplatesQueryParams build() {
      return new GetGlobalMetadataTemplatesQueryParams(this);
    }
  }
}
