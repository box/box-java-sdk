package com.box.sdkgen.schemas.metadatataxonomylevel;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/**
 * A level in the metadata taxonomy represents a hierarchical category within the taxonomy
 * structure.
 */
@JsonFilter("nullablePropertyFilter")
public class MetadataTaxonomyLevel extends SerializableObject {

  /** The display name of the level as it is shown to the user. */
  protected String displayName;

  /** A description of the level. */
  protected String description;

  /** An index of the level within the taxonomy. Levels are indexed starting from 1. */
  protected Integer level;

  public MetadataTaxonomyLevel() {
    super();
  }

  protected MetadataTaxonomyLevel(Builder builder) {
    super();
    this.displayName = builder.displayName;
    this.description = builder.description;
    this.level = builder.level;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getDescription() {
    return description;
  }

  public Integer getLevel() {
    return level;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataTaxonomyLevel casted = (MetadataTaxonomyLevel) o;
    return Objects.equals(displayName, casted.displayName)
        && Objects.equals(description, casted.description)
        && Objects.equals(level, casted.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, description, level);
  }

  @Override
  public String toString() {
    return "MetadataTaxonomyLevel{"
        + "displayName='"
        + displayName
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "level='"
        + level
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String displayName;

    protected String description;

    protected Integer level;

    public Builder displayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder level(Integer level) {
      this.level = level;
      return this;
    }

    public MetadataTaxonomyLevel build() {
      return new MetadataTaxonomyLevel(this);
    }
  }
}
