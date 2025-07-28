package com.box.sdkgen.test.memberships;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.groups.CreateGroupRequestBody;
import com.box.sdkgen.managers.memberships.CreateGroupMembershipRequestBody;
import com.box.sdkgen.managers.memberships.CreateGroupMembershipRequestBodyGroupField;
import com.box.sdkgen.managers.memberships.CreateGroupMembershipRequestBodyUserField;
import com.box.sdkgen.managers.memberships.UpdateGroupMembershipByIdRequestBody;
import com.box.sdkgen.managers.memberships.UpdateGroupMembershipByIdRequestBodyRoleField;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.schemas.groupfull.GroupFull;
import com.box.sdkgen.schemas.groupmembership.GroupMembership;
import com.box.sdkgen.schemas.groupmemberships.GroupMemberships;
import com.box.sdkgen.schemas.userfull.UserFull;
import org.junit.jupiter.api.Test;

public class MembershipsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testMemberships() {
    UserFull user =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(getUuid())
                    .login(String.join("", getUuid(), "@boxdemo.com"))
                    .build());
    GroupMemberships userMemberships = client.getMemberships().getUserMemberships(user.getId());
    assert userMemberships.getTotalCount() == 0;
    GroupFull group = client.getGroups().createGroup(new CreateGroupRequestBody(getUuid()));
    GroupMemberships groupMemberships = client.getMemberships().getGroupMemberships(group.getId());
    assert groupMemberships.getTotalCount() == 0;
    GroupMembership groupMembership =
        client
            .getMemberships()
            .createGroupMembership(
                new CreateGroupMembershipRequestBody(
                    new CreateGroupMembershipRequestBodyUserField(user.getId()),
                    new CreateGroupMembershipRequestBodyGroupField(group.getId())));
    assert groupMembership.getUser().getId().equals(user.getId());
    assert groupMembership.getGroup().getId().equals(group.getId());
    assert convertToString(groupMembership.getRole()).equals("member");
    GroupMembership getGroupMembership =
        client.getMemberships().getGroupMembershipById(groupMembership.getId());
    assert getGroupMembership.getId().equals(groupMembership.getId());
    GroupMembership updatedGroupMembership =
        client
            .getMemberships()
            .updateGroupMembershipById(
                groupMembership.getId(),
                new UpdateGroupMembershipByIdRequestBody.Builder()
                    .role(UpdateGroupMembershipByIdRequestBodyRoleField.ADMIN)
                    .build());
    assert updatedGroupMembership.getId().equals(groupMembership.getId());
    assert convertToString(updatedGroupMembership.getRole()).equals("admin");
    client.getMemberships().deleteGroupMembershipById(groupMembership.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getMemberships().getGroupMembershipById(groupMembership.getId()));
    client.getGroups().deleteGroupById(group.getId());
    client.getUsers().deleteUserById(user.getId());
  }
}
