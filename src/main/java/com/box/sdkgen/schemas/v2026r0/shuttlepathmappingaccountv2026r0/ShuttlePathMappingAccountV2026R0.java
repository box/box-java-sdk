package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** The account that owns an account-scoped path mapping location. */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingAccountV2026R0 extends SerializableObject {

  /** The platform-native identifier of the account. `null` when the system does not provide one. */
  @Nullable protected String id;

  /** The email address of the account. `null` when unknown. */
  @Nullable protected String email;

  /** The display name of the account. `null` when unknown. */
  @Nullable protected String name;

  public ShuttlePathMappingAccountV2026R0() {
    super();
  }

  protected ShuttlePathMappingAccountV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.email = builder.email;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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
    ShuttlePathMappingAccountV2026R0 casted = (ShuttlePathMappingAccountV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(email, casted.email)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, name);
  }

  @Override
  public String toString() {
    return "ShuttlePathMappingAccountV2026R0{"
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

    protected String id;

    protected String email;

    protected String name;

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

    public ShuttlePathMappingAccountV2026R0 build() {
      return new ShuttlePathMappingAccountV2026R0(this);
    }
  }
}
