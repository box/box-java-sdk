Search
======

Different examples of search which have been implemented in the example [SearchExamplesAsAppUser.java](https://github.com/box/box-java-sdk/blob/master/src/example/java/com/box/sdk/example/SearchExamplesAsAppUser.java)

For more information refer [search documentation](https://developer.box.com/v2.0/reference#searching-for-content)

* [Search](#search)

Search
------

A search can be performed in your Box instance with specified starting position with
[`searchRange(long, long, BoxSearchParameters)`][search]

By passing in a specific Offset value this will allow you to determine a starting position to begin the search response.
By passing in a Limit value, this will allow you to determine how many response items you will get back.

```java
Long offsetValue = 10;
Long limitValue = 10;
BoxSearch boxSearch = new BoxSearch(api);
searchParams.setType("file");
PartialCollection<BoxItems.Info> searchResults = boxSearch.searchRange(offsetValue, limitValue, searchParams);
```

You can also construct a custom query param to locate the items you want with the BoxSearchParameters field
[`searchRange(long, long, BoxSearchParameters)`][search]

```java
String query = "A query";
BoxSearch boxSearch = new BoxSearch(api);
BoxSearchParameters searchParams = new BoxSearchParameters();
searchParams.setQuery(query);
searchParams.setType("file");
PartialCollection<BoxItem.Info> searchResults = boxSearch.searchRange(10, 10, searchParams);
```

[search]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSearch.html#searchRange-long-long-com.box.sdk.BoxSearchParameters-
