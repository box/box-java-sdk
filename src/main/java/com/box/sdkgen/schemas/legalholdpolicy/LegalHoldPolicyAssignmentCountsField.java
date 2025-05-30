package com.box.sdkgen.schemas.legalholdpolicy;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class LegalHoldPolicyAssignmentCountsField extends SerializableObject {

  protected Long user;

  protected Long folder;

  protected Long file;

  @JsonProperty("file_version")
  protected Long fileVersion;

  public LegalHoldPolicyAssignmentCountsField() {
    super();
  }

  protected LegalHoldPolicyAssignmentCountsField(
      LegalHoldPolicyAssignmentCountsFieldBuilder builder) {
    super();
    this.user = builder.user;
    this.folder = builder.folder;
    this.file = builder.file;
    this.fileVersion = builder.fileVersion;
  }

  public Long getUser() {
    return user;
  }

  public Long getFolder() {
    return folder;
  }

  public Long getFile() {
    return file;
  }

  public Long getFileVersion() {
    return fileVersion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LegalHoldPolicyAssignmentCountsField casted = (LegalHoldPolicyAssignmentCountsField) o;
    return Objects.equals(user, casted.user)
        && Objects.equals(folder, casted.folder)
        && Objects.equals(file, casted.file)
        && Objects.equals(fileVersion, casted.fileVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, folder, file, fileVersion);
  }

  @Override
  public String toString() {
    return "LegalHoldPolicyAssignmentCountsField{"
        + "user='"
        + user
        + '\''
        + ", "
        + "folder='"
        + folder
        + '\''
        + ", "
        + "file='"
        + file
        + '\''
        + ", "
        + "fileVersion='"
        + fileVersion
        + '\''
        + "}";
  }

  public static class LegalHoldPolicyAssignmentCountsFieldBuilder {

    protected Long user;

    protected Long folder;

    protected Long file;

    protected Long fileVersion;

    public LegalHoldPolicyAssignmentCountsFieldBuilder user(Long user) {
      this.user = user;
      return this;
    }

    public LegalHoldPolicyAssignmentCountsFieldBuilder folder(Long folder) {
      this.folder = folder;
      return this;
    }

    public LegalHoldPolicyAssignmentCountsFieldBuilder file(Long file) {
      this.file = file;
      return this;
    }

    public LegalHoldPolicyAssignmentCountsFieldBuilder fileVersion(Long fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public LegalHoldPolicyAssignmentCountsField build() {
      return new LegalHoldPolicyAssignmentCountsField(this);
    }
  }
}
