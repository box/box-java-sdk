package com.box.sdkgen.schemas.v2026r0.queryinsightmetricresultv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;

/** The computed result for a single metric, including the metric type and its computed value(s). */
@JsonFilter("nullablePropertyFilter")
public class QueryInsightMetricResultV2026R0 extends SerializableObject {

  /** The metric type that was computed. */
  protected final String type;

  /**
   * The computed metric result(s), keyed by the metric function (for example `sum`, `avg`, `min`,
   * `max`, or `count`).
   */
  protected final Map<String, Double> values;

  public QueryInsightMetricResultV2026R0(
      @JsonProperty("type") String type, @JsonProperty("values") Map<String, Double> values) {
    super();
    this.type = type;
    this.values = values;
  }

  public String getType() {
    return type;
  }

  public Map<String, Double> getValues() {
    return values;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryInsightMetricResultV2026R0 casted = (QueryInsightMetricResultV2026R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(values, casted.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, values);
  }

  @Override
  public String toString() {
    return "QueryInsightMetricResultV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "values='"
        + values
        + '\''
        + "}";
  }
}
