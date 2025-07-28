package com.box.sdkgen.test.folderwatermarks;

import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.folderwatermarks.UpdateFolderWatermarkRequestBody;
import com.box.sdkgen.managers.folderwatermarks.UpdateFolderWatermarkRequestBodyWatermarkField;
import com.box.sdkgen.managers.folderwatermarks.UpdateFolderWatermarkRequestBodyWatermarkImprintField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.watermark.Watermark;
import org.junit.jupiter.api.Test;

public class FolderWatermarksITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateGetDeleteFolderWatermark() {
    String folderName = getUuid();
    FolderFull folder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    folderName, new CreateFolderRequestBodyParentField("0")));
    Watermark createdWatermark =
        client
            .getFolderWatermarks()
            .updateFolderWatermark(
                folder.getId(),
                new UpdateFolderWatermarkRequestBody(
                    new UpdateFolderWatermarkRequestBodyWatermarkField.Builder()
                        .imprint(UpdateFolderWatermarkRequestBodyWatermarkImprintField.DEFAULT)
                        .build()));
    Watermark watermark = client.getFolderWatermarks().getFolderWatermark(folder.getId());
    client.getFolderWatermarks().deleteFolderWatermark(folder.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getFolderWatermarks().getFolderWatermark(folder.getId()));
    client.getFolders().deleteFolderById(folder.getId());
  }
}
