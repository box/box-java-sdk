package com.box.sdkgen.managers.folderlocks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class CreateFolderLockRequestBody extends SerializableObject {

  @JsonProperty("locked_operations")
  protected CreateFolderLockRequestBodyLockedOperationsField lockedOperations;

  protected final CreateFolderLockRequestBodyFolderField folder;

  public CreateFolderLockRequestBody(
      @JsonProperty("folder") CreateFolderLockRequestBodyFolderField folder) {
    super();
    this.folder = folder;
  }

  protected CreateFolderLockRequestBody(CreateFolderLockRequestBodyBuilder builder) {
    super();
    this.lockedOperations = builder.lockedOperations;
    this.folder = builder.folder;
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

  public static class CreateFolderLockRequestBodyBuilder {

    protected CreateFolderLockRequestBodyLockedOperationsField lockedOperations;

    protected final CreateFolderLockRequestBodyFolderField folder;

    public CreateFolderLockRequestBodyBuilder(CreateFolderLockRequestBodyFolderField folder) {
      this.folder = folder;
    }

    public CreateFolderLockRequestBodyBuilder lockedOperations(
        CreateFolderLockRequestBodyLockedOperationsField lockedOperations) {
      this.lockedOperations = lockedOperations;
      return this;
    }

    public CreateFolderLockRequestBody build() {
      return new CreateFolderLockRequestBody(this);
    }
  }
}
