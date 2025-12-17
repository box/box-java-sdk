# DownloadsManager


- [Download file URL](#download-file-url)
- [Download file](#download-file)
- [Download file](#download-file)

## Download file URL

Get the download URL without downloading the content.

This operation is performed by calling function `getDownloadFileUrl`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-id-content/).

<!-- sample get_files_id_content -->
```
client.getDownloads().getDownloadFileUrl(uploadedFile.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- queryParams `GetDownloadFileUrlQueryParams`
  - Query parameters of downloadFile method
- headers `GetDownloadFileUrlHeaders`
  - Headers of downloadFile method


### Returns

This function returns a value of type `String`.

Returns the requested file if the client has the **follow
redirects** setting enabled to automatically
follow HTTP `3xx` responses as redirects. If not, the request
will return `302` instead.
For details, see
the [download file guide](https://developer.box.com/guides/downloads/file#download-url).If the file is not ready to be downloaded yet `Retry-After` header will
be returned indicating the time in seconds after which the file will
be available for the client to download.

This response can occur when the file was uploaded immediately before the
download request.


## Download file

Returns the contents of a file in binary format.

This operation is performed by calling function `downloadFile`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-id-content/).

<!-- sample get_files_id_content -->
```
client.getDownloads().downloadFile(uploadedFile.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- queryParams `DownloadFileQueryParams`
  - Query parameters of downloadFile method
- headers `DownloadFileHeaders`
  - Headers of downloadFile method


### Returns

This function returns a value of type `InputStream`.

Returns the requested file if the client has the **follow
redirects** setting enabled to automatically
follow HTTP `3xx` responses as redirects. If not, the request
will return `302` instead.
For details, see
the [download file guide](https://developer.box.com/guides/downloads/file#download-url).If the file is not ready to be downloaded yet `Retry-After` header will
be returned indicating the time in seconds after which the file will
be available for the client to download.

This response can occur when the file was uploaded immediately before the
download request.


## Download file

Download file to a given output stream

This operation is performed by calling function `downloadFileToOutputStream`.



```
client.getDownloads().downloadFileToOutputStream(uploadedFile.getId(), fileOutputStream)
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- outputStream `OutputStream`
  - Download file to a given output stream
- queryParams `DownloadFileToOutputStreamQueryParams`
  - Query parameters of downloadFile method
- headers `DownloadFileToOutputStreamHeaders`
  - Headers of downloadFile method


### Returns

This function returns a value of type `void`.




