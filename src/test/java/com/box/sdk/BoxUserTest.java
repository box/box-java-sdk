package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;

import static com.box.sdk.internal.utils.CollectionUtils.createListFrom;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * {@link BoxUser} related tests.
 */

public class BoxUserTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();
    private final static String NEW_USER_LOGIN = "login2@boz.com";
    private final static String NEW_USER_NAME = "non-empty name";

    @BeforeClass
    public static void cleanup() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        for (BoxUser.Info user : BoxUser.getAllEnterpriseUsers(api, NEW_USER_LOGIN)) {
            user.getResource().delete(false, false);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCurrentUserInfoIsCorrect() {
        final String expectedName = "Java SDK";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxUser.Info info = user.getInfo(BoxUser.ALL_FIELDS);

        assertThat(info.getName(), equalTo(expectedName));
        assertNotNull(info.getEnterprise().getID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void createAndDeleteEnterpriseUserSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        // Since deleting users happens in a separate process in the backend
        // it is really an asynchronous call.  So we have to use a new user in
        // this test in case the previous user's deletion hasn't completed.

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, NEW_USER_LOGIN, NEW_USER_NAME);

        assertNotNull(createdUserInfo.getID());
        assertEquals(NEW_USER_NAME, createdUserInfo.getName());
        assertEquals(NEW_USER_LOGIN, createdUserInfo.getLogin());

        createdUserInfo.getResource().delete(false, false);

        Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(api, NEW_USER_LOGIN);
        assertThat(createListFrom(users), Matchers.<BoxUser.Info>hasSize(0));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getMembershipsHasCorrectMemberships() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[getMembershipsHasCorrectMemberships] Test Group";
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.GroupRole groupRole = BoxGroupMembership.GroupRole.ADMIN;

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();
        BoxGroupMembership.Info membershipInfo = group.addMembership(user, groupRole);
        String membershipID = membershipInfo.getID();

        Collection<BoxGroupMembership.Info> memberships = user.getMemberships();

        assertThat(memberships, hasSize(greaterThanOrEqualTo(1)));
        assertThat(memberships, hasItem(Matchers.<BoxGroupMembership.Info>hasProperty("ID", equalTo(membershipID))));

        group.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String login = "login3+" + Calendar.getInstance().getTimeInMillis() + "@boz.com";
        final String originalName = "original name";
        final String updatedName = "updated name";

        BoxUser.Info userInfo = BoxUser.createEnterpriseUser(api, login, originalName);
        userInfo.setName(updatedName);

        BoxUser user = userInfo.getResource();
        user.updateInfo(userInfo);

        assertEquals(updatedName, userInfo.getName());

        user.delete(false, false);
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAvatar() throws IOException {
        final String expectedURL = "/users/12345/avatar";
        File file = new File("src/test/Fixtures/BoxUser/small_avatar.png");
        byte[] fileByteArray = Files.readAllBytes(file.toPath());

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(expectedURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "image/png")
                        .withBody(fileByteArray)));

        BoxUser user = new BoxUser(this.api, "12345");
        InputStream avatarStream = user.getAvatar();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[10000];
        try {
            int n = avatarStream.read(buffer);
            while (n != -1) {
                output.write(buffer, 0, n);
                n = avatarStream.read(buffer);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        Assert.assertArrayEquals(fileByteArray, output.toByteArray());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetCurrentUserInfoSucceeds() throws IOException {
        final String userURL = "/users/me";
        final String userInfoURL = "/users/12345";
        final String userName = "Test User";
        final String userLogin = "test@user.com";
        final String userphoneNumber = "1111111111";

        String result = TestConfig.getFixture("BoxUser/GetCurrentUserInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(userInfoURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser user = BoxUser.getCurrentUser(this.api);
        BoxUser.Info info = user.getInfo();

        Assert.assertEquals(userName, info.getName());
        Assert.assertEquals(userLogin, info.getLogin());
        Assert.assertEquals(userphoneNumber, info.getPhone());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetUserInfoByIDSucceeds() throws IOException {
        final String userID = "12345";
        final String userURL = "/users/" + userID;
        final String userName = "Test User";
        final String userLogin = "test@user.com";
        final String userPhoneNumber = "1111111111";

        String result = TestConfig.getFixture("BoxUser/GetCurrentUserInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser user = new BoxUser(this.api, userID);
        BoxUser.Info userInfo = user.getInfo();

        Assert.assertEquals(userID, userInfo.getID());
        Assert.assertEquals(userName, userInfo.getName());
        Assert.assertEquals(userLogin, userInfo.getLogin());
        Assert.assertEquals(userPhoneNumber, userInfo.getPhone());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateAppUserSucceeds() throws IOException {
        final String userURL = "/users";
        final String userID = "12345";
        final String userName = "Java SDK App User";
        final String userLogin = "testuser@boxdevedition.com";

        String result = TestConfig.getFixture("BoxUser/CreateAppUser201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(userURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser.Info createdUserInfo = BoxUser.createAppUser(this.api, userName);

        Assert.assertEquals(userID, createdUserInfo.getID());
        Assert.assertEquals(userName, createdUserInfo.getName());
        Assert.assertEquals(userLogin, createdUserInfo.getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateManagedUserSucceeds() throws IOException {
        final String userURL = "/users";
        final String userID = "12345";
        final String userName = "Test Managed User";
        final String userLogin = "test@user.com";
        final String userTimeZone = "America/Los_Angeles";

        String result = TestConfig.getFixture("BoxUser/CreateManagedUser201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(userURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(this.api, userLogin, userName);

        Assert.assertEquals(userID, createdUserInfo.getID());
        Assert.assertEquals(userName, createdUserInfo.getName());
        Assert.assertEquals(userLogin, createdUserInfo.getLogin());
        Assert.assertEquals(userTimeZone, createdUserInfo.getTimezone());
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateUserInfoSucceedsAndSendsCorrectJson() throws IOException {
        final String userID = "12345";
        final String userURL = "/users/" + userID;
        final String userName = "New User Name";
        final String userLogin = "new@test.com";
        final String userJob = "Example Job";
        final String userPhone = "650-123-4567";

        JsonObject userObject = new JsonObject()
                .add("name", userName)
                .add("login", userLogin)
                .add("job_title", userJob)
                .add("phone", userPhone);

        String result = TestConfig.getFixture("BoxUser/UpdateUserInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(userURL))
                .withRequestBody(WireMock.equalToJson(userObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser user = new BoxUser(this.api, userID);
        BoxUser.Info info = user.new Info();
        info.setName(userName);
        info.setLogin(userLogin);
        info.setJobTitle(userJob);
        info.setPhone(userPhone);
        user.updateInfo(info);

        Assert.assertEquals(userID, info.getID());
        Assert.assertEquals(userName, info.getName());
        Assert.assertEquals(userLogin, info.getLogin());
        Assert.assertEquals(userJob, info.getJobTitle());
        Assert.assertEquals(userPhone, info.getPhone());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteUserSucceeds() {
        final String userID = "12345";
        final String userURL = "/users/" + userID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(userURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxUser user = new BoxUser(this.api, userID);
        user.delete(false, false);
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateEmailAliasSucceeds() throws IOException {
        final String userID = "12345";
        final String emailAliasURL = "/users/" + userID + "/email_aliases";
        final String emailAlias = "test@user.com";
        JsonObject emailAliasObject = new JsonObject()
                .add("email", emailAlias);

        String result = TestConfig.getFixture("BoxUser/CreateEmailAlias201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(emailAliasURL))
                .withRequestBody(WireMock.equalToJson(emailAliasObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser user = new BoxUser(this.api, userID);
        EmailAlias alias = user.addEmailAlias(emailAlias);

        Assert.assertEquals(userID, alias.getID());
        Assert.assertTrue(alias.getIsConfirmed());
        Assert.assertEquals(emailAlias, alias.getEmail());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetEmailAliasSucceeds() throws IOException {
        final String userID = "12345";
        final String userEmail = "test@user.com";
        final String emailAliasURL = "/users/" + userID + "/email_aliases";

        String result = TestConfig.getFixture("BoxUser/GetUserEmailAlias200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(emailAliasURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser user = new BoxUser(this.api, userID);
        Collection<EmailAlias> emailAliases = user.getEmailAliases();

        Assert.assertEquals(userID, emailAliases.iterator().next().getID());
        Assert.assertEquals(userEmail, emailAliases.iterator().next().getEmail());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteEmailAliasSucceeds() {
        final String userID = "12345";
        final String aliasID = "12345";
        final String deleteAliasURL = "/users/" + userID + "/email_aliases/" + aliasID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteAliasURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxUser user = new BoxUser(this.api, userID);
        user.deleteEmailAlias(aliasID);
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllEnterpriseUsersSucceeds() throws IOException {
        final String getAllUsersURL = "/users";
        final String firstUserID = "12345";
        final String firstUserName = "Test User";
        final String firstUserLogin = "test@user.com";
        final String secondUserID = "43242";
        final String secondUserName = "Example User";
        final String secondUserLogin = "example@user.com";

        String result = TestConfig.getFixture("BoxUser/GetAllEnterpriseUsers200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllUsersURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(this.api).iterator();
        BoxUser.Info firstUser = users.next();

        Assert.assertEquals(firstUserID, firstUser.getID());
        Assert.assertEquals(firstUserName, firstUser.getName());
        Assert.assertEquals(firstUserLogin, firstUser.getLogin());

        BoxUser.Info secondUser = users.next();

        Assert.assertEquals(secondUserID, secondUser.getID());
        Assert.assertEquals(secondUserName, secondUser.getName());
        Assert.assertEquals(secondUserLogin, secondUser.getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllEnterpriseUsersMarkerPaginationSucceeds() throws IOException {
        final String getAllUsersURL = "/users";
        final String firstUserID = "12345";
        final String firstUserName = "Test User";
        final String firstUserLogin = "test@user.com";
        final String secondUserID = "43242";
        final String secondUserName = "Example User";
        final String secondUserLogin = "example@user.com";

        String result = TestConfig.getFixture("BoxUser/GetAllEnterpriseUsersMarkerPagination200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllUsersURL))
                .withQueryParam("usemarker", WireMock.equalTo("true"))
                .withQueryParam("limit", WireMock.equalTo("100"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(this.api, true, null).iterator();
        BoxUser.Info firstUser = users.next();

        Assert.assertEquals(firstUserID, firstUser.getID());
        Assert.assertEquals(firstUserName, firstUser.getName());
        Assert.assertEquals(firstUserLogin, firstUser.getLogin());

        BoxUser.Info secondUser = users.next();

        Assert.assertEquals(secondUserID, secondUser.getID());
        Assert.assertEquals(secondUserName, secondUser.getName());
        Assert.assertEquals(secondUserLogin, secondUser.getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testTransferContent() throws IOException, InterruptedException {
        final String sourceUserID = "1111";
        final String destinationUserID = "5678";
        final String createdByLogin = "test@user.com";
        final String transferredFolderName = "Example Test Folder";
        final String transferContentURL = "/users/" + sourceUserID + "/folders/0";

        JsonObject destinationUser = new JsonObject()
                .add("id", destinationUserID);
        JsonObject ownedBy = new JsonObject()
                .add("owned_by", destinationUser);

        String result = TestConfig.getFixture("BoxFolder/PutTransferFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(transferContentURL))
                .withRequestBody(WireMock.equalToJson(ownedBy.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Thread.sleep(5000);

        BoxUser sourceUser = new BoxUser(this.api, sourceUserID);
        BoxFolder.Info movedFolder = sourceUser.transferContent(destinationUserID);

        Assert.assertEquals(transferredFolderName, movedFolder.getName());
        Assert.assertEquals(createdByLogin, movedFolder.getCreatedBy().getLogin());
    }

    @Test(expected = BoxDeserializationException.class)
    public void testDeserializationException() throws IOException {
        final String userID = "12345";
        final String usersURL = "/users/" + userID;

        String result = TestConfig.getFixture("BoxUser/GetUserInfoCausesDeserializationException");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(usersURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser.Info userInfo = new BoxUser(this.api, userID).getInfo();
        Assert.assertEquals("12345", userInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateReadAddTrackingCodesSucceeds() throws IOException {
        final String userID = "12345";
        final String departmentID = "8675";
        final String companyID = "1701";
        final String usersURL = "/users/" + userID;

        // Mock: Create two tracking codes
        Map<String, String> createTrackingCodes = new HashMap<>();
        createTrackingCodes.put("Employee ID", userID);
        createTrackingCodes.put("Department ID", departmentID);
        String createBody = this.trackingCodesJson(createTrackingCodes).toString();
        String createResponse = TestConfig.getFixture("BoxUser/CreateTrackingCodes200");
        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(usersURL))
            .withRequestBody(WireMock.equalToJson(createBody))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(createResponse)));

        // Mock: Verify change
        String twoTrackingCodesResponse = TestConfig.getFixture("BoxUser/GetUserTwoTrackingCodes200");
        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(usersURL))
            .withQueryParam("fields", WireMock.equalTo("tracking_codes"))
            .inScenario("Get Tracking Code Scenario")
            .whenScenarioStateIs(Scenario.STARTED)
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(twoTrackingCodesResponse))
            .willSetStateTo("1st Request"));

        // Mock: Add one more tracking code
        Map<String, String> appendTrackingCodes = new HashMap<>();
        appendTrackingCodes.put("Employee ID", userID);
        appendTrackingCodes.put("Department ID", departmentID);
        appendTrackingCodes.put("Company ID", companyID);
        String updateBody = this.trackingCodesJson(appendTrackingCodes).toString();
        String updateResponse = TestConfig.getFixture("BoxUser/UpdateTrackingCodes200");
        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(usersURL))
            .withRequestBody(WireMock.equalToJson(updateBody))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(updateResponse)));

        // Mock: Verify change
        String threeTrackingCodesResponse = TestConfig.getFixture("BoxUser/GetUserThreeTrackingCodes200");
        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(usersURL))
            .withQueryParam("fields", WireMock.equalTo("tracking_codes"))
            .inScenario("Get Tracking Code Scenario")
            .whenScenarioStateIs("1st Request")
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(threeTrackingCodesResponse))
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
        Assert.assertEquals(createTrackingCodes, receivedTrackingCodes);

        // Add one more tracking code
        info.appendTrackingCodes("Company ID", companyID);
        user.updateInfo(info);

        // Verify change
        user = new BoxUser(this.api, userID);
        info = user.getInfo("tracking_codes");
        receivedTrackingCodes = info.getTrackingCodes();
        Assert.assertEquals(appendTrackingCodes, receivedTrackingCodes);
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
