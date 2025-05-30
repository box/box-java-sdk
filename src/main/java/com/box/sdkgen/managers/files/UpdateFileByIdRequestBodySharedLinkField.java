package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateFileByIdRequestBodySharedLinkField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateFileByIdRequestBodySharedLinkAccessField
              .UpdateFileByIdRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFileByIdRequestBodySharedLinkAccessField
              .UpdateFileByIdRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField> access;

  protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  protected UpdateFileByIdRequestBodySharedLinkPermissionsField permissions;

  public UpdateFileByIdRequestBodySharedLinkField() {
    super();
  }

  protected UpdateFileByIdRequestBodySharedLinkField(
      UpdateFileByIdRequestBodySharedLinkFieldBuilder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
  }

  public EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField> getAccess() {
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

  public UpdateFileByIdRequestBodySharedLinkPermissionsField getPermissions() {
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
    UpdateFileByIdRequestBodySharedLinkField casted = (UpdateFileByIdRequestBodySharedLinkField) o;
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
    return "UpdateFileByIdRequestBodySharedLinkField{"
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

  public static class UpdateFileByIdRequestBodySharedLinkFieldBuilder {

    protected EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected String unsharedAt;

    protected UpdateFileByIdRequestBodySharedLinkPermissionsField permissions;

    public UpdateFileByIdRequestBodySharedLinkFieldBuilder access(
        UpdateFileByIdRequestBodySharedLinkAccessField access) {
      this.access = new EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public UpdateFileByIdRequestBodySharedLinkFieldBuilder access(
        EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public UpdateFileByIdRequestBodySharedLinkFieldBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UpdateFileByIdRequestBodySharedLinkFieldBuilder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public UpdateFileByIdRequestBodySharedLinkFieldBuilder unsharedAt(String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public UpdateFileByIdRequestBodySharedLinkFieldBuilder permissions(
        UpdateFileByIdRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public UpdateFileByIdRequestBodySharedLinkField build() {
      return new UpdateFileByIdRequestBodySharedLinkField(this);
    }
  }
}
