package com.box.sdkgen.managers.metadatatemplates;

public class GetMetadataTemplatesByInstanceIdQueryParams {

  public final String metadataInstanceId;

  public String marker;

  public Long limit;

  public GetMetadataTemplatesByInstanceIdQueryParams(String metadataInstanceId) {
    this.metadataInstanceId = metadataInstanceId;
  }

  protected GetMetadataTemplatesByInstanceIdQueryParams(Builder builder) {
    this.metadataInstanceId = builder.metadataInstanceId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMetadataInstanceId() {
    return metadataInstanceId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String metadataInstanceId;

    protected String marker;

    protected Long limit;

    public Builder(String metadataInstanceId) {
      this.metadataInstanceId = metadataInstanceId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetMetadataTemplatesByInstanceIdQueryParams build() {
      return new GetMetadataTemplatesByInstanceIdQueryParams(this);
    }
  }
}
