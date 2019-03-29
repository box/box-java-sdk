Search
======

Different examples of search which have been implemented in the example [SearchExamplesAsAppUser.java](https://github.com/box/box-java-sdk/blob/master/src/example/java/com/box/sdk/example/SearchExamplesAsAppUser.java)

For more information refer to the
[Search API documentation](https://developer.box.com/v2.0/reference#searching-for-content).

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Search](#search)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Search
------

A search can be performed in your Box instance with specified starting position with
[`searchRange(long offset, long limit, BoxSearchParameters queryParams)`][search]

You can use the `limit` and `offset` parameters to page through the search results.

```java
// Find the first 10 files matching "taxes"
long offsetValue = 0;
long limitValue = 10;
BoxSearch boxSearch = new BoxSearch(api);
BoxSearchParameters searchParams = new BoxSearchParameters();
searchParams.setQuery("taxes");
searchParams.setType("file");
PartialCollection<BoxItem.Info> searchResults = boxSearch.searchRange(offsetValue, limitValue, searchParams);
```

You can also sort the search results with the `sort` and `direction` flags passed into BoxSearch. This allows for the
order of items returned to be maintained. The `sort` field can only be set to `modifed_at` currently. For direction you
can specify either `DESC` for descending order or `ASC` for ascending order to sort the results.

```java
long offsetValue = 0;
long limitValue = 10;
BoxSearch boxSearch = new BoxSearch(api);
BoxSearchParameters searchParams = new BoxSearchParameters();
searchParams.setQuery("taxes");
searchParams.setSort("modified_at");
searchParams.setDirection("DESC");
PartialCollection<BoxItem.Info> searchResults = boxSearch.searchRange(offsetValue, limitValue, searchParams);
```
