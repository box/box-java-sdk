package com.box.sdkgen.zipdownloads;

import static com.box.sdkgen.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;
import static com.box.sdkgen.internal.utils.UtilsManager.bufferEquals;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeToString;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.zipdownload.ZipDownload;
import com.box.sdkgen.schemas.zipdownloadrequest.ZipDownloadRequest;
import com.box.sdkgen.schemas.zipdownloadrequest.ZipDownloadRequestItemsField;
import com.box.sdkgen.schemas.zipdownloadrequest.ZipDownloadRequestItemsTypeField;
import com.box.sdkgen.schemas.zipdownloadstatus.ZipDownloadStatus;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ZipDownloadsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testZipDownload() {
    FileFull file1 = uploadNewFile();
    FileFull file2 = uploadNewFile();
    FolderFull folder1 = createNewFolder();
    InputStream zipStream =
        client
            .getZipDownloads()
            .downloadZip(
                new ZipDownloadRequest.Builder(
                        Arrays.asList(
                            new ZipDownloadRequestItemsField(
                                ZipDownloadRequestItemsTypeField.FILE, file1.getId()),
                            new ZipDownloadRequestItemsField(
                                ZipDownloadRequestItemsTypeField.FILE, file2.getId()),
                            new ZipDownloadRequestItemsField(
                                ZipDownloadRequestItemsTypeField.FOLDER, folder1.getId())))
                    .downloadFileName("zip")
                    .build());
    assert bufferEquals(readByteStream(zipStream), generateByteBuffer(10)) == false;
    client.getFiles().deleteFileById(file1.getId());
    client.getFiles().deleteFileById(file2.getId());
    client.getFolders().deleteFolderById(folder1.getId());
  }

  @Test
  public void testManualZipDownloadAndCheckStatus() {
    FileFull file1 = uploadNewFile();
    FileFull file2 = uploadNewFile();
    FolderFull folder1 = createNewFolder();
    ZipDownload zipDownload =
        client
            .getZipDownloads()
            .createZipDownload(
                new ZipDownloadRequest.Builder(
                        Arrays.asList(
                            new ZipDownloadRequestItemsField(
                                ZipDownloadRequestItemsTypeField.FILE, file1.getId()),
                            new ZipDownloadRequestItemsField(
                                ZipDownloadRequestItemsTypeField.FILE, file2.getId()),
                            new ZipDownloadRequestItemsField(
                                ZipDownloadRequestItemsTypeField.FOLDER, folder1.getId())))
                    .downloadFileName("zip")
                    .build());
    assert !(zipDownload.getDownloadUrl().equals(""));
    assert !(zipDownload.getStatusUrl().equals(""));
    assert !(dateTimeToString(zipDownload.getExpiresAt()).equals(""));
    InputStream zipStream =
        client.getZipDownloads().getZipDownloadContent(zipDownload.getDownloadUrl());
    assert bufferEquals(readByteStream(zipStream), generateByteBuffer(10)) == false;
    ZipDownloadStatus zipDownloadStatus =
        client.getZipDownloads().getZipDownloadStatus(zipDownload.getStatusUrl());
    assert zipDownloadStatus.getTotalFileCount() == 2;
    assert zipDownloadStatus.getDownloadedFileCount() == 2;
    assert zipDownloadStatus.getSkippedFileCount() == 0;
    assert zipDownloadStatus.getSkippedFolderCount() == 0;
    assert !(convertToString(zipDownloadStatus.getState()).equals("failed"));
    client.getFiles().deleteFileById(file1.getId());
    client.getFiles().deleteFileById(file2.getId());
    client.getFolders().deleteFolderById(folder1.getId());
  }
}
