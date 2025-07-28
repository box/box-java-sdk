package com.box.sdkgen.test.files;

import static com.box.sdkgen.internal.utils.UtilsManager.bufferEquals;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStreamFromBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.uploadNewFile;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.files.CopyFileRequestBody;
import com.box.sdkgen.managers.files.CopyFileRequestBodyParentField;
import com.box.sdkgen.managers.files.GetFileByIdHeaders;
import com.box.sdkgen.managers.files.GetFileByIdQueryParams;
import com.box.sdkgen.managers.files.GetFileThumbnailByIdExtension;
import com.box.sdkgen.managers.files.GetFileThumbnailUrlExtension;
import com.box.sdkgen.managers.files.UpdateFileByIdQueryParams;
import com.box.sdkgen.managers.files.UpdateFileByIdRequestBody;
import com.box.sdkgen.managers.files.UpdateFileByIdRequestBodyLockAccessField;
import com.box.sdkgen.managers.files.UpdateFileByIdRequestBodyLockField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.trashfile.TrashFile;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class FilesITest {

  private static final BoxClient client = getDefaultClient();

  public static FileFull uploadFile(String fileName, InputStream fileStream) {
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        fileName, new UploadFileRequestBodyAttributesParentField("0")),
                    fileStream));
    return uploadedFiles.getEntries().get(0);
  }

  @Test
  public void testGetFileThumbnailUrl() {
    String thumbnailFileName = getUuid();
    InputStream thumbnailContentStream = generateByteStream(1024 * 1024);
    FileFull thumbnailFile = uploadFile(thumbnailFileName, thumbnailContentStream);
    String downloadUrl =
        client
            .getFiles()
            .getFileThumbnailUrl(thumbnailFile.getId(), GetFileThumbnailUrlExtension.PNG);
    assert !(downloadUrl == null);
    assert downloadUrl.contains("https://");
    client.getFiles().deleteFileById(thumbnailFile.getId());
  }

  @Test
  public void testGetFileThumbnail() {
    String thumbnailFileName = getUuid();
    byte[] thumbnailBuffer = generateByteBuffer(1024 * 1024);
    InputStream thumbnailContentStream = generateByteStreamFromBuffer(thumbnailBuffer);
    FileFull thumbnailFile = uploadFile(thumbnailFileName, thumbnailContentStream);
    InputStream thumbnail =
        client
            .getFiles()
            .getFileThumbnailById(thumbnailFile.getId(), GetFileThumbnailByIdExtension.PNG);
    assert !(bufferEquals(readByteStream(thumbnail), thumbnailBuffer) == true);
    client.getFiles().deleteFileById(thumbnailFile.getId());
  }

  @Test
  public void testGetFileFullExtraFields() {
    String newFileName = getUuid();
    InputStream fileStream = generateByteStream(1024 * 1024);
    FileFull uploadedFile = uploadFile(newFileName, fileStream);
    FileFull file =
        client
            .getFiles()
            .getFileById(
                uploadedFile.getId(),
                new GetFileByIdQueryParams.Builder()
                    .fields(Arrays.asList("is_externally_owned", "has_collaborations"))
                    .build());
    assert file.getIsExternallyOwned() == false;
    assert file.getHasCollaborations() == false;
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testCreateGetAndDeleteFile() {
    String newFileName = getUuid();
    InputStream updatedContentStream = generateByteStream(1024 * 1024);
    FileFull uploadedFile = uploadFile(newFileName, updatedContentStream);
    FileFull file = client.getFiles().getFileById(uploadedFile.getId());
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getFiles()
                .getFileById(
                    uploadedFile.getId(),
                    new GetFileByIdQueryParams.Builder().fields(Arrays.asList("name")).build(),
                    new GetFileByIdHeaders.Builder()
                        .extraHeaders(mapOf(entryOf("if-none-match", file.getEtag())))
                        .build()));
    assert file.getName().equals(newFileName);
    client.getFiles().deleteFileById(uploadedFile.getId());
    TrashFile trashedFile = client.getTrashedFiles().getTrashedFileById(uploadedFile.getId());
    assert file.getId().equals(trashedFile.getId());
  }

  @Test
  public void testUpdateFile() {
    FileFull fileToUpdate = uploadNewFile();
    String updatedName = getUuid();
    FileFull updatedFile =
        client
            .getFiles()
            .updateFileById(
                fileToUpdate.getId(),
                new UpdateFileByIdRequestBody.Builder()
                    .name(updatedName)
                    .description("Updated description")
                    .build());
    assert updatedFile.getName().equals(updatedName);
    assert updatedFile.getDescription().equals("Updated description");
    client.getFiles().deleteFileById(updatedFile.getId());
  }

  @Test
  public void testFileLock() {
    FileFull file = uploadNewFile();
    FileFull fileWithLock =
        client
            .getFiles()
            .updateFileById(
                file.getId(),
                new UpdateFileByIdRequestBody.Builder()
                    .lock(
                        new UpdateFileByIdRequestBodyLockField.Builder()
                            .access(UpdateFileByIdRequestBodyLockAccessField.LOCK)
                            .build())
                    .build(),
                new UpdateFileByIdQueryParams.Builder().fields(Arrays.asList("lock")).build());
    assert !(fileWithLock.getLock() == null);
    FileFull fileWithoutLock =
        client
            .getFiles()
            .updateFileById(
                file.getId(),
                new UpdateFileByIdRequestBody.Builder().lock(null).build(),
                new UpdateFileByIdQueryParams.Builder().fields(Arrays.asList("lock")).build());
    assert fileWithoutLock.getLock() == null;
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testCopyFile() {
    FileFull fileOrigin = uploadNewFile();
    String copiedFileName = getUuid();
    FileFull copiedFile =
        client
            .getFiles()
            .copyFile(
                fileOrigin.getId(),
                new CopyFileRequestBody.Builder(new CopyFileRequestBodyParentField("0"))
                    .name(copiedFileName)
                    .build());
    assert copiedFile.getParent().getId().equals("0");
    assert copiedFile.getName().equals(copiedFileName);
    client.getFiles().deleteFileById(fileOrigin.getId());
    client.getFiles().deleteFileById(copiedFile.getId());
  }
}
