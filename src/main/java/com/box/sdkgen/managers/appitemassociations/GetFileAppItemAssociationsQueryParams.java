package com.box.sdkgen.managers.appitemassociations;

public class GetFileAppItemAssociationsQueryParams {

  public Long limit;

  public String marker;

  public String applicationType;

  public GetFileAppItemAssociationsQueryParams() {}

  protected GetFileAppItemAssociationsQueryParams(
      GetFileAppItemAssociationsQueryParamsBuilder builder) {
    this.limit = builder.limit;
    this.marker = builder.marker;
    this.applicationType = builder.applicationType;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public String getApplicationType() {
    return applicationType;
  }

  public static class GetFileAppItemAssociationsQueryParamsBuilder {

    protected Long limit;

    protected String marker;

    protected String applicationType;

    public GetFileAppItemAssociationsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFileAppItemAssociationsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetFileAppItemAssociationsQueryParamsBuilder applicationType(String applicationType) {
      this.applicationType = applicationType;
      return this;
    }

    public GetFileAppItemAssociationsQueryParams build() {
      return new GetFileAppItemAssociationsQueryParams(this);
    }
  }
}
