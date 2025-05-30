package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AddShareLinkToWebLinkRequestBodySharedLinkField extends SerializableObject {

  @JsonDeserialize(
      using =
          AddShareLinkToWebLinkRequestBodySharedLinkAccessField
              .AddShareLinkToWebLinkRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          AddShareLinkToWebLinkRequestBodySharedLinkAccessField
              .AddShareLinkToWebLinkRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField> access;

  protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  protected AddShareLinkToWebLinkRequestBodySharedLinkPermissionsField permissions;

  public AddShareLinkToWebLinkRequestBodySharedLinkField() {
    super();
  }

  protected AddShareLinkToWebLinkRequestBodySharedLinkField(
      AddShareLinkToWebLinkRequestBodySharedLinkFieldBuilder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
  }

  public EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField> getAccess() {
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

  public AddShareLinkToWebLinkRequestBodySharedLinkPermissionsField getPermissions() {
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
    AddShareLinkToWebLinkRequestBodySharedLinkField casted =
        (AddShareLinkToWebLinkRequestBodySharedLinkField) o;
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
    return "AddShareLinkToWebLinkRequestBodySharedLinkField{"
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

  public static class AddShareLinkToWebLinkRequestBodySharedLinkFieldBuilder {

    protected EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected String unsharedAt;

    protected AddShareLinkToWebLinkRequestBodySharedLinkPermissionsField permissions;

    public AddShareLinkToWebLinkRequestBodySharedLinkFieldBuilder access(
        AddShareLinkToWebLinkRequestBodySharedLinkAccessField access) {
      this.access = new EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public AddShareLinkToWebLinkRequestBodySharedLinkFieldBuilder access(
        EnumWrapper<AddShareLinkToWebLinkRequestBodySharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public AddShareLinkToWebLinkRequestBodySharedLinkFieldBuilder password(String password) {
      this.password = password;
      return this;
    }

    public AddShareLinkToWebLinkRequestBodySharedLinkFieldBuilder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public AddShareLinkToWebLinkRequestBodySharedLinkFieldBuilder unsharedAt(String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public AddShareLinkToWebLinkRequestBodySharedLinkFieldBuilder permissions(
        AddShareLinkToWebLinkRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public AddShareLinkToWebLinkRequestBodySharedLinkField build() {
      return new AddShareLinkToWebLinkRequestBodySharedLinkField(this);
    }
  }
}
