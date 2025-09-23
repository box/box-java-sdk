package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.CleanupTools.deleteGroup;
import static com.box.sdk.CleanupTools.removeAllGroups;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.internal.utils.CollectionUtils.createListFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** {@link BoxGroup} related integration tests. */
public class BoxGroupIT {

  @BeforeClass
  public static void setup() {
    setupUniqeFolder();
  }

  @AfterClass
  public static void afterClass() {
    removeUniqueFolder();
  }

  @Before
  public void cleanup() {
    removeAllGroups();
  }

  @Test
  public void createAndDeleteGroupSucceeds() {
    final String groupName = "[createAndDeleteGroupSucceeds] Test Group";
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxGroup.Info createdGroupInfo = BoxGroup.createGroup(api, groupName);

    assertThat(createdGroupInfo.getName(), equalTo(groupName));

    createdGroupInfo.getResource().delete();
    Iterable<BoxGroup.Info> groups = BoxGroup.getAllGroupsByName(api, groupName);
    assertThat(createListFrom(groups), Matchers.hasSize(0));
  }

  @Test
  public void addMembershipSucceedsAndGetMembershipsHasCorrectMemberships() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String groupName = "[addMembershipSucceedsAndGetMembershipsHasCorrectMemberships] Test Group";
    BoxUser user = BoxUser.getCurrentUser(api);
    BoxGroupMembership.GroupRole groupRole = BoxGroupMembership.GroupRole.ADMIN;

    BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();
    BoxGroupMembership.Info membershipInfo = group.addMembership(user, groupRole);
    String membershipID = membershipInfo.getID();

    assertThat(membershipInfo.getUser().getID(), is(equalTo(user.getID())));
    assertThat(membershipInfo.getGroup().getID(), is(equalTo(group.getID())));
    assertThat(membershipInfo.getGroupRole(), is(equalTo(groupRole)));

    Collection<BoxGroupMembership.Info> memberships = group.getMemberships();

    assertThat(memberships, hasSize(1));
    assertThat(
        memberships,
        hasItem(Matchers.<BoxGroupMembership.Info>hasProperty("ID", equalTo(membershipID))));

    group.delete();
  }

  @Test
  public void getInfoSucceeds() {
    final String groupName = "[getInfoSucceeds] Test Group";
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxGroup createdGroup = null;
    try {
      createdGroup = BoxGroup.createGroup(api, groupName).getResource();
      BoxGroup.Info createdGroupInfo = createdGroup.getInfo();

      assertThat(createdGroupInfo.getName(), equalTo(groupName));
    } finally {
      deleteGroup(createdGroup);
    }
  }

  @Test
  public void getCollaborationsSucceedsAndHandlesResponseCorrectly() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String groupName = "[getCollaborationsSucceedsAndHandlesResponseCorrectly] Test Group";

    BoxGroup group = null;
    BoxFolder folder = null;

    String folderName = "[getCollaborationsSucceedsAndHandlesResponseCorrectly] Test Folder";

    try {
      group = BoxGroup.createGroup(api, groupName).getResource();
      BoxCollaborator collabGroup = new BoxGroup(api, group.getID());
      folder = getUniqueFolder(api).createFolder(folderName).getResource();

      BoxCollaboration.Info collabInfo =
          folder.collaborate(collabGroup, BoxCollaboration.Role.EDITOR);

      Collection<BoxCollaboration.Info> collaborations = group.getCollaborations();

      assertThat(collaborations, hasSize(1));
      assertThat(
          collaborations,
          hasItem(Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabInfo.getID()))));
    } finally {
      deleteGroup(group);
      deleteFolder(folder);
    }
  }

  @Test
  public void getAllGroupsByNameSearchesCorrectly() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String groupName = "getAllGroupsByNameSearchesCorrectly";
    BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();
    try {
      // Searching groups requires few seconds delay.
      Thread.sleep(5000);
    } catch (InterruptedException ie) {
      // Do nothing
    }

    try {
      Iterator<BoxGroup.Info> iterator = BoxGroup.getAllGroupsByName(api, groupName).iterator();
      boolean matchingGroup;
      do {
        BoxGroup.Info groupInfo = iterator.next();
        matchingGroup = groupName.equals(groupInfo.getName());
      } while (!matchingGroup && iterator.hasNext());
      assertTrue("Group was not found", matchingGroup);
    } finally {
      deleteGroup(group);
    }
  }

  @Test
  public void getAllGroupsByNameWithFieldsOptionSearchesCorrectly() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String groupName = "getAllGroupsByNameWithFieldsOptionSearchesCorrectly";
    String groupDescription = "This is Test Group";
    BoxGroup group =
        BoxGroup.createGroup(api, groupName, null, null, groupDescription, null, null)
            .getResource();
    try {
      // Searching groups requires few seconds delay.
      Thread.sleep(5000);
    } catch (InterruptedException ie) {
      // Do nothing
    }
    try {
      Iterator<BoxGroup.Info> iterator =
          BoxGroup.getAllGroupsByName(api, groupName, "description").iterator();
      boolean matchingGroup;
      do {
        BoxGroup.Info groupInfo = iterator.next();
        matchingGroup =
            groupInfo.getName() == null && groupDescription.equals(groupInfo.getDescription());
      } while (!matchingGroup && iterator.hasNext());
      assertTrue("Group was not found", matchingGroup);
    } finally {
      deleteGroup(group);
    }
  }
}
