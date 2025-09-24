package com.box.sdkgen.managers.collections;

import java.util.List;

public class GetCollectionItemsQueryParams {

  public List<String> fields;

  public Long offset;

  public Long limit;

  public GetCollectionItemsQueryParams() {}

  protected GetCollectionItemsQueryParams(Builder builder) {
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

    public GetCollectionItemsQueryParams build() {
      return new GetCollectionItemsQueryParams(this);
    }
  }
}
