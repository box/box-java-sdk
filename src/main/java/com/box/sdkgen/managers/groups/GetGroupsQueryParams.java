package com.box.sdkgen.managers.groups;

import java.util.List;

public class GetGroupsQueryParams {

  public String filterTerm;

  public List<String> fields;

  public Long limit;

  public Long offset;

  public GetGroupsQueryParams() {}

  protected GetGroupsQueryParams(GetGroupsQueryParamsBuilder builder) {
    this.filterTerm = builder.filterTerm;
    this.fields = builder.fields;
    this.limit = builder.limit;
    this.offset = builder.offset;
  }

  public String getFilterTerm() {
    return filterTerm;
  }

  public List<String> getFields() {
    return fields;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getOffset() {
    return offset;
  }

  public static class GetGroupsQueryParamsBuilder {

    protected String filterTerm;

    protected List<String> fields;

    protected Long limit;

    protected Long offset;

    public GetGroupsQueryParamsBuilder filterTerm(String filterTerm) {
      this.filterTerm = filterTerm;
      return this;
    }

    public GetGroupsQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetGroupsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetGroupsQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetGroupsQueryParams build() {
      return new GetGroupsQueryParams(this);
    }
  }
}
