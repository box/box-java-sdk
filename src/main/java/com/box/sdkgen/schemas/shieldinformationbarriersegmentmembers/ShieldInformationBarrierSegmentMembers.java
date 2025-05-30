package com.box.sdkgen.schemas.shieldinformationbarriersegmentmembers;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmember.ShieldInformationBarrierSegmentMember;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class ShieldInformationBarrierSegmentMembers extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  protected List<ShieldInformationBarrierSegmentMember> entries;

  public ShieldInformationBarrierSegmentMembers() {
    super();
  }

  protected ShieldInformationBarrierSegmentMembers(
      ShieldInformationBarrierSegmentMembersBuilder builder) {
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

  public List<ShieldInformationBarrierSegmentMember> getEntries() {
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
    ShieldInformationBarrierSegmentMembers casted = (ShieldInformationBarrierSegmentMembers) o;
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
    return "ShieldInformationBarrierSegmentMembers{"
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

  public static class ShieldInformationBarrierSegmentMembersBuilder {

    protected Long limit;

    protected String nextMarker;

    protected List<ShieldInformationBarrierSegmentMember> entries;

    public ShieldInformationBarrierSegmentMembersBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public ShieldInformationBarrierSegmentMembersBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public ShieldInformationBarrierSegmentMembersBuilder entries(
        List<ShieldInformationBarrierSegmentMember> entries) {
      this.entries = entries;
      return this;
    }

    public ShieldInformationBarrierSegmentMembers build() {
      return new ShieldInformationBarrierSegmentMembers(this);
    }
  }
}
