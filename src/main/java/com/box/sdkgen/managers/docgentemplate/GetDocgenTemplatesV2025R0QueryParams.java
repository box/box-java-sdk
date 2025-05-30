package com.box.sdkgen.managers.docgentemplate;

public class GetDocgenTemplatesV2025R0QueryParams {

  public String marker;

  public Long limit;

  public GetDocgenTemplatesV2025R0QueryParams() {}

  protected GetDocgenTemplatesV2025R0QueryParams(
      GetDocgenTemplatesV2025R0QueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetDocgenTemplatesV2025R0QueryParamsBuilder {

    protected String marker;

    protected Long limit;

    public GetDocgenTemplatesV2025R0QueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetDocgenTemplatesV2025R0QueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetDocgenTemplatesV2025R0QueryParams build() {
      return new GetDocgenTemplatesV2025R0QueryParams(this);
    }
  }
}
