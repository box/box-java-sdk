package com.box.sdkgen.managers.sessiontermination;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TerminateUsersSessionsRequestBody extends SerializableObject {

  /** A list of user IDs. */
  @JsonProperty("user_ids")
  protected final List<String> userIds;

  /** A list of user logins. */
  @JsonProperty("user_logins")
  protected final List<String> userLogins;

  public TerminateUsersSessionsRequestBody(
      @JsonProperty("user_ids") List<String> userIds,
      @JsonProperty("user_logins") List<String> userLogins) {
    super();
    this.userIds = userIds;
    this.userLogins = userLogins;
  }

  public List<String> getUserIds() {
    return userIds;
  }

  public List<String> getUserLogins() {
    return userLogins;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminateUsersSessionsRequestBody casted = (TerminateUsersSessionsRequestBody) o;
    return Objects.equals(userIds, casted.userIds) && Objects.equals(userLogins, casted.userLogins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userIds, userLogins);
  }

  @Override
  public String toString() {
    return "TerminateUsersSessionsRequestBody{"
        + "userIds='"
        + userIds
        + '\''
        + ", "
        + "userLogins='"
        + userLogins
        + '\''
        + "}";
  }
}
