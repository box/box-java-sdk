# RecentItemsManager


- [List recently accessed items](#list-recently-accessed-items)

## List recently accessed items

Returns information about the recent items accessed
by a user, either in the last 90 days or up to the last
1000 items accessed.

This operation is performed by calling function `getRecentItems`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-recent-items/).

<!-- sample get_recent_items -->
```
client.getRecentItems().getRecentItems()
```

### Arguments

- queryParams `GetRecentItemsQueryParams`
  - Query parameters of getRecentItems method
- headers `GetRecentItemsHeaders`
  - Headers of getRecentItems method


### Returns

This function returns a value of type `RecentItems`.

Returns a list recent items access by a user.


