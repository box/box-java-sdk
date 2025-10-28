package com.box.sdkgen.schemas.v2025r0.listuserv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** A user in allowlist or denylist. */
@JsonFilter("nullablePropertyFilter")
public class ListUserV2025R0 extends SerializableObject {

  /** The ID of the user. */
  @Nullable protected Long id;

  /** The name of the user. */
  @Nullable protected String name;

  /** The email of the user. */
  @Nullable protected String email;

  public ListUserV2025R0() {
    super();
  }

  protected ListUserV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.name = builder.name;
    this.email = builder.email;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
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
    ListUserV2025R0 casted = (ListUserV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(name, casted.name)
        && Objects.equals(email, casted.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email);
  }

  @Override
  public String toString() {
    return "ListUserV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "email='"
        + email
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long id;

    protected String name;

    protected String email;

    public Builder id(Long id) {
      this.id = id;
      this.markNullableFieldAsSet("id");
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      this.markNullableFieldAsSet("name");
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      this.markNullableFieldAsSet("email");
      return this;
    }

    public ListUserV2025R0 build() {
      return new ListUserV2025R0(this);
    }
  }
}
