package com.box.sdkgen.schemas.folder;

import com.box.sdkgen.schemas.folderbase.FolderBaseTypeField;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.items.Items;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class Folder extends FolderMini {

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  protected String description;

  protected Long size;

  @JsonProperty("path_collection")
  protected FolderPathCollectionField pathCollection;

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
  protected FolderSharedLinkField sharedLink;

  @JsonProperty("folder_upload_email")
  protected FolderFolderUploadEmailField folderUploadEmail;

  protected FolderMini parent;

  @JsonDeserialize(using = FolderItemStatusField.FolderItemStatusFieldDeserializer.class)
  @JsonSerialize(using = FolderItemStatusField.FolderItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected EnumWrapper<FolderItemStatusField> itemStatus;

  @JsonProperty("item_collection")
  protected Items itemCollection;

  public Folder(@JsonProperty("id") String id) {
    super(id);
  }

  protected Folder(FolderBuilder builder) {
    super(builder);
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
    this.itemCollection = builder.itemCollection;
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

  public FolderPathCollectionField getPathCollection() {
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

  public FolderSharedLinkField getSharedLink() {
    return sharedLink;
  }

  public FolderFolderUploadEmailField getFolderUploadEmail() {
    return folderUploadEmail;
  }

  public FolderMini getParent() {
    return parent;
  }

  public EnumWrapper<FolderItemStatusField> getItemStatus() {
    return itemStatus;
  }

  public Items getItemCollection() {
    return itemCollection;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Folder casted = (Folder) o;
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
        && Objects.equals(itemCollection, casted.itemCollection);
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
        itemCollection);
  }

  @Override
  public String toString() {
    return "Folder{"
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
        + "}";
  }

  public static class FolderBuilder extends FolderMiniBuilder {

    protected String createdAt;

    protected String modifiedAt;

    protected String description;

    protected Long size;

    protected FolderPathCollectionField pathCollection;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected String trashedAt;

    protected String purgedAt;

    protected String contentCreatedAt;

    protected String contentModifiedAt;

    protected UserMini ownedBy;

    protected FolderSharedLinkField sharedLink;

    protected FolderFolderUploadEmailField folderUploadEmail;

    protected FolderMini parent;

    protected EnumWrapper<FolderItemStatusField> itemStatus;

    protected Items itemCollection;

    public FolderBuilder(String id) {
      super(id);
    }

    public FolderBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public FolderBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public FolderBuilder description(String description) {
      this.description = description;
      return this;
    }

    public FolderBuilder size(Long size) {
      this.size = size;
      return this;
    }

    public FolderBuilder pathCollection(FolderPathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    public FolderBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public FolderBuilder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public FolderBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public FolderBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public FolderBuilder contentCreatedAt(String contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public FolderBuilder contentModifiedAt(String contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public FolderBuilder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public FolderBuilder sharedLink(FolderSharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public FolderBuilder folderUploadEmail(FolderFolderUploadEmailField folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      return this;
    }

    public FolderBuilder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public FolderBuilder itemStatus(FolderItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<FolderItemStatusField>(itemStatus);
      return this;
    }

    public FolderBuilder itemStatus(EnumWrapper<FolderItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    public FolderBuilder itemCollection(Items itemCollection) {
      this.itemCollection = itemCollection;
      return this;
    }

    @Override
    public FolderBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    @Override
    public FolderBuilder type(FolderBaseTypeField type) {
      this.type = new EnumWrapper<FolderBaseTypeField>(type);
      return this;
    }

    @Override
    public FolderBuilder type(EnumWrapper<FolderBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public FolderBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    @Override
    public FolderBuilder name(String name) {
      this.name = name;
      return this;
    }

    public Folder build() {
      return new Folder(this);
    }
  }
}
