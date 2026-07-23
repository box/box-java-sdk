package com.box.sdkgen.schemas.v2026r0.queryrequestbodyv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.queryancestorreferencev2026r0.QueryAncestorReferenceV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class QueryRequestBodyV2026R0QueryField extends SerializableObject {

  /**
   * A logical expression used to filter the dataset, similar to an SQL `WHERE` clause. May include
   * named parameters referenced as `:placeholder`.
   */
  protected final String predicate;

  /**
   * A map of placeholder names (without the `:` prefix) to their values. Required only when the
   * predicate contains parameter placeholders. The type of each value must match the type of the
   * field it is compared to.
   */
  protected Map<String, Object> params;

  /**
   * Restricts results to the specified ancestor entities and their recursive descendants. The user
   * must have read access to every listed ancestor.
   */
  protected List<QueryAncestorReferenceV2026R0> ancestors;

  public QueryRequestBodyV2026R0QueryField(@JsonProperty("predicate") String predicate) {
    super();
    this.predicate = predicate;
  }

  protected QueryRequestBodyV2026R0QueryField(Builder builder) {
    super();
    this.predicate = builder.predicate;
    this.params = builder.params;
    this.ancestors = builder.ancestors;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getPredicate() {
    return predicate;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public List<QueryAncestorReferenceV2026R0> getAncestors() {
    return ancestors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryRequestBodyV2026R0QueryField casted = (QueryRequestBodyV2026R0QueryField) o;
    return Objects.equals(predicate, casted.predicate)
        && Objects.equals(params, casted.params)
        && Objects.equals(ancestors, casted.ancestors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(predicate, params, ancestors);
  }

  @Override
  public String toString() {
    return "QueryRequestBodyV2026R0QueryField{"
        + "predicate='"
        + predicate
        + '\''
        + ", "
        + "params='"
        + params
        + '\''
        + ", "
        + "ancestors='"
        + ancestors
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String predicate;

    protected Map<String, Object> params;

    protected List<QueryAncestorReferenceV2026R0> ancestors;

    public Builder(String predicate) {
      super();
      this.predicate = predicate;
    }

    public Builder params(Map<String, Object> params) {
      this.params = params;
      return this;
    }

    public Builder ancestors(List<QueryAncestorReferenceV2026R0> ancestors) {
      this.ancestors = ancestors;
      return this;
    }

    public QueryRequestBodyV2026R0QueryField build() {
      return new QueryRequestBodyV2026R0QueryField(this);
    }
  }
}
