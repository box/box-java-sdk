package com.box.sdkgen.schemas.trashfolder;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** Represents a trashed folder. */
@JsonFilter("nullablePropertyFilter")
public class TrashFolder extends SerializableObject {

  /**
   * The unique identifier that represent a folder.
   *
   * <p>The ID for any folder can be determined by visiting a folder in the web application and
   * copying the ID from the URL. For example, for the URL `https://*.app.box.com/folders/123` the
   * `folder_id` is `123`.
   */
  protected final String id;

  /**
   * The HTTP `etag` of this folder. This can be used within some API endpoints in the `If-Match`
   * and `If-None-Match` headers to only perform changes on the folder if (no) changes have
   * happened.
   */
  @Nullable protected String etag;

  /** The value will always be `folder`. */
  @JsonDeserialize(using = TrashFolderTypeField.TrashFolderTypeFieldDeserializer.class)
  @JsonSerialize(using = TrashFolderTypeField.TrashFolderTypeFieldSerializer.class)
  protected EnumWrapper<TrashFolderTypeField> type;

  @JsonProperty("sequence_id")
  protected String sequenceId;

  /** The name of the folder. */
  protected final String name;

  /**
   * The date and time when the folder was created. This value may be `null` for some folders such
   * as the root folder or the trash folder.
   */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime createdAt;

  /**
   * The date and time when the folder was last updated. This value may be `null` for some folders
   * such as the root folder or the trash folder.
   */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime modifiedAt;

  protected final String description;

  /**
   * The folder size in bytes.
   *
   * <p>Be careful parsing this integer as its value can get very large.
   */
  protected final long size;

  @JsonProperty("path_collection")
  protected final TrashFolderPathCollectionField pathCollection;

  @JsonProperty("created_by")
  protected final UserMini createdBy;

  @JsonProperty("modified_by")
  protected final UserMini modifiedBy;

  /** The time at which this folder was put in the trash. */
  @JsonProperty("trashed_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime trashedAt;

  /** The time at which this folder is expected to be purged from the trash. */
  @JsonProperty("purged_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime purgedAt;

  /** The date and time at which this folder was originally created. */
  @JsonProperty("content_created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime contentCreatedAt;

  /** The date and time at which this folder was last updated. */
  @JsonProperty("content_modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime contentModifiedAt;

  @JsonProperty("owned_by")
  protected final UserMini ownedBy;

  /**
   * The shared link for this folder. This will be `null` if a folder has been trashed, since the
   * link will no longer be active.
   */
  @JsonProperty("shared_link")
  @Nullable
  protected String sharedLink;

  /**
   * The folder upload email for this folder. This will be `null` if a folder has been trashed,
   * since the upload will no longer work.
   */
  @JsonProperty("folder_upload_email")
  @Nullable
  protected String folderUploadEmail;

  protected FolderMini parent;

  /**
   * Defines if this item has been deleted or not.
   *
   * <p>* `active` when the item has is not in the trash * `trashed` when the item has been moved to
   * the trash but not deleted * `deleted` when the item has been permanently deleted.
   */
  @JsonDeserialize(using = TrashFolderItemStatusField.TrashFolderItemStatusFieldDeserializer.class)
  @JsonSerialize(using = TrashFolderItemStatusField.TrashFolderItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected final EnumWrapper<TrashFolderItemStatusField> itemStatus;

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

  protected TrashFolder(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected String etag;

    protected EnumWrapper<TrashFolderTypeField> type;

    protected String sequenceId;

    protected final String name;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected final String description;

    protected final long size;

    protected final TrashFolderPathCollectionField pathCollection;

    protected final UserMini createdBy;

    protected final UserMini modifiedBy;

    protected OffsetDateTime trashedAt;

    protected OffsetDateTime purgedAt;

    protected OffsetDateTime contentCreatedAt;

    protected OffsetDateTime contentModifiedAt;

    protected final UserMini ownedBy;

    protected String sharedLink;

    protected String folderUploadEmail;

    protected FolderMini parent;

    protected final EnumWrapper<TrashFolderItemStatusField> itemStatus;

    public Builder(
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

    public Builder(
        String id,
        String name,
        String description,
        long size,
        TrashFolderPathCollectionField pathCollection,
        UserMini createdBy,
        UserMini modifiedBy,
        UserMini ownedBy,
        EnumWrapper<TrashFolderItemStatusField> itemStatus) {
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

    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    public Builder type(TrashFolderTypeField type) {
      this.type = new EnumWrapper<TrashFolderTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TrashFolderTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      this.markNullableFieldAsSet("created_at");
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      this.markNullableFieldAsSet("modified_at");
      return this;
    }

    public Builder trashedAt(OffsetDateTime trashedAt) {
      this.trashedAt = trashedAt;
      this.markNullableFieldAsSet("trashed_at");
      return this;
    }

    public Builder purgedAt(OffsetDateTime purgedAt) {
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

    public Builder sharedLink(String sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    public Builder folderUploadEmail(String folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      this.markNullableFieldAsSet("folder_upload_email");
      return this;
    }

    public Builder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public TrashFolder build() {
      return new TrashFolder(this);
    }
  }
}
