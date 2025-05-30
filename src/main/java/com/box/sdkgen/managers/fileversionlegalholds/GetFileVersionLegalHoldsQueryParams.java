package com.box.sdkgen.managers.fileversionlegalholds;

public class GetFileVersionLegalHoldsQueryParams {

  public final String policyId;

  public String marker;

  public Long limit;

  public GetFileVersionLegalHoldsQueryParams(String policyId) {
    this.policyId = policyId;
  }

  protected GetFileVersionLegalHoldsQueryParams(
      GetFileVersionLegalHoldsQueryParamsBuilder builder) {
    this.policyId = builder.policyId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getPolicyId() {
    return policyId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetFileVersionLegalHoldsQueryParamsBuilder {

    protected final String policyId;

    protected String marker;

    protected Long limit;

    public GetFileVersionLegalHoldsQueryParamsBuilder(String policyId) {
      this.policyId = policyId;
    }

    public GetFileVersionLegalHoldsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetFileVersionLegalHoldsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFileVersionLegalHoldsQueryParams build() {
      return new GetFileVersionLegalHoldsQueryParams(this);
    }
  }
}
