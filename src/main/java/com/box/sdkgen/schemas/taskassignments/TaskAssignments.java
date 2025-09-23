package com.box.sdkgen.schemas.taskassignments;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.taskassignment.TaskAssignment;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TaskAssignments extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected List<TaskAssignment> entries;

  public TaskAssignments() {
    super();
  }

  protected TaskAssignments(Builder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public List<TaskAssignment> getEntries() {
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
    TaskAssignments casted = (TaskAssignments) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "TaskAssignments{"
        + "totalCount='"
        + totalCount
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long totalCount;

    protected List<TaskAssignment> entries;

    public Builder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public Builder entries(List<TaskAssignment> entries) {
      this.entries = entries;
      return this;
    }

    public TaskAssignments build() {
      return new TaskAssignments(this);
    }
  }
}
