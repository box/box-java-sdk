package com.box.sdkgen.schemas.v2026r0.queryinsightsgroupbyv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Defines a single grouping criterion for an insights request. Currently only a single grouping
 * field is supported.
 */
@JsonFilter("nullablePropertyFilter")
public class QueryInsightsGroupByV2026R0 extends SerializableObject {

  /** The fully qualified field name to group by. Supports metadata and item properties. */
  protected final String field;

  /** The maximum number of buckets to return for the grouping. Defaults to `5`. */
  @JsonProperty("bucket_limit")
  protected Integer bucketLimit;

  public QueryInsightsGroupByV2026R0(@JsonProperty("field") String field) {
    super();
    this.field = field;
  }

  protected QueryInsightsGroupByV2026R0(Builder builder) {
    super();
    this.field = builder.field;
    this.bucketLimit = builder.bucketLimit;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getField() {
    return field;
  }

  public Integer getBucketLimit() {
    return bucketLimit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryInsightsGroupByV2026R0 casted = (QueryInsightsGroupByV2026R0) o;
    return Objects.equals(field, casted.field) && Objects.equals(bucketLimit, casted.bucketLimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, bucketLimit);
  }

  @Override
  public String toString() {
    return "QueryInsightsGroupByV2026R0{"
        + "field='"
        + field
        + '\''
        + ", "
        + "bucketLimit='"
        + bucketLimit
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String field;

    protected Integer bucketLimit;

    public Builder(String field) {
      super();
      this.field = field;
    }

    public Builder bucketLimit(Integer bucketLimit) {
      this.bucketLimit = bucketLimit;
      return this;
    }

    public QueryInsightsGroupByV2026R0 build() {
      return new QueryInsightsGroupByV2026R0(this);
    }
  }
}
