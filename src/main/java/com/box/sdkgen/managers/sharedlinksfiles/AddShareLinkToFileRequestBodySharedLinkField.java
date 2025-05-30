package com.box.sdkgen.managers.sharedlinksfiles;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AddShareLinkToFileRequestBodySharedLinkField extends SerializableObject {

  @JsonDeserialize(
      using =
          AddShareLinkToFileRequestBodySharedLinkAccessField
              .AddShareLinkToFileRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          AddShareLinkToFileRequestBodySharedLinkAccessField
              .AddShareLinkToFileRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField> access;

  protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  protected AddShareLinkToFileRequestBodySharedLinkPermissionsField permissions;

  public AddShareLinkToFileRequestBodySharedLinkField() {
    super();
  }

  protected AddShareLinkToFileRequestBodySharedLinkField(
      AddShareLinkToFileRequestBodySharedLinkFieldBuilder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
  }

  public EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField> getAccess() {
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

  public AddShareLinkToFileRequestBodySharedLinkPermissionsField getPermissions() {
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
    AddShareLinkToFileRequestBodySharedLinkField casted =
        (AddShareLinkToFileRequestBodySharedLinkField) o;
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
    return "AddShareLinkToFileRequestBodySharedLinkField{"
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

  public static class AddShareLinkToFileRequestBodySharedLinkFieldBuilder {

    protected EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected String unsharedAt;

    protected AddShareLinkToFileRequestBodySharedLinkPermissionsField permissions;

    public AddShareLinkToFileRequestBodySharedLinkFieldBuilder access(
        AddShareLinkToFileRequestBodySharedLinkAccessField access) {
      this.access = new EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public AddShareLinkToFileRequestBodySharedLinkFieldBuilder access(
        EnumWrapper<AddShareLinkToFileRequestBodySharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public AddShareLinkToFileRequestBodySharedLinkFieldBuilder password(String password) {
      this.password = password;
      return this;
    }

    public AddShareLinkToFileRequestBodySharedLinkFieldBuilder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public AddShareLinkToFileRequestBodySharedLinkFieldBuilder unsharedAt(String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public AddShareLinkToFileRequestBodySharedLinkFieldBuilder permissions(
        AddShareLinkToFileRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public AddShareLinkToFileRequestBodySharedLinkField build() {
      return new AddShareLinkToFileRequestBodySharedLinkField(this);
    }
  }
}
