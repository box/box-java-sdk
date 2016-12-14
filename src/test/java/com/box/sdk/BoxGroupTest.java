package com.box.sdk;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONCompareMode.LENIENT;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 * {@link BoxGroup} related tests.
 */
public class BoxGroupTest {

    /**
     * Wiremock
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    /**
     * Unit test for {@link BoxGroup#getInfo(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequestWithParams() {
        final JsonObject fakeJSONResponse = new JsonObject()
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/groups/0?fields=name%2Ccreated_at", request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxGroup group = new BoxGroup(api, "0");
        group.getInfo("name", "created_at");
    }

    /**
     * Unit test for {@link BoxGroup#getInfo()}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        final JsonObject fakeJSONResponse = new JsonObject()
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/groups/0", request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxGroup group = new BoxGroup(api, "0");
        group.getInfo();
    }

    /**
     * Unit test for {@link BoxGroup#getInfo()}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "119720";
        final String name = "Box Employees";
        final Date createdAt = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"group\",\n"
                + "    \"id\": \"119720\",\n"
                + "    \"name\": \"Box Employees\",\n"
                + "    \"created_at\": \"2013-05-16T15:27:57-07:00\",\n"
                + "    \"modified_at\": \"2013-05-16T15:27:57-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxGroup group = new BoxGroup(api, id);
        BoxGroup.Info info = group.getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(name, info.getName());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
    }

    /**
     * Unit test for {@link BoxGroup#addMembership(BoxUser)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testAddMembershipSendsCorrectJson() {
        final String userID = "1992432";
        final String groupID = "1992433";

        final JsonObject fakeJSONResponse = new JsonObject().add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/group_memberships", request.getUrl().toString());
                Assert.assertEquals(userID, json.get("user").asObject().get("id").asString());
                Assert.assertEquals(groupID, json.get("group").asObject().get("id").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxGroup group = new BoxGroup(api, groupID);
        group.addMembership(new BoxUser(api, userID));
    }

    /**
     * Unit test for {@link BoxGroup#addMembership(BoxUser)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testAddMembershipParseAllFieldscorrectly() {
        final String id = "1560354";
        final String userID = "13130406";
        final String userName = "Alison Wonderland";
        final String userLogin = "alice@gmail.com";
        final String groupID = "119720";
        final String groupName = "family";
        final BoxGroupMembership.Role role = BoxGroupMembership.Role.MEMBER;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "            \"type\": \"group_membership\",\n"
                + "            \"id\": \"1560354\",\n"
                + "            \"user\": {\n"
                + "                \"type\": \"user\",\n"
                + "                \"id\": \"13130406\",\n"
                + "                \"name\": \"Alison Wonderland\",\n"
                + "                \"login\": \"alice@gmail.com\"\n"
                + "            },\n"
                + "            \"group\": {\n"
                + "                \"type\": \"group\",\n"
                + "                \"id\": \"119720\",\n"
                + "                \"name\": \"family\"\n"
                + "            },\n"
                + "            \"role\": \"member\"\n"
                + "        }");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
        BoxGroup group = new BoxGroup(api, id);
        BoxGroupMembership.Info info = group.addMembership(new BoxUser(api, "0"));
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(userID, info.getUser().getID());
        Assert.assertEquals(userName, info.getUser().getName());
        Assert.assertEquals(userLogin, info.getUser().getLogin());
        Assert.assertEquals(groupID, info.getGroup().getID());
        Assert.assertEquals(groupName, info.getGroup().getName());
        Assert.assertEquals(role, info.getRole());
    }

    /**
     * Unit test for {@link BoxGroup#getAllMemberships(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllMembershipsParseAllFieldsCorrectly() {
        final String firstMembershipID = "1560354";
        final String firstMembershipUserID = "13130906";
        final String firstMembershipUserName = "Alice";
        final String firstMembershipUserLogin = "alice@gmail.com";
        final String groupID = "119720";
        final String groupName = "family";
        final BoxGroupMembership.Role role = BoxGroupMembership.Role.MEMBER;
        final String secondMembershipID = "1560356";
        final String secondMembershipUserID = "192633962";
        final String secondMembershipUserName = "rabbit";
        final String secondMembershipUserLogin = "rabbit@gmail.com";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"total_count\": 2,\n"
                + "    \"entries\": [\n"
                + "        {\n"
                + "            \"type\": \"group_membership\",\n"
                + "            \"id\": \"1560354\",\n"
                + "            \"user\": {\n"
                + "                \"type\": \"user\",\n"
                + "                \"id\": \"13130906\",\n"
                + "                \"name\": \"Alice\",\n"
                + "                \"login\": \"alice@gmail.com\"\n"
                + "            },\n"
                + "            \"group\": {\n"
                + "                \"type\": \"group\",\n"
                + "                \"id\": \"119720\",\n"
                + "                \"name\": \"family\"\n"
                + "            },\n"
                + "            \"role\": \"member\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"type\": \"group_membership\",\n"
                + "            \"id\": \"1560356\",\n"
                + "            \"user\": {\n"
                + "                \"type\": \"user\",\n"
                + "                \"id\": \"192633962\",\n"
                + "                \"name\": \"rabbit\",\n"
                + "                \"login\": \"rabbit@gmail.com\"\n"
                + "            },\n"
                + "            \"group\": {\n"
                + "                \"type\": \"group\",\n"
                + "                \"id\": \"119720\",\n"
                + "                \"name\": \"family\"\n"
                + "            },\n"
                + "            \"role\": \"member\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"offset\": 0,\n"
                + "    \"limit\": 1000\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
        BoxGroup group = new BoxGroup(api, "0");
        Iterator<BoxGroupMembership.Info> iterator = group.getAllMemberships().iterator();
        BoxGroupMembership.Info info = iterator.next();
        Assert.assertEquals(firstMembershipID, info.getID());
        Assert.assertEquals(firstMembershipUserID, info.getUser().getID());
        Assert.assertEquals(firstMembershipUserName, info.getUser().getName());
        Assert.assertEquals(firstMembershipUserLogin, info.getUser().getLogin());
        Assert.assertEquals(groupID, info.getGroup().getID());
        Assert.assertEquals(groupName, info.getGroup().getName());
        Assert.assertEquals(role, info.getRole());
        info = iterator.next();
        Assert.assertEquals(secondMembershipID, info.getID());
        Assert.assertEquals(secondMembershipUserID, info.getUser().getID());
        Assert.assertEquals(secondMembershipUserName, info.getUser().getName());
        Assert.assertEquals(secondMembershipUserLogin, info.getUser().getLogin());
        Assert.assertEquals(groupID, info.getGroup().getID());
        Assert.assertEquals(groupName, info.getGroup().getName());
        Assert.assertEquals(role, info.getRole());
        Assert.assertEquals(false, iterator.hasNext());
    }

    /**
     * Unit test for {@link BoxGroup#getAllMemberships(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllMembershipsSendsCorrectRequest() {
        final JsonObject fakeJSONResponse = new JsonObject()
                .add("total_count", 0)
                .add("offset", 0)
                .add("entries", new JsonArray());

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/groups/0/memberships?fields=user%2Cgroup&limit=1000&offset=0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxGroup group = new BoxGroup(api, "0");
        Iterator<BoxGroupMembership.Info> iterator = group.getAllMemberships("user", "group").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxGroup#getAllGroups(BoxAPIConnection, String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllGroupsSendsCorrectRequest() {
        final JsonObject fakeJSONResponse = new JsonObject()
                .add("total_count", 0)
                .add("offset", 0)
                .add("entries", new JsonArray());

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/groups?fields=name%2Ccreated_at&limit=1000&offset=0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        Iterator<BoxGroup.Info> iterator = BoxGroup.getAllGroups(api, "name", "created_at").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxGroup#updateInfo(BoxGroup.Info)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateInfoSendsCorrectJSON() {
        final String name = "family";
        final String provenance = "non-empty provenance";
        final String externalSyncIdentifier = "non-empty identifier";
        final String description = "non-empty description";
        final String invitabilityLevel = "non-empty level";
        final String memberViewabilityLevel = "another non-empty level";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/groups/0", request.getUrl().toString());
                Assert.assertEquals(name, json.get("name").asString());
                Assert.assertEquals(provenance, json.get("provenance").asString());
                Assert.assertEquals(externalSyncIdentifier, json.get("external_sync_identifier").asString());
                Assert.assertEquals(description, json.get("description").asString());
                Assert.assertEquals(invitabilityLevel, json.get("invitability_level").asString());
                Assert.assertEquals(memberViewabilityLevel, json.get("member_viewability_level").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        BoxGroup group = new BoxGroup(api, "0");
        BoxGroup.Info info = group.new Info();
        info.addPendingChange("name", name);
        info.addPendingChange("provenance", provenance);
        info.addPendingChange("external_sync_identifier", externalSyncIdentifier);
        info.addPendingChange("description", description);
        info.addPendingChange("invitability_level", invitabilityLevel);
        info.addPendingChange("member_viewability_level", memberViewabilityLevel);
        group.updateInfo(info);
    }

    /**
     * Unit test for {@link BoxGroup#updateInfo(BoxGroup.Info)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "119720";
        final String name = "family";
        final Date createdAt = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2013-05-17T15:27:57-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"group\",\n"
                + "    \"id\": \"119720\",\n"
                + "    \"name\": \"family\",\n"
                + "    \"created_at\": \"2013-05-16T15:27:57-07:00\",\n"
                + "    \"modified_at\": \"2013-05-17T15:27:57-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxGroup group = new BoxGroup(api, id);
        BoxGroup.Info info = group.new Info();
        info.addPendingChange("name", name);
        group.updateInfo(info);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(name, info.getName());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
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

    /**
     * Unit test for {@link BoxGroup#createGroup(BoxAPIConnection, String, String, String, String, String, String)}.
     */
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

    /**
     * Unit test for {@link BoxGroup#delete()}.
     */
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
