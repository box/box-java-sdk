package com.box.sdkgen.schemas.metadatacascadepolicies;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatacascadepolicy.MetadataCascadePolicy;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class MetadataCascadePolicies extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<MetadataCascadePolicy> entries;

  public MetadataCascadePolicies() {
    super();
  }

  protected MetadataCascadePolicies(MetadataCascadePoliciesBuilder builder) {
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

  public List<MetadataCascadePolicy> getEntries() {
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
    MetadataCascadePolicies casted = (MetadataCascadePolicies) o;
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
    return "MetadataCascadePolicies{"
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

  public static class MetadataCascadePoliciesBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<MetadataCascadePolicy> entries;

    public MetadataCascadePoliciesBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public MetadataCascadePoliciesBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public MetadataCascadePoliciesBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public MetadataCascadePoliciesBuilder entries(List<MetadataCascadePolicy> entries) {
      this.entries = entries;
      return this;
    }

    public MetadataCascadePolicies build() {
      return new MetadataCascadePolicies(this);
    }
  }
}
