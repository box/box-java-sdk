Comments
========

Comment objects represent a user-created comment on a file. They can be added
directly to a file or they can be a reply to another comment.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get a Comment's Information](#get-a-comments-information)
- [Get the Comments on a File](#get-the-comments-on-a-file)
- [Add a Comment to a File](#add-a-comment-to-a-file)
- [Reply to a Comment](#reply-to-a-comment)
- [Change a Comment's Message](#change-a-comments-message)
- [Delete a Comment](#delete-a-comment)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get a Comment's Information
---------------------------

Calling [`getInfo()`][get-info] on a comment returns a snapshot of the comment's
info.

```java
BoxComment comment = new BoxComment(api, "id");
BoxComment.Info info = comment.getInfo();
```

[get-info]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxComment.html#getInfo--

Get the Comments on a File
--------------------------

You can get all of the comments on a file by calling the
[`getComments()`][get-comments] method.

```java
BoxFile file = new BoxFile(api, "id");
List<BoxComment.Info> comments = file.getComments();
```

[get-comments]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getComments--

Add a Comment to a File
-----------------------

A comment can be added to a file with the [`addComment(String message)`][add-comment]
method.

```java
BoxFile file = new BoxFile(api, "id");
file.addComment("This file is pretty cool.");
```

The comment's message can also contain @mentions by using the string
@[userid:username] anywhere within the message, where userid and username are
the ID and username of the person being mentioned. [See the documentation]
(https://developers.box.com/docs/#comments-comment-object) on the
`tagged_message` field for more information on @mentions.

```java
BoxFile file = new BoxFile(api, "id");
file.addComment("Message mentioning @[1234:user@box.com].");
```

[add-comment]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#addComment-java.lang.String-

Reply to a Comment
------------------

You can reply to a comment with the [`reply(String message)`][reply] method.

```java
BoxComment comment = new BoxComment(api, "id");
comment.reply("A reply to another comment.");
```

[reply]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxComment.html#reply-java.lang.String-

Change a Comment's Message
--------------------------

The message of a comment can be changed with the
[`changeMessage(String message)`][change-message] method.

```java
BoxComment comment = new BoxComment(api, "id");
comment.changeMessage("An edited message.");
```

[change-message]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxComment.html#changeMessage-java.lang.String-

Delete a Comment
----------------

A comment can be deleted with the [`delete()`][delete] method.

```java
BoxComment comment = new BoxComment(api, "id");
comment.delete();
```

[delete]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxComment.html#delete--
