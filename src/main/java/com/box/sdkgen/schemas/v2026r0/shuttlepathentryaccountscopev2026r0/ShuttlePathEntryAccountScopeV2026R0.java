package com.box.sdkgen.schemas.v2026r0.shuttlepathentryaccountscopev2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * The mapping scope of a path entry that lives under a user or service account. `type` is the
 * discriminator. The remaining fields are the account itself, flattened onto this object — they are
 * not a nested `account` wrapper. `id` here is the account identifier, not the path entry `id`.
 * Copy these fields onto `account` when creating an account-scoped path mapping.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathEntryAccountScopeV2026R0 extends SerializableObject {

  /** The value will always be `account`. */
  @JsonDeserialize(
      using =
          ShuttlePathEntryAccountScopeV2026R0TypeField
              .ShuttlePathEntryAccountScopeV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttlePathEntryAccountScopeV2026R0TypeField
              .ShuttlePathEntryAccountScopeV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathEntryAccountScopeV2026R0TypeField> type;

  /**
   * The platform-native identifier of the account that owns this entry. `null` when the system does
   * not provide one. This is not the path entry `id`. Maps to `account.id` on an account-scoped
   * path mapping location.
   */
  @Nullable protected String id;

  /**
   * The email of the account that owns this entry. This is a system-native identifier and is not
   * always an RFC 5322 address. `null` when unknown. Maps to `account.email` on an account-scoped
   * path mapping location.
   */
  @Nullable protected String email;

  /**
   * The display name of the account that owns this entry. `null` when unknown. Maps to
   * `account.name` on an account-scoped path mapping location.
   */
  @Nullable protected String name;

  public ShuttlePathEntryAccountScopeV2026R0() {
    super();
    this.type =
        new EnumWrapper<ShuttlePathEntryAccountScopeV2026R0TypeField>(
            ShuttlePathEntryAccountScopeV2026R0TypeField.ACCOUNT);
  }

  protected ShuttlePathEntryAccountScopeV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.email = builder.email;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttlePathEntryAccountScopeV2026R0TypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttlePathEntryAccountScopeV2026R0 casted = (ShuttlePathEntryAccountScopeV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(email, casted.email)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, email, name);
  }

  @Override
  public String toString() {
    return "ShuttlePathEntryAccountScopeV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "email='"
        + email
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttlePathEntryAccountScopeV2026R0TypeField> type;

    protected String id;

    protected String email;

    protected String name;

    public Builder() {
      super();
    }

    public Builder type(ShuttlePathEntryAccountScopeV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttlePathEntryAccountScopeV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttlePathEntryAccountScopeV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      this.markNullableFieldAsSet("id");
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      this.markNullableFieldAsSet("email");
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      this.markNullableFieldAsSet("name");
      return this;
    }

    public ShuttlePathEntryAccountScopeV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathEntryAccountScopeV2026R0TypeField>(
                ShuttlePathEntryAccountScopeV2026R0TypeField.ACCOUNT);
      }
      return new ShuttlePathEntryAccountScopeV2026R0(this);
    }
  }
}
