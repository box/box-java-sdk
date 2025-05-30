package com.box.sdkgen.managers.listcollaborations;

import java.util.List;

public class GetFileCollaborationsQueryParams {

  public List<String> fields;

  public Long limit;

  public String marker;

  public GetFileCollaborationsQueryParams() {}

  protected GetFileCollaborationsQueryParams(GetFileCollaborationsQueryParamsBuilder builder) {
    this.fields = builder.fields;
    this.limit = builder.limit;
    this.marker = builder.marker;
  }

  public List<String> getFields() {
    return fields;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public static class GetFileCollaborationsQueryParamsBuilder {

    protected List<String> fields;

    protected Long limit;

    protected String marker;

    public GetFileCollaborationsQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetFileCollaborationsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFileCollaborationsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetFileCollaborationsQueryParams build() {
      return new GetFileCollaborationsQueryParams(this);
    }
  }
}
