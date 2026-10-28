package com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingimportcreaterequestv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * The request body used to import a shuttle job's subject mappings from a spreadsheet already
 * stored in Box.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSubjectMappingImportCreateRequestV2026R0 extends SerializableObject {

  /** The unique identifier of the shuttle job whose subject mappings are replaced. */
  @JsonProperty("job_id")
  protected final String jobId;

  /**
   * The unique identifier of the Box file holding the mapping workbook. The file is read as the
   * calling user, so it must live somewhere that user can download from.
   */
  @JsonProperty("file_id")
  protected final String fileId;

  public ShuttleSubjectMappingImportCreateRequestV2026R0(
      @JsonProperty("job_id") String jobId, @JsonProperty("file_id") String fileId) {
    super();
    this.jobId = jobId;
    this.fileId = fileId;
  }

  public String getJobId() {
    return jobId;
  }

  public String getFileId() {
    return fileId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleSubjectMappingImportCreateRequestV2026R0 casted =
        (ShuttleSubjectMappingImportCreateRequestV2026R0) o;
    return Objects.equals(jobId, casted.jobId) && Objects.equals(fileId, casted.fileId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId, fileId);
  }

  @Override
  public String toString() {
    return "ShuttleSubjectMappingImportCreateRequestV2026R0{"
        + "jobId='"
        + jobId
        + '\''
        + ", "
        + "fileId='"
        + fileId
        + '\''
        + "}";
  }
}
