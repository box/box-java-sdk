package com.box.sdkgen.schemas.fileversion;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.fileversionbase.FileVersionBaseTypeField;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileVersion extends FileVersionMini {

  protected String name;

  protected Long size;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  @JsonProperty("modified_by")
  protected UserMini modifiedBy;

  @JsonProperty("trashed_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime trashedAt;

  @JsonProperty("trashed_by")
  protected UserMini trashedBy;

  @JsonProperty("restored_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime restoredAt;

  @JsonProperty("restored_by")
  protected UserMini restoredBy;

  @JsonProperty("purged_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime purgedAt;

  @JsonProperty("uploader_display_name")
  protected String uploaderDisplayName;

  public FileVersion(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileVersion(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public Long getSize() {
    return size;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  public UserMini getModifiedBy() {
    return modifiedBy;
  }

  public OffsetDateTime getTrashedAt() {
    return trashedAt;
  }

  public UserMini getTrashedBy() {
    return trashedBy;
  }

  public OffsetDateTime getRestoredAt() {
    return restoredAt;
  }

  public UserMini getRestoredBy() {
    return restoredBy;
  }

  public OffsetDateTime getPurgedAt() {
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

  public static class Builder extends FileVersionMini.Builder {

    protected String name;

    protected Long size;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected UserMini modifiedBy;

    protected OffsetDateTime trashedAt;

    protected UserMini trashedBy;

    protected OffsetDateTime restoredAt;

    protected UserMini restoredBy;

    protected OffsetDateTime purgedAt;

    protected String uploaderDisplayName;

    public Builder(String id) {
      super(id);
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder size(Long size) {
      this.size = size;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public Builder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public Builder trashedAt(OffsetDateTime trashedAt) {
      this.trashedAt = trashedAt;
      this.markNullableFieldAsSet("trashed_at");
      return this;
    }

    public Builder trashedBy(UserMini trashedBy) {
      this.trashedBy = trashedBy;
      return this;
    }

    public Builder restoredAt(OffsetDateTime restoredAt) {
      this.restoredAt = restoredAt;
      this.markNullableFieldAsSet("restored_at");
      return this;
    }

    public Builder restoredBy(UserMini restoredBy) {
      this.restoredBy = restoredBy;
      return this;
    }

    public Builder purgedAt(OffsetDateTime purgedAt) {
      this.purgedAt = purgedAt;
      this.markNullableFieldAsSet("purged_at");
      return this;
    }

    public Builder uploaderDisplayName(String uploaderDisplayName) {
      this.uploaderDisplayName = uploaderDisplayName;
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

    public FileVersion build() {
      return new FileVersion(this);
    }
  }
}
