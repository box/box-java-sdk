package com.box.sdkgen.schemas.trashfolder;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class TrashFolder extends SerializableObject {

  protected final String id;

  protected String etag;

  @JsonDeserialize(using = TrashFolderTypeField.TrashFolderTypeFieldDeserializer.class)
  @JsonSerialize(using = TrashFolderTypeField.TrashFolderTypeFieldSerializer.class)
  protected EnumWrapper<TrashFolderTypeField> type;

  @JsonProperty("sequence_id")
  protected String sequenceId;

  protected final String name;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  protected final String description;

  protected final long size;

  @JsonProperty("path_collection")
  protected final TrashFolderPathCollectionField pathCollection;

  @JsonProperty("created_by")
  protected final UserMini createdBy;

  @JsonProperty("modified_by")
  protected final UserMini modifiedBy;

  @JsonProperty("trashed_at")
  protected String trashedAt;

  @JsonProperty("purged_at")
  protected String purgedAt;

  @JsonProperty("content_created_at")
  protected String contentCreatedAt;

  @JsonProperty("content_modified_at")
  protected String contentModifiedAt;

  @JsonProperty("owned_by")
  protected final UserMini ownedBy;

  @JsonProperty("shared_link")
  protected String sharedLink;

  @JsonProperty("folder_upload_email")
  protected String folderUploadEmail;

  protected FolderMini parent;

  @JsonDeserialize(using = TrashFolderItemStatusField.TrashFolderItemStatusFieldDeserializer.class)
  @JsonSerialize(using = TrashFolderItemStatusField.TrashFolderItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected final EnumWrapper<TrashFolderItemStatusField> itemStatus;

  public TrashFolder(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("description") String description,
      @JsonProperty("size") long size,
      @JsonProperty("path_collection") TrashFolderPathCollectionField pathCollection,
      @JsonProperty("created_by") UserMini createdBy,
      @JsonProperty("modified_by") UserMini modifiedBy,
      @JsonProperty("owned_by") UserMini ownedBy,
      @JsonProperty("item_status") EnumWrapper<TrashFolderItemStatusField> itemStatus) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
    this.size = size;
    this.pathCollection = pathCollection;
    this.createdBy = createdBy;
    this.modifiedBy = modifiedBy;
    this.ownedBy = ownedBy;
    this.itemStatus = itemStatus;
    this.type = new EnumWrapper<TrashFolderTypeField>(TrashFolderTypeField.FOLDER);
  }

  public TrashFolder(
      String id,
      String name,
      String description,
      long size,
      TrashFolderPathCollectionField pathCollection,
      UserMini createdBy,
      UserMini modifiedBy,
      UserMini ownedBy,
      TrashFolderItemStatusField itemStatus) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
    this.size = size;
    this.pathCollection = pathCollection;
    this.createdBy = createdBy;
    this.modifiedBy = modifiedBy;
    this.ownedBy = ownedBy;
    this.itemStatus = new EnumWrapper<TrashFolderItemStatusField>(itemStatus);
    this.type = new EnumWrapper<TrashFolderTypeField>(TrashFolderTypeField.FOLDER);
  }

  protected TrashFolder(TrashFolderBuilder builder) {
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

  public EnumWrapper<TrashFolderTypeField> getType() {
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

  public long getSize() {
    return size;
  }

  public TrashFolderPathCollectionField getPathCollection() {
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

  public EnumWrapper<TrashFolderItemStatusField> getItemStatus() {
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
    TrashFolder casted = (TrashFolder) o;
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
    return "TrashFolder{"
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

  public static class TrashFolderBuilder {

    protected final String id;

    protected String etag;

    protected EnumWrapper<TrashFolderTypeField> type;

    protected String sequenceId;

    protected final String name;

    protected String createdAt;

    protected String modifiedAt;

    protected final String description;

    protected final long size;

    protected final TrashFolderPathCollectionField pathCollection;

    protected final UserMini createdBy;

    protected final UserMini modifiedBy;

    protected String trashedAt;

    protected String purgedAt;

    protected String contentCreatedAt;

    protected String contentModifiedAt;

    protected final UserMini ownedBy;

    protected String sharedLink;

    protected String folderUploadEmail;

    protected FolderMini parent;

    protected final EnumWrapper<TrashFolderItemStatusField> itemStatus;

    public TrashFolderBuilder(
        String id,
        String name,
        String description,
        long size,
        TrashFolderPathCollectionField pathCollection,
        UserMini createdBy,
        UserMini modifiedBy,
        UserMini ownedBy,
        EnumWrapper<TrashFolderItemStatusField> itemStatus) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.size = size;
      this.pathCollection = pathCollection;
      this.createdBy = createdBy;
      this.modifiedBy = modifiedBy;
      this.ownedBy = ownedBy;
      this.itemStatus = itemStatus;
      this.type = new EnumWrapper<TrashFolderTypeField>(TrashFolderTypeField.FOLDER);
    }

    public TrashFolderBuilder(
        String id,
        String name,
        String description,
        long size,
        TrashFolderPathCollectionField pathCollection,
        UserMini createdBy,
        UserMini modifiedBy,
        UserMini ownedBy,
        TrashFolderItemStatusField itemStatus) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.size = size;
      this.pathCollection = pathCollection;
      this.createdBy = createdBy;
      this.modifiedBy = modifiedBy;
      this.ownedBy = ownedBy;
      this.itemStatus = new EnumWrapper<TrashFolderItemStatusField>(itemStatus);
      this.type = new EnumWrapper<TrashFolderTypeField>(TrashFolderTypeField.FOLDER);
    }

    public TrashFolderBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public TrashFolderBuilder type(TrashFolderTypeField type) {
      this.type = new EnumWrapper<TrashFolderTypeField>(type);
      return this;
    }

    public TrashFolderBuilder type(EnumWrapper<TrashFolderTypeField> type) {
      this.type = type;
      return this;
    }

    public TrashFolderBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public TrashFolderBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public TrashFolderBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public TrashFolderBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public TrashFolderBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public TrashFolderBuilder contentCreatedAt(String contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public TrashFolderBuilder contentModifiedAt(String contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public TrashFolderBuilder sharedLink(String sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public TrashFolderBuilder folderUploadEmail(String folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      return this;
    }

    public TrashFolderBuilder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public TrashFolder build() {
      return new TrashFolder(this);
    }
  }
}
