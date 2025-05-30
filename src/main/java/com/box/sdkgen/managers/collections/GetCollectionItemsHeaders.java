package com.box.sdkgen.managers.collections;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollectionItemsHeaders {

  public Map<String, String> extraHeaders;

  public GetCollectionItemsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollectionItemsHeaders(GetCollectionItemsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetCollectionItemsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetCollectionItemsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollectionItemsHeaders build() {
      return new GetCollectionItemsHeaders(this);
    }
  }
}
