package com.box.sdkgen.managers.search;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class SearchForContentHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public SearchForContentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected SearchForContentHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {}

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public SearchForContentHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new SearchForContentHeaders(this);
    }
  }
}
