package com.box.sdkgen.managers.collaborationallowlistexempttargets;

public class GetCollaborationWhitelistExemptTargetsQueryParams {

  public String marker;

  public Long limit;

  public GetCollaborationWhitelistExemptTargetsQueryParams() {}

  protected GetCollaborationWhitelistExemptTargetsQueryParams(
      GetCollaborationWhitelistExemptTargetsQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetCollaborationWhitelistExemptTargetsQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    public GetCollaborationWhitelistExemptTargetsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetCollaborationWhitelistExemptTargetsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetCollaborationWhitelistExemptTargetsQueryParams build() {
      return new GetCollaborationWhitelistExemptTargetsQueryParams(this);
    }
  }
}
