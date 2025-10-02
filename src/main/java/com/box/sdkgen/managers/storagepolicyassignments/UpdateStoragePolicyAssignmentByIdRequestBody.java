package com.box.sdkgen.managers.storagepolicyassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateStoragePolicyAssignmentByIdRequestBody extends SerializableObject {

  /** The storage policy to assign to the user or enterprise. */
  @JsonProperty("storage_policy")
  protected final UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField storagePolicy;

  public UpdateStoragePolicyAssignmentByIdRequestBody(
      @JsonProperty("storage_policy")
          UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField storagePolicy) {
    super();
    this.storagePolicy = storagePolicy;
  }

  public UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField getStoragePolicy() {
    return storagePolicy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateStoragePolicyAssignmentByIdRequestBody casted =
        (UpdateStoragePolicyAssignmentByIdRequestBody) o;
    return Objects.equals(storagePolicy, casted.storagePolicy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storagePolicy);
  }

  @Override
  public String toString() {
    return "UpdateStoragePolicyAssignmentByIdRequestBody{"
        + "storagePolicy='"
        + storagePolicy
        + '\''
        + "}";
  }
}
