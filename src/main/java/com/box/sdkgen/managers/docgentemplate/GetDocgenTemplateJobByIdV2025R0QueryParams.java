package com.box.sdkgen.managers.docgentemplate;

public class GetDocgenTemplateJobByIdV2025R0QueryParams {

  public String marker;

  public Long limit;

  public GetDocgenTemplateJobByIdV2025R0QueryParams() {}

  protected GetDocgenTemplateJobByIdV2025R0QueryParams(Builder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected String marker;

    protected Long limit;

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetDocgenTemplateJobByIdV2025R0QueryParams build() {
      return new GetDocgenTemplateJobByIdV2025R0QueryParams(this);
    }
  }
}
