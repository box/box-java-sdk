package com.box.sdkgen.automateworkflows;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.automateworkflows.GetAutomateWorkflowsV2026R0QueryParams;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.v2026r0.automateworkflowactionv2026r0.AutomateWorkflowActionV2026R0;
import com.box.sdkgen.schemas.v2026r0.automateworkflowstartrequestv2026r0.AutomateWorkflowStartRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.automateworkflowsv2026r0.AutomateWorkflowsV2026R0;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AutomateWorkflowsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testAutomateWorkflows() {
    BoxClient adminClient = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String workflowFolderId = getEnvVar("AUTOMATE_WORKFLOW_FOLDER_ID");
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
    AutomateWorkflowsV2026R0 automateWorkflows =
        adminClient
            .getAutomateWorkflows()
            .getAutomateWorkflowsV2026R0(
                new GetAutomateWorkflowsV2026R0QueryParams(workflowFolderId));
    assert automateWorkflows.getEntries().size() == 1;
    AutomateWorkflowActionV2026R0 workflowAction = automateWorkflows.getEntries().get(0);
    assert convertToString(workflowAction.getType()).equals("workflow_action");
    assert convertToString(workflowAction.getActionType()).equals("run_workflow");
    assert convertToString(workflowAction.getWorkflow().getType()).equals("workflow");
    adminClient
        .getAutomateWorkflows()
        .createAutomateWorkflowStartV2026R0(
            workflowAction.getWorkflow().getId(),
            new AutomateWorkflowStartRequestV2026R0(
                workflowAction.getId(), Arrays.asList(workflowFileId)));
  }
}
