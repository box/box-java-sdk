Collaborations
==============

Collaborations are used to share folders between users or groups. They also
define what permissions a user has for a folder.

* [Add a Collaboration](#add-a-collaboration)
* [Edit a Collaboration](#edit-a-collaboration)
* [Remove a Collaboration](#remove-a-collaboration)
* [Get a Collaboration's Information](#get-a-collaborations-information)
* [Get the Collaborations on a Folder](#get-the-collaborations-on-a-folder)
* [Get Pending Collaborations](#get-pending-collaborations)
* [Accept or Decline a Pending Collaboration](#accept-or-decline-a-pending-collaboration)

Add a Collaboration
-------------------

A collaboration can be added for an existing user or group with
[`collaborate(BoxCollaborator, BoxCollaboration.Role)`][collaborate1]. The
`role` parameter determines what permissions the collaborator will have on the
folder.

```java
BoxCollaborator user = new BoxUser(api, "user-id")
BoxFolder folder = new BoxFile(api, "folder-id");
folder.collaborate(user, BoxCollaboration.Role.EDITOR);
```

You can also add a collaboration by providing an email address with
[`collaborate(String, BoxCollaboration.Role)`][collaborate2]. If the recipient
doesn't have a Box account, they will be asked create one.

```java
BoxFolder folder = new BoxFile(api, "id");
folder.collaborate("gcurtis@box.com", BoxCollaboration.Role.EDITOR);
```

[collaborate1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#collaborate-com.box.sdk.BoxCollaborator-com.box.sdk.BoxCollaboration.Role-
[collaborate2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#collaborate-java.lang.String-com.box.sdk.BoxCollaboration.Role-

Edit a Collaboration
--------------------

A collaboration can be edited by creating a new
[`BoxCollaboration.Info`][box-collaboration-info] object or updating an existing
one, and then calling [`updateInfo(BoxCollaboration.Info)`][update-info]

```java
BoxCollaboration collaboration = new BoxCollaboration(api, "id");
BoxCollaboration.Info info = collaboration.new Info();
info.setStatus(BoxCollaboration.Status.ACCEPTED);
collaboration.updateInfo(info);
```

[box-collaboration-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaboration.Info.html
[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaboration.html#updateInfo-com.box.sdk.BoxCollaboration.Info-

Remove a Collaboration
----------------------

A collaboration can be removed by calling [`delete()`][delete].

```java
BoxCollaboration collaboration = new BoxCollaboration(api, "id");
collaboration.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaboration.html#delete--

Get a Collaboration's Information
---------------------------------

Calling [`getInfo()`][get-info] on a collaboration returns a snapshot of the
collaboration's info.

```java
BoxCollaboration collaboration = new BoxCollaboration(api, "id");
BoxCollaboration.Info info = collaboration.getInfo();
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaboration.html#getInfo--

Get the Collaborations on a Folder
----------------------------------

You can get all of the collaborations on a folder by calling
[`getCollaborations()`][get-collaborations] on the folder.

```java
BoxFolder folder = new BoxFile(api, "id");
Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();
```

[get-collaborations]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getCollaborations--

Get Pending Collaborations
--------------------------

A collection of all the user's pending collaborations can be retrieved with
[`getPendingCollaborations(BoxAPIConnection)`][get-pending-collaborations].

```java
Collection<BoxCollaboration.Info> pendingCollaborations =
    BoxCollaboration.getPendingCollaborations(api);
```

[get-pending-collaborations]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaboration.html#getPendingCollaborations-com.box.sdk.BoxAPIConnection-

Accept or Decline a Pending Collaboration
-----------------------------------------

To accept or decline a pending collaboration, update the info of the pending collaboration object
with the desired status.

```java
// Accept all pending collaborations
Collection<BoxCollaboration.Info> pendingCollaborations = BoxCollaboration.getPendingCollaborations(api);
for (BoxCollaboration.Info collabInfo : pendingCollaborations) {
    collabInfo.setStatus(BoxCollaboration.Status.ACCEPTED);
    collabInfo.getResource().updateInfo(collabInfo);
}
```
