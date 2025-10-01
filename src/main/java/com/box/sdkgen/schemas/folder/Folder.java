package com.box.sdkgen.schemas.folder;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.folderbase.FolderBaseTypeField;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.items.Items;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** A standard representation of a folder, as returned from any folder API endpoints by default. */
@JsonFilter("nullablePropertyFilter")
public class Folder extends FolderMini {

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

  protected String description;

  /**
   * The folder size in bytes.
   *
   * <p>Be careful parsing this integer as its value can get very large.
   */
  protected Long size;

  @JsonProperty("path_collection")
  protected FolderPathCollectionField pathCollection;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("modified_by")
  protected UserMini modifiedBy;

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
  protected UserMini ownedBy;

  @JsonProperty("shared_link")
  @Nullable
  protected FolderSharedLinkField sharedLink;

  /**
   * The `folder_upload_email` parameter is not `null` if one of the following options is **true**:
   *
   * <p>* The **Allow uploads to this folder via email** and the **Only allow email uploads from
   * collaborators in this folder** are [enabled for a folder in the Admin
   * Console](https://support.box.com/hc/en-us/articles/360043697534-Upload-to-Box-Through-Email),
   * and the user has at least **Upload** permissions granted.
   *
   * <p>* The **Allow uploads to this folder via email** setting is enabled for a folder in the
   * Admin Console, and the **Only allow email uploads from collaborators in this folder** setting
   * is deactivated (unchecked).
   *
   * <p>If the conditions are not met, the parameter will have the following value:
   * `folder_upload_email: null`.
   */
  @JsonProperty("folder_upload_email")
  @Nullable
  protected FolderFolderUploadEmailField folderUploadEmail;

  @Nullable protected FolderMini parent;

  /**
   * Defines if this item has been deleted or not.
   *
   * <p>* `active` when the item has is not in the trash * `trashed` when the item has been moved to
   * the trash but not deleted * `deleted` when the item has been permanently deleted.
   */
  @JsonDeserialize(using = FolderItemStatusField.FolderItemStatusFieldDeserializer.class)
  @JsonSerialize(using = FolderItemStatusField.FolderItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected EnumWrapper<FolderItemStatusField> itemStatus;

  @JsonProperty("item_collection")
  protected Items itemCollection;

  public Folder(@JsonProperty("id") String id) {
    super(id);
  }

  protected Folder(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends FolderMini.Builder {

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected String description;

    protected Long size;

    protected FolderPathCollectionField pathCollection;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected OffsetDateTime trashedAt;

    protected OffsetDateTime purgedAt;

    protected OffsetDateTime contentCreatedAt;

    protected OffsetDateTime contentModifiedAt;

    protected UserMini ownedBy;

    protected FolderSharedLinkField sharedLink;

    protected FolderFolderUploadEmailField folderUploadEmail;

    protected FolderMini parent;

    protected EnumWrapper<FolderItemStatusField> itemStatus;

    protected Items itemCollection;

    public Builder(String id) {
      super(id);
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

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder size(Long size) {
      this.size = size;
      return this;
    }

    public Builder pathCollection(FolderPathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
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

    public Builder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public Builder sharedLink(FolderSharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    public Builder folderUploadEmail(FolderFolderUploadEmailField folderUploadEmail) {
      this.folderUploadEmail = folderUploadEmail;
      this.markNullableFieldAsSet("folder_upload_email");
      return this;
    }

    public Builder parent(FolderMini parent) {
      this.parent = parent;
      this.markNullableFieldAsSet("parent");
      return this;
    }

    public Builder itemStatus(FolderItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<FolderItemStatusField>(itemStatus);
      return this;
    }

    public Builder itemStatus(EnumWrapper<FolderItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    public Builder itemCollection(Items itemCollection) {
      this.itemCollection = itemCollection;
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

    public Folder build() {
      return new Folder(this);
    }
  }
}
