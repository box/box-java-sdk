package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateFolderRequestBody extends SerializableObject {

  /**
   * The name for the new folder.
   *
   * <p>The following restrictions to folder names apply: names containing non-printable ASCII
   * characters, forward and backward slashes (`/`, `\`), names with trailing spaces, and names `.`
   * and `..` are not allowed.
   *
   * <p>Folder names must be unique within their parent folder. The name check is case-insensitive,
   * so a folder named `New Folder` cannot be created in a parent folder that already contains a
   * folder named `new folder`.
   */
  protected final String name;

  /** The parent folder to create the new folder within. */
  protected final CreateFolderRequestBodyParentField parent;

  @JsonProperty("folder_upload_email")
  protected CreateFolderRequestBodyFolderUploadEmailField folderUploadEmail;

  /**
   * Specifies whether a folder should be synced to a user's device or not. This is used by Box Sync
   * (discontinued) and is not used by Box Drive.
   */
  @JsonDeserialize(
      using =
          CreateFolderRequestBodySyncStateField.CreateFolderRequestBodySyncStateFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          CreateFolderRequestBodySyncStateField.CreateFolderRequestBodySyncStateFieldSerializer
              .class)
  @JsonProperty("sync_state")
  protected EnumWrapper<CreateFolderRequestBodySyncStateField> syncState;

  public CreateFolderRequestBody(
      @JsonProperty("name") String name,
      @JsonProperty("parent") CreateFolderRequestBodyParentField parent) {
    super();
    this.name = name;
    this.parent = parent;
  }

  protected CreateFolderRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    this.folderUploadEmail = builder.folderUploadEmail;
    this.syncState = builder.syncState;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public CreateFolderRequestBodyParentField getParent() {
    return parent;
  }

  public CreateFolderRequestBodyFolderUploadEmailField getFolderUploadEmail() {
    return folderUploadEmail;
  }

  public EnumWrapper<CreateFolderRequestBodySyncStateField> getSyncState() {
    return syncState;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateFolderRequestBody casted = (CreateFolderRequestBody) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(folderUploadEmail, casted.folderUploadEmail)
        && Objects.equals(syncState, casted.syncState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent, folderUploadEmail, syncState);
  }

  @Override
  public String toString() {
    return "CreateFolderRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "folderUploadEmail='"
        + folderUploadEmail
        + '\''
        + ", "
        + "syncState='"
        + syncState
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected final CreateFolderRequestBodyParentField parent;

    protected CreateFolderRequestBodyFolderUploadEmailField folderUploadEmail;

    protected EnumWrapper<CreateFolderRequestBodySyncStateField> syncState;

    public Builder(String name, CreateFolderRequestBodyParentField parent) {
      super();
      this.name = name;
      this.parent = parent;
    }

    public Builder folderUploadEmail(
        CreateFolderRequestBodyFolderUploadEmailField folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      return this;
    }

    public Builder syncState(CreateFolderRequestBodySyncStateField syncState) {
      this.syncState = new EnumWrapper<CreateFolderRequestBodySyncStateField>(syncState);
      return this;
    }

    public Builder syncState(EnumWrapper<CreateFolderRequestBodySyncStateField> syncState) {
      this.syncState = syncState;
      return this;
    }

    public CreateFolderRequestBody build() {
      return new CreateFolderRequestBody(this);
    }
  }
}
