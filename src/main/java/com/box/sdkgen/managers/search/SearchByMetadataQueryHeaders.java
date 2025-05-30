package com.box.sdkgen.managers.search;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class SearchByMetadataQueryHeaders {

  public Map<String, String> extraHeaders;

  public SearchByMetadataQueryHeaders() {
    this.extraHeaders = mapOf();
  }

  protected SearchByMetadataQueryHeaders(SearchByMetadataQueryHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class SearchByMetadataQueryHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public SearchByMetadataQueryHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public SearchByMetadataQueryHeaders build() {
      return new SearchByMetadataQueryHeaders(this);
    }
  }
}
