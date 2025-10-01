package com.box.sdkgen.managers.fileversionlegalholds;

public class GetFileVersionLegalHoldsQueryParams {

  /** The ID of the legal hold policy to get the file version legal holds for. */
  public final String policyId;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetFileVersionLegalHoldsQueryParams(String policyId) {
    this.policyId = policyId;
  }

  protected GetFileVersionLegalHoldsQueryParams(Builder builder) {
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

  public static class Builder {

    protected final String policyId;

    protected String marker;

    protected Long limit;

    public Builder(String policyId) {
      this.policyId = policyId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFileVersionLegalHoldsQueryParams build() {
      return new GetFileVersionLegalHoldsQueryParams(this);
    }
  }
}
