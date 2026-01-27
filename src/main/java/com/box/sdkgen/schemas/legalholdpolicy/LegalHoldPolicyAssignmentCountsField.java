package com.box.sdkgen.schemas.legalholdpolicy;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class LegalHoldPolicyAssignmentCountsField extends SerializableObject {

  /** The number of users this policy is applied to with the `access` type assignment. */
  protected Long user;

  /** The number of folders this policy is applied to. */
  protected Long folder;

  /** The number of files this policy is applied to. */
  protected Long file;

  /** The number of file versions this policy is applied to. */
  @JsonProperty("file_version")
  protected Long fileVersion;

  /** The number of users this policy is applied to with the `ownership` type assignment. */
  protected Long ownership;

  /** The number of users this policy is applied to with the `interactions` type assignment. */
  protected Long interactions;

  public LegalHoldPolicyAssignmentCountsField() {
    super();
  }

  protected LegalHoldPolicyAssignmentCountsField(Builder builder) {
    super();
    this.user = builder.user;
    this.folder = builder.folder;
    this.file = builder.file;
    this.fileVersion = builder.fileVersion;
    this.ownership = builder.ownership;
    this.interactions = builder.interactions;
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

  public Long getOwnership() {
    return ownership;
  }

  public Long getInteractions() {
    return interactions;
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
        && Objects.equals(fileVersion, casted.fileVersion)
        && Objects.equals(ownership, casted.ownership)
        && Objects.equals(interactions, casted.interactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, folder, file, fileVersion, ownership, interactions);
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
        + ", "
        + "ownership='"
        + ownership
        + '\''
        + ", "
        + "interactions='"
        + interactions
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long user;

    protected Long folder;

    protected Long file;

    protected Long fileVersion;

    protected Long ownership;

    protected Long interactions;

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

    public Builder ownership(Long ownership) {
      this.ownership = ownership;
      return this;
    }

    public Builder interactions(Long interactions) {
      this.interactions = interactions;
      return this;
    }

    public LegalHoldPolicyAssignmentCountsField build() {
      return new LegalHoldPolicyAssignmentCountsField(this);
    }
  }
}
