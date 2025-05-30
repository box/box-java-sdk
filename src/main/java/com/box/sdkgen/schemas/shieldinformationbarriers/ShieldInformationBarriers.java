package com.box.sdkgen.schemas.shieldinformationbarriers;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarrier.ShieldInformationBarrier;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class ShieldInformationBarriers extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  protected List<ShieldInformationBarrier> entries;

  public ShieldInformationBarriers() {
    super();
  }

  protected ShieldInformationBarriers(ShieldInformationBarriersBuilder builder) {
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

  public List<ShieldInformationBarrier> getEntries() {
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
    ShieldInformationBarriers casted = (ShieldInformationBarriers) o;
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
    return "ShieldInformationBarriers{"
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

  public static class ShieldInformationBarriersBuilder {

    protected Long limit;

    protected String nextMarker;

    protected List<ShieldInformationBarrier> entries;

    public ShieldInformationBarriersBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public ShieldInformationBarriersBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public ShieldInformationBarriersBuilder entries(List<ShieldInformationBarrier> entries) {
      this.entries = entries;
      return this;
    }

    public ShieldInformationBarriers build() {
      return new ShieldInformationBarriers(this);
    }
  }
}
