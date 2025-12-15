package com.box.sdkgen.schemas.metadatataxonomy;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatataxonomylevel.MetadataTaxonomyLevel;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A taxonomy object for metadata that can be used in metadata templates. */
@JsonFilter("nullablePropertyFilter")
public class MetadataTaxonomy extends SerializableObject {

  /** A unique identifier of the metadata taxonomy. */
  protected final String id;

  /**
   * A unique identifier of the metadata taxonomy. The identifier must be unique within the
   * namespace to which it belongs.
   */
  protected String key;

  /** The display name of the metadata taxonomy. This can be seen in the Box web app. */
  protected final String displayName;

  /** A namespace that the metadata taxonomy is associated with. */
  protected final String namespace;

  /** Levels of the metadata taxonomy. */
  protected List<MetadataTaxonomyLevel> levels;

  public MetadataTaxonomy(
      @JsonProperty("id") String id,
      @JsonProperty("displayName") String displayName,
      @JsonProperty("namespace") String namespace) {
    super();
    this.id = id;
    this.displayName = displayName;
    this.namespace = namespace;
  }

  protected MetadataTaxonomy(Builder builder) {
    super();
    this.id = builder.id;
    this.key = builder.key;
    this.displayName = builder.displayName;
    this.namespace = builder.namespace;
    this.levels = builder.levels;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getKey() {
    return key;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getNamespace() {
    return namespace;
  }

  public List<MetadataTaxonomyLevel> getLevels() {
    return levels;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataTaxonomy casted = (MetadataTaxonomy) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(key, casted.key)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(namespace, casted.namespace)
        && Objects.equals(levels, casted.levels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, displayName, namespace, levels);
  }

  @Override
  public String toString() {
    return "MetadataTaxonomy{"
        + "id='"
        + id
        + '\''
        + ", "
        + "key='"
        + key
        + '\''
        + ", "
        + "displayName='"
        + displayName
        + '\''
        + ", "
        + "namespace='"
        + namespace
        + '\''
        + ", "
        + "levels='"
        + levels
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected String key;

    protected final String displayName;

    protected final String namespace;

    protected List<MetadataTaxonomyLevel> levels;

    public Builder(String id, String displayName, String namespace) {
      super();
      this.id = id;
      this.displayName = displayName;
      this.namespace = namespace;
    }

    public Builder key(String key) {
      this.key = key;
      return this;
    }

    public Builder levels(List<MetadataTaxonomyLevel> levels) {
      this.levels = levels;
      return this;
    }

    public MetadataTaxonomy build() {
      return new MetadataTaxonomy(this);
    }
  }
}
