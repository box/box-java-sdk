package com.box.sdkgen.schemas.v2026r0.documentsplitpageselectionitemv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.documentsplitpageselectionsegmentv2026r0.DocumentSplitPageSelectionSegmentV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * A single output file definition within a page selection request. Each item produces one PDF
 * containing the pages specified by its segments array.
 */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitPageSelectionItemV2026R0 extends SerializableObject {

  /**
   * Optional name for the output file (without extension). When omitted the system generates a
   * sequential name based on the source file name.
   */
  protected String name;

  /**
   * An ordered list of page segments to include in this output file. Each segment specifies either
   * a single page or a contiguous page range.
   */
  protected final List<DocumentSplitPageSelectionSegmentV2026R0> segments;

  public DocumentSplitPageSelectionItemV2026R0(
      @JsonProperty("segments") List<DocumentSplitPageSelectionSegmentV2026R0> segments) {
    super();
    this.segments = segments;
  }

  protected DocumentSplitPageSelectionItemV2026R0(Builder builder) {
    super();
    this.name = builder.name;
    this.segments = builder.segments;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public List<DocumentSplitPageSelectionSegmentV2026R0> getSegments() {
    return segments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitPageSelectionItemV2026R0 casted = (DocumentSplitPageSelectionItemV2026R0) o;
    return Objects.equals(name, casted.name) && Objects.equals(segments, casted.segments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, segments);
  }

  @Override
  public String toString() {
    return "DocumentSplitPageSelectionItemV2026R0{"
        + "name='"
        + name
        + '\''
        + ", "
        + "segments='"
        + segments
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected final List<DocumentSplitPageSelectionSegmentV2026R0> segments;

    public Builder(List<DocumentSplitPageSelectionSegmentV2026R0> segments) {
      super();
      this.segments = segments;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public DocumentSplitPageSelectionItemV2026R0 build() {
      return new DocumentSplitPageSelectionItemV2026R0(this);
    }
  }
}
