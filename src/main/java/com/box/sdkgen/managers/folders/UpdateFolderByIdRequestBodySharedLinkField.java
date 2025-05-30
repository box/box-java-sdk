package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateFolderByIdRequestBodySharedLinkField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateFolderByIdRequestBodySharedLinkAccessField
              .UpdateFolderByIdRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFolderByIdRequestBodySharedLinkAccessField
              .UpdateFolderByIdRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField> access;

  protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  protected UpdateFolderByIdRequestBodySharedLinkPermissionsField permissions;

  public UpdateFolderByIdRequestBodySharedLinkField() {
    super();
  }

  protected UpdateFolderByIdRequestBodySharedLinkField(
      UpdateFolderByIdRequestBodySharedLinkFieldBuilder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
  }

  public EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField> getAccess() {
    return access;
  }

  public String getPassword() {
    return password;
  }

  public String getVanityName() {
    return vanityName;
  }

  public String getUnsharedAt() {
    return unsharedAt;
  }

  public UpdateFolderByIdRequestBodySharedLinkPermissionsField getPermissions() {
    return permissions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFolderByIdRequestBodySharedLinkField casted =
        (UpdateFolderByIdRequestBodySharedLinkField) o;
    return Objects.equals(access, casted.access)
        && Objects.equals(password, casted.password)
        && Objects.equals(vanityName, casted.vanityName)
        && Objects.equals(unsharedAt, casted.unsharedAt)
        && Objects.equals(permissions, casted.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(access, password, vanityName, unsharedAt, permissions);
  }

  @Override
  public String toString() {
    return "UpdateFolderByIdRequestBodySharedLinkField{"
        + "access='"
        + access
        + '\''
        + ", "
        + "password='"
        + password
        + '\''
        + ", "
        + "vanityName='"
        + vanityName
        + '\''
        + ", "
        + "unsharedAt='"
        + unsharedAt
        + '\''
        + ", "
        + "permissions='"
        + permissions
        + '\''
        + "}";
  }

  public static class UpdateFolderByIdRequestBodySharedLinkFieldBuilder {

    protected EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected String unsharedAt;

    protected UpdateFolderByIdRequestBodySharedLinkPermissionsField permissions;

    public UpdateFolderByIdRequestBodySharedLinkFieldBuilder access(
        UpdateFolderByIdRequestBodySharedLinkAccessField access) {
      this.access = new EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public UpdateFolderByIdRequestBodySharedLinkFieldBuilder access(
        EnumWrapper<UpdateFolderByIdRequestBodySharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public UpdateFolderByIdRequestBodySharedLinkFieldBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UpdateFolderByIdRequestBodySharedLinkFieldBuilder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public UpdateFolderByIdRequestBodySharedLinkFieldBuilder unsharedAt(String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public UpdateFolderByIdRequestBodySharedLinkFieldBuilder permissions(
        UpdateFolderByIdRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public UpdateFolderByIdRequestBodySharedLinkField build() {
      return new UpdateFolderByIdRequestBodySharedLinkField(this);
    }
  }
}
