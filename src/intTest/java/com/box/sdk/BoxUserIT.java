package com.box.sdk;

import static com.box.sdk.internal.utils.CollectionUtils.createListFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Collection;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link BoxUser} related tests.
 */

public class BoxUserIT {

    private static final String NEW_USER_LOGIN = "login2@boz.com";
    private static final String NEW_USER_NAME = "non-empty name";

    @BeforeClass
    public static void cleanup() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        for (BoxUser.Info user : BoxUser.getAllEnterpriseUsers(api, NEW_USER_LOGIN)) {
            user.getResource().delete(false, false);
        }
    }

    @Test
    public void getCurrentUserInfoIsCorrect() {
        final String expectedName = "Java SDK";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxUser.Info info = user.getInfo(BoxUser.ALL_FIELDS);

        assertThat(info.getName(), equalTo(expectedName));
        assertNotNull(info.getEnterprise().getID());
    }

    @Test
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
        assertThat(createListFrom(users), Matchers.hasSize(0));
    }

    @Test
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
}
