package com.box.sdkgen.test.retentionpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.retentionpolicies.CreateRetentionPolicyRequestBody;
import com.box.sdkgen.managers.retentionpolicies.CreateRetentionPolicyRequestBodyDispositionActionField;
import com.box.sdkgen.managers.retentionpolicies.CreateRetentionPolicyRequestBodyPolicyTypeField;
import com.box.sdkgen.managers.retentionpolicies.CreateRetentionPolicyRequestBodyRetentionTypeField;
import com.box.sdkgen.managers.retentionpolicyassignments.CreateRetentionPolicyAssignmentRequestBody;
import com.box.sdkgen.managers.retentionpolicyassignments.CreateRetentionPolicyAssignmentRequestBodyAssignToField;
import com.box.sdkgen.managers.retentionpolicyassignments.CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.managers.uploads.UploadFileVersionRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileVersionRequestBodyAttributesField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.filesunderretention.FilesUnderRetention;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.retentionpolicy.RetentionPolicy;
import com.box.sdkgen.schemas.retentionpolicyassignment.RetentionPolicyAssignment;
import com.box.sdkgen.schemas.retentionpolicyassignments.RetentionPolicyAssignments;
import org.junit.jupiter.api.Test;

public class RetentionPolicyAssignmentsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateUpdateGetDeleteRetentionPolicyAssignment() {
    String retentionPolicyName = getUuid();
    String retentionDescription = "test description";
    RetentionPolicy retentionPolicy =
        client
            .getRetentionPolicies()
            .createRetentionPolicy(
                new CreateRetentionPolicyRequestBody.Builder(
                        retentionPolicyName,
                        CreateRetentionPolicyRequestBodyPolicyTypeField.FINITE,
                        CreateRetentionPolicyRequestBodyDispositionActionField.REMOVE_RETENTION)
                    .description(retentionDescription)
                    .retentionLength("1")
                    .retentionType(CreateRetentionPolicyRequestBodyRetentionTypeField.MODIFIABLE)
                    .canOwnerExtendRetention(true)
                    .areOwnersNotified(true)
                    .build());
    FolderFull folder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    Files files =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(), new UploadFileRequestBodyAttributesParentField(folder.getId())),
                    generateByteStream(10)));
    FileFull file = files.getEntries().get(0);
    Files newVersions =
        client
            .getUploads()
            .uploadFileVersion(
                file.getId(),
                new UploadFileVersionRequestBody(
                    new UploadFileVersionRequestBodyAttributesField(getUuid()),
                    generateByteStream(20)));
    FileFull newVersion = newVersions.getEntries().get(0);
    RetentionPolicyAssignment retentionPolicyAssignment =
        client
            .getRetentionPolicyAssignments()
            .createRetentionPolicyAssignment(
                new CreateRetentionPolicyAssignmentRequestBody(
                    retentionPolicy.getId(),
                    new CreateRetentionPolicyAssignmentRequestBodyAssignToField.Builder(
                            CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField.FOLDER)
                        .id(folder.getId())
                        .build()));
    assert retentionPolicyAssignment.getRetentionPolicy().getId().equals(retentionPolicy.getId());
    assert retentionPolicyAssignment.getAssignedTo().getId().equals(folder.getId());
    RetentionPolicyAssignment retentionPolicyAssignmentById =
        client
            .getRetentionPolicyAssignments()
            .getRetentionPolicyAssignmentById(retentionPolicyAssignment.getId());
    assert retentionPolicyAssignmentById.getId().equals(retentionPolicyAssignment.getId());
    RetentionPolicyAssignments retentionPolicyAssignments =
        client
            .getRetentionPolicyAssignments()
            .getRetentionPolicyAssignments(retentionPolicy.getId());
    assert retentionPolicyAssignments.getEntries().size() == 1;
    FilesUnderRetention filesUnderRetention =
        client
            .getRetentionPolicyAssignments()
            .getFilesUnderRetentionPolicyAssignment(retentionPolicyAssignment.getId());
    assert filesUnderRetention.getEntries().size() == 1;
    client
        .getRetentionPolicyAssignments()
        .deleteRetentionPolicyAssignmentById(retentionPolicyAssignment.getId());
    RetentionPolicyAssignments retentionPolicyAssignmentsAfterDelete =
        client
            .getRetentionPolicyAssignments()
            .getRetentionPolicyAssignments(retentionPolicy.getId());
    assert retentionPolicyAssignmentsAfterDelete.getEntries().size() == 0;
    client.getRetentionPolicies().deleteRetentionPolicyById(retentionPolicy.getId());
    client.getFiles().deleteFileById(file.getId());
  }
}
