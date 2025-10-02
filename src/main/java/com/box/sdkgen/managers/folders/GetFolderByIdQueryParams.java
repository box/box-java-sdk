package com.box.sdkgen.managers.folders;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetFolderByIdQueryParams {

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
  public EnumWrapper<GetFolderByIdQueryParamsSortField> sort;

  /**
   * The direction to sort results in. This can be either in alphabetical ascending (`ASC`) or
   * descending (`DESC`) order.
   */
  public EnumWrapper<GetFolderByIdQueryParamsDirectionField> direction;

  /**
   * The offset of the item at which to begin the response.
   *
   * <p>Queries with offset parameter value exceeding 10000 will be rejected with a 400 response.
   */
  public Long offset;

  /** The maximum number of items to return per page. */
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
