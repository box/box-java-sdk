package com.box.sdkgen.schemas.v2025r0.hubcreaterequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** Request schema for creating a new Box Hub. */
@JsonFilter("nullablePropertyFilter")
public class HubCreateRequestV2025R0 extends SerializableObject {

  /** Title of the Box Hub. It cannot be empty and should be less than 50 characters. */
  protected final String title;

  /** Description of the Box Hub. */
  protected String description;

  public HubCreateRequestV2025R0(@JsonProperty("title") String title) {
    super();
    this.title = title;
  }

  protected HubCreateRequestV2025R0(Builder builder) {
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
    HubCreateRequestV2025R0 casted = (HubCreateRequestV2025R0) o;
    return Objects.equals(title, casted.title) && Objects.equals(description, casted.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description);
  }

  @Override
  public String toString() {
    return "HubCreateRequestV2025R0{"
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

    protected final String title;

    protected String description;

    public Builder(String title) {
      super();
      this.title = title;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public HubCreateRequestV2025R0 build() {
      return new HubCreateRequestV2025R0(this);
    }
  }
}
