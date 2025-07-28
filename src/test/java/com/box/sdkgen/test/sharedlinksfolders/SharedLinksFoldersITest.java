package com.box.sdkgen.test.sharedlinksfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.sharedlinksfolders.AddShareLinkToFolderQueryParams;
import com.box.sdkgen.managers.sharedlinksfolders.AddShareLinkToFolderRequestBody;
import com.box.sdkgen.managers.sharedlinksfolders.AddShareLinkToFolderRequestBodySharedLinkAccessField;
import com.box.sdkgen.managers.sharedlinksfolders.AddShareLinkToFolderRequestBodySharedLinkField;
import com.box.sdkgen.managers.sharedlinksfolders.FindFolderForSharedLinkHeaders;
import com.box.sdkgen.managers.sharedlinksfolders.FindFolderForSharedLinkQueryParams;
import com.box.sdkgen.managers.sharedlinksfolders.GetSharedLinkForFolderQueryParams;
import com.box.sdkgen.managers.sharedlinksfolders.RemoveSharedLinkFromFolderQueryParams;
import com.box.sdkgen.managers.sharedlinksfolders.RemoveSharedLinkFromFolderRequestBody;
import com.box.sdkgen.managers.sharedlinksfolders.UpdateSharedLinkOnFolderQueryParams;
import com.box.sdkgen.managers.sharedlinksfolders.UpdateSharedLinkOnFolderRequestBody;
import com.box.sdkgen.managers.sharedlinksfolders.UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField;
import com.box.sdkgen.managers.sharedlinksfolders.UpdateSharedLinkOnFolderRequestBodySharedLinkField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import org.junit.jupiter.api.Test;

public class SharedLinksFoldersITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testSharedLinksFolders() {
    FolderFull folder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    client
        .getSharedLinksFolders()
        .addShareLinkToFolder(
            folder.getId(),
            new AddShareLinkToFolderRequestBody.Builder()
                .sharedLink(
                    new AddShareLinkToFolderRequestBodySharedLinkField.Builder()
                        .access(AddShareLinkToFolderRequestBodySharedLinkAccessField.OPEN)
                        .password("Secret123@")
                        .build())
                .build(),
            new AddShareLinkToFolderQueryParams("shared_link"));
    FolderFull folderFromApi =
        client
            .getSharedLinksFolders()
            .getSharedLinkForFolder(
                folder.getId(), new GetSharedLinkForFolderQueryParams("shared_link"));
    assert convertToString(folderFromApi.getSharedLink().getAccess()).equals("open");
    String userId = getEnvVar("USER_ID");
    BoxClient userClient = getDefaultClientWithUserSubject(userId);
    FolderFull folderFromSharedLinkPassword =
        userClient
            .getSharedLinksFolders()
            .findFolderForSharedLink(
                new FindFolderForSharedLinkQueryParams(),
                new FindFolderForSharedLinkHeaders(
                    String.join(
                        "",
                        "shared_link=",
                        folderFromApi.getSharedLink().getUrl(),
                        "&shared_link_password=Secret123@")));
    assert folder.getId().equals(folderFromSharedLinkPassword.getId());
    assertThrows(
        RuntimeException.class,
        () ->
            userClient
                .getSharedLinksFolders()
                .findFolderForSharedLink(
                    new FindFolderForSharedLinkQueryParams(),
                    new FindFolderForSharedLinkHeaders(
                        String.join(
                            "",
                            "shared_link=",
                            folderFromApi.getSharedLink().getUrl(),
                            "&shared_link_password=incorrectPassword"))));
    FolderFull updatedFolder =
        client
            .getSharedLinksFolders()
            .updateSharedLinkOnFolder(
                folder.getId(),
                new UpdateSharedLinkOnFolderRequestBody.Builder()
                    .sharedLink(
                        new UpdateSharedLinkOnFolderRequestBodySharedLinkField.Builder()
                            .access(
                                UpdateSharedLinkOnFolderRequestBodySharedLinkAccessField
                                    .COLLABORATORS)
                            .build())
                    .build(),
                new UpdateSharedLinkOnFolderQueryParams("shared_link"));
    assert convertToString(updatedFolder.getSharedLink().getAccess()).equals("collaborators");
    client
        .getSharedLinksFolders()
        .removeSharedLinkFromFolder(
            folder.getId(),
            new RemoveSharedLinkFromFolderRequestBody.Builder().sharedLink(null).build(),
            new RemoveSharedLinkFromFolderQueryParams("shared_link"));
    FolderFull folderFromApiAfterRemove =
        client
            .getSharedLinksFolders()
            .getSharedLinkForFolder(
                folder.getId(), new GetSharedLinkForFolderQueryParams("shared_link"));
    assert folderFromApiAfterRemove.getSharedLink() == null;
    client.getFolders().deleteFolderById(folder.getId());
  }
}
