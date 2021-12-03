File Requests
=============

File request objects represent a file request associated with a folder.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Get a File Request's Information](#get-a-file-requests-information)
- [Copy a File Request's Information](#copy-a-file-requests-information)
- [Update a File Request's Information](#update-a-file-requests-information)
- [Delete a File Request](#delete-a-file-request)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get a File Request's Information
------------------------

Calling [`getInfo()`][get-info] returns information on a file request.

<!-- sample get_file_requests_id -->
```java
BoxFileRequest fileRequest = new BoxFileRequest(api, "id");
BoxFileRequest.Info fileRequestInfo = fileRequest.getInfo();
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileRequest.html#getInfo

Copy a File Request's Information
---------------------------

Calling  [`copyInfo(String folderId)`][copy-info] copies an existing file request that is already present 
on one folder, and applies it to another folder. If you want to set certain fields of the newly copied file request when it is created, 
set those fields in the `BoxFileRequest.Info` that you pass into this method [`copyInfo(BoxFileRequest.Info info, String folderId)`][copy-info].

<!-- sample post_file_requests_id_copy -->
```java
BoxFileRequest fileRequest = new BoxFileRequest(api, "id");
BoxFileRequest.Info fileRequestInfo = fileRequest.new Info();
fileRequestInfo.setDescription("Following documents are requested for your process");
fileRequestInfo.setIsDescriptionRequired(true);
fileRequestInfo.setStatus(BoxFileRequest.Status.ACTIVE);
fileRequestInfo = fileRequest.copyInfo(fileRequestInfo, "folderId");
```

[copy-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTask.html#copyInfo

Update a File Request's Information
---------------------------

Calling [`updateInfo(BoxFileRequest.Info info)`][update-info] updates a file request. This can be used to activate 
or deactivate a file request.

<!-- sample put_file_requests_id -->
```java
BoxFileRequest fileRequest = new BoxFileRequest(api, "id");
BoxFileRequest.Info fileRequestInfo = fileRequest.new Info();
fileRequestInfo.setDescription("Following documents are requested for your process");
fileRequestInfo.setIsDescriptionRequired(true);
fileRequestInfo.setStatus(BoxFileRequest.Status.ACTIVE);
fileRequestInfo = fileRequest.updateInfo(fileRequestInfo);
```

[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileRequest.html#updateInfo

Delete a File Request
-------------

Calling [`delete()`][delete] deletes a file request permanently.

<!-- sample delete_file_requests_id -->
```java
BoxFileRequest fileRequest = new BoxFileRequest(api, "id");
fileRequest.delete();
```

[delete]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFileRequest.html#delete
