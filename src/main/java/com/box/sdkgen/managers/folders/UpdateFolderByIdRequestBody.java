package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFolderByIdRequestBody extends SerializableObject {

  /**
   * The optional new name for this folder.
   *
   * <p>The following restrictions to folder names apply: names containing non-printable ASCII
   * characters, forward and backward slashes (`/`, `\`), names with trailing spaces, and names `.`
   * and `..` are not allowed.
   *
   * <p>Folder names must be unique within their parent folder. The name check is case-insensitive,
   * so a folder named `New Folder` cannot be created in a parent folder that already contains a
   * folder named `new folder`.
   */
  protected String name;

  /** The optional description of this folder. */
  protected String description;

  /**
   * Specifies whether a folder should be synced to a user's device or not. This is used by Box Sync
   * (discontinued) and is not used by Box Drive.
   */
  @JsonDeserialize(
      using =
          UpdateFolderByIdRequestBodySyncStateField
              .UpdateFolderByIdRequestBodySyncStateFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFolderByIdRequestBodySyncStateField
              .UpdateFolderByIdRequestBodySyncStateFieldSerializer.class)
  @JsonProperty("sync_state")
  protected EnumWrapper<UpdateFolderByIdRequestBodySyncStateField> syncState;

  /**
   * Specifies if users who are not the owner of the folder can invite new collaborators to the
   * folder.
   */
  @JsonProperty("can_non_owners_invite")
  protected Boolean canNonOwnersInvite;

  protected UpdateFolderByIdRequestBodyParentField parent;

  @JsonProperty("shared_link")
  protected UpdateFolderByIdRequestBodySharedLinkField sharedLink;

  @JsonProperty("folder_upload_email")
  @Nullable
  protected UpdateFolderByIdRequestBodyFolderUploadEmailField folderUploadEmail;

  /**
   * The tags for this item. These tags are shown in the Box web app and mobile apps next to an
   * item.
   *
   * <p>To add or remove a tag, retrieve the item's current tags, modify them, and then update this
   * field.
   *
   * <p>There is a limit of 100 tags per item, and 10,000 unique tags per enterprise.
   */
  protected List<String> tags;

  /**
   * Specifies if new invites to this folder are restricted to users within the enterprise. This
   * does not affect existing collaborations.
   */
  @JsonProperty("is_collaboration_restricted_to_enterprise")
  protected Boolean isCollaborationRestrictedToEnterprise;

  /**
   * An array of collections to make this folder a member of. Currently we only support the
   * `favorites` collection.
   *
   * <p>To get the ID for a collection, use the [List all collections][1] endpoint.
   *
   * <p>Passing an empty array `[]` or `null` will remove the folder from all collections.
   *
   * <p>[1]: e://get-collections
   */
  @Nullable protected List<UpdateFolderByIdRequestBodyCollectionsField> collections;

  /**
   * Restricts collaborators who are not the owner of this folder from viewing other collaborations
   * on this folder.
   *
   * <p>It also restricts non-owners from inviting new collaborators.
   *
   * <p>When setting this field to `false`, it is required to also set
   * `can_non_owners_invite_collaborators` to `false` if it has not already been set.
   */
  @JsonProperty("can_non_owners_view_collaborators")
  protected Boolean canNonOwnersViewCollaborators;

  public UpdateFolderByIdRequestBody() {
    super();
  }

  protected UpdateFolderByIdRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.description = builder.description;
    this.syncState = builder.syncState;
    this.canNonOwnersInvite = builder.canNonOwnersInvite;
    this.parent = builder.parent;
    this.sharedLink = builder.sharedLink;
    this.folderUploadEmail = builder.folderUploadEmail;
    this.tags = builder.tags;
    this.isCollaborationRestrictedToEnterprise = builder.isCollaborationRestrictedToEnterprise;
    this.collections = builder.collections;
    this.canNonOwnersViewCollaborators = builder.canNonOwnersViewCollaborators;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<UpdateFolderByIdRequestBodySyncStateField> getSyncState() {
    return syncState;
  }

  public Boolean getCanNonOwnersInvite() {
    return canNonOwnersInvite;
  }

  public UpdateFolderByIdRequestBodyParentField getParent() {
    return parent;
  }

  public UpdateFolderByIdRequestBodySharedLinkField getSharedLink() {
    return sharedLink;
  }

  public UpdateFolderByIdRequestBodyFolderUploadEmailField getFolderUploadEmail() {
    return folderUploadEmail;
  }

  public List<String> getTags() {
    return tags;
  }

  public Boolean getIsCollaborationRestrictedToEnterprise() {
    return isCollaborationRestrictedToEnterprise;
  }

  public List<UpdateFolderByIdRequestBodyCollectionsField> getCollections() {
    return collections;
  }

  public Boolean getCanNonOwnersViewCollaborators() {
    return canNonOwnersViewCollaborators;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFolderByIdRequestBody casted = (UpdateFolderByIdRequestBody) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(syncState, casted.syncState)
        && Objects.equals(canNonOwnersInvite, casted.canNonOwnersInvite)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(sharedLink, casted.sharedLink)
        && Objects.equals(folderUploadEmail, casted.folderUploadEmail)
        && Objects.equals(tags, casted.tags)
        && Objects.equals(
            isCollaborationRestrictedToEnterprise, casted.isCollaborationRestrictedToEnterprise)
        && Objects.equals(collections, casted.collections)
        && Objects.equals(canNonOwnersViewCollaborators, casted.canNonOwnersViewCollaborators);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        name,
        description,
        syncState,
        canNonOwnersInvite,
        parent,
        sharedLink,
        folderUploadEmail,
        tags,
        isCollaborationRestrictedToEnterprise,
        collections,
        canNonOwnersViewCollaborators);
  }

  @Override
  public String toString() {
    return "UpdateFolderByIdRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "syncState='"
        + syncState
        + '\''
        + ", "
        + "canNonOwnersInvite='"
        + canNonOwnersInvite
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "sharedLink='"
        + sharedLink
        + '\''
        + ", "
        + "folderUploadEmail='"
        + folderUploadEmail
        + '\''
        + ", "
        + "tags='"
        + tags
        + '\''
        + ", "
        + "isCollaborationRestrictedToEnterprise='"
        + isCollaborationRestrictedToEnterprise
        + '\''
        + ", "
        + "collections='"
        + collections
        + '\''
        + ", "
        + "canNonOwnersViewCollaborators='"
        + canNonOwnersViewCollaborators
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected String description;

    protected EnumWrapper<UpdateFolderByIdRequestBodySyncStateField> syncState;

    protected Boolean canNonOwnersInvite;

    protected UpdateFolderByIdRequestBodyParentField parent;

    protected UpdateFolderByIdRequestBodySharedLinkField sharedLink;

    protected UpdateFolderByIdRequestBodyFolderUploadEmailField folderUploadEmail;

    protected List<String> tags;

    protected Boolean isCollaborationRestrictedToEnterprise;

    protected List<UpdateFolderByIdRequestBodyCollectionsField> collections;

    protected Boolean canNonOwnersViewCollaborators;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder syncState(UpdateFolderByIdRequestBodySyncStateField syncState) {
      this.syncState = new EnumWrapper<UpdateFolderByIdRequestBodySyncStateField>(syncState);
      return this;
    }

    public Builder syncState(EnumWrapper<UpdateFolderByIdRequestBodySyncStateField> syncState) {
      this.syncState = syncState;
      return this;
    }

    public Builder canNonOwnersInvite(Boolean canNonOwnersInvite) {
      this.canNonOwnersInvite = canNonOwnersInvite;
      return this;
    }

    public Builder parent(UpdateFolderByIdRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public Builder sharedLink(UpdateFolderByIdRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public Builder folderUploadEmail(
        UpdateFolderByIdRequestBodyFolderUploadEmailField folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      this.markNullableFieldAsSet("folder_upload_email");
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder isCollaborationRestrictedToEnterprise(
        Boolean isCollaborationRestrictedToEnterprise) {
      this.isCollaborationRestrictedToEnterprise = isCollaborationRestrictedToEnterprise;
      return this;
    }

    public Builder collections(List<UpdateFolderByIdRequestBodyCollectionsField> collections) {
      this.collections = collections;
      this.markNullableFieldAsSet("collections");
      return this;
    }

    public Builder canNonOwnersViewCollaborators(Boolean canNonOwnersViewCollaborators) {
      this.canNonOwnersViewCollaborators = canNonOwnersViewCollaborators;
      return this;
    }

    public UpdateFolderByIdRequestBody build() {
      return new UpdateFolderByIdRequestBody(this);
    }
  }
}
