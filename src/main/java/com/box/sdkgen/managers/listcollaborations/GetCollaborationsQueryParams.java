package com.box.sdkgen.managers.listcollaborations;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetCollaborationsQueryParams {

  /** The status of the collaborations to retrieve. */
  public final EnumWrapper<GetCollaborationsQueryParamsStatusField> status;

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
