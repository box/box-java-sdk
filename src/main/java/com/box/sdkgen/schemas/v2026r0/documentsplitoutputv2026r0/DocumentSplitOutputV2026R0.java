package com.box.sdkgen.schemas.v2026r0.documentsplitoutputv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.documentsplitpagerangev2026r0.DocumentSplitPageRangeV2026R0;
import com.box.sdkgen.schemas.v2026r0.filereferencev2026r0.FileReferenceV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** The set of files produced by a completed Document Split job. */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitOutputV2026R0 extends SerializableObject {

  /** References to the files that were created by the split. */
  @JsonProperty("generated_files")
  protected List<FileReferenceV2026R0> generatedFiles;

  /**
   * The page ranges that Box produced for a `smart` split. Each entry corresponds to one generated
   * file with the inclusive page range `[start, end]`. Omitted or `null` for `manual` splits, where
   * the caller-supplied ranges are available in `split_input.split_definition`. Omitted for
   * `page_selection` splits, where the caller-supplied page layout is available in
   * `split_input.page_selection`.
   */
  @JsonProperty("split_definition")
  @Nullable
  protected List<DocumentSplitPageRangeV2026R0> splitDefinition;

  public DocumentSplitOutputV2026R0() {
    super();
  }

  protected DocumentSplitOutputV2026R0(Builder builder) {
    super();
    this.generatedFiles = builder.generatedFiles;
    this.splitDefinition = builder.splitDefinition;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<FileReferenceV2026R0> getGeneratedFiles() {
    return generatedFiles;
  }

  public List<DocumentSplitPageRangeV2026R0> getSplitDefinition() {
    return splitDefinition;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitOutputV2026R0 casted = (DocumentSplitOutputV2026R0) o;
    return Objects.equals(generatedFiles, casted.generatedFiles)
        && Objects.equals(splitDefinition, casted.splitDefinition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(generatedFiles, splitDefinition);
  }

  @Override
  public String toString() {
    return "DocumentSplitOutputV2026R0{"
        + "generatedFiles='"
        + generatedFiles
        + '\''
        + ", "
        + "splitDefinition='"
        + splitDefinition
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<FileReferenceV2026R0> generatedFiles;

    protected List<DocumentSplitPageRangeV2026R0> splitDefinition;

    public Builder generatedFiles(List<FileReferenceV2026R0> generatedFiles) {
      this.generatedFiles = generatedFiles;
      return this;
    }

    public Builder splitDefinition(List<DocumentSplitPageRangeV2026R0> splitDefinition) {
      this.splitDefinition = splitDefinition;
      this.markNullableFieldAsSet("split_definition");
      return this;
    }

    public DocumentSplitOutputV2026R0 build() {
      return new DocumentSplitOutputV2026R0(this);
    }
  }
}
