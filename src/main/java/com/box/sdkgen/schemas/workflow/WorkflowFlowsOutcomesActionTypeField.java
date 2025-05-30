package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum WorkflowFlowsOutcomesActionTypeField implements Valuable {
  ADD_METADATA("add_metadata"),
  ASSIGN_TASK("assign_task"),
  COPY_FILE("copy_file"),
  COPY_FOLDER("copy_folder"),
  CREATE_FOLDER("create_folder"),
  DELETE_FILE("delete_file"),
  DELETE_FOLDER("delete_folder"),
  LOCK_FILE("lock_file"),
  MOVE_FILE("move_file"),
  MOVE_FOLDER("move_folder"),
  REMOVE_WATERMARK_FILE("remove_watermark_file"),
  RENAME_FOLDER("rename_folder"),
  RESTORE_FOLDER("restore_folder"),
  SHARE_FILE("share_file"),
  SHARE_FOLDER("share_folder"),
  UNLOCK_FILE("unlock_file"),
  UPLOAD_FILE("upload_file"),
  WAIT_FOR_TASK("wait_for_task"),
  WATERMARK_FILE("watermark_file"),
  GO_BACK_TO_STEP("go_back_to_step"),
  APPLY_FILE_CLASSIFICATION("apply_file_classification"),
  APPLY_FOLDER_CLASSIFICATION("apply_folder_classification"),
  SEND_NOTIFICATION("send_notification");

  private final String value;

  WorkflowFlowsOutcomesActionTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WorkflowFlowsOutcomesActionTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WorkflowFlowsOutcomesActionTypeField>> {

    public WorkflowFlowsOutcomesActionTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WorkflowFlowsOutcomesActionTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WorkflowFlowsOutcomesActionTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WorkflowFlowsOutcomesActionTypeField>(value));
    }
  }

  public static class WorkflowFlowsOutcomesActionTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WorkflowFlowsOutcomesActionTypeField>> {

    public WorkflowFlowsOutcomesActionTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WorkflowFlowsOutcomesActionTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
