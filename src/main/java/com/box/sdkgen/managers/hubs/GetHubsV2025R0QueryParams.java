package com.box.sdkgen.managers.hubs;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetHubsV2025R0QueryParams {

  /** The query string to search for Box Hubs. */
  public String query;

  /**
   * The scope of the Box Hubs to retrieve. Possible values include `editable`, `view_only`, and
   * `all`. Default is `all`.
   */
  public String scope;

  /**
   * The field to sort results by. Possible values include `name`, `updated_at`, `last_accessed_at`,
   * `view_count`, and `relevance`. Default is `relevance`.
   */
  public String sort;

  /**
   * The direction to sort results in. This can be either in alphabetical ascending (`ASC`) or
   * descending (`DESC`) order.
   */
  public EnumWrapper<GetHubsV2025R0QueryParamsDirectionField> direction;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetHubsV2025R0QueryParams() {}

  protected GetHubsV2025R0QueryParams(Builder builder) {
    this.query = builder.query;
    this.scope = builder.scope;
    this.sort = builder.sort;
    this.direction = builder.direction;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getQuery() {
    return query;
  }

  public String getScope() {
    return scope;
  }

  public String getSort() {
    return sort;
  }

  public EnumWrapper<GetHubsV2025R0QueryParamsDirectionField> getDirection() {
    return direction;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected String query;

    protected String scope;

    protected String sort;

    protected EnumWrapper<GetHubsV2025R0QueryParamsDirectionField> direction;

    protected String marker;

    protected Long limit;

    public Builder query(String query) {
      this.query = query;
      return this;
    }

    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder sort(String sort) {
      this.sort = sort;
      return this;
    }

    public Builder direction(GetHubsV2025R0QueryParamsDirectionField direction) {
      this.direction = new EnumWrapper<GetHubsV2025R0QueryParamsDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<GetHubsV2025R0QueryParamsDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetHubsV2025R0QueryParams build() {
      return new GetHubsV2025R0QueryParams(this);
    }
  }
}
