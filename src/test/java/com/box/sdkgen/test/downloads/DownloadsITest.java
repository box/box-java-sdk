package com.box.sdkgen.test.downloads;

import static com.box.sdkgen.internal.utils.UtilsManager.bufferEquals;
import static com.box.sdkgen.internal.utils.UtilsManager.closeFileOutputStream;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStreamFromBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.getFileOutputStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.readBufferFromFile;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.uploadNewFile;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.jupiter.api.Test;

public class DownloadsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testDownloadFile() {
    String newFileName = getUuid();
    byte[] fileBuffer = generateByteBuffer(1024 * 1024);
    InputStream fileContentStream = generateByteStreamFromBuffer(fileBuffer);
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        newFileName, new UploadFileRequestBodyAttributesParentField("0")),
                    fileContentStream));
    FileFull uploadedFile = uploadedFiles.getEntries().get(0);
    InputStream downloadedFileContent = client.getDownloads().downloadFile(uploadedFile.getId());
    assert bufferEquals(readByteStream(downloadedFileContent), fileBuffer);
    client.getFiles().deleteFileById(uploadedFile.getId());
  }

  @Test
  public void testGetDownloadUrl() {
    FileFull uploadedFile = uploadNewFile();
    String downloadUrl = client.getDownloads().getDownloadFileUrl(uploadedFile.getId());
    assert !(downloadUrl == null);
    assert downloadUrl.contains("https://");
    client.getFiles().deleteFileById(uploadedFile.getId());
  }

  @Test
  public void testDownloadFileToOutputStream() {
    String newFileName = getUuid();
    byte[] fileBuffer = generateByteBuffer(1024 * 1024);
    InputStream fileContentStream = generateByteStreamFromBuffer(fileBuffer);
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        newFileName, new UploadFileRequestBodyAttributesParentField("0")),
                    fileContentStream));
    FileFull uploadedFile = uploadedFiles.getEntries().get(0);
    OutputStream fileOutputStream = getFileOutputStream(newFileName);
    client.getDownloads().downloadFileToOutputStream(uploadedFile.getId(), fileOutputStream);
    closeFileOutputStream(fileOutputStream);
    byte[] downloadedFileContent = readBufferFromFile(newFileName);
    assert bufferEquals(downloadedFileContent, fileBuffer);
    client.getFiles().deleteFileById(uploadedFile.getId());
  }
}
