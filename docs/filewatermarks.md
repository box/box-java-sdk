# FileWatermarksManager


- [Get watermark on file](#get-watermark-on-file)
- [Apply watermark to file](#apply-watermark-to-file)
- [Remove watermark from file](#remove-watermark-from-file)

## Get watermark on file

Retrieve the watermark for a file.

This operation is performed by calling function `getFileWatermark`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-id-watermark/).

<!-- sample get_files_id_watermark -->
```
client.getFileWatermarks().getFileWatermark(file.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- headers `GetFileWatermarkHeaders`
  - Headers of getFileWatermark method


### Returns

This function returns a value of type `Watermark`.

Returns an object containing information about the
watermark associated for to this file.


## Apply watermark to file

Applies or update a watermark on a file.

This operation is performed by calling function `updateFileWatermark`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-files-id-watermark/).

<!-- sample put_files_id_watermark -->
```
client.getFileWatermarks().updateFileWatermark(file.getId(), new UpdateFileWatermarkRequestBody(new UpdateFileWatermarkRequestBodyWatermarkField.Builder().imprint(UpdateFileWatermarkRequestBodyWatermarkImprintField.DEFAULT).build()))
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- requestBody `UpdateFileWatermarkRequestBody`
  - Request body of updateFileWatermark method
- headers `UpdateFileWatermarkHeaders`
  - Headers of updateFileWatermark method


### Returns

This function returns a value of type `Watermark`.

Returns an updated watermark if a watermark already
existed on this file.Returns a new watermark if no watermark existed on
this file yet.


## Remove watermark from file

Removes the watermark from a file.

This operation is performed by calling function `deleteFileWatermark`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-files-id-watermark/).

<!-- sample delete_files_id_watermark -->
```
client.getFileWatermarks().deleteFileWatermark(file.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- headers `DeleteFileWatermarkHeaders`
  - Headers of deleteFileWatermark method


### Returns

This function returns a value of type `void`.

Removes the watermark and returns an empty response.


