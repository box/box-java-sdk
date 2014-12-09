Search
======

* [Search a Folder and Its Children](#search-a-folder-and-its-children)
* [Advanced Search](#advanced-search)

Basic Search
------------

Calling [`search(String)`][search] on a folder will recursively search the
folder and its children. To search the user's entire account, simply search
their root folder.

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
Iterable<BoxItem.Info> results = rootFolder.search("my query");
for (BoxItem.Info result : results) {
    // Do something with the search result.
}
```

[search]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#search(java.lang.String)

Advanced Search
---------------

Advanced is not yet implemented, but is coming soon.
