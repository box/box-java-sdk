package com.box.sdkgen.managers.sharedlinksfiles;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateSharedLinkOnFileRequestBodySharedLinkField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateSharedLinkOnFileRequestBodySharedLinkAccessField
              .UpdateSharedLinkOnFileRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateSharedLinkOnFileRequestBodySharedLinkAccessField
              .UpdateSharedLinkOnFileRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField> access;

  protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  protected UpdateSharedLinkOnFileRequestBodySharedLinkPermissionsField permissions;

  public UpdateSharedLinkOnFileRequestBodySharedLinkField() {
    super();
  }

  protected UpdateSharedLinkOnFileRequestBodySharedLinkField(
      UpdateSharedLinkOnFileRequestBodySharedLinkFieldBuilder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
  }

  public EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField> getAccess() {
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

  public UpdateSharedLinkOnFileRequestBodySharedLinkPermissionsField getPermissions() {
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
    UpdateSharedLinkOnFileRequestBodySharedLinkField casted =
        (UpdateSharedLinkOnFileRequestBodySharedLinkField) o;
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
    return "UpdateSharedLinkOnFileRequestBodySharedLinkField{"
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

  public static class UpdateSharedLinkOnFileRequestBodySharedLinkFieldBuilder {

    protected EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected String unsharedAt;

    protected UpdateSharedLinkOnFileRequestBodySharedLinkPermissionsField permissions;

    public UpdateSharedLinkOnFileRequestBodySharedLinkFieldBuilder access(
        UpdateSharedLinkOnFileRequestBodySharedLinkAccessField access) {
      this.access = new EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public UpdateSharedLinkOnFileRequestBodySharedLinkFieldBuilder access(
        EnumWrapper<UpdateSharedLinkOnFileRequestBodySharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public UpdateSharedLinkOnFileRequestBodySharedLinkFieldBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UpdateSharedLinkOnFileRequestBodySharedLinkFieldBuilder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public UpdateSharedLinkOnFileRequestBodySharedLinkFieldBuilder unsharedAt(String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public UpdateSharedLinkOnFileRequestBodySharedLinkFieldBuilder permissions(
        UpdateSharedLinkOnFileRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public UpdateSharedLinkOnFileRequestBodySharedLinkField build() {
      return new UpdateSharedLinkOnFileRequestBodySharedLinkField(this);
    }
  }
}
