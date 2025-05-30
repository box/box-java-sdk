package com.box.sdkgen.schemas.fileversionfull;

import com.box.sdkgen.schemas.fileversion.FileVersion;
import com.box.sdkgen.schemas.fileversionbase.FileVersionBaseTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class FileVersionFull extends FileVersion {

  @JsonProperty("version_number")
  protected String versionNumber;

  public FileVersionFull(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileVersionFull(FileVersionFullBuilder builder) {
    super(builder);
    this.versionNumber = builder.versionNumber;
  }

  public String getVersionNumber() {
    return versionNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileVersionFull casted = (FileVersionFull) o;
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
        && Objects.equals(uploaderDisplayName, casted.uploaderDisplayName)
        && Objects.equals(versionNumber, casted.versionNumber);
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
        uploaderDisplayName,
        versionNumber);
  }

  @Override
  public String toString() {
    return "FileVersionFull{"
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
        + ", "
        + "versionNumber='"
        + versionNumber
        + '\''
        + "}";
  }

  public static class FileVersionFullBuilder extends FileVersionBuilder {

    protected String versionNumber;

    public FileVersionFullBuilder(String id) {
      super(id);
    }

    public FileVersionFullBuilder versionNumber(String versionNumber) {
      this.versionNumber = versionNumber;
      return this;
    }

    @Override
    public FileVersionFullBuilder type(FileVersionBaseTypeField type) {
      this.type = new EnumWrapper<FileVersionBaseTypeField>(type);
      return this;
    }

    @Override
    public FileVersionFullBuilder type(EnumWrapper<FileVersionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public FileVersionFullBuilder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    @Override
    public FileVersionFullBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public FileVersionFullBuilder size(Long size) {
      this.size = size;
      return this;
    }

    @Override
    public FileVersionFullBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @Override
    public FileVersionFullBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public FileVersionFullBuilder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    @Override
    public FileVersionFullBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    @Override
    public FileVersionFullBuilder trashedBy(UserMini trashedBy) {
      this.trashedBy = trashedBy;
      return this;
    }

    @Override
    public FileVersionFullBuilder restoredAt(String restoredAt) {
      this.restoredAt = restoredAt;
      return this;
    }

    @Override
    public FileVersionFullBuilder restoredBy(UserMini restoredBy) {
      this.restoredBy = restoredBy;
      return this;
    }

    @Override
    public FileVersionFullBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    @Override
    public FileVersionFullBuilder uploaderDisplayName(String uploaderDisplayName) {
      this.uploaderDisplayName = uploaderDisplayName;
      return this;
    }

    public FileVersionFull build() {
      return new FileVersionFull(this);
    }
  }
}
