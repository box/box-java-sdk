Recent Items
============

Recent Items returns information about files that have been accessed by a user not long ago. It keeps track of items
that were accessed either in the last 90 days or the last 1000 items accessed (both conditions must be met).

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get a User's Recent Items](#get-a-users-recent-items)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->


Get a User's Recent Items
-------------------------

Get a list of all recent items the user has by calling
[`BoxRecents.getRecentItems(BoxAPIConnection api, int limit, String... fields)`][get-recents].
This returns an ordered iterable of the [`BoxRecentItem`][recent-item] records,
which describe which item the user interacted with, when they interacted with it,
and what type of interaction it was.  Any `fields` specified relate to the items
themselves (e.g. the recent files and folders).

```java
// Get the latest 100 items the user has interacted with
Iterable<BoxRecentItem> recentItems = BoxRecents.getRecentItems(api, 100);
```

[get-recents]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRecents.html#getRecentItems-com.box.sdk.BoxAPIConnection-int-java.lang.String...-
[recent-item]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRecentItem.html
