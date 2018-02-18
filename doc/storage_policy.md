Storage Policy 
==============

Allows the enterprise admin to manage the Storage Policies for users in their
enterprise. Used for an enterprise to decide storage location for users based on
where they work/reside. 

*[Get Storage Policy](#get-storage-policy) 
*[Get List of Storage Policies](#get-list-of-storage-policies)
*[Create New Assignment](#create-new-assignment)
*[Get Assignment](#get-assignment)
*[Get List of Assignments](#get-list-of-assignments)
*[Update Existing Assignment](#update-existing-assignment)
*[Delete Assignment](#delete-assignment)

Get Storage Policy
------------------

Calling [`getInfo(String...)`][get-info] will return BoxStoragePolicy.Info object
containing information about the storage policy. If necessary to retrieve 
limited set of fields. It is possible to specify them using param.

```java
BoxStoragePolicy storagePolicy = new BoxStoragePolicy(api, id);
BoxStoragePolicy.Info storagePolicyInfo = storagePolicy.getInfo();
```

Get List of Storage Policies
----------------------------

Calling the static [`getAll(BoxAPIConnection)`][get-list-of-storage-policies]
will return an iterable that will page through all of the storage policies. 
It is possible to specify maximum number of items per response and fields to retrieve by 
calling the static [`getAll(BoxAPIConnection, int, String)`][get-list-storage-policies-with-fields] method. 

```java
Iterable<BoxStoragePolicy.Info> storagePolicies = BoxStoragePolicy.getAll(api);
for (BoxStoragePolicy.Info storagePolicyInfo : storagePolicies) {
        //Do something with the storage policy.
}
```

[get-list-of-storage-policies]:
[get-list-of-storage-policies-with-fields]: 


