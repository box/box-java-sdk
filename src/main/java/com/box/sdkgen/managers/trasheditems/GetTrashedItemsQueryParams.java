package com.box.sdkgen.managers.trasheditems;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetTrashedItemsQueryParams {

  public List<String> fields;

  public Long limit;

  public Long offset;

  public Boolean usemarker;

  public String marker;

  public EnumWrapper<GetTrashedItemsQueryParamsDirectionField> direction;

  public EnumWrapper<GetTrashedItemsQueryParamsSortField> sort;

  public GetTrashedItemsQueryParams() {}

  protected GetTrashedItemsQueryParams(Builder builder) {
    this.fields = builder.fields;
    this.limit = builder.limit;
    this.offset = builder.offset;
    this.usemarker = builder.usemarker;
    this.marker = builder.marker;
    this.direction = builder.direction;
    this.sort = builder.sort;
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

  public Boolean getUsemarker() {
    return usemarker;
  }

  public String getMarker() {
    return marker;
  }

  public EnumWrapper<GetTrashedItemsQueryParamsDirectionField> getDirection() {
    return direction;
  }

  public EnumWrapper<GetTrashedItemsQueryParamsSortField> getSort() {
    return sort;
  }

  public static class Builder {

    protected List<String> fields;

    protected Long limit;

    protected Long offset;

    protected Boolean usemarker;

    protected String marker;

    protected EnumWrapper<GetTrashedItemsQueryParamsDirectionField> direction;

    protected EnumWrapper<GetTrashedItemsQueryParamsSortField> sort;

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

    public Builder usemarker(Boolean usemarker) {
      this.usemarker = usemarker;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder direction(GetTrashedItemsQueryParamsDirectionField direction) {
      this.direction = new EnumWrapper<GetTrashedItemsQueryParamsDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<GetTrashedItemsQueryParamsDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public Builder sort(GetTrashedItemsQueryParamsSortField sort) {
      this.sort = new EnumWrapper<GetTrashedItemsQueryParamsSortField>(sort);
      return this;
    }

    public Builder sort(EnumWrapper<GetTrashedItemsQueryParamsSortField> sort) {
      this.sort = sort;
      return this;
    }

    public GetTrashedItemsQueryParams build() {
      return new GetTrashedItemsQueryParams(this);
    }
  }
}
