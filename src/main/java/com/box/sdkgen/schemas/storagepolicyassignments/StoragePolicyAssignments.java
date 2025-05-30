package com.box.sdkgen.schemas.storagepolicyassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.storagepolicyassignment.StoragePolicyAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class StoragePolicyAssignments extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<StoragePolicyAssignment> entries;

  public StoragePolicyAssignments() {
    super();
  }

  protected StoragePolicyAssignments(StoragePolicyAssignmentsBuilder builder) {
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

  public List<StoragePolicyAssignment> getEntries() {
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
    StoragePolicyAssignments casted = (StoragePolicyAssignments) o;
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
    return "StoragePolicyAssignments{"
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

  public static class StoragePolicyAssignmentsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<StoragePolicyAssignment> entries;

    public StoragePolicyAssignmentsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public StoragePolicyAssignmentsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public StoragePolicyAssignmentsBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public StoragePolicyAssignmentsBuilder entries(List<StoragePolicyAssignment> entries) {
      this.entries = entries;
      return this;
    }

    public StoragePolicyAssignments build() {
      return new StoragePolicyAssignments(this);
    }
  }
}
