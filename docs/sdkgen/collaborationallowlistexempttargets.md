# CollaborationAllowlistExemptTargetsManager


- [List users exempt from collaboration domain restrictions](#list-users-exempt-from-collaboration-domain-restrictions)
- [Create user exemption from collaboration domain restrictions](#create-user-exemption-from-collaboration-domain-restrictions)
- [Get user exempt from collaboration domain restrictions](#get-user-exempt-from-collaboration-domain-restrictions)
- [Remove user from list of users exempt from domain restrictions](#remove-user-from-list-of-users-exempt-from-domain-restrictions)

## List users exempt from collaboration domain restrictions

Returns a list of users who have been exempt from the collaboration
domain restrictions.

This operation is performed by calling function `getCollaborationWhitelistExemptTargets`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-collaboration-whitelist-exempt-targets/).

<!-- sample get_collaboration_whitelist_exempt_targets -->
```
client.getCollaborationAllowlistExemptTargets().getCollaborationWhitelistExemptTargets()
```

### Arguments

- queryParams `GetCollaborationWhitelistExemptTargetsQueryParams`
  - Query parameters of getCollaborationWhitelistExemptTargets method
- headers `GetCollaborationWhitelistExemptTargetsHeaders`
  - Headers of getCollaborationWhitelistExemptTargets method


### Returns

This function returns a value of type `CollaborationAllowlistExemptTargets`.

Returns a collection of user exemptions.


## Create user exemption from collaboration domain restrictions

Exempts a user from the restrictions set out by the allowed list of domains
for collaborations.

This operation is performed by calling function `createCollaborationWhitelistExemptTarget`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-collaboration-whitelist-exempt-targets/).

<!-- sample post_collaboration_whitelist_exempt_targets -->
```
client.getCollaborationAllowlistExemptTargets().createCollaborationWhitelistExemptTarget(new CreateCollaborationWhitelistExemptTargetRequestBody(new CreateCollaborationWhitelistExemptTargetRequestBodyUserField(user.getId())))
```

### Arguments

- requestBody `CreateCollaborationWhitelistExemptTargetRequestBody`
  - Request body of createCollaborationWhitelistExemptTarget method
- headers `CreateCollaborationWhitelistExemptTargetHeaders`
  - Headers of createCollaborationWhitelistExemptTarget method


### Returns

This function returns a value of type `CollaborationAllowlistExemptTarget`.

Returns a new exemption entry.


## Get user exempt from collaboration domain restrictions

Returns a users who has been exempt from the collaboration
domain restrictions.

This operation is performed by calling function `getCollaborationWhitelistExemptTargetById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-collaboration-whitelist-exempt-targets-id/).

<!-- sample get_collaboration_whitelist_exempt_targets_id -->
```
client.getCollaborationAllowlistExemptTargets().getCollaborationWhitelistExemptTargetById(newExemptTarget.getId())
```

### Arguments

- collaborationWhitelistExemptTargetId `String`
  - The ID of the exemption to the list. Example: "984923"
- headers `GetCollaborationWhitelistExemptTargetByIdHeaders`
  - Headers of getCollaborationWhitelistExemptTargetById method


### Returns

This function returns a value of type `CollaborationAllowlistExemptTarget`.

Returns the user's exempted from the list of collaboration domains.


## Remove user from list of users exempt from domain restrictions

Removes a user's exemption from the restrictions set out by the allowed list
of domains for collaborations.

This operation is performed by calling function `deleteCollaborationWhitelistExemptTargetById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-collaboration-whitelist-exempt-targets-id/).

<!-- sample delete_collaboration_whitelist_exempt_targets_id -->
```
client.getCollaborationAllowlistExemptTargets().deleteCollaborationWhitelistExemptTargetById(exemptTarget.getId())
```

### Arguments

- collaborationWhitelistExemptTargetId `String`
  - The ID of the exemption to the list. Example: "984923"
- headers `DeleteCollaborationWhitelistExemptTargetByIdHeaders`
  - Headers of deleteCollaborationWhitelistExemptTargetById method


### Returns

This function returns a value of type `void`.

A blank response is returned if the exemption was
successfully deleted.


