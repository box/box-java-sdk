Files
=====

File objects represent individual files in Box. They can be used to download a
file's contents, upload new versions, and perform other common file operations
(move, copy, delete, etc.).

* [Get a File's Information](#get-a-files-information)
* [Update a File's Information](#update-a-files-information)
* [Download a File](#download-a-file)
* [Upload a File](#upload-a-file)
* [Upload Preflight Check](#upload-preflight-check)
* [Upload a Large File in Chunks](#upload-a-large-file-in-chunks)
* [Upload a Large File Version in Chunks](#upload-a-large-file-version-in-chunks)
* [Upload a Large File Or File Version Manually](#upload-a-large-file-or-file-version-manually)
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
* [Share a File](#share-a-file)
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

Requesting information for only the fields you need with
[`getInfo(String...)`][get-info2]
can improve performance and reduce the size of the network request.

```java
BoxFile file = new BoxFile(api, "id");
// Only get information about a few specific fields.
BoxFile.Info info = file.getInfo("size", "owned_by");
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getInfo--
[get-info2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getInfo-java.lang.String...-

Update a File's Information
---------------------------

Updating a file's information is done by creating a new [`BoxFile.Info`][box-file-info]
object or updating an existing one, and then calling
[`updateInfo(BoxFile.Info)`][update-info].

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.new Info();
info.setName("New Name");
file.updateInfo(info);
```

[box-file-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.Info.html
[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#updateInfo-com.box.sdk.BoxFile.Info-

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

[download]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#download-java.io.OutputStream-
[download2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#download-java.io.OutputStream-com.box.sdk.ProgressListener-

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

[upload]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile-java.io.InputStream-java.lang.String-
[upload2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile-java.io.InputStream-java.lang.String-long-com.box.sdk.ProgressListener-

Upload Preflight Check
----------------------

You may want to check if a file can be successfully uploaded before beginning the file transfer, in order
to the time and bandwidth of sending the file over the network if the upload would not have succeeded.
Calling [`BoxFolder#canUpload(String, long)`][upload-preflight] on the folder you want to upload a new file
to will verify that there is no name conflict and that the account has enough storage space for the file.

```java
String fileName = "My Doc.pdf";
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
try {
    folder.canUpload(fileName, 98734576);

    // If the file upload would not have succeeded, it will not be attempted
    folder.uploadFile(fileContents, fileName);
} catch (BoxAPIException ex) (

)
```

[upload-preflight]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#canUpload-java.lang.String-long-

Upload a Large File in Chunks
-----------------------------

A large file can be uploaded with the [`BoxFolder#uploadLargeFile(InputStream, fileName, fileSize)`][upload-large-file] method.
This will upload the file in parts with integrity checks on each part, to ensure that network errors mid-upload do not
fail the entire operation.

```java
File myFile = new File("My Large_File.txt"); 
FileInputStream stream = new FileInputStream(myFile);

BoxFolder rootFolder = BoxFolder.getRootFolder(api);
BoxFile.Info fileInfo = rootFolder.uploadLargeFile(inputStream, "My_Large_File.txt", myFile.length());
```

[upload-large-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadLargeFile-java.io.InputStream-java.lang.String-long-


Upload a Large File Version in Chunks
-------------------------------------

To upload a new file version for a large file, call the [`BoxFile#uploadLargeFile(InputStrem, long)`][upload-large-file-version]
method.  This will upload the new version of the file in parts with integrity checks on each part, to ensure that network errors
mid-upload do not fail the entire operation.

```java
File myFile = new File("My Large_File.txt"); 
FileInputStream stream = new FileInputStream(myFile);

String fileID = "12345";
BoxFile file = new BoxFile(api, fileID);
BoxFile.Info fileInfo = file.uploadLargeFile(inputStream, myFile.length());
```

[upload-large-file-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#uploadLargeFile-java.io.InputStream-long-

Upload a Large File Or File Version Manually
--------------------------------------------

To start the process of uploading a large file or file version, first create a new upload session with
[`BoxFolder#createUploadSession(fileName, fileSize)`][create-upload-session] for a new file, or
[`BoxFile#createUploadSession(long)`][create-upload-session-version] for a new file version.
Once the upload session is created, all other steps are identical for both cases.

```java
BoxFileUploadSession.Info sessionInfo;
if (/* uploading a new file */) {
    // Create the upload session for a new file
    BoxFolder rootFolder = BoxFolder.getRootFolder(api);
    sessionInfo = rootFolder.createUploadSession("New Large File.pdf", fileSize);
} else if (/* uploading a new version of an exiting file */) {
    // Create the uplaod session for a new version of an existing file
    String fileID = "93465";
    BoxFile file = new BoxFile(api, fileID);
    sessionInfo = file.createUploadSession(fileSize);
}

//Get the session resource from the session info
BoxFileUploadSession session = sessionInfo.getResource();

//Create the Message Digest for the whole file
MessageDigest digest = null;
try {
    digest = MessageDigest.getInstance("SHA1");
} catch (NoSuchAlgorithmException ae) {
    throw new BoxAPIException("Digest algorithm not found", ae);
}
```
Once the upload session is created, the large file can be uploaded in chunks with the
[`uploadPart(partId, stream, offset, partSize, totalSizeOfFile)`][upload-part] method of the session instance.
If there is a failure in uploading any of the parts, the failed part can be uploaded again without
affecting the other parts.

```java
//Reading a large file
FileInputStream fis = new FileInputStream("My_Large_File.txt");
//Create the digest input stream to calculate the digest for the whole file.
DigestInputStream dis = new DigestInputStream(fis, digest);

List<BoxFileUploadSessionPart> parts = new ArrayList<BoxFileUploadSessionPart>();

//Get the part size. Each uploaded part should match the part size returned as part of the upload session.
//The last part of the file can be less than part size if the remaining bytes of the last part is less than
//the given part size
long partSize = sessionInfo.getPartSize();
//Start byte of the part
long offset = 0;
//Overall of bytes processed so far
long processed = 0;
while (processed < fileSize) {
    long diff = fileSize - processed;
    //The size last part of the file can be less than the part size.
    if (diff < partSize) {
        partSize = diff;
    }

    //Generate a unique part ID
    String partId = LargeFileUpload.generateHex();
    //Upload a part. It can be uploaded asynchorously
    BoxFileUploadSessionPart part = session.uploadPart(partId, dis, offset, partSize, fileSize);
    parts.add(part);

    //Increase the offset and proceesed bytes to calculate the Content-Range header.
    processed += partSize;
    offset += partSize;
}
```

At any point in time, the list of parts that have been uploaded successfully can be retrieved with the
[`listParts(offset, limit)`][list-parts] method of the session instance.

```java
//The following snippet retrives first 1000 parts that are uploaded.
BoxFileUploadSessionPartList partList = session.listParts(0, 1000);
List<BoxFileUploadSessionPart> parts = partList.getEntries();
```

Once all the parts are uploaded successfully, the upload session can be committed with the
[`commit(digest, parts, attributes, ifMatch, ifNoneMatch)`][upload-session-commit] method.

```java
//Creates the file hash
byte[] digestBytes = digest.digest();
//Base64 encoding of the hash
String digestStr = Base64.encode(digestBytes);

//Commit the upload session. If there is a failure, abort the commit.
BoxFile.Info fileInfo = session.commit(digestStr, parts, null, null, null);
```

The upload session can be aborted at any time with the [`abort()`][upload-session-abort] method of the session
instance.  This will cancel the upload and any parts that were already uploaded will be lost.

```java
session.abort();
```

The upload session status can be retrieved at any time with the [`getStatus()`][upload-session-status] method.
This call will update the parts processed and other information in the session info instance.

```java
BoxFileUploadSession.Info updatedSessionInfo = session.getStatus();
```

[create-upload-session]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createUploadSession-java.lang.String-long-
[create-upload-session-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createUploadSession-long-
[upload-part]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileUploadSession.html#uploadPart-java.io.InputStream-long-int-long-
[list-parts]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileUploadSession.html#listParts-int-int-
[upload-session-commit]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileUploadSession.html#commit-java.lang.String-java.util.List-java.util.Map-java.lang.String-java.lang.String-
[upload-session-abort]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileUploadSession.html#abort--
[upload-session-status]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileUploadSession.html#getStatus--

Move a File
-----------

To move a file from one folder into another, call [`move(BoxFolder)`][move] on the file to be moved
with the destination folder.

```java
String fileID = "1234";
String destinationFolderID = "5678";
BoxFile file = new BoxFile(api, fileID);
BoxFolder destinationFolder = new BoxFolder(destinationFolderID);
file.move(destinationFolder)
```

To avoid name conflicts in the destination folder, you can optionally provide a new name for the file
to [`move(BoxFolder, String)`][move-rename].  The file will be placed into the destination folder with
the new name.

```java
String fileID = "1234";
String destinationFolderID = "5678";
BoxFile file = new BoxFile(api, fileID);
BoxFolder destinationFolder = new BoxFolder(destinationFolderID);
file.move(destinationFolder, "Vacation Photo (1).jpg");
```

[move]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#move-com.box.sdk.BoxFolder-
[move-rename]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#move-com.box.sdk.BoxFolder-java.lang.String-

Copy a File
-----------

A file can be copied to a new folder and optionally be renamed with the
[`copy(BoxFolder)`][copy] and [`copy(BoxFolder, String)`][copy2] methods.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
BoxFile file = new BoxFile(api, "id");
BoxFile.Info copiedFileInfo = file.copy(rootFolder, "New Name");
```

[copy]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#copy-com.box.sdk.BoxFolder-
[copy2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#copy-com.box.sdk.BoxFolder-java.lang.String-

Delete a File
-------------

Calling the [`delete()`][delete] method will move the file to the user's trash.

```java
BoxFile file = new BoxFile(api, "id");
file.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#delete--

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

[get-versions]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getVersions--

Upload a New Version of a File
------------------------------

New versions of a file can be uploaded with the
[`uploadVersion(InputStream)`][upload-version] method.

```java
BoxFile file = new BoxFile(api, "id");
FileInputStream stream = new FileInputStream("My File.txt");
file.uploadVersion(stream);
```

[upload-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#uploadVersion-java.io.InputStream-

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

[download-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#download-java.io.OutputStream-

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

[promote]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#promote--

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

[delete-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#delete--

Create new version of a large File
----------------------------------

New versions of a large file can be uploaded with the
[`uploadLargeFile(InputStream, fileSize)`][upload-large-file-version] method.

```java
File myFile = new File("My File.txt");
FileInputStream stream = new FileInputStream(myFile);

BoxFile file = new BoxFile(api, "id");
BoxFile.Info versionedFileInfo = file.uploadLargeFile(inputStream, myFile.length());
```

[upload-large-file-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#uploadLargeFile-java.io.InputStream-long-

Lock a File
-----------

A file can be locked by calling [`lock(Date)`][lock].

```java
BoxFile file = new BoxFile(api, "id");
Date expiresAt = new Date();
file.lock(expiresAt);
```

[lock]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#lock-java.util.Date-

Unlock a File
-------------

A file can be unlocked by calling [`unlock()`][unlock].

```java
BoxFile file = new BoxFile(api, "id");
file.unlock();
```

[unlock]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#unlock--

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

[create-shared-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createSharedLink-com.box.sdk.BoxSharedLink.Access-java.util.Date-com.box.sdk.BoxSharedLink.Permissions-

Share a File
------------
You can invite another person to collaborate on a file by email with [`collaborate(String, BoxCollaboration.Role, Boolean, Boolean)`][share-a-file].
 
The notify param will determine if the user or group will receive email notifications. 
 
The can_view_path param allows the invitee to see the entire ancestral path of the associated file. The user will not gain privileges in any ancestral file. 
 
```java
BoxFile file = new BoxFile(api, "id");
BoxCollaboration.Info collabInfo = file.collaborate("testuser@example.com", BoxCollaboration.Role.EDITOR, true, true);
``` 
 
Alternatively, if you know the user's ID, you can invite them directly without needing to know their email address with the
[`collaborate(BoxCollaborator, BoxCollaboration.Role, Boolean, Boolean)`][share-a-file-userID]
 
```java
BoxUser collaborator = new User(api, "user-id");
BoxFile file = new BoxFile(api, "file-id");
BoxCollaboration.Info collabInfo = file.collaborate(collaborator, BoxCollaboration.Role.EDITOR, true, true);
```
 
[share-a-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#collaborate-java.lang.String-com.box.sdk.BoxCollaboration.Role-java.lang.Boolean-java.lang.Boolean-
[share-a-file-userID]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#collaborate-com.box.sdk.BoxCollaborator-com.box.sdk.BoxCollaboration.Role-java.lang.Boolean-java.lang.Boolean-


Get an Embed Link
-----------------

A file embed link can be generated by calling [`getPreviewLink()`][get-preview-link].

```java
BoxFile file = new BoxFile(api, "id");
URL embedLink = file.getPreviewLink();
```

[get-preview-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getPreviewLink--

Get Thumbnail
-------------

A thumbnail for a file can be retrieved by calling [`getThumbnail(BoxFile.ThumbnailFileType, int, int, int)`][get-thumbnail].

```java
BoxFile file = new BoxFile(api, "id");
byte[] thumbnail = file.getThumbnail(BoxFile.ThumbnailFileType.PNG, 256, 256, 256, 256)
```

[get-thumbnail]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getThumbnail-com.box.sdk.BoxFile.ThumbnailFileType-int-int-int-int-

Create Metadata
---------------

Metadata can be created on a file by calling [`createMetadata(Metadata)`][create-metadata], [`createMetadata(String, Metadata)`][create-metadata-2], or [`createMetadata(String, String, Metadata)`][create-metadata-3]

```java
BoxFile file = new BoxFile(api, "id");
file.createMetadata(new Metadata().add("/foo", "bar"));
```

[create-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata-com.box.sdk.Metadata-
[create-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata-java.lang.String-com.box.sdk.Metadata-
[create-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata-java.lang.String-java.lang.String-com.box.sdk.Metadata-

Get Metadata
------------

Retrieve a files Metadata by calling [`getMetadata()`][get-metadata], [`getMetadata(String)`][get-metadata-2],
or [`getMetadata(String, String)`][get-metadata-3].
These methods return a [`Metadata`][metadata] object, which allows access to metadata values.

```java
BoxFile file = new BoxFile(api, "id");
Metadata metadata = file.getMetadata();

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
[get-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata--
[get-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata-java.lang.String-
[get-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata-java.lang.String-java.lang.String-

Update Metadata
---------------

Update a files Metadata by calling [`updateMetadata(Metadata)`][update-metadata].

```java
BoxFile file = new BoxFile(api, "id");
file.updateMetadata(new Metadata().add("/foo", "bar"));
```

[update-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#updateMetadata-com.box.sdk.Metadata-

Delete Metadata
---------------

A files Metadata can be deleted by calling [`deleteMetadata()`][delete-metadata], [`deleteMetadata(String)`][delete-metadata-2], or [`deleteMetadata(String, String)`][delete-metadata-3].

```java
BoxFile file = new BoxFile(api, "id");
file.deleteMetadata();
```

[delete-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata--
[delete-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata-java.lang.String-
[delete-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata-java.lang.String-java.lang.String-

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

[get-all-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getAllMetadata-java.lang.String...-
