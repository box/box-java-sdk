package com.box.sdkgen.schemas.v2026r0.queryrequestbodyv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.queryorderbyv2026r0.QueryOrderByV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * Request body describing the query to run, including the filtering predicate, optional sorting,
 * pagination, and the fields to return for each result.
 */
@JsonFilter("nullablePropertyFilter")
public class QueryRequestBodyV2026R0 extends SerializableObject {

  /**
   * The query definition, including the filtering predicate and its optional parameters and
   * ancestor restrictions.
   */
  protected final QueryRequestBodyV2026R0QueryField query;

  /**
   * The sorting criteria for the result set. Entries are applied sequentially to define multi-level
   * sorting.
   */
  @JsonProperty("order_by")
  protected List<QueryOrderByV2026R0> orderBy;

  /** The maximum number of results to return. Defaults to `50` when not provided. */
  protected Integer limit;

  /**
   * Controls which additional fields are included in each result entry. Each value must be one of:
   * a fully qualified item field key (for example `box:item:name`), a metadata template key to
   * hydrate the full template (for example `enterprise_12345678:project`), or a specific metadata
   * template field key to hydrate a single field from the template (for example
   * `enterprise_12345678:project:name`). When omitted, entries include only the item type and
   * identifier.
   */
  protected List<String> fields;

  /**
   * An opaque token returned from a previous response, used to continue retrieval. When provided,
   * all other request parameters must exactly match those of the original request.
   */
  protected String marker;

  public QueryRequestBodyV2026R0(@JsonProperty("query") QueryRequestBodyV2026R0QueryField query) {
    super();
    this.query = query;
  }

  protected QueryRequestBodyV2026R0(Builder builder) {
    super();
    this.query = builder.query;
    this.orderBy = builder.orderBy;
    this.limit = builder.limit;
    this.fields = builder.fields;
    this.marker = builder.marker;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public QueryRequestBodyV2026R0QueryField getQuery() {
    return query;
  }

  public List<QueryOrderByV2026R0> getOrderBy() {
    return orderBy;
  }

  public Integer getLimit() {
    return limit;
  }

  public List<String> getFields() {
    return fields;
  }

  public String getMarker() {
    return marker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryRequestBodyV2026R0 casted = (QueryRequestBodyV2026R0) o;
    return Objects.equals(query, casted.query)
        && Objects.equals(orderBy, casted.orderBy)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(fields, casted.fields)
        && Objects.equals(marker, casted.marker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(query, orderBy, limit, fields, marker);
  }

  @Override
  public String toString() {
    return "QueryRequestBodyV2026R0{"
        + "query='"
        + query
        + '\''
        + ", "
        + "orderBy='"
        + orderBy
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + ", "
        + "fields='"
        + fields
        + '\''
        + ", "
        + "marker='"
        + marker
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final QueryRequestBodyV2026R0QueryField query;

    protected List<QueryOrderByV2026R0> orderBy;

    protected Integer limit;

    protected List<String> fields;

    protected String marker;

    public Builder(QueryRequestBodyV2026R0QueryField query) {
      super();
      this.query = query;
    }

    public Builder orderBy(List<QueryOrderByV2026R0> orderBy) {
      this.orderBy = orderBy;
      return this;
    }

    public Builder limit(Integer limit) {
      this.limit = limit;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public QueryRequestBodyV2026R0 build() {
      return new QueryRequestBodyV2026R0(this);
    }
  }
}
