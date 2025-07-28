package com.box.sdkgen.managers.sharedlinksfolders;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateSharedLinkOnFolderRequestBodySharedLinkField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField
              .UpdateSharedLinkOnFolderRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField
              .UpdateSharedLinkOnFolderRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField> access;

  protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date unsharedAt;

  protected UpdateSharedLinkOnFolderRequestBodySharedLinkPermissionsField permissions;

  public UpdateSharedLinkOnFolderRequestBodySharedLinkField() {
    super();
  }

  protected UpdateSharedLinkOnFolderRequestBodySharedLinkField(Builder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField> getAccess() {
    return access;
  }

  public String getPassword() {
    return password;
  }

  public String getVanityName() {
    return vanityName;
  }

  public Date getUnsharedAt() {
    return unsharedAt;
  }

  public UpdateSharedLinkOnFolderRequestBodySharedLinkPermissionsField getPermissions() {
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
    UpdateSharedLinkOnFolderRequestBodySharedLinkField casted =
        (UpdateSharedLinkOnFolderRequestBodySharedLinkField) o;
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
    return "UpdateSharedLinkOnFolderRequestBodySharedLinkField{"
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

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected Date unsharedAt;

    protected UpdateSharedLinkOnFolderRequestBodySharedLinkPermissionsField permissions;

    public Builder access(UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField access) {
      this.access =
          new EnumWrapper<UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public Builder access(
        EnumWrapper<UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public Builder unsharedAt(Date unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public Builder permissions(
        UpdateSharedLinkOnFolderRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public UpdateSharedLinkOnFolderRequestBodySharedLinkField build() {
      return new UpdateSharedLinkOnFolderRequestBodySharedLinkField(this);
    }
  }
}
