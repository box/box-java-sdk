package com.box.sdkgen.managers.groups;

import java.util.List;

public class GetGroupsQueryParams {

  /** Limits the results to only groups whose `name` starts with the search term. */
  public String filterTerm;

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   */
  public List<String> fields;

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * The offset of the item at which to begin the response.
   *
   * <p>Queries with offset parameter value exceeding 10000 will be rejected with a 400 response.
   */
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
