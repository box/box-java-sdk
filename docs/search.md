# SearchManager


- [Query files/folders by metadata](#query-files-folders-by-metadata)
- [Search for content](#search-for-content)

## Query files/folders by metadata

Create a search using SQL-like syntax to return items that match specific
metadata.

By default, this endpoint returns only the most basic info about the items for
which the query matches. To get additional fields for each item, including any
of the metadata, use the `fields` attribute in the query.

This operation is performed by calling function `searchByMetadataQuery`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-metadata-queries-execute-read/).

<!-- sample post_metadata_queries_execute_read -->
```
client.getSearch().searchByMetadataQuery(new MetadataQuery.Builder(searchFrom, "0").query("name = :name AND age < :age AND birthDate >= :birthDate AND countryCode = :countryCode AND sports = :sports").queryParams(mapOf(entryOf("name", "John"), entryOf("age", 50), entryOf("birthDate", "2001-01-01T02:20:10.120Z"), entryOf("countryCode", "US"), entryOf("sports", Arrays.asList("basketball", "tennis")))).build())
```

### Arguments

- requestBody `MetadataQuery`
  - Request body of searchByMetadataQuery method
- headers `SearchByMetadataQueryHeaders`
  - Headers of searchByMetadataQuery method


### Returns

This function returns a value of type `MetadataQueryResults`.

Returns a list of files and folders that match this metadata query.


## Search for content

Searches for files, folders, web links, and shared files across the
users content or across the entire enterprise.

This operation is performed by calling function `searchForContent`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-search/).

<!-- sample get_search -->
```
client.getSearch().searchForContent(new SearchForContentQueryParams.Builder().ancestorFolderIds(Arrays.asList("0")).mdfilters(Arrays.asList(new MetadataFilter.Builder().scope(MetadataFilterScopeField.ENTERPRISE).templateKey(templateKey).filters(searchFilters).build())).build())
```

### Arguments

- queryParams `SearchForContentQueryParams`
  - Query parameters of searchForContent method
- headers `SearchForContentHeaders`
  - Headers of searchForContent method


### Returns

This function returns a value of type `SearchResultsOrSearchResultsWithSharedLinks`.

Returns a collection of search results. If there are no matching
search results, the `entries` array will be empty.


