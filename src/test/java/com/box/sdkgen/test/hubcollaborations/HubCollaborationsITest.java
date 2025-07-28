package com.box.sdkgen.test.hubcollaborations;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.hubcollaborations.GetHubCollaborationsV2025R0QueryParams;
import com.box.sdkgen.managers.hubs.GetHubsV2025R0QueryParams;
import com.box.sdkgen.managers.hubs.GetHubsV2025R0QueryParamsDirectionField;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.schemas.userfull.UserFull;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationcreaterequestv2025r0.HubCollaborationCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationcreaterequestv2025r0.HubCollaborationCreateRequestV2025R0AccessibleByField;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationcreaterequestv2025r0.HubCollaborationCreateRequestV2025R0HubField;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationsv2025r0.HubCollaborationsV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationupdaterequestv2025r0.HubCollaborationUpdateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationv2025r0.HubCollaborationV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubsv2025r0.HubsV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubv2025r0.HubV2025R0;
import org.junit.jupiter.api.Test;

public class HubCollaborationsITest {

  private static final BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));

  @Test
  public void testCrudHubCollaboration() {
    HubsV2025R0 hubs =
        client
            .getHubs()
            .getHubsV2025R0(
                new GetHubsV2025R0QueryParams.Builder()
                    .scope("all")
                    .sort("name")
                    .direction(GetHubsV2025R0QueryParamsDirectionField.ASC)
                    .build());
    HubV2025R0 hub = hubs.getEntries().get(0);
    String userName = getUuid();
    String userLogin = String.join("", getUuid(), "@gmail.com");
    UserFull user =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(userName)
                    .login(userLogin)
                    .isPlatformAccessOnly(true)
                    .build());
    HubCollaborationV2025R0 createdCollaboration =
        client
            .getHubCollaborations()
            .createHubCollaborationV2025R0(
                new HubCollaborationCreateRequestV2025R0(
                    new HubCollaborationCreateRequestV2025R0HubField(hub.getId()),
                    new HubCollaborationCreateRequestV2025R0AccessibleByField.Builder("user")
                        .id(user.getId())
                        .build(),
                    "viewer"));
    assert !(createdCollaboration.getId().equals(""));
    assert convertToString(createdCollaboration.getType()).equals("hub_collaboration");
    assert createdCollaboration.getHub().getId().equals(hub.getId());
    assert convertToString(createdCollaboration.getAccessibleBy().getType()).equals("user");
    assert createdCollaboration.getAccessibleBy().getId().equals(user.getId());
    assert createdCollaboration.getRole().equals("viewer");
    HubCollaborationV2025R0 updatedCollaboration =
        client
            .getHubCollaborations()
            .updateHubCollaborationByIdV2025R0(
                createdCollaboration.getId(),
                new HubCollaborationUpdateRequestV2025R0.Builder().role("editor").build());
    assert !(updatedCollaboration.getId().equals(""));
    assert convertToString(updatedCollaboration.getType()).equals("hub_collaboration");
    assert updatedCollaboration.getHub().getId().equals(hub.getId());
    assert convertToString(updatedCollaboration.getAccessibleBy().getType()).equals("user");
    assert updatedCollaboration.getAccessibleBy().getId().equals(user.getId());
    assert updatedCollaboration.getRole().equals("editor");
    HubCollaborationsV2025R0 collaborations =
        client
            .getHubCollaborations()
            .getHubCollaborationsV2025R0(new GetHubCollaborationsV2025R0QueryParams(hub.getId()));
    assert collaborations.getEntries().size() >= 1;
    HubCollaborationV2025R0 retrievedCollaboration =
        client.getHubCollaborations().getHubCollaborationByIdV2025R0(createdCollaboration.getId());
    assert retrievedCollaboration.getId().equals(createdCollaboration.getId());
    assert convertToString(retrievedCollaboration.getType()).equals("hub_collaboration");
    assert retrievedCollaboration.getHub().getId().equals(hub.getId());
    assert convertToString(retrievedCollaboration.getAccessibleBy().getType()).equals("user");
    assert retrievedCollaboration.getAccessibleBy().getId().equals(user.getId());
    assert retrievedCollaboration.getRole().equals("editor");
    client.getHubCollaborations().deleteHubCollaborationByIdV2025R0(createdCollaboration.getId());
    client.getUsers().deleteUserById(user.getId());
  }
}
