package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.file.FileItemStatusField;
import com.box.sdkgen.schemas.file.FilePathCollectionField;
import com.box.sdkgen.schemas.file.FileSharedLinkField;
import com.box.sdkgen.schemas.filebase.FileBaseTypeField;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
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

/** A full representation of a file, as can be returned from any file API endpoints by default. */
@JsonFilter("nullablePropertyFilter")
public class FileFull extends File {

  /** The version number of this file. */
  @JsonProperty("version_number")
  protected String versionNumber;

  /** The number of comments on this file. */
  @JsonProperty("comment_count")
  protected Long commentCount;

  protected FileFullPermissionsField permissions;

  protected List<String> tags;

  @Nullable protected FileFullLockField lock;

  /**
   * Indicates the (optional) file extension for this file. By default, this is set to an empty
   * string.
   */
  protected String extension;

  /**
   * Indicates if the file is a package. Packages are commonly used by Mac Applications and can
   * include iWork files.
   */
  @JsonProperty("is_package")
  protected Boolean isPackage;

  @JsonProperty("expiring_embed_link")
  protected FileFullExpiringEmbedLinkField expiringEmbedLink;

  @JsonProperty("watermark_info")
  protected FileFullWatermarkInfoField watermarkInfo;

  /**
   * Specifies if the file can be accessed via the direct shared link or a shared link to a parent
   * folder.
   */
  @JsonProperty("is_accessible_via_shared_link")
  protected Boolean isAccessibleViaSharedLink;

  /** A list of the types of roles that user can be invited at when sharing this file. */
  @JsonDeserialize(using = AllowedInviteeRolesDeserializer.class)
  @JsonSerialize(using = AllowedInviteeRolesSerializer.class)
  @JsonProperty("allowed_invitee_roles")
  protected List<EnumWrapper<FileFullAllowedInviteeRolesField>> allowedInviteeRoles;

  /** Specifies if this file is owned by a user outside of the authenticated enterprise. */
  @JsonProperty("is_externally_owned")
  protected Boolean isExternallyOwned;

  /** Specifies if this file has any other collaborators. */
  @JsonProperty("has_collaborations")
  protected Boolean hasCollaborations;

  protected FileFullMetadataField metadata;

  /** When the file will automatically be deleted. */
  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime expiresAt;

  protected FileFullRepresentationsField representations;

  protected FileFullClassificationField classification;

  @JsonProperty("uploader_display_name")
  protected String uploaderDisplayName;

  /** The retention expiration timestamp for the given file. */
  @JsonProperty("disposition_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime dispositionAt;

  /** A list of the types of roles that user can be invited at when sharing this file. */
  @JsonDeserialize(using = SharedLinkPermissionOptionsDeserializer.class)
  @JsonSerialize(using = SharedLinkPermissionOptionsSerializer.class)
  @JsonProperty("shared_link_permission_options")
  @Nullable
  protected List<EnumWrapper<FileFullSharedLinkPermissionOptionsField>> sharedLinkPermissionOptions;

  /**
   * This field will return true if the file or any ancestor of the file is associated with at least
   * one app item. Note that this will return true even if the context user does not have access to
   * the app item(s) associated with the file.
   */
  @JsonProperty("is_associated_with_app_item")
  protected Boolean isAssociatedWithAppItem;

  public FileFull(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileFull(Builder builder) {
    super(builder);
    this.versionNumber = builder.versionNumber;
    this.commentCount = builder.commentCount;
    this.permissions = builder.permissions;
    this.tags = builder.tags;
    this.lock = builder.lock;
    this.extension = builder.extension;
    this.isPackage = builder.isPackage;
    this.expiringEmbedLink = builder.expiringEmbedLink;
    this.watermarkInfo = builder.watermarkInfo;
    this.isAccessibleViaSharedLink = builder.isAccessibleViaSharedLink;
    this.allowedInviteeRoles = builder.allowedInviteeRoles;
    this.isExternallyOwned = builder.isExternallyOwned;
    this.hasCollaborations = builder.hasCollaborations;
    this.metadata = builder.metadata;
    this.expiresAt = builder.expiresAt;
    this.representations = builder.representations;
    this.classification = builder.classification;
    this.uploaderDisplayName = builder.uploaderDisplayName;
    this.dispositionAt = builder.dispositionAt;
    this.sharedLinkPermissionOptions = builder.sharedLinkPermissionOptions;
    this.isAssociatedWithAppItem = builder.isAssociatedWithAppItem;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getVersionNumber() {
    return versionNumber;
  }

  public Long getCommentCount() {
    return commentCount;
  }

  public FileFullPermissionsField getPermissions() {
    return permissions;
  }

  public List<String> getTags() {
    return tags;
  }

  public FileFullLockField getLock() {
    return lock;
  }

  public String getExtension() {
    return extension;
  }

  public Boolean getIsPackage() {
    return isPackage;
  }

  public FileFullExpiringEmbedLinkField getExpiringEmbedLink() {
    return expiringEmbedLink;
  }

  public FileFullWatermarkInfoField getWatermarkInfo() {
    return watermarkInfo;
  }

  public Boolean getIsAccessibleViaSharedLink() {
    return isAccessibleViaSharedLink;
  }

  public List<EnumWrapper<FileFullAllowedInviteeRolesField>> getAllowedInviteeRoles() {
    return allowedInviteeRoles;
  }

  public Boolean getIsExternallyOwned() {
    return isExternallyOwned;
  }

  public Boolean getHasCollaborations() {
    return hasCollaborations;
  }

  public FileFullMetadataField getMetadata() {
    return metadata;
  }

  public OffsetDateTime getExpiresAt() {
    return expiresAt;
  }

  public FileFullRepresentationsField getRepresentations() {
    return representations;
  }

  public FileFullClassificationField getClassification() {
    return classification;
  }

  public String getUploaderDisplayName() {
    return uploaderDisplayName;
  }

  public OffsetDateTime getDispositionAt() {
    return dispositionAt;
  }

  public List<EnumWrapper<FileFullSharedLinkPermissionOptionsField>>
      getSharedLinkPermissionOptions() {
    return sharedLinkPermissionOptions;
  }

  public Boolean getIsAssociatedWithAppItem() {
    return isAssociatedWithAppItem;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFull casted = (FileFull) o;
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
        && Objects.equals(itemStatus, casted.itemStatus)
        && Objects.equals(versionNumber, casted.versionNumber)
        && Objects.equals(commentCount, casted.commentCount)
        && Objects.equals(permissions, casted.permissions)
        && Objects.equals(tags, casted.tags)
        && Objects.equals(lock, casted.lock)
        && Objects.equals(extension, casted.extension)
        && Objects.equals(isPackage, casted.isPackage)
        && Objects.equals(expiringEmbedLink, casted.expiringEmbedLink)
        && Objects.equals(watermarkInfo, casted.watermarkInfo)
        && Objects.equals(isAccessibleViaSharedLink, casted.isAccessibleViaSharedLink)
        && Objects.equals(allowedInviteeRoles, casted.allowedInviteeRoles)
        && Objects.equals(isExternallyOwned, casted.isExternallyOwned)
        && Objects.equals(hasCollaborations, casted.hasCollaborations)
        && Objects.equals(metadata, casted.metadata)
        && Objects.equals(expiresAt, casted.expiresAt)
        && Objects.equals(representations, casted.representations)
        && Objects.equals(classification, casted.classification)
        && Objects.equals(uploaderDisplayName, casted.uploaderDisplayName)
        && Objects.equals(dispositionAt, casted.dispositionAt)
        && Objects.equals(sharedLinkPermissionOptions, casted.sharedLinkPermissionOptions)
        && Objects.equals(isAssociatedWithAppItem, casted.isAssociatedWithAppItem);
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
        itemStatus,
        versionNumber,
        commentCount,
        permissions,
        tags,
        lock,
        extension,
        isPackage,
        expiringEmbedLink,
        watermarkInfo,
        isAccessibleViaSharedLink,
        allowedInviteeRoles,
        isExternallyOwned,
        hasCollaborations,
        metadata,
        expiresAt,
        representations,
        classification,
        uploaderDisplayName,
        dispositionAt,
        sharedLinkPermissionOptions,
        isAssociatedWithAppItem);
  }

  @Override
  public String toString() {
    return "FileFull{"
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
        + ", "
        + "versionNumber='"
        + versionNumber
        + '\''
        + ", "
        + "commentCount='"
        + commentCount
        + '\''
        + ", "
        + "permissions='"
        + permissions
        + '\''
        + ", "
        + "tags='"
        + tags
        + '\''
        + ", "
        + "lock='"
        + lock
        + '\''
        + ", "
        + "extension='"
        + extension
        + '\''
        + ", "
        + "isPackage='"
        + isPackage
        + '\''
        + ", "
        + "expiringEmbedLink='"
        + expiringEmbedLink
        + '\''
        + ", "
        + "watermarkInfo='"
        + watermarkInfo
        + '\''
        + ", "
        + "isAccessibleViaSharedLink='"
        + isAccessibleViaSharedLink
        + '\''
        + ", "
        + "allowedInviteeRoles='"
        + allowedInviteeRoles
        + '\''
        + ", "
        + "isExternallyOwned='"
        + isExternallyOwned
        + '\''
        + ", "
        + "hasCollaborations='"
        + hasCollaborations
        + '\''
        + ", "
        + "metadata='"
        + metadata
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + ", "
        + "representations='"
        + representations
        + '\''
        + ", "
        + "classification='"
        + classification
        + '\''
        + ", "
        + "uploaderDisplayName='"
        + uploaderDisplayName
        + '\''
        + ", "
        + "dispositionAt='"
        + dispositionAt
        + '\''
        + ", "
        + "sharedLinkPermissionOptions='"
        + sharedLinkPermissionOptions
        + '\''
        + ", "
        + "isAssociatedWithAppItem='"
        + isAssociatedWithAppItem
        + '\''
        + "}";
  }

  public static class Builder extends File.Builder {

    protected String versionNumber;

    protected Long commentCount;

    protected FileFullPermissionsField permissions;

    protected List<String> tags;

    protected FileFullLockField lock;

    protected String extension;

    protected Boolean isPackage;

    protected FileFullExpiringEmbedLinkField expiringEmbedLink;

    protected FileFullWatermarkInfoField watermarkInfo;

    protected Boolean isAccessibleViaSharedLink;

    protected List<EnumWrapper<FileFullAllowedInviteeRolesField>> allowedInviteeRoles;

    protected Boolean isExternallyOwned;

    protected Boolean hasCollaborations;

    protected FileFullMetadataField metadata;

    protected OffsetDateTime expiresAt;

    protected FileFullRepresentationsField representations;

    protected FileFullClassificationField classification;

    protected String uploaderDisplayName;

    protected OffsetDateTime dispositionAt;

    protected List<EnumWrapper<FileFullSharedLinkPermissionOptionsField>>
        sharedLinkPermissionOptions;

    protected Boolean isAssociatedWithAppItem;

    public Builder(String id) {
      super(id);
    }

    public Builder versionNumber(String versionNumber) {
      this.versionNumber = versionNumber;
      return this;
    }

    public Builder commentCount(Long commentCount) {
      this.commentCount = commentCount;
      return this;
    }

    public Builder permissions(FileFullPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder lock(FileFullLockField lock) {
      this.lock = lock;
      this.markNullableFieldAsSet("lock");
      return this;
    }

    public Builder extension(String extension) {
      this.extension = extension;
      return this;
    }

    public Builder isPackage(Boolean isPackage) {
      this.isPackage = isPackage;
      return this;
    }

    public Builder expiringEmbedLink(FileFullExpiringEmbedLinkField expiringEmbedLink) {
      this.expiringEmbedLink = expiringEmbedLink;
      return this;
    }

    public Builder watermarkInfo(FileFullWatermarkInfoField watermarkInfo) {
      this.watermarkInfo = watermarkInfo;
      return this;
    }

    public Builder isAccessibleViaSharedLink(Boolean isAccessibleViaSharedLink) {
      this.isAccessibleViaSharedLink = isAccessibleViaSharedLink;
      return this;
    }

    public Builder allowedInviteeRoles(List<? extends Valuable> allowedInviteeRoles) {
      this.allowedInviteeRoles =
          EnumWrapper.wrapValuableEnumList(
              allowedInviteeRoles, FileFullAllowedInviteeRolesField.class);
      return this;
    }

    public Builder isExternallyOwned(Boolean isExternallyOwned) {
      this.isExternallyOwned = isExternallyOwned;
      return this;
    }

    public Builder hasCollaborations(Boolean hasCollaborations) {
      this.hasCollaborations = hasCollaborations;
      return this;
    }

    public Builder metadata(FileFullMetadataField metadata) {
      this.metadata = metadata;
      return this;
    }

    public Builder expiresAt(OffsetDateTime expiresAt) {
      this.expiresAt = expiresAt;
      this.markNullableFieldAsSet("expires_at");
      return this;
    }

    public Builder representations(FileFullRepresentationsField representations) {
      this.representations = representations;
      return this;
    }

    public Builder classification(FileFullClassificationField classification) {
      this.classification = classification;
      return this;
    }

    public Builder uploaderDisplayName(String uploaderDisplayName) {
      this.uploaderDisplayName = uploaderDisplayName;
      return this;
    }

    public Builder dispositionAt(OffsetDateTime dispositionAt) {
      this.dispositionAt = dispositionAt;
      this.markNullableFieldAsSet("disposition_at");
      return this;
    }

    public Builder sharedLinkPermissionOptions(
        List<? extends Valuable> sharedLinkPermissionOptions) {
      this.sharedLinkPermissionOptions =
          EnumWrapper.wrapValuableEnumList(
              sharedLinkPermissionOptions, FileFullSharedLinkPermissionOptionsField.class);
      this.markNullableFieldAsSet("shared_link_permission_options");
      return this;
    }

    public Builder isAssociatedWithAppItem(Boolean isAssociatedWithAppItem) {
      this.isAssociatedWithAppItem = isAssociatedWithAppItem;
      return this;
    }

    @Override
    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    @Override
    public Builder type(FileBaseTypeField type) {
      this.type = new EnumWrapper<FileBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<FileBaseTypeField> type) {
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

    @Override
    public Builder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    @Override
    public Builder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    @Override
    public Builder description(String description) {
      this.description = description;
      return this;
    }

    @Override
    public Builder size(Long size) {
      this.size = size;
      return this;
    }

    @Override
    public Builder pathCollection(FilePathCollectionField pathCollection) {
      this.pathCollection = pathCollection;
      return this;
    }

    @Override
    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @Override
    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public Builder trashedAt(OffsetDateTime trashedAt) {
      this.trashedAt = trashedAt;
      this.markNullableFieldAsSet("trashed_at");
      return this;
    }

    @Override
    public Builder purgedAt(OffsetDateTime purgedAt) {
      this.purgedAt = purgedAt;
      this.markNullableFieldAsSet("purged_at");
      return this;
    }

    @Override
    public Builder contentCreatedAt(OffsetDateTime contentCreatedAt) {
      this.contentCreatedAt = contentCreatedAt;
      this.markNullableFieldAsSet("content_created_at");
      return this;
    }

    @Override
    public Builder contentModifiedAt(OffsetDateTime contentModifiedAt) {
      this.contentModifiedAt = contentModifiedAt;
      this.markNullableFieldAsSet("content_modified_at");
      return this;
    }

    @Override
    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    @Override
    public Builder modifiedBy(UserMini modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    @Override
    public Builder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    @Override
    public Builder sharedLink(FileSharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    @Override
    public Builder parent(FolderMini parent) {
      this.parent = parent;
      this.markNullableFieldAsSet("parent");
      return this;
    }

    @Override
    public Builder itemStatus(FileItemStatusField itemStatus) {
      this.itemStatus = new EnumWrapper<FileItemStatusField>(itemStatus);
      return this;
    }

    @Override
    public Builder itemStatus(EnumWrapper<FileItemStatusField> itemStatus) {
      this.itemStatus = itemStatus;
      return this;
    }

    public FileFull build() {
      return new FileFull(this);
    }
  }

  public static class AllowedInviteeRolesDeserializer
      extends JsonDeserializer<List<EnumWrapper<FileFullAllowedInviteeRolesField>>> {

    public final JsonDeserializer<EnumWrapper<FileFullAllowedInviteeRolesField>>
        elementDeserializer;

    public AllowedInviteeRolesDeserializer() {
      super();
      this.elementDeserializer =
          new FileFullAllowedInviteeRolesField.FileFullAllowedInviteeRolesFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<FileFullAllowedInviteeRolesField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<FileFullAllowedInviteeRolesField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class AllowedInviteeRolesSerializer
      extends JsonSerializer<List<EnumWrapper<FileFullAllowedInviteeRolesField>>> {

    public final JsonSerializer<EnumWrapper<FileFullAllowedInviteeRolesField>> elementSerializer;

    public AllowedInviteeRolesSerializer() {
      super();
      this.elementSerializer =
          new FileFullAllowedInviteeRolesField.FileFullAllowedInviteeRolesFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<FileFullAllowedInviteeRolesField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<FileFullAllowedInviteeRolesField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }

  public static class SharedLinkPermissionOptionsDeserializer
      extends JsonDeserializer<List<EnumWrapper<FileFullSharedLinkPermissionOptionsField>>> {

    public final JsonDeserializer<EnumWrapper<FileFullSharedLinkPermissionOptionsField>>
        elementDeserializer;

    public SharedLinkPermissionOptionsDeserializer() {
      super();
      this.elementDeserializer =
          new FileFullSharedLinkPermissionOptionsField
              .FileFullSharedLinkPermissionOptionsFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<FileFullSharedLinkPermissionOptionsField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<FileFullSharedLinkPermissionOptionsField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class SharedLinkPermissionOptionsSerializer
      extends JsonSerializer<List<EnumWrapper<FileFullSharedLinkPermissionOptionsField>>> {

    public final JsonSerializer<EnumWrapper<FileFullSharedLinkPermissionOptionsField>>
        elementSerializer;

    public SharedLinkPermissionOptionsSerializer() {
      super();
      this.elementSerializer =
          new FileFullSharedLinkPermissionOptionsField
              .FileFullSharedLinkPermissionOptionsFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<FileFullSharedLinkPermissionOptionsField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<FileFullSharedLinkPermissionOptionsField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
