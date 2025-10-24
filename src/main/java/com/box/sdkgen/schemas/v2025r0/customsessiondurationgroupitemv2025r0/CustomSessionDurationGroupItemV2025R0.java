package com.box.sdkgen.schemas.v2025r0.customsessiondurationgroupitemv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** A custom session duration group item. */
@JsonFilter("nullablePropertyFilter")
public class CustomSessionDurationGroupItemV2025R0 extends SerializableObject {

  /** Group ID (numerical). */
  protected String id;

  /** Group Name. */
  protected String name;

  public CustomSessionDurationGroupItemV2025R0() {
    super();
  }

  protected CustomSessionDurationGroupItemV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
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
    CustomSessionDurationGroupItemV2025R0 casted = (CustomSessionDurationGroupItemV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "CustomSessionDurationGroupItemV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected String name;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public CustomSessionDurationGroupItemV2025R0 build() {
      return new CustomSessionDurationGroupItemV2025R0(this);
    }
  }
}
