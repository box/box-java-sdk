package com.box.sdkgen.hubitems;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.hubitems.GetHubItemsV2025R0QueryParams;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.v2025r0.folderreferencev2025r0.FolderReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcreaterequestv2025r0.HubCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemoperationresultv2025r0.HubItemOperationResultV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemoperationv2025r0.HubItemOperationV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemoperationv2025r0.HubItemOperationV2025R0ActionField;
import com.box.sdkgen.schemas.v2025r0.hubitemsmanagerequestv2025r0.HubItemsManageRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemsmanageresponsev2025r0.HubItemsManageResponseV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemsv2025r0.HubItemsV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubv2025r0.HubV2025R0;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class HubItemsITest {

  private static final BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));

  @Test
  public void testCreateDeleteGetHubItems() {
    String hubTitle = getUuid();
    FolderFull folder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    HubV2025R0 createdHub =
        client.getHubs().createHubV2025R0(new HubCreateRequestV2025R0(hubTitle));
    HubItemsV2025R0 hubItemsBeforeAdd =
        client
            .getHubItems()
            .getHubItemsV2025R0(new GetHubItemsV2025R0QueryParams(createdHub.getId()));
    assert hubItemsBeforeAdd.getEntries().size() == 0;
    HubItemsManageResponseV2025R0 addedHubItems =
        client
            .getHubItems()
            .manageHubItemsV2025R0(
                createdHub.getId(),
                new HubItemsManageRequestV2025R0.Builder()
                    .operations(
                        Arrays.asList(
                            new HubItemOperationV2025R0(
                                HubItemOperationV2025R0ActionField.ADD,
                                new FolderReferenceV2025R0(folder.getId()))))
                    .build());
    HubItemOperationResultV2025R0 addedHubItem = addedHubItems.getOperations().get(0);
    assert convertToString(addedHubItem.getAction()).equals("add");
    assert addedHubItem.getStatus() == 200;
    assert addedHubItem.getItem().getId().equals(folder.getId());
    assert addedHubItem.getItem().getType().equals("folder");
    HubItemsV2025R0 hubItemsAfterAdd =
        client
            .getHubItems()
            .getHubItemsV2025R0(new GetHubItemsV2025R0QueryParams(createdHub.getId()));
    assert hubItemsAfterAdd.getEntries().size() == 1;
    HubItemsManageResponseV2025R0 removedHubItems =
        client
            .getHubItems()
            .manageHubItemsV2025R0(
                createdHub.getId(),
                new HubItemsManageRequestV2025R0.Builder()
                    .operations(
                        Arrays.asList(
                            new HubItemOperationV2025R0(
                                HubItemOperationV2025R0ActionField.REMOVE,
                                new FolderReferenceV2025R0(folder.getId()))))
                    .build());
    HubItemOperationResultV2025R0 removedHubItem = removedHubItems.getOperations().get(0);
    assert convertToString(removedHubItem.getAction()).equals("remove");
    assert removedHubItem.getStatus() == 200;
    assert removedHubItem.getItem().getId().equals(folder.getId());
    assert removedHubItem.getItem().getType().equals("folder");
    HubItemsV2025R0 hubItemsAfterRemove =
        client
            .getHubItems()
            .getHubItemsV2025R0(new GetHubItemsV2025R0QueryParams(createdHub.getId()));
    assert hubItemsAfterRemove.getEntries().size() == 0;
    client.getHubs().deleteHubByIdV2025R0(createdHub.getId());
    client.getFolders().deleteFolderById(folder.getId());
  }
}
