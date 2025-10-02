package com.box.sdkgen.managers.termsofserviceuserstatuses;

public class GetTermsOfServiceUserStatusesQueryParams {

  /** The ID of the terms of service. */
  public final String tosId;

  /** Limits results to the given user ID. */
  public String userId;

  public GetTermsOfServiceUserStatusesQueryParams(String tosId) {
    this.tosId = tosId;
  }

  protected GetTermsOfServiceUserStatusesQueryParams(Builder builder) {
    this.tosId = builder.tosId;
    this.userId = builder.userId;
  }

  public String getTosId() {
    return tosId;
  }

  public String getUserId() {
    return userId;
  }

  public static class Builder {

    protected final String tosId;

    protected String userId;

    public Builder(String tosId) {
      this.tosId = tosId;
    }

    public Builder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public GetTermsOfServiceUserStatusesQueryParams build() {
      return new GetTermsOfServiceUserStatusesQueryParams(this);
    }
  }
}
