Web Links
======

Web links are objects that point to URLs. These objects are also known as
bookmarks within the Box web application. Web link objects are treated
similarly to file objects.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Create Web Link](#create-web-link)
- [Get Web Link](#get-web-link)
- [Update Web Link](#update-web-link)
- [Delete Web Link](#delete-web-link)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Create Web Link
---------------

Calling [`BoxFolder.createWebLink(String name, URL url, String description)`][create-web-link] will let you create a new web link with a specified name and description.

<!-- sample post_web_links -->
```java
BoxFolder folder = new BoxFolder(api, id);
URL url = new URL("https://www.example.com");
folder.createWebLink("Link to Example", url, "This goes to an example page");
```

Name and description params are optional, so it is possible to create web link
with only one of them or with URL only:
[`BoxFolder.createWebLink(URL url)`][create-web-link2]

```java
BoxFolder folder = new BoxFolder(api, id);
URL url = new URL("https://www.example.com");
BoxWebLink.Info webLinkInfo = folder.createWebLink(url);
```

[create-web-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createWebLink-java.lang.String-java.net.URL-java.lang.String-
[create-web-link2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#createWebLink-java.net.URL-

Get Web Link
------------

A web link info can be retrieved by calling the [`getInfo(String... fields)`][get-web-link] method.
Optional parameters can be used to retrieve specific fields of the Device Pin object.

<!-- sample get_web_links_id -->
```java
BoxWebLink webLink = new BoxWebLink(api, id);
BoxWebLink.Info webLinkInfo = webLink.getInfo();
```

[get-web-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebLink.html#getInfo-java.lang.String...-

Update Web Link
---------------

A web link can be updated by calling the
[`updateInfo(BoxWebLink.Info fieldsToUpdate)`][update-web-link] method.

<!-- sample put_web_links_id -->
```java
BoxWebLink webLink = new BoxWebLink(api, id);
BoxWebLink.Info webLinkInfo = webLink.new Info();
webLinkInfo.setName("new name for weblink");
webLink.updateInfo(webLinkInfo);
```

[update-web-link]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebLink.html#updateInfo-com.box.sdk.BoxWebLink.Info-

Create a Shared Link
--------------------

You can create a shared link for a web link by calling the
[`createSharedLink(BoxSharedLink.Access accessLevel, Date expirationDate, BoxSharedLink.Permissions permissions)`][create-shared-link]
method.

```java
BoxWebLink webLink = new BoxWebLink(api, "id");
SharedLink link = webLink.createSharedLink(BoxSharedLink.Access.OPEN, null,
    permissions);
```

[create-shared-link]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxWebLink.html#createSharedLink-com.box.sdk.BoxSharedLink.Access-java.util.Date-com.box.sdk.BoxSharedLink.Permissions-

Remove a Shared Link
--------------------

You can remove a shared link for a web link by calling the [`removeSharedLink`](remove-shared-link) method.

```java
BoxWebLink webLink = new BoxWebLink(api, "12345");
BoxWebLink.Info webLinkInfo = webLink.getInfo();
info.removeSharedLink()
webLink.updateInfo(info)
```

[remove-shared-link]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxWebLink.html#removeSharedLink--


Delete Web Link
---------------

A web link can be deleted by calling the [`delete()`][delete] method.

<!-- sample delete_web_links_id -->
```java
BoxWebLink webLink = new BoxWebLink(api, id);
webLink.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebLink.html#delete--
