package com.box.sdkgen.managers.collections;

import java.util.List;

public class GetCollectionsQueryParams {

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   */
  public List<String> fields;

  /**
   * The offset of the item at which to begin the response.
   *
   * <p>Queries with offset parameter value exceeding 10000 will be rejected with a 400 response.
   */
  public Long offset;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetCollectionsQueryParams() {}

  protected GetCollectionsQueryParams(Builder builder) {
    this.fields = builder.fields;
    this.offset = builder.offset;
    this.limit = builder.limit;
  }

  public List<String> getFields() {
    return fields;
  }

  public Long getOffset() {
    return offset;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected List<String> fields;

    protected Long offset;

    protected Long limit;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetCollectionsQueryParams build() {
      return new GetCollectionsQueryParams(this);
    }
  }
}
