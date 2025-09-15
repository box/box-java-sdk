package com.box.sdkgen.managers.weblinks;

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
public class UpdateWebLinkByIdRequestBodySharedLinkField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateWebLinkByIdRequestBodySharedLinkAccessField
              .UpdateWebLinkByIdRequestBodySharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateWebLinkByIdRequestBodySharedLinkAccessField
              .UpdateWebLinkByIdRequestBodySharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField> access;

  @Nullable protected String password;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonProperty("unshared_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime unsharedAt;

  public UpdateWebLinkByIdRequestBodySharedLinkField() {
    super();
  }

  protected UpdateWebLinkByIdRequestBodySharedLinkField(Builder builder) {
    super();
    this.access = builder.access;
    this.password = builder.password;
    this.vanityName = builder.vanityName;
    this.unsharedAt = builder.unsharedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField> getAccess() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateWebLinkByIdRequestBodySharedLinkField casted =
        (UpdateWebLinkByIdRequestBodySharedLinkField) o;
    return Objects.equals(access, casted.access)
        && Objects.equals(password, casted.password)
        && Objects.equals(vanityName, casted.vanityName)
        && Objects.equals(unsharedAt, casted.unsharedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(access, password, vanityName, unsharedAt);
  }

  @Override
  public String toString() {
    return "UpdateWebLinkByIdRequestBodySharedLinkField{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField> access;

    protected String password;

    protected String vanityName;

    protected OffsetDateTime unsharedAt;

    public Builder access(UpdateWebLinkByIdRequestBodySharedLinkAccessField access) {
      this.access = new EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField>(access);
      return this;
    }

    public Builder access(EnumWrapper<UpdateWebLinkByIdRequestBodySharedLinkAccessField> access) {
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

    public UpdateWebLinkByIdRequestBodySharedLinkField build() {
      return new UpdateWebLinkByIdRequestBodySharedLinkField(this);
    }
  }
}
