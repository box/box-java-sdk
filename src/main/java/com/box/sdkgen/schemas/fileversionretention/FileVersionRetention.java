package com.box.sdkgen.schemas.fileversionretention;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.retentionpolicymini.RetentionPolicyMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class FileVersionRetention extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using = FileVersionRetentionTypeField.FileVersionRetentionTypeFieldDeserializer.class)
  @JsonSerialize(
      using = FileVersionRetentionTypeField.FileVersionRetentionTypeFieldSerializer.class)
  protected EnumWrapper<FileVersionRetentionTypeField> type;

  @JsonProperty("file_version")
  protected FileVersionMini fileVersion;

  protected FileMini file;

  @JsonProperty("applied_at")
  protected String appliedAt;

  @JsonProperty("disposition_at")
  protected String dispositionAt;

  @JsonProperty("winning_retention_policy")
  protected RetentionPolicyMini winningRetentionPolicy;

  public FileVersionRetention() {
    super();
  }

  protected FileVersionRetention(FileVersionRetentionBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.fileVersion = builder.fileVersion;
    this.file = builder.file;
    this.appliedAt = builder.appliedAt;
    this.dispositionAt = builder.dispositionAt;
    this.winningRetentionPolicy = builder.winningRetentionPolicy;
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

  public String getAppliedAt() {
    return appliedAt;
  }

  public String getDispositionAt() {
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

  public static class FileVersionRetentionBuilder {

    protected String id;

    protected EnumWrapper<FileVersionRetentionTypeField> type;

    protected FileVersionMini fileVersion;

    protected FileMini file;

    protected String appliedAt;

    protected String dispositionAt;

    protected RetentionPolicyMini winningRetentionPolicy;

    public FileVersionRetentionBuilder id(String id) {
      this.id = id;
      return this;
    }

    public FileVersionRetentionBuilder type(FileVersionRetentionTypeField type) {
      this.type = new EnumWrapper<FileVersionRetentionTypeField>(type);
      return this;
    }

    public FileVersionRetentionBuilder type(EnumWrapper<FileVersionRetentionTypeField> type) {
      this.type = type;
      return this;
    }

    public FileVersionRetentionBuilder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public FileVersionRetentionBuilder file(FileMini file) {
      this.file = file;
      return this;
    }

    public FileVersionRetentionBuilder appliedAt(String appliedAt) {
      this.appliedAt = appliedAt;
      return this;
    }

    public FileVersionRetentionBuilder dispositionAt(String dispositionAt) {
      this.dispositionAt = dispositionAt;
      return this;
    }

    public FileVersionRetentionBuilder winningRetentionPolicy(
        RetentionPolicyMini winningRetentionPolicy) {
      this.winningRetentionPolicy = winningRetentionPolicy;
      return this;
    }

    public FileVersionRetention build() {
      return new FileVersionRetention(this);
    }
  }
}
