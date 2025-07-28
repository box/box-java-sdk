package com.box.sdkgen.managers.metadatatemplates;

public class GetGlobalMetadataTemplatesQueryParams {

  public String marker;

  public Long limit;

  public GetGlobalMetadataTemplatesQueryParams() {}

  protected GetGlobalMetadataTemplatesQueryParams(Builder builder) {
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

    public GetGlobalMetadataTemplatesQueryParams build() {
      return new GetGlobalMetadataTemplatesQueryParams(this);
    }
  }
}
