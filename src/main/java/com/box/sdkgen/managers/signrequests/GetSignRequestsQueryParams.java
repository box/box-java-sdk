package com.box.sdkgen.managers.signrequests;

import java.util.List;

public class GetSignRequestsQueryParams {

  public String marker;

  public Long limit;

  public List<String> senders;

  public Boolean sharedRequests;

  public GetSignRequestsQueryParams() {}

  protected GetSignRequestsQueryParams(GetSignRequestsQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
    this.senders = builder.senders;
    this.sharedRequests = builder.sharedRequests;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public List<String> getSenders() {
    return senders;
  }

  public Boolean getSharedRequests() {
    return sharedRequests;
  }

  public static class GetSignRequestsQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    protected List<String> senders;

    protected Boolean sharedRequests;

    public GetSignRequestsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetSignRequestsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetSignRequestsQueryParamsBuilder senders(List<String> senders) {
      this.senders = senders;
      return this;
    }

    public GetSignRequestsQueryParamsBuilder sharedRequests(Boolean sharedRequests) {
      this.sharedRequests = sharedRequests;
      return this;
    }

    public GetSignRequestsQueryParams build() {
      return new GetSignRequestsQueryParams(this);
    }
  }
}
