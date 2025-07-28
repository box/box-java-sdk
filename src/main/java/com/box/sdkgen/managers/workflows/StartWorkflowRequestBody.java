package com.box.sdkgen.managers.workflows;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.outcome.Outcome;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class StartWorkflowRequestBody extends SerializableObject {

  @JsonDeserialize(
      using = StartWorkflowRequestBodyTypeField.StartWorkflowRequestBodyTypeFieldDeserializer.class)
  @JsonSerialize(
      using = StartWorkflowRequestBodyTypeField.StartWorkflowRequestBodyTypeFieldSerializer.class)
  protected EnumWrapper<StartWorkflowRequestBodyTypeField> type;

  protected final StartWorkflowRequestBodyFlowField flow;

  protected final List<StartWorkflowRequestBodyFilesField> files;

  protected final StartWorkflowRequestBodyFolderField folder;

  protected List<Outcome> outcomes;

  public StartWorkflowRequestBody(
      @JsonProperty("flow") StartWorkflowRequestBodyFlowField flow,
      @JsonProperty("files") List<StartWorkflowRequestBodyFilesField> files,
      @JsonProperty("folder") StartWorkflowRequestBodyFolderField folder) {
    super();
    this.flow = flow;
    this.files = files;
    this.folder = folder;
  }

  protected StartWorkflowRequestBody(Builder builder) {
    super();
    this.type = builder.type;
    this.flow = builder.flow;
    this.files = builder.files;
    this.folder = builder.folder;
    this.outcomes = builder.outcomes;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<StartWorkflowRequestBodyTypeField> getType() {
    return type;
  }

  public StartWorkflowRequestBodyFlowField getFlow() {
    return flow;
  }

  public List<StartWorkflowRequestBodyFilesField> getFiles() {
    return files;
  }

  public StartWorkflowRequestBodyFolderField getFolder() {
    return folder;
  }

  public List<Outcome> getOutcomes() {
    return outcomes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartWorkflowRequestBody casted = (StartWorkflowRequestBody) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(flow, casted.flow)
        && Objects.equals(files, casted.files)
        && Objects.equals(folder, casted.folder)
        && Objects.equals(outcomes, casted.outcomes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, flow, files, folder, outcomes);
  }

  @Override
  public String toString() {
    return "StartWorkflowRequestBody{"
        + "type='"
        + type
        + '\''
        + ", "
        + "flow='"
        + flow
        + '\''
        + ", "
        + "files='"
        + files
        + '\''
        + ", "
        + "folder='"
        + folder
        + '\''
        + ", "
        + "outcomes='"
        + outcomes
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<StartWorkflowRequestBodyTypeField> type;

    protected final StartWorkflowRequestBodyFlowField flow;

    protected final List<StartWorkflowRequestBodyFilesField> files;

    protected final StartWorkflowRequestBodyFolderField folder;

    protected List<Outcome> outcomes;

    public Builder(
        StartWorkflowRequestBodyFlowField flow,
        List<StartWorkflowRequestBodyFilesField> files,
        StartWorkflowRequestBodyFolderField folder) {
      super();
      this.flow = flow;
      this.files = files;
      this.folder = folder;
    }

    public Builder type(StartWorkflowRequestBodyTypeField type) {
      this.type = new EnumWrapper<StartWorkflowRequestBodyTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<StartWorkflowRequestBodyTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder outcomes(List<Outcome> outcomes) {
      this.outcomes = outcomes;
      return this;
    }

    public StartWorkflowRequestBody build() {
      return new StartWorkflowRequestBody(this);
    }
  }
}
