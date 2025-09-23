package com.box.sdkgen.collaborationallowlistexempttargets;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.collaborationallowlistexempttargets.CreateCollaborationWhitelistExemptTargetRequestBody;
import com.box.sdkgen.managers.collaborationallowlistexempttargets.CreateCollaborationWhitelistExemptTargetRequestBodyUserField;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.schemas.collaborationallowlistexempttarget.CollaborationAllowlistExemptTarget;
import com.box.sdkgen.schemas.collaborationallowlistexempttargets.CollaborationAllowlistExemptTargets;
import com.box.sdkgen.schemas.userfull.UserFull;
import org.junit.jupiter.api.Test;

public class CollaborationAllowlistExemptTargetsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCollaborationAllowlistExemptTargets() {
    CollaborationAllowlistExemptTargets exemptTargets =
        client.getCollaborationAllowlistExemptTargets().getCollaborationWhitelistExemptTargets();
    assert exemptTargets.getEntries().size() >= 0;
    UserFull user =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(getUuid())
                    .login(String.join("", getUuid(), "@boxdemo.com"))
                    .isPlatformAccessOnly(true)
                    .build());
    CollaborationAllowlistExemptTarget newExemptTarget =
        client
            .getCollaborationAllowlistExemptTargets()
            .createCollaborationWhitelistExemptTarget(
                new CreateCollaborationWhitelistExemptTargetRequestBody(
                    new CreateCollaborationWhitelistExemptTargetRequestBodyUserField(
                        user.getId())));
    assert convertToString(newExemptTarget.getType())
        .equals("collaboration_whitelist_exempt_target");
    assert newExemptTarget.getUser().getId().equals(user.getId());
    CollaborationAllowlistExemptTarget exemptTarget =
        client
            .getCollaborationAllowlistExemptTargets()
            .getCollaborationWhitelistExemptTargetById(newExemptTarget.getId());
    assert exemptTarget.getId().equals(newExemptTarget.getId());
    assert exemptTarget.getUser().getId().equals(user.getId());
    client
        .getCollaborationAllowlistExemptTargets()
        .deleteCollaborationWhitelistExemptTargetById(exemptTarget.getId());
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getCollaborationAllowlistExemptTargets()
                .getCollaborationWhitelistExemptTargetById(exemptTarget.getId()));
    client.getUsers().deleteUserById(user.getId());
  }
}
