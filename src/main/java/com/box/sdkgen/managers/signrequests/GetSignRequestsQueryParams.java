package com.box.sdkgen.managers.signrequests;

import java.util.List;

public class GetSignRequestsQueryParams {

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * A list of sender emails to filter the signature requests by sender. If provided,
   * `shared_requests` must be set to `true`.
   */
  public List<String> senders;

  /**
   * If set to `true`, only includes requests that user is not an owner, but user is a collaborator.
   * Collaborator access is determined by the user access level of the sign files of the request.
   * Default is `false`. Must be set to `true` if `senders` are provided.
   */
  public Boolean sharedRequests;

  public GetSignRequestsQueryParams() {}

  protected GetSignRequestsQueryParams(Builder builder) {
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

  public static class Builder {

    protected String marker;

    protected Long limit;

    protected List<String> senders;

    protected Boolean sharedRequests;

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder senders(List<String> senders) {
      this.senders = senders;
      return this;
    }

    public Builder sharedRequests(Boolean sharedRequests) {
      this.sharedRequests = sharedRequests;
      return this;
    }

    public GetSignRequestsQueryParams build() {
      return new GetSignRequestsQueryParams(this);
    }
  }
}
