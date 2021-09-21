package com.box.sdk;

import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxGroup} related unit tests.
 */
public class BoxGroupTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testGetAllGroupsByNameSucceeds() throws IOException {
        final String getGroupsByNameURL = "/groups";
        final String groupsID = "12345";
        final String groupsName = "[getCollaborationsSucceedsAndHandlesResponseCorrectly] Test Group";

        String result = TestConfig.getFixture("BoxGroup/GetGroupsByName200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getGroupsByNameURL))
            .withQueryParam("filter_term", WireMock.containing("Test"))
            .withQueryParam("limit", WireMock.containing("1000"))
            .withQueryParam("offset", WireMock.containing("0"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxGroup.Info> iterator = BoxGroup.getAllGroupsByName(this.api, "Test").iterator();
        BoxGroup.Info firstGroupInfo = iterator.next();

        assertEquals(groupsName, firstGroupInfo.getName());
        assertEquals(groupsID, firstGroupInfo.getID());
    }

    @Test
    public void testGetAllGroupsByNameWithFieldsOptionSucceeds() throws IOException {
        final String getGroupsByNameURL = "/groups";
        final String groupsID = "12345";
        final String groupsDescription = "This is Test Group";

        String result = TestConfig.getFixture("BoxGroup/GetGroupsByNameWithFieldsOption200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getGroupsByNameURL))
            .withQueryParam("filter_term", WireMock.containing("Test"))
            .withQueryParam("fields", WireMock.containing("description"))
            .withQueryParam("limit", WireMock.containing("1000"))
            .withQueryParam("offset", WireMock.containing("0"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxGroup.Info> iterator = BoxGroup.getAllGroupsByName(this.api, "Test", "description").iterator();
        BoxGroup.Info firstGroupInfo = iterator.next();

        assertEquals(groupsID, firstGroupInfo.getID());
        assertEquals(groupsDescription, firstGroupInfo.getDescription());
        Assert.assertNull(firstGroupInfo.getName());
    }

    @Test
    public void testGetMembershipForAUserSucceeds() throws IOException {
        final String userID = "1111";
        final String getMembershipForUserURL = "/users/" + userID + "/memberships";
        final String firstGroupMembershipID = "12345";
        final String firstGroupMembershipUserName = "Example User";
        final String firstGroupMembershipGroupName = "Test Group 1";
        final String secondGroupMembershipID = "32423";
        final String secondGroupMembershipUserName = "Example User";
        final String secondGroupMembershipGroupName = "Test Group 2";

        String result = TestConfig.getFixture("BoxGroup/GetGroupMembershipForAUser200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getMembershipForUserURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxUser user = new BoxUser(this.api, userID);
        Iterator<BoxGroupMembership.Info> memberships = user.getAllMemberships().iterator();

        BoxGroupMembership.Info firstMembershipInfo = memberships.next();

        assertEquals(firstGroupMembershipID, firstMembershipInfo.getID());
        assertEquals(firstGroupMembershipUserName, firstMembershipInfo.getUser().getName());
        assertEquals(firstGroupMembershipGroupName, firstMembershipInfo.getGroup().getName());

        BoxGroupMembership.Info secondMembershipInfo = memberships.next();

        assertEquals(secondGroupMembershipID, secondMembershipInfo.getID());
        assertEquals(secondGroupMembershipUserName, secondMembershipInfo.getUser().getName());
        assertEquals(secondGroupMembershipGroupName, secondMembershipInfo.getGroup().getName());
    }

    @Test
    public void testGetMembershipForAGroupSucceeds() throws IOException {
        final String groupMembershipID = "12345";
        final String groupID = "1111";
        final String getGroupMembershipURL = "/groups/" + groupID + "/memberships";
        final String userID = "2222";

        String result = TestConfig.getFixture("BoxGroup/GetMembershipForAGroup200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getGroupMembershipURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        Iterable<BoxGroupMembership.Info> memberships = group.getAllMemberships();
        BoxGroupMembership.Info membership = memberships.iterator().next();

        assertEquals(groupMembershipID, membership.getID());
        assertEquals(groupID, membership.getGroup().getID());
        assertEquals(userID, membership.getUser().getID());
    }

    @Test
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
    public void testUpdateGroupMembershipSucceeds() throws IOException {
        final String groupMembershipID = "12345";
        final String groupMembershipURL = "/group_memberships/" + groupMembershipID;
        final String groupName = "Example Group";
        final BoxGroupMembership.GroupRole role = BoxGroupMembership.GroupRole.ADMIN;

        JsonObject membershipObject = new JsonObject()
            .add("role", "admin");

        String result = TestConfig.getFixture("BoxGroup/UpdateGroupMembership200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(groupMembershipURL))
            .withRequestBody(WireMock.equalToJson(membershipObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxGroupMembership membership = new BoxGroupMembership(this.api, groupMembershipID);
        BoxGroupMembership.Info info = membership.new Info();
        info.addPendingChange("role", "admin");
        membership.updateInfo(info);

        assertEquals(groupName, info.getGroup().getName());
        assertEquals(groupMembershipID, info.getID());
        assertEquals(role, info.getGroupRole());
    }

    @Test
    public void testCreateGroupMembershipSucceedsAndSendsCorrectJson() throws IOException {
        final String groupID = "2222";
        final String userID = "1111";
        final String groupCollaborationURL = "/group_memberships";
        final String groupMembershipID = "12345";
        final BoxGroupMembership.Role groupRole = BoxGroupMembership.Role.MEMBER;
        final BoxGroupMembership.GroupRole groupMembershipRole = BoxGroupMembership.GroupRole.MEMBER;

        JsonObject userObject = new JsonObject()
            .add("id", userID);
        JsonObject groupObject = new JsonObject()
            .add("id", groupID);
        JsonObject groupMembershipObject = new JsonObject()
            .add("user", userObject)
            .add("group", groupObject);

        String result = TestConfig.getFixture("BoxGroup/CreateGroupMembership201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(groupCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        BoxUser user = new BoxUser(this.api, userID);
        BoxGroupMembership.Info groupMembershipInfo = group.addMembership(user);

        assertEquals(groupMembershipID, groupMembershipInfo.getID());
        assertEquals(groupRole, groupMembershipInfo.getRole());
        assertEquals(groupMembershipRole, groupMembershipInfo.getGroupRole());
        assertEquals(groupID, groupMembershipInfo.getGroup().getID());
        assertEquals(userID, groupMembershipInfo.getUser().getID());
    }

    @Test
    public void testGetGroupsCollaborationsSucceeds() throws IOException {
        final String groupID = "12345";
        final String groupCollaborationURL = "/groups/" + groupID + "/collaborations";
        final String accessibleByName = "New Group Name";
        final String accessiblyByID = "1111";
        final BoxCollaboration.Role groupRole = BoxCollaboration.Role.EDITOR;
        final String itemID = "2222";
        final String itemName = "Ball Valve Diagram";

        String result = TestConfig.getFixture("BoxGroup/GetAGroupsCollaborations1stPage200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(groupCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        Collection<BoxCollaboration.Info> groupInfo = group.getCollaborations();
        BoxCollaboration.Info info = groupInfo.iterator().next();

        assertEquals(accessibleByName, info.getAccessibleBy().getName());
        assertEquals(accessiblyByID, info.getAccessibleBy().getID());
        assertEquals(groupRole, info.getRole());
        assertEquals(itemID, info.getItem().getID());
        assertEquals(itemName, info.getItem().getName());
    }

    @Test
    public void testGetAllGroupsCollaborationsSucceeds() throws IOException {
        final String groupID = "12345";
        final String groupCollaborationURL = "/groups/" + groupID + "/collaborations";
        String result1 = TestConfig.getFixture("BoxGroup/GetAGroupsCollaborations1stPage200");
        String result2 = TestConfig.getFixture("BoxGroup/GetAGroupsCollaborations2ndPage200");

        // First request will return a page of results with one item
        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(groupCollaborationURL))
            .withQueryParam("offset", WireMock.containing("0"))
            .withQueryParam("limit", WireMock.containing("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result1)));

        // Second request will return a page of results with remaining one item
        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(groupCollaborationURL))
            .withQueryParam("offset", WireMock.containing("1"))
            .withQueryParam("limit", WireMock.containing("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result2)));

        BoxGroup group = new BoxGroup(this.api, groupID);
        Iterator<BoxCollaboration.Info> collaborations = group.getAllCollaborations().iterator();

        // First item on the first page of results
        BoxCollaboration.Info currCollaboration = collaborations.next();
        assertEquals("12345", currCollaboration.getID());
        assertEquals("New Group Name", currCollaboration.getAccessibleBy().getName());
        assertEquals("2222", currCollaboration.getItem().getID());
        assertEquals("folder", currCollaboration.getItem().getType());

        // First item on the second page of results (this next call makes the second request to get the second page)
        currCollaboration = collaborations.next();
        assertEquals("23647", currCollaboration.getID());
        assertEquals("New Group Name", currCollaboration.getAccessibleBy().getName());
        assertEquals("12342", currCollaboration.getItem().getID());
        assertEquals("file", currCollaboration.getItem().getType());
    }

    @Test
    public void testDeleteAGroupSucceedsAndSendsCorrectJson() {
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
    public void testUpdateAGroupInfoSendsCorrectJson() throws IOException {
        final String groupID = "12345";
        final String groupURL = "/groups/" + groupID;
        final String groupName = "New Group Name";

        JsonObject groupObject = new JsonObject()
            .add("name", groupName);

        String getGroupResult = TestConfig.getFixture("BoxGroup/GetAGroupsInfo200");
        String updateGroupResult = TestConfig.getFixture("BoxGroup/UpdateAGroupsInfo200");

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
        groupInfo.setName(groupName);
        group.updateInfo(groupInfo);
    }

    @Test
    public void testGetAGroupInfoSucceeds() throws IOException {
        final String groupID = "12345";
        final String groupURL = "/groups/" + groupID;
        final String groupName = "Test Group";

        String result = TestConfig.getFixture("BoxGroup/GetAGroupsInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(groupURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxGroup.Info groupInfo = new BoxGroup(this.api, groupID).getInfo();

        assertEquals(groupID, groupInfo.getID());
        assertEquals(groupName, groupInfo.getName());
    }

    @Test
    public void testCreateGroupSucceedsAndSendsCorrectJson() throws IOException {
        final String createGroupsURL = "/groups";
        final String groupID = "12345";
        final String groupName = "Test Group";

        JsonObject groupObject = new JsonObject()
            .add("name", groupName);

        String result = TestConfig.getFixture("BoxGroup/CreateAGroup201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(createGroupsURL))
            .withRequestBody(WireMock.equalToJson(groupObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxGroup.Info groupInfo = BoxGroup.createGroup(this.api, groupName);

        assertEquals(groupID, groupInfo.getID());
        assertEquals(groupName, groupInfo.getName());
    }

    @Test
    public void testGetAllEnterpriseGroupsSucceeds() throws IOException {
        final String getAllGroupsURL = "/groups";
        final String firstGroupID = "12345";
        final String firstGroupName = "Test Group 1";
        final String secondGroupID = "53453";
        final String secondGroupName = "Test Group 2";

        String result = TestConfig.getFixture("BoxGroup/GetAllGroups200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllGroupsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxGroup.Info> groups = BoxGroup.getAllGroups(this.api).iterator();
        BoxGroup.Info firstGroupInfo = groups.next();

        assertEquals(firstGroupName, firstGroupInfo.getName());
        assertEquals(firstGroupID, firstGroupInfo.getID());

        BoxGroup.Info secondGroupInfo = groups.next();

        assertEquals(secondGroupName, secondGroupInfo.getName());
        assertEquals(secondGroupID, secondGroupInfo.getID());
    }

    private void deleteGroup(BoxGroup createdGroup) {
        if (createdGroup != null) {
            createdGroup.delete();
        }
    }

    private void deleteFolder(BoxFolder folder) {
        if (folder != null) {
            folder.delete(true);
        }
    }
}
