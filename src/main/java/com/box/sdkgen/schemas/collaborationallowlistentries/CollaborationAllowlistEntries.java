package com.box.sdkgen.schemas.collaborationallowlistentries;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.collaborationallowlistentry.CollaborationAllowlistEntry;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class CollaborationAllowlistEntries extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<CollaborationAllowlistEntry> entries;

  public CollaborationAllowlistEntries() {
    super();
  }

  protected CollaborationAllowlistEntries(CollaborationAllowlistEntriesBuilder builder) {
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

  public List<CollaborationAllowlistEntry> getEntries() {
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
    CollaborationAllowlistEntries casted = (CollaborationAllowlistEntries) o;
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
    return "CollaborationAllowlistEntries{"
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

  public static class CollaborationAllowlistEntriesBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<CollaborationAllowlistEntry> entries;

    public CollaborationAllowlistEntriesBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public CollaborationAllowlistEntriesBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public CollaborationAllowlistEntriesBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public CollaborationAllowlistEntriesBuilder entries(List<CollaborationAllowlistEntry> entries) {
      this.entries = entries;
      return this;
    }

    public CollaborationAllowlistEntries build() {
      return new CollaborationAllowlistEntries(this);
    }
  }
}
