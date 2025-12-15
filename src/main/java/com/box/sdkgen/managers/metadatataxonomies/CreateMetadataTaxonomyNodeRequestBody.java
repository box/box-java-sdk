package com.box.sdkgen.managers.metadatataxonomies;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateMetadataTaxonomyNodeRequestBody extends SerializableObject {

  /** The display name of the taxonomy node. */
  protected final String displayName;

  /** The level of the taxonomy node. */
  protected final long level;

  /** The identifier of the parent taxonomy node. Omit this field for root-level nodes. */
  protected String parentId;

  public CreateMetadataTaxonomyNodeRequestBody(
      @JsonProperty("displayName") String displayName, @JsonProperty("level") long level) {
    super();
    this.displayName = displayName;
    this.level = level;
  }

  protected CreateMetadataTaxonomyNodeRequestBody(Builder builder) {
    super();
    this.displayName = builder.displayName;
    this.level = builder.level;
    this.parentId = builder.parentId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateMetadataTaxonomyNodeRequestBody casted = (CreateMetadataTaxonomyNodeRequestBody) o;
    return Objects.equals(displayName, casted.displayName)
        && Objects.equals(level, casted.level)
        && Objects.equals(parentId, casted.parentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, level, parentId);
  }

  @Override
  public String toString() {
    return "CreateMetadataTaxonomyNodeRequestBody{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String displayName;

    protected final long level;

    protected String parentId;

    public Builder(String displayName, long level) {
      super();
      this.displayName = displayName;
      this.level = level;
    }

    public Builder parentId(String parentId) {
      this.parentId = parentId;
      return this;
    }

    public CreateMetadataTaxonomyNodeRequestBody build() {
      return new CreateMetadataTaxonomyNodeRequestBody(this);
    }
  }
}
