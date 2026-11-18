package com.box.sdkgen.managers.aisessions;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetAiSessionsV2026R0QueryParams {

  /** Restricts the list to AI sessions with this origin. The only supported value is `api`. */
  public final EnumWrapper<GetAiSessionsV2026R0QueryParamsSourceField> source;

  /**
   * The position marker at which to begin returning results, as returned in `next_marker` by the
   * previous page. Omit it for the first page. All other parameters must match the request that
   * returned the marker.
   */
  public String marker;

  /**
   * The field to sort results by. Possible values are `created_at`, `updated_at`, and `name`.
   * Default is `updated_at`, which advances on each turn of session activity.
   */
  public EnumWrapper<GetAiSessionsV2026R0QueryParamsSortField> sort;

  /**
   * The direction to sort results in, either ascending (`asc`) or descending (`desc`). Default is
   * `desc`.
   */
  public EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField> direction;

  /**
   * The maximum number of AI sessions to return per page. The maximum is `100`; larger values are
   * treated as `100`. The server may return fewer entries than requested.
   */
  public Integer limit;

  /**
   * A comma-separated list of OAuth client IDs. Restricts the list to AI sessions created by any of
   * these applications. Client IDs that match no application are ignored, so the response is an
   * empty list if none of them match. Empty entries are ignored, and an empty value is treated as
   * if the parameter were not provided.
   */
  public List<String> clientIds;

  public GetAiSessionsV2026R0QueryParams(GetAiSessionsV2026R0QueryParamsSourceField source) {
    this.source = new EnumWrapper<GetAiSessionsV2026R0QueryParamsSourceField>(source);
  }

  public GetAiSessionsV2026R0QueryParams(
      EnumWrapper<GetAiSessionsV2026R0QueryParamsSourceField> source) {
    this.source = source;
  }

  protected GetAiSessionsV2026R0QueryParams(Builder builder) {
    this.source = builder.source;
    this.marker = builder.marker;
    this.sort = builder.sort;
    this.direction = builder.direction;
    this.limit = builder.limit;
    this.clientIds = builder.clientIds;
  }

  public EnumWrapper<GetAiSessionsV2026R0QueryParamsSourceField> getSource() {
    return source;
  }

  public String getMarker() {
    return marker;
  }

  public EnumWrapper<GetAiSessionsV2026R0QueryParamsSortField> getSort() {
    return sort;
  }

  public EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField> getDirection() {
    return direction;
  }

  public Integer getLimit() {
    return limit;
  }

  public List<String> getClientIds() {
    return clientIds;
  }

  public static class Builder {

    protected final EnumWrapper<GetAiSessionsV2026R0QueryParamsSourceField> source;

    protected String marker;

    protected EnumWrapper<GetAiSessionsV2026R0QueryParamsSortField> sort;

    protected EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField> direction;

    protected Integer limit;

    protected List<String> clientIds;

    public Builder(GetAiSessionsV2026R0QueryParamsSourceField source) {
      this.source = new EnumWrapper<GetAiSessionsV2026R0QueryParamsSourceField>(source);
    }

    public Builder(EnumWrapper<GetAiSessionsV2026R0QueryParamsSourceField> source) {
      this.source = source;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder sort(GetAiSessionsV2026R0QueryParamsSortField sort) {
      this.sort = new EnumWrapper<GetAiSessionsV2026R0QueryParamsSortField>(sort);
      return this;
    }

    public Builder sort(EnumWrapper<GetAiSessionsV2026R0QueryParamsSortField> sort) {
      this.sort = sort;
      return this;
    }

    public Builder direction(GetAiSessionsV2026R0QueryParamsDirectionField direction) {
      this.direction = new EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<GetAiSessionsV2026R0QueryParamsDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public Builder limit(Integer limit) {
      this.limit = limit;
      return this;
    }

    public Builder clientIds(List<String> clientIds) {
      this.clientIds = clientIds;
      return this;
    }

    public GetAiSessionsV2026R0QueryParams build() {
      return new GetAiSessionsV2026R0QueryParams(this);
    }
  }
}
