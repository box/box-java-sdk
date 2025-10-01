package com.box.sdkgen.schemas.v2025r0.hubcopyrequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** Request schema for copying a Box Hub. */
@JsonFilter("nullablePropertyFilter")
public class HubCopyRequestV2025R0 extends SerializableObject {

  /** Title of the Box Hub. It cannot be empty and should be less than 50 characters. */
  protected String title;

  /** Description of the Box Hub. */
  protected String description;

  public HubCopyRequestV2025R0() {
    super();
  }

  protected HubCopyRequestV2025R0(Builder builder) {
    super();
    this.title = builder.title;
    this.description = builder.description;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getTitle() {
    return title;
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
    HubCopyRequestV2025R0 casted = (HubCopyRequestV2025R0) o;
    return Objects.equals(title, casted.title) && Objects.equals(description, casted.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description);
  }

  @Override
  public String toString() {
    return "HubCopyRequestV2025R0{"
        + "title='"
        + title
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String title;

    protected String description;

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public HubCopyRequestV2025R0 build() {
      return new HubCopyRequestV2025R0(this);
    }
  }
}
