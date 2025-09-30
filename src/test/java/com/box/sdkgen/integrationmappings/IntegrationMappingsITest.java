package com.box.sdkgen.integrationmappings;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.integrationmappings.UpdateSlackIntegrationMappingByIdRequestBody;
import com.box.sdkgen.managers.integrationmappings.UpdateTeamsIntegrationMappingByIdRequestBody;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBody;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyAccessibleByField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyAccessibleByTypeField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyItemField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyItemTypeField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyRoleField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.folderreference.FolderReference;
import com.box.sdkgen.schemas.integrationmapping.IntegrationMapping;
import com.box.sdkgen.schemas.integrationmappingboxitemslack.IntegrationMappingBoxItemSlack;
import com.box.sdkgen.schemas.integrationmappingpartneritemslack.IntegrationMappingPartnerItemSlack;
import com.box.sdkgen.schemas.integrationmappingpartneritemteamscreaterequest.IntegrationMappingPartnerItemTeamsCreateRequest;
import com.box.sdkgen.schemas.integrationmappingpartneritemteamscreaterequest.IntegrationMappingPartnerItemTeamsCreateRequestTypeField;
import com.box.sdkgen.schemas.integrationmappings.IntegrationMappings;
import com.box.sdkgen.schemas.integrationmappingslackcreaterequest.IntegrationMappingSlackCreateRequest;
import com.box.sdkgen.schemas.integrationmappingteamscreaterequest.IntegrationMappingTeamsCreateRequest;
import org.junit.jupiter.api.Test;

public class IntegrationMappingsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testSlackIntegrationMappings() {
    String userId = getEnvVar("USER_ID");
    String slackAutomationUserId = getEnvVar("SLACK_AUTOMATION_USER_ID");
    String slackOrgId = getEnvVar("SLACK_ORG_ID");
    String slackPartnerItemId = getEnvVar("SLACK_PARTNER_ITEM_ID");
    BoxClient userClient = getDefaultClientWithUserSubject(userId);
    FolderFull folder =
        userClient
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    userClient
        .getUserCollaborations()
        .createCollaboration(
            new CreateCollaborationRequestBody(
                new CreateCollaborationRequestBodyItemField.Builder()
                    .type(CreateCollaborationRequestBodyItemTypeField.FOLDER)
                    .id(folder.getId())
                    .build(),
                new CreateCollaborationRequestBodyAccessibleByField.Builder(
                        CreateCollaborationRequestBodyAccessibleByTypeField.USER)
                    .id(slackAutomationUserId)
                    .build(),
                CreateCollaborationRequestBodyRoleField.CO_OWNER));
    IntegrationMappings slackIntegrations =
        userClient.getIntegrationMappings().getSlackIntegrationMapping();
    if (slackIntegrations.getEntries().size() == 0) {
      userClient
          .getIntegrationMappings()
          .createSlackIntegrationMapping(
              new IntegrationMappingSlackCreateRequest(
                  new IntegrationMappingPartnerItemSlack.Builder(slackPartnerItemId)
                      .slackOrgId(slackOrgId)
                      .build(),
                  new IntegrationMappingBoxItemSlack(folder.getId())));
    }
    IntegrationMappings slackMappings =
        userClient.getIntegrationMappings().getSlackIntegrationMapping();
    assert slackMappings.getEntries().size() >= 1;
    IntegrationMapping slackIntegrationMapping = slackMappings.getEntries().get(0);
    assert convertToString(slackIntegrationMapping.getIntegrationType()).equals("slack");
    assert convertToString(slackIntegrationMapping.getType()).equals("integration_mapping");
    assert convertToString(slackIntegrationMapping.getBoxItem().getType()).equals("folder");
    assert slackIntegrationMapping.getPartnerItem().getId().equals(slackPartnerItemId);
    assert slackIntegrationMapping.getPartnerItem().getSlackWorkspaceId().equals(slackOrgId);
    assert convertToString(slackIntegrationMapping.getPartnerItem().getType()).equals("channel");
    IntegrationMapping updatedSlackMapping =
        userClient
            .getIntegrationMappings()
            .updateSlackIntegrationMappingById(
                slackIntegrationMapping.getId(),
                new UpdateSlackIntegrationMappingByIdRequestBody.Builder()
                    .boxItem(new IntegrationMappingBoxItemSlack(folder.getId()))
                    .build());
    assert convertToString(updatedSlackMapping.getBoxItem().getType()).equals("folder");
    assert updatedSlackMapping.getBoxItem().getId().equals(folder.getId());
    if (slackMappings.getEntries().size() > 2) {
      userClient
          .getIntegrationMappings()
          .deleteSlackIntegrationMappingById(slackIntegrationMapping.getId());
    }
    userClient.getFolders().deleteFolderById(folder.getId());
  }

  @Test
  public void testTeamsIntegrationMappings() {
    FolderFull folder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    String tenantId = "1";
    String teamId = "2";
    String partnerItemId = "3";
    String userId = getEnvVar("USER_ID");
    BoxClient userClient = getDefaultClientWithUserSubject(userId);
    assertThrows(
        RuntimeException.class,
        () ->
            userClient
                .getIntegrationMappings()
                .createTeamsIntegrationMapping(
                    new IntegrationMappingTeamsCreateRequest(
                        new IntegrationMappingPartnerItemTeamsCreateRequest(
                            IntegrationMappingPartnerItemTeamsCreateRequestTypeField.CHANNEL,
                            partnerItemId,
                            tenantId,
                            teamId),
                        new FolderReference(folder.getId()))));
    assertThrows(
        RuntimeException.class,
        () -> userClient.getIntegrationMappings().getTeamsIntegrationMapping());
    String integrationMappingId = "123456";
    assertThrows(
        RuntimeException.class,
        () ->
            userClient
                .getIntegrationMappings()
                .updateTeamsIntegrationMappingById(
                    integrationMappingId,
                    new UpdateTeamsIntegrationMappingByIdRequestBody.Builder()
                        .boxItem(new FolderReference("1234567"))
                        .build()));
    assertThrows(
        RuntimeException.class,
        () ->
            userClient
                .getIntegrationMappings()
                .deleteTeamsIntegrationMappingById(integrationMappingId));
    client.getFolders().deleteFolderById(folder.getId());
  }
}
