package com.box.sdkgen.schemas.v2026r0.shuttlesystemupdaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** The request body for updating a shuttle system. Only the fields supplied are changed. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSystemUpdateRequestV2026R0 extends SerializableObject {

  /** The updated display name for the shuttle system. */
  protected String name;

  public ShuttleSystemUpdateRequestV2026R0() {
    super();
  }

  protected ShuttleSystemUpdateRequestV2026R0(Builder builder) {
    super();
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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
    ShuttleSystemUpdateRequestV2026R0 casted = (ShuttleSystemUpdateRequestV2026R0) o;
    return Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "ShuttleSystemUpdateRequestV2026R0{" + "name='" + name + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public ShuttleSystemUpdateRequestV2026R0 build() {
      return new ShuttleSystemUpdateRequestV2026R0(this);
    }
  }
}
