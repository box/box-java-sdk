package com.box.sdkgen.managers.folders;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetFolderItemsQueryParams {

  public List<String> fields;

  public Boolean usemarker;

  public String marker;

  public Long offset;

  public Long limit;

  public EnumWrapper<GetFolderItemsQueryParamsSortField> sort;

  public EnumWrapper<GetFolderItemsQueryParamsDirectionField> direction;

  public GetFolderItemsQueryParams() {}

  protected GetFolderItemsQueryParams(Builder builder) {
    this.fields = builder.fields;
    this.usemarker = builder.usemarker;
    this.marker = builder.marker;
    this.offset = builder.offset;
    this.limit = builder.limit;
    this.sort = builder.sort;
    this.direction = builder.direction;
  }

  public List<String> getFields() {
    return fields;
  }

  public Boolean getUsemarker() {
    return usemarker;
  }

  public String getMarker() {
    return marker;
  }

  public Long getOffset() {
    return offset;
  }

  public Long getLimit() {
    return limit;
  }

  public EnumWrapper<GetFolderItemsQueryParamsSortField> getSort() {
    return sort;
  }

  public EnumWrapper<GetFolderItemsQueryParamsDirectionField> getDirection() {
    return direction;
  }

  public static class Builder {

    protected List<String> fields;

    protected Boolean usemarker;

    protected String marker;

    protected Long offset;

    protected Long limit;

    protected EnumWrapper<GetFolderItemsQueryParamsSortField> sort;

    protected EnumWrapper<GetFolderItemsQueryParamsDirectionField> direction;

    public Builder fields(List<String> fields) {
      this.fields = fields;
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

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder sort(GetFolderItemsQueryParamsSortField sort) {
      this.sort = new EnumWrapper<GetFolderItemsQueryParamsSortField>(sort);
      return this;
    }

    public Builder sort(EnumWrapper<GetFolderItemsQueryParamsSortField> sort) {
      this.sort = sort;
      return this;
    }

    public Builder direction(GetFolderItemsQueryParamsDirectionField direction) {
      this.direction = new EnumWrapper<GetFolderItemsQueryParamsDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<GetFolderItemsQueryParamsDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public GetFolderItemsQueryParams build() {
      return new GetFolderItemsQueryParams(this);
    }
  }
}
