package com.box.sdkgen.schemas.metadatataxonomynode;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatataxonomyancestor.MetadataTaxonomyAncestor;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A node object for metadata taxonomy that can be used in metadata templates. */
@JsonFilter("nullablePropertyFilter")
public class MetadataTaxonomyNode extends SerializableObject {

  /** A unique identifier of the metadata taxonomy node. */
  protected final String id;

  /** The display name of the metadata taxonomy node. */
  protected final String displayName;

  /** An index of the level to which the node belongs. */
  protected final long level;

  /** The identifier of the parent node. */
  protected String parentId;

  /** An array of identifiers for all ancestor nodes. Not returned for root-level nodes. */
  protected List<String> nodePath;

  /** An array of objects for all ancestor nodes. Not returned for root-level nodes. */
  protected List<MetadataTaxonomyAncestor> ancestors;

  public MetadataTaxonomyNode(
      @JsonProperty("id") String id,
      @JsonProperty("displayName") String displayName,
      @JsonProperty("level") long level) {
    super();
    this.id = id;
    this.displayName = displayName;
    this.level = level;
  }

  protected MetadataTaxonomyNode(Builder builder) {
    super();
    this.id = builder.id;
    this.displayName = builder.displayName;
    this.level = builder.level;
    this.parentId = builder.parentId;
    this.nodePath = builder.nodePath;
    this.ancestors = builder.ancestors;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public long getLevel() {
    return level;
  }

  public String getParentId() {
    return parentId;
  }

  public List<String> getNodePath() {
    return nodePath;
  }

  public List<MetadataTaxonomyAncestor> getAncestors() {
    return ancestors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataTaxonomyNode casted = (MetadataTaxonomyNode) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(level, casted.level)
        && Objects.equals(parentId, casted.parentId)
        && Objects.equals(nodePath, casted.nodePath)
        && Objects.equals(ancestors, casted.ancestors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, displayName, level, parentId, nodePath, ancestors);
  }

  @Override
  public String toString() {
    return "MetadataTaxonomyNode{"
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
        + ", "
        + "parentId='"
        + parentId
        + '\''
        + ", "
        + "nodePath='"
        + nodePath
        + '\''
        + ", "
        + "ancestors='"
        + ancestors
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected final String displayName;

    protected final long level;

    protected String parentId;

    protected List<String> nodePath;

    protected List<MetadataTaxonomyAncestor> ancestors;

    public Builder(String id, String displayName, long level) {
      super();
      this.id = id;
      this.displayName = displayName;
      this.level = level;
    }

    public Builder parentId(String parentId) {
      this.parentId = parentId;
      return this;
    }

    public Builder nodePath(List<String> nodePath) {
      this.nodePath = nodePath;
      return this;
    }

    public Builder ancestors(List<MetadataTaxonomyAncestor> ancestors) {
      this.ancestors = ancestors;
      return this;
    }

    public MetadataTaxonomyNode build() {
      return new MetadataTaxonomyNode(this);
    }
  }
}
