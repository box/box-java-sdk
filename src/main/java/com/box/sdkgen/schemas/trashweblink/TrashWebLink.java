package com.box.sdkgen.schemas.trashweblink;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class TrashWebLink extends SerializableObject {

  @JsonDeserialize(using = TrashWebLinkTypeField.TrashWebLinkTypeFieldDeserializer.class)
  @JsonSerialize(using = TrashWebLinkTypeField.TrashWebLinkTypeFieldSerializer.class)
  protected EnumWrapper<TrashWebLinkTypeField> type;

  protected String id;

  @JsonProperty("sequence_id")
  protected String sequenceId;

  protected String etag;

  protected String name;

  protected String url;

  protected FolderMini parent;

  protected String description;

  @JsonProperty("path_collection")
  protected TrashWebLinkPathCollectionField pathCollection;

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
      using = TrashWebLinkItemStatusField.TrashWebLinkItemStatusFieldDeserializer.class)
  @JsonSerialize(using = TrashWebLinkItemStatusField.TrashWebLinkItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected EnumWrapper<TrashWebLinkItemStatusField> itemStatus;

  public TrashWebLink() {
    super();
  }

  protected TrashWebLink(TrashWebLinkBuilder builder) {
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

  public EnumWrapper<TrashWebLinkTypeField> getType() {
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

  public TrashWebLinkPathCollectionField getPathCollection() {
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

  public EnumWrapper<TrashWebLinkItemStatusField> getItemStatus() {
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
    TrashWebLink casted = (TrashWebLink) o;
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
    return "TrashWebLink{"
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

  public static class TrashWebLinkBuilder {

    protected EnumWrapper<TrashWebLinkTypeField> type;

    protected String id;

    protected String sequenceId;

    protected String etag;

    protected String name;

    protected String url;

    protected FolderMini parent;

    protected String description;

    protected TrashWebLinkPathCollectionField pathCollection;

    protected String createdAt;

    protected String modifiedAt;

    protected String trashedAt;

    protected String purgedAt;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected UserMini ownedBy;

    protected String sharedLink;

    protected EnumWrapper<TrashWebLinkItemStatusField> itemStatus;

    public TrashWebLinkBuilder type(TrashWebLinkTypeField type) {
      this.type = new EnumWrapper<TrashWebLinkTypeField>(type);
      return this;
    }

    public TrashWebLinkBuilder type(EnumWrapper<TrashWebLinkTypeField> type) {
      this.type = type;
      return this;
    }

    public TrashWebLinkBuilder id(String id) {
      this.id = id;
      return this;
    }

    public TrashWebLinkBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public TrashWebLinkBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public TrashWebLinkBuilder name(String name) {
      this.name = name;
      return this;
    }

    public TrashWebLinkBuilder url(String url) {
      this.url = url;
      return this;
    }

    public TrashWebLinkBuilder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public TrashWebLinkBuilder description(String description) {
      this.description = description;
      return this;
    }

    public TrashWebLinkBuilder pathCollection(TrashWebLinkPathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    public TrashWebLinkBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public TrashWebLinkBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public TrashWebLinkBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public TrashWebLinkBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public TrashWebLinkBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public TrashWebLinkBuilder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public TrashWebLinkBuilder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public TrashWebLinkBuilder sharedLink(String sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public TrashWebLinkBuilder itemStatus(TrashWebLinkItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<TrashWebLinkItemStatusField>(itemStatus);
      return this;
    }

    public TrashWebLinkBuilder itemStatus(EnumWrapper<TrashWebLinkItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    public TrashWebLink build() {
      return new TrashWebLink(this);
    }
  }
}
