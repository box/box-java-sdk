# HubItemsManager


- [Get Box Hub items](#get-box-hub-items)
- [Manage Box Hub items](#manage-box-hub-items)

## Get Box Hub items

Retrieves all items associated with a Box Hub.

This operation is performed by calling function `getHubItemsV2025R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2025.0/get-hub-items/).

<!-- sample get_hub_items_v2025.0 -->
```
client.getHubItems().getHubItemsV2025R0(new GetHubItemsV2025R0QueryParams(createdHub.getId()))
```

### Arguments

- queryParams `GetHubItemsV2025R0QueryParams`
  - Query parameters of getHubItemsV2025R0 method
- headers `GetHubItemsV2025R0Headers`
  - Headers of getHubItemsV2025R0 method


### Returns

This function returns a value of type `HubItemsV2025R0`.

Retrieves the items associated with the specified Box Hub.


## Manage Box Hub items

Adds and/or removes Box Hub items from a Box Hub.

This operation is performed by calling function `manageHubItemsV2025R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2025.0/post-hubs-id-manage-items/).

<!-- sample post_hubs_id_manage_items_v2025.0 -->
```
client.getHubItems().manageHubItemsV2025R0(createdHub.getId(), new HubItemsManageRequestV2025R0.Builder().operations(Arrays.asList(new HubItemOperationV2025R0(HubItemOperationV2025R0ActionField.ADD, new FolderReferenceV2025R0(folder.getId())))).build())
```

### Arguments

- hubId `String`
  - The unique identifier that represent a hub.  The ID for any hub can be determined by visiting this hub in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the `hub_id` is `123`. Example: "12345"
- requestBody `HubItemsManageRequestV2025R0`
  - Request body of manageHubItemsV2025R0 method
- headers `ManageHubItemsV2025R0Headers`
  - Headers of manageHubItemsV2025R0 method


### Returns

This function returns a value of type `HubItemsManageResponseV2025R0`.




