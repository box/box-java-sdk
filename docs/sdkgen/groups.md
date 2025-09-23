# GroupsManager


- [List groups for enterprise](#list-groups-for-enterprise)
- [Create group](#create-group)
- [Get group](#get-group)
- [Update group](#update-group)
- [Remove group](#remove-group)

## List groups for enterprise

Retrieves all of the groups for a given enterprise. The user
must have admin permissions to inspect enterprise's groups.

This operation is performed by calling function `getGroups`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-groups/).

<!-- sample get_groups -->
```
client.getGroups().getGroups()
```

### Arguments

- queryParams `GetGroupsQueryParams`
  - Query parameters of getGroups method
- headers `GetGroupsHeaders`
  - Headers of getGroups method


### Returns

This function returns a value of type `Groups`.

Returns a collection of group objects. If there are no groups, an
empty collection will be returned.


## Create group

Creates a new group of users in an enterprise. Only users with admin
permissions can create new groups.

This operation is performed by calling function `createGroup`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-groups/).

<!-- sample post_groups -->
```
client.getGroups().createGroup(new CreateGroupRequestBody.Builder(groupName).description(groupDescription).build())
```

### Arguments

- requestBody `CreateGroupRequestBody`
  - Request body of createGroup method
- queryParams `CreateGroupQueryParams`
  - Query parameters of createGroup method
- headers `CreateGroupHeaders`
  - Headers of createGroup method


### Returns

This function returns a value of type `GroupFull`.

Returns the new group object.


## Get group

Retrieves information about a group. Only members of this
group or users with admin-level permissions will be able to
use this API.

This operation is performed by calling function `getGroupById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-groups-id/).

<!-- sample get_groups_id -->
```
client.getGroups().getGroupById(group.getId(), new GetGroupByIdQueryParams.Builder().fields(Arrays.asList("id", "name", "description", "group_type")).build())
```

### Arguments

- groupId `String`
  - The ID of the group. Example: "57645"
- queryParams `GetGroupByIdQueryParams`
  - Query parameters of getGroupById method
- headers `GetGroupByIdHeaders`
  - Headers of getGroupById method


### Returns

This function returns a value of type `GroupFull`.

Returns the group object.


## Update group

Updates a specific group. Only admins of this
group or users with admin-level permissions will be able to
use this API.

This operation is performed by calling function `updateGroupById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-groups-id/).

<!-- sample put_groups_id -->
```
client.getGroups().updateGroupById(group.getId(), new UpdateGroupByIdRequestBody.Builder().name(updatedGroupName).build())
```

### Arguments

- groupId `String`
  - The ID of the group. Example: "57645"
- requestBody `UpdateGroupByIdRequestBody`
  - Request body of updateGroupById method
- queryParams `UpdateGroupByIdQueryParams`
  - Query parameters of updateGroupById method
- headers `UpdateGroupByIdHeaders`
  - Headers of updateGroupById method


### Returns

This function returns a value of type `GroupFull`.

Returns the updated group object.


## Remove group

Permanently deletes a group. Only users with
admin-level permissions will be able to use this API.

This operation is performed by calling function `deleteGroupById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-groups-id/).

<!-- sample delete_groups_id -->
```
client.getGroups().deleteGroupById(group.getId())
```

### Arguments

- groupId `String`
  - The ID of the group. Example: "57645"
- headers `DeleteGroupByIdHeaders`
  - Headers of deleteGroupById method


### Returns

This function returns a value of type `void`.

A blank response is returned if the group was
successfully deleted.


