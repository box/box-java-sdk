Folders
=======

Folder objects represent a folder from a user's account. They can be used to
iterate through a folder's contents, collaborate a folder with another user or
group, and perform other common folder operations (move, copy, delete, etc.).

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get the User's Root Folder](#get-the-users-root-folder)
- [Get a Folder's Items](#get-a-folders-items)
- [Get a Folder's Information](#get-a-folders-information)
- [Update a Folder's Information](#update-a-folders-information)
- [Create a Folder](#create-a-folder)
- [Copy a Folder](#copy-a-folder)
- [Move a Folder](#move-a-folder)
- [Rename a Folder](#rename-a-folder)
- [Delete a Folder](#delete-a-folder)
- [Created a Shared Link for a Folder](#created-a-shared-link-for-a-folder)
- [Share a Folder](#share-a-folder)
- [Get All Collaborations for a Folder](#get-all-collaborations-for-a-folder)
- [Create Metadata](#create-metadata)
- [Set Metadata](#set-metadata)
- [Get Metadata](#get-metadata)
- [Update Metadata](#update-metadata)
- [Delete Metadata](#delete-metadata)
- [Get All Metadata on Folder](#get-all-metadata-on-folder)
- [Get Metadata for Multiple Files](#get-metadata-for-multiple-files)
- [Set Classification on Folder](#set-classification-on-folder)
- [Get Classification on Folder](#get-classification-on-folder)
- [Remove Classification on Folder](#remove-classification-on-folder)
- [Create Cascade Policy On Folder](#create-cascade-policy-on-folder)
- [Get a Cascade Policy's Information](#get-a-cascade-policys-information)
- [Get All Cascade Policies on Folder](#get-all-cascade-policies-on-folder)
- [Force Apply Cascade Policy on Folder](#force-apply-cascade-policy-on-folder)
- [Delete Cascade Policy](#delete-cascade-policy)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get the User's Root Folder
--------------------------

The user's root folder can be accessed with the static
[`getRootFolder(BoxAPIConnection api)`][get-root-folder] method.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
```

[get-root-folder]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getRootFolder-com.box.sdk.BoxAPIConnection-

Get a Folder's Items
--------------------

Every `BoxFolder` implements [`Iterable<BoxItem>`][iterator] which allows you to
iterate over the folder's contents. The iterator automatically handles paging
and will make additional API calls to load more data when necessary.

<!-- sample get_folders_id_items -->
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

We also allow users to sort the results of the folder items by `name`, `id`, or `date`. This will maintain the integrity
of the ordering when you retrieve the items for a folder. You can do this by calling the 
[`getChildren(String sortField, BoxFolder.SortDirection sortDirection, String... fields)`][get-items-with-sort] method.

```java
BoxFolder folder = new BoxFolder(this.api, "12345");
Iterator<BoxItem.Info> itemIterator = folder.getChildren("name", BoxFolder.SortDirection.ASC).iterator();
for (BoxItem.Info itemInfo : itemIterator) {
    // Do something
}
```

[iterator]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#iterator--
[get-items-with-sort]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getChildren-java.lang.String-com.box.sdk.BoxFolder.SortDirection-java.lang.String...-

Get a Folder's Information
--------------------------

Calling [`getInfo()`][get-info] on a folder returns a snapshot of the folder's
info.

<!-- sample get_folders_id -->
```java
BoxFolder folder = new BoxFolder(api, "id");
BoxFolder.Info info = folder.getInfo();
```

Requesting information for only the fields you need can improve performance and
reduce the size of the network request. The [`getInfo(String... fields)`][get-info2]
method lets you specify which fields are retrieved.

```java
BoxFolder folder = new BoxFolder(api, "id");
// Only get information about a few specific fields.
BoxFolder.Info info = folder.getInfo("size", "owned_by");
```

[get-info]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getInfo--
[get-info2]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getInfo-java.lang.String...-

Update a Folder's Information
-----------------------------

Updating a folder's information is done by creating a new `BoxFolder.Info`
object or updating an existing one, and then calling
[`updateInfo(BoxFolder.Info fieldsToUpdate)`][update-info].

<!-- sample put_folders_id -->
```java
BoxFolder folder = new BoxFolder(api, "id");
BoxFolder.Info info = folder.new Info();
info.setName("New Name");
folder.updateInfo(info);
```

[update-info]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#updateInfo-com.box.sdk.BoxFolder.Info-

Create a Folder
---------------

Create a child folder by calling [`createFolder(String folderName)`][create-folder]
on the parent folder.

<!-- sample post_folders -->
```java
BoxFolder parentFolder = new BoxFolder(api, "id");
BoxFolder.Info childFolderInfo = parentFolder.createFolder("Child Folder Name");
```

[create-folder]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createFolder-java.lang.String-

Copy a Folder
-------------

Call the [`copy(BoxFolder destination)`][copy] method to copy a folder to
another folder.

<!-- sample post_folders_id_copy -->
```java
BoxFolder folder = new BoxFolder(api, "id1");
BoxFolder destination = new BoxFolder(api, "id2");
folder.copy(destination);
```

You can also use the [`copy(BoxFolder destination, String newName)`][copy2] method to rename the
folder while copying it. This allows you to make a copy of the folder in the
same parent folder, but with a different name.

```java
BoxFolder folder = new BoxFolder(api, "id1");
BoxFolder.Info parentFolderInfo = folder.getInfo().getParent();
BoxFolder parentFolder = parentFolderInfo.getResource();
folder.copy(parentFolder, "New Name");
```

[copy]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#copy-com.box.sdk.BoxFolder-
[copy2]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#copy-com.box.sdk.BoxFolder-java.lang.String-

Move a Folder
-------------

Call the [`move(BoxFolder destination)`][move] method with the destination you want the folder moved
to.

```java
BoxFolder folder = new BoxFolder(api, "id1");
BoxFolder destination = new BoxFolder(api, "id2");
folder.move(destination);
```

[move]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#move-com.box.sdk.BoxFolder-

Rename a Folder
---------------

Call the [`rename(String newName)`][rename] method with a new name for the folder.

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

[rename]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#rename-java.lang.String-

Delete a Folder
---------------

A folder can be deleted with the [`delete(boolean recursive)`][delete] method. Passing
`true` to this method indicates that the folder and its contents should be
recursively deleted.

<!-- sample delete_folders_id -->
```java
// Delete the folder and all its contents
BoxFolder folder = new BoxFolder(api, "id");
folder.delete(true);
```

[delete]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#delete-boolean-

Created a Shared Link for a Folder
----------------------------------

You can get a shared link for a folder by calling the
[`createSharedLink(BoxSharedLink.Access accessLevel, Date expirationDate, BoxSharedLink.Permissions permissions)`][create-shared-link]
method.

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

[create-shared-link]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createSharedLink-com.box.sdk.BoxSharedLink.Access-java.util.Date-com.box.sdk.BoxSharedLink.Permissions-

Share a Folder
--------------

You can invite another person to collaborate on a folder with the
[`collaborate(String emailAddress, BoxCollaboration.Role role)`][collaborate] method.

```java
BoxFolder folder = new BoxFolder(api, "id");
BoxCollaboration.Info collabInfo = folder.collaborate("gcurtis@box.com",
    BoxCollaboration.Role.EDITOR);
```

If you already know the user's ID, you can invite them directly without needing
to know their email address with the
[`collaborate(BoxCollaborator user, BoxCollaboration.Role role)`][collaborate2] method.

```java
BoxUser collaborator = new User(api, "user-id");
BoxFolder folder = new BoxFolder(api, "folder-id");
BoxCollaboration.Info collabInfo = folder.collaborate(collaborator,
    BoxCollaboration.Role.EDITOR);
```

[collaborate]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#collaborate-java.lang.String-com.box.sdk.BoxCollaboration.Role-
[collaborate2]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#collaborate-com.box.sdk.BoxCollaborator-com.box.sdk.BoxCollaboration.Role-

Get All Collaborations for a Folder
-----------------------------------

The [`getCollaborations()`][get-collaborations] method will return a collection
of `BoxCollaboration.Info` objects for a folder.

<!-- sample get_folders_id_collaborations -->
```java
BoxFolder folder = new BoxFolder(api, "id");
Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();
```

[get-collaborations]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getCollaborations--

Set Metadata
------------

To set metadata on a folder, call [`setMetadata(String templateKey, String templateScope, Metadata properties)`][set-metadata].

```java
BoxFolder folder = new BoxFolder(api, "id");
folder.setMetadata("test_template", "enterprise", new Metadata().add("/foo", "bar"));
```

Note: This method will unconditionally apply the provided metadata, overwriting existing metadata for the keys provided.
To specifically create or update metadata, please refer to the `createMetadata()` and `updateMetadata()` methods.

Metadata can be created on a folder by calling
[`createMetadata(Metadata properties)`][create-metadata],
[`createMetadata(String templateKey, Metadata properties)`][create-metadata-2], or
[`createMetadata(String templateKey, String templateScope, Metadata properties)`][create-metadata-3]

Note: This method will only succeed if the provided metadata template is not currently applied to the folder, otherwise
it will fail with a Conflict error.

<!-- sample post_folders_id_metadata_id_id -->
```java
BoxFolder folder = new BoxFolder(api, "id");
folder.createMetadata(new Metadata().add("/foo", "bar"));
```

Note: This method will only succeed if the provided metadata template has already been applied to the folder; if the 
folder does not have existing metadata, this method will fail with a Not Found error. This is useful in cases where you 
know the folder will already have metadata applied, since it will save an API call compared to `setMetadata()`.

[set-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#setMetadata-java.lang.String-java.lang.String-com.box.sdk.Metadata-
[create-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createMetadata-com.box.sdk.Metadata-
[create-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createMetadata-java.lang.String-com.box.sdk.Metadata-
[create-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createMetadata-java.lang.String-java.lang.String-com.box.sdk.Metadata-


Get Metadata
------------

Retrieve a folder's metadata by calling [`getMetadata()`][get-metadata],
[`getMetadata(String templateKey)`][get-metadata-2], or
[`getMetadata(String templateKey, String templateScope)`][get-metadata-3].
These methods return a [`Metadata`][metadata] object, which allows access to metadata values.

<!-- sample get_folders_id_metadata_id_id -->
```java
BoxFolder folder = new BoxFolder(api, "id");
Metadata metadata = folder.getMetadata();

// Unknown type metadata field, you can test for type or try to get as any type
JsonValue unknownValue = metadata.getValue("/someField");

// String or Enum metadata fields
String stringValue = metadata.getString("/author");

// Float metadata fields can be interpreted as any numeric type
float floatValue = metadata.getFloat("/price");

// Date metadata fields
Date dateValue = metadata.getDate("/deadline");
```

[metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/Metadata.html
[get-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadata--
[get-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadata-java.lang.String-
[get-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadata-java.lang.String-java.lang.String-

Update Metadata
---------------

Update a folder's metadata by calling [`updateMetadata(Metadata properties)`][update-metadata].

<!-- sample put_folders_id_metadata_id_id -->
```java
BoxFolder folder = new BoxFolder(api, "id");
folder.updateMetadata(new Metadata().add("/foo", "bar"));
```

[update-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#updateMetadata-com.box.sdk.Metadata-

Delete Metadata
---------------

A folder's metadata can be deleted by calling
[`deleteMetadata()`][delete-metadata],
[`deleteMetadata(String templateKey)`][delete-metadata-2], or
[`deleteMetadata(String templateKey, String templateScope)`][delete-metadata-3].

<!-- sample delete_folders_id_metadata_id_id -->
```java
BoxFolder folder = new BoxFolder(api, "id");
folder.deleteMetadata("myMetadataTemplate");
```

[delete-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#deleteMetadata--
[delete-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#deleteMetadata-java.lang.String-
[delete-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#deleteMetadata-java.lang.String-java.lang.String-

Get All Metadata on Folder
-------------------------

[`getAllMetadata()`][get-all-metadata] method will return an iterable that will page through all of the metadata associated with the folder.

<!-- sample get_folders_id_metadata -->
```java
BoxFolder file = new BoxFolder(api, "id");
Iterable<Metadata> metadataList = folder.getAllMetadata();
for (Metadata metadata : metadataList) {
    // Do something with the metadata.
}
```

[get-all-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getAllMetadata-java.lang.String...-

Get Metadata for Multiple Files
-------------------------------

When fetching a large number of items, for example the items in a folder, it would
often be impractical to fetch the metadata for each of those items individually.
Instead, you can get the metadata for all of the items in a single API call by
requesting the `metadata` field on those items:

> __Note:__ The field name should have the form `metadata.<templateScope>.<templateKey>`

```java
BoxFolder root = BoxFolder.getRootFolder();
Iterable<BoxItem.Info> itemsInFolder = root.getChildren("metadata.global.properties")
for (BoxItem.Info itemInfo : itemsInFolder) {
    Metadata itemMetadata = itemInfo.getMetadata("properties", "global");
}
```

Set Classification on Folder
----------------------------

Calling the [`setClassification(String classificationType)`][set-classification] method on a folder will add a 
classification template on the folder. This method will return the classification type applied on the folder. The
classification types include `Public`, `Internal`, and `Confidential`. `Public` being the most available permission,
`Internal` which restricts the specified file to company and collaborators only, and finally, `Confidential`, which
is for collaborators only.

```java
BoxFolder folder = new BoxFolder(api, "id");
String classificationType = folder.setClassification("Public");
```

It is important to note that this call will attempt to create a classification on the folder first, if one already 
exists then it will do the update. If you already know that a classification exists on the folder and would like to save 
an API call, we encourage you to use the [`updateClassification(String classificationType)`][update-classification] 
method.

```java
BoxFolder folder = new BoxFolder(api, "id");
String classificationType = folder.updateClassification("Public");
```

[set-classification]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#setClassification-java.lang.String...-
[update-classification]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#updateClassification-java.lang.String...-

Get Classification on Folder
----------------------------

To retrieve the classification assigned on the folder, use the [`getClassification()`][get-classification] method. This
will return the classification type on the folder.

```java
BoxFolder folder = new BoxFolder(api, "id");
String classificationType = folder.getClassification();
```

[get-classification]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getClassification-java.lang.String...-

Remove Classification on Folder
-------------------------------

To remove classification from the folder, use the [`deleteClassification()`][delete-classification] method.

```java
BoxFolder folder = new BoxFolder(api, "id");
folder.deleteClassification();
```

[delete-classification]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#deleteClassification--

Create Cascade Policy On Folder
-------------------------------

To set a metadata policy, which applies metadata values on a folder to new items in the folder, call 
[`BoxFolder.addMetadataCascadePolicy(String scope, String template)`][create-cascade-policy-on-folder].

```java
String scope = "global";
String templateKey = "template";
String folderId = "12345";
BoxFolder folder = new BoxFolder(api, folderId);
BoxMetadataCascadePolicy.Info cascadePolicyInfo = folder.addMetadataCascadePolicy(scope, template);
```

[create-cascade-policy-on-folder]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#addMetadataCascadePolicy-java.lang.String-java.lang.String-

Get a Cascade Policy's Information
----------------------------------

To retrieve information about a specific metadata cascade policy, call 
[`getInfo()`][get-info]

<!-- sample get_metadata_cascade_policies_id -->
```java
String cascadePolicyID = "1234";
BoxMetadataCascadePolicy metadataCascadePolicy = new BoxMetadataCascadePolicy(api, cascadePolicyID);
BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = metadataCascadePolicy.getInfo();
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxMetadataCascadePolicy.html#getInfo--

Get All Cascade Policies on Folder
----------------------------------

To get a list of all cascade policies on a folder, which show the metadata templates that are being applied to all 
items in the folder, call [`getMetadataCascadePolicies()`][get-all] on that folder.

<!-- sample get_metadata_cascade_policies -->
```java
String folderID = "2222";
BoxFolder folder = new BoxFolder(api, folderID);
Iterable<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder.getMetadataCascadePolicies();
for (BoxMetadataCascadePolicy.Info policyInfo : metadataCascadePolicies) {
    // take action on policy here
}
```

You can also call [`getMetadataCascadePolicies(String enterpriseID, int limit, String... fields)`][get-all-with-limit] 
and set the `enterpriseID` option to retrieve metadata cascade policies from another enterprise.

```java
String folderID = "2222";
String enterpriseID = "3333";
int limit = 50;
BoxFolder folder = new BoxFolder(api, folderID);
Iterable<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder.getMetadataCascadePolicies(enterpriseID, limit);
for (BoxMetadataCascadePolicy.Info policyInfo : metadataCascadePolicies) {
    // take action on policy here
}
```

[get-all]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadataCascadePolicies--
[get-all-with-limit]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadataCascadePolicies-java.lang.String-int-

Force Apply Cascade Policy on Folder
------------------------------------

To force apply a metadata template policy and apply metadata values to all existing items in an affected folder, call 
[`forceApply(String conflictResolution)`][force-apply] with the conflict resolution method for dealing with items that already have a metadata value that conflicts with the folder. Specifying a resolution value of `none` will preserve the existing values on items, and specifying `overwrite` will overwrite values on items in the folder with the metadata value from the folder.

<!-- sample post_metadata_cascade_policies_id_apply -->
```java
String cascadePolicyID = "e4392a41-7de5-4232-bdf7-15e0d6bba067";
BoxMetadataCascadePolicy policy = new BoxMetadataCascadePolicy(api, cascadePolicyID);
policy.forceApply("none");
```

[force-apply]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxMetadataCascadePolicy.html#forceApply-java.lang.String-

Delete Cascade Policy
---------------------

To remove a cascade policy and stop applying metadata from a folder to items in the folder,
call [`delete()`][delete-cascade-policy].

<!-- sample delete_metadata_cascade_policies_id -->
```java
String cascadePolicyID = "e4392a41-7de5-4232-bdf7-15e0d6bba067";
BoxMetadataCascadePolicy policyToDelete = new BoxMetadataCascadePolicy(api, cascadePolicyID);
policyToDelete.delete();
```

[delete-cascade-policy]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxMetadataCascadePolicy.html#delete--


