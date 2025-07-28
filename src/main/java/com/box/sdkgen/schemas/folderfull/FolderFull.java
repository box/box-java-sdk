package com.box.sdkgen.schemas.folderfull;

import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.schemas.folder.FolderFolderUploadEmailField;
import com.box.sdkgen.schemas.folder.FolderItemStatusField;
import com.box.sdkgen.schemas.folder.FolderPathCollectionField;
import com.box.sdkgen.schemas.folder.FolderSharedLinkField;
import com.box.sdkgen.schemas.folderbase.FolderBaseTypeField;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.items.Items;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderFull extends Folder {

  @JsonDeserialize(using = FolderFullSyncStateField.FolderFullSyncStateFieldDeserializer.class)
  @JsonSerialize(using = FolderFullSyncStateField.FolderFullSyncStateFieldSerializer.class)
  @JsonProperty("sync_state")
  protected EnumWrapper<FolderFullSyncStateField> syncState;

  @JsonProperty("has_collaborations")
  protected Boolean hasCollaborations;

  protected FolderFullPermissionsField permissions;

  protected List<String> tags;

  @JsonProperty("can_non_owners_invite")
  protected Boolean canNonOwnersInvite;

  @JsonProperty("is_externally_owned")
  protected Boolean isExternallyOwned;

  protected FolderFullMetadataField metadata;

  @JsonProperty("is_collaboration_restricted_to_enterprise")
  protected Boolean isCollaborationRestrictedToEnterprise;

  @JsonDeserialize(using = AllowedSharedLinkAccessLevelsDeserializer.class)
  @JsonSerialize(using = AllowedSharedLinkAccessLevelsSerializer.class)
  @JsonProperty("allowed_shared_link_access_levels")
  protected List<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>>
      allowedSharedLinkAccessLevels;

  @JsonDeserialize(using = AllowedInviteeRolesDeserializer.class)
  @JsonSerialize(using = AllowedInviteeRolesSerializer.class)
  @JsonProperty("allowed_invitee_roles")
  protected List<EnumWrapper<FolderFullAllowedInviteeRolesField>> allowedInviteeRoles;

  @JsonProperty("watermark_info")
  protected FolderFullWatermarkInfoField watermarkInfo;

  @JsonProperty("is_accessible_via_shared_link")
  protected Boolean isAccessibleViaSharedLink;

  @JsonProperty("can_non_owners_view_collaborators")
  protected Boolean canNonOwnersViewCollaborators;

  protected FolderFullClassificationField classification;

  @JsonProperty("is_associated_with_app_item")
  protected Boolean isAssociatedWithAppItem;

  public FolderFull(@JsonProperty("id") String id) {
    super(id);
  }

  protected FolderFull(Builder builder) {
    super(builder);
    this.syncState = builder.syncState;
    this.hasCollaborations = builder.hasCollaborations;
    this.permissions = builder.permissions;
    this.tags = builder.tags;
    this.canNonOwnersInvite = builder.canNonOwnersInvite;
    this.isExternallyOwned = builder.isExternallyOwned;
    this.metadata = builder.metadata;
    this.isCollaborationRestrictedToEnterprise = builder.isCollaborationRestrictedToEnterprise;
    this.allowedSharedLinkAccessLevels = builder.allowedSharedLinkAccessLevels;
    this.allowedInviteeRoles = builder.allowedInviteeRoles;
    this.watermarkInfo = builder.watermarkInfo;
    this.isAccessibleViaSharedLink = builder.isAccessibleViaSharedLink;
    this.canNonOwnersViewCollaborators = builder.canNonOwnersViewCollaborators;
    this.classification = builder.classification;
    this.isAssociatedWithAppItem = builder.isAssociatedWithAppItem;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<FolderFullSyncStateField> getSyncState() {
    return syncState;
  }

  public Boolean getHasCollaborations() {
    return hasCollaborations;
  }

  public FolderFullPermissionsField getPermissions() {
    return permissions;
  }

  public List<String> getTags() {
    return tags;
  }

  public Boolean getCanNonOwnersInvite() {
    return canNonOwnersInvite;
  }

  public Boolean getIsExternallyOwned() {
    return isExternallyOwned;
  }

  public FolderFullMetadataField getMetadata() {
    return metadata;
  }

  public Boolean getIsCollaborationRestrictedToEnterprise() {
    return isCollaborationRestrictedToEnterprise;
  }

  public List<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>>
      getAllowedSharedLinkAccessLevels() {
    return allowedSharedLinkAccessLevels;
  }

  public List<EnumWrapper<FolderFullAllowedInviteeRolesField>> getAllowedInviteeRoles() {
    return allowedInviteeRoles;
  }

  public FolderFullWatermarkInfoField getWatermarkInfo() {
    return watermarkInfo;
  }

  public Boolean getIsAccessibleViaSharedLink() {
    return isAccessibleViaSharedLink;
  }

  public Boolean getCanNonOwnersViewCollaborators() {
    return canNonOwnersViewCollaborators;
  }

  public FolderFullClassificationField getClassification() {
    return classification;
  }

  public Boolean getIsAssociatedWithAppItem() {
    return isAssociatedWithAppItem;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderFull casted = (FolderFull) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(type, casted.type)
        && Objects.equals(sequenceId, casted.sequenceId)
        && Objects.equals(name, casted.name)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(description, casted.description)
        && Objects.equals(size, casted.size)
        && Objects.equals(pathCollection, casted.pathCollection)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(modifiedBy, casted.modifiedBy)
        && Objects.equals(trashedAt, casted.trashedAt)
        && Objects.equals(purgedAt, casted.purgedAt)
        && Objects.equals(contentCreatedAt, casted.contentCreatedAt)
        && Objects.equals(contentModifiedAt, casted.contentModifiedAt)
        && Objects.equals(ownedBy, casted.ownedBy)
        && Objects.equals(sharedLink, casted.sharedLink)
        && Objects.equals(folderUploadEmail, casted.folderUploadEmail)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(itemStatus, casted.itemStatus)
        && Objects.equals(itemCollection, casted.itemCollection)
        && Objects.equals(syncState, casted.syncState)
        && Objects.equals(hasCollaborations, casted.hasCollaborations)
        && Objects.equals(permissions, casted.permissions)
        && Objects.equals(tags, casted.tags)
        && Objects.equals(canNonOwnersInvite, casted.canNonOwnersInvite)
        && Objects.equals(isExternallyOwned, casted.isExternallyOwned)
        && Objects.equals(metadata, casted.metadata)
        && Objects.equals(
            isCollaborationRestrictedToEnterprise, casted.isCollaborationRestrictedToEnterprise)
        && Objects.equals(allowedSharedLinkAccessLevels, casted.allowedSharedLinkAccessLevels)
        && Objects.equals(allowedInviteeRoles, casted.allowedInviteeRoles)
        && Objects.equals(watermarkInfo, casted.watermarkInfo)
        && Objects.equals(isAccessibleViaSharedLink, casted.isAccessibleViaSharedLink)
        && Objects.equals(canNonOwnersViewCollaborators, casted.canNonOwnersViewCollaborators)
        && Objects.equals(classification, casted.classification)
        && Objects.equals(isAssociatedWithAppItem, casted.isAssociatedWithAppItem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        etag,
        type,
        sequenceId,
        name,
        createdAt,
        modifiedAt,
        description,
        size,
        pathCollection,
        createdBy,
        modifiedBy,
        trashedAt,
        purgedAt,
        contentCreatedAt,
        contentModifiedAt,
        ownedBy,
        sharedLink,
        folderUploadEmail,
        parent,
        itemStatus,
        itemCollection,
        syncState,
        hasCollaborations,
        permissions,
        tags,
        canNonOwnersInvite,
        isExternallyOwned,
        metadata,
        isCollaborationRestrictedToEnterprise,
        allowedSharedLinkAccessLevels,
        allowedInviteeRoles,
        watermarkInfo,
        isAccessibleViaSharedLink,
        canNonOwnersViewCollaborators,
        classification,
        isAssociatedWithAppItem);
  }

  @Override
  public String toString() {
    return "FolderFull{"
        + "id='"
        + id
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "sequenceId='"
        + sequenceId
        + '\''
        + ", "
        + "name='"
        + name
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
        + "description='"
        + description
        + '\''
        + ", "
        + "size='"
        + size
        + '\''
        + ", "
        + "pathCollection='"
        + pathCollection
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
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
        + "purgedAt='"
        + purgedAt
        + '\''
        + ", "
        + "contentCreatedAt='"
        + contentCreatedAt
        + '\''
        + ", "
        + "contentModifiedAt='"
        + contentModifiedAt
        + '\''
        + ", "
        + "ownedBy='"
        + ownedBy
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
        + "parent='"
        + parent
        + '\''
        + ", "
        + "itemStatus='"
        + itemStatus
        + '\''
        + ", "
        + "itemCollection='"
        + itemCollection
        + '\''
        + ", "
        + "syncState='"
        + syncState
        + '\''
        + ", "
        + "hasCollaborations='"
        + hasCollaborations
        + '\''
        + ", "
        + "permissions='"
        + permissions
        + '\''
        + ", "
        + "tags='"
        + tags
        + '\''
        + ", "
        + "canNonOwnersInvite='"
        + canNonOwnersInvite
        + '\''
        + ", "
        + "isExternallyOwned='"
        + isExternallyOwned
        + '\''
        + ", "
        + "metadata='"
        + metadata
        + '\''
        + ", "
        + "isCollaborationRestrictedToEnterprise='"
        + isCollaborationRestrictedToEnterprise
        + '\''
        + ", "
        + "allowedSharedLinkAccessLevels='"
        + allowedSharedLinkAccessLevels
        + '\''
        + ", "
        + "allowedInviteeRoles='"
        + allowedInviteeRoles
        + '\''
        + ", "
        + "watermarkInfo='"
        + watermarkInfo
        + '\''
        + ", "
        + "isAccessibleViaSharedLink='"
        + isAccessibleViaSharedLink
        + '\''
        + ", "
        + "canNonOwnersViewCollaborators='"
        + canNonOwnersViewCollaborators
        + '\''
        + ", "
        + "classification='"
        + classification
        + '\''
        + ", "
        + "isAssociatedWithAppItem='"
        + isAssociatedWithAppItem
        + '\''
        + "}";
  }

  public static class Builder extends Folder.Builder {

    protected EnumWrapper<FolderFullSyncStateField> syncState;

    protected Boolean hasCollaborations;

    protected FolderFullPermissionsField permissions;

    protected List<String> tags;

    protected Boolean canNonOwnersInvite;

    protected Boolean isExternallyOwned;

    protected FolderFullMetadataField metadata;

    protected Boolean isCollaborationRestrictedToEnterprise;

    protected List<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>>
        allowedSharedLinkAccessLevels;

    protected List<EnumWrapper<FolderFullAllowedInviteeRolesField>> allowedInviteeRoles;

    protected FolderFullWatermarkInfoField watermarkInfo;

    protected Boolean isAccessibleViaSharedLink;

    protected Boolean canNonOwnersViewCollaborators;

    protected FolderFullClassificationField classification;

    protected Boolean isAssociatedWithAppItem;

    public Builder(String id) {
      super(id);
    }

    public Builder syncState(FolderFullSyncStateField syncState) {
      this.syncState = new EnumWrapper<FolderFullSyncStateField>(syncState);
      return this;
    }

    public Builder syncState(EnumWrapper<FolderFullSyncStateField> syncState) {
      this.syncState = syncState;
      return this;
    }

    public Builder hasCollaborations(Boolean hasCollaborations) {
      this.hasCollaborations = hasCollaborations;
      return this;
    }

    public Builder permissions(FolderFullPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder canNonOwnersInvite(Boolean canNonOwnersInvite) {
      this.canNonOwnersInvite = canNonOwnersInvite;
      return this;
    }

    public Builder isExternallyOwned(Boolean isExternallyOwned) {
      this.isExternallyOwned = isExternallyOwned;
      return this;
    }

    public Builder metadata(FolderFullMetadataField metadata) {
      this.metadata = metadata;
      return this;
    }

    public Builder isCollaborationRestrictedToEnterprise(
        Boolean isCollaborationRestrictedToEnterprise) {
      this.isCollaborationRestrictedToEnterprise = isCollaborationRestrictedToEnterprise;
      return this;
    }

    public Builder allowedSharedLinkAccessLevels(
        List<? extends Valuable> allowedSharedLinkAccessLevels) {
      this.allowedSharedLinkAccessLevels =
          EnumWrapper.wrapValuableEnumList(
              allowedSharedLinkAccessLevels, FolderFullAllowedSharedLinkAccessLevelsField.class);
      return this;
    }

    public Builder allowedInviteeRoles(List<? extends Valuable> allowedInviteeRoles) {
      this.allowedInviteeRoles =
          EnumWrapper.wrapValuableEnumList(
              allowedInviteeRoles, FolderFullAllowedInviteeRolesField.class);
      return this;
    }

    public Builder watermarkInfo(FolderFullWatermarkInfoField watermarkInfo) {
      this.watermarkInfo = watermarkInfo;
      return this;
    }

    public Builder isAccessibleViaSharedLink(Boolean isAccessibleViaSharedLink) {
      this.isAccessibleViaSharedLink = isAccessibleViaSharedLink;
      return this;
    }

    public Builder canNonOwnersViewCollaborators(Boolean canNonOwnersViewCollaborators) {
      this.canNonOwnersViewCollaborators = canNonOwnersViewCollaborators;
      return this;
    }

    public Builder classification(FolderFullClassificationField classification) {
      this.classification = classification;
      return this;
    }

    public Builder isAssociatedWithAppItem(Boolean isAssociatedWithAppItem) {
      this.isAssociatedWithAppItem = isAssociatedWithAppItem;
      return this;
    }

    @Override
    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    @Override
    public Builder type(FolderBaseTypeField type) {
      this.type = new EnumWrapper<FolderBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<FolderBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    @Override
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      this.markNullableFieldAsSet("created_at");
      return this;
    }

    @Override
    public Builder modifiedAt(Date modifiedAt) {
      this.modifiedAt = modifiedAt;
      this.markNullableFieldAsSet("modified_at");
      return this;
    }

    @Override
    public Builder description(String description) {
      this.description = description;
      return this;
    }

    @Override
    public Builder size(Long size) {
      this.size = size;
      return this;
    }

    @Override
    public Builder pathCollection(FolderPathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    @Override
    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    @Override
    public Builder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    @Override
    public Builder trashedAt(Date trashedAt) {
      this.trashedAt = trashedAt;
      this.markNullableFieldAsSet("trashed_at");
      return this;
    }

    @Override
    public Builder purgedAt(Date purgedAt) {
      this.purgedAt = purgedAt;
      this.markNullableFieldAsSet("purged_at");
      return this;
    }

    @Override
    public Builder contentCreatedAt(Date contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      this.markNullableFieldAsSet("content_created_at");
      return this;
    }

    @Override
    public Builder contentModifiedAt(Date contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      this.markNullableFieldAsSet("content_modified_at");
      return this;
    }

    @Override
    public Builder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    @Override
    public Builder sharedLink(FolderSharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    @Override
    public Builder folderUploadEmail(FolderFolderUploadEmailField folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      this.markNullableFieldAsSet("folder_upload_email");
      return this;
    }

    @Override
    public Builder parent(FolderMini parent) {
      this.parent = parent;
      this.markNullableFieldAsSet("parent");
      return this;
    }

    @Override
    public Builder itemStatus(FolderItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<FolderItemStatusField>(itemStatus);
      return this;
    }

    @Override
    public Builder itemStatus(EnumWrapper<FolderItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    @Override
    public Builder itemCollection(Items itemCollection) {
      this.itemCollection = itemCollection;
      return this;
    }

    public FolderFull build() {
      return new FolderFull(this);
    }
  }

  public static class AllowedSharedLinkAccessLevelsDeserializer
      extends JsonDeserializer<List<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>>> {

    public final JsonDeserializer<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>>
        elementDeserializer;

    public AllowedSharedLinkAccessLevelsDeserializer() {
      super();
      this.elementDeserializer =
          new FolderFullAllowedSharedLinkAccessLevelsField
              .FolderFullAllowedSharedLinkAccessLevelsFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class AllowedSharedLinkAccessLevelsSerializer
      extends JsonSerializer<List<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>>> {

    public final JsonSerializer<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>>
        elementSerializer;

    public AllowedSharedLinkAccessLevelsSerializer() {
      super();
      this.elementSerializer =
          new FolderFullAllowedSharedLinkAccessLevelsField
              .FolderFullAllowedSharedLinkAccessLevelsFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<FolderFullAllowedSharedLinkAccessLevelsField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }

  public static class AllowedInviteeRolesDeserializer
      extends JsonDeserializer<List<EnumWrapper<FolderFullAllowedInviteeRolesField>>> {

    public final JsonDeserializer<EnumWrapper<FolderFullAllowedInviteeRolesField>>
        elementDeserializer;

    public AllowedInviteeRolesDeserializer() {
      super();
      this.elementDeserializer =
          new FolderFullAllowedInviteeRolesField.FolderFullAllowedInviteeRolesFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<FolderFullAllowedInviteeRolesField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<FolderFullAllowedInviteeRolesField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class AllowedInviteeRolesSerializer
      extends JsonSerializer<List<EnumWrapper<FolderFullAllowedInviteeRolesField>>> {

    public final JsonSerializer<EnumWrapper<FolderFullAllowedInviteeRolesField>> elementSerializer;

    public AllowedInviteeRolesSerializer() {
      super();
      this.elementSerializer =
          new FolderFullAllowedInviteeRolesField.FolderFullAllowedInviteeRolesFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<FolderFullAllowedInviteeRolesField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<FolderFullAllowedInviteeRolesField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
