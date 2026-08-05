# QueryManager


- [Query for Box items](#query-for-box-items)
- [Create insights for Box items](#create-insights-for-box-items)

## Query for Box items

Runs a query to discover Box items using a logical predicate that can filter
across item fields and metadata templates. Results can be sorted, paginated,
and shaped to include additional item or metadata fields.

This operation is performed by calling function `createQueryV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-query/).

<!-- sample post_query_v2026.0 -->
```
client.getQuery().createQueryV2026R0(new QueryRequestBodyV2026R0.Builder(new QueryRequestBodyV2026R0QueryField.Builder(predicate).params(mapOf(entryOf("name", "John"), entryOf("age", 50))).ancestors(Arrays.asList(new QueryAncestorReferenceV2026R0("0", "folder"))).build()).limit(10).fields(Arrays.asList("box:item:name", searchFrom)).build())
```

### Arguments

- requestBody `QueryRequestBodyV2026R0`
  - Request body of createQueryV2026R0 method
- headers `CreateQueryV2026R0Headers`
  - Headers of createQueryV2026R0 method


### Returns

This function returns a value of type `QueryResultsV2026R0`.

Returns a paginated list of items matching the query.


## Create insights for Box items

Computes aggregated metrics over Box items matching a query predicate.
Filters are applied first, followed by optional grouping, after which the
requested metrics (such as `sum`, `avg`, `min`, `max`, and `count`) are
computed for each resulting group or over the entire filtered dataset.

This operation is performed by calling function `createQueryInsightV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-query-insights/).

<!-- sample post_query_insights_v2026.0 -->
```
client.getQuery().createQueryInsightV2026R0(new QueryInsightsRequestBodyV2026R0(new QueryInsightsRequestBodyV2026R0QueryField.Builder(predicate).params(mapOf(entryOf("minAmount", 0))).ancestors(Arrays.asList(new QueryAncestorReferenceV2026R0("0", "folder"))).groupBy(Arrays.asList(new QueryInsightsGroupByV2026R0.Builder(String.join("", mdPrefix, ".category")).bucketLimit(5).build())).build(), metrics))
```

### Arguments

- requestBody `QueryInsightsRequestBodyV2026R0`
  - Request body of createQueryInsightV2026R0 method
- headers `CreateQueryInsightV2026R0Headers`
  - Headers of createQueryInsightV2026R0 method


### Returns

This function returns a value of type `QueryInsightsV2026R0`.

Returns the computed insight entries.


