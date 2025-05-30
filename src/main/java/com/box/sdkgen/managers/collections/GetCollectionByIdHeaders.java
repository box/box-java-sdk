package com.box.sdkgen.managers.collections;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollectionByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetCollectionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollectionByIdHeaders(GetCollectionByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetCollectionByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetCollectionByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollectionByIdHeaders build() {
      return new GetCollectionByIdHeaders(this);
    }
  }
}
