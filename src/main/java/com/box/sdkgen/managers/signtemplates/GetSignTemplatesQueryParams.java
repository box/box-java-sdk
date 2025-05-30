package com.box.sdkgen.managers.signtemplates;

public class GetSignTemplatesQueryParams {

  public String marker;

  public Long limit;

  public GetSignTemplatesQueryParams() {}

  protected GetSignTemplatesQueryParams(GetSignTemplatesQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetSignTemplatesQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    public GetSignTemplatesQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetSignTemplatesQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetSignTemplatesQueryParams build() {
      return new GetSignTemplatesQueryParams(this);
    }
  }
}
