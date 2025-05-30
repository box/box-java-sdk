package com.box.sdkgen.schemas.storagepolicies;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.storagepolicy.StoragePolicy;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class StoragePolicies extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<StoragePolicy> entries;

  public StoragePolicies() {
    super();
  }

  protected StoragePolicies(StoragePoliciesBuilder builder) {
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

  public List<StoragePolicy> getEntries() {
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
    StoragePolicies casted = (StoragePolicies) o;
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
    return "StoragePolicies{"
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

  public static class StoragePoliciesBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<StoragePolicy> entries;

    public StoragePoliciesBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public StoragePoliciesBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public StoragePoliciesBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public StoragePoliciesBuilder entries(List<StoragePolicy> entries) {
      this.entries = entries;
      return this;
    }

    public StoragePolicies build() {
      return new StoragePolicies(this);
    }
  }
}
