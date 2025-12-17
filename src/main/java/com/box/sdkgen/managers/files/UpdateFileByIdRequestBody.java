package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFileByIdRequestBody extends SerializableObject {

  /**
   * An optional different name for the file. This can be used to rename the file.
   *
   * <p>File names must be unique within their parent folder. The name check is case-insensitive, so
   * a file named `New File` cannot be created in a parent folder that already contains a folder
   * named `new file`.
   */
  protected String name;

  /**
   * The description for a file. This can be seen in the right-hand sidebar panel when viewing a
   * file in the Box web app. Additionally, this index is used in the search index of the file,
   * allowing users to find the file by the content in the description.
   */
  protected String description;

  protected UpdateFileByIdRequestBodyParentField parent;

  @JsonProperty("shared_link")
  @Nullable
  protected UpdateFileByIdRequestBodySharedLinkField sharedLink;

  /**
   * Defines a lock on an item. This prevents the item from being moved, renamed, or otherwise
   * changed by anyone other than the user who created the lock.
   *
   * <p>Set this to `null` to remove the lock.
   */
  @Nullable protected UpdateFileByIdRequestBodyLockField lock;

  /**
   * The retention expiration timestamp for the given file. This date cannot be shortened once set
   * on a file.
   */
  @JsonProperty("disposition_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime dispositionAt;

  /** Defines who can download a file. */
  protected UpdateFileByIdRequestBodyPermissionsField permissions;

  /**
   * An array of collections to make this file a member of. Currently we only support the
   * `favorites` collection.
   *
   * <p>To get the ID for a collection, use the [List all collections][1] endpoint.
   *
   * <p>Passing an empty array `[]` or `null` will remove the file from all collections.
   *
   * <p>[1]: https://developer.box.com/reference/get-collections
   */
  @Nullable protected List<UpdateFileByIdRequestBodyCollectionsField> collections;

  /**
   * The tags for this item. These tags are shown in the Box web app and mobile apps next to an
   * item.
   *
   * <p>To add or remove a tag, retrieve the item's current tags, modify them, and then update this
   * field.
   *
   * <p>There is a limit of 100 tags per item, and 10,000 unique tags per enterprise.
   */
  protected List<String> tags;

  public UpdateFileByIdRequestBody() {
    super();
  }

  protected UpdateFileByIdRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.description = builder.description;
    this.parent = builder.parent;
    this.sharedLink = builder.sharedLink;
    this.lock = builder.lock;
    this.dispositionAt = builder.dispositionAt;
    this.permissions = builder.permissions;
    this.collections = builder.collections;
    this.tags = builder.tags;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public UpdateFileByIdRequestBodyParentField getParent() {
    return parent;
  }

  public UpdateFileByIdRequestBodySharedLinkField getSharedLink() {
    return sharedLink;
  }

  public UpdateFileByIdRequestBodyLockField getLock() {
    return lock;
  }

  public OffsetDateTime getDispositionAt() {
    return dispositionAt;
  }

  public UpdateFileByIdRequestBodyPermissionsField getPermissions() {
    return permissions;
  }

  public List<UpdateFileByIdRequestBodyCollectionsField> getCollections() {
    return collections;
  }

  public List<String> getTags() {
    return tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFileByIdRequestBody casted = (UpdateFileByIdRequestBody) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(sharedLink, casted.sharedLink)
        && Objects.equals(lock, casted.lock)
        && Objects.equals(dispositionAt, casted.dispositionAt)
        && Objects.equals(permissions, casted.permissions)
        && Objects.equals(collections, casted.collections)
        && Objects.equals(tags, casted.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        name, description, parent, sharedLink, lock, dispositionAt, permissions, collections, tags);
  }

  @Override
  public String toString() {
    return "UpdateFileByIdRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "sharedLink='"
        + sharedLink
        + '\''
        + ", "
        + "lock='"
        + lock
        + '\''
        + ", "
        + "dispositionAt='"
        + dispositionAt
        + '\''
        + ", "
        + "permissions='"
        + permissions
        + '\''
        + ", "
        + "collections='"
        + collections
        + '\''
        + ", "
        + "tags='"
        + tags
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected String description;

    protected UpdateFileByIdRequestBodyParentField parent;

    protected UpdateFileByIdRequestBodySharedLinkField sharedLink;

    protected UpdateFileByIdRequestBodyLockField lock;

    protected OffsetDateTime dispositionAt;

    protected UpdateFileByIdRequestBodyPermissionsField permissions;

    protected List<UpdateFileByIdRequestBodyCollectionsField> collections;

    protected List<String> tags;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder parent(UpdateFileByIdRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public Builder sharedLink(UpdateFileByIdRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      this.markNullableFieldAsSet("shared_link");
      return this;
    }

    public Builder lock(UpdateFileByIdRequestBodyLockField lock) {
      this.lock = lock;
      this.markNullableFieldAsSet("lock");
      return this;
    }

    public Builder dispositionAt(OffsetDateTime dispositionAt) {
      this.dispositionAt = dispositionAt;
      return this;
    }

    public Builder permissions(UpdateFileByIdRequestBodyPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public Builder collections(List<UpdateFileByIdRequestBodyCollectionsField> collections) {
      this.collections = collections;
      this.markNullableFieldAsSet("collections");
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public UpdateFileByIdRequestBody build() {
      return new UpdateFileByIdRequestBody(this);
    }
  }
}
