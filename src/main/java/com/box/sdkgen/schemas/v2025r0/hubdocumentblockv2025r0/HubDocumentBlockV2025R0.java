package com.box.sdkgen.schemas.v2025r0.hubdocumentblockv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** Base block in the Box Hub Document. */
@JsonFilter("nullablePropertyFilter")
public class HubDocumentBlockV2025R0 extends SerializableObject {

  /** The unique identifier for this block. */
  protected final String id;

  /** The unique identifier of the parent block. Null for direct children of the page. */
  @JsonProperty("parent_id")
  @Nullable
  protected String parentId;

  public HubDocumentBlockV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
  }

  protected HubDocumentBlockV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.parentId = builder.parentId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
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
    HubDocumentBlockV2025R0 casted = (HubDocumentBlockV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(parentId, casted.parentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parentId);
  }

  @Override
  public String toString() {
    return "HubDocumentBlockV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "parentId='"
        + parentId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected String parentId;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder parentId(String parentId) {
      this.parentId = parentId;
      this.markNullableFieldAsSet("parent_id");
      return this;
    }

    public HubDocumentBlockV2025R0 build() {
      return new HubDocumentBlockV2025R0(this);
    }
  }
}
