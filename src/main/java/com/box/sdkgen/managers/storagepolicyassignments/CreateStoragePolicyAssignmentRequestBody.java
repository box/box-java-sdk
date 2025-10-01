package com.box.sdkgen.managers.storagepolicyassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateStoragePolicyAssignmentRequestBody extends SerializableObject {

  /** The storage policy to assign to the user or enterprise. */
  @JsonProperty("storage_policy")
  protected final CreateStoragePolicyAssignmentRequestBodyStoragePolicyField storagePolicy;

  /** The user or enterprise to assign the storage policy to. */
  @JsonProperty("assigned_to")
  protected final CreateStoragePolicyAssignmentRequestBodyAssignedToField assignedTo;

  public CreateStoragePolicyAssignmentRequestBody(
      @JsonProperty("storage_policy")
          CreateStoragePolicyAssignmentRequestBodyStoragePolicyField storagePolicy,
      @JsonProperty("assigned_to")
          CreateStoragePolicyAssignmentRequestBodyAssignedToField assignedTo) {
    super();
    this.storagePolicy = storagePolicy;
    this.assignedTo = assignedTo;
  }

  public CreateStoragePolicyAssignmentRequestBodyStoragePolicyField getStoragePolicy() {
    return storagePolicy;
  }

  public CreateStoragePolicyAssignmentRequestBodyAssignedToField getAssignedTo() {
    return assignedTo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateStoragePolicyAssignmentRequestBody casted = (CreateStoragePolicyAssignmentRequestBody) o;
    return Objects.equals(storagePolicy, casted.storagePolicy)
        && Objects.equals(assignedTo, casted.assignedTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storagePolicy, assignedTo);
  }

  @Override
  public String toString() {
    return "CreateStoragePolicyAssignmentRequestBody{"
        + "storagePolicy='"
        + storagePolicy
        + '\''
        + ", "
        + "assignedTo='"
        + assignedTo
        + '\''
        + "}";
  }
}
