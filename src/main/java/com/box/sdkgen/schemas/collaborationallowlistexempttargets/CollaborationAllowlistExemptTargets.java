package com.box.sdkgen.schemas.collaborationallowlistexempttargets;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.collaborationallowlistexempttarget.CollaborationAllowlistExemptTarget;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class CollaborationAllowlistExemptTargets extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<CollaborationAllowlistExemptTarget> entries;

  public CollaborationAllowlistExemptTargets() {
    super();
  }

  protected CollaborationAllowlistExemptTargets(
      CollaborationAllowlistExemptTargetsBuilder builder) {
    super();
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.prevMarker = builder.prevMarker;
    this.entries = builder.entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  public String getPrevMarker() {
    return prevMarker;
  }

  public List<CollaborationAllowlistExemptTarget> getEntries() {
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
    CollaborationAllowlistExemptTargets casted = (CollaborationAllowlistExemptTargets) o;
    return Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(prevMarker, casted.prevMarker)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, nextMarker, prevMarker, entries);
  }

  @Override
  public String toString() {
    return "CollaborationAllowlistExemptTargets{"
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + ", "
        + "prevMarker='"
        + prevMarker
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class CollaborationAllowlistExemptTargetsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<CollaborationAllowlistExemptTarget> entries;

    public CollaborationAllowlistExemptTargetsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public CollaborationAllowlistExemptTargetsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public CollaborationAllowlistExemptTargetsBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public CollaborationAllowlistExemptTargetsBuilder entries(
        List<CollaborationAllowlistExemptTarget> entries) {
      this.entries = entries;
      return this;
    }

    public CollaborationAllowlistExemptTargets build() {
      return new CollaborationAllowlistExemptTargets(this);
    }
  }
}
