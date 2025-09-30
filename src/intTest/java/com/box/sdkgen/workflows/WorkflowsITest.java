package com.box.sdkgen.workflows;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.managers.workflows.GetWorkflowsQueryParams;
import com.box.sdkgen.managers.workflows.StartWorkflowRequestBody;
import com.box.sdkgen.managers.workflows.StartWorkflowRequestBodyFilesField;
import com.box.sdkgen.managers.workflows.StartWorkflowRequestBodyFilesTypeField;
import com.box.sdkgen.managers.workflows.StartWorkflowRequestBodyFlowField;
import com.box.sdkgen.managers.workflows.StartWorkflowRequestBodyFolderField;
import com.box.sdkgen.managers.workflows.StartWorkflowRequestBodyFolderTypeField;
import com.box.sdkgen.managers.workflows.StartWorkflowRequestBodyTypeField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.workflow.Workflow;
import com.box.sdkgen.schemas.workflows.Workflows;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class WorkflowsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testWorkflows() {
    BoxClient adminClient = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String workflowFolderId = getEnvVar("WORKFLOW_FOLDER_ID");
    Files uploadedFiles =
        adminClient
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(),
                        new UploadFileRequestBodyAttributesParentField(workflowFolderId)),
                    generateByteStream(1024 * 1024)));
    FileFull file = uploadedFiles.getEntries().get(0);
    String workflowFileId = file.getId();
    Workflows workflows =
        adminClient.getWorkflows().getWorkflows(new GetWorkflowsQueryParams(workflowFolderId));
    assert workflows.getEntries().size() == 1;
    Workflow workflowToRun = workflows.getEntries().get(0);
    assert convertToString(workflowToRun.getType()).equals("workflow");
    assert workflowToRun.getIsEnabled() == true;
    assert convertToString(workflowToRun.getFlows().get(0).getType()).equals("flow");
    assert convertToString(workflowToRun.getFlows().get(0).getTrigger().getType())
        .equals("trigger");
    assert convertToString(workflowToRun.getFlows().get(0).getTrigger().getTriggerType())
        .equals("WORKFLOW_MANUAL_START");
    assert convertToString(workflowToRun.getFlows().get(0).getOutcomes().get(0).getActionType())
        .equals("delete_file");
    assert convertToString(workflowToRun.getFlows().get(0).getOutcomes().get(0).getType())
        .equals("outcome");
    adminClient
        .getWorkflows()
        .startWorkflow(
            workflowToRun.getId(),
            new StartWorkflowRequestBody.Builder(
                    new StartWorkflowRequestBodyFlowField.Builder()
                        .type("flow")
                        .id(workflowToRun.getFlows().get(0).getId())
                        .build(),
                    Arrays.asList(
                        new StartWorkflowRequestBodyFilesField.Builder()
                            .type(StartWorkflowRequestBodyFilesTypeField.FILE)
                            .id(workflowFileId)
                            .build()),
                    new StartWorkflowRequestBodyFolderField.Builder()
                        .type(StartWorkflowRequestBodyFolderTypeField.FOLDER)
                        .id(workflowFolderId)
                        .build())
                .type(StartWorkflowRequestBodyTypeField.WORKFLOW_PARAMETERS)
                .build());
  }
}
