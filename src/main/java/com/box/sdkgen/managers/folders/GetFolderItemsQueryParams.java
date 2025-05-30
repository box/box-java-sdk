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

  protected GetFolderItemsQueryParams(GetFolderItemsQueryParamsBuilder builder) {
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

  public static class GetFolderItemsQueryParamsBuilder {

    protected List<String> fields;

    protected Boolean usemarker;

    protected String marker;

    protected Long offset;

    protected Long limit;

    protected EnumWrapper<GetFolderItemsQueryParamsSortField> sort;

    protected EnumWrapper<GetFolderItemsQueryParamsDirectionField> direction;

    public GetFolderItemsQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetFolderItemsQueryParamsBuilder usemarker(Boolean usemarker) {
      this.usemarker = usemarker;
      return this;
    }

    public GetFolderItemsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetFolderItemsQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetFolderItemsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFolderItemsQueryParamsBuilder sort(GetFolderItemsQueryParamsSortField sort) {
      this.sort = new EnumWrapper<GetFolderItemsQueryParamsSortField>(sort);
      return this;
    }

    public GetFolderItemsQueryParamsBuilder sort(
        EnumWrapper<GetFolderItemsQueryParamsSortField> sort) {
      this.sort = sort;
      return this;
    }

    public GetFolderItemsQueryParamsBuilder direction(
        GetFolderItemsQueryParamsDirectionField direction) {
      this.direction = new EnumWrapper<GetFolderItemsQueryParamsDirectionField>(direction);
      return this;
    }

    public GetFolderItemsQueryParamsBuilder direction(
        EnumWrapper<GetFolderItemsQueryParamsDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public GetFolderItemsQueryParams build() {
      return new GetFolderItemsQueryParams(this);
    }
  }
}
