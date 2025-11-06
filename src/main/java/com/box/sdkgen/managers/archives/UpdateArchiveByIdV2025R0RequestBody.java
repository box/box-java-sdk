package com.box.sdkgen.managers.archives;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateArchiveByIdV2025R0RequestBody extends SerializableObject {

  /** The name of the archive. */
  protected String name;

  /** The description of the archive. */
  protected String description;

  public UpdateArchiveByIdV2025R0RequestBody() {
    super();
  }

  protected UpdateArchiveByIdV2025R0RequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.description = builder.description;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateArchiveByIdV2025R0RequestBody casted = (UpdateArchiveByIdV2025R0RequestBody) o;
    return Objects.equals(name, casted.name) && Objects.equals(description, casted.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    return "UpdateArchiveByIdV2025R0RequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected String description;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public UpdateArchiveByIdV2025R0RequestBody build() {
      return new UpdateArchiveByIdV2025R0RequestBody(this);
    }
  }
}
