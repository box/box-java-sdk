package com.box.sdkgen.schemas.fileversionlegalhold;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.legalholdpolicyassignment.LegalHoldPolicyAssignment;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileVersionLegalHold extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using = FileVersionLegalHoldTypeField.FileVersionLegalHoldTypeFieldDeserializer.class)
  @JsonSerialize(
      using = FileVersionLegalHoldTypeField.FileVersionLegalHoldTypeFieldSerializer.class)
  protected EnumWrapper<FileVersionLegalHoldTypeField> type;

  @JsonProperty("file_version")
  protected FileVersionMini fileVersion;

  protected FileMini file;

  @JsonProperty("legal_hold_policy_assignments")
  protected List<LegalHoldPolicyAssignment> legalHoldPolicyAssignments;

  @JsonProperty("deleted_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date deletedAt;

  public FileVersionLegalHold() {
    super();
  }

  protected FileVersionLegalHold(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.fileVersion = builder.fileVersion;
    this.file = builder.file;
    this.legalHoldPolicyAssignments = builder.legalHoldPolicyAssignments;
    this.deletedAt = builder.deletedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<FileVersionLegalHoldTypeField> getType() {
    return type;
  }

  public FileVersionMini getFileVersion() {
    return fileVersion;
  }

  public FileMini getFile() {
    return file;
  }

  public List<LegalHoldPolicyAssignment> getLegalHoldPolicyAssignments() {
    return legalHoldPolicyAssignments;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileVersionLegalHold casted = (FileVersionLegalHold) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(fileVersion, casted.fileVersion)
        && Objects.equals(file, casted.file)
        && Objects.equals(legalHoldPolicyAssignments, casted.legalHoldPolicyAssignments)
        && Objects.equals(deletedAt, casted.deletedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, fileVersion, file, legalHoldPolicyAssignments, deletedAt);
  }

  @Override
  public String toString() {
    return "FileVersionLegalHold{"
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
        + "legalHoldPolicyAssignments='"
        + legalHoldPolicyAssignments
        + '\''
        + ", "
        + "deletedAt='"
        + deletedAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<FileVersionLegalHoldTypeField> type;

    protected FileVersionMini fileVersion;

    protected FileMini file;

    protected List<LegalHoldPolicyAssignment> legalHoldPolicyAssignments;

    protected Date deletedAt;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(FileVersionLegalHoldTypeField type) {
      this.type = new EnumWrapper<FileVersionLegalHoldTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FileVersionLegalHoldTypeField> type) {
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

    public Builder legalHoldPolicyAssignments(
        List<LegalHoldPolicyAssignment> legalHoldPolicyAssignments) {
      this.legalHoldPolicyAssignments = legalHoldPolicyAssignments;
      return this;
    }

    public Builder deletedAt(Date deletedAt) {
      this.deletedAt = deletedAt;
      return this;
    }

    public FileVersionLegalHold build() {
      return new FileVersionLegalHold(this);
    }
  }
}
