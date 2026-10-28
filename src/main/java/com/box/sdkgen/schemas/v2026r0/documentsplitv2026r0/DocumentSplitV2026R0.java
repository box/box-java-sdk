package com.box.sdkgen.schemas.v2026r0.documentsplitv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestmanualv2026r0.DocumentSplitCreateRequestManualV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestpageselectionv2026r0.DocumentSplitCreateRequestPageSelectionV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestsmartv2026r0.DocumentSplitCreateRequestSmartV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequesttypev2026r0.DocumentSplitCreateRequestTypeV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitfailuresv2026r0.DocumentSplitFailuresV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitoutputv2026r0.DocumentSplitOutputV2026R0;
import com.box.sdkgen.schemas.v2026r0.enterprisereferencev2026r0.EnterpriseReferenceV2026R0;
import com.box.sdkgen.schemas.v2026r0.filereferencev2026r0.FileReferenceV2026R0;
import com.box.sdkgen.schemas.v2026r0.folderreferencev2026r0.FolderReferenceV2026R0;
import com.box.sdkgen.schemas.v2026r0.userminiv2026r0.UserMiniV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A standard representation of a Document Split job. The job represents a request to split a
 * paginated source document into multiple files based on either a manual set of page ranges, a
 * non-linear page selection, or a smart prompt-driven analysis.
 */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitV2026R0 extends SerializableObject {

  /** The unique identifier for the Document Split job. */
  protected final String id;

  /** The value will always be `document_split`. */
  @JsonDeserialize(
      using = DocumentSplitV2026R0TypeField.DocumentSplitV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = DocumentSplitV2026R0TypeField.DocumentSplitV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<DocumentSplitV2026R0TypeField> type;

  @JsonProperty("source_file")
  protected final FileReferenceV2026R0 sourceFile;

  /** The current state of the Document Split job. */
  @JsonDeserialize(
      using = DocumentSplitV2026R0StatusField.DocumentSplitV2026R0StatusFieldDeserializer.class)
  @JsonSerialize(
      using = DocumentSplitV2026R0StatusField.DocumentSplitV2026R0StatusFieldSerializer.class)
  protected final EnumWrapper<DocumentSplitV2026R0StatusField> status;

  @JsonProperty("split_input")
  protected final DocumentSplitCreateRequestTypeV2026R0 splitInput;

  /** The result of the Document Split job. `null` until the job has produced output. */
  protected DocumentSplitOutputV2026R0 output;

  @JsonProperty("destination_folder")
  protected final FolderReferenceV2026R0 destinationFolder;

  /** The system that initiated the request, used for analytics and traceability. */
  @JsonDeserialize(
      using =
          DocumentSplitV2026R0InputSourceField.DocumentSplitV2026R0InputSourceFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          DocumentSplitV2026R0InputSourceField.DocumentSplitV2026R0InputSourceFieldSerializer.class)
  @JsonProperty("input_source")
  protected final EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource;

  /** When the Document Split job was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonProperty("created_by")
  protected UserMiniV2026R0 createdBy;

  protected EnterpriseReferenceV2026R0 enterprise;

  /**
   * Errors and warnings that occurred during the document split. `null` when no failures were
   * recorded.
   */
  protected DocumentSplitFailuresV2026R0 failures;

  /**
   * An optional identifier supplied by the caller when the Document Split job was created. Returned
   * for correlation only. It is not a Box resource ID, is not required to be unique, and cannot be
   * used to look up the job.
   */
  @JsonProperty("external_id")
  protected String externalId;

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      DocumentSplitV2026R0StatusField status,
      DocumentSplitCreateRequestManualV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      DocumentSplitV2026R0InputSourceField inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      DocumentSplitV2026R0StatusField status,
      DocumentSplitCreateRequestManualV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = inputSource;
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      DocumentSplitV2026R0StatusField status,
      DocumentSplitCreateRequestPageSelectionV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      DocumentSplitV2026R0InputSourceField inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      DocumentSplitV2026R0StatusField status,
      DocumentSplitCreateRequestPageSelectionV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = inputSource;
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      DocumentSplitV2026R0StatusField status,
      DocumentSplitCreateRequestSmartV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      DocumentSplitV2026R0InputSourceField inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      DocumentSplitV2026R0StatusField status,
      DocumentSplitCreateRequestSmartV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = inputSource;
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      DocumentSplitV2026R0StatusField status,
      DocumentSplitCreateRequestTypeV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      DocumentSplitV2026R0InputSourceField inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
    this.splitInput = splitInput;
    this.destinationFolder = destinationFolder;
    this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      DocumentSplitV2026R0StatusField status,
      DocumentSplitCreateRequestTypeV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
    this.splitInput = splitInput;
    this.destinationFolder = destinationFolder;
    this.inputSource = inputSource;
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      EnumWrapper<DocumentSplitV2026R0StatusField> status,
      DocumentSplitCreateRequestManualV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      DocumentSplitV2026R0InputSourceField inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = status;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      EnumWrapper<DocumentSplitV2026R0StatusField> status,
      DocumentSplitCreateRequestManualV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = status;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = inputSource;
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      EnumWrapper<DocumentSplitV2026R0StatusField> status,
      DocumentSplitCreateRequestPageSelectionV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      DocumentSplitV2026R0InputSourceField inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = status;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      EnumWrapper<DocumentSplitV2026R0StatusField> status,
      DocumentSplitCreateRequestPageSelectionV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = status;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = inputSource;
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      EnumWrapper<DocumentSplitV2026R0StatusField> status,
      DocumentSplitCreateRequestSmartV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      DocumentSplitV2026R0InputSourceField inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = status;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      EnumWrapper<DocumentSplitV2026R0StatusField> status,
      DocumentSplitCreateRequestSmartV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = status;
    this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
    this.destinationFolder = destinationFolder;
    this.inputSource = inputSource;
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      String id,
      FileReferenceV2026R0 sourceFile,
      EnumWrapper<DocumentSplitV2026R0StatusField> status,
      DocumentSplitCreateRequestTypeV2026R0 splitInput,
      FolderReferenceV2026R0 destinationFolder,
      DocumentSplitV2026R0InputSourceField inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = status;
    this.splitInput = splitInput;
    this.destinationFolder = destinationFolder;
    this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  public DocumentSplitV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("source_file") FileReferenceV2026R0 sourceFile,
      @JsonProperty("status") EnumWrapper<DocumentSplitV2026R0StatusField> status,
      @JsonProperty("split_input") DocumentSplitCreateRequestTypeV2026R0 splitInput,
      @JsonProperty("destination_folder") FolderReferenceV2026R0 destinationFolder,
      @JsonProperty("input_source") EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
    super();
    this.id = id;
    this.sourceFile = sourceFile;
    this.status = status;
    this.splitInput = splitInput;
    this.destinationFolder = destinationFolder;
    this.inputSource = inputSource;
    this.type =
        new EnumWrapper<DocumentSplitV2026R0TypeField>(
            DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
  }

  protected DocumentSplitV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.sourceFile = builder.sourceFile;
    this.status = builder.status;
    this.splitInput = builder.splitInput;
    this.output = builder.output;
    this.destinationFolder = builder.destinationFolder;
    this.inputSource = builder.inputSource;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.enterprise = builder.enterprise;
    this.failures = builder.failures;
    this.externalId = builder.externalId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<DocumentSplitV2026R0TypeField> getType() {
    return type;
  }

  public FileReferenceV2026R0 getSourceFile() {
    return sourceFile;
  }

  public EnumWrapper<DocumentSplitV2026R0StatusField> getStatus() {
    return status;
  }

  public DocumentSplitCreateRequestTypeV2026R0 getSplitInput() {
    return splitInput;
  }

  public DocumentSplitOutputV2026R0 getOutput() {
    return output;
  }

  public FolderReferenceV2026R0 getDestinationFolder() {
    return destinationFolder;
  }

  public EnumWrapper<DocumentSplitV2026R0InputSourceField> getInputSource() {
    return inputSource;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public UserMiniV2026R0 getCreatedBy() {
    return createdBy;
  }

  public EnterpriseReferenceV2026R0 getEnterprise() {
    return enterprise;
  }

  public DocumentSplitFailuresV2026R0 getFailures() {
    return failures;
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
    DocumentSplitV2026R0 casted = (DocumentSplitV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(sourceFile, casted.sourceFile)
        && Objects.equals(status, casted.status)
        && Objects.equals(splitInput, casted.splitInput)
        && Objects.equals(output, casted.output)
        && Objects.equals(destinationFolder, casted.destinationFolder)
        && Objects.equals(inputSource, casted.inputSource)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(failures, casted.failures)
        && Objects.equals(externalId, casted.externalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        sourceFile,
        status,
        splitInput,
        output,
        destinationFolder,
        inputSource,
        createdAt,
        createdBy,
        enterprise,
        failures,
        externalId);
  }

  @Override
  public String toString() {
    return "DocumentSplitV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "sourceFile='"
        + sourceFile
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "splitInput='"
        + splitInput
        + '\''
        + ", "
        + "output='"
        + output
        + '\''
        + ", "
        + "destinationFolder='"
        + destinationFolder
        + '\''
        + ", "
        + "inputSource='"
        + inputSource
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "failures='"
        + failures
        + '\''
        + ", "
        + "externalId='"
        + externalId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<DocumentSplitV2026R0TypeField> type;

    protected final FileReferenceV2026R0 sourceFile;

    protected final EnumWrapper<DocumentSplitV2026R0StatusField> status;

    protected final DocumentSplitCreateRequestTypeV2026R0 splitInput;

    protected DocumentSplitOutputV2026R0 output;

    protected final FolderReferenceV2026R0 destinationFolder;

    protected final EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource;

    protected OffsetDateTime createdAt;

    protected UserMiniV2026R0 createdBy;

    protected EnterpriseReferenceV2026R0 enterprise;

    protected DocumentSplitFailuresV2026R0 failures;

    protected String externalId;

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        DocumentSplitV2026R0StatusField status,
        DocumentSplitCreateRequestManualV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        DocumentSplitV2026R0InputSourceField inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        DocumentSplitV2026R0StatusField status,
        DocumentSplitCreateRequestManualV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = inputSource;
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        DocumentSplitV2026R0StatusField status,
        DocumentSplitCreateRequestPageSelectionV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        DocumentSplitV2026R0InputSourceField inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        DocumentSplitV2026R0StatusField status,
        DocumentSplitCreateRequestPageSelectionV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = inputSource;
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        DocumentSplitV2026R0StatusField status,
        DocumentSplitCreateRequestSmartV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        DocumentSplitV2026R0InputSourceField inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        DocumentSplitV2026R0StatusField status,
        DocumentSplitCreateRequestSmartV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = inputSource;
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        DocumentSplitV2026R0StatusField status,
        DocumentSplitCreateRequestTypeV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        DocumentSplitV2026R0InputSourceField inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
      this.splitInput = splitInput;
      this.destinationFolder = destinationFolder;
      this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        DocumentSplitV2026R0StatusField status,
        DocumentSplitCreateRequestTypeV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = new EnumWrapper<DocumentSplitV2026R0StatusField>(status);
      this.splitInput = splitInput;
      this.destinationFolder = destinationFolder;
      this.inputSource = inputSource;
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        EnumWrapper<DocumentSplitV2026R0StatusField> status,
        DocumentSplitCreateRequestManualV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        DocumentSplitV2026R0InputSourceField inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = status;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        EnumWrapper<DocumentSplitV2026R0StatusField> status,
        DocumentSplitCreateRequestManualV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = status;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = inputSource;
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        EnumWrapper<DocumentSplitV2026R0StatusField> status,
        DocumentSplitCreateRequestPageSelectionV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        DocumentSplitV2026R0InputSourceField inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = status;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        EnumWrapper<DocumentSplitV2026R0StatusField> status,
        DocumentSplitCreateRequestPageSelectionV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = status;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = inputSource;
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        EnumWrapper<DocumentSplitV2026R0StatusField> status,
        DocumentSplitCreateRequestSmartV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        DocumentSplitV2026R0InputSourceField inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = status;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        EnumWrapper<DocumentSplitV2026R0StatusField> status,
        DocumentSplitCreateRequestSmartV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = status;
      this.splitInput = new DocumentSplitCreateRequestTypeV2026R0(splitInput);
      this.destinationFolder = destinationFolder;
      this.inputSource = inputSource;
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        EnumWrapper<DocumentSplitV2026R0StatusField> status,
        DocumentSplitCreateRequestTypeV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        DocumentSplitV2026R0InputSourceField inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = status;
      this.splitInput = splitInput;
      this.destinationFolder = destinationFolder;
      this.inputSource = new EnumWrapper<DocumentSplitV2026R0InputSourceField>(inputSource);
    }

    public Builder(
        String id,
        FileReferenceV2026R0 sourceFile,
        EnumWrapper<DocumentSplitV2026R0StatusField> status,
        DocumentSplitCreateRequestTypeV2026R0 splitInput,
        FolderReferenceV2026R0 destinationFolder,
        EnumWrapper<DocumentSplitV2026R0InputSourceField> inputSource) {
      super();
      this.id = id;
      this.sourceFile = sourceFile;
      this.status = status;
      this.splitInput = splitInput;
      this.destinationFolder = destinationFolder;
      this.inputSource = inputSource;
    }

    public Builder type(DocumentSplitV2026R0TypeField type) {
      this.type = new EnumWrapper<DocumentSplitV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<DocumentSplitV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder output(DocumentSplitOutputV2026R0 output) {
      this.output = output;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder createdBy(UserMiniV2026R0 createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder enterprise(EnterpriseReferenceV2026R0 enterprise) {
      this.enterprise = enterprise;
      return this;
    }

    public Builder failures(DocumentSplitFailuresV2026R0 failures) {
      this.failures = failures;
      return this;
    }

    public Builder externalId(String externalId) {
      this.externalId = externalId;
      return this;
    }

    public DocumentSplitV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<DocumentSplitV2026R0TypeField>(
                DocumentSplitV2026R0TypeField.DOCUMENT_SPLIT);
      }
      return new DocumentSplitV2026R0(this);
    }
  }
}
