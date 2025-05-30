package com.box.sdkgen.schemas.fileversion;

import com.box.sdkgen.schemas.fileversionbase.FileVersionBaseTypeField;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class FileVersion extends FileVersionMini {

  protected String name;

  protected Long size;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  @JsonProperty("modified_by")
  protected UserMini modifiedBy;

  @JsonProperty("trashed_at")
  protected String trashedAt;

  @JsonProperty("trashed_by")
  protected UserMini trashedBy;

  @JsonProperty("restored_at")
  protected String restoredAt;

  @JsonProperty("restored_by")
  protected UserMini restoredBy;

  @JsonProperty("purged_at")
  protected String purgedAt;

  @JsonProperty("uploader_display_name")
  protected String uploaderDisplayName;

  public FileVersion(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileVersion(FileVersionBuilder builder) {
    super(builder);
    this.name = builder.name;
    this.size = builder.size;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.modifiedBy = builder.modifiedBy;
    this.trashedAt = builder.trashedAt;
    this.trashedBy = builder.trashedBy;
    this.restoredAt = builder.restoredAt;
    this.restoredBy = builder.restoredBy;
    this.purgedAt = builder.purgedAt;
    this.uploaderDisplayName = builder.uploaderDisplayName;
  }

  public String getName() {
    return name;
  }

  public Long getSize() {
    return size;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public UserMini getModifiedBy() {
    return modifiedBy;
  }

  public String getTrashedAt() {
    return trashedAt;
  }

  public UserMini getTrashedBy() {
    return trashedBy;
  }

  public String getRestoredAt() {
    return restoredAt;
  }

  public UserMini getRestoredBy() {
    return restoredBy;
  }

  public String getPurgedAt() {
    return purgedAt;
  }

  public String getUploaderDisplayName() {
    return uploaderDisplayName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileVersion casted = (FileVersion) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(sha1, casted.sha1)
        && Objects.equals(name, casted.name)
        && Objects.equals(size, casted.size)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(modifiedBy, casted.modifiedBy)
        && Objects.equals(trashedAt, casted.trashedAt)
        && Objects.equals(trashedBy, casted.trashedBy)
        && Objects.equals(restoredAt, casted.restoredAt)
        && Objects.equals(restoredBy, casted.restoredBy)
        && Objects.equals(purgedAt, casted.purgedAt)
        && Objects.equals(uploaderDisplayName, casted.uploaderDisplayName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        sha1,
        name,
        size,
        createdAt,
        modifiedAt,
        modifiedBy,
        trashedAt,
        trashedBy,
        restoredAt,
        restoredBy,
        purgedAt,
        uploaderDisplayName);
  }

  @Override
  public String toString() {
    return "FileVersion{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "sha1='"
        + sha1
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "size='"
        + size
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + ", "
        + "modifiedBy='"
        + modifiedBy
        + '\''
        + ", "
        + "trashedAt='"
        + trashedAt
        + '\''
        + ", "
        + "trashedBy='"
        + trashedBy
        + '\''
        + ", "
        + "restoredAt='"
        + restoredAt
        + '\''
        + ", "
        + "restoredBy='"
        + restoredBy
        + '\''
        + ", "
        + "purgedAt='"
        + purgedAt
        + '\''
        + ", "
        + "uploaderDisplayName='"
        + uploaderDisplayName
        + '\''
        + "}";
  }

  public static class FileVersionBuilder extends FileVersionMiniBuilder {

    protected String name;

    protected Long size;

    protected String createdAt;

    protected String modifiedAt;

    protected UserMini modifiedBy;

    protected String trashedAt;

    protected UserMini trashedBy;

    protected String restoredAt;

    protected UserMini restoredBy;

    protected String purgedAt;

    protected String uploaderDisplayName;

    public FileVersionBuilder(String id) {
      super(id);
    }

    public FileVersionBuilder name(String name) {
      this.name = name;
      return this;
    }

    public FileVersionBuilder size(Long size) {
      this.size = size;
      return this;
    }

    public FileVersionBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public FileVersionBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public FileVersionBuilder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public FileVersionBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public FileVersionBuilder trashedBy(UserMini trashedBy) {
      this.trashedBy = trashedBy;
      return this;
    }

    public FileVersionBuilder restoredAt(String restoredAt) {
      this.restoredAt = restoredAt;
      return this;
    }

    public FileVersionBuilder restoredBy(UserMini restoredBy) {
      this.restoredBy = restoredBy;
      return this;
    }

    public FileVersionBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public FileVersionBuilder uploaderDisplayName(String uploaderDisplayName) {
      this.uploaderDisplayName = uploaderDisplayName;
      return this;
    }

    @Override
    public FileVersionBuilder type(FileVersionBaseTypeField type) {
      this.type = new EnumWrapper<FileVersionBaseTypeField>(type);
      return this;
    }

    @Override
    public FileVersionBuilder type(EnumWrapper<FileVersionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public FileVersionBuilder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    public FileVersion build() {
      return new FileVersion(this);
    }
  }
}
