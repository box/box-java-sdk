package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountinputv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/**
 * The account that owns an account-scoped path mapping location. Provide at least one of `id` or
 * `email`.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingAccountInputV2026R0 extends SerializableObject {

  /** The platform-native identifier of the account. */
  protected String id;

  /** The email address of the account. */
  protected String email;

  public ShuttlePathMappingAccountInputV2026R0() {
    super();
  }

  protected ShuttlePathMappingAccountInputV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.email = builder.email;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttlePathMappingAccountInputV2026R0 casted = (ShuttlePathMappingAccountInputV2026R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(email, casted.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email);
  }

  @Override
  public String toString() {
    return "ShuttlePathMappingAccountInputV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "email='"
        + email
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected String email;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public ShuttlePathMappingAccountInputV2026R0 build() {
      return new ShuttlePathMappingAccountInputV2026R0(this);
    }
  }
}
