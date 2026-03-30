package com.box.sdkgen.schemas.v2025r0.hubdocumentpagev2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A Page in the Box Hub Document. */
@JsonFilter("nullablePropertyFilter")
public class HubDocumentPageV2025R0 extends SerializableObject {

  /** The unique identifier for this page. */
  protected final String id;

  /** The type of this resource. The value is always `page`. */
  protected final String type;

  /** The unique identifier of the parent page. Null for root-level pages. */
  @JsonProperty("parent_id")
  @Nullable
  protected String parentId;

  /** The title text of the page. Includes rich text formatting. */
  @JsonProperty("title_fragment")
  protected final String titleFragment;

  public HubDocumentPageV2025R0(
      @JsonProperty("id") String id,
      @JsonProperty("type") String type,
      @JsonProperty("title_fragment") String titleFragment) {
    super();
    this.id = id;
    this.type = type;
    this.titleFragment = titleFragment;
  }

  protected HubDocumentPageV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.parentId = builder.parentId;
    this.titleFragment = builder.titleFragment;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getParentId() {
    return parentId;
  }

  public String getTitleFragment() {
    return titleFragment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubDocumentPageV2025R0 casted = (HubDocumentPageV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(parentId, casted.parentId)
        && Objects.equals(titleFragment, casted.titleFragment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, parentId, titleFragment);
  }

  @Override
  public String toString() {
    return "HubDocumentPageV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "parentId='"
        + parentId
        + '\''
        + ", "
        + "titleFragment='"
        + titleFragment
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected final String type;

    protected String parentId;

    protected final String titleFragment;

    public Builder(String id, String type, String titleFragment) {
      super();
      this.id = id;
      this.type = type;
      this.titleFragment = titleFragment;
    }

    public Builder parentId(String parentId) {
      this.parentId = parentId;
      this.markNullableFieldAsSet("parent_id");
      return this;
    }

    public HubDocumentPageV2025R0 build() {
      return new HubDocumentPageV2025R0(this);
    }
  }
}
