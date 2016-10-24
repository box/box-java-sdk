Collections
======

Collections contain information about the items contained inside of them, including files and folders. 
The only collection available currently is a “Favorites” collection. 
The contents of the collection are discovered in a similar way in which the contents of a folder are discovered.

* [Get Collections](#get-collections)
* [Get Collection Items](#get-collection-items)
* [Create or Delete](#create-or-delete)

Get Collections
--------------

Existing collections can be retrieved by calling the [`getAllCollections(BoxAPIConnection)`][get-collections] method.
Currently only "favorites" collection is supported.

```java
Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(boxAPIConnection);
for (BoxCollection.Info collectionInfo : collections) {
	//Do something with the collection.
}
```

[get-collections]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollection.html#getAllCollections(com.box.sdk.BoxAPIConnection)

Get Collection Items
--------------

Calling the [`getItems(String...)`][get-collection-items] will return an iterable that will page through all of the items in collection.

```java
Iterable<BoxItem.Info> items = BoxCollection.getItems();
for (BoxItem.Info itemInfo : items) {
	//Do something with the item.
}
```

[get-collection-items]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollection.html#getItems(java.lang.String...)

Create or Delete
--------------

Calling [`BoxItem.setCollections(String... collectionsID)`][create-or-delete] will set list of collections this items included to.

```java
boxFile.setCollections(collectionID);
```

[create-or-delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#setCollections(java.lang.String...)