# StoragePolicyAssignmentsManager


- [List storage policy assignments](#list-storage-policy-assignments)
- [Assign storage policy](#assign-storage-policy)
- [Get storage policy assignment](#get-storage-policy-assignment)
- [Update storage policy assignment](#update-storage-policy-assignment)
- [Unassign storage policy](#unassign-storage-policy)

## List storage policy assignments

Fetches all the storage policy assignment for an enterprise or user.

This operation is performed by calling function `getStoragePolicyAssignments`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-storage-policy-assignments/).

<!-- sample get_storage_policy_assignments -->
```
client.getStoragePolicyAssignments().getStoragePolicyAssignments(new GetStoragePolicyAssignmentsQueryParams(GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField.USER, userId))
```

### Arguments

- queryParams `GetStoragePolicyAssignmentsQueryParams`
  - Query parameters of getStoragePolicyAssignments method
- headers `GetStoragePolicyAssignmentsHeaders`
  - Headers of getStoragePolicyAssignments method


### Returns

This function returns a value of type `StoragePolicyAssignments`.

Returns a collection of storage policies for
the enterprise or user.


## Assign storage policy

Creates a storage policy assignment for an enterprise or user.

This operation is performed by calling function `createStoragePolicyAssignment`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-storage-policy-assignments/).

<!-- sample post_storage_policy_assignments -->
```
client.getStoragePolicyAssignments().createStoragePolicyAssignment(new CreateStoragePolicyAssignmentRequestBody(new CreateStoragePolicyAssignmentRequestBodyStoragePolicyField(policyId), new CreateStoragePolicyAssignmentRequestBodyAssignedToField(CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField.USER, userId)))
```

### Arguments

- requestBody `CreateStoragePolicyAssignmentRequestBody`
  - Request body of createStoragePolicyAssignment method
- headers `CreateStoragePolicyAssignmentHeaders`
  - Headers of createStoragePolicyAssignment method


### Returns

This function returns a value of type `StoragePolicyAssignment`.

Returns the new storage policy assignment created.


## Get storage policy assignment

Fetches a specific storage policy assignment.

This operation is performed by calling function `getStoragePolicyAssignmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-storage-policy-assignments-id/).

<!-- sample get_storage_policy_assignments_id -->
```
client.getStoragePolicyAssignments().getStoragePolicyAssignmentById(storagePolicyAssignment.getId())
```

### Arguments

- storagePolicyAssignmentId `String`
  - The ID of the storage policy assignment. Example: "932483"
- headers `GetStoragePolicyAssignmentByIdHeaders`
  - Headers of getStoragePolicyAssignmentById method


### Returns

This function returns a value of type `StoragePolicyAssignment`.

Returns a storage policy assignment object.


## Update storage policy assignment

Updates a specific storage policy assignment.

This operation is performed by calling function `updateStoragePolicyAssignmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-storage-policy-assignments-id/).

<!-- sample put_storage_policy_assignments_id -->
```
client.getStoragePolicyAssignments().updateStoragePolicyAssignmentById(storagePolicyAssignment.getId(), new UpdateStoragePolicyAssignmentByIdRequestBody(new UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField(storagePolicy2.getId())))
```

### Arguments

- storagePolicyAssignmentId `String`
  - The ID of the storage policy assignment. Example: "932483"
- requestBody `UpdateStoragePolicyAssignmentByIdRequestBody`
  - Request body of updateStoragePolicyAssignmentById method
- headers `UpdateStoragePolicyAssignmentByIdHeaders`
  - Headers of updateStoragePolicyAssignmentById method


### Returns

This function returns a value of type `StoragePolicyAssignment`.

Returns an updated storage policy assignment object.


## Unassign storage policy

Delete a storage policy assignment.

Deleting a storage policy assignment on a user
will have the user inherit the enterprise's default
storage policy.

There is a rate limit for calling this endpoint of only
twice per user in a 24 hour time frame.

This operation is performed by calling function `deleteStoragePolicyAssignmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-storage-policy-assignments-id/).

<!-- sample delete_storage_policy_assignments_id -->
```
client.getStoragePolicyAssignments().deleteStoragePolicyAssignmentById(storagePolicyAssignment.getId())
```

### Arguments

- storagePolicyAssignmentId `String`
  - The ID of the storage policy assignment. Example: "932483"
- headers `DeleteStoragePolicyAssignmentByIdHeaders`
  - Headers of deleteStoragePolicyAssignmentById method


### Returns

This function returns a value of type `void`.

Returns an empty response when the storage policy
assignment is successfully deleted.


