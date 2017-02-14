Users
=====

Users represent an individual's account on Box.

* [Get the Current User's Information](#get-the-current-users-information)
* [Create An Enterprise User](#create-an-enterprise-user)
* [Update User](#update-user)
* [Delete User](#delete-user)
* [Invite User](#invite-user)
* [Get Email Aliases](#get-email-aliases)
* [Add Email Alias](#add-email-alias)
* [Delete Email Alias](#delete-email-alias)
* [Get Enterprise Users](#get-enterprise-users)
* [Move User's Folder](#move-users-folder)

Get the Current User's Information
----------------------------------

To get the current user, call the static [`getCurrentUser(BoxAPIConnection)`]
[get-current-user] method. Then use [`getInfo()`][get-info] to get information
about the user.

```java
BoxUser user = BoxUser.getCurrentUser(api);
BoxUser.Info info = user.getInfo();
```

[get-current-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getCurrentUser(com.box.sdk.BoxAPIConnection)
[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getInfo()

Create An Enterprise User
-------------------------

To create an enterprise user call the [`createEnterpriseUser(BoxAPIConnection, String, String)`][create-enterprise-user] or [`createEnterpriseUser(BoxAPIConnection, String, String, CreateUserParams)`][create-enterprise-user-2] method.

```java
BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, "user@email.com", "A User");
```

[create-enterprise-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#createEnterpriseUser(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20java.lang.String)
[create-enterprise-user-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#createEnterpriseUser(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20java.lang.String,%20com.box.sdk.CreateUserParams)

Update User
-----------

To update a user call the [`updateInfo(BoxUser.Info)`][update-info] method.

```java
BoxUser user = new BoxUser(api, "0");
BoxUser.Info info = user.new Info();
info.setName(name);
user.updateInfo(info);
```

[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#updateInfo(com.box.sdk.BoxUser.Info)

Delete User
-----------

To delete a user call the [`delete(boolean, boolean)`][delete] method.

```java
BoxUser user = new BoxUser(api, "0");
user.delete(false, false);
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#delete(boolean,%20boolean)

Invite User
-----------

To invite an existing user to join an Enterprise call the [`inviteUser(String, String)`][invite] method.

```java
BoxUser user = new BoxUser(api, "0");
user.invite("Enterprise ID", "Invited User Login");
```

[invite]:  http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#inviteUser(java.lang.String,%java.lang.String)

Get Email Aliases
-----------------

To get a users email aliases call the [`getEmailAliases()`][get-email-aliases] method.

```java
BoxUser user = new BoxUser(api, "0");
Collection<EmailAlias> emailAliases = user.getEmailAliases();
```

[get-email-aliases]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getEmailAliases()

Add Email Alias
---------------

To add an email alias for a user call the [`addEmailAlias(String)`][add-email-alias] method.

```java
BoxUser user = new BoxUser(api, "0");
user.addEmailAlias("user@email.com");
```

[add-email-alias]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#addEmailAlias(java.lang.String)

Delete Email Alias
------------------

To delete a users email alias call the [`deleteEmailAlias(String)`][delete-email-alias] method.

```java
BoxUser user = new BoxUser(api, "0");
user.deleteEmailAlias("123");
```

[delete-email-alias]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#deleteEmailAlias(java.lang.String)

Get Enterprise Users
--------------------

To get an enterprises users call the [`getAllEnterpriseUsers(BoxAPIConnection)`][get-all-enterprise-users], [`getAllEnterpriseUsers(BoxAPIConnection, String, String...)`][get-all-enterprise-users-2], or [`getAllEnterpriseOrExternalUsers(BoxAPIConnection, String, String...)`][get-all-enterprise-users-3] method.

```java
Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(api);
```

[get-all-enterprise-users]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseUsers(com.box.sdk.BoxAPIConnection)
[get-all-enterprise-users-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseUsers(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20java.lang.String...)
[get-all-enterprise-users-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseOrExternalUsers(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20java.lang.String...)

Move User's Folder
------------------

To move a users folder call the [`moveFolderToUser(String)`][move-folder-to-user] method. Currently, only moving the root folder (0) is supported.

```java
BoxUser user = new BoxUser(api, "0");
BoxFolder.Info folderInfo = user.moveFolderToUser("1");
```

[move-folder-to-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#moveFolderToUser(java.lang.String)