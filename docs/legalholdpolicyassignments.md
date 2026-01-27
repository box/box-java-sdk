# LegalHoldPolicyAssignmentsManager


- [List legal hold policy assignments](#list-legal-hold-policy-assignments)
- [Assign legal hold policy](#assign-legal-hold-policy)
- [Get legal hold policy assignment](#get-legal-hold-policy-assignment)
- [Unassign legal hold policy](#unassign-legal-hold-policy)
- [List files with current file versions for legal hold policy assignment](#list-files-with-current-file-versions-for-legal-hold-policy-assignment)

## List legal hold policy assignments

Retrieves a list of items a legal hold policy has been assigned to.

This operation is performed by calling function `getLegalHoldPolicyAssignments`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-legal-hold-policy-assignments/).

<!-- sample get_legal_hold_policy_assignments -->
```
client.getLegalHoldPolicyAssignments().getLegalHoldPolicyAssignments(new GetLegalHoldPolicyAssignmentsQueryParams(legalHoldPolicyId))
```

### Arguments

- queryParams `GetLegalHoldPolicyAssignmentsQueryParams`
  - Query parameters of getLegalHoldPolicyAssignments method
- headers `GetLegalHoldPolicyAssignmentsHeaders`
  - Headers of getLegalHoldPolicyAssignments method


### Returns

This function returns a value of type `LegalHoldPolicyAssignments`.

Returns a list of legal hold policy assignments.


## Assign legal hold policy

Assign a legal hold to an item type of: file, file version, folder, user, ownership, or interactions.

This operation is performed by calling function `createLegalHoldPolicyAssignment`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-legal-hold-policy-assignments/).

<!-- sample post_legal_hold_policy_assignments -->
```
client.getLegalHoldPolicyAssignments().createLegalHoldPolicyAssignment(new CreateLegalHoldPolicyAssignmentRequestBody(legalHoldPolicyId, new CreateLegalHoldPolicyAssignmentRequestBodyAssignToField(CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField.FILE, fileId)))
```

### Arguments

- requestBody `CreateLegalHoldPolicyAssignmentRequestBody`
  - Request body of createLegalHoldPolicyAssignment method
- headers `CreateLegalHoldPolicyAssignmentHeaders`
  - Headers of createLegalHoldPolicyAssignment method


### Returns

This function returns a value of type `LegalHoldPolicyAssignment`.

Returns a new legal hold policy assignment.


## Get legal hold policy assignment

Retrieve a legal hold policy assignment.

This operation is performed by calling function `getLegalHoldPolicyAssignmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-legal-hold-policy-assignments-id/).

<!-- sample get_legal_hold_policy_assignments_id -->
```
client.getLegalHoldPolicyAssignments().getLegalHoldPolicyAssignmentById(legalHoldPolicyAssignmentId)
```

### Arguments

- legalHoldPolicyAssignmentId `String`
  - The ID of the legal hold policy assignment. Example: "753465"
- headers `GetLegalHoldPolicyAssignmentByIdHeaders`
  - Headers of getLegalHoldPolicyAssignmentById method


### Returns

This function returns a value of type `LegalHoldPolicyAssignment`.

Returns a legal hold policy object.


## Unassign legal hold policy

Remove a legal hold from an item.

This is an asynchronous process. The policy will not be
fully removed yet when the response returns.

This operation is performed by calling function `deleteLegalHoldPolicyAssignmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-legal-hold-policy-assignments-id/).

<!-- sample delete_legal_hold_policy_assignments_id -->
```
client.getLegalHoldPolicyAssignments().deleteLegalHoldPolicyAssignmentById(legalHoldPolicyAssignmentId)
```

### Arguments

- legalHoldPolicyAssignmentId `String`
  - The ID of the legal hold policy assignment. Example: "753465"
- headers `DeleteLegalHoldPolicyAssignmentByIdHeaders`
  - Headers of deleteLegalHoldPolicyAssignmentById method


### Returns

This function returns a value of type `void`.

A blank response is returned if the assignment was
successfully deleted.


## List files with current file versions for legal hold policy assignment

Get a list of files with current file versions for a legal hold
assignment.

In some cases you may want to get previous file versions instead. In these
cases, use the `GET  /legal_hold_policy_assignments/:id/file_versions_on_hold`
API instead to return any previous versions of a file for this legal hold
policy assignment.

Due to ongoing re-architecture efforts this API might not return all file
versions held for this policy ID. Instead, this API will only return the
latest file version held in the newly developed architecture. The `GET
/file_version_legal_holds` API can be used to fetch current and past versions
of files held within the legacy architecture.

This endpoint does not support returning any content that is on hold due to
a Custodian collaborating on a Hub.

The `GET /legal_hold_policy_assignments?policy_id={id}` API can be used to
find a list of policy assignments for a given policy ID.

This operation is performed by calling function `getLegalHoldPolicyAssignmentFileOnHold`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-legal-hold-policy-assignments-id-files-on-hold/).

<!-- sample get_legal_hold_policy_assignments_id_files_on_hold -->
```
client.getLegalHoldPolicyAssignments().getLegalHoldPolicyAssignmentFileOnHold(legalHoldPolicyAssignmentId)
```

### Arguments

- legalHoldPolicyAssignmentId `String`
  - The ID of the legal hold policy assignment. Example: "753465"
- queryParams `GetLegalHoldPolicyAssignmentFileOnHoldQueryParams`
  - Query parameters of getLegalHoldPolicyAssignmentFileOnHold method
- headers `GetLegalHoldPolicyAssignmentFileOnHoldHeaders`
  - Headers of getLegalHoldPolicyAssignmentFileOnHold method


### Returns

This function returns a value of type `FilesOnHold`.

Returns the list of current file versions held under legal hold for a
specific legal hold policy assignment.


