package com.box.sdkgen.schemas.v2025r0.docgenjobfullv2025r0;

import com.box.sdkgen.schemas.v2025r0.docgenbatchbasev2025r0.DocGenBatchBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobbasev2025r0.DocGenJobBaseV2025R0TypeField;
import com.box.sdkgen.schemas.v2025r0.docgenjobv2025r0.DocGenJobV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobv2025r0.DocGenJobV2025R0StatusField;
import com.box.sdkgen.schemas.v2025r0.enterprisereferencev2025r0.EnterpriseReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.fileversionbasev2025r0.FileVersionBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.userbasev2025r0.UserBaseV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A full representation of a Box Doc Gen job. */
@JsonFilter("nullablePropertyFilter")
public class DocGenJobFullV2025R0 extends DocGenJobV2025R0 {

  /** Time of job creation. */
  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("created_by")
  protected final UserBaseV2025R0 createdBy;

  protected final EnterpriseReferenceV2025R0 enterprise;

  /** Source of the request. */
  protected final String source;

  public DocGenJobFullV2025R0(
      String id,
      DocGenBatchBaseV2025R0 batch,
      FileReferenceV2025R0 templateFile,
      FileVersionBaseV2025R0 templateFileVersion,
      DocGenJobV2025R0StatusField status,
      String outputType,
      UserBaseV2025R0 createdBy,
      EnterpriseReferenceV2025R0 enterprise,
      String source) {
    super(id, batch, templateFile, templateFileVersion, status, outputType);
    this.createdBy = createdBy;
    this.enterprise = enterprise;
    this.source = source;
  }

  public DocGenJobFullV2025R0(
      @JsonProperty("id") String id,
      @JsonProperty("batch") DocGenBatchBaseV2025R0 batch,
      @JsonProperty("template_file") FileReferenceV2025R0 templateFile,
      @JsonProperty("template_file_version") FileVersionBaseV2025R0 templateFileVersion,
      @JsonProperty("status") EnumWrapper<DocGenJobV2025R0StatusField> status,
      @JsonProperty("output_type") String outputType,
      @JsonProperty("created_by") UserBaseV2025R0 createdBy,
      @JsonProperty("enterprise") EnterpriseReferenceV2025R0 enterprise,
      @JsonProperty("source") String source) {
    super(id, batch, templateFile, templateFileVersion, status, outputType);
    this.createdBy = createdBy;
    this.enterprise = enterprise;
    this.source = source;
  }

  protected DocGenJobFullV2025R0(Builder builder) {
    super(builder);
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.enterprise = builder.enterprise;
    this.source = builder.source;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public UserBaseV2025R0 getCreatedBy() {
    return createdBy;
  }

  public EnterpriseReferenceV2025R0 getEnterprise() {
    return enterprise;
  }

  public String getSource() {
    return source;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenJobFullV2025R0 casted = (DocGenJobFullV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(batch, casted.batch)
        && Objects.equals(templateFile, casted.templateFile)
        && Objects.equals(templateFileVersion, casted.templateFileVersion)
        && Objects.equals(outputFile, casted.outputFile)
        && Objects.equals(outputFileVersion, casted.outputFileVersion)
        && Objects.equals(status, casted.status)
        && Objects.equals(outputType, casted.outputType)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(source, casted.source);
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
        outputType,
        createdAt,
        createdBy,
        enterprise,
        source);
  }

  @Override
  public String toString() {
    return "DocGenJobFullV2025R0{"
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
        + "source='"
        + source
        + '\''
        + "}";
  }

  public static class Builder extends DocGenJobV2025R0.Builder {

    protected String createdAt;

    protected final UserBaseV2025R0 createdBy;

    protected final EnterpriseReferenceV2025R0 enterprise;

    protected final String source;

    public Builder(
        String id,
        DocGenBatchBaseV2025R0 batch,
        FileReferenceV2025R0 templateFile,
        FileVersionBaseV2025R0 templateFileVersion,
        DocGenJobV2025R0StatusField status,
        String outputType,
        UserBaseV2025R0 createdBy,
        EnterpriseReferenceV2025R0 enterprise,
        String source) {
      super(id, batch, templateFile, templateFileVersion, status, outputType);
      this.createdBy = createdBy;
      this.enterprise = enterprise;
      this.source = source;
    }

    public Builder(
        String id,
        DocGenBatchBaseV2025R0 batch,
        FileReferenceV2025R0 templateFile,
        FileVersionBaseV2025R0 templateFileVersion,
        EnumWrapper<DocGenJobV2025R0StatusField> status,
        String outputType,
        UserBaseV2025R0 createdBy,
        EnterpriseReferenceV2025R0 enterprise,
        String source) {
      super(id, batch, templateFile, templateFileVersion, status, outputType);
      this.createdBy = createdBy;
      this.enterprise = enterprise;
      this.source = source;
    }

    public Builder createdAt(String createdAt) {
      this.createdAt = createdAt;
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

    @Override
    public Builder outputFile(FileReferenceV2025R0 outputFile) {
      this.outputFile = outputFile;
      return this;
    }

    @Override
    public Builder outputFileVersion(FileVersionBaseV2025R0 outputFileVersion) {
      this.outputFileVersion = outputFileVersion;
      return this;
    }

    public DocGenJobFullV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<DocGenJobBaseV2025R0TypeField>(
                DocGenJobBaseV2025R0TypeField.DOCGEN_JOB);
      }
      return new DocGenJobFullV2025R0(this);
    }
  }
}
