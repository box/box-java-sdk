package com.box.sdkgen.schemas.v2026r0.documentsplitpageselectionpagerangev2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A page selection segment that includes an inclusive page range from the source document. */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitPageSelectionPageRangeV2026R0 extends SerializableObject {

  /** Discriminator. Must be `page_range` for this variant. */
  @JsonDeserialize(
      using =
          DocumentSplitPageSelectionPageRangeV2026R0TypeField
              .DocumentSplitPageSelectionPageRangeV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitPageSelectionPageRangeV2026R0TypeField
              .DocumentSplitPageSelectionPageRangeV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<DocumentSplitPageSelectionPageRangeV2026R0TypeField> type;

  /** The first page (inclusive) included in this segment. */
  protected final int start;

  /** The last page (inclusive) included in this segment. */
  protected final int end;

  public DocumentSplitPageSelectionPageRangeV2026R0(
      @JsonProperty("start") int start, @JsonProperty("end") int end) {
    super();
    this.start = start;
    this.end = end;
    this.type =
        new EnumWrapper<DocumentSplitPageSelectionPageRangeV2026R0TypeField>(
            DocumentSplitPageSelectionPageRangeV2026R0TypeField.PAGE_RANGE);
  }

  protected DocumentSplitPageSelectionPageRangeV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.start = builder.start;
    this.end = builder.end;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<DocumentSplitPageSelectionPageRangeV2026R0TypeField> getType() {
    return type;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitPageSelectionPageRangeV2026R0 casted =
        (DocumentSplitPageSelectionPageRangeV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(start, casted.start)
        && Objects.equals(end, casted.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, start, end);
  }

  @Override
  public String toString() {
    return "DocumentSplitPageSelectionPageRangeV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "start='"
        + start
        + '\''
        + ", "
        + "end='"
        + end
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<DocumentSplitPageSelectionPageRangeV2026R0TypeField> type;

    protected final int start;

    protected final int end;

    public Builder(int start, int end) {
      super();
      this.start = start;
      this.end = end;
    }

    public Builder type(DocumentSplitPageSelectionPageRangeV2026R0TypeField type) {
      this.type = new EnumWrapper<DocumentSplitPageSelectionPageRangeV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<DocumentSplitPageSelectionPageRangeV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public DocumentSplitPageSelectionPageRangeV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<DocumentSplitPageSelectionPageRangeV2026R0TypeField>(
                DocumentSplitPageSelectionPageRangeV2026R0TypeField.PAGE_RANGE);
      }
      return new DocumentSplitPageSelectionPageRangeV2026R0(this);
    }
  }
}
