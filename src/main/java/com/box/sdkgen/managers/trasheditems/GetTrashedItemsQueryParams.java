package com.box.sdkgen.managers.trasheditems;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetTrashedItemsQueryParams {

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

  /**
   * Specifies whether to use marker-based pagination instead of offset-based pagination. Only one
   * pagination method can be used at a time.
   *
   * <p>By setting this value to true, the API will return a `marker` field that can be passed as a
   * parameter to this endpoint to get the next page of the response.
   */
  public Boolean usemarker;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /**
   * The direction to sort results in. This can be either in alphabetical ascending (`ASC`) or
   * descending (`DESC`) order.
   */
  public EnumWrapper<GetTrashedItemsQueryParamsDirectionField> direction;

  /**
   * Defines the **second** attribute by which items are sorted.
   *
   * <p>Items are always sorted by their `type` first, with folders listed before files, and files
   * listed before web links.
   *
   * <p>This parameter is not supported when using marker-based pagination.
   */
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
