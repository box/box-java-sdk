package com.box.sdkgen.schemas.legalholdpolicyassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.legalholdpolicyassignment.LegalHoldPolicyAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class LegalHoldPolicyAssignments extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<LegalHoldPolicyAssignment> entries;

  public LegalHoldPolicyAssignments() {
    super();
  }

  protected LegalHoldPolicyAssignments(LegalHoldPolicyAssignmentsBuilder builder) {
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

  public List<LegalHoldPolicyAssignment> getEntries() {
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
    LegalHoldPolicyAssignments casted = (LegalHoldPolicyAssignments) o;
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
    return "LegalHoldPolicyAssignments{"
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

  public static class LegalHoldPolicyAssignmentsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<LegalHoldPolicyAssignment> entries;

    public LegalHoldPolicyAssignmentsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public LegalHoldPolicyAssignmentsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public LegalHoldPolicyAssignmentsBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public LegalHoldPolicyAssignmentsBuilder entries(List<LegalHoldPolicyAssignment> entries) {
      this.entries = entries;
      return this;
    }

    public LegalHoldPolicyAssignments build() {
      return new LegalHoldPolicyAssignments(this);
    }
  }
}
