package com.box.sdkgen.managers.recentitems;

import java.util.List;

public class GetRecentItemsQueryParams {

  public List<String> fields;

  public Long limit;

  public String marker;

  public GetRecentItemsQueryParams() {}

  protected GetRecentItemsQueryParams(Builder builder) {
    this.fields = builder.fields;
    this.limit = builder.limit;
    this.marker = builder.marker;
  }

  public List<String> getFields() {
    return fields;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public static class Builder {

    protected List<String> fields;

    protected Long limit;

    protected String marker;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetRecentItemsQueryParams build() {
      return new GetRecentItemsQueryParams(this);
    }
  }
}
