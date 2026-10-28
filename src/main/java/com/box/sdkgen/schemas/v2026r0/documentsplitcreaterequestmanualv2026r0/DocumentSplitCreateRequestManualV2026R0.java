package com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestmanualv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.documentsplitpagerangev2026r0.DocumentSplitPageRangeV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** Validation rules for creating a Document Split job using caller-supplied page ranges. */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitCreateRequestManualV2026R0 extends SerializableObject {

  /** Discriminator. Must be `manual` for this variant. */
  @JsonDeserialize(
      using =
          DocumentSplitCreateRequestManualV2026R0SplitTypeField
              .DocumentSplitCreateRequestManualV2026R0SplitTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitCreateRequestManualV2026R0SplitTypeField
              .DocumentSplitCreateRequestManualV2026R0SplitTypeFieldSerializer.class)
  @JsonProperty("split_type")
  protected EnumWrapper<DocumentSplitCreateRequestManualV2026R0SplitTypeField> splitType;

  /**
   * The version of the manual split input format. Omitted or `v_1` is the legacy `split_definition`
   * format. Existing clients may omit this field.
   */
  @JsonDeserialize(
      using =
          DocumentSplitCreateRequestManualV2026R0VersionField
              .DocumentSplitCreateRequestManualV2026R0VersionFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitCreateRequestManualV2026R0VersionField
              .DocumentSplitCreateRequestManualV2026R0VersionFieldSerializer.class)
  protected EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField> version;

  /**
   * Page ranges that define the split. Each entry produces one output file containing the inclusive
   * page range `[start, end]`.
   */
  @JsonProperty("split_definition")
  protected final List<DocumentSplitPageRangeV2026R0> splitDefinition;

  public DocumentSplitCreateRequestManualV2026R0(
      @JsonProperty("split_definition") List<DocumentSplitPageRangeV2026R0> splitDefinition) {
    super();
    this.splitDefinition = splitDefinition;
    this.splitType =
        new EnumWrapper<DocumentSplitCreateRequestManualV2026R0SplitTypeField>(
            DocumentSplitCreateRequestManualV2026R0SplitTypeField.MANUAL);
  }

  protected DocumentSplitCreateRequestManualV2026R0(Builder builder) {
    super();
    this.splitType = builder.splitType;
    this.version = builder.version;
    this.splitDefinition = builder.splitDefinition;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<DocumentSplitCreateRequestManualV2026R0SplitTypeField> getSplitType() {
    return splitType;
  }

  public EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField> getVersion() {
    return version;
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
    DocumentSplitCreateRequestManualV2026R0 casted = (DocumentSplitCreateRequestManualV2026R0) o;
    return Objects.equals(splitType, casted.splitType)
        && Objects.equals(version, casted.version)
        && Objects.equals(splitDefinition, casted.splitDefinition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(splitType, version, splitDefinition);
  }

  @Override
  public String toString() {
    return "DocumentSplitCreateRequestManualV2026R0{"
        + "splitType='"
        + splitType
        + '\''
        + ", "
        + "version='"
        + version
        + '\''
        + ", "
        + "splitDefinition='"
        + splitDefinition
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<DocumentSplitCreateRequestManualV2026R0SplitTypeField> splitType;

    protected EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField> version;

    protected final List<DocumentSplitPageRangeV2026R0> splitDefinition;

    public Builder(List<DocumentSplitPageRangeV2026R0> splitDefinition) {
      super();
      this.splitDefinition = splitDefinition;
    }

    public Builder splitType(DocumentSplitCreateRequestManualV2026R0SplitTypeField splitType) {
      this.splitType =
          new EnumWrapper<DocumentSplitCreateRequestManualV2026R0SplitTypeField>(splitType);
      return this;
    }

    public Builder splitType(
        EnumWrapper<DocumentSplitCreateRequestManualV2026R0SplitTypeField> splitType) {
      this.splitType = splitType;
      return this;
    }

    public Builder version(DocumentSplitCreateRequestManualV2026R0VersionField version) {
      this.version = new EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField>(version);
      return this;
    }

    public Builder version(
        EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField> version) {
      this.version = version;
      return this;
    }

    public DocumentSplitCreateRequestManualV2026R0 build() {
      if (this.splitType == null) {
        this.splitType =
            new EnumWrapper<DocumentSplitCreateRequestManualV2026R0SplitTypeField>(
                DocumentSplitCreateRequestManualV2026R0SplitTypeField.MANUAL);
      }
      return new DocumentSplitCreateRequestManualV2026R0(this);
    }
  }
}
