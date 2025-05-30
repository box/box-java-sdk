package com.box.sdkgen.fileversions;

import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.fileversions.PromoteFileVersionRequestBody;
import com.box.sdkgen.managers.fileversions.PromoteFileVersionRequestBodyTypeField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.managers.uploads.UploadFileVersionRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileVersionRequestBodyAttributesField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.fileversionfull.FileVersionFull;
import com.box.sdkgen.schemas.fileversions.FileVersions;
import org.junit.jupiter.api.Test;

public class FileVersionsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateListGetPromoteFileVersion() {
    String oldName = getUuid();
    String newName = getUuid();
    Files files =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        oldName, new UploadFileRequestBodyAttributesParentField("0")),
                    generateByteStream(10)));
    FileFull file = files.getEntries().get(0);
    assert file.getName().equals(oldName);
    assert file.getSize() == 10;
    Files newFiles =
        client
            .getUploads()
            .uploadFileVersion(
                file.getId(),
                new UploadFileVersionRequestBody(
                    new UploadFileVersionRequestBodyAttributesField(newName),
                    generateByteStream(20)));
    FileFull newFile = newFiles.getEntries().get(0);
    assert newFile.getName().equals(newName);
    assert newFile.getSize() == 20;
    FileVersions fileVersions = client.getFileVersions().getFileVersions(file.getId());
    assert fileVersions.getTotalCount() == 1;
    FileVersionFull fileVersion =
        client
            .getFileVersions()
            .getFileVersionById(file.getId(), fileVersions.getEntries().get(0).getId());
    assert fileVersion.getId().equals(fileVersions.getEntries().get(0).getId());
    client
        .getFileVersions()
        .promoteFileVersion(
            file.getId(),
            new PromoteFileVersionRequestBody.PromoteFileVersionRequestBodyBuilder()
                .id(fileVersions.getEntries().get(0).getId())
                .type(PromoteFileVersionRequestBodyTypeField.FILE_VERSION)
                .build());
    FileFull fileWithPromotedVersion = client.getFiles().getFileById(file.getId());
    assert fileWithPromotedVersion.getName().equals(oldName);
    assert fileWithPromotedVersion.getSize() == 10;
    client.getFileVersions().deleteFileVersionById(file.getId(), fileVersion.getId());
    client.getFiles().deleteFileById(file.getId());
  }
}
