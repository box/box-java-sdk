package com.box.sdkgen.schemas.shieldinformationbarriersegments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarriersegment.ShieldInformationBarrierSegment;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class ShieldInformationBarrierSegments extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  protected List<ShieldInformationBarrierSegment> entries;

  public ShieldInformationBarrierSegments() {
    super();
  }

  protected ShieldInformationBarrierSegments(ShieldInformationBarrierSegmentsBuilder builder) {
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

  public List<ShieldInformationBarrierSegment> getEntries() {
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
    ShieldInformationBarrierSegments casted = (ShieldInformationBarrierSegments) o;
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
    return "ShieldInformationBarrierSegments{"
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

  public static class ShieldInformationBarrierSegmentsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected List<ShieldInformationBarrierSegment> entries;

    public ShieldInformationBarrierSegmentsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public ShieldInformationBarrierSegmentsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public ShieldInformationBarrierSegmentsBuilder entries(
        List<ShieldInformationBarrierSegment> entries) {
      this.entries = entries;
      return this;
    }

    public ShieldInformationBarrierSegments build() {
      return new ShieldInformationBarrierSegments(this);
    }
  }
}
