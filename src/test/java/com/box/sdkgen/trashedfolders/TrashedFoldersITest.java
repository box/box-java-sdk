package com.box.sdkgen.trashedfolders;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.trashfolder.TrashFolder;
import com.box.sdkgen.schemas.trashfolderrestored.TrashFolderRestored;
import org.junit.jupiter.api.Test;

public class TrashedFoldersITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testTrashedFolders() {
    FolderFull folder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    client.getFolders().deleteFolderById(folder.getId());
    TrashFolder fromTrash = client.getTrashedFolders().getTrashedFolderById(folder.getId());
    assert fromTrash.getId().equals(folder.getId());
    assert fromTrash.getName().equals(folder.getName());
    assertThrows(RuntimeException.class, () -> client.getFolders().getFolderById(folder.getId()));
    TrashFolderRestored restoredFolder =
        client.getTrashedFolders().restoreFolderFromTrash(folder.getId());
    FolderFull fromApi = client.getFolders().getFolderById(folder.getId());
    assert restoredFolder.getId().equals(fromApi.getId());
    assert restoredFolder.getName().equals(fromApi.getName());
    client.getFolders().deleteFolderById(folder.getId());
    client.getTrashedFolders().deleteTrashedFolderById(folder.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getTrashedFolders().getTrashedFolderById(folder.getId()));
  }
}
