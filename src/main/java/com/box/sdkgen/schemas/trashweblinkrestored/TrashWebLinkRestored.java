package com.box.sdkgen.schemas.trashweblinkrestored;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

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
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  @JsonProperty("trashed_at")
  protected String trashedAt;

  @JsonProperty("purged_at")
  protected String purgedAt;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("modified_by")
  protected UserMini modifiedBy;

  @JsonProperty("owned_by")
  protected UserMini ownedBy;

  @JsonProperty("shared_link")
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

  protected TrashWebLinkRestored(TrashWebLinkRestoredBuilder builder) {
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

  public static class TrashWebLinkRestoredBuilder {

    protected EnumWrapper<TrashWebLinkRestoredTypeField> type;

    protected String id;

    protected final String sequenceId;

    protected String etag;

    protected String name;

    protected String url;

    protected FolderMini parent;

    protected String description;

    protected final TrashWebLinkRestoredPathCollectionField pathCollection;

    protected String createdAt;

    protected String modifiedAt;

    protected String trashedAt;

    protected String purgedAt;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected UserMini ownedBy;

    protected String sharedLink;

    protected EnumWrapper<TrashWebLinkRestoredItemStatusField> itemStatus;

    public TrashWebLinkRestoredBuilder(
        String sequenceId, TrashWebLinkRestoredPathCollectionField pathCollection) {
      this.sequenceId = sequenceId;
      this.pathCollection = pathCollection;
    }

    public TrashWebLinkRestoredBuilder type(TrashWebLinkRestoredTypeField type) {
      this.type = new EnumWrapper<TrashWebLinkRestoredTypeField>(type);
      return this;
    }

    public TrashWebLinkRestoredBuilder type(EnumWrapper<TrashWebLinkRestoredTypeField> type) {
      this.type = type;
      return this;
    }

    public TrashWebLinkRestoredBuilder id(String id) {
      this.id = id;
      return this;
    }

    public TrashWebLinkRestoredBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public TrashWebLinkRestoredBuilder name(String name) {
      this.name = name;
      return this;
    }

    public TrashWebLinkRestoredBuilder url(String url) {
      this.url = url;
      return this;
    }

    public TrashWebLinkRestoredBuilder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public TrashWebLinkRestoredBuilder description(String description) {
      this.description = description;
      return this;
    }

    public TrashWebLinkRestoredBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public TrashWebLinkRestoredBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public TrashWebLinkRestoredBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public TrashWebLinkRestoredBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public TrashWebLinkRestoredBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public TrashWebLinkRestoredBuilder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public TrashWebLinkRestoredBuilder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public TrashWebLinkRestoredBuilder sharedLink(String sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public TrashWebLinkRestoredBuilder itemStatus(TrashWebLinkRestoredItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<TrashWebLinkRestoredItemStatusField>(itemStatus);
      return this;
    }

    public TrashWebLinkRestoredBuilder itemStatus(
        EnumWrapper<TrashWebLinkRestoredItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    public TrashWebLinkRestored build() {
      return new TrashWebLinkRestored(this);
    }
  }
}
