# FileRequestsManager


- [Get file request](#get-file-request)
- [Update file request](#update-file-request)
- [Delete file request](#delete-file-request)
- [Copy file request](#copy-file-request)

## Get file request

Retrieves the information about a file request.

This operation is performed by calling function `getFileRequestById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-file-requests-id/).

<!-- sample get_file_requests_id -->
```
client.getFileRequests().getFileRequestById(fileRequestId)
```

### Arguments

- fileRequestId `String`
  - The unique identifier that represent a file request.  The ID for any file request can be determined by visiting a file request builder in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
- headers `GetFileRequestByIdHeaders`
  - Headers of getFileRequestById method


### Returns

This function returns a value of type `FileRequest`.

Returns a file request object.


## Update file request

Updates a file request. This can be used to activate or
deactivate a file request.

This operation is performed by calling function `updateFileRequestById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-file-requests-id/).

<!-- sample put_file_requests_id -->
```
client.getFileRequests().updateFileRequestById(copiedFileRequest.getId(), new FileRequestUpdateRequest.Builder().title("updated title").description("updated description").build())
```

### Arguments

- fileRequestId `String`
  - The unique identifier that represent a file request.  The ID for any file request can be determined by visiting a file request builder in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
- requestBody `FileRequestUpdateRequest`
  - Request body of updateFileRequestById method
- headers `UpdateFileRequestByIdHeaders`
  - Headers of updateFileRequestById method


### Returns

This function returns a value of type `FileRequest`.

Returns the updated file request object.


## Delete file request

Deletes a file request permanently.

This operation is performed by calling function `deleteFileRequestById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-file-requests-id/).

<!-- sample delete_file_requests_id -->
```
client.getFileRequests().deleteFileRequestById(updatedFileRequest.getId())
```

### Arguments

- fileRequestId `String`
  - The unique identifier that represent a file request.  The ID for any file request can be determined by visiting a file request builder in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
- headers `DeleteFileRequestByIdHeaders`
  - Headers of deleteFileRequestById method


### Returns

This function returns a value of type `void`.

Returns an empty response when the file request has been successfully
deleted.


## Copy file request

Copies an existing file request that is already present on one folder,
and applies it to another folder.

This operation is performed by calling function `createFileRequestCopy`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-file-requests-id-copy/).

<!-- sample post_file_requests_id_copy -->
```
client.getFileRequests().createFileRequestCopy(fileRequestId, new FileRequestCopyRequest(new FileRequestCopyRequestFolderField.Builder(fileRequest.getFolder().getId()).type(FileRequestCopyRequestFolderTypeField.FOLDER).build()))
```

### Arguments

- fileRequestId `String`
  - The unique identifier that represent a file request.  The ID for any file request can be determined by visiting a file request builder in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
- requestBody `FileRequestCopyRequest`
  - Request body of createFileRequestCopy method
- headers `CreateFileRequestCopyHeaders`
  - Headers of createFileRequestCopy method


### Returns

This function returns a value of type `FileRequest`.

Returns updated file request object.


