Users
=====

Users represent an individual's account on Box.

* [Get the Current User's Information](#get-the-current-users-information)

Get the Current User's Information
----------------------------------

To get the current user, call the static [`getCurrentUser(BoxAPIConnection)`]
[get-current-user] method. Then use [`getInfo()`][get-info] to get information
about the user.

```java
BoxUser user = BoxUser.getCurrentUser(api);
BoxUser.Info info = user.getInfo();
```

[get-current-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getCurrentUser(com.box.sdk.BoxAPIConnection)
[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getInfo()
