package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictions;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestriction.ShieldInformationBarrierSegmentRestriction;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class ShieldInformationBarrierSegmentRestrictions extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  protected List<ShieldInformationBarrierSegmentRestriction> entries;

  public ShieldInformationBarrierSegmentRestrictions() {
    super();
  }

  protected ShieldInformationBarrierSegmentRestrictions(
      ShieldInformationBarrierSegmentRestrictionsBuilder builder) {
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

  public List<ShieldInformationBarrierSegmentRestriction> getEntries() {
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
    ShieldInformationBarrierSegmentRestrictions casted =
        (ShieldInformationBarrierSegmentRestrictions) o;
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
    return "ShieldInformationBarrierSegmentRestrictions{"
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

  public static class ShieldInformationBarrierSegmentRestrictionsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected List<ShieldInformationBarrierSegmentRestriction> entries;

    public ShieldInformationBarrierSegmentRestrictionsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionsBuilder entries(
        List<ShieldInformationBarrierSegmentRestriction> entries) {
      this.entries = entries;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictions build() {
      return new ShieldInformationBarrierSegmentRestrictions(this);
    }
  }
}
