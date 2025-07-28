package com.box.sdkgen.test.appitemassociations;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.files.GetFileByIdQueryParams;
import com.box.sdkgen.managers.folders.GetFolderByIdQueryParams;
import com.box.sdkgen.schemas.appitemassociation.AppItemAssociation;
import com.box.sdkgen.schemas.appitemassociations.AppItemAssociations;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AppItemAssociationsITest {

  @Test
  public void testListFileAppItemAssocations() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String fileId = getEnvVar("APP_ITEM_ASSOCIATION_FILE_ID");
    AppItemAssociations fileAppItemAssociations =
        client.getAppItemAssociations().getFileAppItemAssociations(fileId);
    assert fileAppItemAssociations.getEntries().size() == 1;
    AppItemAssociation association = fileAppItemAssociations.getEntries().get(0);
    assert !(association.getId().equals(""));
    assert convertToString(association.getAppItem().getApplicationType()).equals("hubs");
    assert convertToString(association.getAppItem().getType()).equals("app_item");
    assert convertToString(association.getItem().getType()).equals("file");
    assert association.getItem().getId().equals(fileId);
    FileFull file =
        client
            .getFiles()
            .getFileById(
                fileId,
                new GetFileByIdQueryParams.Builder()
                    .fields(Arrays.asList("is_associated_with_app_item"))
                    .build());
    assert file.getIsAssociatedWithAppItem() == true;
  }

  @Test
  public void testListFolderAppItemAssocations() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String folderId = getEnvVar("APP_ITEM_ASSOCIATION_FOLDER_ID");
    AppItemAssociations folderAppItemAssociations =
        client.getAppItemAssociations().getFolderAppItemAssociations(folderId);
    assert folderAppItemAssociations.getEntries().size() == 1;
    AppItemAssociation association = folderAppItemAssociations.getEntries().get(0);
    assert !(association.getId().equals(""));
    assert convertToString(association.getAppItem().getApplicationType()).equals("hubs");
    assert convertToString(association.getAppItem().getType()).equals("app_item");
    assert convertToString(association.getItem().getType()).equals("folder");
    assert association.getItem().getId().equals(folderId);
    FolderFull folder =
        client
            .getFolders()
            .getFolderById(
                folderId,
                new GetFolderByIdQueryParams.Builder()
                    .fields(Arrays.asList("is_associated_with_app_item"))
                    .build());
    assert folder.getIsAssociatedWithAppItem() == true;
  }
}
