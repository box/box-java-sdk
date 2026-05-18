package com.box.sdkgen.schemas.v2026r0.automateworkflowstartrequestv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** Request body to start an Automate workflow. */
@JsonFilter("nullablePropertyFilter")
public class AutomateWorkflowStartRequestV2026R0 extends SerializableObject {

  /** The callable action ID used to trigger the selected workflow. */
  @JsonProperty("workflow_action_id")
  protected final String workflowActionId;

  /** The files to process with the selected workflow. */
  @JsonProperty("file_ids")
  protected final List<String> fileIds;

  public AutomateWorkflowStartRequestV2026R0(
      @JsonProperty("workflow_action_id") String workflowActionId,
      @JsonProperty("file_ids") List<String> fileIds) {
    super();
    this.workflowActionId = workflowActionId;
    this.fileIds = fileIds;
  }

  public String getWorkflowActionId() {
    return workflowActionId;
  }

  public List<String> getFileIds() {
    return fileIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AutomateWorkflowStartRequestV2026R0 casted = (AutomateWorkflowStartRequestV2026R0) o;
    return Objects.equals(workflowActionId, casted.workflowActionId)
        && Objects.equals(fileIds, casted.fileIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workflowActionId, fileIds);
  }

  @Override
  public String toString() {
    return "AutomateWorkflowStartRequestV2026R0{"
        + "workflowActionId='"
        + workflowActionId
        + '\''
        + ", "
        + "fileIds='"
        + fileIds
        + '\''
        + "}";
  }
}
