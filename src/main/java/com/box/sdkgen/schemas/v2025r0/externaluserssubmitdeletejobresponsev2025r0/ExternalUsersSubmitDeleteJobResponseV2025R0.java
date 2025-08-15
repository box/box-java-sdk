package com.box.sdkgen.schemas.v2025r0.externaluserssubmitdeletejobresponsev2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.externaluserdeletionresultv2025r0.ExternalUserDeletionResultV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ExternalUsersSubmitDeleteJobResponseV2025R0 extends SerializableObject {

  protected final List<ExternalUserDeletionResultV2025R0> entries;

  public ExternalUsersSubmitDeleteJobResponseV2025R0(
      @JsonProperty("entries") List<ExternalUserDeletionResultV2025R0> entries) {
    super();
    this.entries = entries;
  }

  public List<ExternalUserDeletionResultV2025R0> getEntries() {
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
    ExternalUsersSubmitDeleteJobResponseV2025R0 casted =
        (ExternalUsersSubmitDeleteJobResponseV2025R0) o;
    return Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries);
  }

  @Override
  public String toString() {
    return "ExternalUsersSubmitDeleteJobResponseV2025R0{" + "entries='" + entries + '\'' + "}";
  }
}
