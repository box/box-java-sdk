package com.box.sdkgen.managers.groups;

import java.util.List;

public class GetGroupsQueryParams {

  public String filterTerm;

  public List<String> fields;

  public Long limit;

  public Long offset;

  public GetGroupsQueryParams() {}

  protected GetGroupsQueryParams(Builder builder) {
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

  public static class Builder {

    protected String filterTerm;

    protected List<String> fields;

    protected Long limit;

    protected Long offset;

    public Builder filterTerm(String filterTerm) {
      this.filterTerm = filterTerm;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetGroupsQueryParams build() {
      return new GetGroupsQueryParams(this);
    }
  }
}
