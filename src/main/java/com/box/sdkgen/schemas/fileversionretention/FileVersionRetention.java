package com.box.sdkgen.schemas.fileversionretention;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.retentionpolicymini.RetentionPolicyMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A retention policy blocks permanent deletion of content for a specified amount of time. Admins
 * can apply policies to specified folders, or an entire enterprise. A file version retention is a
 * record for a retained file version. To use this feature, you must have the manage retention
 * policies scope enabled for your API key in your application management console.
 *
 * <p>**Note**: File retention API is now **deprecated**. To get information about files and file
 * versions under retention, see [files under
 * retention](e://get-retention-policy-assignments-id-files-under-retention) or [file versions under
 * retention](e://get-retention-policy-assignments-id-file-versions-under-retention) endpoints.
 */
@JsonFilter("nullablePropertyFilter")
public class FileVersionRetention extends SerializableObject {

  /** The unique identifier for this file version retention. */
  protected String id;

  /** The value will always be `file_version_retention`. */
  @JsonDeserialize(
      using = FileVersionRetentionTypeField.FileVersionRetentionTypeFieldDeserializer.class)
  @JsonSerialize(
      using = FileVersionRetentionTypeField.FileVersionRetentionTypeFieldSerializer.class)
  protected EnumWrapper<FileVersionRetentionTypeField> type;

  @JsonProperty("file_version")
  protected FileVersionMini fileVersion;

  protected FileMini file;

  /** When this file version retention object was created. */
  @JsonProperty("applied_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime appliedAt;

  /** When the retention expires on this file version retention. */
  @JsonProperty("disposition_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime dispositionAt;

  @JsonProperty("winning_retention_policy")
  protected RetentionPolicyMini winningRetentionPolicy;

  public FileVersionRetention() {
    super();
  }

  protected FileVersionRetention(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.fileVersion = builder.fileVersion;
    this.file = builder.file;
    this.appliedAt = builder.appliedAt;
    this.dispositionAt = builder.dispositionAt;
    this.winningRetentionPolicy = builder.winningRetentionPolicy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<FileVersionRetentionTypeField> getType() {
    return type;
  }

  public FileVersionMini getFileVersion() {
    return fileVersion;
  }

  public FileMini getFile() {
    return file;
  }

  public OffsetDateTime getAppliedAt() {
    return appliedAt;
  }

  public OffsetDateTime getDispositionAt() {
    return dispositionAt;
  }

  public RetentionPolicyMini getWinningRetentionPolicy() {
    return winningRetentionPolicy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileVersionRetention casted = (FileVersionRetention) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(fileVersion, casted.fileVersion)
        && Objects.equals(file, casted.file)
        && Objects.equals(appliedAt, casted.appliedAt)
        && Objects.equals(dispositionAt, casted.dispositionAt)
        && Objects.equals(winningRetentionPolicy, casted.winningRetentionPolicy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, fileVersion, file, appliedAt, dispositionAt, winningRetentionPolicy);
  }

  @Override
  public String toString() {
    return "FileVersionRetention{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "fileVersion='"
        + fileVersion
        + '\''
        + ", "
        + "file='"
        + file
        + '\''
        + ", "
        + "appliedAt='"
        + appliedAt
        + '\''
        + ", "
        + "dispositionAt='"
        + dispositionAt
        + '\''
        + ", "
        + "winningRetentionPolicy='"
        + winningRetentionPolicy
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<FileVersionRetentionTypeField> type;

    protected FileVersionMini fileVersion;

    protected FileMini file;

    protected OffsetDateTime appliedAt;

    protected OffsetDateTime dispositionAt;

    protected RetentionPolicyMini winningRetentionPolicy;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(FileVersionRetentionTypeField type) {
      this.type = new EnumWrapper<FileVersionRetentionTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FileVersionRetentionTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public Builder file(FileMini file) {
      this.file = file;
      return this;
    }

    public Builder appliedAt(OffsetDateTime appliedAt) {
      this.appliedAt = appliedAt;
      return this;
    }

    public Builder dispositionAt(OffsetDateTime dispositionAt) {
      this.dispositionAt = dispositionAt;
      return this;
    }

    public Builder winningRetentionPolicy(RetentionPolicyMini winningRetentionPolicy) {
      this.winningRetentionPolicy = winningRetentionPolicy;
      return this;
    }

    public FileVersionRetention build() {
      return new FileVersionRetention(this);
    }
  }
}
