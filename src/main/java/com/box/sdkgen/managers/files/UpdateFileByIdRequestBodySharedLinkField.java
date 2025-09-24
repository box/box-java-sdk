package com.box.sdkgen.managers.files;

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

  @Nullable protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime unsharedAt;

  protected UpdateFileByIdRequestBodySharedLinkPermissionsField permissions;

  public UpdateFileByIdRequestBodySharedLinkField() {
    super();
  }

  protected UpdateFileByIdRequestBodySharedLinkField(Builder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    this.permissions = builder.permissions;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getUnsharedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected OffsetDateTime unsharedAt;

    protected UpdateFileByIdRequestBodySharedLinkPermissionsField permissions;

    public Builder access(UpdateFileByIdRequestBodySharedLinkAccessField access) {
      this.access = new EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public Builder access(EnumWrapper<UpdateFileByIdRequestBodySharedLinkAccessField> access) {
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

    public Builder permissions(UpdateFileByIdRequestBodySharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public UpdateFileByIdRequestBodySharedLinkField build() {
      return new UpdateFileByIdRequestBodySharedLinkField(this);
    }
  }
}
