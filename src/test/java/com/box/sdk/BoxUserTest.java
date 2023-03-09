package com.box.sdk;

import static com.box.sdk.BinaryBodyUtils.writeStreamTo;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.MultipartValuePatternBuilder;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxUser} related tests.
 */

public class BoxUserTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testGetAvatar() throws IOException {
        final String expectedURL = "/2.0/users/12345/avatar";
        File file = new File("src/test/Fixtures/BoxUser/small_avatar.png");
        byte[] fileByteArray = Files.readAllBytes(file.toPath());

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(expectedURL))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "image/png")
                .withBody(fileByteArray)));

        BoxUser user = new BoxUser(this.api, "12345");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream avatarStream = user.getAvatar();
        writeStreamTo(avatarStream, output);
        assertArrayEquals(fileByteArray, output.toByteArray());
    }

    @Test
    public void downloadAvatar() throws IOException {
        final String expectedURL = "/2.0/users/12345/avatar";
        File file = new File("src/test/Fixtures/BoxUser/small_avatar.png");
        byte[] fileByteArray = Files.readAllBytes(file.toPath());

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(expectedURL))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "image/png")
                .withBody(fileByteArray)));

        BoxUser user = new BoxUser(this.api, "12345");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        user.downloadAvatar(output);
        assertArrayEquals(fileByteArray, output.toByteArray());
    }

    @Test
    public void testGetCurrentUserInfoSucceeds() {
        final String userURL = "/2.0/users/me";
        final String userInfoURL = "/2.0/users/12345";
        final String userName = "Test User";
        final String userLogin = "test@user.com";
        final String userphoneNumber = "1111111111";

        String result = TestUtils.getFixture("BoxUser/GetCurrentUserInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(userInfoURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser user = BoxUser.getCurrentUser(this.api);
        BoxUser.Info info = user.getInfo();

        assertEquals(userName, info.getName());
        assertEquals(userLogin, info.getLogin());
        assertEquals(userphoneNumber, info.getPhone());
    }

    @Test
    public void testGetUserInfoByIDSucceeds() {
        final String userID = "12345";
        final String userURL = "/2.0/users/" + userID;
        final String userName = "Test User";
        final String userLogin = "test@user.com";
        final String userPhoneNumber = "1111111111";
        final String notificationEmail = "notifications@example.com";

        String result = TestUtils.getFixture("BoxUser/GetCurrentUserInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser user = new BoxUser(this.api, userID);
        BoxUser.Info userInfo = user.getInfo();

        assertEquals(userID, userInfo.getID());
        assertEquals(userName, userInfo.getName());
        assertEquals(userLogin, userInfo.getLogin());
        assertEquals(userPhoneNumber, userInfo.getPhone());
        assertEquals(notificationEmail, userInfo.getNotificationEmail().getEmail());
        assertTrue(userInfo.getNotificationEmail().getIsConfirmed());
    }

    @Test
    public void testGetUserInfoWithNoNotificationEmailByIDSucceeds() {
        final String userID = "12345";
        final String userURL = "/2.0/users/" + userID;
        final String userName = "Test User";
        final String userLogin = "test@user.com";
        final String userPhoneNumber = "1111111111";

        String result = TestUtils.getFixture("BoxUser/GetCurrentUserInfoWithNoNotifcationEmail200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser user = new BoxUser(this.api, userID);
        BoxUser.Info userInfo = user.getInfo();

        assertEquals(userID, userInfo.getID());
        assertEquals(userName, userInfo.getName());
        assertEquals(userLogin, userInfo.getLogin());
        assertEquals(userPhoneNumber, userInfo.getPhone());
        assertNull(userInfo.getNotificationEmail());
    }

    @Test
    public void testCreateAppUserSucceeds() {
        final String userURL = "/2.0/users";
        final String userID = "12345";
        final String userName = "Java SDK App User";
        final String userLogin = "testuser@boxdevedition.com";

        String result = TestUtils.getFixture("BoxUser/CreateAppUser201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser.Info createdUserInfo = BoxUser.createAppUser(this.api, userName);

        assertEquals(userID, createdUserInfo.getID());
        assertEquals(userName, createdUserInfo.getName());
        assertEquals(userLogin, createdUserInfo.getLogin());
    }

    @Test
    public void testCreateAppUserSucceedsWithFields() {
        final String userURL = "/2.0/users";
        final String userID = "12345";
        final String userName = "Java SDK App User";
        final String userLogin = "testuser@boxdevedition.com";

        String result = TestUtils.getFixture("BoxUser/CreateAppUser201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(userURL))
            .withQueryParam("fields", WireMock.equalTo("name,login"))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser.Info createdUserInfo = BoxUser.createAppUser(this.api, userName, "name", "login");

        assertEquals(userID, createdUserInfo.getID());
        assertEquals(userName, createdUserInfo.getName());
        assertEquals(userLogin, createdUserInfo.getLogin());
    }

    @Test
    public void testCreateManagedUserSucceeds() {
        final String userURL = "/2.0/users";
        final String userID = "12345";
        final String userName = "Test Managed User";
        final String userLogin = "test@user.com";
        final String userTimeZone = "America/Los_Angeles";

        String result = TestUtils.getFixture("BoxUser/CreateManagedUser201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(this.api, userLogin, userName);

        assertEquals(userID, createdUserInfo.getID());
        assertEquals(userName, createdUserInfo.getName());
        assertEquals(userLogin, createdUserInfo.getLogin());
        assertEquals(userTimeZone, createdUserInfo.getTimezone());
    }

    @Test
    public void testCreateManagedUserSucceedsWithFields() {
        final String userURL = "/2.0/users";
        final String userID = "12345";
        final String userName = "Test Managed User";
        final String userLogin = "test@user.com";
        final String userTimeZone = "America/Los_Angeles";

        String result = TestUtils.getFixture("BoxUser/CreateManagedUser201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(userURL))
                .withQueryParam("fields", WireMock.equalTo("name,login,timezone"))
                .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(
                this.api, userLogin, userName, "name", "login", "timezone"
        );

        assertEquals(userID, createdUserInfo.getID());
        assertEquals(userName, createdUserInfo.getName());
        assertEquals(userLogin, createdUserInfo.getLogin());
        assertEquals(userTimeZone, createdUserInfo.getTimezone());
    }

    @Test
    public void testCreateManagedUserDoesNotIncludeNotdefinedOptionalFields() {
        final String userURL = "/2.0/users";
        final String userName = "Test Managed User";
        final String userLogin = "test@user.com";
        final boolean isSyncEnabled = false;

        String result = TestUtils.getFixture("BoxUser/CreateManagedUser201");

        JsonObject createBody = new JsonObject()
                .add("name", userName)
                .add("login", userLogin)
                .add("is_sync_enabled", isSyncEnabled);

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(userURL))
            .withRequestBody(WireMock.equalToJson(createBody.toString()))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        CreateUserParams optionalParams = new CreateUserParams();
        optionalParams.setIsSyncEnabled(isSyncEnabled);
        BoxUser.createEnterpriseUser(this.api, userLogin, userName, optionalParams);
    }

    @Test
    public void testUpdateUserInfoSucceedsAndSendsCorrectJson() {
        final String userID = "12345";
        final String userURL = "/2.0/users/" + userID;
        final String userName = "New User Name";
        final String userLogin = "new@test.com";
        final String userJob = "Example Job";
        final String userPhone = "650-123-4567";

        JsonObject userObject = new JsonObject()
            .add("name", userName)
            .add("login", userLogin)
            .add("job_title", userJob)
            .add("phone", userPhone);

        String result = TestUtils.getFixture("BoxUser/UpdateUserInfo200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(userURL))
            .withRequestBody(WireMock.equalToJson(userObject.toString()))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser user = new BoxUser(this.api, userID);
        BoxUser.Info info = user.new Info();
        info.setName(userName);
        info.setLogin(userLogin);
        info.setJobTitle(userJob);
        info.setPhone(userPhone);
        user.updateInfo(info);

        assertEquals(userID, info.getID());
        assertEquals(userName, info.getName());
        assertEquals(userLogin, info.getLogin());
        assertEquals(userJob, info.getJobTitle());
        assertEquals(userPhone, info.getPhone());
    }

    @Test
    public void testUpdateUserInfoSucceedsWithFields() {
        final String userID = "12345";
        final String userURL = "/2.0/users/" + userID;
        final String userName = "New User Name";
        final String userLogin = "new@test.com";
        final String userJob = "Example Job";
        final String userPhone = "650-123-4567";

        JsonObject userObject = new JsonObject()
                .add("name", userName)
                .add("login", userLogin)
                .add("job_title", userJob)
                .add("phone", userPhone);

        String result = TestUtils.getFixture("BoxUser/UpdateUserInfo200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(userURL))
                .withQueryParam("fields", WireMock.equalTo("name,login,job_title,phone"))
                .withRequestBody(WireMock.equalToJson(userObject.toString()))
                .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser user = new BoxUser(this.api, userID);
        BoxUser.Info info = user.new Info();
        info.setName(userName);
        info.setLogin(userLogin);
        info.setJobTitle(userJob);
        info.setPhone(userPhone);
        user.updateInfo(info, "name", "login", "job_title", "phone");

        assertEquals(userID, info.getID());
        assertEquals(userName, info.getName());
        assertEquals(userLogin, info.getLogin());
        assertEquals(userJob, info.getJobTitle());
        assertEquals(userPhone, info.getPhone());
    }

    @Test
    public void testDeleteUserWithParamsSucceeds() {
        final String userID = "12345";
        final String userURL = "/2.0/users/" + userID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(userURL))
                .withQueryParam("force", new EqualToPattern("false"))
                .withQueryParam("notify", new EqualToPattern("false"))
            .willReturn(WireMock.noContent()));

        BoxUser user = new BoxUser(this.api, userID);
        user.delete(false, false);
    }

    @Test
    public void testDeleteUserSucceeds() {
        final String userID = "12345";
        final String userURL = "/2.0/users/" + userID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(userURL))
            // we expect no query params will be sent
            .withQueryParams(new HashMap<>())
            .willReturn(WireMock.noContent()));

        BoxUser user = new BoxUser(this.api, userID);
        user.delete();
    }

    @Test
    public void testCreateEmailAliasSucceeds() {
        final String userID = "12345";
        final String emailAliasURL = "/2.0/users/" + userID + "/email_aliases";
        final String emailAlias = "test@user.com";
        JsonObject emailAliasObject = new JsonObject()
            .add("email", emailAlias);

        String result = TestUtils.getFixture("BoxUser/CreateEmailAlias201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(emailAliasURL))
            .withRequestBody(WireMock.equalToJson(emailAliasObject.toString()))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser user = new BoxUser(this.api, userID);
        EmailAlias alias = user.addEmailAlias(emailAlias);

        assertEquals(userID, alias.getID());
        assertTrue(alias.getIsConfirmed());
        assertEquals(emailAlias, alias.getEmail());
    }

    @Test
    public void testGetEmailAliasSucceeds() {
        final String userID = "12345";
        final String userEmail = "test@user.com";
        final String emailAliasURL = "/2.0/users/" + userID + "/email_aliases";

        String result = TestUtils.getFixture("BoxUser/GetUserEmailAlias200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(emailAliasURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser user = new BoxUser(this.api, userID);
        Collection<EmailAlias> emailAliases = user.getEmailAliases();

        assertEquals(userID, emailAliases.iterator().next().getID());
        assertEquals(userEmail, emailAliases.iterator().next().getEmail());
    }

    @Test
    public void testDeleteEmailAliasSucceeds() {
        final String userID = "12345";
        final String aliasID = "12345";
        final String deleteAliasURL = "/2.0/users/" + userID + "/email_aliases/" + aliasID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteAliasURL))
            .willReturn(WireMock.noContent()));

        BoxUser user = new BoxUser(this.api, userID);
        user.deleteEmailAlias(aliasID);
    }

    @Test
    public void testGetAllEnterpriseUsersSucceeds() {
        final String getAllUsersURL = "/2.0/users";
        final String firstUserID = "12345";
        final String firstUserName = "Test User";
        final String firstUserLogin = "test@user.com";
        final String secondUserID = "43242";
        final String secondUserName = "Example User";
        final String secondUserLogin = "example@user.com";

        String result = TestUtils.getFixture("BoxUser/GetAllEnterpriseUsers200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllUsersURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        Iterator<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(this.api).iterator();
        BoxUser.Info firstUser = users.next();

        assertEquals(firstUserID, firstUser.getID());
        assertEquals(firstUserName, firstUser.getName());
        assertEquals(firstUserLogin, firstUser.getLogin());

        BoxUser.Info secondUser = users.next();

        assertEquals(secondUserID, secondUser.getID());
        assertEquals(secondUserName, secondUser.getName());
        assertEquals(secondUserLogin, secondUser.getLogin());
    }

    @Test
    public void testGetAllEnterpriseUsersMarkerPaginationSucceeds() {
        final String getAllUsersURL = "/2.0/users";
        final String firstUserID = "12345";
        final String firstUserName = "Test User";
        final String firstUserLogin = "test@user.com";
        final String secondUserID = "43242";
        final String secondUserName = "Example User";
        final String secondUserLogin = "example@user.com";

        String result = TestUtils.getFixture("BoxUser/GetAllEnterpriseUsersMarkerPagination200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllUsersURL))
            .withQueryParam("usemarker", WireMock.equalTo("true"))
            .withQueryParam("limit", WireMock.equalTo("100"))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        Iterator<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(this.api, true, null).iterator();
        BoxUser.Info firstUser = users.next();

        assertEquals(firstUserID, firstUser.getID());
        assertEquals(firstUserName, firstUser.getName());
        assertEquals(firstUserLogin, firstUser.getLogin());

        BoxUser.Info secondUser = users.next();

        assertEquals(secondUserID, secondUser.getID());
        assertEquals(secondUserName, secondUser.getName());
        assertEquals(secondUserLogin, secondUser.getLogin());
    }

    @Test
    public void testTransferContent() {
        final String sourceUserID = "1111";
        final String destinationUserID = "5678";
        final String createdByLogin = "test@user.com";
        final String transferredFolderName = "Example Test Folder";
        final String transferContentURL = "/2.0/users/" + sourceUserID + "/folders/0";

        JsonObject destinationUser = new JsonObject()
            .add("id", destinationUserID);
        JsonObject ownedBy = new JsonObject()
            .add("owned_by", destinationUser);

        String result = TestUtils.getFixture("BoxFolder/PutTransferFolder200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(transferContentURL))
            .withRequestBody(WireMock.equalToJson(ownedBy.toString()))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser sourceUser = new BoxUser(this.api, sourceUserID);
        BoxFolder.Info movedFolder = sourceUser.transferContent(destinationUserID);

        assertEquals(transferredFolderName, movedFolder.getName());
        assertEquals(createdByLogin, movedFolder.getCreatedBy().getLogin());
    }

    @Test(expected = BoxDeserializationException.class)
    public void testDeserializationException() {
        final String userID = "12345";
        final String usersURL = "/2.0/users/" + userID;

        String result = TestUtils.getFixture("BoxUser/GetUserInfoCausesDeserializationException");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(usersURL))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, result)));

        BoxUser.Info userInfo = new BoxUser(this.api, userID).getInfo();
        assertEquals("12345", userInfo.getID());
    }

    @Test
    public void testCreateReadAddTrackingCodesSucceeds() {
        final String userID = "12345";
        final String departmentID = "8675";
        final String companyID = "1701";
        final String usersURL = "/2.0/users/" + userID;

        // Mock: Create two tracking codes
        Map<String, String> createTrackingCodes = new HashMap<>();
        createTrackingCodes.put("Employee ID", userID);
        createTrackingCodes.put("Department ID", departmentID);
        String createBody = this.trackingCodesJson(createTrackingCodes).toString();
        String createResponse = TestUtils.getFixture("BoxUser/CreateTrackingCodes200");
        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(usersURL))
            .withRequestBody(WireMock.equalToJson(createBody))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, createResponse)));

        // Mock: Verify change
        String twoTrackingCodesResponse = TestUtils.getFixture("BoxUser/GetUserTwoTrackingCodes200");
        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(usersURL))
            .withQueryParam("fields", WireMock.equalTo("tracking_codes"))
            .inScenario("Get Tracking Code Scenario")
            .whenScenarioStateIs(Scenario.STARTED)
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(twoTrackingCodesResponse))
            .willSetStateTo("1st Request"));

        // Mock: Add one more tracking code
        Map<String, String> appendTrackingCodes = new HashMap<>();
        appendTrackingCodes.put("Employee ID", userID);
        appendTrackingCodes.put("Department ID", departmentID);
        appendTrackingCodes.put("Company ID", companyID);
        String updateBody = this.trackingCodesJson(appendTrackingCodes).toString();
        String updateResponse = TestUtils.getFixture("BoxUser/UpdateTrackingCodes200");
        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(usersURL))
            .withRequestBody(WireMock.equalToJson(updateBody))
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, updateResponse)));

        // Mock: Verify change
        String threeTrackingCodesResponse = TestUtils.getFixture("BoxUser/GetUserThreeTrackingCodes200");
        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(usersURL))
            .withQueryParam("fields", WireMock.equalTo("tracking_codes"))
            .inScenario("Get Tracking Code Scenario")
            .whenScenarioStateIs("1st Request")
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, threeTrackingCodesResponse))
            .willSetStateTo("2nd Request"));

        // Create two tracking codes
        BoxUser user = new BoxUser(this.api, userID);
        BoxUser.Info info = user.new Info();
        info.setTrackingCodes(createTrackingCodes);
        user.updateInfo(info);

        // Verify change
        user = new BoxUser(this.api, userID);
        info = user.getInfo("tracking_codes");
        Map<String, String> receivedTrackingCodes = info.getTrackingCodes();
        assertEquals(createTrackingCodes, receivedTrackingCodes);

        // Add one more tracking code
        info.appendTrackingCodes("Company ID", companyID);
        user.updateInfo(info);

        // Verify change
        user = new BoxUser(this.api, userID);
        info = user.getInfo("tracking_codes");
        receivedTrackingCodes = info.getTrackingCodes();
        assertEquals(appendTrackingCodes, receivedTrackingCodes);
    }

    @Test
    public void uploadAvatarAsFile() {
        final String userID = "12345";
        BoxUser user = new BoxUser(api, userID);
        String fileName = "red_100x100.png";
        String filePath = getSampleFilePath(fileName);
        File file = new File(filePath);

        String responseBody = "{\"pic_urls\": {\"small\": \"url1\",\"large\": \"url2\",\"preview\": \"url3\"}}";
        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/users/12345/avatar"))
            .withHeader("Content-Type", new ContainsPattern("multipart/form-data"))
            .withMultipartRequestBody(
                new MultipartValuePatternBuilder()
                    .withName("pic")
                    .withHeader("Content-Type", new EqualToPattern("image/png"))
            )
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, responseBody)));

        AvatarUploadResponse response = user.uploadAvatar(file);

        assertThat(response.getSmall(), CoreMatchers.equalTo("url1"));
        assertThat(response.getLarge(), CoreMatchers.equalTo("url2"));
        assertThat(response.getPreview(), CoreMatchers.equalTo("url3"));
    }

    @Test
    public void uploadAvatarAsStream() throws IOException {
        final String userID = "12345";
        BoxUser user = new BoxUser(api, userID);
        String fileName = "red_100x100.png";
        String filePath = getSampleFilePath(fileName);

        String responseBody = "{\"pic_urls\": {\"small\": \"url1\",\"large\": \"url2\",\"preview\": \"url3\"}}";
        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/users/12345/avatar"))
            .withHeader("Content-Type", new ContainsPattern("multipart/form-data"))
            .withMultipartRequestBody(
                new MultipartValuePatternBuilder()
                    .withName("pic")
                    .withHeader("Content-Type", new EqualToPattern("image/png"))
            )
            .willReturn(WireMock.okForContentType(APPLICATION_JSON, responseBody)));

        AvatarUploadResponse response = user.uploadAvatar(Files.newInputStream(Paths.get(filePath)), fileName);

        assertThat(response.getSmall(), CoreMatchers.equalTo("url1"));
        assertThat(response.getLarge(), CoreMatchers.equalTo("url2"));
        assertThat(response.getPreview(), CoreMatchers.equalTo("url3"));
    }

    @Test
    public void deleteAvatar() {
        final String userID = "12345";
        BoxUser user = new BoxUser(api, userID);

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo("/2.0/users/12345/avatar"))
            .willReturn(WireMock.noContent()));

        user.deleteAvatar();
    }

    @Test
    public void createBoxNotificationEmailSetsAllFields() {
        final String email = "mail@box.com";
        final boolean isConfirmed = true;

        BoxNotificationEmail boxNotificationEmail = new BoxNotificationEmail(email, isConfirmed);

        assertEquals(email, boxNotificationEmail.getEmail());
        assertEquals(isConfirmed, boxNotificationEmail.getIsConfirmed());
    }

    private static String getSampleFilePath(String fileName) {
        URL fileURL = BoxUserTest.class.getResource("/sample-files/" + fileName);
        try {
            return URLDecoder.decode(fileURL.getFile(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonObject trackingCodesJson(Map<String, String> trackingCodes) {
        JsonArray trackingCodesJsonArray = new JsonArray();
        for (String attrKey : trackingCodes.keySet()) {
            JsonObject trackingCode = new JsonObject();
            trackingCode.set("type", "tracking_code");
            trackingCode.set("name", attrKey);
            trackingCode.set("value", trackingCodes.get(attrKey));
            trackingCodesJsonArray.add(trackingCode);
        }

        JsonObject trackingCodesJson = new JsonObject();
        trackingCodesJson.set("tracking_codes", trackingCodesJsonArray);
        return trackingCodesJson;
    }
}
