package com.box.sdkgen.test.fileversionretentions;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.DeleteFolderByIdQueryParams;
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
import com.box.sdkgen.schemas.fileversionretention.FileVersionRetention;
import com.box.sdkgen.schemas.fileversionretentions.FileVersionRetentions;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.retentionpolicy.RetentionPolicy;
import com.box.sdkgen.schemas.retentionpolicyassignment.RetentionPolicyAssignment;
import org.junit.jupiter.api.Test;

public class FileVersionRetentionsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateUpdateGetDeleteRetentionPolicy() {
    String description = getUuid();
    RetentionPolicy retentionPolicy =
        client
            .getRetentionPolicies()
            .createRetentionPolicy(
                new CreateRetentionPolicyRequestBody.Builder(
                        getUuid(),
                        CreateRetentionPolicyRequestBodyPolicyTypeField.FINITE,
                        CreateRetentionPolicyRequestBodyDispositionActionField.REMOVE_RETENTION)
                    .description(description)
                    .retentionLength("1")
                    .retentionType(CreateRetentionPolicyRequestBodyRetentionTypeField.MODIFIABLE)
                    .canOwnerExtendRetention(false)
                    .build());
    assert retentionPolicy.getDescription().equals(description);
    assert retentionPolicy.getCanOwnerExtendRetention() == false;
    assert convertToString(retentionPolicy.getRetentionType()).equals("modifiable");
    FolderFull folder = createNewFolder();
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
    assert convertToString(retentionPolicyAssignment.getAssignedTo().getType())
        .equals(convertToString(folder.getType()));
    Files files =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(), new UploadFileRequestBodyAttributesParentField(folder.getId())),
                    generateByteStream(10)));
    FileFull file = files.getEntries().get(0);
    Files newFiles =
        client
            .getUploads()
            .uploadFileVersion(
                file.getId(),
                new UploadFileVersionRequestBody(
                    new UploadFileVersionRequestBodyAttributesField(file.getName()),
                    generateByteStream(20)));
    FileFull newFile = newFiles.getEntries().get(0);
    assert newFile.getId().equals(file.getId());
    FileVersionRetentions fileVersionRetentions =
        client.getFileVersionRetentions().getFileVersionRetentions();
    int fileVersionRetentionsCount = fileVersionRetentions.getEntries().size();
    assert fileVersionRetentionsCount >= 0;
    if (fileVersionRetentionsCount == 0) {
      client.getRetentionPolicies().deleteRetentionPolicyById(retentionPolicy.getId());
      client
          .getFolders()
          .deleteFolderById(
              folder.getId(), new DeleteFolderByIdQueryParams.Builder().recursive(true).build());
      return;
    }
    FileVersionRetention fileVersionRetention = fileVersionRetentions.getEntries().get(0);
    FileVersionRetention fileVersionRetentionById =
        client.getFileVersionRetentions().getFileVersionRetentionById(fileVersionRetention.getId());
    assert fileVersionRetentionById.getId().equals(fileVersionRetention.getId());
    client.getRetentionPolicies().deleteRetentionPolicyById(retentionPolicy.getId());
    client
        .getFolders()
        .deleteFolderById(
            folder.getId(), new DeleteFolderByIdQueryParams.Builder().recursive(true).build());
  }
}
