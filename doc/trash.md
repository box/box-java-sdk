Trash
=====

Under normal circumstances, when an item in Box is deleted, it is not actually erased immediately. Instead, it is
moved to the Trash. The Trash allows you to recover files and folders that have been deleted. By default, items in
the Trash will be purged after 30 days.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get Trashed Items](#get-trashed-items)
- [Get Trashed File Information](#get-trashed-file-information)
- [Get Trashed Folder Information](#get-trashed-folder-information)
- [Permanently Delete File From Trash](#permanently-delete-file-from-trash)
- [Permanently Delete Folder From Trash](#permanently-delete-folder-from-trash)
- [Restore a File from the Trash](#restore-a-file-from-the-trash)
- [Restore a Folder from the Trash](#restore-a-folder-from-the-trash)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get Trashed Items
-----------------

The [`BoxTrash`][trash-object] implements `Iterable<BoxItem.Info>`, so to get
the collection of items currently in the trash, simply iterate over it.

```java
BoxTrash trash = new BoxTrash(api);
for (BoxItem.Info itemInfo : trash) {
    // Process the item
}
```

[trash-object]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html

Get Trashed File Information
----------------------------

Ordinarily, trying to call [`getInfo()`][file-get-info] on a file that is in
the trash will return a 404 error.  To get the information of a file in the
trash, you must instead call
[`BoxTrash#getFileInfo(String fileID)`][get-trashed-file] with the ID of the trashed file.  You can optionally
pass a specific list of fields to retrieve to [`getFileInfo(String fileID, String... fields)`][get-trashed-file-fields],
which will return only the specified fields to reduce payload size.

```java
String fileID = "9873459";
BoxTrash trash = new BoxTrash(api);
BoxFile.Info fileInfo = trash.getFileInfo(fileID);
```

[get-trashed-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#getFileInfo-java.lang.String-
[get-trashed-file-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#getFileInfo-java.lang.String-java.lang.String...-

Get Trashed Folder Information
------------------------------

Ordinarily, trying to call [`getInfo()`][folder-get-info] on a folder that is in the trash will return a 404 error.
To get the information of a folder in the trash, you must instead call
[`BoxTrash#getFolderInfo(String fileID)`][get-trashed-folder] with the ID of the trashed folder.  You can optionally
pass a specific list of fields to retrieve to
[`getFileInfo(String folderID, String... fields)`][get-trashed-folder-fields], which will return only the specified
fields to reduce payload size.

```java
String folderID = "2345343";
BoxTrash trash = new BoxTrash(api);
BoxFolder.Info folderInfo = trash.getFolderInfo(folderInfo);
```

[get-trashed-folder]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#getFolderInfo-java.lang.String-
[get-trashed-folder-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#getFolderInfo-java.lang.String-java.lang.String...-


Permanently Delete File From Trash
----------------------------------

To delete a file from the trash, call [`BoxTrash#deleteFile(String fileID)`][delete-file] with the ID of the file to
delete.

> __Note:__ This will permanently delete the file, and cannot be undone.

```java
String fileID = "87398";
BoxTrash trash = new BoxTrash(api);
trash.deleteFile(fileID);
```

[delete-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#deleteFile-java.lang.String-


Permanently Delete Folder From Trash
----------------------------------

To delete a folder from the trash, call [`BoxTrash#deleteFolder(String fileID)`][delete-folder] with the ID of the
folder to delete.

> __Note:__ This will permanently delete the folder, and cannot be undone.

```java
String folder = "123456";
BoxTrash trash = new BoxTrash(api);
trash.deleteFolder(folderID);
```

[delete-folder]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#deleteFolder-java.lang.String-

Restore a File from the Trash
-----------------------------

To restore a file from the trash, effectively undeleting it, call [`BoxTrash#restoreFile(String fileID)`][restore-file]
with the ID of the file.  To avoid scenarios where the parent folder that previously contained the file is no longer available,
the user does not have permission to create items in that folder, or that folder has an item with the same name as the one
being restored; you can pass a new parent folder ID and/or file name to
[`BoxTrash#restoreFile(String fileID, String newName, String newParentID)`][restore-file-safe].  The new name
and parent will only be used if a conflict is encountered while trying to restore the file to its original location.

```java
String fileID = "125367";
String newName = "Presentation 2018 ORIGINAL.pptx";
String newParentID = "98765";

BoxTrash trash = new BoxTrash(api);
// Avoid conflicts at the original location
trash.restoreFile(fileID, newName, newParentID);
```

[restore-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#restoreFile-java.lang.String-
[restore-file-safe]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#restoreFile-java.lang.String-java.lang.String-java.lang.String-

Restore a Folder from the Trash
-------------------------------

To restore a folder from the trash, effectively undeleting it, call
[`BoxTrash#restoreFolder(String folderID)`][restore-folder] with the ID of the folder.  To avoid scenarios where the
parent folder that previously contained the folder to be restored is no longer available, the user
does not have permission to create items in that folder, or that folder has an item with the same name as the one
being restored; you can pass a new parent folder ID and/or folder name to
[`BoxTrash#restoreFolder(String folderID, String newName, String newParentID)`][restore-folder-safe].  The new name
and parent will only be used if a conflict is encountered while trying to restore the folder to its original location.

```java
String folderID = "125367";
String newName = "My Documents ORIGINAL";
String newParentID = "98765";

BoxTrash trash = new BoxTrash(api);
// Avoid conflicts at the original location
trash.restoreFolder(folderID, newName, newParentID);
```

[restore-folder]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#restoreFolder-java.lang.String-
[restore-folder-safe]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTrash.html#restoreFolder-java.lang.String-java.lang.String-java.lang.String-
