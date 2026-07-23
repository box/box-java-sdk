package com.box.sdkgen.schemas.v2026r0.queryancestorreferencev2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * A reference to an ancestor entity used to restrict query results to that entity and its recursive
 * descendants.
 */
@JsonFilter("nullablePropertyFilter")
public class QueryAncestorReferenceV2026R0 extends SerializableObject {

  /** The unique identifier of the ancestor entity. */
  protected final String id;

  /** The type of the ancestor entity. Possible value: folder. */
  protected final String type;

  public QueryAncestorReferenceV2026R0(
      @JsonProperty("id") String id, @JsonProperty("type") String type) {
    super();
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryAncestorReferenceV2026R0 casted = (QueryAncestorReferenceV2026R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "QueryAncestorReferenceV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }
}
