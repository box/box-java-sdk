Collections
===========

Collections contain information about the items contained inside of them,
including files and folders. The only collection available currently is a
“Favorites” collection. The contents of the collection are discovered in a
similar way in which the contents of a folder are discovered.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get Collections](#get-collections)
- [Get a Collection's Items](#get-a-collections-items)
- [Add Items to a Collection](#add-items-to-a-collection)
- [Remove Items from a Collection](#remove-items-from-a-collection)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get Collections
---------------

Existing collections can be retrieved by calling the
[`getAllCollections(BoxAPIConnection)`][get-collections] method. Currently only
"Favorites" collection is supported.

```java
Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(api);
for (BoxCollection.Info collectionInfo : collections) {
	// Do something with the collection.
}
```

[get-collections]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollection.html#getAllCollections-com.box.sdk.BoxAPIConnection-

Get a Collection's Items
------------------------

Every `BoxCollection` implements [`Iterable<BoxItem>`][iterator] which allows
you to iterate over the collection's contents. The iterator automatically
handles paging and will make additional network calls to load more data from Box
when necessary.

```java
BoxFolder folder = new BoxFolder(api, "id");
for (BoxItem.Info itemInfo : folder) {
    if (itemInfo instanceof BoxFile.Info) {
        BoxFile.Info fileInfo = (BoxFile.Info) itemInfo;
        // Do something with the file.
    } else if (itemInfo instanceof BoxFolder.Info) {
        BoxFolder.Info folderInfo = (BoxFolder.Info) itemInfo;
        // Do something with the folder.
    }
}
```

[iterator]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxCollection.html#iterator--

Add Items to a Collection
-------------------------

Add an item to a collection by calling
[`setCollections(BoxCollection... collections)`][set-collections] on any `BoxItem`. Note that this
method will overwrite all collections that the item belongs to.

```java
BoxCollection favorites = null;
for (BoxCollection.Info info : BoxCollection.getAllCollections(api)) {
    if (info.getCollectionType().equals("favorites")) {
        favorites = info.getResource();
        break;
    }
}
BoxFile file = new BoxFile(api, "id");
file.setCollections(favorites);
```

Remove Items from a Collection
------------------------------

Remove an item from a collection by calling
[`setCollections(BoxCollection... collections)`][set-collections] on any `BoxItem` and exclude the
collection to wish to remove it from.

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo("collections");

ArrayList<BoxCollection> collections = new ArrayList<BoxCollection>();
for (BoxCollection.Info info : info.getCollections(api)) {
    // Include every existing collection except for favorites to remove the file
    // from the favorites collection.
    if (!info.getCollectionType().equals("favorites")) {
        collections.add(info.getResource());
    }
}
file.setCollections(collections.toArray());
```

[set-collections]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#setCollections-com.box.sdk.BoxCollection...-
