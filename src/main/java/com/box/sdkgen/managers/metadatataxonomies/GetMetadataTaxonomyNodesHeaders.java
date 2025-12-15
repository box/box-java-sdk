package com.box.sdkgen.managers.metadatataxonomies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetMetadataTaxonomyNodesHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public GetMetadataTaxonomyNodesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetMetadataTaxonomyNodesHeaders(Builder builder) {
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

    public GetMetadataTaxonomyNodesHeaders build() {
      return new GetMetadataTaxonomyNodesHeaders(this);
    }
  }
}
