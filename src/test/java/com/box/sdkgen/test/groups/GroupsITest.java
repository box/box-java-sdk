package com.box.sdkgen.test.groups;

import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.groups.CreateGroupRequestBody;
import com.box.sdkgen.managers.groups.GetGroupByIdQueryParams;
import com.box.sdkgen.managers.groups.UpdateGroupByIdRequestBody;
import com.box.sdkgen.schemas.groupfull.GroupFull;
import com.box.sdkgen.schemas.groups.Groups;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class GroupsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testGetGroups() {
    Groups groups = client.getGroups().getGroups();
    assert groups.getTotalCount() >= 0;
  }

  @Test
  public void testCreateGetDeleteGroup() {
    String groupName = getUuid();
    String groupDescription = "Group description";
    GroupFull group =
        client
            .getGroups()
            .createGroup(
                new CreateGroupRequestBody.Builder(groupName)
                    .description(groupDescription)
                    .build());
    assert group.getName().equals(groupName);
    GroupFull groupById =
        client
            .getGroups()
            .getGroupById(
                group.getId(),
                new GetGroupByIdQueryParams.Builder()
                    .fields(Arrays.asList("id", "name", "description", "group_type"))
                    .build());
    assert groupById.getId().equals(group.getId());
    assert groupById.getDescription().equals(groupDescription);
    String updatedGroupName = getUuid();
    GroupFull updatedGroup =
        client
            .getGroups()
            .updateGroupById(
                group.getId(),
                new UpdateGroupByIdRequestBody.Builder().name(updatedGroupName).build());
    assert updatedGroup.getName().equals(updatedGroupName);
    client.getGroups().deleteGroupById(group.getId());
    assertThrows(RuntimeException.class, () -> client.getGroups().getGroupById(group.getId()));
  }
}
