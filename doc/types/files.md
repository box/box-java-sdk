Files
=====

File objects represent individual files in Box. They can be used to download a
file's contents, upload new versions, and perform other common file operations
(move, copy, delete, etc.).

* [Get a File's Information](#get-a-files-information)
* [Update a File's Information](#update-a-files-information)
* [Download a File](#download-a-file)
* [Upload a File](#upload-a-file)
* [Copy a File](#copy-a-file)
* [Delete a File](#delete-a-file)
* [Get Previous Versions of a File](#get-previous-versions-of-a-file)
* [Upload a New Version of a File](#upload-a-new-version-of-a-file)
* [Download a Previous Version of a File](#download-a-previous-version-of-a-file)
* [Promote a Previous Version of a File](#promote-a-previous-version-of-a-file)
* [Delete a Previous Version of a File](#delete-a-previous-version-of-a-file)

Get a File's Information
------------------------

Calling `getInfo()` on a file returns a snapshot of the file's info.

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();
```

Requesting information for only the fields you need can improve performance and
reduce the size of the network request.

```java
BoxFile file = new BoxFile(api, "id");
// Only get information about a few specific fields.
BoxFile.Info info = file.getInfo("size", "owned_by");
```

Update a File's Information
---------------------------

Updating a file's information is done by creating a new `BoxFile.Info` object or
updating an existing one, and then calling `updateInfo(BoxFile.Info)`.

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.new Info();
info.setName("New Name");
file.updateInfo(info);
```

Download a File
---------------

A file can be downloaded by calling `download(OutputStream)` and providing an
`OutputStream` where the file's contents will be written.

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();

FileOutputStream stream = new FileOutputStream(info.getName());
file.download(stream);
stream.close();
```

Download progress can be tracked by providing a `ProgressListener` which will
receive progress updates as the download completes.

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();

FileOutputStream stream = new FileOutputStream(info.getName());
// Provide a ProgressListener to monitor the progress of the download.
file.download(stream, new ProgressListener() {
    public void onProgressChanged(long numBytes, long totalBytes) {
        double percentComplete = numBytes / totalBytes;
    }
});
stream.close();
```

Upload a File
-------------

Files are uploaded to a folder by calling the `uploadFile(InputStream, String)`
method.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
FileInputStream stream = new FileInputStream("My File.txt");
rootFolder.uploadFile(stream, "My File.txt");
stream.close();
```

Upload progress can be tracked by providing the size of the file and a
`ProgressListener`. The `ProgressListener` will receive progress updates as the
upload completes.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
FileInputStream stream = new FileInputStream("My File.txt");
rootFolder.uploadFile(stream, "My File.txt", 1024, new ProgressListener() {
    public void onProgressChanged(long numBytes, long totalBytes) {
        double percentComplete = numBytes / totalBytes;
    }
});
stream.close();
```

Copy a File
-----------

A file can be copied to a new folder and optionally be renamed.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
BoxFile file = new BoxFile(api, "id");
BoxFile.Info copiedFileInfo = file.copy(rootFolder, "New Name");
```

Delete a File
-------------

Calling the `delete()` method will move the file to the user's trash.

```java
BoxFile file = new BoxFile(api, "id");
file.delete();
```

Get Previous Versions of a File
-------------------------------

For users with premium accounts, versions of a file can be retrieved.

```java
BoxFile file = new BoxFile(api, "id");
List<BoxFileVersion> versions = file.getVersions();
for (BoxFileVersion version : versions) {
    System.out.format("SHA1 of \"%s\": %s\n", item.getName(), version.getSha1());
}
```

Upload a New Version of a File
------------------------------

For users with premium accounts, new versions of a file can be uploaded.

```java
BoxFile file = new BoxFile(api, "id");
FileInputStream stream = new FileInputStream("My File.txt");
file.uploadVersion(stream);
```

Download a Previous Version of a File
-------------------------------------

For users with premium accounts, previous versions of a file can be downloaded.

```java
BoxFile file = new BoxFile(api, "id");
List<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.get(0);

FileOutputStream stream = new FileOutputStream(firstVersion.getName());
firstVersion.download(stream);
stream.close();
```

Promote a Previous Version of a File
------------------------------------

A previous version of a file can be promoted to become the current version of
the file.

```java
BoxFile file = new BoxFile(api, "id");
List<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.get(0);
firstVersion.promote();
```

Delete a Previous Version of a File
-----------------------------------

A version of a file can be deleted and moved to the trash.

```java
BoxFile file = new BoxFile(api, "id");
List<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.get(0);
firstVersion.delete();
```
