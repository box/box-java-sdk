# InvitesManager


- [Create user invite](#create-user-invite)
- [Get user invite status](#get-user-invite-status)

## Create user invite

Invites an existing external user to join an enterprise.

The user must already have a Box account and cannot be
part of another enterprise. Once invited, the user receives
an email and is prompted to accept the invitation in
the Box web application.

If `is_auto_roll_in_enabled` is set to `true`, the user does not have to
accept the invitation and is added to the enterprise automatically after
14 days. Only unmanaged users can be invited this way. An unmanaged user
is a free user whose email domain matches one of the enterprise's
domains. Availability depends on the enterprise's plan.

This method requires the "Manage An Enterprise" scope enabled for
the application, which can be enabled within the developer console.

This operation is performed by calling function `createInvite`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-invites/).

<!-- sample post_invites -->
```
client.getInvites().createInvite(new CreateInviteRequestBody(new CreateInviteRequestBodyEnterpriseField(currentUser.getEnterprise().getId()), new CreateInviteRequestBodyActionableByField.Builder().login(email).build()))
```

### Arguments

- requestBody `CreateInviteRequestBody`
  - Request body of createInvite method
- queryParams `CreateInviteQueryParams`
  - Query parameters of createInvite method
- headers `CreateInviteHeaders`
  - Headers of createInvite method


### Returns

This function returns a value of type `Invite`.

Returns a new invite object.


## Get user invite status

Returns the status of a user invite.

This operation is performed by calling function `getInviteById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-invites-id/).

<!-- sample get_invites_id -->
```
client.getInvites().getInviteById(invitation.getId())
```

### Arguments

- inviteId `String`
  - The ID of an invite. Example: "213723"
- queryParams `GetInviteByIdQueryParams`
  - Query parameters of getInviteById method
- headers `GetInviteByIdHeaders`
  - Headers of getInviteById method


### Returns

This function returns a value of type `Invite`.

Returns an invite object.


