package com.box.sdk;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
 * {@link BoxGroup} related tests.
 */
public class BoxGroupTest {

    public static final URLTemplate GROUPS_URL_TEMPLATE = new URLTemplate("groups");

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testGetAllGroupsByNameSucceeds() throws IOException {
        String result = "";
        final String getGroupsByNameURL = "/groups";
        final String groupsID = "12345";
        final String groupsName = "[getCollaborationsSucceedsAndHandlesResponseCorrectly] Test Group";

        result = TestConfig.getFixture("BoxGroup/GetGroupsByName200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getGroupsByNameURL))
                .withQueryParam("name", WireMock.containing("Test"))
                .withQueryParam("limit", WireMock.containing("1000"))
                .withQueryParam("offset", WireMock.containing("0"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxGroup.Info> iterator = BoxGroup.getAllGroupsByName(this.api, "Test").iterator();
        BoxGroup.Info firstGroupInfo = iterator.next();

        Assert.assertEquals(groupsName, firstGroupInfo.getName());
        Assert.assertEquals(groupsID, firstGroupInfo.getID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void createAndDeleteGroupSucceeds() {
        final String groupName = "[createAndDeleteGroupSucceeds] Test Group";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxGroup.Info createdGroupInfo = BoxGroup.createGroup(api, groupName);

        assertThat(createdGroupInfo.getName(), equalTo(groupName));

        createdGroupInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void addMembershipSucceedsAndGetMembershipsHasCorrectMemberships() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[addMembershipSucceedsAndGetMembershipsHasCorrectMemberships] Test Group";
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.Role membershipRole = BoxGroupMembership.Role.ADMIN;

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();
        BoxGroupMembership.Info membershipInfo = group.addMembership(user, membershipRole);
        String membershipID = membershipInfo.getID();

        assertThat(membershipInfo.getUser().getID(), is(equalTo(user.getID())));
        assertThat(membershipInfo.getGroup().getID(), is(equalTo(group.getID())));
        assertThat(membershipInfo.getRole(), is(equalTo(membershipRole)));

        Collection<BoxGroupMembership.Info> memberships = group.getMemberships();

        assertThat(memberships, hasSize(1));
        assertThat(memberships, hasItem(Matchers.<BoxGroupMembership.Info>hasProperty("ID", equalTo(membershipID))));

        group.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoSucceeds() {
        final String groupName = "[getInfoSucceeds] Test Group";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxGroup createdGroup = BoxGroup.createGroup(api, groupName).getResource();
        BoxGroup.Info createdGroupInfo = createdGroup.getInfo();

        assertThat(createdGroupInfo.getName(), equalTo(groupName));

        createdGroupInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationsSucceedsAndHandlesResponseCorrectly() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[getCollaborationsSucceedsAndHandlesResponseCorrectly] Test Group";

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();
        BoxCollaborator collabGroup = new BoxGroup(api, group.getID());

        String folderName = "[getCollaborationsSucceedsAndHandlesResponseCorrectly] Test Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();

        BoxCollaboration.Info collabInfo = folder.collaborate(collabGroup, BoxCollaboration.Role.EDITOR);

        Collection<BoxCollaboration.Info> collaborations = group.getCollaborations();

        assertThat(collaborations, hasSize(1));
        assertThat(collaborations, hasItem(
                Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabInfo.getID())))
        );

        group.delete();
        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllGroupsByNameSearchesCorrectly() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "getAllGroupsByNameSearchesCorrectly";
        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();
        try {
            //Searching groups requires few seconds delay.
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            //Do nothing
        }

        try {
            Iterable<BoxGroup.Info> iterable = BoxGroup.getAllGroupsByName(api, groupName);
            Iterator<BoxGroup.Info> iterator = iterable.iterator();
            if (iterator.hasNext()) {
                BoxGroup.Info groupInfo = iterator.next();
                if (!groupName.equals(groupInfo.getName())) {
                    Assert.fail();
                }
            } else {
                Assert.fail();
            }
        } finally {
            group.delete();
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testGetMembershipForAUserSucceeds() throws IOException {
        String result = "";
        final String userID = "1111";
        final String getMembershipForUserURL = "/users/" + userID + "/memberships";
        final String firstGroupMembershipID = "12345";
        final String firstGroupMembershipUserName = "Example User";
        final String firstGroupMembershipGroupName = "Test Group 1";
        final String secondGroupMembershipID = "32423";
        final String secondGroupMembershipUserName = "Example User";
        final String secondGroupMembershipGroupName = "Test Group 2";

        result = TestConfig.getFixture("BoxGroup/GetGroupMembershipForAUser200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getMembershipForUserURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser user = new BoxUser(this.api, userID);
        Iterator<BoxGroupMembership.Info> memberships = user.getAllMemberships().iterator();

        BoxGroupMembership.Info firstMembershipInfo = memberships.next();

        Assert.assertEquals(firstGroupMembershipID, firstMembershipInfo.getID());
        Assert.assertEquals(firstGroupMembershipUserName, firstMembershipInfo.getUser().getName());
        Assert.assertEquals(firstGroupMembershipGroupName, firstMembershipInfo.getGroup().getName());

        BoxGroupMembership.Info secondMembershipInfo = memberships.next();

        Assert.assertEquals(secondGroupMembershipID, secondMembershipInfo.getID());
        Assert.assertEquals(secondGroupMembershipUserName, secondMembershipInfo.getUser().getName());
        Assert.assertEquals(secondGroupMembershipGroupName, secondMembershipInfo.getGroup().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetMembershipForAGroupSucceeds() throws IOException {
        String result = "";
        final String groupMembershipID = "12345";
        final String groupID = "1111";
        final String getGroupMembershipURL = "/groups/" + groupID + "/memberships";
        final String userID = "2222";

        result = TestConfig.getFixture("BoxGroup/GetMembershipForAGroup200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getGroupMembershipURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        Iterable<BoxGroupMembership.Info> memberships = group.getAllMemberships();
        BoxGroupMembership.Info membership = memberships.iterator().next();

        Assert.assertEquals(groupMembershipID, membership.getID());
        Assert.assertEquals(groupID, membership.getGroup().getID());
        Assert.assertEquals(userID, membership.getUser().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteGroupMembershipSucceeds() {
        final String groupMembershipID = "12345";
        final String deleteGroupMembershipURL = "/group_memberships/" + groupMembershipID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteGroupMembershipURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxGroupMembership membership = new BoxGroupMembership(this.api, groupMembershipID);
        membership.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateGroupMembershipSucceeds() throws IOException {
        String result = "";
        final String groupMembershipID = "12345";
        final String groupMembershipURL = "/group_memberships/" + groupMembershipID;
        final String groupName = "Example Group";
        final BoxGroupMembership.Role role = BoxGroupMembership.Role.ADMIN;

        JsonObject membershipObject = new JsonObject()
                .add("role", "admin");

        result = TestConfig.getFixture("BoxGroup/UpdateGroupMembership200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(groupMembershipURL))
                .withRequestBody(WireMock.equalToJson(membershipObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxGroupMembership membership = new BoxGroupMembership(this.api, groupMembershipID);
        BoxGroupMembership.Info info = membership.new Info();
        info.addPendingChange("role", "admin");
        membership.updateInfo(info);

        Assert.assertEquals(groupName, info.getGroup().getName());
        Assert.assertEquals(groupMembershipID, info.getID());
        Assert.assertEquals(role, info.getRole());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateGroupMembershipSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String groupID = "2222";
        final String userID = "1111";
        final String groupCollaborationURL = "/group_memberships";
        final String groupMembershipID = "12345";
        final BoxGroupMembership.Role groupMembershipRole = BoxGroupMembership.Role.MEMBER;

        JsonObject userObject = new JsonObject()
                .add("id", userID);
        JsonObject groupObject = new JsonObject()
                .add("id", groupID);
        JsonObject groupMembershipObject = new JsonObject()
                .add("user", userObject)
                .add("group", groupObject);

        result = TestConfig.getFixture("BoxGroup/CreateGroupMembership201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(groupCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        BoxUser user = new BoxUser(this.api, userID);
        BoxGroupMembership.Info groupMembershipInfo = group.addMembership(user);

        Assert.assertEquals(groupMembershipID, groupMembershipInfo.getID());
        Assert.assertEquals(groupMembershipRole, groupMembershipInfo.getRole());
        Assert.assertEquals(groupID, groupMembershipInfo.getGroup().getID());
        Assert.assertEquals(userID, groupMembershipInfo.getUser().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllGroupsCollaborationsSucceeds() throws IOException {
        String result = "";
        final String groupID = "12345";
        final String groupCollaborationURL = "/groups/" + groupID + "/collaborations";
        final String collaborationStatus = "accepted";
        final String accessibleByName = "New Group Name";
        final String accessiblyByID = "1111";
        final BoxCollaboration.Role groupRole = BoxCollaboration.Role.EDITOR;
        final String itemID = "2222";
        final String itemName = "Ball Valve Diagram";

        result = TestConfig.getFixture("BoxGroup/GetAGroupsCollaborations200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(groupCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        Collection<BoxCollaboration.Info> groupInfo = group.getCollaborations();
        BoxCollaboration.Info info = groupInfo.iterator().next();

        Assert.assertEquals(accessibleByName, info.getAccessibleBy().getName());
        Assert.assertEquals(accessiblyByID, info.getAccessibleBy().getID());
        Assert.assertEquals(groupRole, info.getRole());
        Assert.assertEquals(itemID, info.getItem().getID());
        Assert.assertEquals(itemName, info.getItem().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteAGroupSucceedsAndSendsCorrectJson() throws IOException {
        final String groupID = "12345";
        final String deleteGroupURL = "/groups/" + groupID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteGroupURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        group.delete();
    }


    @Test
    @Category(UnitTest.class)
    public void testUpdateAGroupInfoSendsCorrectJson() throws IOException {
        String getGroupResult = "";
        String updateGroupResult = "";
        final String groupID = "12345";
        final String groupURL = "/groups/" + groupID;
        final String groupName = "New Group Name";

        JsonObject groupObject = new JsonObject()
                .add("name", groupName);

        getGroupResult = TestConfig.getFixture("BoxGroup/GetAGroupsInfo200");
        updateGroupResult = TestConfig.getFixture("BoxGroup/UpdateAGroupsInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(groupURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getGroupResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(groupURL))
                .withRequestBody(WireMock.equalToJson(groupObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(updateGroupResult)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        BoxGroup.Info groupInfo = group.getInfo();
        groupInfo.addPendingChange("name", groupName);
        group.updateInfo(groupInfo);
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAGroupInfoSucceeds() throws IOException {
        String result = "";
        final String groupID = "12345";
        final String groupURL = "/groups/" + groupID;
        final String groupName = "Test Group";

        result = TestConfig.getFixture("BoxGroup/GetAGroupsInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(groupURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxGroup.Info groupInfo = new BoxGroup(this.api, groupID).getInfo();

        Assert.assertEquals(groupID, groupInfo.getID());
        Assert.assertEquals(groupName, groupInfo.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateGroupSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String createGroupsURL = "/groups";
        final String groupID = "12345";
        final String groupName = "Test Group";

        JsonObject groupObject = new JsonObject()
                .add("name", groupName);

        result = TestConfig.getFixture("BoxGroup/CreateAGroup201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(createGroupsURL))
                .withRequestBody(WireMock.equalToJson(groupObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxGroup.Info groupInfo = BoxGroup.createGroup(this.api, groupName);

        Assert.assertEquals(groupID, groupInfo.getID());
        Assert.assertEquals(groupName, groupInfo.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllEnterpriseGroupsSucceeds() throws IOException {
        String result = "";
        final String getAllGroupsURL = "/groups";
        final String firstGroupID = "12345";
        final String firstGroupName = "Test Group 1";
        final String secondGroupID = "53453";
        final String secondGroupName = "Test Group 2";

        result = TestConfig.getFixture("BoxGroup/GetAllGroups200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllGroupsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxGroup.Info> groups = BoxGroup.getAllGroups(this.api).iterator();
        BoxGroup.Info firstGroupInfo = groups.next();

        Assert.assertEquals(firstGroupName, firstGroupInfo.getName());
        Assert.assertEquals(firstGroupID, firstGroupInfo.getID());

        BoxGroup.Info secondGroupInfo = groups.next();

        Assert.assertEquals(secondGroupName, secondGroupInfo.getName());
        Assert.assertEquals(secondGroupID, secondGroupInfo.getID());
    }
}
