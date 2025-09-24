package com.box.sdkgen.managers.folders;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetFolderByIdQueryParams {

  public List<String> fields;

  public EnumWrapper<GetFolderByIdQueryParamsSortField> sort;

  public EnumWrapper<GetFolderByIdQueryParamsDirectionField> direction;

  public Long offset;

  public Long limit;

  public GetFolderByIdQueryParams() {}

  protected GetFolderByIdQueryParams(Builder builder) {
    this.fields = builder.fields;
    this.sort = builder.sort;
    this.direction = builder.direction;
    this.offset = builder.offset;
    this.limit = builder.limit;
  }

  public List<String> getFields() {
    return fields;
  }

  public EnumWrapper<GetFolderByIdQueryParamsSortField> getSort() {
    return sort;
  }

  public EnumWrapper<GetFolderByIdQueryParamsDirectionField> getDirection() {
    return direction;
  }

  public Long getOffset() {
    return offset;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected List<String> fields;

    protected EnumWrapper<GetFolderByIdQueryParamsSortField> sort;

    protected EnumWrapper<GetFolderByIdQueryParamsDirectionField> direction;

    protected Long offset;

    protected Long limit;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder sort(GetFolderByIdQueryParamsSortField sort) {
      this.sort = new EnumWrapper<GetFolderByIdQueryParamsSortField>(sort);
      return this;
    }

    public Builder sort(EnumWrapper<GetFolderByIdQueryParamsSortField> sort) {
      this.sort = sort;
      return this;
    }

    public Builder direction(GetFolderByIdQueryParamsDirectionField direction) {
      this.direction = new EnumWrapper<GetFolderByIdQueryParamsDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<GetFolderByIdQueryParamsDirectionField> direction) {
      this.direction = direction;
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

    public GetFolderByIdQueryParams build() {
      return new GetFolderByIdQueryParams(this);
    }
  }
}
