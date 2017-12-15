Collaboration Whitelists
========================

Collaboration Whitelists are used to manage a set of approved domains or Box users that an enterprise
can collaborate with.

* [Add a Collaboration Whitelist Entry for a Domain](#add-a-collaboration-whitelist-for-a-domain)
* [Get a Collaboration Whitelist's Information for a Domain](#get-a-collaboration-whitelists-information-for--a-domain)
* [Get all Collaboration Whitelist's Information for Domains](#get-all-collaboration-whitelists-information-for-a-domain)
* [Remove a Collaboration Whitelist for a Domain](#remove-a-collaboration-whitelist-for-a-domain)
* [Add a Collaboration Whitelist Entry for a User](#add-a-collaboration-whitelist-for-a-user)
* [Get a Collaboration Whitelist's Information for a User](#get-a-collaboration-whitelists-information-for-a-user)
* [Get all Collaboration Whitelist's Information for Users](#get-all-collaboration-whitelists-information-for-users)
* [Remove a Collaboration Whitelist for a User](#remove-a-collaboration-whitelist-for-a-user)

Add a Collaboration Whitelist For a Domain
------------------------------------------

A collaboration whitelist can be created for a domain with
[`create(BoxAPIConnection, String, WhitelistDirection)`][whitelist1]. The `WhitelistDirection`
parameter determines which way the whitelisting applies. You can set the value to inbound, outbound, or both.

```java
BoxCollaborationWhitelist.create(api, "test.com", BoxCollaborationWhitelist.WhitelistDirection.BOTH);
```

[whitelist1]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelist.html#create(com.box.sdk.BoxAPIConnection, %20java.lang.String, %20com.box.sdk.BoxCollaborationWhitelist.WhitelistDirection)

Get a Collaboration Whitelist's Information for a Domain
--------------------------------------------------------

A specific collaboration whitelist for a domain can be retrieved with
[`getInfo()`][getInfo]

```java
BoxCollaborationWhitelist domainWhitelist = new BoxCollaborationWhitelist(api, "id");
domainWhitelist.getInfo();
```

[getInfo]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelist.html#getInfo()

Get all Collaboration Whitelist's Information for Domain
--------------------------------------------------------

All domain collaboration whitelists associated with an enterprise can be retrieved with
[`getAll(BoxAPIConnection)`][getAll1]

```java
BoxCollaborationWhitelist.getAll(api);
```

To specify the number of whitelists to retrieve you can specify a limit on how many whitelists to return.
[`getAll(BoxAPIConnection, Integer)`]

```java
BoxCollaborationWhitelist.getAll(api, 10);
```

[getAll1]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelist.html#getAll(com.box.sdk.BoxAPIConnection)
[getAll2]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelist.html#getAll(com.box.sdk.BoxAPIConnection, %20java.lang.Integer)

Remove a Collaboration Whitelist for a Domain
---------------------------------------------

To remove a collaboration whitelist you can call
[`delete()`][delete]

```java
BoxCollaborationWhitelist domainToBeDeleted = new BoxCollaborationWhitelist(api, "whitelist-id");
domainToBeDeleted.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelist.html#delete()

Add a Collaboration Whitelist for a User
----------------------------------------

A collaboration whitelist can be created for a user with
[`create(BoxAPIConnection, String)`][create]

```java
String userID = "12345";
BoxCollaborationWhitelistExemptTarget.create(api, userID);
```

[create]:  http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelistExemptTarget.html#create(com.box.sdk.BoxAPIConnection, %20java.lang.String)

Get a Collaboration Whitelist's Information for a User
------------------------------------------------------

To retrieve information regarding a specific user collaboration whitelist use
[`getInfo()`][getInfo]

```java
BoxCollaborationWhitelistExemptTarget userWhitelist = new BoxCollaborationWhitelistExemptTarget(api, "whitelistID");
userWhitelist.getInfo();
```

[getInfo]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelistExemptTarget.html#getInfo()

Get all Collaboration Whitelist's Information for a User
--------------------------------------------------------

To retrieve information regarding all user whitelists associated with an enterprise use
[`getAll(BoxAPIConnection)`][getAll1]

```java
BoxCollaborationWhitelistExemptTarget.getAll(api);
```

Alternatively you can specify the number of user whitelists to return with one request with
[`getAll(BoxApiConnection, Integer)`][getAll2]

```java
BoxCollaborationWhitelistExemptTarget.getAll(api, 5);
```

[getAll1]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelistExemptTarget.html#getAll(com.box.sdk.BoxAPIConnection)
[getAll2]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelistExemptTarget.html#getAll(com.box.sdk.BoxAPIConnection, %20java.lang.Integer)

Remove a Collaboration Whitelist for a User
-------------------------------------------

To remove a user collaboration whitelist entry from an enterprise use
[`delete()`][delete]

```java
BoxCollaborationWhitelistExemptTarget.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/sdk/BoxCollaborationWhitelistExemptTarget.html#delete()