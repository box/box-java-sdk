package com.box.sdkgen.schemas.storagepolicyassignment;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.storagepolicymini.StoragePolicyMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The assignment of a storage policy to a user or enterprise. */
@JsonFilter("nullablePropertyFilter")
public class StoragePolicyAssignment extends SerializableObject {

  /** The unique identifier for a storage policy assignment. */
  protected final String id;

  /** The value will always be `storage_policy_assignment`. */
  @JsonDeserialize(
      using = StoragePolicyAssignmentTypeField.StoragePolicyAssignmentTypeFieldDeserializer.class)
  @JsonSerialize(
      using = StoragePolicyAssignmentTypeField.StoragePolicyAssignmentTypeFieldSerializer.class)
  protected EnumWrapper<StoragePolicyAssignmentTypeField> type;

  @JsonProperty("storage_policy")
  protected StoragePolicyMini storagePolicy;

  @JsonProperty("assigned_to")
  protected StoragePolicyAssignmentAssignedToField assignedTo;

  public StoragePolicyAssignment(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<StoragePolicyAssignmentTypeField>(
            StoragePolicyAssignmentTypeField.STORAGE_POLICY_ASSIGNMENT);
  }

  protected StoragePolicyAssignment(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.storagePolicy = builder.storagePolicy;
    this.assignedTo = builder.assignedTo;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<StoragePolicyAssignmentTypeField> getType() {
    return type;
  }

  public StoragePolicyMini getStoragePolicy() {
    return storagePolicy;
  }

  public StoragePolicyAssignmentAssignedToField getAssignedTo() {
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
    StoragePolicyAssignment casted = (StoragePolicyAssignment) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(storagePolicy, casted.storagePolicy)
        && Objects.equals(assignedTo, casted.assignedTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, storagePolicy, assignedTo);
  }

  @Override
  public String toString() {
    return "StoragePolicyAssignment{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "storagePolicy='"
        + storagePolicy
        + '\''
        + ", "
        + "assignedTo='"
        + assignedTo
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<StoragePolicyAssignmentTypeField> type;

    protected StoragePolicyMini storagePolicy;

    protected StoragePolicyAssignmentAssignedToField assignedTo;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(StoragePolicyAssignmentTypeField type) {
      this.type = new EnumWrapper<StoragePolicyAssignmentTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<StoragePolicyAssignmentTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder storagePolicy(StoragePolicyMini storagePolicy) {
      this.storagePolicy = storagePolicy;
      return this;
    }

    public Builder assignedTo(StoragePolicyAssignmentAssignedToField assignedTo) {
      this.assignedTo = assignedTo;
      return this;
    }

    public StoragePolicyAssignment build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<StoragePolicyAssignmentTypeField>(
                StoragePolicyAssignmentTypeField.STORAGE_POLICY_ASSIGNMENT);
      }
      return new StoragePolicyAssignment(this);
    }
  }
}
