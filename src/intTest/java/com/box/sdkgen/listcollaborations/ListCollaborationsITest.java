package com.box.sdkgen.listcollaborations;

import static com.box.sdkgen.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.groups.CreateGroupRequestBody;
import com.box.sdkgen.managers.listcollaborations.GetCollaborationsQueryParams;
import com.box.sdkgen.managers.listcollaborations.GetCollaborationsQueryParamsStatusField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBody;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyAccessibleByField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyAccessibleByTypeField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyItemField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyItemTypeField;
import com.box.sdkgen.managers.usercollaborations.CreateCollaborationRequestBodyRoleField;
import com.box.sdkgen.schemas.collaboration.Collaboration;
import com.box.sdkgen.schemas.collaborations.Collaborations;
import com.box.sdkgen.schemas.collaborationsoffsetpaginated.CollaborationsOffsetPaginated;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.groupfull.GroupFull;
import org.junit.jupiter.api.Test;

public class ListCollaborationsITest {

  @Test
  public void testListCollaborations() {
    BoxClient client = getDefaultClient();
    FolderFull folder = createNewFolder();
    FileFull file = uploadNewFile();
    GroupFull group = client.getGroups().createGroup(new CreateGroupRequestBody(getUuid()));
    Collaboration groupCollaboration =
        client
            .getUserCollaborations()
            .createCollaboration(
                new CreateCollaborationRequestBody(
                    new CreateCollaborationRequestBodyItemField.Builder()
                        .type(CreateCollaborationRequestBodyItemTypeField.FOLDER)
                        .id(folder.getId())
                        .build(),
                    new CreateCollaborationRequestBodyAccessibleByField.Builder(
                            CreateCollaborationRequestBodyAccessibleByTypeField.GROUP)
                        .id(group.getId())
                        .build(),
                    CreateCollaborationRequestBodyRoleField.EDITOR));
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
                        .id(getEnvVar("USER_ID"))
                        .build(),
                    CreateCollaborationRequestBodyRoleField.EDITOR));
    assert convertToString(groupCollaboration.getRole()).equals("editor");
    assert convertToString(groupCollaboration.getType()).equals("collaboration");
    Collaborations fileCollaborations =
        client.getListCollaborations().getFileCollaborations(file.getId());
    assert fileCollaborations.getEntries().size() > 0;
    Collaborations folderCollaborations =
        client.getListCollaborations().getFolderCollaborations(folder.getId());
    assert folderCollaborations.getEntries().size() > 0;
    CollaborationsOffsetPaginated pendingCollaborations =
        client
            .getListCollaborations()
            .getCollaborations(
                new GetCollaborationsQueryParams(GetCollaborationsQueryParamsStatusField.PENDING));
    assert pendingCollaborations.getEntries().size() >= 0;
    CollaborationsOffsetPaginated groupCollaborations =
        client.getListCollaborations().getGroupCollaborations(group.getId());
    assert groupCollaborations.getEntries().size() > 0;
    client.getUserCollaborations().deleteCollaborationById(groupCollaboration.getId());
    client.getFiles().deleteFileById(file.getId());
    client.getFolders().deleteFolderById(folder.getId());
    client.getGroups().deleteGroupById(group.getId());
  }
}
