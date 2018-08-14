package com.box.sdk;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * {@link BoxUser} related tests.
 */

public class BoxUserTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(IntegrationTest.class)
    public void getCurrentUserInfoIsCorrect() throws InterruptedException {
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
        final String login = "login2@boz.com";
        final String name = "non-empty name";

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, login, name);

        assertNotNull(createdUserInfo.getID());
        assertEquals(name, createdUserInfo.getName());
        assertEquals(login, createdUserInfo.getLogin());

        createdUserInfo.getResource().delete(false, false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getMembershipsHasCorrectMemberships() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[getMembershipsHasCorrectMemberships] Test Group";
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.Role membershipRole = BoxGroupMembership.Role.ADMIN;

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();
        BoxGroupMembership.Info membershipInfo = group.addMembership(user, membershipRole);
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
    public void testGetCurrentUserInfoSucceeds() throws IOException {
        String result = "";
        final String userURL = "/users/me";
        final String userInfoURL = "/users/12345";
        final String userName = "Test User";
        final String userLogin = "test@user.com";
        final String userphoneNumber = "1111111111";

        result = TestConfig.getFixture("BoxUser/GetCurrentUserInfo200");

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
        String result = "";
        final String userID = "12345";
        final String userURL = "/users/" + userID;
        final String userName = "Test User";
        final String userLogin = "test@user.com";
        final String userPhoneNumber = "1111111111";

        result = TestConfig.getFixture("BoxUser/GetCurrentUserInfo200");

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
        String result = "";
        final String userURL = "/users";
        final String userID = "12345";
        final String userName = "Java SDK App User";
        final String userLogin = "testuser@boxdevedition.com";

        result = TestConfig.getFixture("BoxUser/CreateAppUser201");

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
        String result = "";
        final String userURL = "/users";
        final String userID = "12345";
        final String userName = "Test Managed User";
        final String userLogin = "test@user.com";
        final String userTimeZone = "America/Los_Angeles";

        result = TestConfig.getFixture("BoxUser/CreateManagedUser201");

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
        String result = "";
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

        result = TestConfig.getFixture("BoxUser/UpdateUserInfo200");

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
    public void testDeleteUserSucceeds() throws IOException {
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
    public void testGreateEmailAliasSucceeds() throws IOException {
        String result = "";
        final String userID = "12345";
        final String emailAliasURL = "/users/" + userID + "/email_aliases";
        final String emailAlias = "test@user.com";
        JsonObject emailAliasObject = new JsonObject()
                .add("email", emailAlias);

        result = TestConfig.getFixture("BoxUser/CreateEmailAlias201");

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
        String result = "";
        final String userID = "12345";
        final String userEmail = "test@user.com";
        final String emailAliasURL = "/users/" + userID + "/email_aliases";

        result = TestConfig.getFixture("BoxUser/GetUserEmailAlias200");

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
    public void testDeleteEmailAliasSucceeds() throws IOException {
        String result = "";
        final String userID = "12345";
        final String aliasID = "12345";
        final String userEmail = "test@user.com";
        final String userURL = "/users/" + userID;
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
        String result = "";
        final String getAllUsersURL = "/users";
        final String firstUserID = "12345";
        final String firstUserName = "Test User";
        final String firstUserLogin = "test@user.com";
        final String secondUserID = "43242";
        final String secondUserName = "Example User";
        final String secondUserLogin = "example@user.com";

        result = TestConfig.getFixture("BoxUser/GetAllEnterpriseUsers200");

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
    public void testTransferContent() throws IOException, InterruptedException {
        String result = "";
        final String sourceUserID = "1111";
        final String destinationUserID = "5678";
        final String createdByLogin = "test@user.com";
        final String transferredFolderName = "Example Test Folder";
        final String transferContentURL = "/users/" + sourceUserID + "/folders/0";

        JsonObject destinationUser = new JsonObject()
                .add("id", destinationUserID);
        JsonObject ownedBy = new JsonObject()
                .add("owned_by", destinationUser);

        result = TestConfig.getFixture("BoxFolder/PutTransferFolder200");

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
}
