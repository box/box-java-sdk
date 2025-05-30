package com.box.sdkgen.schemas.retentionpolicyassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.retentionpolicyassignment.RetentionPolicyAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class RetentionPolicyAssignments extends SerializableObject {

  protected List<RetentionPolicyAssignment> entries;

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  public RetentionPolicyAssignments() {
    super();
  }

  protected RetentionPolicyAssignments(RetentionPolicyAssignmentsBuilder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
  }

  public List<RetentionPolicyAssignment> getEntries() {
    return entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RetentionPolicyAssignments casted = (RetentionPolicyAssignments) o;
    return Objects.equals(entries, casted.entries)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, limit, nextMarker);
  }

  @Override
  public String toString() {
    return "RetentionPolicyAssignments{"
        + "entries='"
        + entries
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + "}";
  }

  public static class RetentionPolicyAssignmentsBuilder {

    protected List<RetentionPolicyAssignment> entries;

    protected Long limit;

    protected String nextMarker;

    public RetentionPolicyAssignmentsBuilder entries(List<RetentionPolicyAssignment> entries) {
      this.entries = entries;
      return this;
    }

    public RetentionPolicyAssignmentsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public RetentionPolicyAssignmentsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public RetentionPolicyAssignments build() {
      return new RetentionPolicyAssignments(this);
    }
  }
}
