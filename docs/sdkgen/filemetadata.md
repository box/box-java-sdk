# FileMetadataManager


- [List metadata instances on file](#list-metadata-instances-on-file)
- [Get metadata instance on file](#get-metadata-instance-on-file)
- [Create metadata instance on file](#create-metadata-instance-on-file)
- [Update metadata instance on file](#update-metadata-instance-on-file)
- [Remove metadata instance from file](#remove-metadata-instance-from-file)

## List metadata instances on file

Retrieves all metadata for a given file.

This operation is performed by calling function `getFileMetadata`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-id-metadata/).

<!-- sample get_files_id_metadata -->
```
client.getFileMetadata().getFileMetadata(file.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- headers `GetFileMetadataHeaders`
  - Headers of getFileMetadata method


### Returns

This function returns a value of type `Metadatas`.

Returns all the metadata associated with a file.

This API does not support pagination and will therefore always return
all of the metadata associated to the file.


## Get metadata instance on file

Retrieves the instance of a metadata template that has been applied to a
file.

This operation is performed by calling function `getFileMetadataById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-id-metadata-id-id/).

<!-- sample get_files_id_metadata_id_id -->
```
client.getFileMetadata().getFileMetadataById(file.getId(), GetFileMetadataByIdScope.GLOBAL, "properties")
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- scope `GetFileMetadataByIdScope`
  - The scope of the metadata template. Example: "global"
- templateKey `String`
  - The name of the metadata template. Example: "properties"
- headers `GetFileMetadataByIdHeaders`
  - Headers of getFileMetadataById method


### Returns

This function returns a value of type `MetadataFull`.

An instance of the metadata template that includes
additional "key:value" pairs defined by the user or
an application.


## Create metadata instance on file

Applies an instance of a metadata template to a file.

In most cases only values that are present in the metadata template
will be accepted, except for the `global.properties` template which accepts
any key-value pair.

This operation is performed by calling function `createFileMetadataById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-files-id-metadata-id-id/).

<!-- sample post_files_id_metadata_id_id -->
```
client.getFileMetadata().createFileMetadataById(file.getId(), CreateFileMetadataByIdScope.ENTERPRISE, templateKey, mapOf(entryOf("name", "John"), entryOf("age", 23), entryOf("birthDate", "2001-01-03T02:20:50.520Z"), entryOf("countryCode", "US"), entryOf("sports", Arrays.asList("basketball", "tennis"))))
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- scope `CreateFileMetadataByIdScope`
  - The scope of the metadata template. Example: "global"
- templateKey `String`
  - The name of the metadata template. Example: "properties"
- requestBody `Map<String, Object>`
  - Request body of createFileMetadataById method
- headers `CreateFileMetadataByIdHeaders`
  - Headers of createFileMetadataById method


### Returns

This function returns a value of type `MetadataFull`.

Returns the instance of the template that was applied to the file,
including the data that was applied to the template.


## Update metadata instance on file

Updates a piece of metadata on a file.

The metadata instance can only be updated if the template has already been
applied to the file before. When editing metadata, only values that match
the metadata template schema will be accepted.

The update is applied atomically. If any errors occur during the
application of the operations, the metadata instance will not be changed.

This operation is performed by calling function `updateFileMetadataById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-files-id-metadata-id-id/).

<!-- sample put_files_id_metadata_id_id -->
```
client.getFileMetadata().updateFileMetadataById(file.getId(), UpdateFileMetadataByIdScope.ENTERPRISE, templateKey, Arrays.asList(new UpdateFileMetadataByIdRequestBody.Builder().op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE).path("/name").value("Jack").build(), new UpdateFileMetadataByIdRequestBody.Builder().op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE).path("/age").value(24L).build(), new UpdateFileMetadataByIdRequestBody.Builder().op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE).path("/birthDate").value("2000-01-03T02:20:50.520Z").build(), new UpdateFileMetadataByIdRequestBody.Builder().op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE).path("/countryCode").value("CA").build(), new UpdateFileMetadataByIdRequestBody.Builder().op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE).path("/sports").value(Arrays.asList("football")).build()))
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- scope `UpdateFileMetadataByIdScope`
  - The scope of the metadata template. Example: "global"
- templateKey `String`
  - The name of the metadata template. Example: "properties"
- requestBody `List<UpdateFileMetadataByIdRequestBody>`
  - Request body of updateFileMetadataById method
- headers `UpdateFileMetadataByIdHeaders`
  - Headers of updateFileMetadataById method


### Returns

This function returns a value of type `MetadataFull`.

Returns the updated metadata template instance, with the
custom template data included.


## Remove metadata instance from file

Deletes a piece of file metadata.

This operation is performed by calling function `deleteFileMetadataById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-files-id-metadata-id-id/).

<!-- sample delete_files_id_metadata_id_id -->
```
client.getFileMetadata().deleteFileMetadataById(file.getId(), DeleteFileMetadataByIdScope.ENTERPRISE, templateKey)
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- scope `DeleteFileMetadataByIdScope`
  - The scope of the metadata template. Example: "global"
- templateKey `String`
  - The name of the metadata template. Example: "properties"
- headers `DeleteFileMetadataByIdHeaders`
  - Headers of deleteFileMetadataById method


### Returns

This function returns a value of type `void`.

Returns an empty response when the metadata is
successfully deleted.


