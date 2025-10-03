package com.box.sdkgen.usercollaborations;

import static com.box.sdkgen.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBody;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyAccessibleByField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyAccessibleByTypeField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyItemField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyItemTypeField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyRoleField;
import com.box.sdkgen.managers.usercollaborations.UpdateCollaborationByIdRequestBody;
import com.box.sdkgen.managers.usercollaborations.UpdateCollaborationByIdRequestBodyRoleField;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.schemas.collaboration.Collaboration;
import com.box.sdkgen.schemas.collaborations.Collaborations;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.userfull.UserFull;
import org.junit.jupiter.api.Test;

public class UserCollaborationsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testUserCollaborations() {
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
    FolderFull folder = createNewFolder();
    Collaboration collaboration =
        client
            .getUserCollaborations()
            .createCollaboration(
                new CreateCollaborationRequestBody(
                    new CreateCollaborationRequestBodyItemField.Builder()
                        .type(CreateCollaborationRequestBodyItemTypeField.FOLDER)
                        .id(folder.getId())
                        .build(),
                    new CreateCollaborationRequestBodyAccessibleByField.Builder(
                            CreateCollaborationRequestBodyAccessibleByTypeField.USER)
                        .id(user.getId())
                        .build(),
                    CreateCollaborationRequestBodyRoleField.EDITOR));
    assert convertToString(collaboration.getRole()).equals("editor");
    String collaborationId = collaboration.getId();
    Collaboration collaborationFromApi =
        client.getUserCollaborations().getCollaborationById(collaborationId);
    assert collaborationId.equals(collaborationFromApi.getId());
    assert convertToString(collaborationFromApi.getStatus()).equals("accepted");
    assert convertToString(collaborationFromApi.getType()).equals("collaboration");
    assert collaborationFromApi.getInviteEmail() == null;
    Collaboration updatedCollaboration =
        client
            .getUserCollaborations()
            .updateCollaborationById(
                collaborationId,
                new UpdateCollaborationByIdRequestBody.Builder()
                    .role(UpdateCollaborationByIdRequestBodyRoleField.VIEWER)
                    .build());
    assert convertToString(updatedCollaboration.getRole()).equals("viewer");
    client.getUserCollaborations().deleteCollaborationById(collaborationId);
    assertThrows(
        RuntimeException.class,
        () -> client.getUserCollaborations().getCollaborationById(collaborationId));
    client.getFolders().deleteFolderById(folder.getId());
    client.getUsers().deleteUserById(user.getId());
  }

  @Test
  public void testConvertingUserCollaborationToOwnership() {
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
    FolderFull folder = createNewFolder();
    Collaboration collaboration =
        client
            .getUserCollaborations()
            .createCollaboration(
                new CreateCollaborationRequestBody(
                    new CreateCollaborationRequestBodyItemField.Builder()
                        .type(CreateCollaborationRequestBodyItemTypeField.FOLDER)
                        .id(folder.getId())
                        .build(),
                    new CreateCollaborationRequestBodyAccessibleByField.Builder(
                            CreateCollaborationRequestBodyAccessibleByTypeField.USER)
                        .id(user.getId())
                        .build(),
                    CreateCollaborationRequestBodyRoleField.EDITOR));
    assert convertToString(collaboration.getRole()).equals("editor");
    Collaboration ownerCollaboration =
        client
            .getUserCollaborations()
            .updateCollaborationById(
                collaboration.getId(),
                new UpdateCollaborationByIdRequestBody.Builder()
                    .role(UpdateCollaborationByIdRequestBodyRoleField.OWNER)
                    .build());
    assert ownerCollaboration == null;
    Collaborations folderCollaborations =
        client.getListCollaborations().getFolderCollaborations(folder.getId());
    Collaboration folderCollaboration = folderCollaborations.getEntries().get(0);
    client.getUserCollaborations().deleteCollaborationById(folderCollaboration.getId());
    BoxClient userClient = client.withAsUserHeader(user.getId());
    userClient.getFolders().deleteFolderById(folder.getId());
    userClient.getTrashedFolders().deleteTrashedFolderById(folder.getId());
    client.getUsers().deleteUserById(user.getId());
  }

  @Test
  public void testExternalUserCollaborations() {
    String userName = getUuid();
    String userLogin = String.join("", getUuid(), "@boxdemo.com");
    FolderFull folder = createNewFolder();
    Collaboration collaboration =
        client
            .getUserCollaborations()
            .createCollaboration(
                new CreateCollaborationRequestBody(
                    new CreateCollaborationRequestBodyItemField.Builder()
                        .type(CreateCollaborationRequestBodyItemTypeField.FOLDER)
                        .id(folder.getId())
                        .build(),
                    new CreateCollaborationRequestBodyAccessibleByField.Builder(
                            CreateCollaborationRequestBodyAccessibleByTypeField.USER)
                        .login(userLogin)
                        .build(),
                    CreateCollaborationRequestBodyRoleField.EDITOR));
    assert convertToString(collaboration.getRole()).equals("editor");
    String collaborationId = collaboration.getId();
    Collaboration collaborationFromApi =
        client.getUserCollaborations().getCollaborationById(collaborationId);
    assert collaborationId.equals(collaborationFromApi.getId());
    assert convertToString(collaborationFromApi.getStatus()).equals("pending");
    assert convertToString(collaborationFromApi.getType()).equals("collaboration");
    assert collaborationFromApi.getInviteEmail().equals(userLogin);
    Collaboration updatedCollaboration =
        client
            .getUserCollaborations()
            .updateCollaborationById(
                collaborationId,
                new UpdateCollaborationByIdRequestBody.Builder()
                    .role(UpdateCollaborationByIdRequestBodyRoleField.VIEWER)
                    .build());
    assert convertToString(updatedCollaboration.getRole()).equals("viewer");
    client.getUserCollaborations().deleteCollaborationById(collaborationId);
    assertThrows(
        RuntimeException.class,
        () -> client.getUserCollaborations().getCollaborationById(collaborationId));
    client.getFolders().deleteFolderById(folder.getId());
  }
}
