package com.box.sdkgen.managers.sessiontermination;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TerminateGroupsSessionsRequestBody extends SerializableObject {

  /** A list of group IDs. */
  @JsonProperty("group_ids")
  protected final List<String> groupIds;

  public TerminateGroupsSessionsRequestBody(@JsonProperty("group_ids") List<String> groupIds) {
    super();
    this.groupIds = groupIds;
  }

  public List<String> getGroupIds() {
    return groupIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminateGroupsSessionsRequestBody casted = (TerminateGroupsSessionsRequestBody) o;
    return Objects.equals(groupIds, casted.groupIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupIds);
  }

  @Override
  public String toString() {
    return "TerminateGroupsSessionsRequestBody{" + "groupIds='" + groupIds + '\'' + "}";
  }
}
