package com.box.sdkgen.auth;

import static com.box.sdkgen.internal.utils.UtilsManager.decodeBase64;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.box.ccgauth.BoxCCGAuth;
import com.box.sdkgen.box.ccgauth.CCGConfig;
import com.box.sdkgen.box.developertokenauth.BoxDeveloperTokenAuth;
import com.box.sdkgen.box.developertokenauth.DeveloperTokenConfig;
import com.box.sdkgen.box.jwtauth.BoxJWTAuth;
import com.box.sdkgen.box.jwtauth.JWTConfig;
import com.box.sdkgen.box.oauth.BoxOAuth;
import com.box.sdkgen.box.oauth.OAuthConfig;
import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.files.UpdateFileByIdRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.folders.UpdateFolderByIdRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.managers.users.GetUserMeQueryParams;
import com.box.sdkgen.schemas.accesstoken.AccessToken;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.userfull.UserFull;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AuthITest {

  public static AccessToken getAccessToken() {
    String userId = getEnvVar("USER_ID");
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    CCGConfig ccgConfig =
        new CCGConfig.Builder(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"))
            .enterpriseId(enterpriseId)
            .userId(userId)
            .build();
    BoxCCGAuth auth = new BoxCCGAuth(ccgConfig);
    BoxCCGAuth authUser = auth.withUserSubject(userId);
    return authUser.retrieveToken();
  }

  @Test
  public void testJwtAuth() {
    String userId = getEnvVar("USER_ID");
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    JWTConfig jwtConfig =
        JWTConfig.fromConfigJsonString(decodeBase64(getEnvVar("JWT_CONFIG_BASE_64")));
    BoxJWTAuth auth = new BoxJWTAuth(jwtConfig);
    BoxJWTAuth userAuth = auth.withUserSubject(userId);
    BoxClient userClient = new BoxClient(userAuth);
    UserFull currentUser = userClient.getUsers().getUserMe();
    assert currentUser.getId().equals(userId);
    BoxJWTAuth enterpriseAuth = auth.withEnterpriseSubject(enterpriseId);
    BoxClient enterpriseClient = new BoxClient(enterpriseAuth);
    UserFull newUser =
        enterpriseClient
            .getUsers()
            .getUserMe(
                new GetUserMeQueryParams.Builder().fields(Arrays.asList("enterprise")).build());
    assert !(newUser.getEnterprise() == null);
    assert newUser.getEnterprise().getId().equals(enterpriseId);
    assert !(newUser.getId().equals(userId));
  }

  @Test
  public void testJwtAuthDownscope() {
    JWTConfig jwtConfig =
        JWTConfig.fromConfigJsonString(decodeBase64(getEnvVar("JWT_CONFIG_BASE_64")));
    BoxJWTAuth auth = new BoxJWTAuth(jwtConfig);
    BoxClient parentClient = new BoxClient(auth);
    Files uploadedFiles =
        parentClient
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(), new UploadFileRequestBodyAttributesParentField("0")),
                    generateByteStream(1024 * 1024)));
    FileFull file = uploadedFiles.getEntries().get(0);
    String resourcePath = String.join("", "https://api.box.com/2.0/files/", file.getId());
    AccessToken downscopedToken =
        auth.downscopeToken(Arrays.asList("item_rename", "item_preview"), resourcePath, null, null);
    assert !(downscopedToken.getAccessToken() == null);
    BoxClient downscopedClient =
        new BoxClient(new BoxDeveloperTokenAuth(downscopedToken.getAccessToken()));
    downscopedClient
        .getFiles()
        .updateFileById(
            file.getId(), new UpdateFileByIdRequestBody.Builder().name(getUuid()).build());
    assertThrows(
        RuntimeException.class, () -> downscopedClient.getFiles().deleteFileById(file.getId()));
    parentClient.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testJwtDownscopeTokenSucceedsIfNoTokenAvailable() {
    JWTConfig jwtConfig =
        JWTConfig.fromConfigJsonString(decodeBase64(getEnvVar("JWT_CONFIG_BASE_64")));
    BoxJWTAuth auth = new BoxJWTAuth(jwtConfig);
    AccessToken downscopedToken =
        auth.downscopeToken(Arrays.asList("root_readonly"), null, null, null);
    assert !(downscopedToken.getAccessToken() == null);
    BoxClient downscopedClient =
        new BoxClient(new BoxDeveloperTokenAuth(downscopedToken.getAccessToken()));
    assertThrows(
        RuntimeException.class,
        () ->
            downscopedClient
                .getUploads()
                .uploadFile(
                    new UploadFileRequestBody(
                        new UploadFileRequestBodyAttributesField(
                            getUuid(), new UploadFileRequestBodyAttributesParentField("0")),
                        generateByteStream(1024 * 1024))));
  }

  @Test
  public void testJwtAuthRevoke() {
    JWTConfig jwtConfig =
        JWTConfig.fromConfigJsonString(decodeBase64(getEnvVar("JWT_CONFIG_BASE_64")));
    BoxJWTAuth auth = new BoxJWTAuth(jwtConfig);
    AccessToken tokenFromStorageBeforeRevoke = auth.retrieveToken();
    assert !(tokenFromStorageBeforeRevoke == null);
    auth.revokeToken();
    AccessToken tokenFromStorageAfterRevoke = auth.retrieveToken();
    assert !(tokenFromStorageBeforeRevoke
        .getAccessToken()
        .equals(tokenFromStorageAfterRevoke.getAccessToken()));
  }

  @Test
  public void testOauthAuthAuthorizeUrl() {
    OAuthConfig config = new OAuthConfig("OAUTH_CLIENT_ID", "OAUTH_CLIENT_SECRET");
    BoxOAuth auth = new BoxOAuth(config);
    String authUrl = auth.getAuthorizeUrl();
    assert authUrl.equals(
            "https://account.box.com/api/oauth2/authorize?client_id=OAUTH_CLIENT_ID&response_type=code")
        || authUrl.equals(
            "https://account.box.com/api/oauth2/authorize?response_type=code&client_id=OAUTH_CLIENT_ID");
  }

  @Test
  public void testOauthDownscopeTokenSucceedsIfNoTokenAvailable() {
    OAuthConfig config = new OAuthConfig(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"));
    BoxOAuth auth = new BoxOAuth(config);
    String resourcePath = String.join("", "https://api.box.com/2.0/files/12345");
    assertThrows(
        RuntimeException.class,
        () ->
            auth.downscopeToken(
                Arrays.asList("item_rename", "item_preview"), resourcePath, null, null));
  }

  @Test
  public void testCcgAuth() {
    String userId = getEnvVar("USER_ID");
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    CCGConfig ccgConfig =
        new CCGConfig.Builder(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"))
            .enterpriseId(enterpriseId)
            .userId(userId)
            .build();
    BoxCCGAuth auth = new BoxCCGAuth(ccgConfig);
    BoxCCGAuth userAuth = auth.withUserSubject(userId);
    BoxClient userClient = new BoxClient(userAuth);
    UserFull currentUser = userClient.getUsers().getUserMe();
    assert currentUser.getId().equals(userId);
    BoxCCGAuth enterpriseAuth = auth.withEnterpriseSubject(enterpriseId);
    BoxClient enterpriseClient = new BoxClient(enterpriseAuth);
    UserFull newUser =
        enterpriseClient
            .getUsers()
            .getUserMe(
                new GetUserMeQueryParams.Builder().fields(Arrays.asList("enterprise")).build());
    assert !(newUser.getEnterprise() == null);
    assert newUser.getEnterprise().getId().equals(enterpriseId);
    assert !(newUser.getId().equals(userId));
  }

  @Test
  public void testCcgAuthDownscope() {
    CCGConfig ccgConfig =
        new CCGConfig.Builder(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"))
            .userId(getEnvVar("USER_ID"))
            .build();
    BoxCCGAuth auth = new BoxCCGAuth(ccgConfig);
    BoxClient parentClient = new BoxClient(auth);
    FolderFull folder =
        parentClient
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    String resourcePath = String.join("", "https://api.box.com/2.0/folders/", folder.getId());
    AccessToken downscopedToken =
        auth.downscopeToken(Arrays.asList("item_rename", "item_preview"), resourcePath, null, null);
    assert !(downscopedToken.getAccessToken() == null);
    BoxClient downscopedClient =
        new BoxClient(new BoxDeveloperTokenAuth(downscopedToken.getAccessToken()));
    downscopedClient
        .getFolders()
        .updateFolderById(
            folder.getId(), new UpdateFolderByIdRequestBody.Builder().name(getUuid()).build());
    assertThrows(
        RuntimeException.class,
        () -> downscopedClient.getFolders().deleteFolderById(folder.getId()));
    parentClient.getFolders().deleteFolderById(folder.getId());
  }

  @Test
  public void testCcgDownscopeTokenSucceedsIfNoTokenAvailable() {
    CCGConfig ccgConfig =
        new CCGConfig.Builder(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"))
            .userId(getEnvVar("USER_ID"))
            .build();
    BoxCCGAuth auth = new BoxCCGAuth(ccgConfig);
    AccessToken downscopedToken =
        auth.downscopeToken(Arrays.asList("root_readonly"), null, null, null);
    assert !(downscopedToken.getAccessToken() == null);
    BoxClient downscopedClient =
        new BoxClient(new BoxDeveloperTokenAuth(downscopedToken.getAccessToken()));
    assertThrows(
        RuntimeException.class,
        () ->
            downscopedClient
                .getUploads()
                .uploadFile(
                    new UploadFileRequestBody(
                        new UploadFileRequestBodyAttributesField(
                            getUuid(), new UploadFileRequestBodyAttributesParentField("0")),
                        generateByteStream(1024 * 1024))));
  }

  @Test
  public void testCcgAuthRevoke() {
    CCGConfig ccgConfig =
        new CCGConfig.Builder(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"))
            .userId(getEnvVar("USER_ID"))
            .build();
    BoxCCGAuth auth = new BoxCCGAuth(ccgConfig);
    AccessToken tokenFromStorageBeforeRevoke = auth.retrieveToken();
    assert !(tokenFromStorageBeforeRevoke == null);
    auth.revokeToken();
    AccessToken tokenFromStorageAfterRevoke = auth.retrieveToken();
    assert !(tokenFromStorageBeforeRevoke
        .getAccessToken()
        .equals(tokenFromStorageAfterRevoke.getAccessToken()));
  }

  @Test
  public void testDeveloperDownscopeTokenSucceedsIfNoTokenAvailable() {
    DeveloperTokenConfig developerTokenConfig =
        new DeveloperTokenConfig.Builder()
            .clientId(getEnvVar("CLIENT_ID"))
            .clientSecret(getEnvVar("CLIENT_SECRET"))
            .build();
    BoxDeveloperTokenAuth auth =
        new BoxDeveloperTokenAuth.Builder("").config(developerTokenConfig).build();
    String resourcePath = String.join("", "https://api.box.com/2.0/folders/12345");
    assertThrows(
        RuntimeException.class,
        () ->
            auth.downscopeToken(
                Arrays.asList("item_rename", "item_preview"), resourcePath, null, null));
  }

  @Test
  public void testDeveloperTokenAuthRevoke() {
    DeveloperTokenConfig developerTokenConfig =
        new DeveloperTokenConfig.Builder()
            .clientId(getEnvVar("CLIENT_ID"))
            .clientSecret(getEnvVar("CLIENT_SECRET"))
            .build();
    AccessToken token = getAccessToken();
    BoxDeveloperTokenAuth auth =
        new BoxDeveloperTokenAuth.Builder(token.getAccessToken())
            .config(developerTokenConfig)
            .build();
    AccessToken tokenFromStorageBeforeRevoke = auth.retrieveToken();
    assert !(tokenFromStorageBeforeRevoke == null);
    auth.revokeToken();
    assertThrows(RuntimeException.class, () -> auth.retrieveToken());
  }

  @Test
  public void testDeveloperTokenAuthDownscope() {
    DeveloperTokenConfig developerTokenConfig =
        new DeveloperTokenConfig.Builder()
            .clientId(getEnvVar("CLIENT_ID"))
            .clientSecret(getEnvVar("CLIENT_SECRET"))
            .build();
    AccessToken token = getAccessToken();
    BoxDeveloperTokenAuth auth =
        new BoxDeveloperTokenAuth.Builder(token.getAccessToken())
            .config(developerTokenConfig)
            .build();
    BoxClient parentClient = new BoxClient(auth);
    FolderFull folder =
        parentClient
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    String resourcePath = String.join("", "https://api.box.com/2.0/folders/", folder.getId());
    AccessToken downscopedToken =
        auth.downscopeToken(Arrays.asList("item_rename", "item_preview"), resourcePath, null, null);
    assert !(downscopedToken.getAccessToken() == null);
    BoxClient downscopedClient =
        new BoxClient(new BoxDeveloperTokenAuth(downscopedToken.getAccessToken()));
    downscopedClient
        .getFolders()
        .updateFolderById(
            folder.getId(), new UpdateFolderByIdRequestBody.Builder().name(getUuid()).build());
    assertThrows(
        RuntimeException.class,
        () -> downscopedClient.getFolders().deleteFolderById(folder.getId()));
    parentClient.getFolders().deleteFolderById(folder.getId());
  }

  @Test
  public void testDeveloperTokenAuth() {
    String userId = getEnvVar("USER_ID");
    AccessToken token = getAccessToken();
    BoxDeveloperTokenAuth devAuth = new BoxDeveloperTokenAuth(token.getAccessToken());
    BoxClient client = new BoxClient(devAuth);
    UserFull currentUser = client.getUsers().getUserMe();
    assert currentUser.getId().equals(userId);
  }

  @Test
  public void testOauthAuthRevoke() {
    AccessToken token = getAccessToken();
    InMemoryTokenStorage tokenStorage = new InMemoryTokenStorage.Builder().token(token).build();
    OAuthConfig config =
        new OAuthConfig.Builder(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"))
            .tokenStorage(tokenStorage)
            .build();
    BoxOAuth auth = new BoxOAuth(config);
    BoxClient client = new BoxClient(auth);
    client.getUsers().getUserMe();
    auth.revokeToken();
    assertThrows(RuntimeException.class, () -> client.getUsers().getUserMe());
  }

  @Test
  public void testOauthAuthDownscope() {
    AccessToken token = getAccessToken();
    InMemoryTokenStorage tokenStorage = new InMemoryTokenStorage.Builder().token(token).build();
    OAuthConfig config =
        new OAuthConfig.Builder(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"))
            .tokenStorage(tokenStorage)
            .build();
    BoxOAuth auth = new BoxOAuth(config);
    BoxClient parentClient = new BoxClient(auth);
    Files uploadedFiles =
        parentClient
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(), new UploadFileRequestBodyAttributesParentField("0")),
                    generateByteStream(1024 * 1024)));
    FileFull file = uploadedFiles.getEntries().get(0);
    String resourcePath = String.join("", "https://api.box.com/2.0/files/", file.getId());
    AccessToken downscopedToken =
        auth.downscopeToken(Arrays.asList("item_rename", "item_preview"), resourcePath, null, null);
    assert !(downscopedToken.getAccessToken() == null);
    BoxClient downscopedClient =
        new BoxClient(new BoxDeveloperTokenAuth(downscopedToken.getAccessToken()));
    downscopedClient
        .getFiles()
        .updateFileById(
            file.getId(), new UpdateFileByIdRequestBody.Builder().name(getUuid()).build());
    assertThrows(
        RuntimeException.class, () -> downscopedClient.getFiles().deleteFileById(file.getId()));
    parentClient.getFiles().deleteFileById(file.getId());
  }
}
