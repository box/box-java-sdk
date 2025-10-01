package com.box.sdkgen.schemas.fileversionfull;

import com.box.sdkgen.schemas.fileversion.FileVersion;
import com.box.sdkgen.schemas.fileversionbase.FileVersionBaseTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A full representation of a file version, as can be returned from any file version API endpoints
 * by default.
 */
@JsonFilter("nullablePropertyFilter")
public class FileVersionFull extends FileVersion {

  /** The version number of this file version. */
  @JsonProperty("version_number")
  protected String versionNumber;

  public FileVersionFull(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileVersionFull(Builder builder) {
    super(builder);
    this.versionNumber = builder.versionNumber;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends FileVersion.Builder {

    protected String versionNumber;

    public Builder(String id) {
      super(id);
    }

    public Builder versionNumber(String versionNumber) {
      this.versionNumber = versionNumber;
      return this;
    }

    @Override
    public Builder type(FileVersionBaseTypeField type) {
      this.type = new EnumWrapper<FileVersionBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<FileVersionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    @Override
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder size(Long size) {
      this.size = size;
      return this;
    }

    @Override
    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @Override
    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public Builder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    @Override
    public Builder trashedAt(OffsetDateTime trashedAt) {
      this.trashedAt = trashedAt;
      this.markNullableFieldAsSet("trashed_at");
      return this;
    }

    @Override
    public Builder trashedBy(UserMini trashedBy) {
      this.trashedBy = trashedBy;
      return this;
    }

    @Override
    public Builder restoredAt(OffsetDateTime restoredAt) {
      this.restoredAt = restoredAt;
      this.markNullableFieldAsSet("restored_at");
      return this;
    }

    @Override
    public Builder restoredBy(UserMini restoredBy) {
      this.restoredBy = restoredBy;
      return this;
    }

    @Override
    public Builder purgedAt(OffsetDateTime purgedAt) {
      this.purgedAt = purgedAt;
      this.markNullableFieldAsSet("purged_at");
      return this;
    }

    @Override
    public Builder uploaderDisplayName(String uploaderDisplayName) {
      this.uploaderDisplayName = uploaderDisplayName;
      return this;
    }

    public FileVersionFull build() {
      return new FileVersionFull(this);
    }
  }
}
