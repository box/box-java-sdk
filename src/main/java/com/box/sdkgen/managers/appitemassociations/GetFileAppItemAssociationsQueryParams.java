package com.box.sdkgen.managers.appitemassociations;

public class GetFileAppItemAssociationsQueryParams {

  public Long limit;

  public String marker;

  public String applicationType;

  public GetFileAppItemAssociationsQueryParams() {}

  protected GetFileAppItemAssociationsQueryParams(Builder builder) {
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

  public static class Builder {

    protected Long limit;

    protected String marker;

    protected String applicationType;

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder applicationType(String applicationType) {
      this.applicationType = applicationType;
      return this;
    }

    public GetFileAppItemAssociationsQueryParams build() {
      return new GetFileAppItemAssociationsQueryParams(this);
    }
  }
}
