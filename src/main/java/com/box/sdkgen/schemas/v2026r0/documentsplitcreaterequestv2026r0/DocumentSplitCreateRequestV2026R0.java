package com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestmanualv2026r0.DocumentSplitCreateRequestManualV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestpageselectionv2026r0.DocumentSplitCreateRequestPageSelectionV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestsmartv2026r0.DocumentSplitCreateRequestSmartV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequesttypev2026r0.DocumentSplitCreateRequestTypeV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * The request body for creating a Document Split job. The `split_input` property carries the
 * caller-supplied input that determines how the split is performed.
 */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitCreateRequestV2026R0 extends SerializableObject {

  /** The ID of the source file to split. */
  @JsonProperty("source_file_id")
  protected final String sourceFileId;

  /** The ID of the folder where the resulting files will be uploaded. */
  @JsonProperty("destination_folder_id")
  protected final String destinationFolderId;

  /** The system that initiated the request, used for analytics and traceability. */
  @JsonDeserialize(
      using =
          DocumentSplitCreateRequestV2026R0InputSourceField
              .DocumentSplitCreateRequestV2026R0InputSourceFieldDeserializer.class)
  @JsonSerialize(
      using =
          DocumentSplitCreateRequestV2026R0InputSourceField
              .DocumentSplitCreateRequestV2026R0InputSourceFieldSerializer.class)
  @JsonProperty("input_source")
  protected final EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource;

  @JsonProperty("split_input")
  protected final DocumentSplitCreateRequestTypeV2026R0 splitInput;

  /**
   * An optional identifier supplied by the caller for this Document Split job. The value is
   * returned on job responses for correlation only. It is not a Box resource ID, is not required to
   * be unique, and cannot be used to look up the job.
   */
  @JsonProperty("external_id")
  protected String externalId;

  public DocumentSplitCreateRequestV2026R0(
      String sourceFileId,
      String destinationFolderId,
      DocumentSplitCreateRequestV2026R0InputSourceField inputSource,
      DocumentSplitCreateRequestManualV2026R0 splitInput) {
    super();
    this.sourceFileId = sourceFileId;
    this.destinationFolderId = destinationFolderId;
    this.inputSource =
        new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(inputSource);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
  }

  public DocumentSplitCreateRequestV2026R0(
      String sourceFileId,
      String destinationFolderId,
      DocumentSplitCreateRequestV2026R0InputSourceField inputSource,
      DocumentSplitCreateRequestPageSelectionV2026R0 splitInput) {
    super();
    this.sourceFileId = sourceFileId;
    this.destinationFolderId = destinationFolderId;
    this.inputSource =
        new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(inputSource);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
  }

  public DocumentSplitCreateRequestV2026R0(
      String sourceFileId,
      String destinationFolderId,
      DocumentSplitCreateRequestV2026R0InputSourceField inputSource,
      DocumentSplitCreateRequestSmartV2026R0 splitInput) {
    super();
    this.sourceFileId = sourceFileId;
    this.destinationFolderId = destinationFolderId;
    this.inputSource =
        new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(inputSource);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
  }

  public DocumentSplitCreateRequestV2026R0(
      String sourceFileId,
      String destinationFolderId,
      DocumentSplitCreateRequestV2026R0InputSourceField inputSource,
      DocumentSplitCreateRequestTypeV2026R0 splitInput) {
    super();
    this.sourceFileId = sourceFileId;
    this.destinationFolderId = destinationFolderId;
    this.inputSource =
        new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(inputSource);
    this.splitInput = splitInput;
  }

  public DocumentSplitCreateRequestV2026R0(
      String sourceFileId,
      String destinationFolderId,
      EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource,
      DocumentSplitCreateRequestManualV2026R0 splitInput) {
    super();
    this.sourceFileId = sourceFileId;
    this.destinationFolderId = destinationFolderId;
    this.inputSource = inputSource;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
  }

  public DocumentSplitCreateRequestV2026R0(
      String sourceFileId,
      String destinationFolderId,
      EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource,
      DocumentSplitCreateRequestPageSelectionV2026R0 splitInput) {
    super();
    this.sourceFileId = sourceFileId;
    this.destinationFolderId = destinationFolderId;
    this.inputSource = inputSource;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
  }

  public DocumentSplitCreateRequestV2026R0(
      String sourceFileId,
      String destinationFolderId,
      EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource,
      DocumentSplitCreateRequestSmartV2026R0 splitInput) {
    super();
    this.sourceFileId = sourceFileId;
    this.destinationFolderId = destinationFolderId;
    this.inputSource = inputSource;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
  }

  public DocumentSplitCreateRequestV2026R0(
      @JsonProperty("source_file_id") String sourceFileId,
      @JsonProperty("destination_folder_id") String destinationFolderId,
      @JsonProperty("input_source")
          EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource,
      @JsonProperty("split_input") DocumentSplitCreateRequestTypeV2026R0 splitInput) {
    super();
    this.sourceFileId = sourceFileId;
    this.destinationFolderId = destinationFolderId;
    this.inputSource = inputSource;
    this.splitInput = splitInput;
  }

  protected DocumentSplitCreateRequestV2026R0(Builder builder) {
    super();
    this.sourceFileId = builder.sourceFileId;
    this.destinationFolderId = builder.destinationFolderId;
    this.inputSource = builder.inputSource;
    this.splitInput = builder.splitInput;
    this.externalId = builder.externalId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getSourceFileId() {
    return sourceFileId;
  }

  public String getDestinationFolderId() {
    return destinationFolderId;
  }

  public EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> getInputSource() {
    return inputSource;
  }

  public DocumentSplitCreateRequestTypeV2026R0 getSplitInput() {
    return splitInput;
  }

  public String getExternalId() {
    return externalId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitCreateRequestV2026R0 casted = (DocumentSplitCreateRequestV2026R0) o;
    return Objects.equals(sourceFileId, casted.sourceFileId)
        && Objects.equals(destinationFolderId, casted.destinationFolderId)
        && Objects.equals(inputSource, casted.inputSource)
        && Objects.equals(splitInput, casted.splitInput)
        && Objects.equals(externalId, casted.externalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceFileId, destinationFolderId, inputSource, splitInput, externalId);
  }

  @Override
  public String toString() {
    return "DocumentSplitCreateRequestV2026R0{"
        + "sourceFileId='"
        + sourceFileId
        + '\''
        + ", "
        + "destinationFolderId='"
        + destinationFolderId
        + '\''
        + ", "
        + "inputSource='"
        + inputSource
        + '\''
        + ", "
        + "splitInput='"
        + splitInput
        + '\''
        + ", "
        + "externalId='"
        + externalId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String sourceFileId;

    protected final String destinationFolderId;

    protected final EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource;

    protected final DocumentSplitCreateRequestTypeV2026R0 splitInput;

    protected String externalId;

    public Builder(
        String sourceFileId,
        String destinationFolderId,
        DocumentSplitCreateRequestV2026R0InputSourceField inputSource,
        DocumentSplitCreateRequestManualV2026R0 splitInput) {
      super();
      this.sourceFileId = sourceFileId;
      this.destinationFolderId = destinationFolderId;
      this.inputSource =
          new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(inputSource);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    }

    public Builder(
        String sourceFileId,
        String destinationFolderId,
        DocumentSplitCreateRequestV2026R0InputSourceField inputSource,
        DocumentSplitCreateRequestPageSelectionV2026R0 splitInput) {
      super();
      this.sourceFileId = sourceFileId;
      this.destinationFolderId = destinationFolderId;
      this.inputSource =
          new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(inputSource);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    }

    public Builder(
        String sourceFileId,
        String destinationFolderId,
        DocumentSplitCreateRequestV2026R0InputSourceField inputSource,
        DocumentSplitCreateRequestSmartV2026R0 splitInput) {
      super();
      this.sourceFileId = sourceFileId;
      this.destinationFolderId = destinationFolderId;
      this.inputSource =
          new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(inputSource);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    }

    public Builder(
        String sourceFileId,
        String destinationFolderId,
        DocumentSplitCreateRequestV2026R0InputSourceField inputSource,
        DocumentSplitCreateRequestTypeV2026R0 splitInput) {
      super();
      this.sourceFileId = sourceFileId;
      this.destinationFolderId = destinationFolderId;
      this.inputSource =
          new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(inputSource);
      this.splitInput = splitInput;
    }

    public Builder(
        String sourceFileId,
        String destinationFolderId,
        EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource,
        DocumentSplitCreateRequestManualV2026R0 splitInput) {
      super();
      this.sourceFileId = sourceFileId;
      this.destinationFolderId = destinationFolderId;
      this.inputSource = inputSource;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    }

    public Builder(
        String sourceFileId,
        String destinationFolderId,
        EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource,
        DocumentSplitCreateRequestPageSelectionV2026R0 splitInput) {
      super();
      this.sourceFileId = sourceFileId;
      this.destinationFolderId = destinationFolderId;
      this.inputSource = inputSource;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    }

    public Builder(
        String sourceFileId,
        String destinationFolderId,
        EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource,
        DocumentSplitCreateRequestSmartV2026R0 splitInput) {
      super();
      this.sourceFileId = sourceFileId;
      this.destinationFolderId = destinationFolderId;
      this.inputSource = inputSource;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    }

    public Builder(
        String sourceFileId,
        String destinationFolderId,
        EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> inputSource,
        DocumentSplitCreateRequestTypeV2026R0 splitInput) {
      super();
      this.sourceFileId = sourceFileId;
      this.destinationFolderId = destinationFolderId;
      this.inputSource = inputSource;
      this.splitInput = splitInput;
    }

    public Builder externalId(String externalId) {
      this.externalId = externalId;
      return this;
    }

    public DocumentSplitCreateRequestV2026R0 build() {
      return new DocumentSplitCreateRequestV2026R0(this);
    }
  }
}
