package com.box.sdkgen.managers.folderlocks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateFolderLockRequestBody extends SerializableObject {

  /**
   * The operations to lock for the folder. If `locked_operations` is included in the request, both
   * `move` and `delete` must also be included and both set to `true`.
   */
  @JsonProperty("locked_operations")
  protected CreateFolderLockRequestBodyLockedOperationsField lockedOperations;

  /** The folder to apply the lock to. */
  protected final CreateFolderLockRequestBodyFolderField folder;

  public CreateFolderLockRequestBody(
      @JsonProperty("folder") CreateFolderLockRequestBodyFolderField folder) {
    super();
    this.folder = folder;
  }

  protected CreateFolderLockRequestBody(Builder builder) {
    super();
    this.lockedOperations = builder.lockedOperations;
    this.folder = builder.folder;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public CreateFolderLockRequestBodyLockedOperationsField getLockedOperations() {
    return lockedOperations;
  }

  public CreateFolderLockRequestBodyFolderField getFolder() {
    return folder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateFolderLockRequestBody casted = (CreateFolderLockRequestBody) o;
    return Objects.equals(lockedOperations, casted.lockedOperations)
        && Objects.equals(folder, casted.folder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lockedOperations, folder);
  }

  @Override
  public String toString() {
    return "CreateFolderLockRequestBody{"
        + "lockedOperations='"
        + lockedOperations
        + '\''
        + ", "
        + "folder='"
        + folder
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected CreateFolderLockRequestBodyLockedOperationsField lockedOperations;

    protected final CreateFolderLockRequestBodyFolderField folder;

    public Builder(CreateFolderLockRequestBodyFolderField folder) {
      super();
      this.folder = folder;
    }

    public Builder lockedOperations(
        CreateFolderLockRequestBodyLockedOperationsField lockedOperations) {
      this.lockedOperations = lockedOperations;
      return this;
    }

    public CreateFolderLockRequestBody build() {
      return new CreateFolderLockRequestBody(this);
    }
  }
}
