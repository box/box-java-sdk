package com.box.sdkgen.schemas.trashfile;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class TrashFile extends SerializableObject {

  protected final String id;

  protected String etag;

  @JsonDeserialize(using = TrashFileTypeField.TrashFileTypeFieldDeserializer.class)
  @JsonSerialize(using = TrashFileTypeField.TrashFileTypeFieldSerializer.class)
  protected EnumWrapper<TrashFileTypeField> type;

  @JsonProperty("sequence_id")
  protected final String sequenceId;

  protected String name;

  protected final String sha1;

  @JsonProperty("file_version")
  protected FileVersionMini fileVersion;

  protected final String description;

  protected final long size;

  @JsonProperty("path_collection")
  protected final TrashFilePathCollectionField pathCollection;

  @JsonProperty("created_at")
  protected final String createdAt;

  @JsonProperty("modified_at")
  protected final String modifiedAt;

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
  protected final UserMini modifiedBy;

  @JsonProperty("owned_by")
  protected final UserMini ownedBy;

  @JsonProperty("shared_link")
  protected String sharedLink;

  protected FolderMini parent;

  @JsonDeserialize(using = TrashFileItemStatusField.TrashFileItemStatusFieldDeserializer.class)
  @JsonSerialize(using = TrashFileItemStatusField.TrashFileItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected final EnumWrapper<TrashFileItemStatusField> itemStatus;

  public TrashFile(
      @JsonProperty("id") String id,
      @JsonProperty("sequence_id") String sequenceId,
      @JsonProperty("sha1") String sha1,
      @JsonProperty("description") String description,
      @JsonProperty("size") long size,
      @JsonProperty("path_collection") TrashFilePathCollectionField pathCollection,
      @JsonProperty("created_at") String createdAt,
      @JsonProperty("modified_at") String modifiedAt,
      @JsonProperty("modified_by") UserMini modifiedBy,
      @JsonProperty("owned_by") UserMini ownedBy,
      @JsonProperty("item_status") EnumWrapper<TrashFileItemStatusField> itemStatus) {
    super();
    this.id = id;
    this.sequenceId = sequenceId;
    this.sha1 = sha1;
    this.description = description;
    this.size = size;
    this.pathCollection = pathCollection;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.modifiedBy = modifiedBy;
    this.ownedBy = ownedBy;
    this.itemStatus = itemStatus;
    this.type = new EnumWrapper<TrashFileTypeField>(TrashFileTypeField.FILE);
  }

  public TrashFile(
      String id,
      String sequenceId,
      String sha1,
      String description,
      long size,
      TrashFilePathCollectionField pathCollection,
      String createdAt,
      String modifiedAt,
      UserMini modifiedBy,
      UserMini ownedBy,
      TrashFileItemStatusField itemStatus) {
    super();
    this.id = id;
    this.sequenceId = sequenceId;
    this.sha1 = sha1;
    this.description = description;
    this.size = size;
    this.pathCollection = pathCollection;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.modifiedBy = modifiedBy;
    this.ownedBy = ownedBy;
    this.itemStatus = new EnumWrapper<TrashFileItemStatusField>(itemStatus);
    this.type = new EnumWrapper<TrashFileTypeField>(TrashFileTypeField.FILE);
  }

  protected TrashFile(TrashFileBuilder builder) {
    super();
    this.id = builder.id;
    this.etag = builder.etag;
    this.type = builder.type;
    this.sequenceId = builder.sequenceId;
    this.name = builder.name;
    this.sha1 = builder.sha1;
    this.fileVersion = builder.fileVersion;
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

  public String getId() {
    return id;
  }

  public String getEtag() {
    return etag;
  }

  public EnumWrapper<TrashFileTypeField> getType() {
    return type;
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getName() {
    return name;
  }

  public String getSha1() {
    return sha1;
  }

  public FileVersionMini getFileVersion() {
    return fileVersion;
  }

  public String getDescription() {
    return description;
  }

  public long getSize() {
    return size;
  }

  public TrashFilePathCollectionField getPathCollection() {
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

  public String getSharedLink() {
    return sharedLink;
  }

  public FolderMini getParent() {
    return parent;
  }

  public EnumWrapper<TrashFileItemStatusField> getItemStatus() {
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
    TrashFile casted = (TrashFile) o;
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
    return "TrashFile{"
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

  public static class TrashFileBuilder {

    protected final String id;

    protected String etag;

    protected EnumWrapper<TrashFileTypeField> type;

    protected final String sequenceId;

    protected String name;

    protected final String sha1;

    protected FileVersionMini fileVersion;

    protected final String description;

    protected final long size;

    protected final TrashFilePathCollectionField pathCollection;

    protected final String createdAt;

    protected final String modifiedAt;

    protected String trashedAt;

    protected String purgedAt;

    protected String contentCreatedAt;

    protected String contentModifiedAt;

    protected UserMini createdBy;

    protected final UserMini modifiedBy;

    protected final UserMini ownedBy;

    protected String sharedLink;

    protected FolderMini parent;

    protected final EnumWrapper<TrashFileItemStatusField> itemStatus;

    public TrashFileBuilder(
        String id,
        String sequenceId,
        String sha1,
        String description,
        long size,
        TrashFilePathCollectionField pathCollection,
        String createdAt,
        String modifiedAt,
        UserMini modifiedBy,
        UserMini ownedBy,
        EnumWrapper<TrashFileItemStatusField> itemStatus) {
      this.id = id;
      this.sequenceId = sequenceId;
      this.sha1 = sha1;
      this.description = description;
      this.size = size;
      this.pathCollection = pathCollection;
      this.createdAt = createdAt;
      this.modifiedAt = modifiedAt;
      this.modifiedBy = modifiedBy;
      this.ownedBy = ownedBy;
      this.itemStatus = itemStatus;
      this.type = new EnumWrapper<TrashFileTypeField>(TrashFileTypeField.FILE);
    }

    public TrashFileBuilder(
        String id,
        String sequenceId,
        String sha1,
        String description,
        long size,
        TrashFilePathCollectionField pathCollection,
        String createdAt,
        String modifiedAt,
        UserMini modifiedBy,
        UserMini ownedBy,
        TrashFileItemStatusField itemStatus) {
      this.id = id;
      this.sequenceId = sequenceId;
      this.sha1 = sha1;
      this.description = description;
      this.size = size;
      this.pathCollection = pathCollection;
      this.createdAt = createdAt;
      this.modifiedAt = modifiedAt;
      this.modifiedBy = modifiedBy;
      this.ownedBy = ownedBy;
      this.itemStatus = new EnumWrapper<TrashFileItemStatusField>(itemStatus);
      this.type = new EnumWrapper<TrashFileTypeField>(TrashFileTypeField.FILE);
    }

    public TrashFileBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public TrashFileBuilder type(TrashFileTypeField type) {
      this.type = new EnumWrapper<TrashFileTypeField>(type);
      return this;
    }

    public TrashFileBuilder type(EnumWrapper<TrashFileTypeField> type) {
      this.type = type;
      return this;
    }

    public TrashFileBuilder name(String name) {
      this.name = name;
      return this;
    }

    public TrashFileBuilder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public TrashFileBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public TrashFileBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public TrashFileBuilder contentCreatedAt(String contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      return this;
    }

    public TrashFileBuilder contentModifiedAt(String contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      return this;
    }

    public TrashFileBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public TrashFileBuilder sharedLink(String sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public TrashFileBuilder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public TrashFile build() {
      return new TrashFile(this);
    }
  }
}
