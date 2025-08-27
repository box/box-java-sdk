package com.box.sdkgen.test.externalusers;

import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.test.commons.CommonsManager.uploadNewFile;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBody;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyAccessibleByField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyAccessibleByTypeField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyItemField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyItemTypeField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyRoleField;
import com.box.sdkgen.schemas.collaboration.Collaboration;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.v2025r0.externaluserssubmitdeletejobrequestv2025r0.ExternalUsersSubmitDeleteJobRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.externaluserssubmitdeletejobresponsev2025r0.ExternalUsersSubmitDeleteJobResponseV2025R0;
import com.box.sdkgen.schemas.v2025r0.userreferencev2025r0.UserReferenceV2025R0;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ExternalUsersITest {

  private static final BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));

  @Test
  public void testSubmitJobToDeleteExternalUsers() {
    FileFull file = uploadNewFile();
    Collaboration fileCollaboration =
        client
            .getUserCollaborations()
            .createCollaboration(
                new CreateCollaborationRequestBody(
                    new CreateCollaborationRequestBodyItemField.Builder()
                        .type(CreateCollaborationRequestBodyItemTypeField.FILE)
                        .id(file.getId())
                        .build(),
                    new CreateCollaborationRequestBodyAccessibleByField.Builder(
                            CreateCollaborationRequestBodyAccessibleByTypeField.USER)
                        .id(getEnvVar("BOX_EXTERNAL_USER_ID"))
                        .build(),
                    CreateCollaborationRequestBodyRoleField.EDITOR));
    ExternalUsersSubmitDeleteJobResponseV2025R0 externalUsersJobDeleteResponse =
        client
            .getExternalUsers()
            .submitJobToDeleteExternalUsersV2025R0(
                new ExternalUsersSubmitDeleteJobRequestV2025R0(
                    Arrays.asList(new UserReferenceV2025R0(getEnvVar("BOX_EXTERNAL_USER_ID")))));
    assert externalUsersJobDeleteResponse.getEntries().size() == 1;
    assert externalUsersJobDeleteResponse.getEntries().get(0).getStatus() == 202;
    client.getFiles().deleteFileById(file.getId());
  }
}
