package com.box.sdkgen.schemas.metadatataxonomyancestor;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** A node object in the metadata taxonomy that represents an ancestor. */
@JsonFilter("nullablePropertyFilter")
public class MetadataTaxonomyAncestor extends SerializableObject {

  /** A unique identifier of the metadata taxonomy node. */
  protected String id;

  /** The display name of the metadata taxonomy node. */
  protected String displayName;

  /** An index of the level to which the node belongs. */
  protected Long level;

  public MetadataTaxonomyAncestor() {
    super();
  }

  protected MetadataTaxonomyAncestor(Builder builder) {
    super();
    this.id = builder.id;
    this.displayName = builder.displayName;
    this.level = builder.level;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public Long getLevel() {
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
    MetadataTaxonomyAncestor casted = (MetadataTaxonomyAncestor) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(level, casted.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, displayName, level);
  }

  @Override
  public String toString() {
    return "MetadataTaxonomyAncestor{"
        + "id='"
        + id
        + '\''
        + ", "
        + "displayName='"
        + displayName
        + '\''
        + ", "
        + "level='"
        + level
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected String displayName;

    protected Long level;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder displayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    public Builder level(Long level) {
      this.level = level;
      return this;
    }

    public MetadataTaxonomyAncestor build() {
      return new MetadataTaxonomyAncestor(this);
    }
  }
}
