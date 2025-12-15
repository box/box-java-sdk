package com.box.sdkgen.managers.metadatataxonomies;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AddMetadataTaxonomyLevelRequestBody extends SerializableObject {

  /** The display name of the taxonomy level. */
  protected final String displayName;

  /** The description of the taxonomy level. */
  protected String description;

  public AddMetadataTaxonomyLevelRequestBody(@JsonProperty("displayName") String displayName) {
    super();
    this.displayName = displayName;
  }

  protected AddMetadataTaxonomyLevelRequestBody(Builder builder) {
    super();
    this.displayName = builder.displayName;
    this.description = builder.description;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getDisplayName() {
    return displayName;
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
    AddMetadataTaxonomyLevelRequestBody casted = (AddMetadataTaxonomyLevelRequestBody) o;
    return Objects.equals(displayName, casted.displayName)
        && Objects.equals(description, casted.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, description);
  }

  @Override
  public String toString() {
    return "AddMetadataTaxonomyLevelRequestBody{"
        + "displayName='"
        + displayName
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String displayName;

    protected String description;

    public Builder(String displayName) {
      super();
      this.displayName = displayName;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public AddMetadataTaxonomyLevelRequestBody build() {
      return new AddMetadataTaxonomyLevelRequestBody(this);
    }
  }
}
