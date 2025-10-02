package com.box.sdkgen.schemas.retentionpolicyassignments;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.retentionpolicyassignment.RetentionPolicyAssignment;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A list of retention policy assignments. */
@JsonFilter("nullablePropertyFilter")
public class RetentionPolicyAssignments extends SerializableObject {

  /** A list of retention policy assignments. */
  protected List<RetentionPolicyAssignment> entries;

  /**
   * The limit that was used for these entries. This will be the same as the `limit` query parameter
   * unless that value exceeded the maximum value allowed. The maximum value varies by API.
   */
  protected Long limit;

  /** The marker for the start of the next page of results. */
  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  public RetentionPolicyAssignments() {
    super();
  }

  protected RetentionPolicyAssignments(Builder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected List<RetentionPolicyAssignment> entries;

    protected Long limit;

    protected String nextMarker;

    public Builder entries(List<RetentionPolicyAssignment> entries) {
      this.entries = entries;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      this.markNullableFieldAsSet("next_marker");
      return this;
    }

    public RetentionPolicyAssignments build() {
      return new RetentionPolicyAssignments(this);
    }
  }
}
