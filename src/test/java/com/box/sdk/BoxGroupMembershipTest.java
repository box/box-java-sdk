package com.box.sdk;

import java.text.ParseException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.box.sdk.BoxGroupMembership.Role;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxGroupMembershipTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    @Category(UnitTest.class)
    public void getInfoSendsCorrectRequestAndParsesResponseCorrectly() throws ParseException {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        final String membershipID = "1";
        final String membershipsURL = "/group_memberships/" + membershipID;

        final String userID = "user id";
        final String userName = "user name";
        final String userLogin = "user login";
        final String groupID = "group id";
        final String groupName = "group name";
        final Role role = Role.MEMBER;
        final String roleName = "member";

        JsonObject mockJSON = new JsonObject()
            .add("type", "group_membership")
            .add("id", membershipID)
            .add("user", new JsonObject()
                .add("type", "user")
                .add("id", userID)
                .add("name", userName)
                .add("login", userLogin))
            .add("group", new JsonObject()
                .add("type", "group")
                .add("id", groupID)
                .add("name", groupName))
            .add("role", roleName)
            .add("created_at", "2015-01-05T16:08:27-08:00")
            .add("modified_at", "2015-01-05T16:08:27-08:00");

        stubFor(get(urlEqualTo(membershipsURL))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(mockJSON.toString())));

        BoxGroupMembership membership = new BoxGroupMembership(api, membershipID);
        BoxGroupMembership.Info membershipInfo = membership.getInfo();

        assertThat(membershipInfo.getID(), is(equalTo(membershipID)));
        assertThat(membershipInfo.getUser().getID(), is(equalTo(userID)));
        assertThat(membershipInfo.getUser().getName(), is(equalTo(userName)));
        assertThat(membershipInfo.getUser().getLogin(), is(equalTo(userLogin)));
        assertThat(membershipInfo.getGroup().getID(), is(equalTo(groupID)));
        assertThat(membershipInfo.getGroup().getName(), is(equalTo(groupName)));
        assertThat(membershipInfo.getRole(), is(equalTo(role)));
        assertThat(membershipInfo.getCreatedAt(),
                is(equalTo(BoxDateFormat.parse(mockJSON.get("created_at").asString()))));
        assertThat(membershipInfo.getModifiedAt(),
                is(equalTo(BoxDateFormat.parse(mockJSON.get("modified_at").asString()))));

        verify(getRequestedFor(urlEqualTo(membershipsURL))
                .withRequestBody(WireMock.equalTo("")));
    }

    @Test
    @Category(UnitTest.class)
    public void deleteMembershipSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        final String membershipID = "1";
        final String membershipsURL = "/group_memberships/" + membershipID;

        stubFor(delete(urlEqualTo(membershipsURL))
                .willReturn(aResponse()
                        .withStatus(204)));

        BoxGroupMembership membership = new BoxGroupMembership(api, membershipID);
        membership.delete();

        verify(deleteRequestedFor(urlEqualTo(membershipsURL))
                .withRequestBody(WireMock.equalTo("")));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoSucceeds() {
        final String groupName = "[getGroupMembershipInfoSucceeds] Test Group";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.Role role = BoxGroupMembership.Role.MEMBER;

        BoxGroup.Info groupInfo = BoxGroup.createGroup(api, groupName);
        BoxUser.Info userInfo = user.getInfo();
        BoxGroup group = groupInfo.getResource();

        BoxGroupMembership membership = group.addMembership(user, role).getResource();
        BoxGroupMembership.Info membershipInfo = membership.getInfo();

        assertThat(membershipInfo.getUser().getID(), is(equalTo(user.getID())));
        assertThat(membershipInfo.getUser().getName(), is(equalTo(userInfo.getName())));
        assertThat(membershipInfo.getUser().getLogin(), is(equalTo(userInfo.getLogin())));
        assertThat(membershipInfo.getGroup().getID(), is(equalTo(group.getID())));
        assertThat(membershipInfo.getGroup().getName(), is(equalTo(groupInfo.getName())));
        assertThat(membershipInfo.getRole(), is(equalTo(role)));

        group.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[updateGroupMembershipInfoSucceeds] Test Group";
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.Role originalRole = BoxGroupMembership.Role.MEMBER;
        BoxGroupMembership.Role newRole = BoxGroupMembership.Role.ADMIN;

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();

        BoxGroupMembership.Info membershipInfo = group.addMembership(user, originalRole);

        assertThat(membershipInfo.getRole(), is(equalTo(originalRole)));

        BoxGroupMembership membership = membershipInfo.getResource();
        membershipInfo.setRole(newRole);
        membership.updateInfo(membershipInfo);

        assertThat(membershipInfo.getRole(), is(equalTo(newRole)));

        group.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[deleteGroupMembershipSucceeds] Test Group";
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.Role originalRole = BoxGroupMembership.Role.MEMBER;

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();

        BoxGroupMembership.Info membershipInfo = group.addMembership(user, originalRole);
        BoxGroupMembership membership = membershipInfo.getResource();
        membership.delete();

        group.delete();
    }
}
