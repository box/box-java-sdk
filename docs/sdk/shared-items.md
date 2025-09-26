Shared Items
============

Shared Items represent files and folders on Box accessed via a shared link.

* [Get a Shared Item](#get-a-shared-item)

Get a Shared Item
-----------------

To get the file or folder information for a shared link, you can call
[`BoxItem.getSharedItem(BoxAPIConnection api, String sharedLink)`][get-shared-item]
with the shared link to get information about the item behind it.

```java
String sharedLink = "https://app.box.com/s/abcdefghijklmnopqrstuvwxyz123456";
BoxItem.Info itemInfo = BoxItem.getSharedItem(api, sharedLink);
```

If the shared link is password-protected, call 
[`BoxItem.getSharedItem(BoxAPIConnection api, String sharedLink, String password)`][get-shared-item-password]
with the shared link and password.

<!-- sample get_shared_items -->
```java
String sharedLink = "https://app.box.com/s/abcdefghijklmnopqrstuvwxyz123456";
String password = "foo";
BoxItem.Info itemInfo = BoxItem.getSharedItem(api, sharedLink, password);
```

[get-shared-item]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#getSharedItem-com.box.sdk.BoxAPIConnection-java.lang.String-
[get-shared-item-password]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#getSharedItem-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String-
