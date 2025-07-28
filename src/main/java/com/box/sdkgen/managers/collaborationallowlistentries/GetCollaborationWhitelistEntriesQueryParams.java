package com.box.sdkgen.managers.collaborationallowlistentries;

public class GetCollaborationWhitelistEntriesQueryParams {

  public String marker;

  public Long limit;

  public GetCollaborationWhitelistEntriesQueryParams() {}

  protected GetCollaborationWhitelistEntriesQueryParams(Builder builder) {
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

    public GetCollaborationWhitelistEntriesQueryParams build() {
      return new GetCollaborationWhitelistEntriesQueryParams(this);
    }
  }
}
