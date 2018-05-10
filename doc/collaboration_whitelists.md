Collaboration Whitelists
========================

Collaboration Whitelists are used to manage a set of approved domains or Box users that an enterprise
can collaborate with.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Add a Collaboration Whitelist For a Domain](#add-a-collaboration-whitelist-for-a-domain)
- [Get a Collaboration Whitelist's Information for a Domain](#get-a-collaboration-whitelists-information-for-a-domain)
- [Get all Collaboration Whitelist's Information for Domain](#get-all-collaboration-whitelists-information-for-domain)
- [Remove a Collaboration Whitelist for a Domain](#remove-a-collaboration-whitelist-for-a-domain)
- [Add a Collaboration Whitelist for a User](#add-a-collaboration-whitelist-for-a-user)
- [Get a Collaboration Whitelist's Information for a User](#get-a-collaboration-whitelists-information-for-a-user)
- [Get all Collaboration Whitelist's Information for a User](#get-all-collaboration-whitelists-information-for-a-user)
- [Remove a Collaboration Whitelist for a User](#remove-a-collaboration-whitelist-for-a-user)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Add a Collaboration Whitelist For a Domain
------------------------------------------

A collaboration whitelist can be created for a domain with
[`create(BoxAPIConnection api, String domain, WhitelistDirection direction)`][whitelist1].
The `WhitelistDirection` parameter determines which way the whitelisting
applies. You can set the value to inbound, outbound, or both.

```java
BoxCollaborationWhitelist.create(api, "test.com", BoxCollaborationWhitelist.WhitelistDirection.BOTH);
```

[whitelist1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelist.html#create-com.box.sdk.BoxAPIConnection-java.lang.String-com.box.sdk.BoxCollaborationWhitelist.WhitelistDirection-

Get a Collaboration Whitelist's Information for a Domain
--------------------------------------------------------

A specific collaboration whitelist for a domain can be retrieved with
[`getInfo()`][getWhitelistInfo]

```java
BoxCollaborationWhitelist domainWhitelist = new BoxCollaborationWhitelist(api, "id");
domainWhitelist.getInfo();
```

[getWhitelistInfo]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelist.html#getInfo--

Get all Collaboration Whitelist's Information for Domain
--------------------------------------------------------

All domain collaboration whitelists associated with an enterprise can be
retrieved with [`getAll(BoxAPIConnection api)`][getAllWhitelists1]

```java
BoxCollaborationWhitelist.getAll(api);
```

To specify the number of whitelists to retrieve you can pass a limit on how
many whitelists to return to [`getAll(BoxAPIConnection api, int limit)`][getAllWhitelists2].

```java
BoxCollaborationWhitelist.getAll(api, 10);
```

[getAllWhitelists1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelist.html#getAll-com.box.sdk.BoxAPIConnection-java.lang.String...-
[getAllWhitelists2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelist.html#getAll-com.box.sdk.BoxAPIConnection-int-java.lang.String...-

Remove a Collaboration Whitelist for a Domain
---------------------------------------------

To remove a collaboration whitelist you can call [`delete()`][deleteWhitelist]

```java
BoxCollaborationWhitelist domainToBeDeleted = new BoxCollaborationWhitelist(api, "whitelist-id");
domainToBeDeleted.delete();
```

[deleteWhitelist]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelist.html#delete--

Add a Collaboration Whitelist for a User
----------------------------------------

A collaboration whitelist can be created for a user with
[`create(BoxAPIConnection api, String userID)`][createExempt]

```java
String userID = "12345";
BoxCollaborationWhitelistExemptTarget.create(api, userID);
```

[createExempt]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelistExemptTarget.html#create-com.box.sdk.BoxAPIConnection-java.lang.String-

Get a Collaboration Whitelist's Information for a User
------------------------------------------------------

To retrieve information regarding a specific user collaboration whitelist use
[`getInfo()`][getInfoExempt]

```java
BoxCollaborationWhitelistExemptTarget userWhitelist = new BoxCollaborationWhitelistExemptTarget(api, "whitelistID");
userWhitelist.getInfo();
```

[getInfoExempt]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelistExemptTarget.html#getInfo--

Get all Collaboration Whitelist's Information for a User
--------------------------------------------------------

To retrieve information regarding all user whitelists associated with an enterprise use
[`getAll(BoxAPIConnection api)`][getAllExempt1]

```java
BoxCollaborationWhitelistExemptTarget.getAll(api);
```

Alternatively you can specify the number of user whitelists to return with one
request by passing a the maximum number of records to return to
[`getAll(BoxApiConnection api, int limit)`][getAllExempt2]

```java
BoxCollaborationWhitelistExemptTarget.getAll(api, 5);
```

[getAllExempt1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelistExemptTarget.html#getAll-com.box.sdk.BoxAPIConnection-java.lang.String...-
[getAllExempt2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelistExemptTarget.html#getAll-com.box.sdk.BoxAPIConnection-int-java.lang.String...-

Remove a Collaboration Whitelist for a User
-------------------------------------------

To remove a user collaboration whitelist entry from an enterprise use
[`delete()`][deleteExempt]

```java
BoxCollaborationWhitelistExemptTarget userWhitelist = new BoxCollaborationWhitelistExemptTarget(api, "whitelist_id") 
userWhitelist.delete();
```

[deleteExempt]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationWhitelistExemptTarget.html#delete--
