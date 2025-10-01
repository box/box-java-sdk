package com.box.sdkgen.schemas.legalholdpolicy;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class LegalHoldPolicyAssignmentCountsField extends SerializableObject {

  /** The number of users this policy is applied to. */
  protected Long user;

  /** The number of folders this policy is applied to. */
  protected Long folder;

  /** The number of files this policy is applied to. */
  protected Long file;

  /** The number of file versions this policy is applied to. */
  @JsonProperty("file_version")
  protected Long fileVersion;

  public LegalHoldPolicyAssignmentCountsField() {
    super();
  }

  protected LegalHoldPolicyAssignmentCountsField(Builder builder) {
    super();
    this.user = builder.user;
    this.folder = builder.folder;
    this.file = builder.file;
    this.fileVersion = builder.fileVersion;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected Long user;

    protected Long folder;

    protected Long file;

    protected Long fileVersion;

    public Builder user(Long user) {
      this.user = user;
      return this;
    }

    public Builder folder(Long folder) {
      this.folder = folder;
      return this;
    }

    public Builder file(Long file) {
      this.file = file;
      return this;
    }

    public Builder fileVersion(Long fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public LegalHoldPolicyAssignmentCountsField build() {
      return new LegalHoldPolicyAssignmentCountsField(this);
    }
  }
}
