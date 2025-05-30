package com.box.sdkgen.schemas.folderlock;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class FolderLock extends SerializableObject {

  protected FolderMini folder;

  protected String id;

  protected String type;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("locked_operations")
  protected FolderLockLockedOperationsField lockedOperations;

  @JsonProperty("lock_type")
  protected String lockType;

  public FolderLock() {
    super();
  }

  protected FolderLock(FolderLockBuilder builder) {
    super();
    this.folder = builder.folder;
    this.id = builder.id;
    this.type = builder.type;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.lockedOperations = builder.lockedOperations;
    this.lockType = builder.lockType;
  }

  public FolderMini getFolder() {
    return folder;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public FolderLockLockedOperationsField getLockedOperations() {
    return lockedOperations;
  }

  public String getLockType() {
    return lockType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderLock casted = (FolderLock) o;
    return Objects.equals(folder, casted.folder)
        && Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(lockedOperations, casted.lockedOperations)
        && Objects.equals(lockType, casted.lockType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(folder, id, type, createdBy, createdAt, lockedOperations, lockType);
  }

  @Override
  public String toString() {
    return "FolderLock{"
        + "folder='"
        + folder
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "lockedOperations='"
        + lockedOperations
        + '\''
        + ", "
        + "lockType='"
        + lockType
        + '\''
        + "}";
  }

  public static class FolderLockBuilder {

    protected FolderMini folder;

    protected String id;

    protected String type;

    protected UserBase createdBy;

    protected String createdAt;

    protected FolderLockLockedOperationsField lockedOperations;

    protected String lockType;

    public FolderLockBuilder folder(FolderMini folder) {
      this.folder = folder;
      return this;
    }

    public FolderLockBuilder id(String id) {
      this.id = id;
      return this;
    }

    public FolderLockBuilder type(String type) {
      this.type = type;
      return this;
    }

    public FolderLockBuilder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public FolderLockBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public FolderLockBuilder lockedOperations(FolderLockLockedOperationsField lockedOperations) {
      this.lockedOperations = lockedOperations;
      return this;
    }

    public FolderLockBuilder lockType(String lockType) {
      this.lockType = lockType;
      return this;
    }

    public FolderLock build() {
      return new FolderLock(this);
    }
  }
}
