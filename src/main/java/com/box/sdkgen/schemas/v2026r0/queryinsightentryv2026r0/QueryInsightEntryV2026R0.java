package com.box.sdkgen.schemas.v2026r0.queryinsightentryv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.queryinsightmetricresultv2026r0.QueryInsightMetricResultV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A single computed insight entry, containing its grouping keys (if applicable) and the computed
 * metrics.
 */
@JsonFilter("nullablePropertyFilter")
public class QueryInsightEntryV2026R0 extends SerializableObject {

  /**
   * The grouping key values associated with the entry. Contains one value per `group_by` field for
   * `group` entries, and is empty for `overall` and `other` entries.
   */
  protected final List<String> key;

  /** The type of insight entry, indicating how the associated metrics are aggregated. */
  @JsonDeserialize(
      using = QueryInsightEntryV2026R0TypeField.QueryInsightEntryV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = QueryInsightEntryV2026R0TypeField.QueryInsightEntryV2026R0TypeFieldSerializer.class)
  protected final EnumWrapper<QueryInsightEntryV2026R0TypeField> type;

  /**
   * A map of metric aliases to their computed results. For `other` entries, the count is reported
   * under the `totalCountBeyondTopGroups` key.
   */
  protected final Map<String, QueryInsightMetricResultV2026R0> metrics;

  public QueryInsightEntryV2026R0(
      List<String> key,
      QueryInsightEntryV2026R0TypeField type,
      Map<String, QueryInsightMetricResultV2026R0> metrics) {
    super();
    this.key = key;
    this.type = new EnumWrapper<QueryInsightEntryV2026R0TypeField>(type);
    this.metrics = metrics;
  }

  public QueryInsightEntryV2026R0(
      @JsonProperty("key") List<String> key,
      @JsonProperty("type") EnumWrapper<QueryInsightEntryV2026R0TypeField> type,
      @JsonProperty("metrics") Map<String, QueryInsightMetricResultV2026R0> metrics) {
    super();
    this.key = key;
    this.type = type;
    this.metrics = metrics;
  }

  public List<String> getKey() {
    return key;
  }

  public EnumWrapper<QueryInsightEntryV2026R0TypeField> getType() {
    return type;
  }

  public Map<String, QueryInsightMetricResultV2026R0> getMetrics() {
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
    QueryInsightEntryV2026R0 casted = (QueryInsightEntryV2026R0) o;
    return Objects.equals(key, casted.key)
        && Objects.equals(type, casted.type)
        && Objects.equals(metrics, casted.metrics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, type, metrics);
  }

  @Override
  public String toString() {
    return "QueryInsightEntryV2026R0{"
        + "key='"
        + key
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "metrics='"
        + metrics
        + '\''
        + "}";
  }
}
