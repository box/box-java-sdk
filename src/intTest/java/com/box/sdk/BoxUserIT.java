package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.Retry.retry;
import static com.box.sdk.internal.utils.CollectionUtils.createListFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
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
        BoxAPIConnection api = jwtApiForServiceAccount();
        for (BoxUser.Info user : BoxUser.getAllEnterpriseUsers(api, NEW_USER_LOGIN)) {
            user.getResource().delete(false, false);
        }
    }

    @Test(timeout = 10000)
    public void getCurrentUserInfoIsCorrect() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxUser.Info info = user.getInfo(BoxUser.ALL_FIELDS);

        assertNotNull(info.getEnterprise().getID());
        assertNotNull(info.getEnterprise().getName());
    }

    @Test(timeout = 10000)
    public void createAndDeleteEnterpriseUserSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
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

    @Test(timeout = 10000)
    public void getMembershipsHasCorrectMemberships() {
        BoxAPIConnection api = jwtApiForServiceAccount();
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

    @Test(timeout = 10000)
    public void updateInfoSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
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

    @Test(timeout = 60000)
    public void uploadGetAndDeleteAvatar() throws IOException, InterruptedException {
        // given
        BoxAPIConnection api = jwtApiForServiceAccount();
        String filePath = getSampleFilePath("red_100x100.png");
        BoxUser user = new BoxUser(api, TestConfig.getUserId());

        // when
        AvatarUploadResponse response = user.uploadAvatar(new File(filePath));

        // then
        assertThat(response.getSmall(), not(emptyOrNullString()));
        assertThat(response.getLarge(), not(emptyOrNullString()));
        assertThat(response.getPreview(), not(emptyOrNullString()));

        retry(() -> {
            InputStream uploadedAvatar = user.getAvatar();
            try {
                assertThat(uploadedAvatar.available(), greaterThan(0));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 5, 1000);

        // when
        user.deleteAvatar();

        // then
        try {
            user.getAvatar();
        } catch (BoxAPIException e) {
            assertThat(e.getResponseCode(), is(404));
        }
    }

    private String getSampleFilePath(String fileName) throws UnsupportedEncodingException {
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        return URLDecoder.decode(fileURL.getFile(), "utf-8");
    }
}
