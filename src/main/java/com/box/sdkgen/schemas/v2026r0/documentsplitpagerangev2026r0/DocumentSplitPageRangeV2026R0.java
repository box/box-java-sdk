package com.box.sdkgen.schemas.v2026r0.documentsplitpagerangev2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** An inclusive page range used to define one slice of a manual Document Split job. */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitPageRangeV2026R0 extends SerializableObject {

  /** The first page (inclusive) included in this slice. */
  protected final int start;

  /** The last page (inclusive) included in this slice. */
  protected final int end;

  public DocumentSplitPageRangeV2026R0(
      @JsonProperty("start") int start, @JsonProperty("end") int end) {
    super();
    this.start = start;
    this.end = end;
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
    DocumentSplitPageRangeV2026R0 casted = (DocumentSplitPageRangeV2026R0) o;
    return Objects.equals(start, casted.start) && Objects.equals(end, casted.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  @Override
  public String toString() {
    return "DocumentSplitPageRangeV2026R0{"
        + "start='"
        + start
        + '\''
        + ", "
        + "end='"
        + end
        + '\''
        + "}";
  }
}
