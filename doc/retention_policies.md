Retention Policies
======

A retention policy blocks permanent deletion of content for a specified amount of time. Admins can create retention policies and then later assign them to specific folders or their entire enterprise.

* [Create Retention Policy](#create-retention-policy)
* [Get Retention Policy](#get-retention-policy)
* [Update Retention Policy](#update-retention-policy)
* [Get Retention Policies](#get-retention-policies)
* [Get Retention Policy Assignments](#get-retention-policy-assignments)
* [Create Retention Policy Assignment](#create-retention-policy-assignment)
* [Get Retention Policy Assignment](#get-retention-policy-assignment)
* [Get File Version Retention](#get-file-version-retention)
* [Get File Version Retentions](#get-file-version-retentions)


Create Retention Policy
--------------

The static [`createIndefinitePolicy(BoxAPIConnection, String)`][create-indefinite-retention-policy] method will let you create a new indefinite retention policy with a specified name.

```java
BoxRetentionPolicy.createIndefinitePolicy(api, name);
```

The static [`createFinitePolicy(BoxAPIConnection, String, int, String)`][create-finite-retention-policy] method will let you create a new finite retention policy with a specified name, amount of time to apply the retention policy (in days) and a disposition action. the disposition action can be "permanently\_delete" or "remove\_retention".

```java
BoxRetentionPolicy.createFinitePolicy(api, name, length, action);
```

Both finite and indefinite policies allow you to specify optional parameters using the [`RetentionPolicyParams`][policy-params]
object.

```java
String notifiedUserID = "12345";
RetentionPolicyParams optionalParams = new RetentionPolicyParams();
optionalParams.setCanOwnerExtendRetention(true);
optionalParams.setAreOwnersNotified(true);
optionalParams.addCustomNotificationRecipient(notifiedUserID);

// Create indefinite policy with optional parameters
BoxRetentionPolicy.createIndefinitePolicy(api, "Retain Stuff Forever", optionalParams);

// Create finite policy with optional parameters
BoxRetentionPolicy.createFinitePolicy(api, "Keep One Year", 365, BoxRetentionPolicy.ACTION_REMOVE_RETENTION, optionalParams);
```

[create-indefinite-retention-policy]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#createIndefinitePolicy-com.box.sdk.BoxAPIConnection-java.lang.String-
[create-finite-retention-policy]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#createFinitePolicy-com.box.sdk.BoxAPIConnection-java.lang.String-int-java.lang.String-
[policy-params]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/RetentionPolicyParams.html

Get Retention Policy
--------------

Calling [`getInfo(String...)`][get-info] will return a BoxRetentionPolicy.Info object containing information about the retention policy. If necessary to retrieve limited set of fields, it is possible to specify them using param.

```java
BoxRetentionPolicy policy = new BoxRetentionPolicy(api, id);
policy.getInfo("policy_name", "status");
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#getInfo-java.lang.String...-

Update Retention Policy
--------------

Updating a retention policy's information is done by calling [`updateInfo(BoxRetentionPolicy.Info)`][update-info].

```java
BoxRetentionPolicy policy = new BoxRetentionPolicy(api, id);
BoxRetentionPolicy.Info policyInfo = policy.new Info();
policyInfo.addPendingChange("policy_name", "new policy name");
policy.updateInfo(policyInfo);
```

[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#updateInfo-com.box.sdk.BoxRetentionPolicy.Info-

Get Retention Policies
--------------

Calling the static [`getAll(BoxAPIConnection, String...)`][get-retention-policies] will return an iterable that will page through all of the retention policies.
It is possible to specify filter for the name of retention policy, filter for the type of the policy, filter for the id of user, limit of items per single response and fields to retrieve by calling the static [`getAll(String, String, String, int, BoxAPIConnection, String...)`][get-retention-policies-with-fields] method.

```java
Iterable<BoxRetentionPolicy.Info> policies = BoxRetentionPolicy.getAll(api);
for (BoxRetentionPolicy.Info policyInfo : policies) {
	// Do something with the retention policy.
}
```

[get-retention-policies]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#getAll-com.box.sdk.BoxAPIConnection-java.lang.String...-

[get-retention-policies-with-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#getAll-java.lang.String-java.lang.String-java.lang.String-int-com.box.sdk.BoxAPIConnection-java.lang.String...-

Get Retention Policy Assignments
--------------

Calling [`getAllAssignments(String...)`][get-all-assignments] will return an iterable that will page through all of the assignments of the retention policy. It is possible to specify maximum number of items per single response and fields to retrieve by calling [`getAllAssignments(int, String...)`][get-all-assignments-with-params].
If it is necessary to retrieve only assignments of certain type, you can call [`getFolderAssignments(int, String...)`][get-folder-assignments] or [`getEnterpriseAssignments(int, String...)`][get-enterprise-assignments].

```java
BoxRetentionPolicy policy = new BoxRetentionPolicy(api, id);
Iterable<BoxRetentionPolicyAssignment.Info> allAssignments = BoxRetentionPolicy.getAllAssignments("assigned_by");
Iterable<BoxRetentionPolicyAssignment.Info> folderAssignments = BoxRetentionPolicy.getFolderAssignments(50, "assigned_by");
Iterable<BoxRetentionPolicyAssignment.Info> enterpriseAssignments = BoxRetentionPolicy.getEnterpriseAssignments();
for (BoxRetentionPolicyAssignments.Info assignmentInfo : allAssignments) {
	// Do something with the assignment.
}
for (BoxRetentionPolicyAssignments.Info assignmentInfo : folderAssignments) {
	// Do something with the assignment.
}
for (BoxRetentionPolicyAssignments.Info assignmentInfo : enterpriseAssignments) {
	// Do something with the assignment.
}
```

[get-all-assignments]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#getAllAssignments-java.lang.String...-
[get-all-assignments-with-params]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#getAllAssignments-int-java.lang.String...-
[get-folder-assignments]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#getFolderAssignments-int-java.lang.String...-
[get-enterprise-assignments]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#getEnterpriseAssignments-int-java.lang.String...-

Create Retention Policy Assignment
--------------
To create new retention policy assignment call [`assignTo(BoxFolder folder)`][create-assignment] method to assign the policy
to a specific folder, [`assignToEnterprise()`][create-assignment-to-enterprise] to assign the retention policy to the
entire enterprise, or [`assignToMetadataTemplate(String templateID, MetadataFieldFilter... filterFields)`][assign-to-metadata]
to assign the policy to items with a specific metadata template.

```java
// Assign the policy to the entire enterprise
BoxRetentionPolicy policy = new BoxRetentionPolicy(api, policyID);
BoxRetentionPolicyAssignment.Info enterpriseAssignmentInfo = policy.assignToEnterprise();

// Assign the policy to a single folder
BoxFolder folder = new BoxFolder(api, folderID);
BoxRetentionPolicyAssignment.Info folderAssignmentInfo = policy.assignTo(folder);

// Assign the policy to all items with metadata template "f0dce190-8106-43ca-9d67-7dce9b10a55e"
BoxRetentionPolicyAssignment.Info metadataAssignmentInfo = policy.assignToMetadataTemplate("f0dce190-8106-43ca-9d67-7dce9b10a55e");
```

[create-assignment]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#assignTo-com.box.sdk.BoxFolder-
[create-assignment-to-enterprise]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#assignToEnterprise--
[assign-to-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicy.html#assignToMetadataTemplate-java.lang.String-com.box.sdk.MetadataFieldFilter-

Get Retention Policy Assignment
--------------

Calling [`getInfo(String...)`][get-assignment] will return a BoxRetentionPolicyAssignment.Info object containing information about the retention policy assignment.

```java
BoxRetentionPolicyAssignment assignment = new BoxRetentionPolicyAssignment(api, id);
BoxRetentionPolicyAssignment.Info assignmentInfo = assignment.getInfo("assigned_to");
```

[get-assignment]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxRetentionPolicyAssignment.html#getInfo-java.lang.String...-

Get File Version Retention
--------------

Calling [`getInfo(String...)`][get-file-version-retention] will return a BoxFileVersionRetention.Info object containing information about the file version retention policy.

```java
BoxFileVersionRetention policy = new BoxFileVersionRetention(api, id);
BoxFileVersionRetention.Info policyInfo = policy.getInfo();
```

[get-file-version-retention]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersionRetention.html#getInfo-java.lang.String...-

Get File Version Retentions
--------------

To get an iterable with all file version retentions for current retention policy, call the static [`getAll(BoxAPIConnection, String...)`][get-all-file-version-retentions]. It is possible to add filters to query using [`QueryFilter`][query-filter] object as a parameter: [`getRetentions(BoxAPIConnection, QueryFilter, String...)`][get-all-file-version-retentions-with-filter].

```java
BoxFileVersionRetention.QueryFilter filter = new BoxFileVersionRetention.QueryFilter()
                .addFileID("0")
                .addFileVersionID("1")
                .addPolicyID("2")
                .addDispositionAction(BoxRetentionPolicy.ACTION_PERMANENTLY_DELETE)
                .addDispositionBefore(BoxDateFormat.parse("2016-09-15T13:15:35+0000"))
                .addDispositionAfter(BoxDateFormat.parse("2014-09-15T13:15:35+0000"));
Iterable<BoxFileVersionRetention.Info> retentions
                = BoxFileVersionRetention.getRetentions(api, filter, "file", "applied_at");
for (BoxFileVersionRetention.Info retentionInfo : retentions) {
	// Do something with the file version retention.
}
```

[get-all-file-version-retentions]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersionRetention.html#getAll-com.box.sdk.BoxAPIConnection-java.lang.String...-
[query-filter]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersionRetention.QueryFilter.html
[get-all-file-version-retentions-with-filter]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFileVersionRetention.html#getRetentions-com.box.sdk.BoxAPIConnection-com.box.sdk.BoxFileVersionRetention.QueryFilter-java.lang.String...-
