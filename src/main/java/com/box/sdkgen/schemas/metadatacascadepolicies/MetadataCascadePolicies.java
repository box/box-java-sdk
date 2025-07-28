package com.box.sdkgen.schemas.metadatacascadepolicies;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatacascadepolicy.MetadataCascadePolicy;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataCascadePolicies extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  @JsonProperty("prev_marker")
  @Nullable
  protected String prevMarker;

  protected List<MetadataCascadePolicy> entries;

  public MetadataCascadePolicies() {
    super();
  }

  protected MetadataCascadePolicies(Builder builder) {
    super();
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.prevMarker = builder.prevMarker;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<MetadataCascadePolicy> entries;

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      this.markNullableFieldAsSet("next_marker");
      return this;
    }

    public Builder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      this.markNullableFieldAsSet("prev_marker");
      return this;
    }

    public Builder entries(List<MetadataCascadePolicy> entries) {
      this.entries = entries;
      return this;
    }

    public MetadataCascadePolicies build() {
      return new MetadataCascadePolicies(this);
    }
  }
}
