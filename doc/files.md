Files
=====

File objects represent individual files in Box. They can be used to download a
file's contents, upload new versions, and perform other common file operations
(move, copy, delete, etc.).

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Get a File's Information](#get-a-files-information)
- [Update a File's Information](#update-a-files-information)
- [Download a File](#download-a-file)
- [Upload a File](#upload-a-file)
- [Upload Preflight Check](#upload-preflight-check)
- [Upload a Large File in Chunks](#upload-a-large-file-in-chunks)
- [Upload a Large File in Chunks Including Attributes](#upload-a-large-file-in-chunks-including-attributes)
- [Upload a Large File Version in Chunks](#upload-a-large-file-version-in-chunks)
- [Upload a Large File Version in Chunks Including Attributes](#upload-a-large-file-version-in-chunks-including-attributes)
- [Upload a Large File Or File Version Manually](#upload-a-large-file-or-file-version-manually)
- [Move a File](#move-a-file)
- [Copy a File](#copy-a-file)
- [Delete a File](#delete-a-file)
- [Get Previous Versions of a File](#get-previous-versions-of-a-file)
- [Upload a New Version of a File](#upload-a-new-version-of-a-file)
- [Download a Previous Version of a File](#download-a-previous-version-of-a-file)
- [Promote a Previous Version of a File](#promote-a-previous-version-of-a-file)
- [Delete a Previous Version of a File](#delete-a-previous-version-of-a-file)
- [Lock a File](#lock-a-file)
- [Unlock a File](#unlock-a-file)
- [Find File for Shared Link](#find-file-for-shared-link)
- [Download File for Shared Link](#download-file-for-shared-link)
- [Create a Shared Link](#create-a-shared-link)
- [Get a Shared Link](#get-a-shared-link)
- [Update a Shared Link](#update-a-shared-link)
- [Remove a Shared Link](#remove-a-shared-link)
- [Add a Collaborator](#add-a-collaborator)
- [Get an Embed Link](#get-an-embed-link)
- [Create Metadata](#create-metadata)
- [Set Metadata](#set-metadata)
- [Get Metadata](#get-metadata)
- [Update Metadata](#update-metadata)
- [Delete Metadata](#delete-metadata)
- [Get All Metadata on File](#get-all-metadata-on-file)
- [Set Classification on File](#set-classification-on-file)
- [Get Classification on File](#get-classification-on-file)
- [Remove Classification on File](#remove-classification-on-file)
- [Get File Representations](#get-file-representations)
- [Get Representation Content](#get-representation-content)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get a File's Information
------------------------

Calling [`getInfo()`][get-info] on a file returns a snapshot of the file's info.

<!-- sample get_files_id -->
```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();
```

Requesting information for only the fields you need with
[`getInfo(String... fields)`][get-info]
can improve performance and reduce the size of the network request.

```java
BoxFile file = new BoxFile(api, "id");
// Only get information about a few specific fields.
BoxFile.Info info = file.getInfo("size", "owned_by");
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getInfo-java.lang.String...-

Update a File's Information
---------------------------

Updating a file's information is done by creating a new [`BoxFile.Info`][box-file-info]
object or updating an existing one, and then calling
[`updateInfo(BoxFile.Info fieldsToUpdate)`][update-info].

<!-- sample put_files_id -->
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

A file can be downloaded by calling [`download(OutputStream stream)`][download]
and providing an `OutputStream` where the file's contents will be written.

<!-- sample get_files_id_content -->
```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();

FileOutputStream stream = new FileOutputStream(info.getName());
file.download(stream);
stream.close();
```

Download progress can be tracked by providing a [`ProgressListener`][progress]
to [`download(OutputStream stream, ProgressListener progress)`][download2].
The `ProgressListener` will then receive progress updates as the download
completes.

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
[progress]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/ProgressListener.html

Upload a File
-------------

Files are uploaded to a folder by calling the
[`uploadFile(InputStream fileContents, String fileName)`][upload] method
on the [`BoxFolder`][box-folder] you want to upload the file into.

<!-- sample post_files_content -->
```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
FileInputStream stream = new FileInputStream("My File.txt");
BoxFile.Info newFileInfo = rootFolder.uploadFile(stream, "My File.txt");
stream.close();
```

Note that using `FileInputStream` allows you to read the content of the file only once. 
If the first upload attempt fails, the stream will become exhausted,
and request retry with no content might be performed. To retry an upload, you would have to 
create a new `FileInputStream` and call `uploadFile()` method again.

If you want the SDK to automatically retry the upload in case of any error, you need to provide an 
`InputStream` class that supports the `reset()` operation. For example, you can read all bytes from your file into
a `ByteArrayInputStream` and then use it for the upload method. Be aware that this approach will load the whole file
into memory, so it is not recommended for large files, as it can exhaust easily your memory.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
InputStream stream = new ByteArrayInputStream(Files.readAllBytes(new File(path).toPath()));
BoxFile.Info newFileInfo = rootFolder.uploadFile(stream, "My File.txt");
stream.close();
```

Upload progress can be tracked by providing the size of the file and a
[`ProgressListener`][progress] to
[`uploadFile(InputStream fileContents, String fileName, long fileSize, ProgressListener progress)`][upload2].
The `ProgressListener` will then receive progress updates as the upload completes.

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

We also support the ability to attach a description of the file upon upload by calling the
[`uploadFile(InputStream fileContents, String fileName, String fileDescription)`][upload3] method.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
FileInputStream stream = new FileInputStream("My File.txt");
BoxFile.Info newFileInfo = rootFolder.uploadFile(stream, "My File.txt", "File Description");
stream.close();
```

[upload]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile-java.io.InputStream-java.lang.String-
[upload2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile-java.io.InputStream-java.lang.String-long-com.box.sdk.ProgressListener-
[upload3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile-java.io.InputStream-java.lang.String-java.lang.String-
[box-folder]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html

Upload Preflight Check
----------------------

You may want to check if a file can be successfully uploaded before beginning
the file transfer, in order to the time and bandwidth of sending the file over
the network if the upload would not have succeeded.  Calling the
[`BoxFolder#canUpload(String fileName, long fileSize)`][upload-preflight] method
on the folder you want to upload a new file into will verify that there is no
name conflict and that the account has enough storage space for the file.

<!-- sample options_files_content -->
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

A large file can be uploaded with the
[`uploadLargeFile(InputStream fileContents, String fileName, long fileSize)`][upload-large-file]
method on the folder to upload the new file into.  This will upload the file in
parts with integrity checks on each part, to ensure that network errors
mid-upload do not fail the entire operation.

<!-- samples x_chunked_uploads automatic -->
```java
File myFile = new File("My Large_File.txt"); 
FileInputStream stream = new FileInputStream(myFile);

BoxFolder rootFolder = BoxFolder.getRootFolder(api);
BoxFile.Info fileInfo = rootFolder.uploadLargeFile(inputStream, "My_Large_File.txt", myFile.length());
```

[upload-large-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadLargeFile-java.io.InputStream-java.lang.String-long-

Upload a Large File in Chunks Including Attributes
--------------------------------------------------

A large file can be uploaded, including attributes, with the
[`uploadLargeFile(InputStream fileContents, String fileName, long fileSize, Map<String, String> fileAttributes)`][upload-large-file-including-attributes]
method on the folder to upload the new file into.  This will upload the file in
parts with integrity checks on each part, to ensure that network errors
mid-upload do not fail the entire operation.

```java
File myFile = new File("My Large_File.txt"); 
FileInputStream stream = new FileInputStream(myFile);
Map<String, String> fileAttributes = new HashMap<String, String>();
fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

BoxFolder rootFolder = BoxFolder.getRootFolder(api);
BoxFile.Info fileInfo = rootFolder.uploadLargeFile(inputStream, "My_Large_File.txt", myFile.length(), fileAttributes);
```

[upload-large-file-including-attributes]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadLargeFile-java.io.InputStream-java.lang.String-long-java.util.Map-

Upload a Large File Version in Chunks
-------------------------------------

To upload a new file version for a large file, call the
[`uploadLargeFile(InputStream fileContents, long fileSize)`][upload-large-file-version]
method on the file to be updated.  This will upload the new version of the file
in parts with integrity checks on each part, to ensure that network errors
mid-upload do not fail the entire operation.

<!-- samples x_chunked_uploads automatic_new_version -->
```java
File myFile = new File("My Large_File.txt"); 
FileInputStream stream = new FileInputStream(myFile);

String fileID = "12345";
BoxFile file = new BoxFile(api, fileID);
BoxFile.Info fileInfo = file.uploadLargeFile(inputStream, myFile.length());
```

[upload-large-file-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#uploadLargeFile-java.io.InputStream-long-

Upload a Large File Version in Chunks Including Attributes
----------------------------------------------------------

To upload a new file version for a large file, including attributes, call the
[`uploadLargeFile(InputStream fileContents, long fileSize, Map<String, String> fileAttributes)`][upload-large-file-version-including-attributes]
method on the file to be updated.  This will upload the new version of the file
in parts with integrity checks on each part, to ensure that network errors
mid-upload do not fail the entire operation.

```java
File myFile = new File("My Large_File.txt"); 
FileInputStream stream = new FileInputStream(myFile);
Map<String, String> fileAttributes = new HashMap<String, String>();
fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

String fileID = "12345";
BoxFile file = new BoxFile(api, fileID);
BoxFile.Info fileInfo = file.uploadLargeFile(inputStream, myFile.length(), fileAttributes);
```

[upload-large-file-version-including-attributes]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#uploadLargeFile-java.io.InputStream-long-java.util.Map-

Upload a Large File Or File Version Manually
--------------------------------------------

To start the process of uploading a large file or file version, first create a
new upload session with
[`BoxFolder#createUploadSession(String fileName, String fileSize)`][create-upload-session]
for a new file, or
[`BoxFile#createUploadSession(long fileSize)`][create-upload-session-version]
for a new file version.  Once the upload session is created, all other steps
are identical for both cases.

<!-- sample post_files_upload_sessions -->
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

Both of these methods will return a 

Once the upload session is created, the large file can be uploaded in chunks with the
[`uploadPart(InputStream stream, long offset, int partSize, long totalSizeOfFile)`][upload-part]
method of the session instance.  If there is a failure in uploading any of the
parts, the failed part can be uploaded again without affecting the other parts.

<!-- sample put_files_upload_sessions_id -->
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

    //Upload a part. It can be uploaded asynchorously
    BoxFileUploadSessionPart part = session.uploadPart(dis, offset, (int)partSize, fileSize);
    parts.add(part);

    //Increase the offset and proceesed bytes to calculate the Content-Range header.
    processed += partSize;
    offset += partSize;
}
```

At any point in time, the list of parts that have been uploaded successfully can be retrieved with the
[`listParts(int offset, int limit)`][list-parts] method of the session instance.

<!-- sample get_files_upload_sessions_id_parts -->
```java
//The following snippet retrives first 1000 parts that are uploaded.
BoxFileUploadSessionPartList partList = session.listParts(0, 1000);
List<BoxFileUploadSessionPart> parts = partList.getEntries();
```

Once all the parts are uploaded successfully, the upload session can be committed with the
[`commit(String digest, List<BoxFileUploadSessionPart> parts, Map<String, String> attributes, String ifMatch, String ifNoneMatch)`][upload-session-commit] method.

<!-- sample post_files_upload_sessions_id_commit -->
```java
//Creates the file hash
byte[] digestBytes = digest.digest();
//Base64 encoding of the hash
String digestStr = Base64.encode(digestBytes);

//Commit the upload session. If there is a failure, abort the commit.
BoxFile.Info fileInfo = session.commit(digestStr, parts, null, null, null);
```

The upload session can be aborted at any time with the
[`abort()`][upload-session-abort] method of the session instance.  This will
cancel the upload and any parts that were already uploaded will be lost.

<!-- sample delete_files_upload_sessions_id -->
```java
session.abort();
```

The upload session status can be retrieved at any time with the [`getStatus()`][upload-session-status] method.
This call will update the parts processed and other information in the session info instance.

<!-- sample get_files_upload_sessions_id -->
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

To move a file from one folder into another, call
[`move(BoxFolder destination)`][move] on the file to be moved with the
destination folder.

```java
String fileID = "1234";
String destinationFolderID = "5678";
BoxFile file = new BoxFile(api, fileID);
BoxFolder destinationFolder = new BoxFolder(destinationFolderID);
file.move(destinationFolder)
```

To avoid name conflicts in the destination folder, you can optionally provide a
new name for the file to [`move(BoxFolder destination, String newName)`][move-rename].  The file
will be placed into the destination folder with the new name.

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
[`copy(BoxFolder destination)`][copy] and
[`copy(BoxFolder destination, String newName)`][copy2] methods.

<!-- sample post_files_id_copy -->
```java
// Copy a file into the user's root folder
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
BoxFile file = new BoxFile(api, "id");
BoxFile.Info copiedFileInfo = file.copy(rootFolder, "New Name");
```

[copy]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#copy-com.box.sdk.BoxFolder-
[copy2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#copy-com.box.sdk.BoxFolder-java.lang.String-

Delete a File
-------------

Calling the [`delete()`][delete] method will move the file to the user's trash.

<!-- sample delete_files_id -->
```java
BoxFile file = new BoxFile(api, "id");
file.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#delete--

Get Previous Versions of a File
-------------------------------

For users with premium accounts, versions of a file can be retrieved with the
[`getVersions()`][get-versions] method. By default, it will return up to 1000 file versions with all default fields set.

<!-- sample get_files_id_versions -->
```java
BoxFile file = new BoxFile(api, "id");
Collection<BoxFileVersion> versions = file.getVersions();
for (BoxFileVersion version : versions) {
    System.out.format("SHA1 of \"%s\": %s\n", file.getInfo().getName(), version.getSha1());
}
```

File versions can be retrieved with specified starting position with the
[`getVersionsRange(long offset, long limit)`][get-versions-range] method.
You can use the `limit` and `offset` parameters to page through the all available file versions.

```java
BoxFile file = new BoxFile(api, "id");
Collection<BoxFileVersion> versions = file.getVersionsRange(1000, 2000);
for (BoxFileVersion version : versions) {
    System.out.format("SHA1 of \"%s\": %s\n", file.getInfo().getName(), version.getSha1());
}
```

You can specify selected fields to be returned while getting versions information. 
Assume we want to get version SHA1 and version number:
```java
BoxFile file = new BoxFile(api, "id");
Collection<BoxFileVersion> versions = file.getVersions("sha1", "version_number");
for (BoxFileVersion version : versions) {
    System.out.format("SHA1 of \"%d\": %s\n", version.getVersionNumber(), version.getSha1());
}
```
You can find a list of available fields at [`BoxFile.ALL_VERSION_FIELDS`][versions-fields].

[get-versions]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getVersions--
[versions-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#ALL_VERSION_FIELDS--

Upload a New Version of a File
------------------------------

New versions of a file can be uploaded with the
[`uploadNewVersion(InputStream fileContents)`][upload-new-version]
method.

<!-- sample post_files_id_content -->
```java
BoxFile file = new BoxFile(api, "id");
FileInputStream stream = new FileInputStream("My File.txt");
file.uploadNewVersion(stream);
```

[upload-new-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#uploadNewVersion-java.io.InputStream-

Download a Previous Version of a File
-------------------------------------

For users with premium accounts, previous versions of a file can be downloaded
by calling [`download(OutputStream output)`][download-version].

<!-- sample get_files_id_content for_version -->
```java
BoxFile file = new BoxFile(api, "id");
Collection<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.iterator().next();

FileOutputStream stream = new FileOutputStream(firstVersion.getName());
firstVersion.download(stream);
stream.close();
```

[download-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#download-java.io.OutputStream-

Promote a Previous Version of a File
------------------------------------

A previous version of a file can be promoted with the [`promote()`][promote]
method to become the current version of the file.

<!-- sample post_files_id_versions_current -->
```java
BoxFile file = new BoxFile(api, "id");
Collection<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.iterator().next();
firstVersion.promote();
```

[promote]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#promote--

Delete a Previous Version of a File
-----------------------------------

A version of a file can be deleted and moved to the trash by calling
[`delete()`][delete-version].

<!-- sample delete_files_id_versions_id -->
```java
BoxFile file = new BoxFile(api, "id");
Collection<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.iterator().next();
firstVersion.delete();
```

[delete-version]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersion.html#delete--

Lock a File
-----------

A file can be locked indefinitely by calling [`lock()`][lock] on the file to
be locked.  A locked file cannot be modified by any other user until it is
unlocked.  This is useful if you want to "check out" a file while you're working
on it, to ensure that other collaborators do not make changes while your changes
are in progress.

```java
BoxFile file = new BoxFile(api, "id");
file.lock();
```

When locking a file, you can optionally prevent other users from downloading the
file in addition to prevent changes by calling
[`lock(boolean preventDownload)`][lock-download] with `true`.

```java
// Lock the file and prevent downloading
BoxFile file = new BoxFile(api, "id");
file.lock(true);
```

You can also set a date when the lock will automatically be released by
calling [`lock(Date expirationDate)`][lock-expires] with the date on
which the lock should expire.  This is recommended to prevent a file
from accidentally being locked longer than intended.

```java
final long ONE_WEEK_MILLIS = 1000 * 60 * 60 * 24 * 7;
long expirationTimestamp = System.currentTimeMillis() + ONE_WEEK_MILLIS;
Date expirationTime = new Date(expirationTimestamp);
BoxFile file = new BoxFile(api, "id");
file.lock(expirationTime);
```

Both options can be passed together to
[`lock(boolean preventDownload, Date expireTime)`][lock-both].

[lock]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#lock-java.util.Date-
[lock-download]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#lock-boolean-
[lock-expires]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#lock-java.util.Date-
[lock-both]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#lock-java.util.Date-boolean-

Unlock a File
-------------

A file can be unlocked by calling [`unlock()`][unlock].

```java
BoxFile file = new BoxFile(api, "id");
file.unlock();
```

[unlock]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#unlock--

Find File for Shared Link
-------------------------

To get the file information for a shared link, you can call
[`BoxItem.getSharedItem(BoxAPIConnection api, String sharedLink)`][get-shared-item]
with the shared link to get information about the file behind it.

```java
String sharedLink = "https://app.box.com/s/abcdefghijklmnopqrstuvwxyz123456";
BoxItem.Info itemInfo = BoxItem.getSharedItem(api, sharedLink);
```

If the shared link is password-protected, call 
[`BoxItem.getSharedItem(BoxAPIConnection api, String sharedLink, String password)`][get-shared-item-password]
with the shared link and password.

<!-- sample get_shared_items -->
```java
String sharedLink = "https://app.box.com/s/abcdefghijklmnopqrstuvwxyz123456";
String password = "letmein";
BoxItem.Info itemInfo = BoxItem.getSharedItem(api, sharedLink, password);
```

[get-shared-item]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#getSharedItem-com.box.sdk.BoxAPIConnection-java.lang.String-
[get-shared-item-password]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#getSharedItem-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String-

Download File from Shared Link
---------------

A file can be downloaded via a shared link
by calling [`downloadFromSharedLink(BoxAPIConnection api, OutputStream output, String sharedLink)`][download-from-shared-link]
and providing an `OutputStream` where the file's contents will be written and shared link of the file.

If the shared link is password-protected, call 
[`downloadFromSharedLink(BoxAPIConnection api, OutputStream output, String sharedLink, String password)`][download-from-shared-link-password]
method.

```java
FileOutputStream stream = new FileOutputStream("My File.txt");
String sharedLink = "https://cloud.box.com/s/12339wbq4c7y2xd3drg4j9j9wer3ptt6n";
String password = "Secret123@";
BoxFile.downloadFromSharedLink(api, stream, sharedLink, password);
stream.close();
```

Download progress can be tracked by providing a [`ProgressListener`][progress]
to [` downloadFromSharedLink(BoxAPIConnection api, OutputStream output, String sharedLink, String password, ProgressListener listener)`][download-from-shared-link-password-progress].
The `ProgressListener` will then receive progress updates as the download
completes.

```java
FileOutputStream stream = new FileOutputStream("My File.txt");
// Provide a ProgressListener to monitor the progress of the download.
BoxFile.downloadFromSharedLink(api, stream, sharedLink, password, new ProgressListener() {
    public void onProgressChanged(long numBytes, long totalBytes) {
        double percentComplete = numBytes / totalBytes;
    }
});
stream.close();
```
[download-from-shared-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#downloadFromSharedLink-com.box.sdk.BoxAPIConnection-java.io.OutputStream-java.lang.String-
[download-from-shared-link-password]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#downloadFromSharedLink-com.box.sdk.BoxAPIConnection-java.io.OutputStream-java.lang.String-java.lang.String-
[download-from-shared-link-password-progress]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#downloadFromSharedLink-com.box.sdk.BoxAPIConnection-java.io.OutputStream-java.lang.String-java.lang.String-com.box.sdk.ProgressListener-

Create a Shared Link
--------------------

A shared link for a file can be generated by calling
[`createSharedLink(BoxSharedLinkRequest sharedLinkRequest)`][create-shared-link].

<!-- sample put_files_id add-shared-link-->
```java
// Optionally we can calculate and set the date when shared link will automatically be disabled
final long ONE_WEEK_MILLIS = 1000 * 60 * 60 * 24 * 7;
long unsharedTimestamp = System.currentTimeMillis() + ONE_WEEK_MILLIS;
Date unsharedDate = new Date(unsharedTimestamp);

BoxFile file = new BoxFile(api, "id");
BoxSharedLinkRequest sharedLinkRequest = new BoxSharedLinkRequest()
    .access(OPEN)
    .permissions(true, true)
    .unsharedDate(unsharedDate);
BoxSharedLink sharedLink = file.createSharedLink(sharedLinkRequest);
```

A set of shared link access level constants are available through the SDK for convenience:
* `BoxSharedLink.Access.OPEN`
* `BoxSharedLink.Access.COLLABORATORS`
* `BoxSharedLink.Access.COMPANY`
* `BoxSharedLink.Access.DEFAULT`

[create-shared-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createSharedLink-com.box.sdk.sharedlink.BoxSharedLinkRequest-

Get a Shared Link
-----------------

Retrieve the shared link for a file by calling
[`getSharedLink()`][get-shared-link].

<!-- sample get_files_id get_shared_link -->
```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();
BoxSharedLink link = info.getSharedLink();
String url = link.getUrl();
```

[get-shared-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.Info.html#getSharedLink--

Update a Shared Link
--------------------

A shared link for a file can be updated by calling the same method as used when creating a shared link,
[`createSharedLink(BoxSharedLinkRequest sharedLinkRequest)`][create-shared-link].

<!-- sample put_files_id update_shared_link -->
```java
BoxFile file = new BoxFile(api, "id");
BoxSharedLinkRequest sharedLinkRequest = new BoxSharedLinkRequest()
    .access(OPEN)
    .permissions(true, true);
BoxSharedLink sharedLink = file.createSharedLink(sharedLinkRequest);
```

Remove a Shared Link
--------------------

A shared link for a file can be removed by calling [`removeSharedLink()`][remove-shared-link].

<!-- sample put_files_id remove_shared_link -->
```java
BoxFile file = new BoxFile(api, "12345");
BoxFile.Info info = file.getInfo();
info.removeSharedLink();
file.updateInfo(info);
```

[remove-shared-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#removeSharedLink--

Add a Collaborator
------------------

You can invite another person to collaborate on a file by email with
[`collaborate(String emailAddress, BoxCollaboration.Role role, Boolean notify, Boolean canViewPath, Date expiresAt, Boolean isAccessOnly)`][share-a-file].
 
The `notify` parameter will determine if the user or group will receive an
email notification when being added as a collaborator.  This option is only
available to enterprise administrators.
 
The `canViewPath` parameter allows the invitee to see the entire list of ancestor
folders of the associated file. The user will not gain privileges in any ancestor
folder, but will be able to see the whole path to that file in the owner's account.

The `expiresAt` parameter allows the owner to set a date-time in the future when
the collaboration should expire.

The `isAccessOnly` parameter allows the owner to set the collaboration to be
access only collaboration.

The `notify`, `canViewPath`, `expiresAt` and `isAccessOnly` parameters can be left as `null`.
 
```java
BoxFile file = new BoxFile(api, "id");
BoxCollaboration.Info collabInfo = file.collaborate("testuser@example.com", BoxCollaboration.Role.EDITOR, true, true);
``` 
 
Alternatively, if you know the user's ID, you can invite them directly
without needing to know their email address with the
[`collaborate(BoxCollaborator user, BoxCollaboration.Role role, Boolean notify, Boolean canViewPath, Date expiresAt, Boolean isAccessOnly)`][share-a-file-userID]
 
```java
BoxUser collaborator = new BoxUser(api, "user-id");
BoxFile file = new BoxFile(api, "file-id");
BoxCollaboration.Info collabInfo = file.collaborate(collaborator, BoxCollaboration.Role.EDITOR, true, true);
```
 
[share-a-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#collaborate-java.lang.String-com.box.sdk.BoxCollaboration.Role-java.lang.Boolean-java.lang.Boolean-java.lang.String-java.util.Date-java.lang.Boolean-
[share-a-file-userID]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#collaborate-com.box.sdk.BoxCollaborator-com.box.sdk.BoxCollaboration.Role-java.lang.Boolean-java.lang.Boolean-java.lang.String-java.util.Date-java.lang.Boolean-


Get an Embed Link
-----------------

A file embed link can be generated by calling [`getPreviewLink()`][get-preview-link].

```java
BoxFile file = new BoxFile(api, "id");
URL embedLink = file.getPreviewLink();
```

[get-preview-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getPreviewLink--

Create Metadata
---------------

Metadata can be created on a file by calling
[`createMetadata(Metadata metadata)`][create-metadata],
[`createMetadata(String typeName, Metadata metadata)`][create-metadata-2], or
[`createMetadata(String typeName, String scope, Metadata metadata)`][create-metadata-3].

<!-- sample post_files_id_metadata_id_id -->
```java
// Add property "foo" with value "bar" to the default metadata properties
BoxFile file = new BoxFile(api, "id");
file.createMetadata(new Metadata().add("/foo", "bar"));
```

Note: This method will only succeed if the provided metadata template is not currently applied to the file, otherwise
it will fail with a Conflict error. To get to know how to edit existing metadata please
go to [set metadata](#set-metadata) and [update metadata](#update-metadata) sections.

[create-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata-com.box.sdk.Metadata-
[create-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata-java.lang.String-com.box.sdk.Metadata-
[create-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#createMetadata-java.lang.String-java.lang.String-com.box.sdk.Metadata-
[update-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#updateMetadata-com.box.sdk.Metadata-

Set Metadata
------------

To set metadata on a file, call [`setMetadata(String templateName, String scope, Metadata metadata)`][set-metadata].
This method will try to create provided metadata on a file. However, if metadata has already been applied to this file,
it will overwrite values of metadata keys specified in the `metadata` parameter. The metadata keys not specified in
the `metadata` parameter will remain unchanged.

```java
BoxFile file = new BoxFile(api, "id");
file.setMetadata("test_template", "enterprise", new Metadata().add("/foo", "bar"));
```

Note: If you want to set new metadata on a file including hard reset of the current metadata 
(also removing keys not specified in the `metadata` param):
first delete metadata as specified in [delete metadata](#delete-metadata) section and then set new metadata again.

[set-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#setMetadata-java.lang.String-java.lang.String-com.box.sdk.Metadata-

Get Metadata
------------

Retrieve a file's Metadata by calling [`getMetadata()`][get-metadata],
[`getMetadata(String typeName)`][get-metadata-2], or
[`getMetadata(String typeName, String scope)`][get-metadata-3].
These methods return a [`Metadata`][metadata] object, which allows access to metadata values.

<!-- sample get_files_id_metadata_id_id -->
```java
// Get the default free-form metadata properties
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

// Multiselect metadata fields
List<String> multiSelectValues = metadata.getMultiSelect("/categories");
```

[metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/Metadata.html
[get-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata--
[get-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata-java.lang.String-
[get-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata-java.lang.String-java.lang.String-

Update Metadata
---------------

Update a file's Metadata by calling [`updateMetadata(Metadata properties)`][update-metadata].

Note: This method will only succeed if the provided metadata template has already been applied to the file. If the file
does not have existing metadata, this method will fail with a Not Found error. This is useful in cases where you know
the file will already have metadata applied, since it will save an API call compared to `setMetadata()`.

<!-- sample put_files_id_metadata_id_id -->
```java
BoxFile file = new BoxFile(api, "id");
file.updateMetadata(new Metadata("templateScope", "templateKey").add("/foo", "bar"));
```

Also, it is possible to add multi-select fields for your file metadata by calling
[`updateMetadata(Metadata properties)`][update-metadata] with a list of values.

```java
BoxFile file = new BoxFile(api, "id");
List<String> valueList = new ArrayList<String>();
valueList.add("bar");
valueList.add("qux");
file.updateMetadata(new Metadata("templateScope", "templateKey").add("/foo", valueList));
```

If you wanted to replace all selected fields for a specified key you can use the
[`replace(String path, List<String> values)`][replace-metadata].

```java
BoxFile file = new BoxFile(api, "id");
List<String> valueList = new ArrayList<String>();
valueList.add("bar");
valueList.add("qux");
file.updateMetadata(new Metadata("templateScope", "templateKey").replace("/foo", valueList));
```

If you wanted to remove a metadata value for a specified key you can use the
[`remove(String path)`][remove-metadata].

```java
BoxFile file = new BoxFile(api, "id");
file.updateMetadata(new Metadata("templateScope", "templateKey").remove("/foo"));
```

[update-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#updateMetadata-com.box.sdk.Metadata-
[replace-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/Metadata.html#replace-java.lang.String-java.util.List-
[remove-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/Metadata.html#remove-java.lang.String-

Delete Metadata
---------------

A file's Metadata can be deleted by calling
[`deleteMetadata()`][delete-metadata],
[`deleteMetadata(String typeName)`][delete-metadata-2], or
[`deleteMetadata(String typeName, String scope)`][delete-metadata-3].

<!-- sample delete_files_id_metadata_id_id -->
```java
BoxFile file = new BoxFile(api, "id");
file.deleteMetadata("myMetadataTemplate");
```

[delete-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata--
[delete-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata-java.lang.String-
[delete-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata-java.lang.String-java.lang.String-

Get All Metadata on File
--------------

Calling the [`getAllMetadata()`][get-all-metadata] method on a file will return
an iterable that will page through all of the metadata associated with the file.

<!-- sample get_files_id_metadata -->
```java
BoxFile file = new BoxFile(api, "id");
Iterable<Metadata> metadataList = file.getAllMetadata();
for (Metadata metadata : metadataList) {
    // Do something with the metadata.
}
```

[get-all-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getAllMetadata-java.lang.String...-

Set Classification on File
--------------------------

Calling the [`setClassification(String classificationType)`][set-classification] method on a file will add a 
classification template on the file. This method will return the classification type applied on the file. The
classification types include `Public`, `Internal`, and `Confidential`. `Public` being the most available permission,
`Internal` which restricts the specified file to company and collaborators only, and finally, `Confidential`, which is
for collaborators only.

```java
BoxFile file = new BoxFile(api, "id");
String classificationType = file.setClassification("Public");
```

It is important to note that this call will attempt to create a classification on the file first, if one already exists
then it will do the update. If you already know that a classification exists on the file and would like to save an API
call, we encourage you to use the [`updateClassification(String classificationType)`][update-classification] method.

```java
BoxFile file = new BoxFile(api, "id");
String classificationType = file.updateClassification("Public");
```

[set-classification]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#setClassification-java.lang.String...-
[update-classification]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#updateClassification-java.lang.String...-

Get Classification on File
--------------------------

To retrieve the classification assigned on the file, use the [`getClassification()`][get-classification] method. This
will return the classification type on the file.

```java
BoxFile file = new BoxFile(api, "id");
String classificationType = file.getClassification();
```

[get-classification]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getClassification-java.lang.String...-

Remove Classification on File
-----------------------------

To remove classification from the file, use the [`deleteClassification()`][delete-classification] method.

```java
BoxFile file = new BoxFile(api, "id");
file.deleteClassification();
```

[delete-classification]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteClassification--

Get File Representations
------------------------

To get the preview representations of a file, call the
[`getInfoWithRepresentations(String representationHints, String... fields)`][get-reps]
method with the [representation hints][rep-hints] to fetch, along with any other
fields on the file object to fetch simultaneously.  This method returns a [`BoxFile.Info`][box-file-info]
object that contains the representations as a list of [`Representation`][rep-obj] objects.

Note that this method only provides information about a set of available representations; your
application will need to handle checking the status of the representations and downlaoding them
via the provided content URL template.

```java
BoxFile file = new BoxFile(api, "1234");

// Get the PDF representation and file name
String repHints = "[pdf]";
BoxFile.Info fileInfo = file.getInfoWithRepresentations(repHints, "name");
List<Representation> representations = fileInfo.getRepresentations();
String name = fileInfo.getName();
```

[get-reps]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getInfoWithRepresentations-java.lang.String-java.lang.String...-
[rep-hints]: https://developer.box.com/v2.0/reference/#section-x-rep-hints-header
[rep-obj]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/Representation.html

Get Representation Content
--------------------------

To write the contents of a single file representation to an `OutputStream`, call the
[`getRepresentationContent(String representationHint, OutputStream output)`][get-rep-content]
method with an [X-Rep-Hints value][rep-hints] specifying the representation you want.

> __Note:__ This method only supports getting the contents of a single representation; if your
> X-Rep-Hints value specifies multiple representations, an arbitrary one of them will be fetched.

```java
// Read the PDF representation of file 12345 into memory
ByteArrayOutputStream output = new ByteArrayOutputStream();

BoxFile file = new BoxFile(api, "12345");
file.getRepresentationContent("[pdf]", output);
```

For representations with multiple files, e.g. multi-page images, you will need to pass an `assetPath`
parameter to specify which file you want to fetch.

```java
// If file 12345 is a document, its PNG representation will consist of one image per page of the document
// Get the image of the first page of the document and write it to a file
FileOutputStream output = new FileOutputStream("/path/to/file.png");
BoxFile file = new BoxFile(api, "12345");
file.getRepresentationContent("[png?dimensions=1024x1024]", "1.png", output);
```

Generating a representation for the selected file is an asynchronous operation and may take some time. 
Therefore, by default, the `getRepresentationContent` method periodically checks the status of the generated file and downloads it when it is ready. 
With the `maxRetries` parameter in [`getRepresentationContent(String representationHint, String assetPath, OutputStream output, int maxRetries)`][get-rep-content-overloaded], you can define 
the number of status checks for the generated file, which will be performed at intervals of 100 ms. 

If this number is exceeded, a `BoxApiException` will be thrown.

```java
FileOutputStream output = new FileOutputStream("/path/to/file.png");
BoxFile file = new BoxFile(api, "12345");
file.getRepresentationContent("[png?dimensions=1024x1024]", "1.png", output, 10);
```


[get-rep-content]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getRepresentationContent-java.lang.String-java.lang.String-java.io.OutputStream-
[get-rep-content-overloaded]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getRepresentationContent-java.lang.String-java.lang.String-java.io.OutputStream-int-
