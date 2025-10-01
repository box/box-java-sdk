package com.box.sdkgen.schemas.v2025r0.docgenbatchcreaterequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.docgendocumentgenerationdatav2025r0.DocGenDocumentGenerationDataV2025R0;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.fileversionbasev2025r0.FileVersionBaseV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** The schema for creating a Box Doc Gen job batch request. */
@JsonFilter("nullablePropertyFilter")
public class DocGenBatchCreateRequestV2025R0 extends SerializableObject {

  protected final FileReferenceV2025R0 file;

  @JsonProperty("file_version")
  protected FileVersionBaseV2025R0 fileVersion;

  /**
   * Source of input. The value has to be `api` for all the API-based document generation requests.
   */
  @JsonProperty("input_source")
  protected final String inputSource;

  @JsonProperty("destination_folder")
  protected final DocGenBatchCreateRequestV2025R0DestinationFolderField destinationFolder;

  /** Type of the output file. */
  @JsonProperty("output_type")
  protected final String outputType;

  @JsonProperty("document_generation_data")
  protected final List<DocGenDocumentGenerationDataV2025R0> documentGenerationData;

  public DocGenBatchCreateRequestV2025R0(
      @JsonProperty("file") FileReferenceV2025R0 file,
      @JsonProperty("input_source") String inputSource,
      @JsonProperty("destination_folder")
          DocGenBatchCreateRequestV2025R0DestinationFolderField destinationFolder,
      @JsonProperty("output_type") String outputType,
      @JsonProperty("document_generation_data")
          List<DocGenDocumentGenerationDataV2025R0> documentGenerationData) {
    super();
    this.file = file;
    this.inputSource = inputSource;
    this.destinationFolder = destinationFolder;
    this.outputType = outputType;
    this.documentGenerationData = documentGenerationData;
  }

  protected DocGenBatchCreateRequestV2025R0(Builder builder) {
    super();
    this.file = builder.file;
    this.fileVersion = builder.fileVersion;
    this.inputSource = builder.inputSource;
    this.destinationFolder = builder.destinationFolder;
    this.outputType = builder.outputType;
    this.documentGenerationData = builder.documentGenerationData;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public FileReferenceV2025R0 getFile() {
    return file;
  }

  public FileVersionBaseV2025R0 getFileVersion() {
    return fileVersion;
  }

  public String getInputSource() {
    return inputSource;
  }

  public DocGenBatchCreateRequestV2025R0DestinationFolderField getDestinationFolder() {
    return destinationFolder;
  }

  public String getOutputType() {
    return outputType;
  }

  public List<DocGenDocumentGenerationDataV2025R0> getDocumentGenerationData() {
    return documentGenerationData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenBatchCreateRequestV2025R0 casted = (DocGenBatchCreateRequestV2025R0) o;
    return Objects.equals(file, casted.file)
        && Objects.equals(fileVersion, casted.fileVersion)
        && Objects.equals(inputSource, casted.inputSource)
        && Objects.equals(destinationFolder, casted.destinationFolder)
        && Objects.equals(outputType, casted.outputType)
        && Objects.equals(documentGenerationData, casted.documentGenerationData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        file, fileVersion, inputSource, destinationFolder, outputType, documentGenerationData);
  }

  @Override
  public String toString() {
    return "DocGenBatchCreateRequestV2025R0{"
        + "file='"
        + file
        + '\''
        + ", "
        + "fileVersion='"
        + fileVersion
        + '\''
        + ", "
        + "inputSource='"
        + inputSource
        + '\''
        + ", "
        + "destinationFolder='"
        + destinationFolder
        + '\''
        + ", "
        + "outputType='"
        + outputType
        + '\''
        + ", "
        + "documentGenerationData='"
        + documentGenerationData
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final FileReferenceV2025R0 file;

    protected FileVersionBaseV2025R0 fileVersion;

    protected final String inputSource;

    protected final DocGenBatchCreateRequestV2025R0DestinationFolderField destinationFolder;

    protected final String outputType;

    protected final List<DocGenDocumentGenerationDataV2025R0> documentGenerationData;

    public Builder(
        FileReferenceV2025R0 file,
        String inputSource,
        DocGenBatchCreateRequestV2025R0DestinationFolderField destinationFolder,
        String outputType,
        List<DocGenDocumentGenerationDataV2025R0> documentGenerationData) {
      super();
      this.file = file;
      this.inputSource = inputSource;
      this.destinationFolder = destinationFolder;
      this.outputType = outputType;
      this.documentGenerationData = documentGenerationData;
    }

    public Builder fileVersion(FileVersionBaseV2025R0 fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public DocGenBatchCreateRequestV2025R0 build() {
      return new DocGenBatchCreateRequestV2025R0(this);
    }
  }
}
