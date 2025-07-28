package com.box.sdkgen.test.trashedfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.trashfile.TrashFile;
import com.box.sdkgen.schemas.trashfilerestored.TrashFileRestored;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

public class TrashedFilesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testTrashedFiles() {
    int fileSize = 1024 * 1024;
    String fileName = getUuid();
    InputStream fileByteStream = generateByteStream(fileSize);
    Files files =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        fileName, new UploadFileRequestBodyAttributesParentField("0")),
                    fileByteStream));
    FileFull file = files.getEntries().get(0);
    client.getFiles().deleteFileById(file.getId());
    TrashFile fromTrash = client.getTrashedFiles().getTrashedFileById(file.getId());
    assert fromTrash.getId().equals(file.getId());
    assert fromTrash.getName().equals(file.getName());
    FileFull fromApiAfterTrashed = client.getFiles().getFileById(file.getId());
    assert convertToString(fromApiAfterTrashed.getItemStatus()).equals("trashed");
    TrashFileRestored restoredFile = client.getTrashedFiles().restoreFileFromTrash(file.getId());
    FileFull fromApiAfterRestore = client.getFiles().getFileById(file.getId());
    assert restoredFile.getId().equals(fromApiAfterRestore.getId());
    assert restoredFile.getName().equals(fromApiAfterRestore.getName());
    assert convertToString(fromApiAfterRestore.getItemStatus()).equals("active");
    client.getFiles().deleteFileById(file.getId());
    client.getTrashedFiles().deleteTrashedFileById(file.getId());
    assertThrows(
        RuntimeException.class, () -> client.getTrashedFiles().getTrashedFileById(file.getId()));
  }
}
