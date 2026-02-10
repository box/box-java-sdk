package com.box.sdkgen.schemas.v2025r0.docgenjobv2025r0;

import com.box.sdkgen.schemas.v2025r0.docgenbatchbasev2025r0.DocGenBatchBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobbasev2025r0.DocGenJobBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobbasev2025r0.DocGenJobBaseV2025R0TypeField;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.fileversionbasev2025r0.FileVersionBaseV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A standard representation of a Box Doc Gen job. */
@JsonFilter("nullablePropertyFilter")
public class DocGenJobV2025R0 extends DocGenJobBaseV2025R0 {

  protected final DocGenBatchBaseV2025R0 batch;

  @JsonProperty("template_file")
  protected final FileReferenceV2025R0 templateFile;

  @JsonProperty("template_file_version")
  protected final FileVersionBaseV2025R0 templateFileVersion;

  @JsonProperty("output_file")
  protected FileReferenceV2025R0 outputFile;

  @JsonProperty("output_file_version")
  protected FileVersionBaseV2025R0 outputFileVersion;

  /** Status of the job. */
  @JsonDeserialize(
      using = DocGenJobV2025R0StatusField.DocGenJobV2025R0StatusFieldDeserializer.class)
  @JsonSerialize(using = DocGenJobV2025R0StatusField.DocGenJobV2025R0StatusFieldSerializer.class)
  protected final EnumWrapper<DocGenJobV2025R0StatusField> status;

  /** Type of the generated file. */
  @JsonProperty("output_type")
  protected final String outputType;

  public DocGenJobV2025R0(
      String id,
      DocGenBatchBaseV2025R0 batch,
      FileReferenceV2025R0 templateFile,
      FileVersionBaseV2025R0 templateFileVersion,
      DocGenJobV2025R0StatusField status,
      String outputType) {
    super(id);
    this.batch = batch;
    this.templateFile = templateFile;
    this.templateFileVersion = templateFileVersion;
    this.status = new EnumWrapper<DocGenJobV2025R0StatusField>(status);
    this.outputType = outputType;
  }

  public DocGenJobV2025R0(
      @JsonProperty("id") String id,
      @JsonProperty("batch") DocGenBatchBaseV2025R0 batch,
      @JsonProperty("template_file") FileReferenceV2025R0 templateFile,
      @JsonProperty("template_file_version") FileVersionBaseV2025R0 templateFileVersion,
      @JsonProperty("status") EnumWrapper<DocGenJobV2025R0StatusField> status,
      @JsonProperty("output_type") String outputType) {
    super(id);
    this.batch = batch;
    this.templateFile = templateFile;
    this.templateFileVersion = templateFileVersion;
    this.status = status;
    this.outputType = outputType;
  }

  protected DocGenJobV2025R0(Builder builder) {
    super(builder);
    this.batch = builder.batch;
    this.templateFile = builder.templateFile;
    this.templateFileVersion = builder.templateFileVersion;
    this.outputFile = builder.outputFile;
    this.outputFileVersion = builder.outputFileVersion;
    this.status = builder.status;
    this.outputType = builder.outputType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public DocGenBatchBaseV2025R0 getBatch() {
    return batch;
  }

  public FileReferenceV2025R0 getTemplateFile() {
    return templateFile;
  }

  public FileVersionBaseV2025R0 getTemplateFileVersion() {
    return templateFileVersion;
  }

  public FileReferenceV2025R0 getOutputFile() {
    return outputFile;
  }

  public FileVersionBaseV2025R0 getOutputFileVersion() {
    return outputFileVersion;
  }

  public EnumWrapper<DocGenJobV2025R0StatusField> getStatus() {
    return status;
  }

  public String getOutputType() {
    return outputType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenJobV2025R0 casted = (DocGenJobV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(batch, casted.batch)
        && Objects.equals(templateFile, casted.templateFile)
        && Objects.equals(templateFileVersion, casted.templateFileVersion)
        && Objects.equals(outputFile, casted.outputFile)
        && Objects.equals(outputFileVersion, casted.outputFileVersion)
        && Objects.equals(status, casted.status)
        && Objects.equals(outputType, casted.outputType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        batch,
        templateFile,
        templateFileVersion,
        outputFile,
        outputFileVersion,
        status,
        outputType);
  }

  @Override
  public String toString() {
    return "DocGenJobV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "batch='"
        + batch
        + '\''
        + ", "
        + "templateFile='"
        + templateFile
        + '\''
        + ", "
        + "templateFileVersion='"
        + templateFileVersion
        + '\''
        + ", "
        + "outputFile='"
        + outputFile
        + '\''
        + ", "
        + "outputFileVersion='"
        + outputFileVersion
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "outputType='"
        + outputType
        + '\''
        + "}";
  }

  public static class Builder extends DocGenJobBaseV2025R0.Builder {

    protected final DocGenBatchBaseV2025R0 batch;

    protected final FileReferenceV2025R0 templateFile;

    protected final FileVersionBaseV2025R0 templateFileVersion;

    protected FileReferenceV2025R0 outputFile;

    protected FileVersionBaseV2025R0 outputFileVersion;

    protected final EnumWrapper<DocGenJobV2025R0StatusField> status;

    protected final String outputType;

    public Builder(
        String id,
        DocGenBatchBaseV2025R0 batch,
        FileReferenceV2025R0 templateFile,
        FileVersionBaseV2025R0 templateFileVersion,
        DocGenJobV2025R0StatusField status,
        String outputType) {
      super(id);
      this.batch = batch;
      this.templateFile = templateFile;
      this.templateFileVersion = templateFileVersion;
      this.status = new EnumWrapper<DocGenJobV2025R0StatusField>(status);
      this.outputType = outputType;
    }

    public Builder(
        String id,
        DocGenBatchBaseV2025R0 batch,
        FileReferenceV2025R0 templateFile,
        FileVersionBaseV2025R0 templateFileVersion,
        EnumWrapper<DocGenJobV2025R0StatusField> status,
        String outputType) {
      super(id);
      this.batch = batch;
      this.templateFile = templateFile;
      this.templateFileVersion = templateFileVersion;
      this.status = status;
      this.outputType = outputType;
    }

    public Builder outputFile(FileReferenceV2025R0 outputFile) {
      this.outputFile = outputFile;
      return this;
    }

    public Builder outputFileVersion(FileVersionBaseV2025R0 outputFileVersion) {
      this.outputFileVersion = outputFileVersion;
      return this;
    }

    @Override
    public Builder type(DocGenJobBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<DocGenJobBaseV2025R0TypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<DocGenJobBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public DocGenJobV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<DocGenJobBaseV2025R0TypeField>(
                DocGenJobBaseV2025R0TypeField.DOCGEN_JOB);
      }
      return new DocGenJobV2025R0(this);
    }
  }
}
