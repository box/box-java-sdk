package com.box.sdkgen.test.transfer;

import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.DeleteFolderByIdQueryParams;
import com.box.sdkgen.managers.transfer.TransferOwnedFolderQueryParams;
import com.box.sdkgen.managers.transfer.TransferOwnedFolderRequestBody;
import com.box.sdkgen.managers.transfer.TransferOwnedFolderRequestBodyOwnedByField;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.managers.users.DeleteUserByIdQueryParams;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.userfull.UserFull;
import org.junit.jupiter.api.Test;

public class TransferITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testTransferUserContent() {
    String sourceUserName = getUuid();
    UserFull sourceUser =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(sourceUserName)
                    .isPlatformAccessOnly(true)
                    .build());
    UserFull targetUser = client.getUsers().getUserMe();
    FolderFull transferredFolder =
        client
            .getTransfer()
            .transferOwnedFolder(
                sourceUser.getId(),
                new TransferOwnedFolderRequestBody(
                    new TransferOwnedFolderRequestBodyOwnedByField(targetUser.getId())),
                new TransferOwnedFolderQueryParams.Builder().notify(false).build());
    assert transferredFolder.getOwnedBy().getId().equals(targetUser.getId());
    client
        .getFolders()
        .deleteFolderById(
            transferredFolder.getId(),
            new DeleteFolderByIdQueryParams.Builder().recursive(true).build());
    client
        .getUsers()
        .deleteUserById(
            sourceUser.getId(),
            new DeleteUserByIdQueryParams.Builder().notify(false).force(true).build());
  }
}
