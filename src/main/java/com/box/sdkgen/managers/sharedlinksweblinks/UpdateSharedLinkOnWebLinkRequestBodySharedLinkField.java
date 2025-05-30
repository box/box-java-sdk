package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateSharedLinkOnWebLinkRequestBodySharedLinkField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField
              .UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField
              .UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField> access;

  protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  protected UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField permissions;

  public UpdateSharedLinkOnWebLinkRequestBodySharedLinkField() {
    super();
  }

  protected UpdateSharedLinkOnWebLinkRequestBodySharedLinkField(
      UpdateSharedLinkOnWebLinkRequestBodySharedLinkFieldBuilder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
  }

  public EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField> getAccess() {
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

  public UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField getPermissions() {
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
    UpdateSharedLinkOnWebLinkRequestBodySharedLinkField casted =
        (UpdateSharedLinkOnWebLinkRequestBodySharedLinkField) o;
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
    return "UpdateSharedLinkOnWebLinkRequestBodySharedLinkField{"
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

  public static class UpdateSharedLinkOnWebLinkRequestBodySharedLinkFieldBuilder {

    protected EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected String unsharedAt;

    protected UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField permissions;

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkFieldBuilder access(
        UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField access) {
      this.access =
          new EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkFieldBuilder access(
        EnumWrapper<UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkFieldBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkFieldBuilder vanityName(
        String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkFieldBuilder unsharedAt(
        String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkFieldBuilder permissions(
        UpdateSharedLinkOnWebLinkRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public UpdateSharedLinkOnWebLinkRequestBodySharedLinkField build() {
      return new UpdateSharedLinkOnWebLinkRequestBodySharedLinkField(this);
    }
  }
}
