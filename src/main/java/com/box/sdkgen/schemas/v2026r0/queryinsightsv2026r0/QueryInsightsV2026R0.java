package com.box.sdkgen.schemas.v2026r0.queryinsightsv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.queryinsightentryv2026r0.QueryInsightEntryV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** The computed results of an insights request, as a list of insight entries. */
@JsonFilter("nullablePropertyFilter")
public class QueryInsightsV2026R0 extends SerializableObject {

  /**
   * The list of computed insight entries. Each entry corresponds to a group, the overall dataset,
   * or the aggregate of groups outside the top results.
   */
  protected final List<QueryInsightEntryV2026R0> insights;

  public QueryInsightsV2026R0(@JsonProperty("insights") List<QueryInsightEntryV2026R0> insights) {
    super();
    this.insights = insights;
  }

  public List<QueryInsightEntryV2026R0> getInsights() {
    return insights;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryInsightsV2026R0 casted = (QueryInsightsV2026R0) o;
    return Objects.equals(insights, casted.insights);
  }

  @Override
  public int hashCode() {
    return Objects.hash(insights);
  }

  @Override
  public String toString() {
    return "QueryInsightsV2026R0{" + "insights='" + insights + '\'' + "}";
  }
}
