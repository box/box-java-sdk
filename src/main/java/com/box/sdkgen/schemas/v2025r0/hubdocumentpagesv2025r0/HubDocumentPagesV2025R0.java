package com.box.sdkgen.schemas.v2025r0.hubdocumentpagesv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.hubdocumentpagev2025r0.HubDocumentPageV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** A list of Hub Document Pages. */
@JsonFilter("nullablePropertyFilter")
public class HubDocumentPagesV2025R0 extends SerializableObject {

  /** Ordered list of pages. */
  protected final List<HubDocumentPageV2025R0> entries;

  /** The value will always be `document_pages`. */
  @JsonDeserialize(
      using = HubDocumentPagesV2025R0TypeField.HubDocumentPagesV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = HubDocumentPagesV2025R0TypeField.HubDocumentPagesV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<HubDocumentPagesV2025R0TypeField> type;

  /**
   * The limit that was used for these entries. This will be the same as the `limit` query parameter
   * unless that value exceeded the maximum value allowed. The maximum value varies by API.
   */
  protected Long limit;

  /** The marker for the start of the next page of results. */
  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  public HubDocumentPagesV2025R0(@JsonProperty("entries") List<HubDocumentPageV2025R0> entries) {
    super();
    this.entries = entries;
    this.type =
        new EnumWrapper<HubDocumentPagesV2025R0TypeField>(
            HubDocumentPagesV2025R0TypeField.DOCUMENT_PAGES);
  }

  protected HubDocumentPagesV2025R0(Builder builder) {
    super();
    this.entries = builder.entries;
    this.type = builder.type;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<HubDocumentPageV2025R0> getEntries() {
    return entries;
  }

  public EnumWrapper<HubDocumentPagesV2025R0TypeField> getType() {
    return type;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubDocumentPagesV2025R0 casted = (HubDocumentPagesV2025R0) o;
    return Objects.equals(entries, casted.entries)
        && Objects.equals(type, casted.type)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, type, limit, nextMarker);
  }

  @Override
  public String toString() {
    return "HubDocumentPagesV2025R0{"
        + "entries='"
        + entries
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final List<HubDocumentPageV2025R0> entries;

    protected EnumWrapper<HubDocumentPagesV2025R0TypeField> type;

    protected Long limit;

    protected String nextMarker;

    public Builder(List<HubDocumentPageV2025R0> entries) {
      super();
      this.entries = entries;
    }

    public Builder type(HubDocumentPagesV2025R0TypeField type) {
      this.type = new EnumWrapper<HubDocumentPagesV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<HubDocumentPagesV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      this.markNullableFieldAsSet("next_marker");
      return this;
    }

    public HubDocumentPagesV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<HubDocumentPagesV2025R0TypeField>(
                HubDocumentPagesV2025R0TypeField.DOCUMENT_PAGES);
      }
      return new HubDocumentPagesV2025R0(this);
    }
  }
}
