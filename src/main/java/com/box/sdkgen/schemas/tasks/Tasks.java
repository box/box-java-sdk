package com.box.sdkgen.schemas.tasks;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.task.Task;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class Tasks extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected List<Task> entries;

  public Tasks() {
    super();
  }

  protected Tasks(TasksBuilder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.entries = builder.entries;
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

  public static class TasksBuilder {

    protected Long totalCount;

    protected List<Task> entries;

    public TasksBuilder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public TasksBuilder entries(List<Task> entries) {
      this.entries = entries;
      return this;
    }

    public Tasks build() {
      return new Tasks(this);
    }
  }
}
