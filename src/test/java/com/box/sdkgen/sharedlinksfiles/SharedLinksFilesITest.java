package com.box.sdkgen.sharedlinksfiles;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.sharedlinksfiles.AddShareLinkToFileQueryParams;
import com.box.sdkgen.managers.sharedlinksfiles.AddShareLinkToFileRequestBody;
import com.box.sdkgen.managers.sharedlinksfiles.AddShareLinkToFileRequestBodySharedLinkAccessField;
import com.box.sdkgen.managers.sharedlinksfiles.AddShareLinkToFileRequestBodySharedLinkField;
import com.box.sdkgen.managers.sharedlinksfiles.FindFileForSharedLinkHeaders;
import com.box.sdkgen.managers.sharedlinksfiles.FindFileForSharedLinkQueryParams;
import com.box.sdkgen.managers.sharedlinksfiles.GetSharedLinkForFileQueryParams;
import com.box.sdkgen.managers.sharedlinksfiles.RemoveSharedLinkFromFileQueryParams;
import com.box.sdkgen.managers.sharedlinksfiles.RemoveSharedLinkFromFileRequestBody;
import com.box.sdkgen.managers.sharedlinksfiles.UpdateSharedLinkOnFileQueryParams;
import com.box.sdkgen.managers.sharedlinksfiles.UpdateSharedLinkOnFileRequestBody;
import com.box.sdkgen.managers.sharedlinksfiles.UpdateSharedLinkOnFileRequestBodySharedLinkAccessField;
import com.box.sdkgen.managers.sharedlinksfiles.UpdateSharedLinkOnFileRequestBodySharedLinkField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import org.junit.jupiter.api.Test;

public class SharedLinksFilesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testSharedLinksFiles() {
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(), new UploadFileRequestBodyAttributesParentField("0")),
                    generateByteStream(10)));
    String fileId = uploadedFiles.getEntries().get(0).getId();
    client
        .getSharedLinksFiles()
        .addShareLinkToFile(
            fileId,
            new AddShareLinkToFileRequestBody.Builder()
                .sharedLink(
                    new AddShareLinkToFileRequestBodySharedLinkField.Builder()
                        .access(AddShareLinkToFileRequestBodySharedLinkAccessField.OPEN)
                        .password("Secret123@")
                        .build())
                .build(),
            new AddShareLinkToFileQueryParams("shared_link"));
    FileFull fileFromApi =
        client
            .getSharedLinksFiles()
            .getSharedLinkForFile(fileId, new GetSharedLinkForFileQueryParams("shared_link"));
    assert convertToString(fileFromApi.getSharedLink().getAccess()).equals("open");
    String userId = getEnvVar("USER_ID");
    BoxClient userClient = getDefaultClientWithUserSubject(userId);
    FileFull fileFromSharedLinkPassword =
        userClient
            .getSharedLinksFiles()
            .findFileForSharedLink(
                new FindFileForSharedLinkQueryParams(),
                new FindFileForSharedLinkHeaders(
                    String.join(
                        "",
                        "shared_link=",
                        fileFromApi.getSharedLink().getUrl(),
                        "&shared_link_password=Secret123@")));
    assert fileId.equals(fileFromSharedLinkPassword.getId());
    assertThrows(
        RuntimeException.class,
        () ->
            userClient
                .getSharedLinksFiles()
                .findFileForSharedLink(
                    new FindFileForSharedLinkQueryParams(),
                    new FindFileForSharedLinkHeaders(
                        String.join(
                            "",
                            "shared_link=",
                            fileFromApi.getSharedLink().getUrl(),
                            "&shared_link_password=incorrectPassword"))));
    FileFull updatedFile =
        client
            .getSharedLinksFiles()
            .updateSharedLinkOnFile(
                fileId,
                new UpdateSharedLinkOnFileRequestBody.Builder()
                    .sharedLink(
                        new UpdateSharedLinkOnFileRequestBodySharedLinkField.Builder()
                            .access(
                                UpdateSharedLinkOnFileRequestBodySharedLinkAccessField
                                    .COLLABORATORS)
                            .build())
                    .build(),
                new UpdateSharedLinkOnFileQueryParams("shared_link"));
    assert convertToString(updatedFile.getSharedLink().getAccess()).equals("collaborators");
    client
        .getSharedLinksFiles()
        .removeSharedLinkFromFile(
            fileId,
            new RemoveSharedLinkFromFileRequestBody.Builder().sharedLink(null).build(),
            new RemoveSharedLinkFromFileQueryParams("shared_link"));
    FileFull fileFromApiAfterRemove =
        client
            .getSharedLinksFiles()
            .getSharedLinkForFile(fileId, new GetSharedLinkForFileQueryParams("shared_link"));
    assert fileFromApiAfterRemove.getSharedLink() == null;
    client.getFiles().deleteFileById(fileId);
  }
}
