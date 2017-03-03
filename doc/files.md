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
* [Lock a File](#lock-a-file)
* [Unlock a File](#unlock-a-file)
* [Create a Shared Link](#create-a-shared-link)
* [Get an Embed Link](#get-an-embed-link)
* [Get Thumbnail](#get-thumbnail)
* [Create Metadata](#create-metadata)
* [Get Metadata](#get-metadata)
* [Update Metadata](#update-metadata)
* [Delete Metadata](#delete-metadata)
* [Get All Metadata on File](#get-all-metadata-on-file)

Get a File's Information
------------------------

Calling [`getInfo()`][get-info] on a file returns a snapshot of the file's info.

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();
```

Requesting information for only the fields you need with [`getInfo(String...)`]
[get-info2] can improve performance and reduce the size of the network request.

```java
BoxFile file = new BoxFile(api, "id");
// Only get information about a few specific fields.
BoxFile.Info info = file.getInfo("size", "owned_by");
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getInfo()
[get-info2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getInfo(java.lang.String...)

Update a File's Information
---------------------------

Updating a file's information is done by creating a new [`BoxFile.Info`]
[box-file-info] object or updating an existing one, and then calling
[`updateInfo(BoxFile.Info)`][update-info].

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.new Info();
info.setName("New Name");
file.updateInfo(info);
```

[box-file-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.Info.html
[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#updateInfo(com.box.sdk.BoxFile.Info)

Download a File
---------------

A file can be downloaded by calling [`download(OutputStream)`][download] and
providing an `OutputStream` where the file's contents will be written.

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();

FileOutputStream stream = new FileOutputStream(info.getName());
file.download(stream);
stream.close();
```

Download progress can be tracked by providing a `ProgressListener` to
[`download(OutputStream, ProgressListener)`][download2]. The `ProgressListener`
will then receive progress updates as the download completes.

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

[download]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#download(java.io.OutputStream)
[download2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#download(java.io.OutputStream,%20com.box.sdk.ProgressListener)

Upload a File
-------------

Files are uploaded to a folder by calling the
[`uploadFile(InputStream, String)`][upload] method.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
FileInputStream stream = new FileInputStream("My File.txt");
BoxFile.Info newFileInfo = rootFolder.uploadFile(stream, "My File.txt");
stream.close();
```

Upload progress can be tracked by providing the size of the file and a
`ProgressListener` to
[`uploadFile(InputStream, String, long, ProgressListener)`][upload2]. The
`ProgressListener` will then receive progress updates as the upload completes.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
FileInputStream stream = new FileInputStream("My File.txt");
BoxFile.Info newFileInfo = rootFolder.uploadFile(stream, "My File.txt", 1024, new ProgressListener() {
    public void onProgressChanged(long numBytes, long totalBytes) {
        double percentComplete = numBytes / totalBytes;
    }
});
stream.close();
```

[upload]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile(java.io.InputStream,%20java.lang.String)
[upload2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile(java.io.InputStream,%20java.lang.String,%20long,%20com.box.sdk.ProgressListener)

Copy a File
-----------

A file can be copied to a new folder and optionally be renamed with the
[`copy(BoxFolder)`][copy] and [`copy(BoxFolder, String)`][copy2] methods.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
BoxFile file = new BoxFile(api, "id");
BoxFile.Info copiedFileInfo = file.copy(rootFolder, "New Name");
```

[copy]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#copy(com.box.sdk.BoxFolder)
[copy2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#copy(com.box.sdk.BoxFolder,%20java.lang.String)

Delete a File
-------------

Calling the [`delete()`][delete] method will move the file to the user's trash.

```java
BoxFile file = new BoxFile(api, "id");
file.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#delete()

Get Previous Versions of a File
-------------------------------

For users with premium accounts, versions of a file can be retrieved with the
[`getVersions()`][get-versions] method.

```java
BoxFile file = new BoxFile(api, "id");
List<BoxFileVersion> versions = file.getVersions();
for (BoxFileVersion version : versions) {
    System.out.format("SHA1 of \"%s\": %s\n", item.getName(), version.getSha1());
}
```

[get-versions]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getVersions()

Upload a New Version of a File
------------------------------

New versions of a file can be uploaded with the
[`uploadVersion(InputStream)`][upload-version] method.

```java
BoxFile file = new BoxFile(api, "id");
FileInputStream stream = new FileInputStream("My File.txt");
file.uploadVersion(stream);
```

[upload-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#uploadVersion(java.io.InputStream)

Download a Previous Version of a File
-------------------------------------

For users with premium accounts, previous versions of a file can be downloaded
by calling [`download(OutputStream)`][download-version].

```java
BoxFile file = new BoxFile(api, "id");
List<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.get(0);

FileOutputStream stream = new FileOutputStream(firstVersion.getName());
firstVersion.download(stream);
stream.close();
```

[download-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#download(java.io.OutputStream)

Promote a Previous Version of a File
------------------------------------

A previous version of a file can be promoted with the [`promote()`][promote]
method to become the current version of the file.

```java
BoxFile file = new BoxFile(api, "id");
List<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.get(0);
firstVersion.promote();
```

[promote]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#promote()

Delete a Previous Version of a File
-----------------------------------

A version of a file can be deleted and moved to the trash by calling
[`delete()`][delete-version].

```java
BoxFile file = new BoxFile(api, "id");
List<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.get(0);
firstVersion.delete();
```

[delete-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#delete()

Lock a File
-----------

A file can be locked by calling [`lock(Date)`][lock].

```java
BoxFile file = new BoxFile(api, "id");
Date expiresAt = new Date();
file.lock(expiresAt);
```

[lock]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#lock(java.lang.Date)

Unlock a File
-------------

A file can be unlocked by calling [`unlock()`][unlock].

```java
BoxFile file = new BoxFile(api, "id");
file.unlock();
```

[unlock]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#unlock()

Create a Shared Link
--------------------

A shared link for a file can be generated by calling [`createSharedLink(BoxSharedLink.Access, Date, BoxSharedLink.Permissions)`][create-shared-link].

```java
BoxFile file = new BoxFile(api, "id");
BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
permissions.setCanDownload(true);
permissions.setCanPreview(true);
Date unshareDate = new Date();
BoxSharedLink sharedLink = file.createSharedLink(BoxSharedLink.Access.OPEN, unshareDate, permissions);
```

[create-shared-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createSharedLink(com.box.sdk.BoxSharedLink.Access,%20java.util.Date,%20com.box.sdk.BoxSharedLink.Permissions)

Get an Embed Link
-----------------

A file embed link can be generated by calling [`getPreviewLink()`][get-preview-link].

```java
BoxFile file = new BoxFile(api, "id");
URL embedLink = file.getPreviewLink();
```

[get-preview-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getPreviewLink()

Get Thumbnail
-------------

A thumbnail for a file can be retrieved by calling [`getThumbnail(BoxFile.ThumbnailFileType, int, int, int)`][get-thumbnail].

```java
BoxFile file = new BoxFile(api, "id");
byte[] thumbnail = file.getThumbnail(BoxFile.ThumbnailFileType.PNG, 256, 256, 256, 256)
```

[get-thumbnail]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getThumbnail(com.box.sdk.BoxFile.ThumbnailFileType,%20int,%20int,%20int,%20int)

Create Metadata
---------------

Metadata can be created on a file by calling [`createMetadata(Metadata)`][create-metadata], [`createMetadata(String, Metadata)`][create-metadata-2], or [`createMetadata(String, String, Metadata)`][create-metadata-3]

```java
BoxFile file = new BoxFile(api, "id");
file.createMetadata(new Metadata().add("/foo", "bar"));
```

[create-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata(com.box.sdk.Metadata)
[create-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata(java.lang.String,%20com.box.sdk.Metadata)
[create-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata(java.lang.String,%20java.lang.String,%20com.box.sdk.Metadata)

Get Metadata
------------

Retrieve a files Metadata by calling [`getMetadata()`][get-metadata], [`getMetadata(String)`][get-metadata-2], or [`getMetadata(String, String)`][get-metadata-3].

```java
BoxFile file = new BoxFile(api, "id");
file.getMetadata();
```

[get-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata()
[get-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata(java.lang.String)
[get-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata(java.lang.String,%20java.lang.String)

Update Metadata
---------------

Update a files Metadata by calling [`updateMetadata(Metadata)`][update-metadata].

```java
BoxFile file = new BoxFile(api, "id");
file.updateMetadata(new Metadata().add("/foo", "bar"));
```

[update-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#updateMetadata(com.box.sdk.Metadata)

Delete Metadata
---------------

A files Metadata can be deleted by calling [`deleteMetadata()`][delete-metadata], [`deleteMetadata(String)`][delete-metadata-2], or [`deleteMetadata(String, String)`][delete-metadata-3].

```java
BoxFile file = new BoxFile(api, "id");
file.deleteMetadata();
```

[delete-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata()
[delete-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata(java.lang.String)
[delete-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata(java.lang.String,%20java.lang.String)

Get All Metadata on File
--------------

[`getAllMetadata(String...)`][get-all-metadata] method will return an iterable that will page through all of the metadata associated with the file.

```java
BoxFile file = new BoxFile(api, "id");
Iterable<Metadata> metadataList = file.getAllMetadata("name", "description");
for (Metadata metadata : metadataList) {
    // Do something with the metadata.
}
```

[get-all-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getAllMetadata(java.lang.String...)
