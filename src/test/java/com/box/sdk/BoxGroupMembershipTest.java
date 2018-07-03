package com.box.sdk;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.box.sdk.BoxGroupMembership.Role;
import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxGroupMembership} related tests.
 */
public class BoxGroupMembershipTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    /**
     * Unit test for {@link BoxGroupMembership#updateInfo(BoxGroupMembership.Info)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateInfoSendsCorrectJson() {
        final String role = "submaster";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/group_memberships/0", request.getUrl().toString());
                Assert.assertEquals(role, json.get("role").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        BoxGroupMembership membership = new BoxGroupMembership(api, "0");
        BoxGroupMembership.Info info = membership.new Info();
        info.addPendingChange("role", role);
        membership.updateInfo(info);
    }

    /**
     * Unit test for {@link BoxGroupMembership#updateInfo(BoxGroupMembership.Info)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "1560354";
        final String userID = "13130406";
        final String userName = "Alison Wonderland";
        final String userLogin = "alice@gmail.com";
        final String groupID = "119720";
        final String groupName = "family";
        final Role role = Role.SUBMASTER;
        final Date createdAt = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"group_membership\",\n"
                + "    \"id\": \"1560354\",\n"
                + "    \"user\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"13130406\",\n"
                + "        \"name\": \"Alison Wonderland\",\n"
                + "        \"login\": \"alice@gmail.com\"\n"
                + "    },\n"
                + "    \"group\": {\n"
                + "        \"type\": \"group\",\n"
                + "        \"id\": \"119720\",\n"
                + "        \"name\": \"family\"\n"
                + "    },\n"
                + "    \"role\": \"submaster\",\n"
                + "    \"created_at\": \"2013-05-16T15:27:57-07:00\",\n"
                + "    \"modified_at\": \"2013-05-16T15:27:57-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
        BoxGroupMembership membership = new BoxGroupMembership(api, id);
        BoxGroupMembership.Info info = membership.new Info();
        info.addPendingChange("role", "non-empty");
        membership.updateInfo(info);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(userID, info.getUser().getID());
        Assert.assertEquals(userName, info.getUser().getName());
        Assert.assertEquals(userLogin, info.getUser().getLogin());
        Assert.assertEquals(groupID, info.getGroup().getID());
        Assert.assertEquals(groupName, info.getGroup().getName());
        Assert.assertEquals(role, info.getRole());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoSucceeds() {
        final String groupName = "[getGroupMembershipInfoSucceeds] Test Group "
                + Calendar.getInstance().getTimeInMillis();
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
        String groupName = "[deleteGroupMembershipSucceeds] Test Group " + Calendar.getInstance().getTimeInMillis();
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.Role originalRole = BoxGroupMembership.Role.MEMBER;

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();

        BoxGroupMembership.Info membershipInfo = group.addMembership(user, originalRole);
        BoxGroupMembership membership = membershipInfo.getResource();
        membership.delete();

        group.delete();
    }
}
