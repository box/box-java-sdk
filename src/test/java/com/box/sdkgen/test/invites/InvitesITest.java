package com.box.sdkgen.test.invites;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.invites.CreateInviteRequestBody;
import com.box.sdkgen.managers.invites.CreateInviteRequestBodyActionableByField;
import com.box.sdkgen.managers.invites.CreateInviteRequestBodyEnterpriseField;
import com.box.sdkgen.managers.users.GetUserMeQueryParams;
import com.box.sdkgen.schemas.invite.Invite;
import com.box.sdkgen.schemas.userfull.UserFull;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class InvitesITest {

  @Test
  public void testInvites() {
    String userId = getEnvVar("USER_ID");
    BoxClient client = getDefaultClientWithUserSubject(userId);
    UserFull currentUser =
        client
            .getUsers()
            .getUserMe(
                new GetUserMeQueryParams.Builder().fields(Arrays.asList("enterprise")).build());
    String email = getEnvVar("BOX_EXTERNAL_USER_EMAIL");
    Invite invitation =
        client
            .getInvites()
            .createInvite(
                new CreateInviteRequestBody(
                    new CreateInviteRequestBodyEnterpriseField(currentUser.getEnterprise().getId()),
                    new CreateInviteRequestBodyActionableByField.Builder().login(email).build()));
    assert convertToString(invitation.getType()).equals("invite");
    assert invitation.getInvitedTo().getId().equals(currentUser.getEnterprise().getId());
    assert invitation.getActionableBy().getLogin().equals(email);
    Invite getInvitation = client.getInvites().getInviteById(invitation.getId());
    assert getInvitation.getId().equals(invitation.getId());
  }
}
