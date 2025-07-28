package com.box.sdkgen.schemas.trashweblinkrestored;

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
public class TrashWebLinkRestored extends SerializableObject {

  @JsonDeserialize(
      using = TrashWebLinkRestoredTypeField.TrashWebLinkRestoredTypeFieldDeserializer.class)
  @JsonSerialize(
      using = TrashWebLinkRestoredTypeField.TrashWebLinkRestoredTypeFieldSerializer.class)
  protected EnumWrapper<TrashWebLinkRestoredTypeField> type;

  protected String id;

  @JsonProperty("sequence_id")
  protected final String sequenceId;

  protected String etag;

  protected String name;

  protected String url;

  protected FolderMini parent;

  protected String description;

  @JsonProperty("path_collection")
  protected final TrashWebLinkRestoredPathCollectionField pathCollection;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date modifiedAt;

  @JsonProperty("trashed_at")
  @Nullable
  protected String trashedAt;

  @JsonProperty("purged_at")
  @Nullable
  protected String purgedAt;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("modified_by")
  protected UserMini modifiedBy;

  @JsonProperty("owned_by")
  protected UserMini ownedBy;

  @JsonProperty("shared_link")
  @Nullable
  protected String sharedLink;

  @JsonDeserialize(
      using =
          TrashWebLinkRestoredItemStatusField.TrashWebLinkRestoredItemStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          TrashWebLinkRestoredItemStatusField.TrashWebLinkRestoredItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected EnumWrapper<TrashWebLinkRestoredItemStatusField> itemStatus;

  public TrashWebLinkRestored(
      @JsonProperty("sequence_id") String sequenceId,
      @JsonProperty("path_collection") TrashWebLinkRestoredPathCollectionField pathCollection) {
    super();
    this.sequenceId = sequenceId;
    this.pathCollection = pathCollection;
  }

  protected TrashWebLinkRestored(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.sequenceId = builder.sequenceId;
    this.etag = builder.etag;
    this.name = builder.name;
    this.url = builder.url;
    this.parent = builder.parent;
    this.description = builder.description;
    this.pathCollection = builder.pathCollection;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.trashedAt = builder.trashedAt;
    this.purgedAt = builder.purgedAt;
    this.createdBy = builder.createdBy;
    this.modifiedBy = builder.modifiedBy;
    this.ownedBy = builder.ownedBy;
    this.sharedLink = builder.sharedLink;
    this.itemStatus = builder.itemStatus;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TrashWebLinkRestoredTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getEtag() {
    return etag;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public FolderMini getParent() {
    return parent;
  }

  public String getDescription() {
    return description;
  }

  public TrashWebLinkRestoredPathCollectionField getPathCollection() {
    return pathCollection;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  public String getTrashedAt() {
    return trashedAt;
  }

  public String getPurgedAt() {
    return purgedAt;
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

  public EnumWrapper<TrashWebLinkRestoredItemStatusField> getItemStatus() {
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
    TrashWebLinkRestored casted = (TrashWebLinkRestored) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(sequenceId, casted.sequenceId)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(name, casted.name)
        && Objects.equals(url, casted.url)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(description, casted.description)
        && Objects.equals(pathCollection, casted.pathCollection)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(trashedAt, casted.trashedAt)
        && Objects.equals(purgedAt, casted.purgedAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(modifiedBy, casted.modifiedBy)
        && Objects.equals(ownedBy, casted.ownedBy)
        && Objects.equals(sharedLink, casted.sharedLink)
        && Objects.equals(itemStatus, casted.itemStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type,
        id,
        sequenceId,
        etag,
        name,
        url,
        parent,
        description,
        pathCollection,
        createdAt,
        modifiedAt,
        trashedAt,
        purgedAt,
        createdBy,
        modifiedBy,
        ownedBy,
        sharedLink,
        itemStatus);
  }

  @Override
  public String toString() {
    return "TrashWebLinkRestored{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "sequenceId='"
        + sequenceId
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "url='"
        + url
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "description='"
        + description
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
        + "itemStatus='"
        + itemStatus
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<TrashWebLinkRestoredTypeField> type;

    protected String id;

    protected final String sequenceId;

    protected String etag;

    protected String name;

    protected String url;

    protected FolderMini parent;

    protected String description;

    protected final TrashWebLinkRestoredPathCollectionField pathCollection;

    protected Date createdAt;

    protected Date modifiedAt;

    protected String trashedAt;

    protected String purgedAt;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected UserMini ownedBy;

    protected String sharedLink;

    protected EnumWrapper<TrashWebLinkRestoredItemStatusField> itemStatus;

    public Builder(String sequenceId, TrashWebLinkRestoredPathCollectionField pathCollection) {
      super();
      this.sequenceId = sequenceId;
      this.pathCollection = pathCollection;
    }

    public Builder type(TrashWebLinkRestoredTypeField type) {
      this.type = new EnumWrapper<TrashWebLinkRestoredTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TrashWebLinkRestoredTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(Date modifiedAt) {
      this.modifiedAt = modifiedAt;
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

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public Builder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public Builder sharedLink(String sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    public Builder itemStatus(TrashWebLinkRestoredItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<TrashWebLinkRestoredItemStatusField>(itemStatus);
      return this;
    }

    public Builder itemStatus(EnumWrapper<TrashWebLinkRestoredItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    public TrashWebLinkRestored build() {
      return new TrashWebLinkRestored(this);
    }
  }
}
