package com.box.sdkgen.schemas.weblink;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.collection.Collection;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.schemas.weblinkbase.WebLinkBaseTypeField;
import com.box.sdkgen.schemas.weblinkmini.WebLinkMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Web links are objects that point to URLs. These objects are also known as bookmarks within the
 * Box web application.
 *
 * <p>Web link objects are treated similarly to file objects, they will also support most actions
 * that apply to regular files.
 */
@JsonFilter("nullablePropertyFilter")
public class WebLink extends WebLinkMini {

  protected FolderMini parent;

  /** The description accompanying the web link. This is visible within the Box web application. */
  protected String description;

  @JsonProperty("path_collection")
  protected WebLinkPathCollectionField pathCollection;

  /** When this file was created on Box’s servers. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** When this file was last updated on the Box servers. */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  /** When this file was moved to the trash. */
  @JsonProperty("trashed_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime trashedAt;

  /** When this file will be permanently deleted. */
  @JsonProperty("purged_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime purgedAt;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("modified_by")
  protected UserMini modifiedBy;

  @JsonProperty("owned_by")
  protected UserMini ownedBy;

  @JsonProperty("shared_link")
  protected WebLinkSharedLinkField sharedLink;

  /**
   * Whether this item is deleted or not. Values include `active`, `trashed` if the file has been
   * moved to the trash, and `deleted` if the file has been permanently deleted.
   */
  @JsonDeserialize(using = WebLinkItemStatusField.WebLinkItemStatusFieldDeserializer.class)
  @JsonSerialize(using = WebLinkItemStatusField.WebLinkItemStatusFieldSerializer.class)
  @JsonProperty("item_status")
  protected EnumWrapper<WebLinkItemStatusField> itemStatus;

  /**
   * The collections that this web link belongs to.
   *
   * <p>For more information, see the [collections
   * guide](https://developer.box.com/guides/collections).
   */
  protected List<Collection> collections;

  /**
   * The shared link access levels the authenticated user is allowed to use when creating or
   * updating a shared link for this web link.
   *
   * <p>The list depends on item policy and user authorization, so it may be narrower than the
   * levels available to the owner. An empty array means no access level is available to this user.
   */
  @JsonDeserialize(using = AllowedSharedLinkAccessLevelsDeserializer.class)
  @JsonSerialize(using = AllowedSharedLinkAccessLevelsSerializer.class)
  @JsonProperty("allowed_shared_link_access_levels")
  protected List<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>>
      allowedSharedLinkAccessLevels;

  public WebLink(@JsonProperty("id") String id) {
    super(id);
  }

  protected WebLink(Builder builder) {
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
    this.collections = builder.collections;
    this.allowedSharedLinkAccessLevels = builder.allowedSharedLinkAccessLevels;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  public OffsetDateTime getTrashedAt() {
    return trashedAt;
  }

  public OffsetDateTime getPurgedAt() {
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

  public List<Collection> getCollections() {
    return collections;
  }

  public List<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>>
      getAllowedSharedLinkAccessLevels() {
    return allowedSharedLinkAccessLevels;
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
        && Objects.equals(itemStatus, casted.itemStatus)
        && Objects.equals(collections, casted.collections)
        && Objects.equals(allowedSharedLinkAccessLevels, casted.allowedSharedLinkAccessLevels);
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
        itemStatus,
        collections,
        allowedSharedLinkAccessLevels);
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
        + ", "
        + "collections='"
        + collections
        + '\''
        + ", "
        + "allowedSharedLinkAccessLevels='"
        + allowedSharedLinkAccessLevels
        + '\''
        + "}";
  }

  public static class Builder extends WebLinkMini.Builder {

    protected FolderMini parent;

    protected String description;

    protected WebLinkPathCollectionField pathCollection;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected OffsetDateTime trashedAt;

    protected OffsetDateTime purgedAt;

    protected UserMini createdBy;

    protected UserMini modifiedBy;

    protected UserMini ownedBy;

    protected WebLinkSharedLinkField sharedLink;

    protected EnumWrapper<WebLinkItemStatusField> itemStatus;

    protected List<Collection> collections;

    protected List<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>>
        allowedSharedLinkAccessLevels;

    public Builder(String id) {
      super(id);
    }

    public Builder parent(FolderMini parent) {
      this.parent = parent;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder pathCollection(WebLinkPathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
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

    public Builder sharedLink(WebLinkSharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public Builder itemStatus(WebLinkItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<WebLinkItemStatusField>(itemStatus);
      return this;
    }

    public Builder itemStatus(EnumWrapper<WebLinkItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    public Builder collections(List<Collection> collections) {
      this.collections = collections;
      return this;
    }

    public Builder allowedSharedLinkAccessLevels(
        List<? extends Valuable> allowedSharedLinkAccessLevels) {
      this.allowedSharedLinkAccessLevels =
          EnumWrapper.wrapValuableEnumList(
              allowedSharedLinkAccessLevels, WebLinkAllowedSharedLinkAccessLevelsField.class);
      return this;
    }

    @Override
    public Builder type(WebLinkBaseTypeField type) {
      this.type = new EnumWrapper<WebLinkBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<WebLinkBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder etag(String etag) {
      this.etag = etag;
      return this;
    }

    @Override
    public Builder url(String url) {
      this.url = url;
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

    public WebLink build() {
      if (this.type == null) {
        this.type = new EnumWrapper<WebLinkBaseTypeField>(WebLinkBaseTypeField.WEB_LINK);
      }
      return new WebLink(this);
    }
  }

  public static class AllowedSharedLinkAccessLevelsDeserializer
      extends JsonDeserializer<List<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>>> {

    public final JsonDeserializer<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>>
        elementDeserializer;

    public AllowedSharedLinkAccessLevelsDeserializer() {
      super();
      this.elementDeserializer =
          new WebLinkAllowedSharedLinkAccessLevelsField
              .WebLinkAllowedSharedLinkAccessLevelsFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class AllowedSharedLinkAccessLevelsSerializer
      extends JsonSerializer<List<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>>> {

    public final JsonSerializer<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>>
        elementSerializer;

    public AllowedSharedLinkAccessLevelsSerializer() {
      super();
      this.elementSerializer =
          new WebLinkAllowedSharedLinkAccessLevelsField
              .WebLinkAllowedSharedLinkAccessLevelsFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<WebLinkAllowedSharedLinkAccessLevelsField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
