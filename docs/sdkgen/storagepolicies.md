# StoragePoliciesManager


- [List storage policies](#list-storage-policies)
- [Get storage policy](#get-storage-policy)

## List storage policies

Fetches all the storage policies in the enterprise.

This operation is performed by calling function `getStoragePolicies`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-storage-policies/).

<!-- sample get_storage_policies -->
```
client.getStoragePolicies().getStoragePolicies()
```

### Arguments

- queryParams `GetStoragePoliciesQueryParams`
  - Query parameters of getStoragePolicies method
- headers `GetStoragePoliciesHeaders`
  - Headers of getStoragePolicies method


### Returns

This function returns a value of type `StoragePolicies`.

Returns a collection of storage policies.


## Get storage policy

Fetches a specific storage policy.

This operation is performed by calling function `getStoragePolicyById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-storage-policies-id/).

<!-- sample get_storage_policies_id -->
```
client.getStoragePolicies().getStoragePolicyById(storagePolicy.getId())
```

### Arguments

- storagePolicyId `String`
  - The ID of the storage policy. Example: "34342"
- headers `GetStoragePolicyByIdHeaders`
  - Headers of getStoragePolicyById method


### Returns

This function returns a value of type `StoragePolicy`.

Returns a storage policy object.


