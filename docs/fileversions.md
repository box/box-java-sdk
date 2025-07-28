# FileVersionsManager


- [List all file versions](#list-all-file-versions)
- [Get file version](#get-file-version)
- [Remove file version](#remove-file-version)
- [Restore file version](#restore-file-version)
- [Promote file version](#promote-file-version)

## List all file versions

Retrieve a list of the past versions for a file.

Versions are only tracked by Box users with premium accounts. To fetch the ID
of the current version of a file, use the `GET /file/:id` API.

This operation is performed by calling function `getFileVersions`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-id-versions/).

<!-- sample get_files_id_versions -->
```
client.getFileVersions().getFileVersions(file.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- queryParams `GetFileVersionsQueryParams`
  - Query parameters of getFileVersions method
- headers `GetFileVersionsHeaders`
  - Headers of getFileVersions method


### Returns

This function returns a value of type `FileVersions`.

Returns an array of past versions for this file.


## Get file version

Retrieve a specific version of a file.

Versions are only tracked for Box users with premium accounts.

This operation is performed by calling function `getFileVersionById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-id-versions-id/).

<!-- sample get_files_id_versions_id -->
```
client.getFileVersions().getFileVersionById(file.getId(), fileVersions.getEntries().get(0).getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- fileVersionId `String`
  - The ID of the file version. Example: "1234"
- queryParams `GetFileVersionByIdQueryParams`
  - Query parameters of getFileVersionById method
- headers `GetFileVersionByIdHeaders`
  - Headers of getFileVersionById method


### Returns

This function returns a value of type `FileVersionFull`.

Returns a specific version of a file.

Not all available fields are returned by default. Use the
[fields](#param-fields) query parameter to explicitly request
any specific fields.


## Remove file version

Move a file version to the trash.

Versions are only tracked for Box users with premium accounts.

This operation is performed by calling function `deleteFileVersionById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-files-id-versions-id/).

<!-- sample delete_files_id_versions_id -->
```
client.getFileVersions().deleteFileVersionById(file.getId(), fileVersion.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- fileVersionId `String`
  - The ID of the file version. Example: "1234"
- headers `DeleteFileVersionByIdHeaders`
  - Headers of deleteFileVersionById method


### Returns

This function returns a value of type `void`.

Returns an empty response when the file has been successfully
deleted.


## Restore file version

Restores a specific version of a file after it was deleted.
Don't use this endpoint to restore Box Notes,
as it works with file formats such as PDF, DOC,
PPTX or similar.

This operation is performed by calling function `updateFileVersionById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-files-id-versions-id/).

<!-- sample put_files_id_versions_id -->
```
client.getFileVersions().updateFileVersionById(file.getId(), fileVersion.getId(), new UpdateFileVersionByIdRequestBody.Builder().trashedAt(null).build())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- fileVersionId `String`
  - The ID of the file version. Example: "1234"
- requestBody `UpdateFileVersionByIdRequestBody`
  - Request body of updateFileVersionById method
- headers `UpdateFileVersionByIdHeaders`
  - Headers of updateFileVersionById method


### Returns

This function returns a value of type `FileVersionFull`.

Returns a restored file version object.


## Promote file version

Promote a specific version of a file.

If previous versions exist, this method can be used to
promote one of the older versions to the top of the version history.

This creates a new copy of the old version and puts it at the
top of the versions history. The file will have the exact same contents
as the older version, with the the same hash digest, `etag`, and
name as the original.

Other properties such as comments do not get updated to their
former values.

Don't use this endpoint to restore Box Notes,
as it works with file formats such as PDF, DOC,
PPTX or similar.

This operation is performed by calling function `promoteFileVersion`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-files-id-versions-current/).

<!-- sample post_files_id_versions_current -->
```
client.getFileVersions().promoteFileVersion(file.getId(), new PromoteFileVersionRequestBody.Builder().id(fileVersions.getEntries().get(0).getId()).type(PromoteFileVersionRequestBodyTypeField.FILE_VERSION).build())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- requestBody `PromoteFileVersionRequestBody`
  - Request body of promoteFileVersion method
- queryParams `PromoteFileVersionQueryParams`
  - Query parameters of promoteFileVersion method
- headers `PromoteFileVersionHeaders`
  - Headers of promoteFileVersion method


### Returns

This function returns a value of type `FileVersionFull`.

Returns a newly created file version object.


