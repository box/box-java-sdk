package com.box.sdkgen.schemas.recentitems;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.recentitem.RecentItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class RecentItems extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<RecentItem> entries;

  public RecentItems() {
    super();
  }

  protected RecentItems(RecentItemsBuilder builder) {
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

  public List<RecentItem> getEntries() {
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
    RecentItems casted = (RecentItems) o;
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
    return "RecentItems{"
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

  public static class RecentItemsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<RecentItem> entries;

    public RecentItemsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public RecentItemsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public RecentItemsBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public RecentItemsBuilder entries(List<RecentItem> entries) {
      this.entries = entries;
      return this;
    }

    public RecentItems build() {
      return new RecentItems(this);
    }
  }
}
