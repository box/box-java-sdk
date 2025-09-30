package com.box.sdkgen.managers.listcollaborations;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetCollaborationsQueryParams {

  public final EnumWrapper<GetCollaborationsQueryParamsStatusField> status;

  public List<String> fields;

  public Long offset;

  public Long limit;

  public GetCollaborationsQueryParams(GetCollaborationsQueryParamsStatusField status) {
    this.status = new EnumWrapper<GetCollaborationsQueryParamsStatusField>(status);
  }

  public GetCollaborationsQueryParams(EnumWrapper<GetCollaborationsQueryParamsStatusField> status) {
    this.status = status;
  }

  protected GetCollaborationsQueryParams(Builder builder) {
    this.status = builder.status;
    this.fields = builder.fields;
    this.offset = builder.offset;
    this.limit = builder.limit;
  }

  public EnumWrapper<GetCollaborationsQueryParamsStatusField> getStatus() {
    return status;
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

    protected final EnumWrapper<GetCollaborationsQueryParamsStatusField> status;

    protected List<String> fields;

    protected Long offset;

    protected Long limit;

    public Builder(GetCollaborationsQueryParamsStatusField status) {
      this.status = new EnumWrapper<GetCollaborationsQueryParamsStatusField>(status);
    }

    public Builder(EnumWrapper<GetCollaborationsQueryParamsStatusField> status) {
      this.status = status;
    }

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

    public GetCollaborationsQueryParams build() {
      return new GetCollaborationsQueryParams(this);
    }
  }
}
