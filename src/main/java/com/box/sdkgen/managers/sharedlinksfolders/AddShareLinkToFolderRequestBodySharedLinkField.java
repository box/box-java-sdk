package com.box.sdkgen.managers.sharedlinksfolders;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AddShareLinkToFolderRequestBodySharedLinkField extends SerializableObject {

  /**
   * The level of access for the shared link. This can be restricted to anyone with the link
   * (`open`), only people within the company (`company`) and only those who have been invited to
   * the folder (`collaborators`).
   *
   * <p>If not set, this field defaults to the access level specified by the enterprise admin. To
   * create a shared link with this default setting pass the `shared_link` object with no `access`
   * field, for example `{ "shared_link": {} }`.
   *
   * <p>The `company` access level is only available to paid accounts.
   */
  @JsonDeserialize(
      using =
          AddShareLinkToFolderRequestBodySharedLinkAccessField
              .AddShareLinkToFolderRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          AddShareLinkToFolderRequestBodySharedLinkAccessField
              .AddShareLinkToFolderRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField> access;

  /**
   * The password required to access the shared link. Set the password to `null` to remove it.
   * Passwords must now be at least eight characters long and include a number, upper case letter,
   * or a non-numeric or non-alphabetic character. A password can only be set when `access` is set
   * to `open`.
   */
  @Nullable protected String password;

  /**
   * Defines a custom vanity name to use in the shared link URL, for example
   * `https://app.box.com/v/my-shared-link`.
   *
   * <p>Custom URLs should not be used when sharing sensitive content as vanity URLs are a lot
   * easier to guess than regular shared links.
   */
  @JsonProperty("vanity_name")
  protected String vanityName;

  /**
   * The timestamp at which this shared link will expire. This field can only be set by users with
   * paid accounts. The value must be greater than the current date and time.
   */
  @JsonProperty("unshared_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime unsharedAt;

  protected AddShareLinkToFolderRequestBodySharedLinkPermissionsField permissions;

  public AddShareLinkToFolderRequestBodySharedLinkField() {
    super();
  }

  protected AddShareLinkToFolderRequestBodySharedLinkField(Builder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField> getAccess() {
    return access;
  }

  public String getPassword() {
    return password;
  }

  public String getVanityName() {
    return vanityName;
  }

  public OffsetDateTime getUnsharedAt() {
    return unsharedAt;
  }

  public AddShareLinkToFolderRequestBodySharedLinkPermissionsField getPermissions() {
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
    AddShareLinkToFolderRequestBodySharedLinkField casted =
        (AddShareLinkToFolderRequestBodySharedLinkField) o;
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
    return "AddShareLinkToFolderRequestBodySharedLinkField{"
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

    protected EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected OffsetDateTime unsharedAt;

    protected AddShareLinkToFolderRequestBodySharedLinkPermissionsField permissions;

    public Builder access(AddShareLinkToFolderRequestBodySharedLinkAccessField access) {
      this.access = new EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public Builder access(
        EnumWrapper<AddShareLinkToFolderRequestBodySharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      this.markNullableFieldAsSet("password");
      return this;
    }

    public Builder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public Builder unsharedAt(OffsetDateTime unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public Builder permissions(
        AddShareLinkToFolderRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public AddShareLinkToFolderRequestBodySharedLinkField build() {
      return new AddShareLinkToFolderRequestBodySharedLinkField(this);
    }
  }
}
