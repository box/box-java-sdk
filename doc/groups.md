Groups
======

Groups are sets of users that can be used in collaborations.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get All Groups](#get-all-groups)
- [Create a Group](#create-a-group)
- [Get Information About a Group](#get-information-about-a-group)
- [Update a Group](#update-a-group)
- [Delete a Group](#delete-a-group)
- [Get first page of a Group's Collaborations](#get-a-groups-collaborations)
- [Get all of a Group's Collaborations](#get-all-a-groups-collaborations)
- [Create Membership](#create-membership)
- [Get Membership](#get-membership)
- [Update Membership](#update-membership)
- [Delete Membership](#delete-membership)
- [Get Memberships for Group](#get-memberships-for-group)
- [Get Memberships for User](#get-memberships-for-user)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get All Groups
--------------

Calling the static [`getAllGroups(BoxAPIConnection api)`][get-all-groups] will
return an iterable that will page through all of the user's groups.

<!-- sample get_groups -->
```java
Iterable<BoxGroup.Info> groups = BoxGroup.getAllGroups(api);
for (BoxGroup.Info groupInfo : groups) {
    // Do something with the group.
}
```

[get-all-groups]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#getAllGroups-com.box.sdk.BoxAPIConnection-

Create a Group
--------------

The static [`createGroup(BoxAPIConnection api, String name)`][create-group] method will
let you create a new group with a specified name.

<!-- sample post_groups -->
```java
BoxGroup.Info groupInfo = BoxGroup.createGroup(api, "My Group");
```

[create-group]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#createGroup-com.box.sdk.BoxAPIConnection-java.lang.String-

Get Information About a Group
-----------------------------

To look up the information about a group by the group's ID, instantiate the [`BoxGroup`][group-object]
object with the group ID and then call [`getInfo()`][get-info] on the group.  You can optionally call
[`getInfo(String... fields)`][get-info-fields] to specify the list of fields to retrieve for the group,
which can result in reduced payload size.

<!-- sample get_groups_id -->
```java
String groupID = "92875";
BoxGroup.Info groupInfo = new BoxGroup(api, groupID).getInfo();
```

[group-object]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html
[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#getInfo--
[get-info-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#getInfo-java.lang.String...-

Update a Group
--------------

To update a group, call [`updateInfo(BoxGroup.Info fieldsToUpdate)`][update-group] method.

<!-- sample put_groups_id -->
```java
BoxGroup group = new BoxGroup(api, id);
BoxGroup.Info groupInfo = group.getInfo();
groupInfo.addPendingChange("name", "New name for My Group");
group.updateInfo(groupInfo);
```

[update-group]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#updateInfo-com.box.sdk.BoxGroup.Info-


Delete a Group
--------------

A group can be deleted by calling the [`delete()`][delete] method.

<!-- sample delete_groups_id -->
```java
BoxGroup group = new BoxGroup(api, "id");
group.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#delete--

Get first page of a Group's Collaborations
---------------------------

The first page of a group's collaborations can be retrieved by calling the [`getCollaborations()`][get-collaborations] method.

<!-- sample get_groups_id_collaborations -->
```java
BoxGroup group = new BoxGroup(api, "id");
Collection<BoxCollaboration.Info> collaborations = group.getCollaborations();
```

[get-collaborations]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#getCollaborations--

Get all of a Group's Collaborations
---------------------------

All of a group's collaborations can be retrieved by calling the [`getAllCollaborations(String... fields)`][get-all-collaborations] method.
An iterable is returned to allow a user to iterate until the last collaboration.

```java
BoxGroup group = new BoxGroup(api, "id");
Iterable<BoxCollaboration.Info> collaborations = group.getAllCollaborations();
```

[get-all-collaborations]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#getAllCollaborations--

Create Membership
---------------

Membership for the group can be created by calling the
[`addMembership(BoxUser user)`][add-membership] and
[`addMembership(BoxUser user, BoxGroupMembership.Role role)`][add-membership2] methods.

<!-- sample post_group_memberships -->
```java
BoxGroup group = new BoxGroup(api, "groupID");
BoxUser user = new BoxUser(api, "userID");
BoxGroupMembership.Info groupMembershipInfo = group.addMembership(user);
```

[add-membership]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#addMembership-com.box.sdk.BoxUser-
[add-membership2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#addMembership-com.box.sdk.BoxUser-com.box.sdk.BoxGroupMembership.Role-

Get Membership
---------------

A groups membership can be retrieved by calling the [`BoxGroupMembership.getInfo()`][get-membership] method.

<!-- sample get_group_memberships_id -->
```java
BoxGroupMembership membership = new BoxGroupMembership(api, id);
BoxGroupMembership.Info groupMembershipInfo = membership.getInfo();
```

[get-membership]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroupMembership.html#getInfo--

Update Membership
---------------

A groups membership can be updated by calling the
[`BoxGroupMembership.updateInfo(BoxGroupMembership.Info fieldsToUpdate)`][update-membership] method.

<!-- sample put_group_memberships_id -->
```java
BoxGroupMembership membership = new BoxGroupMembership(api, id);
BoxGroupMembership.Info info = membership.new Info();
info.addPendingChange("role", role);
membership.updateInfo(info);
```

[update-membership]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroupMembership.html#updateInfo-com.box.sdk.BoxGroupMembership.Info-

Delete Membership
---------------

A group can be deleted by calling the [`BoxGroupMembership.delete()`][delete-membership] method.

<!-- sample delete_group_memberships_id -->
```java
BoxGroupMembership membership = new BoxGroupMembership(api, id);
membership.delete();
```

[delete-membership]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroupMembership.html#delete--

Get Memberships for Group
---------------

Calling the [`getAllMemberships(String... fields)`][get-memberships-for-group] will return an iterable that will page through all of the group's memberships.
Optional parameters can be used to retrieve specific fields of the Group Membership object.

<!-- sample get_groups_id_memberships -->
```java
BoxGroup group = new BoxGroup(api, id);
Iterable<BoxGroupMembership.Info> memberships = group.getAllMemberships();
for (BoxGroupMembership.Info membershipInfo : memberships) {
    // Do something with the membership.
}
```

[get-memberships-for-group]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#getAllMemberships-java.lang.String...-

Get Memberships for User
---------------

Calling the [`BoxUser.getAllMemberships(String... fields)`][get-memberships-for-user] will return an iterable that will page through all of the user's memberships.
Optional parameters can be used to retrieve specific fields of the Group Membership object.

<!-- sample get_users_id_memberships -->
```java
BoxUser user = new BoxUser(api, id);
Iterable<BoxGroupMembership.Info> memberships = user.getAllMemberships();
for (BoxGroupMembership.Info membershipInfo : memberships) {
    // Do something with the membership.
}
```

[get-memberships-for-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllMemberships-java.lang.String...-
