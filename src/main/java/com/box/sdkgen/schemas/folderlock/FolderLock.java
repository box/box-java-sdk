package com.box.sdkgen.schemas.folderlock;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderLock extends SerializableObject {

  protected FolderMini folder;

  protected String id;

  protected String type;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonProperty("locked_operations")
  protected FolderLockLockedOperationsField lockedOperations;

  @JsonProperty("lock_type")
  protected String lockType;

  public FolderLock() {
    super();
  }

  protected FolderLock(Builder builder) {
    super();
    this.folder = builder.folder;
    this.id = builder.id;
    this.type = builder.type;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.lockedOperations = builder.lockedOperations;
    this.lockType = builder.lockType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getCreatedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected FolderMini folder;

    protected String id;

    protected String type;

    protected UserBase createdBy;

    protected OffsetDateTime createdAt;

    protected FolderLockLockedOperationsField lockedOperations;

    protected String lockType;

    public Builder folder(FolderMini folder) {
      this.folder = folder;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder lockedOperations(FolderLockLockedOperationsField lockedOperations) {
      this.lockedOperations = lockedOperations;
      return this;
    }

    public Builder lockType(String lockType) {
      this.lockType = lockType;
      return this;
    }

    public FolderLock build() {
      return new FolderLock(this);
    }
  }
}
