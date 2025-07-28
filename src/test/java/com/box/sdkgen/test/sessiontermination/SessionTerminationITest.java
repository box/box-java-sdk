package com.box.sdkgen.test.sessiontermination;

import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.groups.CreateGroupRequestBody;
import com.box.sdkgen.managers.sessiontermination.TerminateGroupsSessionsRequestBody;
import com.box.sdkgen.managers.sessiontermination.TerminateUsersSessionsRequestBody;
import com.box.sdkgen.schemas.groupfull.GroupFull;
import com.box.sdkgen.schemas.sessionterminationmessage.SessionTerminationMessage;
import com.box.sdkgen.schemas.userfull.UserFull;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SessionTerminationITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testSessionTerminationUser() {
    BoxClient adminClient = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    UserFull user = adminClient.getUsers().getUserMe();
    SessionTerminationMessage result =
        client
            .getSessionTermination()
            .terminateUsersSessions(
                new TerminateUsersSessionsRequestBody(
                    Arrays.asList(getEnvVar("USER_ID")), Arrays.asList(user.getLogin())));
    assert result
        .getMessage()
        .equals("Request is successful, please check the admin events for the status of the job");
  }

  @Test
  public void testSessionTerminationGroup() {
    String groupName = getUuid();
    GroupFull group = client.getGroups().createGroup(new CreateGroupRequestBody(groupName));
    SessionTerminationMessage result =
        client
            .getSessionTermination()
            .terminateGroupsSessions(
                new TerminateGroupsSessionsRequestBody(Arrays.asList(group.getId())));
    assert result
        .getMessage()
        .equals("Request is successful, please check the admin events for the status of the job");
    client.getGroups().deleteGroupById(group.getId());
  }
}
