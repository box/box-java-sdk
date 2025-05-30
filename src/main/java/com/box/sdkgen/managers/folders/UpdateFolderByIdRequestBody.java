package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class UpdateFolderByIdRequestBody extends SerializableObject {

  protected String name;

  protected String description;

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

  @JsonProperty("can_non_owners_invite")
  protected Boolean canNonOwnersInvite;

  protected UpdateFolderByIdRequestBodyParentField parent;

  @JsonProperty("shared_link")
  protected UpdateFolderByIdRequestBodySharedLinkField sharedLink;

  @JsonProperty("folder_upload_email")
  protected UpdateFolderByIdRequestBodyFolderUploadEmailField folderUploadEmail;

  protected List<String> tags;

  @JsonProperty("is_collaboration_restricted_to_enterprise")
  protected Boolean isCollaborationRestrictedToEnterprise;

  protected List<UpdateFolderByIdRequestBodyCollectionsField> collections;

  @JsonProperty("can_non_owners_view_collaborators")
  protected Boolean canNonOwnersViewCollaborators;

  public UpdateFolderByIdRequestBody() {
    super();
  }

  protected UpdateFolderByIdRequestBody(UpdateFolderByIdRequestBodyBuilder builder) {
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

  public static class UpdateFolderByIdRequestBodyBuilder {

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

    public UpdateFolderByIdRequestBodyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder description(String description) {
      this.description = description;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder syncState(
        UpdateFolderByIdRequestBodySyncStateField syncState) {
      this.syncState = new EnumWrapper<UpdateFolderByIdRequestBodySyncStateField>(syncState);
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder syncState(
        EnumWrapper<UpdateFolderByIdRequestBodySyncStateField> syncState) {
      this.syncState = syncState;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder canNonOwnersInvite(Boolean canNonOwnersInvite) {
      this.canNonOwnersInvite = canNonOwnersInvite;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder parent(
        UpdateFolderByIdRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder sharedLink(
        UpdateFolderByIdRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder folderUploadEmail(
        UpdateFolderByIdRequestBodyFolderUploadEmailField folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder isCollaborationRestrictedToEnterprise(
        Boolean isCollaborationRestrictedToEnterprise) {
      this.isCollaborationRestrictedToEnterprise = isCollaborationRestrictedToEnterprise;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder collections(
        List<UpdateFolderByIdRequestBodyCollectionsField> collections) {
      this.collections = collections;
      return this;
    }

    public UpdateFolderByIdRequestBodyBuilder canNonOwnersViewCollaborators(
        Boolean canNonOwnersViewCollaborators) {
      this.canNonOwnersViewCollaborators = canNonOwnersViewCollaborators;
      return this;
    }

    public UpdateFolderByIdRequestBody build() {
      return new UpdateFolderByIdRequestBody(this);
    }
  }
}
