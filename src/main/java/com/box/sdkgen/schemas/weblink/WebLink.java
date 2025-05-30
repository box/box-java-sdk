package com.box.sdkgen.schemas.weblink;

import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.schemas.weblinkbase.WebLinkBaseTypeField;
import com.box.sdkgen.schemas.weblinkmini.WebLinkMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class WebLink extends WebLinkMini {

  protected FolderMini parent;

  protected String description;

  @JsonProperty("path_collection")
  protected WebLinkPathCollectionField pathCollection;

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
  protected WebLinkSharedLinkField sharedLink;

  @JsonDeserialize(using = WebLinkItemStatusField.WebLinkItemStatusFieldDeserializer.class)
  @JsonSerialize(using = WebLinkItemStatusField.WebLinkItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected EnumWrapper<WebLinkItemStatusField> itemStatus;

  public WebLink(@JsonProperty("id") String id) {
    super(id);
  }

  protected WebLink(WebLinkBuilder builder) {
    super(builder);
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

  public FolderMini getParent() {
    return parent;
  }

  public String getDescription() {
    return description;
  }

  public WebLinkPathCollectionField getPathCollection() {
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

  public WebLinkSharedLinkField getSharedLink() {
    return sharedLink;
  }

  public EnumWrapper<WebLinkItemStatusField> getItemStatus() {
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
    WebLink casted = (WebLink) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(url, casted.url)
        && Objects.equals(sequenceId, casted.sequenceId)
        && Objects.equals(name, casted.name)
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
        id,
        type,
        etag,
        url,
        sequenceId,
        name,
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
    return "WebLink{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + ", "
        + "url='"
        + url
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

  public static class WebLinkBuilder extends WebLinkMiniBuilder {

    protected FolderMini parent;

    protected String description;

    protected WebLinkPathCollectionField pathCollection;

    protected String createdAt;

    protected String modifiedAt;

    protected String trashedAt;

    protected String purgedAt;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected UserMini ownedBy;

    protected WebLinkSharedLinkField sharedLink;

    protected EnumWrapper<WebLinkItemStatusField> itemStatus;

    public WebLinkBuilder(String id) {
      super(id);
    }

    public WebLinkBuilder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public WebLinkBuilder description(String description) {
      this.description = description;
      return this;
    }

    public WebLinkBuilder pathCollection(WebLinkPathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    public WebLinkBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public WebLinkBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public WebLinkBuilder trashedAt(String trashedAt) {
      this.trashedAt = trashedAt;
      return this;
    }

    public WebLinkBuilder purgedAt(String purgedAt) {
      this.purgedAt = purgedAt;
      return this;
    }

    public WebLinkBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public WebLinkBuilder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public WebLinkBuilder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public WebLinkBuilder sharedLink(WebLinkSharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public WebLinkBuilder itemStatus(WebLinkItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<WebLinkItemStatusField>(itemStatus);
      return this;
    }

    public WebLinkBuilder itemStatus(EnumWrapper<WebLinkItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    @Override
    public WebLinkBuilder type(WebLinkBaseTypeField type) {
      this.type = new EnumWrapper<WebLinkBaseTypeField>(type);
      return this;
    }

    @Override
    public WebLinkBuilder type(EnumWrapper<WebLinkBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public WebLinkBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    @Override
    public WebLinkBuilder url(String url) {
      this.url = url;
      return this;
    }

    @Override
    public WebLinkBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    @Override
    public WebLinkBuilder name(String name) {
      this.name = name;
      return this;
    }

    public WebLink build() {
      return new WebLink(this);
    }
  }
}
