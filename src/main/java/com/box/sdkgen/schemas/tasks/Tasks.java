package com.box.sdkgen.schemas.tasks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.task.Task;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A list of tasks. */
@JsonFilter("nullablePropertyFilter")
public class Tasks extends SerializableObject {

  /**
   * One greater than the offset of the last entry in the entire collection. The total number of
   * entries in the collection may be less than `total_count`.
   */
  @JsonProperty("total_count")
  protected Long totalCount;

  /** A list of tasks. */
  protected List<Task> entries;

  public Tasks() {
    super();
  }

  protected Tasks(Builder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public List<Task> getEntries() {
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
    Tasks casted = (Tasks) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "Tasks{"
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

    protected List<Task> entries;

    public Builder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public Builder entries(List<Task> entries) {
      this.entries = entries;
      return this;
    }

    public Tasks build() {
      return new Tasks(this);
    }
  }
}
