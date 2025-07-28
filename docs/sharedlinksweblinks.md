# SharedLinksWebLinksManager


- [Find web link for shared link](#find-web-link-for-shared-link)
- [Get shared link for web link](#get-shared-link-for-web-link)
- [Add shared link to web link](#add-shared-link-to-web-link)
- [Update shared link on web link](#update-shared-link-on-web-link)
- [Remove shared link from web link](#remove-shared-link-from-web-link)

## Find web link for shared link

Returns the web link represented by a shared link.

A shared web link can be represented by a shared link,
which can originate within the current enterprise or within another.

This endpoint allows an application to retrieve information about a
shared web link when only given a shared link.

This operation is performed by calling function `findWebLinkForSharedLink`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shared-items--web-links/).

<!-- sample get_shared_items#web_links -->
```
userClient.getSharedLinksWebLinks().findWebLinkForSharedLink(new FindWebLinkForSharedLinkQueryParams(), new FindWebLinkForSharedLinkHeaders(String.join("", "shared_link=", webLinkFromApi.getSharedLink().getUrl(), "&shared_link_password=Secret123@")))
```

### Arguments

- queryParams `FindWebLinkForSharedLinkQueryParams`
  - Query parameters of findWebLinkForSharedLink method
- headers `FindWebLinkForSharedLinkHeaders`
  - Headers of findWebLinkForSharedLink method


### Returns

This function returns a value of type `WebLink`.

Returns a full web link resource if the shared link is valid and
the user has access to it.


## Get shared link for web link

Gets the information for a shared link on a web link.

This operation is performed by calling function `getSharedLinkForWebLink`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-web-links-id--get-shared-link/).

<!-- sample get_web_links_id#get_shared_link -->
```
client.getSharedLinksWebLinks().getSharedLinkForWebLink(webLinkId, new GetSharedLinkForWebLinkQueryParams("shared_link"))
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- queryParams `GetSharedLinkForWebLinkQueryParams`
  - Query parameters of getSharedLinkForWebLink method
- headers `GetSharedLinkForWebLinkHeaders`
  - Headers of getSharedLinkForWebLink method


### Returns

This function returns a value of type `WebLink`.

Returns the base representation of a web link with the
additional shared link information.


## Add shared link to web link

Adds a shared link to a web link.

This operation is performed by calling function `addShareLinkToWebLink`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-web-links-id--add-shared-link/).

<!-- sample put_web_links_id#add_shared_link -->
```
client.getSharedLinksWebLinks().addShareLinkToWebLink(webLinkId, new AddShareLinkToWebLinkRequestBody.Builder().sharedLink(new AddShareLinkToWebLinkRequestBodySharedLinkField.Builder().access(AddShareLinkToWebLinkRequestBodySharedLinkAccessField.OPEN).password("Secret123@").build()).build(), new AddShareLinkToWebLinkQueryParams("shared_link"))
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- requestBody `AddShareLinkToWebLinkRequestBody`
  - Request body of addShareLinkToWebLink method
- queryParams `AddShareLinkToWebLinkQueryParams`
  - Query parameters of addShareLinkToWebLink method
- headers `AddShareLinkToWebLinkHeaders`
  - Headers of addShareLinkToWebLink method


### Returns

This function returns a value of type `WebLink`.

Returns the base representation of a web link with a new shared
link attached.


## Update shared link on web link

Updates a shared link on a web link.

This operation is performed by calling function `updateSharedLinkOnWebLink`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-web-links-id--update-shared-link/).

<!-- sample put_web_links_id#update_shared_link -->
```
client.getSharedLinksWebLinks().updateSharedLinkOnWebLink(webLinkId, new UpdateSharedLinkOnWebLinkRequestBody.Builder().sharedLink(new UpdateSharedLinkOnWebLinkRequestBodySharedLinkField.Builder().access(UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField.COLLABORATORS).build()).build(), new UpdateSharedLinkOnWebLinkQueryParams("shared_link"))
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- requestBody `UpdateSharedLinkOnWebLinkRequestBody`
  - Request body of updateSharedLinkOnWebLink method
- queryParams `UpdateSharedLinkOnWebLinkQueryParams`
  - Query parameters of updateSharedLinkOnWebLink method
- headers `UpdateSharedLinkOnWebLinkHeaders`
  - Headers of updateSharedLinkOnWebLink method


### Returns

This function returns a value of type `WebLink`.

Returns a basic representation of the web link, with the updated shared
link attached.


## Remove shared link from web link

Removes a shared link from a web link.

This operation is performed by calling function `removeSharedLinkFromWebLink`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-web-links-id--remove-shared-link/).

<!-- sample put_web_links_id#remove_shared_link -->
```
client.getSharedLinksWebLinks().removeSharedLinkFromWebLink(webLinkId, new RemoveSharedLinkFromWebLinkRequestBody.Builder().sharedLink(null).build(), new RemoveSharedLinkFromWebLinkQueryParams("shared_link"))
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- requestBody `RemoveSharedLinkFromWebLinkRequestBody`
  - Request body of removeSharedLinkFromWebLink method
- queryParams `RemoveSharedLinkFromWebLinkQueryParams`
  - Query parameters of removeSharedLinkFromWebLink method
- headers `RemoveSharedLinkFromWebLinkHeaders`
  - Headers of removeSharedLinkFromWebLink method


### Returns

This function returns a value of type `WebLink`.

Returns a basic representation of a web link, with the
shared link removed.


