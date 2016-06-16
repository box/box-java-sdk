Groups
======

Groups are sets of users that can be used in collaborations.

* [Get All Groups](#get-all-groups)
* [Create a Group](#create-a-group)
* [Delete a Group](#delete-a-group)
* [Get a Groups collaborations](#get-a-groups-collaborations)

Get All Groups
--------------

Calling the static [`getAllGroups(BoxAPIConnection)`][get-all-groups] will
return an iterable that will page through all of the user's groups.

```java
Iterable<BoxGroup.Info> groups = BoxGroup.getAllGroups(BoxAPIConnection api);
for (BoxGroup.Info groupInfo : groups) {
    // Do something with the group.
}
```

[get-all-groups]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#getAllGroups(com.box.sdk.BoxAPIConnection)

Create a Group
--------------

The static [`createGroup(BoxAPIConnection, String)`][create-group] method will
let you create a new group with a specified name.

```java
BoxGroup.Info groupInfo = BoxGroup.createGroup(api, "My Group");
```

[create-group]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#createGroup(com.box.sdk.BoxAPIConnection,%20java.lang.String)

Delete a Group
--------------

A group can be deleted by calling the [`delete()`][delete] method.

```java
BoxGroup group = new BoxGroup(api, "id");
group.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#delete()

Get a Groups collaborations
---------------------------

A groups collaborations can be retrieved by calling the [`getCollaborations()`[get-collaborations] method.

```java
BoxGroup group = new BoxGroup(api, "id");
group.getCollaborations();
```

[get-collaborations]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGroup.html#getCollaborations()