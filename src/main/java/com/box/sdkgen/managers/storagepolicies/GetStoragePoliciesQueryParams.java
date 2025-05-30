package com.box.sdkgen.managers.storagepolicies;

import java.util.List;

public class GetStoragePoliciesQueryParams {

  public List<String> fields;

  public String marker;

  public Long limit;

  public GetStoragePoliciesQueryParams() {}

  protected GetStoragePoliciesQueryParams(GetStoragePoliciesQueryParamsBuilder builder) {
    this.fields = builder.fields;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public List<String> getFields() {
    return fields;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetStoragePoliciesQueryParamsBuilder {

    protected List<String> fields;

    protected String marker;

    protected Long limit;

    public GetStoragePoliciesQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetStoragePoliciesQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetStoragePoliciesQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetStoragePoliciesQueryParams build() {
      return new GetStoragePoliciesQueryParams(this);
    }
  }
}
