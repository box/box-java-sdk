package com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestpageselectionv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.documentsplitpageselectionitemv2026r0.DocumentSplitPageSelectionItemV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/**
 * Validation rules for creating a Document Split job using non-linear page selections. Each item
 * produces one output file from the declared page segments.
 */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitCreateRequestPageSelectionV2026R0 extends SerializableObject {

  /** Discriminator. Must be `page_selection` for this variant. */
  @JsonDeserialize(
      using =
          DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField
              .DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField
              .DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeFieldSerializer.class)
  @JsonProperty("split_type")
  protected EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField> splitType;

  /** The version of the page selection input format. Optional. When provided must be `v_2`. */
  @JsonDeserialize(
      using =
          DocumentSplitCreateRequestPageSelectionV2026R0VersionField
              .DocumentSplitCreateRequestPageSelectionV2026R0VersionFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitCreateRequestPageSelectionV2026R0VersionField
              .DocumentSplitCreateRequestPageSelectionV2026R0VersionFieldSerializer.class)
  protected EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0VersionField> version;

  @JsonProperty("page_selection")
  protected final List<DocumentSplitPageSelectionItemV2026R0> pageSelection;

  public DocumentSplitCreateRequestPageSelectionV2026R0(
      @JsonProperty("page_selection") List<DocumentSplitPageSelectionItemV2026R0> pageSelection) {
    super();
    this.pageSelection = pageSelection;
    this.splitType =
        new EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField>(
            DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField.PAGE_SELECTION);
  }

  protected DocumentSplitCreateRequestPageSelectionV2026R0(Builder builder) {
    super();
    this.splitType = builder.splitType;
    this.version = builder.version;
    this.pageSelection = builder.pageSelection;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField> getSplitType() {
    return splitType;
  }

  public EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0VersionField> getVersion() {
    return version;
  }

  public List<DocumentSplitPageSelectionItemV2026R0> getPageSelection() {
    return pageSelection;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitCreateRequestPageSelectionV2026R0 casted =
        (DocumentSplitCreateRequestPageSelectionV2026R0) o;
    return Objects.equals(splitType, casted.splitType)
        && Objects.equals(version, casted.version)
        && Objects.equals(pageSelection, casted.pageSelection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(splitType, version, pageSelection);
  }

  @Override
  public String toString() {
    return "DocumentSplitCreateRequestPageSelectionV2026R0{"
        + "splitType='"
        + splitType
        + '\''
        + ", "
        + "version='"
        + version
        + '\''
        + ", "
        + "pageSelection='"
        + pageSelection
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField> splitType;

    protected EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0VersionField> version;

    protected final List<DocumentSplitPageSelectionItemV2026R0> pageSelection;

    public Builder(List<DocumentSplitPageSelectionItemV2026R0> pageSelection) {
      super();
      this.pageSelection = pageSelection;
    }

    public Builder splitType(
        DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField splitType) {
      this.splitType =
          new EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField>(splitType);
      return this;
    }

    public Builder splitType(
        EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField> splitType) {
      this.splitType = splitType;
      return this;
    }

    public Builder version(DocumentSplitCreateRequestPageSelectionV2026R0VersionField version) {
      this.version =
          new EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0VersionField>(version);
      return this;
    }

    public Builder version(
        EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0VersionField> version) {
      this.version = version;
      return this;
    }

    public DocumentSplitCreateRequestPageSelectionV2026R0 build() {
      if (this.splitType == null) {
        this.splitType =
            new EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField>(
                DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField.PAGE_SELECTION);
      }
      return new DocumentSplitCreateRequestPageSelectionV2026R0(this);
    }
  }
}
