# FolderWatermarksManager


- [Get watermark for folder](#get-watermark-for-folder)
- [Apply watermark to folder](#apply-watermark-to-folder)
- [Remove watermark from folder](#remove-watermark-from-folder)

## Get watermark for folder

Retrieve the watermark for a folder.

This operation is performed by calling function `getFolderWatermark`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-folders-id-watermark/).

<!-- sample get_folders_id_watermark -->
```
client.getFolderWatermarks().getFolderWatermark(folder.getId())
```

### Arguments

- folderId `String`
  - The unique identifier that represent a folder.  The ID for any folder can be determined by visiting this folder in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/folder/123` the `folder_id` is `123`.  The root folder of a Box account is always represented by the ID `0`. Example: "12345"
- headers `GetFolderWatermarkHeaders`
  - Headers of getFolderWatermark method


### Returns

This function returns a value of type `Watermark`.

Returns an object containing information about the
watermark associated for to this folder.


## Apply watermark to folder

Applies or update a watermark on a folder.

This operation is performed by calling function `updateFolderWatermark`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-folders-id-watermark/).

<!-- sample put_folders_id_watermark -->
```
client.getFolderWatermarks().updateFolderWatermark(folder.getId(), new UpdateFolderWatermarkRequestBody(new UpdateFolderWatermarkRequestBodyWatermarkField.Builder().imprint(UpdateFolderWatermarkRequestBodyWatermarkImprintField.DEFAULT).build()))
```

### Arguments

- folderId `String`
  - The unique identifier that represent a folder.  The ID for any folder can be determined by visiting this folder in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/folder/123` the `folder_id` is `123`.  The root folder of a Box account is always represented by the ID `0`. Example: "12345"
- requestBody `UpdateFolderWatermarkRequestBody`
  - Request body of updateFolderWatermark method
- headers `UpdateFolderWatermarkHeaders`
  - Headers of updateFolderWatermark method


### Returns

This function returns a value of type `Watermark`.

Returns an updated watermark if a watermark already
existed on this folder.Returns a new watermark if no watermark existed on
this folder yet.


## Remove watermark from folder

Removes the watermark from a folder.

This operation is performed by calling function `deleteFolderWatermark`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-folders-id-watermark/).

<!-- sample delete_folders_id_watermark -->
```
client.getFolderWatermarks().deleteFolderWatermark(folder.getId())
```

### Arguments

- folderId `String`
  - The unique identifier that represent a folder.  The ID for any folder can be determined by visiting this folder in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/folder/123` the `folder_id` is `123`.  The root folder of a Box account is always represented by the ID `0`. Example: "12345"
- headers `DeleteFolderWatermarkHeaders`
  - Headers of deleteFolderWatermark method


### Returns

This function returns a value of type `void`.

An empty response will be returned when the watermark
was successfully deleted.


