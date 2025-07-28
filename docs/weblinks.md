# WebLinksManager


- [Create web link](#create-web-link)
- [Get web link](#get-web-link)
- [Update web link](#update-web-link)
- [Remove web link](#remove-web-link)

## Create web link

Creates a web link object within a folder.

This operation is performed by calling function `createWebLink`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-web-links/).

<!-- sample post_web_links -->
```
client.getWebLinks().createWebLink(new CreateWebLinkRequestBody.Builder(url, new CreateWebLinkRequestBodyParentField(parent.getId())).name(name).description(description).build())
```

### Arguments

- requestBody `CreateWebLinkRequestBody`
  - Request body of createWebLink method
- headers `CreateWebLinkHeaders`
  - Headers of createWebLink method


### Returns

This function returns a value of type `WebLink`.

Returns the newly created web link object.


## Get web link

Retrieve information about a web link.

This operation is performed by calling function `getWebLinkById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-web-links-id/).

<!-- sample get_web_links_id -->
```
client.getWebLinks().getWebLinkById(weblink.getId())
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- headers `GetWebLinkByIdHeaders`
  - Headers of getWebLinkById method


### Returns

This function returns a value of type `WebLink`.

Returns the web link object.


## Update web link

Updates a web link object.

This operation is performed by calling function `updateWebLinkById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-web-links-id/).

<!-- sample put_web_links_id -->
```
client.getWebLinks().updateWebLinkById(weblink.getId(), new UpdateWebLinkByIdRequestBody.Builder().name(updatedName).sharedLink(new UpdateWebLinkByIdRequestBodySharedLinkField.Builder().access(UpdateWebLinkByIdRequestBodySharedLinkAccessField.OPEN).password(password).build()).build())
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- requestBody `UpdateWebLinkByIdRequestBody`
  - Request body of updateWebLinkById method
- headers `UpdateWebLinkByIdHeaders`
  - Headers of updateWebLinkById method


### Returns

This function returns a value of type `WebLink`.

Returns the updated web link object.


## Remove web link

Deletes a web link.

This operation is performed by calling function `deleteWebLinkById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-web-links-id/).

<!-- sample delete_web_links_id -->
```
client.getWebLinks().deleteWebLinkById(weblink.getId())
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- headers `DeleteWebLinkByIdHeaders`
  - Headers of deleteWebLinkById method


### Returns

This function returns a value of type `void`.

An empty response will be returned when the web link
was successfully deleted.


