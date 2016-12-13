Legal Holds Policy
==================

Legal Hold Policy information describes the basic characteristics of the Policy,
such as name, description, and filter dates.

* [Get Legal Hold Policy](#get-legal-hold-policy)
* [Get List of Legal Hold Policies](#get-list-of-legal-hold-policies)
* [Create New Legal Hold Policy](#create-new-legal-hold-policy)
* [Update Existing Legal Hold Policy](#update-existing-legal-hold-policy)
* [Delete Legal Hold Policy](#delete-legal-hold-policy)
* [Get Assignment](#get-assignment)
* [Get List of Assignments](#get-list-of-assignments)
* [Create New Assignment](#create-new-assignment)
* [Delete Assignment](#delete-assignment)
* [Get File Version Legal Hold](#get-file-version-legal-hold)
* [Get List of File Version Legal Holds](#get-list-of-file-version-legal-holds)

Get Legal Hold Policy
---------------------

Calling [`getInfo(String...)`][get-info] will return a BoxLegalHoldPolicy.Info object
containing information about the legal hold policy. If necessary to retrieve
limited set of fields, it is possible to specify them using param.

```java
BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, id);
BoxLegalHoldPolicy.Info policyInfo = policy.getInfo();
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#getInfo(java.lang.String...)

Get List of Legal Hold Policies
-------------------------------

Calling the static [`getAll(BoxAPIConnection)`][get-list-of-legal-hold-policies]
will return an iterable that will page through all of the legal hold policies.
It is possible to specify name of legal hold policy, maximum number of items per
response and fields to retrieve by calling the static
[`getAll(BoxAPIConnection, String, int, String...)`][get-list-of-legal-hold-policies-with-fields] method.

```java
Iterable<BoxLegalHoldPolicy.Info> policies = BoxLegalHoldPolicy.getAll(api);
for (BoxLegalHoldPolicy.Info policyInfo : policies) {
    // Do something with the legal hold policy.
}
```

[get-list-of-legal-hold-policies]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#getAll(com.box.sdk.BoxAPIConnection)
[get-list-of-legal-hold-policies-with-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#getAll(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20int,%20java.lang.String...)

Create New Legal Hold Policy
----------------------------

The static [`create(BoxAPIConnection, String)`][create-new-legal-hold-policy]
method will let you create a new legal hold policy with a specified name. The
static
[`create(BoxAPIConnection, String, String, Date, Date)`][create-new- legal-hold-policy-with-dates]
method will let you create a new legal hold policy with a specified name, description, start and end dates.

```java
BoxLegalHoldPolicy.Info policyInfo = BoxLegalHoldPolicy.create(api, name, description, startedAt, endedAt);
```

[create-new-legal-hold-policy]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#create(com.box.sdk.BoxAPIConnection,%20java.lang.String)
[create-new-legal-hold-policy-with-dates]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#create(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20java.lang.String,%20java.util.Date,%20java.util.Date)

Update Existing Legal Hold Policy
---------------------------------

Updating a legal hold policy's information is done by calling
[`updateInfo(BoxLegalHoldPolicy.Info)`][update-info].

```java
BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, id);
BoxLegalHoldPolicy.Info policyInfo = policy.new Info();
info.addPendingChange("description", "new description");
policy.updateInfo(info);
```

[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#update(com.box.sdk.BoxLegalHoldPolicy.Info)

Delete Legal Hold Policy
------------------------

A legal hold policy can be deleted by calling the [`delete()`][delete] method.

```java
BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, id);
policy.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#delete()

Get Assignment
--------------

Calling [`getInfo(String...)`][get-assignment] will return a BoxLegalHoldAssignment.Info 
object containing information about the legal hold policy assignment.

```java
BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(api, id);
BoxLegalHoldAssignment.Info info = assignment.getInfo("assigned_by");
```

[get-assignment]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldAssignment.html#getInfo(java.lang.String...)

Get List of Assignments
--------------

Calling the static [`getAssignments(String...)`][get-list-of-assignments] will return 
an iterable that will page through all of the assignments of the legal hold policy. 
It is possible to specify filters for type and id, maximum number of items per single 
response and fields to retrieve by calling [`getAssignments(String, String, int, String...)`][get-list-of-assignments-with-params].

```java
BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, id);
Iterable<BoxLegalHoldAssignment.Info> assignments = policy.getAssignments(BoxResource.getResourceType(BoxFolder.class), null, 50, "assigned_at");
for (BoxLegalHoldAssignment.Info assignmentInfo : assignments) {
	// Do something with the legal hold policy assignment.
}
```

[get-list-of-assignments]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#getAssignments(java.lang.String...)
[get-list-of-assignments-with-params]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#getAssignments(java.lang.String,%20java.lang.String,%20int,%20java.lang.String...)

Create New Assignment
--------------

To create new legal hold policy assignment call [`assignTo(BoxResource)`][create-assignment] method. 
Currently only BoxFile, BoxFileVersion, BoxFolder and BoxUser objects are supported as a parameter.

```java
BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, policyID);
BoxFolder folder = new BoxFolder(api, folderID);
policy.assignTo(folder);
```

[create-assignment]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#assignTo(com.box.sdk.BoxResource)

Delete Assignment
--------------

A legal hold policy assignment can be deleted by calling the [`delete()`][delete-assignment] method 
of BoxLegalHoldAssignment object.

```java
BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(api, id);
assignment.delete();
```

[delete-assignment]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldAssignment.html#delete()

Get File Version Legal Hold
--------------

Calling [`getInfo(String...)`][get-file-version-legal-hold] will return 
a BoxFileVersionLegalHold.Info object containing information about the file version legal hold policy.

```java
BoxFileVersionLegalHold hold = new BoxFileVersionLegalHold(api, id);
hold.getInfo("file");
```

[get-file-version-legal-hold]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersionLegalHold.html#getInfo(java.lang.String...)

Get List of File Version Legal Holds
--------------
To get an iterable with all non-deleted file version legal holds for current 
legal hold policy, call [`getFileVersionHolds(String...)`][get-lest-of-file-version-legal-holds]. 
It is possible to specify maximum number of items per single response by calling [`getFileVersionHolds(int, String...)`][get-lest-of-file-version-legal-holds-with-limit].

```java
BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, id);
Iterable<BoxFileVersionLegalHold.Info> fileVersionHolds = policy.getFileVersionHolds();
for (BoxFileVersionLegalHold.Info fileVersionHold : fileVersionHolds) {
	// Do something with the file version legal hold.
}
```

[get-lest-of-file-version-legal-holds]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#getFileVersionHolds(java.lang.String...)
[get-lest-of-file-version-legal-holds-with-limit]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#getFileVersionHolds(int,%20java.lang.String...)
