package com.box.sdkgen.managers.folders;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetFolderItemsQueryParams {

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   *
   * <p>Additionally this field can be used to query any metadata applied to the file by specifying
   * the `metadata` field as well as the scope and key of the template to retrieve, for example
   * `?fields=metadata.enterprise_12345.contractTemplate`.
   */
  public List<String> fields;

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
   * The offset of the item at which to begin the response.
   *
   * <p>Offset-based pagination is not guaranteed to work reliably for high offset values and may
   * fail for large datasets. In those cases, use marker-based pagination by setting `usemarker` to
   * `true`.
   */
  public Long offset;

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * Defines the **second** attribute by which items are sorted.
   *
   * <p>The folder type affects the way the items are sorted:
   *
   * <p>* **Standard folder**: Items are always sorted by their `type` first, with folders listed
   * before files, and files listed before web links.
   *
   * <p>* **Root folder**: This parameter is not supported for marker-based pagination on the root
   * folder
   *
   * <p>(the folder with an `id` of `0`).
   *
   * <p>* **Shared folder with parent path to the associated folder visible to the collaborator**:
   * Items are always sorted by their `type` first, with folders listed before files, and files
   * listed before web links.
   */
  public EnumWrapper<GetFolderItemsQueryParamsSortField> sort;

  /**
   * The direction to sort results in. This can be either in alphabetical ascending (`ASC`) or
   * descending (`DESC`) order.
   */
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
