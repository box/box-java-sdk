package com.box.sdkgen.schemas.v2025r0.usertrackingcodev2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** A user tracking code. */
@JsonFilter("nullablePropertyFilter")
public class UserTrackingCodeV2025R0 extends SerializableObject {

  /** The ID of the user tracking code. */
  @Nullable protected Long id;

  /** The name of the user tracking code. */
  @Nullable protected String name;

  public UserTrackingCodeV2025R0() {
    super();
  }

  protected UserTrackingCodeV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getId() {
    return id;
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
    UserTrackingCodeV2025R0 casted = (UserTrackingCodeV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "UserTrackingCodeV2025R0{" + "id='" + id + '\'' + ", " + "name='" + name + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long id;

    protected String name;

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

    public UserTrackingCodeV2025R0 build() {
      return new UserTrackingCodeV2025R0(this);
    }
  }
}
