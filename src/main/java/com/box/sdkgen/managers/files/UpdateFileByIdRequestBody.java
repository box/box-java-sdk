package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFileByIdRequestBody extends SerializableObject {

  protected String name;

  protected String description;

  protected UpdateFileByIdRequestBodyParentField parent;

  @JsonProperty("shared_link")
  @Nullable
  protected UpdateFileByIdRequestBodySharedLinkField sharedLink;

  @Nullable protected UpdateFileByIdRequestBodyLockField lock;

  @JsonProperty("disposition_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date dispositionAt;

  protected UpdateFileByIdRequestBodyPermissionsField permissions;

  @Nullable protected List<UpdateFileByIdRequestBodyCollectionsField> collections;

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

  public Date getDispositionAt() {
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

    protected Date dispositionAt;

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

    public Builder dispositionAt(Date dispositionAt) {
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
