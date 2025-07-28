package com.box.sdkgen.test.client;

import static com.box.sdkgen.internal.utils.UtilsManager.bufferEquals;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStreamFromBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;
import static com.box.sdkgen.serialization.json.JsonManager.getSdValueByKey;
import static com.box.sdkgen.serialization.json.JsonManager.jsonToSerializedData;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.folders.DeleteFolderByIdQueryParams;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.networking.baseurls.BaseUrls;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.MultipartItem;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.userfull.UserFull;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ClientITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testMakeRequestJsonCrud() {
    String newFolderName = getUuid();
    String requestBodyPost =
        String.join("", "{\"name\": \"", newFolderName, "\", \"parent\": { \"id\": \"0\"}}");
    FetchResponse createFolderResponse =
        client.makeRequest(
            new FetchOptions.Builder("https://api.box.com/2.0/folders", "post")
                .data(jsonToSerializedData(requestBodyPost))
                .build());
    assert createFolderResponse.getStatus() == 201;
    JsonNode createdFolder = createFolderResponse.getData();
    assert getSdValueByKey(createdFolder, "name").equals(newFolderName);
    String updatedName = getUuid();
    String requestBodyPut = String.join("", "{\"name\": \"", updatedName, "\"}");
    FetchResponse updateFolderResponse =
        client.makeRequest(
            new FetchOptions.Builder(
                    String.join(
                        "",
                        "https://api.box.com/2.0/folders/",
                        getSdValueByKey(createdFolder, "id")),
                    "put")
                .data(jsonToSerializedData(requestBodyPut))
                .build());
    assert updateFolderResponse.getStatus() == 200;
    JsonNode updatedFolder = updateFolderResponse.getData();
    assert getSdValueByKey(updatedFolder, "name").equals(updatedName);
    assert getSdValueByKey(updatedFolder, "id").equals(getSdValueByKey(createdFolder, "id"));
    FetchResponse getFolderResponse =
        client.makeRequest(
            new FetchOptions(
                String.join(
                    "", "https://api.box.com/2.0/folders/", getSdValueByKey(createdFolder, "id")),
                "GET"));
    assert getFolderResponse.getStatus() == 200;
    JsonNode receivedFolder = getFolderResponse.getData();
    assert getSdValueByKey(receivedFolder, "name").equals(updatedName);
    assert getSdValueByKey(receivedFolder, "id").equals(getSdValueByKey(updatedFolder, "id"));
    FetchResponse deleteFolderResponse =
        client.makeRequest(
            new FetchOptions(
                String.join(
                    "", "https://api.box.com/2.0/folders/", getSdValueByKey(receivedFolder, "id")),
                "DELETE"));
    assert deleteFolderResponse.getStatus() == 204;
  }

  @Test
  public void testMakeRequestMultipart() {
    String newFolderName = getUuid();
    FolderFull newFolder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    newFolderName, new CreateFolderRequestBodyParentField("0")));
    String newFolderId = newFolder.getId();
    String newFileName = String.join("", getUuid(), ".pdf");
    InputStream fileContentStream = generateByteStream(1024 * 1024);
    String multipartAttributes =
        String.join(
            "", "{\"name\": \"", newFileName, "\", \"parent\": { \"id\":", newFolderId, "}}");
    FetchResponse uploadFileResponse =
        client.makeRequest(
            new FetchOptions.Builder("https://upload.box.com/api/2.0/files/content", "POST")
                .multipartData(
                    Arrays.asList(
                        new MultipartItem.Builder("attributes")
                            .data(jsonToSerializedData(multipartAttributes))
                            .build(),
                        new MultipartItem.Builder("file").fileStream(fileContentStream).build()))
                .contentType("multipart/form-data")
                .build());
    assert uploadFileResponse.getStatus() == 201;
    client
        .getFolders()
        .deleteFolderById(
            newFolderId, new DeleteFolderByIdQueryParams.Builder().recursive(true).build());
  }

  @Test
  public void testMakeRequestBinaryFormat() {
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
    FetchResponse downloadFileResponse =
        client.makeRequest(
            new FetchOptions.Builder(
                    String.join(
                        "", "https://api.box.com/2.0/files/", uploadedFile.getId(), "/content"),
                    "GET")
                .responseFormat(ResponseFormat.BINARY)
                .build());
    assert downloadFileResponse.getStatus() == 200;
    assert bufferEquals(readByteStream(downloadFileResponse.getContent()), fileBuffer);
    client.getFiles().deleteFileById(uploadedFile.getId());
  }

  @Test
  public void testWithAsUserHeader() {
    String userName = getUuid();
    UserFull createdUser =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(userName).isPlatformAccessOnly(true).build());
    BoxClient asUserClient = client.withAsUserHeader(createdUser.getId());
    UserFull adminUser = client.getUsers().getUserMe();
    assert !(convertToString(adminUser.getName()).equals(userName));
    UserFull appUser = asUserClient.getUsers().getUserMe();
    assert convertToString(appUser.getName()).equals(userName);
    client.getUsers().deleteUserById(createdUser.getId());
  }

  @Test
  public void testWithSuppressedNotifications() {
    BoxClient newClient = client.withSuppressedNotifications();
    UserFull user = newClient.getUsers().getUserMe();
    assert !(user.getId().equals(""));
  }

  @Test
  public void testWithExtraHeaders() {
    String userName = getUuid();
    UserFull createdUser =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(userName).isPlatformAccessOnly(true).build());
    BoxClient asUserClient =
        client.withExtraHeaders(mapOf(entryOf("As-User", createdUser.getId())));
    UserFull adminUser = client.getUsers().getUserMe();
    assert !(convertToString(adminUser.getName()).equals(userName));
    UserFull appUser = asUserClient.getUsers().getUserMe();
    assert convertToString(appUser.getName()).equals(userName);
    client.getUsers().deleteUserById(createdUser.getId());
  }

  @Test
  public void testWithCustomBaseUrls() {
    BaseUrls newBaseUrls =
        new BaseUrls.Builder()
            .baseUrl("https://box.com/")
            .uploadUrl("https://box.com/")
            .oauth2Url("https://box.com/")
            .build();
    BoxClient customBaseClient = client.withCustomBaseUrls(newBaseUrls);
    assertThrows(RuntimeException.class, () -> customBaseClient.getUsers().getUserMe());
  }

  @Test
  public void testWithInterceptors() {
    UserFull user = client.getUsers().getUserMe();
    assert user.getRole() == null;
    BoxClient clientWithInterceptor =
        client.withInterceptors(Arrays.asList(new InterceptorAddingRoleToFields()));
    UserFull newUser = clientWithInterceptor.getUsers().getUserMe();
    assert !(newUser.getRole() == null);
    BoxClient clientWithTwoInterceptors =
        clientWithInterceptor.withInterceptors(Arrays.asList(new InterceptorChangingResponse()));
    UserFull superNewUser = clientWithTwoInterceptors.getUsers().getUserMe();
    assert superNewUser.getId().equals("123");
  }

  @Test
  public void testWithFailingInterceptors() {
    UserFull user = client.getUsers().getUserMe();
    assert !(user.getId() == null);
    BoxClient clientWithInterceptor =
        client.withInterceptors(Arrays.asList(new InterceptorThrowingError()));
    assertThrows(RuntimeException.class, () -> clientWithInterceptor.getUsers().getUserMe());
  }
}
