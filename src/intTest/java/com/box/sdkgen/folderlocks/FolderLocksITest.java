package com.box.sdkgen.folderlocks;

import static com.box.sdkgen.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folderlocks.CreateFolderLockRequestBody;
import com.box.sdkgen.managers.folderlocks.CreateFolderLockRequestBodyFolderField;
import com.box.sdkgen.managers.folderlocks.CreateFolderLockRequestBodyLockedOperationsField;
import com.box.sdkgen.managers.folderlocks.GetFolderLocksQueryParams;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.folderlock.FolderLock;
import com.box.sdkgen.schemas.folderlocks.FolderLocks;
import org.junit.jupiter.api.Test;

public class FolderLocksITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testFolderLocks() {
    FolderFull folder = createNewFolder();
    FolderLocks folderLocks =
        client.getFolderLocks().getFolderLocks(new GetFolderLocksQueryParams(folder.getId()));
    assert folderLocks.getEntries().size() == 0;
    FolderLock folderLock =
        client
            .getFolderLocks()
            .createFolderLock(
                new CreateFolderLockRequestBody.Builder(
                        new CreateFolderLockRequestBodyFolderField("folder", folder.getId()))
                    .lockedOperations(
                        new CreateFolderLockRequestBodyLockedOperationsField(true, true))
                    .build());
    assert folderLock.getFolder().getId().equals(folder.getId());
    assert folderLock.getLockedOperations().getMove() == true;
    assert folderLock.getLockedOperations().getDelete() == true;
    client.getFolderLocks().deleteFolderLockById(folderLock.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getFolderLocks().deleteFolderLockById(folderLock.getId()));
    FolderLocks newFolderLocks =
        client.getFolderLocks().getFolderLocks(new GetFolderLocksQueryParams(folder.getId()));
    assert newFolderLocks.getEntries().size() == 0;
    client.getFolders().deleteFolderById(folder.getId());
  }
}
