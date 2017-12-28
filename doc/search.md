Search
======

Different examples of search which have been implemented in the example [SearchExamplesAsAppUser.java](https://github.com/box/box-java-sdk/blob/master/src/example/java/com/box/sdk/example/SearchExamplesAsAppUser.java)

For more information refer [search documentation](https://developer.box.com/v2.0/reference#searching-for-content)

* [Search with Offset](#search-with-offset)
* [Search with Limit](#search-with-limit)
* [Search with Parameters](#search-with-parameters)

Search with Offset
------------------

A search can be performed in your Box instance with specified starting position with
[`searchRange(long, long, BoxSearchParameters)`][search-with-offset]

By passing in a specific Offset value this will allow you to determine a starting position to begin the search response.

```java
Long offsetValue = 10;
BoxSearch boxSearch = new BoxSearch(api);
searchParams.setType("file");
PartialCollection<BoxItems.Info> searchResults = boxSearch.searchRange(offsetValue, 10, searchParams);
```

[search-with-offset]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxSearch.html#searchRange(java.lang.Long, java.lang.Long, com.box.sdk.BoxSearchParameters)

Search with Limit
-----------------

You can also limit the items in the search response you get back by specifying a limit with
[`searchRange(long, long, BoxSearchParameters)`][search-with-offset]

```java
Long limitValue = 10;
BoxSearch boxSearch = new BoxSearch(api);
searchParams.setType("file");
PartialCollection<BoxItems.Info> searchResults = boxSearch.searchRange(10, limitValue, searchParams);
```

[search-with-offset]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxSearch.html#searchRange(java.lang.Long, java.lang.Long, com.box.sdk.BoxSearchParameters)

Search with Parameters
----------------------

You can construct a custom query param to locate the items you want with the BoxSearchParameters field
[`searchRange(long, long, BoxSearchParameters)`][search-with-offset]

```java
String query = "A query";
BoxSearch boxSearch = new BoxSearch(api);
BoxSearchParameters searchParams = new BoxSearchParameters();
searchParams.setQuery(query);
searchParams.setType("file");
PartialCollection<BoxItem.Info> searchResults = boxSearch.searchRange(10, 10, searchParams);
```

[search-with-offset]: https://box.github.io/box-java-sdk/javadoc/com/box/sdk/BoxSearch.html#searchRange(java.lang.Long, java.lang.Long, com.box.sdk.BoxSearchParameters)
