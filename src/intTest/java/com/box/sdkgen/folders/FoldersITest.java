package com.box.sdkgen.folders;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CopyFolderRequestBody;
import com.box.sdkgen.managers.folders.CopyFolderRequestBodyParentField;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.folders.GetFolderByIdQueryParams;
import com.box.sdkgen.managers.folders.UpdateFolderByIdRequestBody;
import com.box.sdkgen.managers.folders.UpdateFolderByIdRequestBodyParentField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.items.Items;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class FoldersITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testGetFolderInfo() {
    FolderFull rootFolder = client.getFolders().getFolderById("0");
    assert rootFolder.getId().equals("0");
    assert rootFolder.getName().equals("All Files");
    assert convertToString(rootFolder.getType()).equals("folder");
  }

  @Test
  public void testGetFolderFullInfoWithExtraFields() {
    FolderFull rootFolder =
        client
            .getFolders()
            .getFolderById(
                "0",
                new GetFolderByIdQueryParams.Builder()
                    .fields(Arrays.asList("has_collaborations", "tags"))
                    .build());
    assert rootFolder.getId().equals("0");
    assert rootFolder.getHasCollaborations() == false;
    int tagsLength = rootFolder.getTags().size();
    assert tagsLength == 0;
  }

  @Test
  public void testCreateAndDeleteFolder() {
    String newFolderName = getUuid();
    FolderFull newFolder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    newFolderName, new CreateFolderRequestBodyParentField("0")));
    FolderFull createdFolder = client.getFolders().getFolderById(newFolder.getId());
    assert createdFolder.getName().equals(newFolderName);
    client.getFolders().deleteFolderById(newFolder.getId());
    assertThrows(
        RuntimeException.class, () -> client.getFolders().getFolderById(newFolder.getId()));
  }

  @Test
  public void testUpdateFolder() {
    String folderToUpdateName = getUuid();
    FolderFull folderToUpdate =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    folderToUpdateName, new CreateFolderRequestBodyParentField("0")));
    String updatedName = getUuid();
    FolderFull updatedFolder =
        client
            .getFolders()
            .updateFolderById(
                folderToUpdate.getId(),
                new UpdateFolderByIdRequestBody.Builder()
                    .name(updatedName)
                    .description("Updated description")
                    .build());
    assert updatedFolder.getName().equals(updatedName);
    assert updatedFolder.getDescription().equals("Updated description");
    client.getFolders().deleteFolderById(updatedFolder.getId());
  }

  @Test
  public void testCopyMoveFolderAndListFolderItems() {
    String folderOriginName = getUuid();
    FolderFull folderOrigin =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    folderOriginName, new CreateFolderRequestBodyParentField("0")));
    String copiedFolderName = getUuid();
    FolderFull copiedFolder =
        client
            .getFolders()
            .copyFolder(
                folderOrigin.getId(),
                new CopyFolderRequestBody.Builder(new CopyFolderRequestBodyParentField("0"))
                    .name(copiedFolderName)
                    .build());
    assert copiedFolder.getParent().getId().equals("0");
    String movedFolderName = getUuid();
    FolderFull movedFolder =
        client
            .getFolders()
            .updateFolderById(
                copiedFolder.getId(),
                new UpdateFolderByIdRequestBody.Builder()
                    .name(movedFolderName)
                    .parent(
                        new UpdateFolderByIdRequestBodyParentField.Builder()
                            .id(folderOrigin.getId())
                            .build())
                    .build());
    assert movedFolder.getParent().getId().equals(folderOrigin.getId());
    Items folderItems = client.getFolders().getFolderItems(folderOrigin.getId());
    assert folderItems.getEntries().get(0).getId().equals(movedFolder.getId());
    assert folderItems.getEntries().get(0).getName().equals(movedFolderName);
    client.getFolders().deleteFolderById(movedFolder.getId());
    client.getFolders().deleteFolderById(folderOrigin.getId());
  }
}
