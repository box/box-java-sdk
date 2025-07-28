package com.box.sdkgen.test.filewatermarks;

import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.filewatermarks.UpdateFileWatermarkRequestBody;
import com.box.sdkgen.managers.filewatermarks.UpdateFileWatermarkRequestBodyWatermarkField;
import com.box.sdkgen.managers.filewatermarks.UpdateFileWatermarkRequestBodyWatermarkImprintField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.watermark.Watermark;
import org.junit.jupiter.api.Test;

public class FileWatermarksITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateGetDeleteFileWatermark() {
    String fileName = String.join("", getUuid(), ".txt");
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        fileName, new UploadFileRequestBodyAttributesParentField("0")),
                    generateByteStream(10)));
    FileFull file = uploadedFiles.getEntries().get(0);
    Watermark createdWatermark =
        client
            .getFileWatermarks()
            .updateFileWatermark(
                file.getId(),
                new UpdateFileWatermarkRequestBody(
                    new UpdateFileWatermarkRequestBodyWatermarkField.Builder()
                        .imprint(UpdateFileWatermarkRequestBodyWatermarkImprintField.DEFAULT)
                        .build()));
    Watermark watermark = client.getFileWatermarks().getFileWatermark(file.getId());
    client.getFileWatermarks().deleteFileWatermark(file.getId());
    assertThrows(
        RuntimeException.class, () -> client.getFileWatermarks().getFileWatermark(file.getId()));
    client.getFiles().deleteFileById(file.getId());
  }
}
