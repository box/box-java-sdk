package com.box.sdkgen.schemas.metadatataxonomylevels;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatataxonomylevel.MetadataTaxonomyLevel;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

/**
 * Levels in the metadata taxonomy represent hierarchical categories within the taxonomy structure.
 */
@JsonFilter("nullablePropertyFilter")
public class MetadataTaxonomyLevels extends SerializableObject {

  /** An array of all taxonomy levels. */
  protected List<MetadataTaxonomyLevel> entries;

  public MetadataTaxonomyLevels() {
    super();
  }

  protected MetadataTaxonomyLevels(Builder builder) {
    super();
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<MetadataTaxonomyLevel> getEntries() {
    return entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataTaxonomyLevels casted = (MetadataTaxonomyLevels) o;
    return Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries);
  }

  @Override
  public String toString() {
    return "MetadataTaxonomyLevels{" + "entries='" + entries + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<MetadataTaxonomyLevel> entries;

    public Builder entries(List<MetadataTaxonomyLevel> entries) {
      this.entries = entries;
      return this;
    }

    public MetadataTaxonomyLevels build() {
      return new MetadataTaxonomyLevels(this);
    }
  }
}
