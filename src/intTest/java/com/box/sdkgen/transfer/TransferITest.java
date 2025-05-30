package com.box.sdkgen.transfer;

import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;

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
    String newUserName = getUuid();
    UserFull newUser =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.CreateUserRequestBodyBuilder(newUserName)
                    .isPlatformAccessOnly(true)
                    .build());
    UserFull currentUser = client.getUsers().getUserMe();
    FolderFull transferedFolder =
        client
            .getTransfer()
            .transferOwnedFolder(
                newUser.getId(),
                new TransferOwnedFolderRequestBody(
                    new TransferOwnedFolderRequestBodyOwnedByField(currentUser.getId())),
                new TransferOwnedFolderQueryParams.TransferOwnedFolderQueryParamsBuilder()
                    .notify(false)
                    .build());
    assert transferedFolder.getOwnedBy().getId().equals(currentUser.getId());
    client
        .getFolders()
        .deleteFolderById(
            transferedFolder.getId(),
            new DeleteFolderByIdQueryParams.DeleteFolderByIdQueryParamsBuilder()
                .recursive(true)
                .build());
    client
        .getUsers()
        .deleteUserById(
            newUser.getId(),
            new DeleteUserByIdQueryParams.DeleteUserByIdQueryParamsBuilder()
                .notify(false)
                .force(true)
                .build());
  }
}
