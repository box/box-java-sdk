# TrashedWebLinksManager


- [Restore web link](#restore-web-link)
- [Get trashed web link](#get-trashed-web-link)
- [Permanently remove web link](#permanently-remove-web-link)

## Restore web link

Restores a web link that has been moved to the trash.

An optional new parent ID can be provided to restore the  web link to in case
the original folder has been deleted.

This operation is performed by calling function `restoreWeblinkFromTrash`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-web-links-id/).

<!-- sample post_web_links_id -->
```
client.getTrashedWebLinks().restoreWeblinkFromTrash(weblink.getId())
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- requestBody `RestoreWeblinkFromTrashRequestBody`
  - Request body of restoreWeblinkFromTrash method
- queryParams `RestoreWeblinkFromTrashQueryParams`
  - Query parameters of restoreWeblinkFromTrash method
- headers `RestoreWeblinkFromTrashHeaders`
  - Headers of restoreWeblinkFromTrash method


### Returns

This function returns a value of type `TrashWebLinkRestored`.

Returns a web link object when it has been restored.


## Get trashed web link

Retrieves a web link that has been moved to the trash.

This operation is performed by calling function `getTrashedWebLinkById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-web-links-id-trash/).

<!-- sample get_web_links_id_trash -->
```
client.getTrashedWebLinks().getTrashedWebLinkById(weblink.getId())
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- queryParams `GetTrashedWebLinkByIdQueryParams`
  - Query parameters of getTrashedWebLinkById method
- headers `GetTrashedWebLinkByIdHeaders`
  - Headers of getTrashedWebLinkById method


### Returns

This function returns a value of type `TrashWebLink`.

Returns the web link that was trashed,
including information about when the it
was moved to the trash.


## Permanently remove web link

Permanently deletes a web link that is in the trash.
This action cannot be undone.

This operation is performed by calling function `deleteTrashedWebLinkById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-web-links-id-trash/).

<!-- sample delete_web_links_id_trash -->
```
client.getTrashedWebLinks().deleteTrashedWebLinkById(weblink.getId())
```

### Arguments

- webLinkId `String`
  - The ID of the web link. Example: "12345"
- headers `DeleteTrashedWebLinkByIdHeaders`
  - Headers of deleteTrashedWebLinkById method


### Returns

This function returns a value of type `void`.

Returns an empty response when the web link was
permanently deleted.


