package com.box.sdkgen.schemas.shieldinformationbarrierreports;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarrierreport.ShieldInformationBarrierReport;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class ShieldInformationBarrierReports extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  protected List<ShieldInformationBarrierReport> entries;

  public ShieldInformationBarrierReports() {
    super();
  }

  protected ShieldInformationBarrierReports(ShieldInformationBarrierReportsBuilder builder) {
    super();
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.entries = builder.entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  public List<ShieldInformationBarrierReport> getEntries() {
    return entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierReports casted = (ShieldInformationBarrierReports) o;
    return Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, nextMarker, entries);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierReports{"
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class ShieldInformationBarrierReportsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected List<ShieldInformationBarrierReport> entries;

    public ShieldInformationBarrierReportsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public ShieldInformationBarrierReportsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public ShieldInformationBarrierReportsBuilder entries(
        List<ShieldInformationBarrierReport> entries) {
      this.entries = entries;
      return this;
    }

    public ShieldInformationBarrierReports build() {
      return new ShieldInformationBarrierReports(this);
    }
  }
}
