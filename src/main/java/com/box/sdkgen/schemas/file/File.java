package com.box.sdkgen.schemas.file;

import com.box.sdkgen.schemas.filebase.FileBaseTypeField;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class File extends FileMini {

  protected String description;

  protected Long size;

  @JsonProperty("path_collection")
  protected FilePathCollectionField pathCollection;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  @JsonProperty("trashed_at")
  protected String trashedAt;

  @JsonProperty("purged_at")
  protected String purgedAt;

  @JsonProperty("content_created_at")
  protected String contentCreatedAt;

  @JsonProperty("content_modified_at")
  protected String contentModifiedAt;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("modified_by")
  protected UserMini modifiedBy;

  @JsonProperty("owned_by")
  protected UserMini ownedBy;

  @JsonProperty("shared_link")
  protected FileSharedLinkField sharedLink;

  protected FolderMini parent;

  @JsonDeserialize(using = FileItemStatusField.FileItemStatusFieldDeserializer.class)
  @JsonSerialize(using = FileItemStatusField.FileItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected EnumWrapper<FileItemStatusField> itemStatus;

  public File(@JsonProperty("id") String id) {
    super(id);
  }

  protected File(FileBuilder builder) {
    super(builder);
    this.description = builder.description;
    this.size = builder.size;
    this.pathCollection = builder.pathCollection;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.trashedAt = builder.trashedAt;
    this.purgedAt = builder.purgedAt;
    this.contentCreatedAt = builder.contentCreatedAt;
    this.contentModifiedAt = builder.contentModifiedAt;
    this.createdBy = builder.createdBy;
    this.modifiedBy = builder.modifiedBy;
    this.ownedBy = builder.ownedBy;
    this.sharedLink = builder.sharedLink;
    this.parent = builder.parent;
    this.itemStatus = builder.itemStatus;
  }

  public String getDescription() {
    return description;
  }

  public Long getSize() {
    return size;
  }

  public FilePathCollectionField getPathCollection() {
    return pathCollection;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
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

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public UserMini getModifiedBy() {
    return modifiedBy;
  }

  public UserMini getOwnedBy() {
    return ownedBy;
  }

  public FileSharedLinkField getSharedLink() {
    return sharedLink;
  }

  public FolderMini getParent() {
    return parent;
  }

  public EnumWrapper<FileItemStatusField> getItemStatus() {
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
    File casted = (File) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(type, casted.type)
        && Objects.equals(sequenceId, casted.sequenceId)
        && Objects.equals(name, casted.name)
        && Objects.equals(sha1, casted.sha1)
        && Objects.equals(fileVersion, casted.fileVersion)
        && Objects.equals(description, casted.description)
        && Objects.equals(size, casted.size)
        && Objects.equals(pathCollection, casted.pathCollection)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(trashedAt, casted.trashedAt)
        && Objects.equals(purgedAt, casted.purgedAt)
        && Objects.equals(contentCreatedAt, casted.contentCreatedAt)
        && Objects.equals(contentModifiedAt, casted.contentModifiedAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(modifiedBy, casted.modifiedBy)
        && Objects.equals(ownedBy, casted.ownedBy)
        && Objects.equals(sharedLink, casted.sharedLink)
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
        sha1,
        fileVersion,
        description,
        size,
        pathCollection,
        createdAt,
        modifiedAt,
        trashedAt,
        purgedAt,
        contentCreatedAt,
        contentModifiedAt,
        createdBy,
        modifiedBy,
        ownedBy,
        sharedLink,
        parent,
        itemStatus);
  }

  @Override
  public String toString() {
    return "File{"
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
        + "sha1='"
        + sha1
        + '\''
        + ", "
        + "fileVersion='"
        + fileVersion
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
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
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
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "modifiedBy='"
        + modifiedBy
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
        + "parent='"
        + parent
        + '\''
        + ", "
        + "itemStatus='"
        + itemStatus
        + '\''
        + "}";
  }

  public static class FileBuilder extends FileMiniBuilder {

    protected String description;

    protected Long size;

    protected FilePathCollectionField pathCollection;

    protected String createdAt;

    protected String modifiedAt;

    protected String trashedAt;

    protected String purgedAt;

    protected String contentCreatedAt;

    protected String contentModifiedAt;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected UserMini ownedBy;

    protected FileSharedLinkField sharedLink;

    protected FolderMini parent;

    protected EnumWrapper<FileItemStatusField> itemStatus;

    public FileBuilder(String id) {
      super(id);
    }

    public FileBuilder description(String description) {
      this.description = description;
      return this;
    }

    public FileBuilder size(Long size) {
      this.size = size;
      return this;
    }

    public FileBuilder pathCollection(FilePathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    public FileBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public FileBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public FileBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public FileBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public FileBuilder contentCreatedAt(String contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public FileBuilder contentModifiedAt(String contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public FileBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public FileBuilder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public FileBuilder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public FileBuilder sharedLink(FileSharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public FileBuilder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public FileBuilder itemStatus(FileItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<FileItemStatusField>(itemStatus);
      return this;
    }

    public FileBuilder itemStatus(EnumWrapper<FileItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    @Override
    public FileBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    @Override
    public FileBuilder type(FileBaseTypeField type) {
      this.type = new EnumWrapper<FileBaseTypeField>(type);
      return this;
    }

    @Override
    public FileBuilder type(EnumWrapper<FileBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public FileBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    @Override
    public FileBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public FileBuilder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    @Override
    public FileBuilder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public File build() {
      return new File(this);
    }
  }
}
