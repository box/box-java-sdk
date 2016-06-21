package com.box.sdk;

import java.text.ParseException;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONCompareMode.*;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxGroupTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

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
    @Category(UnitTest.class)
    public void createGroupSendsCorrectRequestAndParsesResponseCorrectly() throws ParseException {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        final String name = "Test Group";
        final String description = "Test group description";
        final String provenance = "test provenance";
        final String externalSyncItentifier = "unit tests";
        final String invitibilityLevel = "admins_only";
        final String memberViewabilityLevel = "all_managed_users";

        JsonObject mockJSON = new JsonObject()
            .add("type", "group")
            .add("id", "305742")
            .add("name", name)
            .add("description", description)
            .add("provenance", provenance)
            .add("external_sync_identifier", externalSyncItentifier)
            .add("invitability_level", invitibilityLevel)
            .add("member_viewability_level", memberViewabilityLevel)
            .add("created_at", "2015-01-05T16:08:27-08:00")
            .add("modified_at", "2015-01-05T16:08:27-08:00");

        stubFor(post(urlEqualTo("/groups"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(mockJSON.toString())));

        BoxGroup.Info groupInfo = BoxGroup.createGroup(api, name, provenance, externalSyncItentifier, description,
                invitibilityLevel, memberViewabilityLevel);
        assertThat(groupInfo.getID(), is(equalTo(mockJSON.get("id").asString())));
        assertThat(groupInfo.getName(), is(equalTo(mockJSON.get("name").asString())));
        assertThat(groupInfo.getDescription(), is(equalTo(mockJSON.get("description").asString())));
        assertThat(groupInfo.getProvenance(), is(equalTo(mockJSON.get("provenance").asString())));
        assertThat(groupInfo.getExternalSyncIdentifier(),
                is(equalTo(mockJSON.get("external_sync_identifier").asString())));
        assertThat(groupInfo.getInvitabilityLevel(), is(equalTo(mockJSON.get("invitability_level").asString())));
        assertThat(groupInfo.getMemberViewabilityLevel(),
                is(equalTo(mockJSON.get("member_viewability_level").asString())));
        assertThat(groupInfo.getCreatedAt(), is(equalTo(BoxDateFormat.parse(mockJSON.get("created_at").asString()))));
        assertThat(groupInfo.getModifiedAt(), is(equalTo(BoxDateFormat.parse(mockJSON.get("modified_at").asString()))));

        JsonObject expectedJSON = new JsonObject()
            .add("name", name)
            .add("description", description)
            .add("provenance", provenance)
            .add("external_sync_identifier", externalSyncItentifier)
            .add("invitability_level", invitibilityLevel)
            .add("member_viewability_level", memberViewabilityLevel);

        verify(postRequestedFor(urlEqualTo("/groups"))
            .withHeader("Content-Type", WireMock.equalTo("application/json"))
            .withRequestBody(equalToJson(expectedJSON.toString(), LENIENT)));
    }

    @Test
    @Category(UnitTest.class)
    public void deleteGroupSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        final String groupID = "1";
        final String groupURL = "/groups/" + groupID;

        stubFor(delete(urlEqualTo(groupURL))
            .willReturn(aResponse()
                .withStatus(204)));

        BoxGroup group = new BoxGroup(api, groupID);
        group.delete();

        verify(deleteRequestedFor(urlEqualTo(groupURL))
            .withRequestBody(WireMock.equalTo("")));
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
}
