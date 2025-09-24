package com.box.sdkgen.schemas.metadataqueryresultitem;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

@JsonDeserialize(using = MetadataQueryResultItem.MetadataQueryResultItemDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class MetadataQueryResultItem extends OneOfTwo<FileFull, FolderFull> {

  protected final List<String> tags;

  protected final Boolean isAccessibleViaSharedLink;

  protected final List<String> allowedInviteeRoles;

  protected final Boolean isExternallyOwned;

  protected final Boolean hasCollaborations;

  protected final Boolean isAssociatedWithAppItem;

  protected final String description;

  protected final Long size;

  protected final OffsetDateTime createdAt;

  protected final OffsetDateTime modifiedAt;

  protected final OffsetDateTime trashedAt;

  protected final OffsetDateTime purgedAt;

  protected final OffsetDateTime contentCreatedAt;

  protected final OffsetDateTime contentModifiedAt;

  protected final UserMini createdBy;

  protected final UserMini modifiedBy;

  protected final UserMini ownedBy;

  protected final FolderMini parent;

  protected final String itemStatus;

  protected final String sequenceId;

  protected final String name;

  protected final String id;

  protected final String etag;

  protected final String type;

  public MetadataQueryResultItem(FileFull fileFull) {
    super(fileFull, null);
    this.tags = fileFull.getTags();
    this.isAccessibleViaSharedLink = fileFull.getIsAccessibleViaSharedLink();
    this.allowedInviteeRoles = EnumWrapper.convertToString(fileFull.getAllowedInviteeRoles());
    this.isExternallyOwned = fileFull.getIsExternallyOwned();
    this.hasCollaborations = fileFull.getHasCollaborations();
    this.isAssociatedWithAppItem = fileFull.getIsAssociatedWithAppItem();
    this.description = fileFull.getDescription();
    this.size = fileFull.getSize();
    this.createdAt = fileFull.getCreatedAt();
    this.modifiedAt = fileFull.getModifiedAt();
    this.trashedAt = fileFull.getTrashedAt();
    this.purgedAt = fileFull.getPurgedAt();
    this.contentCreatedAt = fileFull.getContentCreatedAt();
    this.contentModifiedAt = fileFull.getContentModifiedAt();
    this.createdBy = fileFull.getCreatedBy();
    this.modifiedBy = fileFull.getModifiedBy();
    this.ownedBy = fileFull.getOwnedBy();
    this.parent = fileFull.getParent();
    this.itemStatus = EnumWrapper.convertToString(fileFull.getItemStatus());
    this.sequenceId = fileFull.getSequenceId();
    this.name = fileFull.getName();
    this.id = fileFull.getId();
    this.etag = fileFull.getEtag();
    this.type = EnumWrapper.convertToString(fileFull.getType());
  }

  public MetadataQueryResultItem(FolderFull folderFull) {
    super(null, folderFull);
    this.tags = folderFull.getTags();
    this.isAccessibleViaSharedLink = folderFull.getIsAccessibleViaSharedLink();
    this.allowedInviteeRoles = EnumWrapper.convertToString(folderFull.getAllowedInviteeRoles());
    this.isExternallyOwned = folderFull.getIsExternallyOwned();
    this.hasCollaborations = folderFull.getHasCollaborations();
    this.isAssociatedWithAppItem = folderFull.getIsAssociatedWithAppItem();
    this.description = folderFull.getDescription();
    this.size = folderFull.getSize();
    this.createdAt = folderFull.getCreatedAt();
    this.modifiedAt = folderFull.getModifiedAt();
    this.trashedAt = folderFull.getTrashedAt();
    this.purgedAt = folderFull.getPurgedAt();
    this.contentCreatedAt = folderFull.getContentCreatedAt();
    this.contentModifiedAt = folderFull.getContentModifiedAt();
    this.createdBy = folderFull.getCreatedBy();
    this.modifiedBy = folderFull.getModifiedBy();
    this.ownedBy = folderFull.getOwnedBy();
    this.parent = folderFull.getParent();
    this.itemStatus = EnumWrapper.convertToString(folderFull.getItemStatus());
    this.sequenceId = folderFull.getSequenceId();
    this.name = folderFull.getName();
    this.id = folderFull.getId();
    this.etag = folderFull.getEtag();
    this.type = EnumWrapper.convertToString(folderFull.getType());
  }

  public boolean isFileFull() {
    return value0 != null;
  }

  public FileFull getFileFull() {
    return value0;
  }

  public boolean isFolderFull() {
    return value1 != null;
  }

  public FolderFull getFolderFull() {
    return value1;
  }

  public List<String> getTags() {
    return tags;
  }

  public boolean getIsAccessibleViaSharedLink() {
    return isAccessibleViaSharedLink;
  }

  public List<String> getAllowedInviteeRoles() {
    return allowedInviteeRoles;
  }

  public boolean getIsExternallyOwned() {
    return isExternallyOwned;
  }

  public boolean getHasCollaborations() {
    return hasCollaborations;
  }

  public boolean getIsAssociatedWithAppItem() {
    return isAssociatedWithAppItem;
  }

  public String getDescription() {
    return description;
  }

  public long getSize() {
    return size;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  public OffsetDateTime getTrashedAt() {
    return trashedAt;
  }

  public OffsetDateTime getPurgedAt() {
    return purgedAt;
  }

  public OffsetDateTime getContentCreatedAt() {
    return contentCreatedAt;
  }

  public OffsetDateTime getContentModifiedAt() {
    return contentModifiedAt;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public UserMini getModifiedBy() {
    return modifiedBy;
  }

  public UserMini getOwnedBy() {
    return ownedBy;
  }

  public FolderMini getParent() {
    return parent;
  }

  public String getItemStatus() {
    return itemStatus;
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getEtag() {
    return etag;
  }

  public String getType() {
    return type;
  }

  static class MetadataQueryResultItemDeserializer
      extends JsonDeserializer<MetadataQueryResultItem> {

    public MetadataQueryResultItemDeserializer() {
      super();
    }

    @Override
    public MetadataQueryResultItem deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "file":
            return new MetadataQueryResultItem(JsonManager.deserialize(node, FileFull.class));
          case "folder":
            return new MetadataQueryResultItem(JsonManager.deserialize(node, FolderFull.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize MetadataQueryResultItem");
    }
  }
}
