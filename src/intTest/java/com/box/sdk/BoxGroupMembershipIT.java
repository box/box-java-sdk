package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.box.sdk.BoxGroupMembership.Permission;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.junit.Ignore;
import org.junit.Test;

/**
 * {@link BoxGroupMembership} related integration tests.
 */
@Ignore
public class BoxGroupMembershipIT {

    @Test
    public void getInfoSucceeds() {
        final String groupName = "[getGroupMembershipInfoSucceeds] Test Group "
            + Calendar.getInstance().getTimeInMillis();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.GroupRole role = BoxGroupMembership.GroupRole.MEMBER;

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
        assertThat(membershipInfo.getGroupRole(), is(equalTo(role)));

        group.delete();
    }

    @Test
    public void updateInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[updateGroupMembershipInfoSucceeds] Test Group";
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.GroupRole originalRole = BoxGroupMembership.GroupRole.MEMBER;
        BoxGroupMembership.GroupRole newRole = BoxGroupMembership.GroupRole.ADMIN;

        Map<Permission, Boolean> configurablePermissions = new HashMap<>();
        configurablePermissions.put(Permission.CAN_CREATE_ACCOUNTS, false);

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();

        BoxGroupMembership.Info membershipInfo = group.addMembership(user, originalRole);

        assertThat(membershipInfo.getGroupRole(), is(equalTo(originalRole)));

        BoxGroupMembership membership = membershipInfo.getResource();
        membershipInfo.setGroupRole(newRole);
        membershipInfo.setConfigurablePermissions(configurablePermissions);
        membership.updateInfo(membershipInfo);

        assertThat(membershipInfo.getGroupRole(), is(equalTo(newRole)));
        assertThat(membershipInfo.getConfigurablePermissions().get(Permission.CAN_CREATE_ACCOUNTS),
            is(equalTo(false)));

        group.delete();
    }

    @Test
    public void addWithConfigurablePermissionsSucceds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[addWithConfigurablePermissionsSucceeds] Test Group";
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.GroupRole role = BoxGroupMembership.GroupRole.ADMIN;

        Map<Permission, Boolean> configurablePermissions = new HashMap<>();
        configurablePermissions.put(Permission.CAN_CREATE_ACCOUNTS, false);

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();

        BoxGroupMembership.Info membershipInfo = group.addMembership(user, role, configurablePermissions);

        assertThat(membershipInfo.getConfigurablePermissions().get(Permission.CAN_CREATE_ACCOUNTS),
            is(equalTo(false)));
        assertThat(membershipInfo.getConfigurablePermissions().get(Permission.CAN_EDIT_ACCOUNTS),
            is(equalTo(true)));

        group.delete();
    }

    @Test
    public void deleteSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[deleteGroupMembershipSucceeds] Test Group " + Calendar.getInstance().getTimeInMillis();
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.GroupRole originalRole = BoxGroupMembership.GroupRole.MEMBER;

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();

        BoxGroupMembership.Info membershipInfo = group.addMembership(user, originalRole);
        BoxGroupMembership membership = membershipInfo.getResource();
        membership.delete();

        group.delete();
    }
}
