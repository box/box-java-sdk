package com.box.sdkgen.schemas.metadataquery;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Create a search using SQL-like syntax to return items that match specific metadata. */
@JsonFilter("nullablePropertyFilter")
public class MetadataQuery extends SerializableObject {

  /**
   * Specifies the template used in the query. Must be in the form `scope.templateKey`. Not all
   * templates can be used in this field, most notably the built-in, Box-provided classification
   * templates can not be used in a query.
   */
  protected final String from;

  /**
   * The query to perform. A query is a logical expression that is very similar to a SQL `SELECT`
   * statement. Values in the search query can be turned into parameters specified in the
   * `query_param` arguments list to prevent having to manually insert search values into the query
   * string.
   *
   * <p>For example, a value of `:amount` would represent the `amount` value in `query_params`
   * object.
   */
  protected String query;

  /**
   * Set of arguments corresponding to the parameters specified in the `query`. The type of each
   * parameter used in the `query_params` must match the type of the corresponding metadata template
   * field.
   */
  @JsonProperty("query_params")
  protected Map<String, Object> queryParams;

  /**
   * The ID of the folder that you are restricting the query to. A value of zero will return results
   * from all folders you have access to. A non-zero value will only return results found in the
   * folder corresponding to the ID or in any of its subfolders.
   */
  @JsonProperty("ancestor_folder_id")
  protected final String ancestorFolderId;

  /**
   * A list of template fields and directions to sort the metadata query results by.
   *
   * <p>The ordering `direction` must be the same for each item in the array.
   */
  @JsonProperty("order_by")
  protected List<MetadataQueryOrderByField> orderBy;

  /**
   * A value between 0 and 100 that indicates the maximum number of results to return for a single
   * request. This only specifies a maximum boundary and will not guarantee the minimum number of
   * results returned.
   */
  protected Long limit;

  /** Marker to use for requesting the next page. */
  protected String marker;

  /**
   * By default, this endpoint returns only the most basic info about the items for which the query
   * matches. This attribute can be used to specify a list of additional attributes to return for
   * any item, including its metadata.
   *
   * <p>This attribute takes a list of item fields, metadata template identifiers, or metadata
   * template field identifiers.
   *
   * <p>For example:
   *
   * <p>* `created_by` will add the details of the user who created the item to the response. *
   * `metadata.&lt;scope&gt;.&lt;templateKey&gt;` will return the mini-representation of the
   * metadata instance identified by the `scope` and `templateKey`. *
   * `metadata.&lt;scope&gt;.&lt;templateKey&gt;.&lt;field&gt;` will return all the
   * mini-representation of the metadata instance identified by the `scope` and `templateKey` plus
   * the field specified by the `field` name. Multiple fields for the same `scope` and `templateKey`
   * can be defined.
   */
  protected List<String> fields;

  public MetadataQuery(
      @JsonProperty("from") String from,
      @JsonProperty("ancestor_folder_id") String ancestorFolderId) {
    super();
    this.from = from;
    this.ancestorFolderId = ancestorFolderId;
  }

  protected MetadataQuery(Builder builder) {
    super();
    this.from = builder.from;
    this.query = builder.query;
    this.queryParams = builder.queryParams;
    this.ancestorFolderId = builder.ancestorFolderId;
    this.orderBy = builder.orderBy;
    this.limit = builder.limit;
    this.marker = builder.marker;
    this.fields = builder.fields;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getFrom() {
    return from;
  }

  public String getQuery() {
    return query;
  }

  public Map<String, Object> getQueryParams() {
    return queryParams;
  }

  public String getAncestorFolderId() {
    return ancestorFolderId;
  }

  public List<MetadataQueryOrderByField> getOrderBy() {
    return orderBy;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public List<String> getFields() {
    return fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataQuery casted = (MetadataQuery) o;
    return Objects.equals(from, casted.from)
        && Objects.equals(query, casted.query)
        && Objects.equals(queryParams, casted.queryParams)
        && Objects.equals(ancestorFolderId, casted.ancestorFolderId)
        && Objects.equals(orderBy, casted.orderBy)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(marker, casted.marker)
        && Objects.equals(fields, casted.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, query, queryParams, ancestorFolderId, orderBy, limit, marker, fields);
  }

  @Override
  public String toString() {
    return "MetadataQuery{"
        + "from='"
        + from
        + '\''
        + ", "
        + "query='"
        + query
        + '\''
        + ", "
        + "queryParams='"
        + queryParams
        + '\''
        + ", "
        + "ancestorFolderId='"
        + ancestorFolderId
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
        + "marker='"
        + marker
        + '\''
        + ", "
        + "fields='"
        + fields
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String from;

    protected String query;

    protected Map<String, Object> queryParams;

    protected final String ancestorFolderId;

    protected List<MetadataQueryOrderByField> orderBy;

    protected Long limit;

    protected String marker;

    protected List<String> fields;

    public Builder(String from, String ancestorFolderId) {
      super();
      this.from = from;
      this.ancestorFolderId = ancestorFolderId;
    }

    public Builder query(String query) {
      this.query = query;
      return this;
    }

    public Builder queryParams(Map<String, Object> queryParams) {
      this.queryParams = queryParams;
      return this;
    }

    public Builder orderBy(List<MetadataQueryOrderByField> orderBy) {
      this.orderBy = orderBy;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public MetadataQuery build() {
      return new MetadataQuery(this);
    }
  }
}
