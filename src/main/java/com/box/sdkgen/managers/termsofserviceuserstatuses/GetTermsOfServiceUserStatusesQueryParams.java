package com.box.sdkgen.managers.termsofserviceuserstatuses;

public class GetTermsOfServiceUserStatusesQueryParams {

  public final String tosId;

  public String userId;

  public GetTermsOfServiceUserStatusesQueryParams(String tosId) {
    this.tosId = tosId;
  }

  protected GetTermsOfServiceUserStatusesQueryParams(
      GetTermsOfServiceUserStatusesQueryParamsBuilder builder) {
    this.tosId = builder.tosId;
    this.userId = builder.userId;
  }

  public String getTosId() {
    return tosId;
  }

  public String getUserId() {
    return userId;
  }

  public static class GetTermsOfServiceUserStatusesQueryParamsBuilder {

    protected final String tosId;

    protected String userId;

    public GetTermsOfServiceUserStatusesQueryParamsBuilder(String tosId) {
      this.tosId = tosId;
    }

    public GetTermsOfServiceUserStatusesQueryParamsBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public GetTermsOfServiceUserStatusesQueryParams build() {
      return new GetTermsOfServiceUserStatusesQueryParams(this);
    }
  }
}
