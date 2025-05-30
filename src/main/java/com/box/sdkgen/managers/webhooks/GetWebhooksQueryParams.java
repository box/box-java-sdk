package com.box.sdkgen.managers.webhooks;

public class GetWebhooksQueryParams {

  public String marker;

  public Long limit;

  public GetWebhooksQueryParams() {}

  protected GetWebhooksQueryParams(GetWebhooksQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetWebhooksQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    public GetWebhooksQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetWebhooksQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetWebhooksQueryParams build() {
      return new GetWebhooksQueryParams(this);
    }
  }
}
