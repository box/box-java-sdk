package com.box.sdkgen.schemas.v2025r0.externaluserssubmitdeletejobrequestv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.userreferencev2025r0.UserReferenceV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** Request to submit a job to delete external users from the current enterprise. */
@JsonFilter("nullablePropertyFilter")
public class ExternalUsersSubmitDeleteJobRequestV2025R0 extends SerializableObject {

  /** List of external users to delete. */
  @JsonProperty("external_users")
  protected final List<UserReferenceV2025R0> externalUsers;

  public ExternalUsersSubmitDeleteJobRequestV2025R0(
      @JsonProperty("external_users") List<UserReferenceV2025R0> externalUsers) {
    super();
    this.externalUsers = externalUsers;
  }

  public List<UserReferenceV2025R0> getExternalUsers() {
    return externalUsers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExternalUsersSubmitDeleteJobRequestV2025R0 casted =
        (ExternalUsersSubmitDeleteJobRequestV2025R0) o;
    return Objects.equals(externalUsers, casted.externalUsers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalUsers);
  }

  @Override
  public String toString() {
    return "ExternalUsersSubmitDeleteJobRequestV2025R0{"
        + "externalUsers='"
        + externalUsers
        + '\''
        + "}";
  }
}
