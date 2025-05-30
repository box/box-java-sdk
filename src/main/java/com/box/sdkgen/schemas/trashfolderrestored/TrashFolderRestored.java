package com.box.sdkgen.schemas.trashfolderrestored;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class TrashFolderRestored extends SerializableObject {

  protected String id;

  protected String etag;

  @JsonDeserialize(
      using = TrashFolderRestoredTypeField.TrashFolderRestoredTypeFieldDeserializer.class)
  @JsonSerialize(using = TrashFolderRestoredTypeField.TrashFolderRestoredTypeFieldSerializer.class)
  protected EnumWrapper<TrashFolderRestoredTypeField> type;

  @JsonProperty("sequence_id")
  protected String sequenceId;

  protected String name;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  protected String description;

  protected Long size;

  @JsonProperty("path_collection")
  protected TrashFolderRestoredPathCollectionField pathCollection;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("modified_by")
  protected UserMini modifiedBy;

  @JsonProperty("trashed_at")
  protected String trashedAt;

  @JsonProperty("purged_at")
  protected String purgedAt;

  @JsonProperty("content_created_at")
  protected String contentCreatedAt;

  @JsonProperty("content_modified_at")
  protected String contentModifiedAt;

  @JsonProperty("owned_by")
  protected UserMini ownedBy;

  @JsonProperty("shared_link")
  protected String sharedLink;

  @JsonProperty("folder_upload_email")
  protected String folderUploadEmail;

  protected FolderMini parent;

  @JsonDeserialize(
      using =
          TrashFolderRestoredItemStatusField.TrashFolderRestoredItemStatusFieldDeserializer.class)
  @JsonSerialize(
      using = TrashFolderRestoredItemStatusField.TrashFolderRestoredItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected EnumWrapper<TrashFolderRestoredItemStatusField> itemStatus;

  public TrashFolderRestored() {
    super();
  }

  protected TrashFolderRestored(TrashFolderRestoredBuilder builder) {
    super();
    this.id = builder.id;
    this.etag = builder.etag;
    this.type = builder.type;
    this.sequenceId = builder.sequenceId;
    this.name = builder.name;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.description = builder.description;
    this.size = builder.size;
    this.pathCollection = builder.pathCollection;
    this.createdBy = builder.createdBy;
    this.modifiedBy = builder.modifiedBy;
    this.trashedAt = builder.trashedAt;
    this.purgedAt = builder.purgedAt;
    this.contentCreatedAt = builder.contentCreatedAt;
    this.contentModifiedAt = builder.contentModifiedAt;
    this.ownedBy = builder.ownedBy;
    this.sharedLink = builder.sharedLink;
    this.folderUploadEmail = builder.folderUploadEmail;
    this.parent = builder.parent;
    this.itemStatus = builder.itemStatus;
  }

  public String getId() {
    return id;
  }

  public String getEtag() {
    return etag;
  }

  public EnumWrapper<TrashFolderRestoredTypeField> getType() {
    return type;
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getName() {
    return name;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public String getDescription() {
    return description;
  }

  public Long getSize() {
    return size;
  }

  public TrashFolderRestoredPathCollectionField getPathCollection() {
    return pathCollection;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public UserMini getModifiedBy() {
    return modifiedBy;
  }

  public String getTrashedAt() {
    return trashedAt;
  }

  public String getPurgedAt() {
    return purgedAt;
  }

  public String getContentCreatedAt() {
    return contentCreatedAt;
  }

  public String getContentModifiedAt() {
    return contentModifiedAt;
  }

  public UserMini getOwnedBy() {
    return ownedBy;
  }

  public String getSharedLink() {
    return sharedLink;
  }

  public String getFolderUploadEmail() {
    return folderUploadEmail;
  }

  public FolderMini getParent() {
    return parent;
  }

  public EnumWrapper<TrashFolderRestoredItemStatusField> getItemStatus() {
    return itemStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrashFolderRestored casted = (TrashFolderRestored) o;
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
        && Objects.equals(itemStatus, casted.itemStatus);
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
        itemStatus);
  }

  @Override
  public String toString() {
    return "TrashFolderRestored{"
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
        + "}";
  }

  public static class TrashFolderRestoredBuilder {

    protected String id;

    protected String etag;

    protected EnumWrapper<TrashFolderRestoredTypeField> type;

    protected String sequenceId;

    protected String name;

    protected String createdAt;

    protected String modifiedAt;

    protected String description;

    protected Long size;

    protected TrashFolderRestoredPathCollectionField pathCollection;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected String trashedAt;

    protected String purgedAt;

    protected String contentCreatedAt;

    protected String contentModifiedAt;

    protected UserMini ownedBy;

    protected String sharedLink;

    protected String folderUploadEmail;

    protected FolderMini parent;

    protected EnumWrapper<TrashFolderRestoredItemStatusField> itemStatus;

    public TrashFolderRestoredBuilder id(String id) {
      this.id = id;
      return this;
    }

    public TrashFolderRestoredBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public TrashFolderRestoredBuilder type(TrashFolderRestoredTypeField type) {
      this.type = new EnumWrapper<TrashFolderRestoredTypeField>(type);
      return this;
    }

    public TrashFolderRestoredBuilder type(EnumWrapper<TrashFolderRestoredTypeField> type) {
      this.type = type;
      return this;
    }

    public TrashFolderRestoredBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public TrashFolderRestoredBuilder name(String name) {
      this.name = name;
      return this;
    }

    public TrashFolderRestoredBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public TrashFolderRestoredBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public TrashFolderRestoredBuilder description(String description) {
      this.description = description;
      return this;
    }

    public TrashFolderRestoredBuilder size(Long size) {
      this.size = size;
      return this;
    }

    public TrashFolderRestoredBuilder pathCollection(
        TrashFolderRestoredPathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    public TrashFolderRestoredBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public TrashFolderRestoredBuilder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public TrashFolderRestoredBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public TrashFolderRestoredBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public TrashFolderRestoredBuilder contentCreatedAt(String contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public TrashFolderRestoredBuilder contentModifiedAt(String contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public TrashFolderRestoredBuilder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public TrashFolderRestoredBuilder sharedLink(String sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public TrashFolderRestoredBuilder folderUploadEmail(String folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      return this;
    }

    public TrashFolderRestoredBuilder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public TrashFolderRestoredBuilder itemStatus(TrashFolderRestoredItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<TrashFolderRestoredItemStatusField>(itemStatus);
      return this;
    }

    public TrashFolderRestoredBuilder itemStatus(
        EnumWrapper<TrashFolderRestoredItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    public TrashFolderRestored build() {
      return new TrashFolderRestored(this);
    }
  }
}
