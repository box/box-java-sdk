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
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrashFolder extends SerializableObject {

  protected final String id;

  @Nullable protected String etag;

  @JsonDeserialize(using = TrashFolderTypeField.TrashFolderTypeFieldDeserializer.class)
  @JsonSerialize(using = TrashFolderTypeField.TrashFolderTypeFieldSerializer.class)
  protected EnumWrapper<TrashFolderTypeField> type;

  @JsonProperty("sequence_id")
  protected String sequenceId;

  protected final String name;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected Date createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected Date modifiedAt;

  protected final String description;

  protected final long size;

  @JsonProperty("path_collection")
  protected final TrashFolderPathCollectionField pathCollection;

  @JsonProperty("created_by")
  protected final UserMini createdBy;

  @JsonProperty("modified_by")
  protected final UserMini modifiedBy;

  @JsonProperty("trashed_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected Date trashedAt;

  @JsonProperty("purged_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected Date purgedAt;

  @JsonProperty("content_created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected Date contentCreatedAt;

  @JsonProperty("content_modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected Date contentModifiedAt;

  @JsonProperty("owned_by")
  protected final UserMini ownedBy;

  @JsonProperty("shared_link")
  @Nullable
  protected String sharedLink;

  @JsonProperty("folder_upload_email")
  @Nullable
  protected String folderUploadEmail;

  protected FolderMini parent;

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

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getModifiedAt() {
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

  public Date getTrashedAt() {
    return trashedAt;
  }

  public Date getPurgedAt() {
    return purgedAt;
  }

  public Date getContentCreatedAt() {
    return contentCreatedAt;
  }

  public Date getContentModifiedAt() {
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

    protected Date createdAt;

    protected Date modifiedAt;

    protected final String description;

    protected final long size;

    protected final TrashFolderPathCollectionField pathCollection;

    protected final UserMini createdBy;

    protected final UserMini modifiedBy;

    protected Date trashedAt;

    protected Date purgedAt;

    protected Date contentCreatedAt;

    protected Date contentModifiedAt;

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

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      this.markNullableFieldAsSet("created_at");
      return this;
    }

    public Builder modifiedAt(Date modifiedAt) {
      this.modifiedAt = modifiedAt;
      this.markNullableFieldAsSet("modified_at");
      return this;
    }

    public Builder trashedAt(Date trashedAt) {
      this.trashedAt = trashedAt;
      this.markNullableFieldAsSet("trashed_at");
      return this;
    }

    public Builder purgedAt(Date purgedAt) {
      this.purgedAt = purgedAt;
      this.markNullableFieldAsSet("purged_at");
      return this;
    }

    public Builder contentCreatedAt(Date contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      this.markNullableFieldAsSet("content_created_at");
      return this;
    }

    public Builder contentModifiedAt(Date contentModifiedAt) {
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
