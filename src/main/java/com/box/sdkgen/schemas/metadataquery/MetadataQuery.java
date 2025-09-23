package com.box.sdkgen.schemas.metadataquery;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataQuery extends SerializableObject {

  protected final String from;

  protected String query;

  @JsonProperty("query_params")
  protected Map<String, Object> queryParams;

  @JsonProperty("ancestor_folder_id")
  protected final String ancestorFolderId;

  @JsonProperty("order_by")
  protected List<MetadataQueryOrderByField> orderBy;

  protected Long limit;

  protected String marker;

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
