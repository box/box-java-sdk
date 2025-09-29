Collaboration Allowlists
========================

Collaboration Allowlists are used to manage a set of approved domains or Box users that an enterprise
can collaborate with.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Add a Collaboration Allowlist For a Domain](#add-a-collaboration-allowlist-for-a-domain)
- [Get a Collaboration Allowlist's Information for a Domain](#get-a-collaboration-allowlists-information-for-a-domain)
- [Get all Collaboration Allowlist's Information for Domain](#get-all-collaboration-allowlists-information-for-domain)
- [Remove a Collaboration Allowlist for a Domain](#remove-a-collaboration-allowlist-for-a-domain)
- [Add a Collaboration Allowlist for a User](#add-a-collaboration-allowlist-for-a-user)
- [Get a Collaboration Allowlist's Information for a User](#get-a-collaboration-allowlists-information-for-a-user)
- [Get all Collaboration Allowlist's Information for a User](#get-all-collaboration-allowlists-information-for-a-user)
- [Remove a Collaboration Allowlist for a User](#remove-a-collaboration-allowlist-for-a-user)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Add a Collaboration Allowlist For a Domain
------------------------------------------

A collaboration allowlist can be created for a domain with
[`create(BoxAPIConnection api, String domain, AllowlistDirection direction)`][allowlist1].
The `AllowlistDirection` parameter determines which way the allowlisting
applies. You can set the value to inbound, outbound, or both.

<!-- sample post_collaboration_allowlist_entries -->
```java
BoxCollaborationAllowlist.create(api, "test.com", BoxCollaborationAllowlist.AllowlistDirection.BOTH);
```

[allowlist1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlist.html#create-com.box.sdk.BoxAPIConnection-java.lang.String-com.box.sdk.BoxCollaborationAllowlist.AllowlistDirection-

Get a Collaboration Allowlist's Information for a Domain
--------------------------------------------------------

A specific collaboration allowlist for a domain can be retrieved with
[`getInfo()`][getAllowlistInfo]

<!-- sample get_collaboration_allowlist_entries_id -->
```java
BoxCollaborationAllowlist domainAllowlist = new BoxCollaborationAllowlist(api, "id");
domainAllowlist.getInfo();
```

[getAllowlistInfo]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlist.html#getInfo--

Get all Collaboration Allowlist's Information for Domain
--------------------------------------------------------

All domain collaboration allowlists associated with an enterprise can be
retrieved with [`getAll(BoxAPIConnection api)`][getAllAllowlists1]

<!-- sample get_collaboration_allowlist_entries -->
```java
BoxCollaborationAllowlist.getAll(api);
```

To specify the number of allowlists to retrieve you can pass a limit on how
many allowlists to return to [`getAll(BoxAPIConnection api, int limit)`][getAllAllowlists2].

```java
BoxCollaborationAllowlist.getAll(api, 10);
```

[getAllAllowlists1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlist.html#getAll-com.box.sdk.BoxAPIConnection-java.lang.String...-
[getAllAllowlists2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlist.html#getAll-com.box.sdk.BoxAPIConnection-int-java.lang.String...-

Remove a Collaboration Allowlist for a Domain
---------------------------------------------

To remove a collaboration allowlist you can call [`delete()`][deleteAllowlist]

<!-- sample delete_collaboration_allowlist_entries_id -->
```java
BoxCollaborationAllowlist domainToBeDeleted = new BoxCollaborationAllowlist(api, "allowlist-id");
domainToBeDeleted.delete();
```

[deleteAllowlist]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlist.html#delete--

Add a Collaboration Allowlist for a User
----------------------------------------

A collaboration allowlist can be created for a user with
[`create(BoxAPIConnection api, String userID)`][createExempt]

<!-- sample post_collaboration_allowlist_exempt_targets -->
```java
String userID = "12345";
BoxCollaborationAllowlistExemptTarget.create(api, userID);
```

[createExempt]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlistExemptTarget.html#create-com.box.sdk.BoxAPIConnection-java.lang.String-

Get a Collaboration Allowlist's Information for a User
------------------------------------------------------

To retrieve information regarding a specific user collaboration allowlist use
[`getInfo()`][getInfoExempt]

<!-- sample get_collaboration_allowlist_exempt_targets_id -->
```java
BoxCollaborationAllowlistExemptTarget userAllowlist = new BoxCollaborationAllowlistExemptTarget(api, "allowlistID");
userAllowlist.getInfo();
```

[getInfoExempt]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlistExemptTarget.html#getInfo--

Get all Collaboration Allowlist's Information for a User
--------------------------------------------------------

To retrieve information regarding all user allowlists associated with an enterprise use
[`getAll(BoxAPIConnection api)`][getAllExempt1]

<!-- sample get_collaboration_allowlist_exempt_targets -->
```java
BoxCollaborationAllowlistExemptTarget.getAll(api);
```

Alternatively you can specify the number of user allowlists to return with one
request by passing a the maximum number of records to return to
[`getAll(BoxApiConnection api, int limit)`][getAllExempt2]

```java
BoxCollaborationAllowlistExemptTarget.getAll(api, 5);
```

[getAllExempt1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlistExemptTarget.html#getAll-com.box.sdk.BoxAPIConnection-java.lang.String...-
[getAllExempt2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlistExemptTarget.html#getAll-com.box.sdk.BoxAPIConnection-int-java.lang.String...-

Remove a Collaboration Allowlist for a User
-------------------------------------------

To remove a user collaboration allowlist entry from an enterprise use
[`delete()`][deleteExempt]

<!-- sample delete_collaboration_allowlist_exempt_targets_id -->
```java
BoxCollaborationAllowlistExemptTarget userAllowlist = new BoxCollaborationAllowlistExemptTarget(api, "allowlist_id");
userAllowlist.delete();
```

[deleteExempt]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxCollaborationAllowlistExemptTarget.html#delete--
