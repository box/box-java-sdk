package com.box.sdkgen.schemas.v2026r0.queryinsightsrequestbodyv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.queryinsightsmetricdefinitionv2026r0.QueryInsightsMetricDefinitionV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;

/** Request body describing the filtering, grouping, and metrics for an insights computation. */
@JsonFilter("nullablePropertyFilter")
public class QueryInsightsRequestBodyV2026R0 extends SerializableObject {

  /**
   * The filtering and grouping definition. Filters are applied first, followed by grouping, before
   * metrics are computed.
   */
  protected final QueryInsightsRequestBodyV2026R0QueryField query;

  /**
   * A map of user-defined metric aliases to their definitions. A maximum of 10 metrics may be
   * defined. Each alias must be a unique, non-empty string of up to 256 characters, containing only
   * letters, digits, `_`, `-`, or `.`, and must not start with a digit, `_`, `-`, or `.`. May be
   * empty to request only a total count.
   */
  protected final Map<String, QueryInsightsMetricDefinitionV2026R0> metrics;

  public QueryInsightsRequestBodyV2026R0(
      @JsonProperty("query") QueryInsightsRequestBodyV2026R0QueryField query,
      @JsonProperty("metrics") Map<String, QueryInsightsMetricDefinitionV2026R0> metrics) {
    super();
    this.query = query;
    this.metrics = metrics;
  }

  public QueryInsightsRequestBodyV2026R0QueryField getQuery() {
    return query;
  }

  public Map<String, QueryInsightsMetricDefinitionV2026R0> getMetrics() {
    return metrics;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryInsightsRequestBodyV2026R0 casted = (QueryInsightsRequestBodyV2026R0) o;
    return Objects.equals(query, casted.query) && Objects.equals(metrics, casted.metrics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(query, metrics);
  }

  @Override
  public String toString() {
    return "QueryInsightsRequestBodyV2026R0{"
        + "query='"
        + query
        + '\''
        + ", "
        + "metrics='"
        + metrics
        + '\''
        + "}";
  }
}
