package com.box.sdkgen.filerequests;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.schemas.filerequest.FileRequest;
import com.box.sdkgen.schemas.filerequestcopyrequest.FileRequestCopyRequest;
import com.box.sdkgen.schemas.filerequestcopyrequest.FileRequestCopyRequestFolderField;
import com.box.sdkgen.schemas.filerequestcopyrequest.FileRequestCopyRequestFolderTypeField;
import com.box.sdkgen.schemas.filerequestupdaterequest.FileRequestUpdateRequest;
import org.junit.jupiter.api.Test;

public class FileRequestsITest {

  @Test
  public void testGetCopyUpdateDeleteFileRequest() {
    String fileRequestId = getEnvVar("BOX_FILE_REQUEST_ID");
    String userId = getEnvVar("USER_ID");
    BoxClient client = getDefaultClientWithUserSubject(userId);
    FileRequest fileRequest = client.getFileRequests().getFileRequestById(fileRequestId);
    assert fileRequest.getId().equals(fileRequestId);
    assert convertToString(fileRequest.getType()).equals("file_request");
    FileRequest copiedFileRequest =
        client
            .getFileRequests()
            .createFileRequestCopy(
                fileRequestId,
                new FileRequestCopyRequest(
                    new FileRequestCopyRequestFolderField.Builder(fileRequest.getFolder().getId())
                        .type(FileRequestCopyRequestFolderTypeField.FOLDER)
                        .build()));
    assert !(copiedFileRequest.getId().equals(fileRequestId));
    assert copiedFileRequest.getTitle().equals(fileRequest.getTitle());
    assert copiedFileRequest.getDescription().equals(fileRequest.getDescription());
    FileRequest updatedFileRequest =
        client
            .getFileRequests()
            .updateFileRequestById(
                copiedFileRequest.getId(),
                new FileRequestUpdateRequest.Builder()
                    .title("updated title")
                    .description("updated description")
                    .build());
    assert updatedFileRequest.getId().equals(copiedFileRequest.getId());
    assert updatedFileRequest.getTitle().equals("updated title");
    assert updatedFileRequest.getDescription().equals("updated description");
    client.getFileRequests().deleteFileRequestById(updatedFileRequest.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getFileRequests().getFileRequestById(updatedFileRequest.getId()));
  }
}
