package com.box.sdkgen.schemas.v2026r0.queryinsightsmetricdefinitionv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Defines a single metric to compute, including the aggregation function and the field it is
 * applied to.
 */
@JsonFilter("nullablePropertyFilter")
public class QueryInsightsMetricDefinitionV2026R0 extends SerializableObject {

  /** The aggregation function to apply. */
  @JsonDeserialize(
      using =
          QueryInsightsMetricDefinitionV2026R0TypeField
              .QueryInsightsMetricDefinitionV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          QueryInsightsMetricDefinitionV2026R0TypeField
              .QueryInsightsMetricDefinitionV2026R0TypeFieldSerializer.class)
  protected final EnumWrapper<QueryInsightsMetricDefinitionV2026R0TypeField> type;

  /** The fully qualified field name on which the metric is computed. */
  protected final String field;

  public QueryInsightsMetricDefinitionV2026R0(
      QueryInsightsMetricDefinitionV2026R0TypeField type, String field) {
    super();
    this.type = new EnumWrapper<QueryInsightsMetricDefinitionV2026R0TypeField>(type);
    this.field = field;
  }

  public QueryInsightsMetricDefinitionV2026R0(
      @JsonProperty("type") EnumWrapper<QueryInsightsMetricDefinitionV2026R0TypeField> type,
      @JsonProperty("field") String field) {
    super();
    this.type = type;
    this.field = field;
  }

  public EnumWrapper<QueryInsightsMetricDefinitionV2026R0TypeField> getType() {
    return type;
  }

  public String getField() {
    return field;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryInsightsMetricDefinitionV2026R0 casted = (QueryInsightsMetricDefinitionV2026R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(field, casted.field);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, field);
  }

  @Override
  public String toString() {
    return "QueryInsightsMetricDefinitionV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "field='"
        + field
        + '\''
        + "}";
  }
}
