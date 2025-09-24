package com.box.sdkgen.schemas.trashfilerestored;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrashFileRestored extends SerializableObject {

  protected final String id;

  @Nullable protected String etag;

  @JsonDeserialize(using = TrashFileRestoredTypeField.TrashFileRestoredTypeFieldDeserializer.class)
  @JsonSerialize(using = TrashFileRestoredTypeField.TrashFileRestoredTypeFieldSerializer.class)
  protected EnumWrapper<TrashFileRestoredTypeField> type;

  @JsonProperty("sequence_id")
  protected final String sequenceId;

  protected String name;

  protected final String sha1;

  @JsonProperty("file_version")
  protected FileVersionMini fileVersion;

  protected final String description;

  protected final long size;

  @JsonProperty("path_collection")
  protected final TrashFileRestoredPathCollectionField pathCollection;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime modifiedAt;

  @JsonProperty("trashed_at")
  @Nullable
  protected String trashedAt;

  @JsonProperty("purged_at")
  @Nullable
  protected String purgedAt;

  @JsonProperty("content_created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime contentCreatedAt;

  @JsonProperty("content_modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime contentModifiedAt;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("modified_by")
  protected final UserMini modifiedBy;

  @JsonProperty("owned_by")
  protected final UserMini ownedBy;

  @JsonProperty("shared_link")
  @Nullable
  protected String sharedLink;

  protected FolderMini parent;

  @JsonDeserialize(
      using = TrashFileRestoredItemStatusField.TrashFileRestoredItemStatusFieldDeserializer.class)
  @JsonSerialize(
      using = TrashFileRestoredItemStatusField.TrashFileRestoredItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected final EnumWrapper<TrashFileRestoredItemStatusField> itemStatus;

  public TrashFileRestored(
      String id,
      String sequenceId,
      String sha1,
      String description,
      long size,
      TrashFileRestoredPathCollectionField pathCollection,
      OffsetDateTime createdAt,
      OffsetDateTime modifiedAt,
      UserMini modifiedBy,
      UserMini ownedBy,
      TrashFileRestoredItemStatusField itemStatus) {
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
    this.itemStatus = new EnumWrapper<TrashFileRestoredItemStatusField>(itemStatus);
    this.type = new EnumWrapper<TrashFileRestoredTypeField>(TrashFileRestoredTypeField.FILE);
  }

  public TrashFileRestored(
      @JsonProperty("id") String id,
      @JsonProperty("sequence_id") String sequenceId,
      @JsonProperty("sha1") String sha1,
      @JsonProperty("description") String description,
      @JsonProperty("size") long size,
      @JsonProperty("path_collection") TrashFileRestoredPathCollectionField pathCollection,
      @JsonProperty("created_at") OffsetDateTime createdAt,
      @JsonProperty("modified_at") OffsetDateTime modifiedAt,
      @JsonProperty("modified_by") UserMini modifiedBy,
      @JsonProperty("owned_by") UserMini ownedBy,
      @JsonProperty("item_status") EnumWrapper<TrashFileRestoredItemStatusField> itemStatus) {
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
    this.type = new EnumWrapper<TrashFileRestoredTypeField>(TrashFileRestoredTypeField.FILE);
  }

  protected TrashFileRestored(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getEtag() {
    return etag;
  }

  public EnumWrapper<TrashFileRestoredTypeField> getType() {
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

  public TrashFileRestoredPathCollectionField getPathCollection() {
    return pathCollection;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  public String getTrashedAt() {
    return trashedAt;
  }

  public String getPurgedAt() {
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

  public String getSharedLink() {
    return sharedLink;
  }

  public FolderMini getParent() {
    return parent;
  }

  public EnumWrapper<TrashFileRestoredItemStatusField> getItemStatus() {
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
    TrashFileRestored casted = (TrashFileRestored) o;
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
    return "TrashFileRestored{"
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

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected String etag;

    protected EnumWrapper<TrashFileRestoredTypeField> type;

    protected final String sequenceId;

    protected String name;

    protected final String sha1;

    protected FileVersionMini fileVersion;

    protected final String description;

    protected final long size;

    protected final TrashFileRestoredPathCollectionField pathCollection;

    protected final OffsetDateTime createdAt;

    protected final OffsetDateTime modifiedAt;

    protected String trashedAt;

    protected String purgedAt;

    protected OffsetDateTime contentCreatedAt;

    protected OffsetDateTime contentModifiedAt;

    protected UserMini createdBy;

    protected final UserMini modifiedBy;

    protected final UserMini ownedBy;

    protected String sharedLink;

    protected FolderMini parent;

    protected final EnumWrapper<TrashFileRestoredItemStatusField> itemStatus;

    public Builder(
        String id,
        String sequenceId,
        String sha1,
        String description,
        long size,
        TrashFileRestoredPathCollectionField pathCollection,
        OffsetDateTime createdAt,
        OffsetDateTime modifiedAt,
        UserMini modifiedBy,
        UserMini ownedBy,
        TrashFileRestoredItemStatusField itemStatus) {
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
      this.itemStatus = new EnumWrapper<TrashFileRestoredItemStatusField>(itemStatus);
      this.type = new EnumWrapper<TrashFileRestoredTypeField>(TrashFileRestoredTypeField.FILE);
    }

    public Builder(
        String id,
        String sequenceId,
        String sha1,
        String description,
        long size,
        TrashFileRestoredPathCollectionField pathCollection,
        OffsetDateTime createdAt,
        OffsetDateTime modifiedAt,
        UserMini modifiedBy,
        UserMini ownedBy,
        EnumWrapper<TrashFileRestoredItemStatusField> itemStatus) {
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
      this.type = new EnumWrapper<TrashFileRestoredTypeField>(TrashFileRestoredTypeField.FILE);
    }

    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    public Builder type(TrashFileRestoredTypeField type) {
      this.type = new EnumWrapper<TrashFileRestoredTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TrashFileRestoredTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public Builder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      this.markNullableFieldAsSet("trashed_at");
      return this;
    }

    public Builder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      this.markNullableFieldAsSet("purged_at");
      return this;
    }

    public Builder contentCreatedAt(OffsetDateTime contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      this.markNullableFieldAsSet("content_created_at");
      return this;
    }

    public Builder contentModifiedAt(OffsetDateTime contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      this.markNullableFieldAsSet("content_modified_at");
      return this;
    }

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder sharedLink(String sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    public Builder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public TrashFileRestored build() {
      return new TrashFileRestored(this);
    }
  }
}
