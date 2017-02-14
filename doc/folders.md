Folders
=======

Folder objects represent a folder from a user's account. They can be used to
iterate through a folder's contents, collaborate a folder with another user or
group, and perform other common folder operations (move, copy, delete, etc.).

* [Get the User's Root Folder](#get-the-users-root-folder)
* [Get a Folder's Items](#get-a-folders-items)
* [Get a Folder's Information](#get-a-folders-information)
* [Update a Folder's Information](#update-a-folders-information)
* [Create a Folder](#create-a-folder)
* [Copy a Folder](#copy-a-folder)
* [Move a Folder](#move-a-folder)
* [Rename a Folder](#rename-a-folder)
* [Delete a Folder](#delete-a-folder)
* [Created a Shared Link for a Folder](#created-a-shared-link-for-a-folder)
* [Share a Folder](#share-a-folder)
* [Get All Collaborations for a Folder](#get-all-collaborations-for-a-folder)
* [Create Metadata](#create-metadata)
* [Get Metadata](#get-metadata)
* [Update Metadata](#update-metadata)
* [Delete Metadata](#delete-metadata)
* [Get All Metadata on Folder](#get-all-metadata-on-folder)

Get the User's Root Folder
--------------------------

The user's root folder can be accessed with the static
[`getRootFolder(BoxAPIConnection)`][get-root-folder] method.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
```

[get-root-folder]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getRootFolder(com.box.sdk.BoxAPIConnection)

Get a Folder's Items
--------------------

Every `BoxFolder` implements [`Iterable<BoxItem>`][iterator] which allows you to
iterate over the folder's contents. The iterator automatically handles paging
and will make additional network calls to load more data from Box when
necessary.

```java
BoxFolder folder = new BoxFolder(api, "id");
for (BoxItem.Info itemInfo : folder) {
    if (itemInfo instanceof BoxFile.Info) {
        BoxFile.Info fileInfo = (BoxFile.Info) itemInfo;
        // Do something with the file.
    } else if (itemInfo instanceof BoxFolder.Info) {
        BoxFolder.Info folderInfo = (BoxFolder.Info) itemInfo;
        // Do something with the folder.
    }
}
```

`BoxFolder` purposely doesn't provide a way of getting a collection of
`BoxItems`. Getting the entire contents of a folder is usually unnecessary and
can be extremely inefficient for folders with a large number of items. If you
really require a collection instead of an iterable, you can create the
collection manually.

```java
Collection<BoxItem> folderItems = new ArrayList<BoxItem>();
BoxFolder folder = new BoxFolder(api, "id");
for (BoxItem.Info itemInfo : folder) {
    folderItems.add(itemInfo.getResource());
}
```

[iterator]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#iterator()

Get a Folder's Information
--------------------------

Calling [`getInfo()`][get-info] on a folder returns a snapshot of the folder's
info.

```java
BoxFolder folder = new BoxFolder(api, "id");
BoxFolder.Info info = folder.getInfo();
```

Requesting information for only the fields you need can improve performance and
reduce the size of the network request. The [`getInfo(String...)`][get-info2]
method lets you specify which fields are retrieved.

```java
BoxFolder folder = new BoxFolder(api, "id");
// Only get information about a few specific fields.
BoxFolder.Info info = folder.getInfo("size", "owned_by");
```

[get-info]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getInfo()
[get-info2]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getInfo(java.lang.String...)

Update a Folder's Information
-----------------------------

Updating a folder's information is done by creating a new `BoxFolder.Info`
object or updating an existing one, and then calling
[`updateInfo(BoxFolder.Info)`][update-info].

```java
BoxFolder folder = new BoxFolder(api, "id");
BoxFolder.Info info = folder.new Info();
info.setName("New Name");
folder.updateInfo(info);
```

[update-info]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#updateInfo(com.box.sdk.BoxFolder.Info)

Create a Folder
---------------

Create a child folder by calling [`createFolder(String)`][create-folder] on the
parent folder.

```java
BoxFolder parentFolder = new BoxFolder(api, "id");
BoxFolder.Info childFolderInfo = parentFolder.createFolder("Child Folder Name");
```

[create-folder]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createFolder(java.lang.String)

Copy a Folder
-------------

Call the [`copy(BoxFolder)`][copy] method to copy a folder to another folder.

```java
BoxFolder folder = new BoxFolder(api, "id1");
BoxFolder destination = new BoxFolder(api, "id2");
folder.copy(destination);
```

You can also use the [`copy(BoxFolder, String)`][copy2] method to rename the
folder while copying it. This allows you to make a copy of the folder in the
same parent folder, but with a different name.

```java
BoxFolder folder = new BoxFolder(api, "id1");
BoxFolder.Info parentFolderInfo = folder.getInfo().getParent();
BoxFolder parentFolder = parentFolderInfo.getResource();
folder.copy(parentFolder, "New Name");
```

[copy]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#copy(com.box.sdk.BoxFolder)
[copy2]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#copy(com.box.sdk.BoxFolder,%20java.lang.String)

Move a Folder
-------------

Call the [`move(BoxFolder)`][move] method with the destination you want the folder moved
to.

```java
BoxFolder folder = new BoxFolder(api, "id1");
BoxFolder destination = new BoxFolder(api, "id2");
folder.move(destination);
```

[move]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#move(com.box.sdk.BoxFolder)

Rename a Folder
---------------

Call the [`rename(String)`][rename] method with a new name for the folder.

```java
BoxFolder folder = new BoxFolder(api, "id");
folder.rename("New Name");
```

A folder can also be renamed by updating the folder's information. This is
useful if you want to perform more than one change to the folder in a single API
request.

```java
BoxFolder folder = new BoxFolder(api, "id");
BoxFolder.Info info = folder.new Info();
info.setName("New Name");
folder.updateInfo(info);
```

[rename]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#rename(java.lang.String)

Delete a Folder
---------------

A folder can be deleted with the [`delete(boolean)`][delete] method. Passing
true to this method indicates that the folder and its contents should be
recursively deleted.

```java
BoxFolder folder = new BoxFolder(api, "id");
folder.delete(true);
```

[delete]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#delete(boolean)

Created a Shared Link for a Folder
----------------------------------

You can get a shared link for a folder by calling the
[`createSharedLink(BoxSharedLink.Access, Date, BoxSharedLink.Permissions)`]
[create-shared-link] method.

```java
BoxFolder folder = new BoxFolder(api, "id");
SharedLink link = folder.createSharedLink(BoxSharedLink.Access.OPEN, null,
    permissions);
```

A shared link can also be created by updating the folder's information. This is
useful if you want to perform more than one change to the folder in a single API
request.

```java
BoxSharedLink sharedLink = new BoxSharedLink();
sharedLink.setAccess(BoxSharedLink.Access.OPEN);

BoxFolder folder = new BoxFolder(api, "id");
BoxFolder.Info info = folder.new Info();
info.setSharedLink(sharedLink);
folder.updateInfo(info);
```

[create-shared-link]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createSharedLink(com.box.sdk.BoxSharedLink.Access,%20java.util.Date,%20com.box.sdk.BoxSharedLink.Permissions)

Share a Folder
--------------

You can invite another person to collaborate on a folder with the
[`collaborate(String, BoxCollaboration.Role)`][collaborate] method.

```java
BoxFolder folder = new BoxFolder(api, "id");
BoxCollaboration.Info collabInfo = folder.collaborate("gcurtis@box.com",
    BoxCollaboration.Role.EDITOR);
```

If you already know the user's ID, you can invite them directly without needing
to know their email address with the
[`collaborate(BoxCollaborator, BoxCollaboration.Role)`][collaborate2] method.

```java
BoxUser collaborator = new User(api, "user-id");
BoxFolder folder = new BoxFolder(api, "folder-id");
BoxCollaboration.Info collabInfo = folder.collaborate(collaborator,
    BoxCollaboration.Role.EDITOR);
```

[collaborate]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#collaborate(java.lang.String,%20com.box.sdk.BoxCollaboration.Role)
[collaborate2]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#collaborate(com.box.sdk.BoxCollaborator,%20com.box.sdk.BoxCollaboration.Role)

Get All Collaborations for a Folder
-----------------------------------

The [`getCollaborations()`][get-collaborations] method will return a collection
of `BoxCollaboration.Info` objects for a folder.

```java
BoxFolder folder = new BoxFolder(api, "id");
Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();
```

[get-collaborations]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getCollaborations()

Create Metadata
---------------

Metadata can be created on a folder by calling
[`createMetadata(Metadata)`][create-metadata],
[`createMetadata(String, Metadata)`][create-metadata-2], or
[`createMetadata(String, String, Metadata)`][create-metadata-3]

```java
BoxFolder folder = new BoxFolder(api, "id");
folder.createMetadata(new Metadata().add("/foo", "bar"));
```

[create-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createMetadata(com.box.sdk.Metadata)
[create-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createMetadata(java.lang.String,%20com.box.sdk.Metadata)
[create-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createMetadata(java.lang.String,%20java.lang.String,%20com.box.sdk.Metadata)

Get Metadata
------------

Retrieve a folder's metadata by calling [`getMetadata()`][get-metadata],
[`getMetadata(String)`][get-metadata-2], or
[`getMetadata(String, String)`][get-metadata-3].

```java
BoxFolder folder = new BoxFolder(api, "id");
folder.getMetadata();
```

[get-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadata()
[get-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadata(java.lang.String)
[get-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadata(java.lang.String,%20java.lang.String)

Update Metadata
---------------

Update a folder's metadata by calling [`updateMetadata(Metadata)`][update-metadata].

```java
BoxFolder folder = new BoxFolder(api, "id");
folder.updateMetadata(new Metadata().add("/foo", "bar"));
```

[update-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#updateMetadata(com.box.sdk.Metadata)

Delete Metadata
---------------

A folder's metadata can be deleted by calling
[`deleteMetadata()`][delete-metadata],
[`deleteMetadata(String)`][delete-metadata-2], or
[`deleteMetadata(String, String)`][delete-metadata-3].

```java
BoxFolder folder = new BoxFolder(api, "id");
folder.deleteMetadata();
```

[delete-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#deleteMetadata()
[delete-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#deleteMetadata(java.lang.String)
[delete-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#deleteMetadata(java.lang.String,%20java.lang.String)

Get All Metadata on Folder
-------------------------

[`getAllMetadata(String...)`][get-all-metadata] method will return an iterable that will page through all of the metadata associated with the folder.

```java
BoxFolder file = new BoxFolder(api, "id");
Iterable<Metadata> metadataList = folder.getAllMetadata("name", "description");
for (Metadata metadata : metadataList) {
    // Do something with the metadata.
}
```

[get-all-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getAllMetadata(java.lang.String...)