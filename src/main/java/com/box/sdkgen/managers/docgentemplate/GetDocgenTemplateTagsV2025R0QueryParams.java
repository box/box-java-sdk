package com.box.sdkgen.managers.docgentemplate;

public class GetDocgenTemplateTagsV2025R0QueryParams {

  public String templateVersionId;

  public String marker;

  public Long limit;

  public GetDocgenTemplateTagsV2025R0QueryParams() {}

  protected GetDocgenTemplateTagsV2025R0QueryParams(
      GetDocgenTemplateTagsV2025R0QueryParamsBuilder builder) {
    this.templateVersionId = builder.templateVersionId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getTemplateVersionId() {
    return templateVersionId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetDocgenTemplateTagsV2025R0QueryParamsBuilder {

    protected String templateVersionId;

    protected String marker;

    protected Long limit;

    public GetDocgenTemplateTagsV2025R0QueryParamsBuilder templateVersionId(
        String templateVersionId) {
      this.templateVersionId = templateVersionId;
      return this;
    }

    public GetDocgenTemplateTagsV2025R0QueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetDocgenTemplateTagsV2025R0QueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetDocgenTemplateTagsV2025R0QueryParams build() {
      return new GetDocgenTemplateTagsV2025R0QueryParams(this);
    }
  }
}
