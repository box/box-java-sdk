Legal Holds Policy
======

Legal Hold Policy information describes the basic characteristics of the Policy, such as name, description, and filter dates.

* [Get Legal Hold Policy](#get-legal-hold-policy)
* [Get List of Legal Hold Policies](#get-list-of-legal-hold-policies)
* [Create New Legal Hold Policy](#create-new-legal-hold-policy)
* [Update Existing Legal Hold Policy](#update-existing-legal-hold-policy)
* [Delete Legal Hold Policy](#delete-legal-hold-policy)

Get Legal Hold Policy
--------------

Calling [`getInfo(String...)`][get-info] will return a BoxLegalHold.Info object containing information about the legal hold policy. If necessary to retrieve limited set of fields, it is possible to specify them using param.

```java
BoxLegalHold policy = new BoxLegalHold(api, id);
BoxLegalHold.Info policyInfo = policy.getInfo();
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHold.html#getInfo(java.lang.String...)

Get List of Legal Hold Policies
--------------

Calling the static [`getAll(BoxAPIConnection)`][get-list-of-legal-hold-policies] will return an iterable that will page through all of the legal hold policies.
It is possible to specify name of legal hold policy, maximum number of items per response and fields to retrieve by calling the static [`getAll(BoxAPIConnection, String, int, String...)`][get-list-of-legal-hold-policies-with-fields] method.

```java
Iterable<BoxLegalHold.Info> policies = BoxLegalHold.getAll(api);
for (BoxLegalHold.Info policyInfo : policies) {
    // Do something with the legal hold policy.
}
```

[get-list-of-legal-hold-policies]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHold.html#getAll(com.box.sdk.BoxAPIConnection)
[get-list-of-legal-hold-policies-with-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHold.html#getAll(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20int,%20java.lang.String...)

Create New Legal Hold Policy
--------------

The static [`create(BoxAPIConnection, String)`][create-new-legal-hold-policy] method will let you create a new legal hold policy with a specified name. The static [`create(BoxAPIConnection, String, String, Date, Date)`][create-new-legal-hold-policy-with-dates] method will let you create a new legal hold policy with a specified name, description, start and end dates.

```java
BoxLegalHold.Info policyInfo = BoxLegalHold.create(api, name, description, startedAt, endedAt);
```

[create-new-legal-hold-policy]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHold.html#create(com.box.sdk.BoxAPIConnection,%20java.lang.String)
[create-new-legal-hold-policy-with-dates]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHold.html#create(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20java.lang.String,%20java.util.Date,%20java.util.Date)

Update Existing Legal Hold Policy
--------------

Updating a legal hold policy's information is done by calling [`updateInfo(BoxLegalHold.Info)`][update-info].

```java
BoxLegalHold policy = new BoxLegalHold(api, id);
BoxLegalHold.Info policyInfo = policy.new Info();
info.addPendingChange("description", "new description");
policy.updateInfo(info);
```

[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHold.html#update(com.box.sdk.BoxLegalHold.Info)

Delete Legal Hold Policy
--------------

A legal hold policy can be deleted by calling the [`delete()`][delete] method.

```java
BoxLegalHold policy = new BoxLegalHold(api, id);
policy.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHold.html#delete()