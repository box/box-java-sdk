Storage Policy 
==============

Allows the enterprise admin to manage the Storage Policies for users in their
enterprise. Used for an enterprise to decide storage location for users based on
where they work/reside. 

* [Get Storage Policy](#get-storage-policy) 
* [Get List of Storage Policies](#get-list-of-storage-policies)
* [Create New Assignment](#create-new-assignment)
* [Get Assignment](#get-assignment)
* [Get Assignment For Target](#get-assignment-for-target)
* [Update Existing Assignment](#update-existing-assignment)
* [Assign Storage Policy](#assign-storage-policy)
* [Delete Assignment](#delete-assignment)

Get Storage Policy
------------------

Calling [`getInfo(String fields ...)`][get-info] will return BoxStoragePolicy.Info object
containing information about the storage policy. If necessary to retrieve 
limited set of fields. It is possible to specify them using param.

```java
BoxStoragePolicy storagePolicy = new BoxStoragePolicy(api, id);
BoxStoragePolicy.Info storagePolicyInfo = storagePolicy.getInfo();
```

[get-info]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicy.html#getInfo--

Get List of Storage Policies
----------------------------

Calling the static [`getAll(BoxAPIConnection api)`][get-list-of-storage-policies]
will return an iterable that will page through all of the storage policies. 
It is possible to specify maximum number of items per response and fields to retrieve by 
calling the static [`getAll(BoxAPIConnection api, int limit, String fields ...)`][get-list-of-storage-policies-with-fields] method. 

```java
Iterable<BoxStoragePolicy.Info> storagePolicies = BoxStoragePolicy.getAll(api);
for (BoxStoragePolicy.Info storagePolicyInfo : storagePolicies) {
        //Do something with the storage policy.
}
```

[get-list-of-storage-policies]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicy.html#getAll--
[get-list-of-storage-policies-with-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicy.html#getAll-java.lang.String...-

Create New Assignment
---------------------

To create new storage policy assignment call [`create(BoxAPIConnection api, String policyID, String userID)`][create] method. 

```java
BoxStoragePolicyAssignment.Info assignmentInfo = BoxStoragePolicyAssignment.create(api, policyID, userID);
```

[create]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicyAssignment.html#create-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String-

Update Existing Assignment
--------------------------

Updating a storage policy assignment information is done by calling
[`updateInfo(BoxStoragePolicyAssignment.Info updatedInfo)`][update-info].

```java
BoxStoragePolicyAssignment storagePolicyAssignment = new BoxStoragePolicyAssignment(api, "ASSIGNMENT_ID");
BoxStoragePolicyAssignment.Info assignmentInfo = storagePolicyAssignment.new Info();
assignmentInfo.setStoragePolicyID("NEW_STORAGE_POLICY_ID");
storagePolicyAssignment.updateInfo(assignmentInfo);
```

[update-info]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicyAssignment.html#updateInfo-com.box.sdk.BoxStoragePolicyAssignment.Info-

Get Assignment
--------------

Calling [`getInfo(String fields ...)`][get-assignment] will return a BoxStoragePolicyAssignment.Info object containing information
about the storage policy assignment. 

```java
BoxStoragePolicyAssignment storagePolicyAssignment = new BoxStoragePolicyAssignment(api, id);
BoxStoragePolicyAssignment.Info assignmentInfo = storagePolicyAssignment.getInfo();
```

[get-assignment]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicyAssignment.html#getInfo--

Get Assignment for Target
-------------------------

Calling [`getAssignmentForTarget(BoxAPIConnection api, String resolvedForType, String resolvedForID)`][get-assignment-for-target] will return a BoxStorageAssignment.Info
object containing information about the storage policy assignment. 

```java
BoxStoragePolicyAssignment.Info assignmentInfo = BoxStoragePolicyAssignment.getAssignmentForTarget(api, "user", "1234")
```

[get-assignment-for-target]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicyAssignment.html#getAssignmentForTarget-com.box.BoxAPIConnection-java.lang.String-java.lang.String-

Assign Storage Policy
---------------------

Calling [`assign(BoxAPIConnection api, String storagePolicyID, String userID)`][assign] will check if this user already has storage policy assigned to them. If not then a new
this user will be assigned the specified storage policy. 

```java
BoxStoragePolicyAssignment.Info assignmentInfo = BoxStoragePolicyAssignment.assign(api, "1234", "5678");
```

[assign]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicyAssigbment.html#assign-com.box.BoxAPIConnection-java.lang.String-java.lang.String-

Delete Assignment
-----------------

Calling [`delete()`][delete] will remove the storage policy assignment from the user. 

```java
BoxStoragePolicyAssignment assignment = new BoxStoragePolicyAssignment(api, "dXNlcl8xMjM0");
assignment.delete();
```

[delete]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxStoragePolicyAssignment.html#delete--


