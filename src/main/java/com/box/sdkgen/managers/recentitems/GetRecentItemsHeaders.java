package com.box.sdkgen.managers.recentitems;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetRecentItemsHeaders {

  public Map<String, String> extraHeaders;

  public GetRecentItemsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetRecentItemsHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetRecentItemsHeaders build() {
      return new GetRecentItemsHeaders(this);
    }
  }
}
