package com.box.sdkgen.schemas.appitemassociations;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.appitemassociation.AppItemAssociation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class AppItemAssociations extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<AppItemAssociation> entries;

  public AppItemAssociations() {
    super();
  }

  protected AppItemAssociations(AppItemAssociationsBuilder builder) {
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

  public List<AppItemAssociation> getEntries() {
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
    AppItemAssociations casted = (AppItemAssociations) o;
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
    return "AppItemAssociations{"
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

  public static class AppItemAssociationsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<AppItemAssociation> entries;

    public AppItemAssociationsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public AppItemAssociationsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public AppItemAssociationsBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public AppItemAssociationsBuilder entries(List<AppItemAssociation> entries) {
      this.entries = entries;
      return this;
    }

    public AppItemAssociations build() {
      return new AppItemAssociations(this);
    }
  }
}
