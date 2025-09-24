# ChunkedUploadsManager

This is a manager for chunked uploads (allowed for files at least 20MB).

- [Create upload session](#create-upload-session)
- [Create upload session for existing file](#create-upload-session-for-existing-file)
- [Get upload session by URL](#get-upload-session-by-url)
- [Get upload session](#get-upload-session)
- [Upload part of file by URL](#upload-part-of-file-by-url)
- [Upload part of file](#upload-part-of-file)
- [Remove upload session by URL](#remove-upload-session-by-url)
- [Remove upload session](#remove-upload-session)
- [List parts by URL](#list-parts-by-url)
- [List parts](#list-parts)
- [Commit upload session by URL](#commit-upload-session-by-url)
- [Commit upload session](#commit-upload-session)
- [Upload big file](#upload-big-file)

## Create upload session

Creates an upload session for a new file.

This operation is performed by calling function `createFileUploadSession`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-files-upload-sessions/).

<!-- sample post_files_upload_sessions -->
```
client.getChunkedUploads().createFileUploadSession(new CreateFileUploadSessionRequestBody(parentFolderId, fileSize, fileName))
```

### Arguments

- requestBody `CreateFileUploadSessionRequestBody`
  - Request body of createFileUploadSession method
- headers `CreateFileUploadSessionHeaders`
  - Headers of createFileUploadSession method


### Returns

This function returns a value of type `UploadSession`.

Returns a new upload session.


## Create upload session for existing file

Creates an upload session for an existing file.

This operation is performed by calling function `createFileUploadSessionForExistingFile`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-files-id-upload-sessions/).

*Currently we don't have an example for calling `createFileUploadSessionForExistingFile` in integration tests*

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- requestBody `CreateFileUploadSessionForExistingFileRequestBody`
  - Request body of createFileUploadSessionForExistingFile method
- headers `CreateFileUploadSessionForExistingFileHeaders`
  - Headers of createFileUploadSessionForExistingFile method


### Returns

This function returns a value of type `UploadSession`.

Returns a new upload session.


## Get upload session by URL

Return information about an upload session.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions) endpoint.

This operation is performed by calling function `getFileUploadSessionByUrl`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-upload-sessions-id/).

<!-- sample get_files_upload_sessions_id -->
```
client.getChunkedUploads().getFileUploadSessionByUrl(statusUrl)
```

### Arguments

- url `String`
  - URL of getFileUploadSessionById method
- headers `GetFileUploadSessionByUrlHeaders`
  - Headers of getFileUploadSessionById method


### Returns

This function returns a value of type `UploadSession`.

Returns an upload session object.


## Get upload session

Return information about an upload session.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions) endpoint.

This operation is performed by calling function `getFileUploadSessionById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-upload-sessions-id/).

<!-- sample get_files_upload_sessions_id -->
```
client.getChunkedUploads().getFileUploadSessionById(uploadSessionId)
```

### Arguments

- uploadSessionId `String`
  - The ID of the upload session. Example: "D5E3F7A"
- headers `GetFileUploadSessionByIdHeaders`
  - Headers of getFileUploadSessionById method


### Returns

This function returns a value of type `UploadSession`.

Returns an upload session object.


## Upload part of file by URL

Uploads a chunk of a file for an upload session.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions)
and [`Get upload session`](e://get-files-upload-sessions-id) endpoints.

This operation is performed by calling function `uploadFilePartByUrl`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-files-upload-sessions-id/).

<!-- sample put_files_upload_sessions_id -->
```
client.getChunkedUploads().uploadFilePartByUrl(acc.getUploadPartUrl(), generateByteStreamFromBuffer(chunkBuffer), new UploadFilePartByUrlHeaders(digest, contentRange))
```

### Arguments

- url `String`
  - URL of uploadFilePart method
- requestBody `InputStream`
  - Request body of uploadFilePart method
- headers `UploadFilePartByUrlHeaders`
  - Headers of uploadFilePart method


### Returns

This function returns a value of type `UploadedPart`.

Chunk has been uploaded successfully.


## Upload part of file

Uploads a chunk of a file for an upload session.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions)
and [`Get upload session`](e://get-files-upload-sessions-id) endpoints.

This operation is performed by calling function `uploadFilePart`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-files-upload-sessions-id/).

<!-- sample put_files_upload_sessions_id -->
```
client.getChunkedUploads().uploadFilePart(acc.getUploadSessionId(), generateByteStreamFromBuffer(chunkBuffer), new UploadFilePartHeaders(digest, contentRange))
```

### Arguments

- uploadSessionId `String`
  - The ID of the upload session. Example: "D5E3F7A"
- requestBody `InputStream`
  - Request body of uploadFilePart method
- headers `UploadFilePartHeaders`
  - Headers of uploadFilePart method


### Returns

This function returns a value of type `UploadedPart`.

Chunk has been uploaded successfully.


## Remove upload session by URL

Abort an upload session and discard all data uploaded.

This cannot be reversed.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions)
and [`Get upload session`](e://get-files-upload-sessions-id) endpoints.

This operation is performed by calling function `deleteFileUploadSessionByUrl`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-files-upload-sessions-id/).

<!-- sample delete_files_upload_sessions_id -->
```
client.getChunkedUploads().deleteFileUploadSessionByUrl(abortUrl)
```

### Arguments

- url `String`
  - URL of deleteFileUploadSessionById method
- headers `DeleteFileUploadSessionByUrlHeaders`
  - Headers of deleteFileUploadSessionById method


### Returns

This function returns a value of type `void`.

A blank response is returned if the session was
successfully aborted.


## Remove upload session

Abort an upload session and discard all data uploaded.

This cannot be reversed.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions)
and [`Get upload session`](e://get-files-upload-sessions-id) endpoints.

This operation is performed by calling function `deleteFileUploadSessionById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-files-upload-sessions-id/).

<!-- sample delete_files_upload_sessions_id -->
```
client.getChunkedUploads().deleteFileUploadSessionById(uploadSessionId)
```

### Arguments

- uploadSessionId `String`
  - The ID of the upload session. Example: "D5E3F7A"
- headers `DeleteFileUploadSessionByIdHeaders`
  - Headers of deleteFileUploadSessionById method


### Returns

This function returns a value of type `void`.

A blank response is returned if the session was
successfully aborted.


## List parts by URL

Return a list of the chunks uploaded to the upload session so far.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions)
and [`Get upload session`](e://get-files-upload-sessions-id) endpoints.

This operation is performed by calling function `getFileUploadSessionPartsByUrl`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-upload-sessions-id-parts/).

<!-- sample get_files_upload_sessions_id_parts -->
```
client.getChunkedUploads().getFileUploadSessionPartsByUrl(listPartsUrl)
```

### Arguments

- url `String`
  - URL of getFileUploadSessionParts method
- queryParams `GetFileUploadSessionPartsByUrlQueryParams`
  - Query parameters of getFileUploadSessionParts method
- headers `GetFileUploadSessionPartsByUrlHeaders`
  - Headers of getFileUploadSessionParts method


### Returns

This function returns a value of type `UploadParts`.

Returns a list of parts that have been uploaded.


## List parts

Return a list of the chunks uploaded to the upload session so far.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions)
and [`Get upload session`](e://get-files-upload-sessions-id) endpoints.

This operation is performed by calling function `getFileUploadSessionParts`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-upload-sessions-id-parts/).

<!-- sample get_files_upload_sessions_id_parts -->
```
client.getChunkedUploads().getFileUploadSessionParts(uploadSessionId)
```

### Arguments

- uploadSessionId `String`
  - The ID of the upload session. Example: "D5E3F7A"
- queryParams `GetFileUploadSessionPartsQueryParams`
  - Query parameters of getFileUploadSessionParts method
- headers `GetFileUploadSessionPartsHeaders`
  - Headers of getFileUploadSessionParts method


### Returns

This function returns a value of type `UploadParts`.

Returns a list of parts that have been uploaded.


## Commit upload session by URL

Close an upload session and create a file from the uploaded chunks.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions)
and [`Get upload session`](e://get-files-upload-sessions-id) endpoints.

This operation is performed by calling function `createFileUploadSessionCommitByUrl`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-files-upload-sessions-id-commit/).

<!-- sample post_files_upload_sessions_id_commit -->
```
client.getChunkedUploads().createFileUploadSessionCommitByUrl(commitUrl, new CreateFileUploadSessionCommitByUrlRequestBody(parts), new CreateFileUploadSessionCommitByUrlHeaders(digest))
```

### Arguments

- url `String`
  - URL of createFileUploadSessionCommit method
- requestBody `CreateFileUploadSessionCommitByUrlRequestBody`
  - Request body of createFileUploadSessionCommit method
- headers `CreateFileUploadSessionCommitByUrlHeaders`
  - Headers of createFileUploadSessionCommit method


### Returns

This function returns a value of type `Files`.

Returns the file object in a list.Returns when all chunks have been uploaded but not yet processed.

Inspect the upload session to get more information about the
progress of processing the chunks, then retry committing the file
when all chunks have processed.


## Commit upload session

Close an upload session and create a file from the uploaded chunks.

The actual endpoint URL is returned by the [`Create upload session`](e://post-files-upload-sessions)
and [`Get upload session`](e://get-files-upload-sessions-id) endpoints.

This operation is performed by calling function `createFileUploadSessionCommit`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-files-upload-sessions-id-commit/).

<!-- sample post_files_upload_sessions_id_commit -->
```
client.getChunkedUploads().createFileUploadSessionCommit(uploadSessionId, new CreateFileUploadSessionCommitRequestBody(parts), new CreateFileUploadSessionCommitHeaders(digest))
```

### Arguments

- uploadSessionId `String`
  - The ID of the upload session. Example: "D5E3F7A"
- requestBody `CreateFileUploadSessionCommitRequestBody`
  - Request body of createFileUploadSessionCommit method
- headers `CreateFileUploadSessionCommitHeaders`
  - Headers of createFileUploadSessionCommit method


### Returns

This function returns a value of type `Files`.

Returns the file object in a list.Returns when all chunks have been uploaded but not yet processed.

Inspect the upload session to get more information about the
progress of processing the chunks, then retry committing the file
when all chunks have processed.


## Upload big file

Starts the process of chunk uploading a big file. Should return a File object representing uploaded file.

This operation is performed by calling function `uploadBigFile`.



```
client.getChunkedUploads().uploadBigFile(fileByteStream, fileName, fileSize, parentFolderId)
```

### Arguments

- file `InputStream`
  - The stream of the file to upload.
- fileName `String`
  - The name of the file, which will be used for storage in Box.
- fileSize `long`
  - The total size of the file for the chunked upload in bytes.
- parentFolderId `String`
  - The ID of the folder where the file should be uploaded.


### Returns

This function returns a value of type `FileFull`.




