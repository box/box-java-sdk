# MembershipsManager


- [List user's groups](#list-users-groups)
- [List members of group](#list-members-of-group)
- [Add user to group](#add-user-to-group)
- [Get group membership](#get-group-membership)
- [Update group membership](#update-group-membership)
- [Remove user from group](#remove-user-from-group)

## List user's groups

Retrieves all the groups for a user. Only members of this
group or users with admin-level permissions will be able to
use this API.

This operation is performed by calling function `getUserMemberships`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-users-id-memberships/).

<!-- sample get_users_id_memberships -->
```
client.getMemberships().getUserMemberships(user.getId())
```

### Arguments

- userId `String`
  - The ID of the user. Example: "12345"
- queryParams `GetUserMembershipsQueryParams`
  - Query parameters of getUserMemberships method
- headers `GetUserMembershipsHeaders`
  - Headers of getUserMemberships method


### Returns

This function returns a value of type `GroupMemberships`.

Returns a collection of membership objects. If there are no
memberships, an empty collection will be returned.


## List members of group

Retrieves all the members for a group. Only members of this
group or users with admin-level permissions will be able to
use this API.

This operation is performed by calling function `getGroupMemberships`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-groups-id-memberships/).

<!-- sample get_groups_id_memberships -->
```
client.getMemberships().getGroupMemberships(group.getId())
```

### Arguments

- groupId `String`
  - The ID of the group. Example: "57645"
- queryParams `GetGroupMembershipsQueryParams`
  - Query parameters of getGroupMemberships method
- headers `GetGroupMembershipsHeaders`
  - Headers of getGroupMemberships method


### Returns

This function returns a value of type `GroupMemberships`.

Returns a collection of membership objects. If there are no
memberships, an empty collection will be returned.


## Add user to group

Creates a group membership. Only users with
admin-level permissions will be able to use this API.

This operation is performed by calling function `createGroupMembership`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-group-memberships/).

<!-- sample post_group_memberships -->
```
client.getMemberships().createGroupMembership(new CreateGroupMembershipRequestBody(new CreateGroupMembershipRequestBodyUserField(user.getId()), new CreateGroupMembershipRequestBodyGroupField(group.getId())))
```

### Arguments

- requestBody `CreateGroupMembershipRequestBody`
  - Request body of createGroupMembership method
- queryParams `CreateGroupMembershipQueryParams`
  - Query parameters of createGroupMembership method
- headers `CreateGroupMembershipHeaders`
  - Headers of createGroupMembership method


### Returns

This function returns a value of type `GroupMembership`.

Returns a new group membership object.


## Get group membership

Retrieves a specific group membership. Only admins of this
group or users with admin-level permissions will be able to
use this API.

This operation is performed by calling function `getGroupMembershipById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-group-memberships-id/).

<!-- sample get_group_memberships_id -->
```
client.getMemberships().getGroupMembershipById(groupMembership.getId())
```

### Arguments

- groupMembershipId `String`
  - The ID of the group membership. Example: "434534"
- queryParams `GetGroupMembershipByIdQueryParams`
  - Query parameters of getGroupMembershipById method
- headers `GetGroupMembershipByIdHeaders`
  - Headers of getGroupMembershipById method


### Returns

This function returns a value of type `GroupMembership`.

Returns the group membership object.


## Update group membership

Updates a user's group membership. Only admins of this
group or users with admin-level permissions will be able to
use this API.

This operation is performed by calling function `updateGroupMembershipById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-group-memberships-id/).

<!-- sample put_group_memberships_id -->
```
client.getMemberships().updateGroupMembershipById(groupMembership.getId(), new UpdateGroupMembershipByIdRequestBody.Builder().role(UpdateGroupMembershipByIdRequestBodyRoleField.ADMIN).build())
```

### Arguments

- groupMembershipId `String`
  - The ID of the group membership. Example: "434534"
- requestBody `UpdateGroupMembershipByIdRequestBody`
  - Request body of updateGroupMembershipById method
- queryParams `UpdateGroupMembershipByIdQueryParams`
  - Query parameters of updateGroupMembershipById method
- headers `UpdateGroupMembershipByIdHeaders`
  - Headers of updateGroupMembershipById method


### Returns

This function returns a value of type `GroupMembership`.

Returns a new group membership object.


## Remove user from group

Deletes a specific group membership. Only admins of this
group or users with admin-level permissions will be able to
use this API.

This operation is performed by calling function `deleteGroupMembershipById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-group-memberships-id/).

<!-- sample delete_group_memberships_id -->
```
client.getMemberships().deleteGroupMembershipById(groupMembership.getId())
```

### Arguments

- groupMembershipId `String`
  - The ID of the group membership. Example: "434534"
- headers `DeleteGroupMembershipByIdHeaders`
  - Headers of deleteGroupMembershipById method


### Returns

This function returns a value of type `void`.

A blank response is returned if the membership was
successfully deleted.


