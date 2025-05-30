package com.box.sdkgen.managers.search;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class SearchForContentHeaders {

  public Map<String, String> extraHeaders;

  public SearchForContentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected SearchForContentHeaders(SearchForContentHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class SearchForContentHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public SearchForContentHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public SearchForContentHeaders build() {
      return new SearchForContentHeaders(this);
    }
  }
}
