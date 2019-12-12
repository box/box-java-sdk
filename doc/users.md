Users
=====

Users represent an individual's account on Box.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get the Current User's Information](#get-the-current-users-information)
- [Create An Enterprise User](#create-an-enterprise-user)
- [Create An App User](#create-an-app-user)
- [Update User](#update-user)
- [Delete User](#delete-user)
- [Invite User](#invite-user)
- [Get Email Aliases](#get-email-aliases)
- [Add Email Alias](#add-email-alias)
- [Delete Email Alias](#delete-email-alias)
- [Get Enterprise Users](#get-enterprise-users)
- [Get Enterprise Users (Marker Pagination)](#get-enterprise-users-marker-pagination)
- [Get App Users By External App User ID](#get-app-users-by-external-app-user-id)
- [Get App Users By External App User ID (Marker Pagination)](#get-app-users-by-external-app-user-id-marker-pagination)
- [Move User's Folder](#move-users-folder)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get the Current User's Information
----------------------------------

To get the current user, call the static
[`getCurrentUser(BoxAPIConnection api)`][get-current-user] method.
Then use [`getInfo(String... fields)`][get-info] to get information about the user.

<!-- sample get_users_me -->
```java
BoxUser user = BoxUser.getCurrentUser(api);
BoxUser.Info info = user.getInfo();
```

[get-current-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getCurrentUser-com.box.sdk.BoxAPIConnection-
[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getInfo-java.lang.String...-

Get User Information
--------------------

To get information about a user, call the [`getInfo()`][get-info] method on the user object.

<!-- sample get_users_id -->
```java
String userID = "33333";
BoxUser user = new BoxUser(api, userID);
BoxUser.Info userInfo = user.getInfo();
```

Get Avatar for a User
---------------------

To retrieve the avatar for a user, call the [`getAvatar()`][get-avatar] method on the user object.

<!-- sample get_users_id_avatar -->
```java
String userID = "33333";
BoxUser user = new BoxUser(api, userID);
InputStream avatarStream = user.getAvatar();
```

[get-avatar]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAvatar--

Create An Enterprise User
-------------------------

To create an enterprise user, call the
[`createEnterpriseUser(BoxAPIConnection api, String loginEmail, String userName)`][create-enterprise-user].
To pass additional optional parameters, use the
[`createEnterpriseUser(BoxAPIConnection api, String loginEmail, String userName, CreateUserParams options)`][create-enterprise-user-2]
method.

<!-- sample post_users -->
```java
BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, "user@example.com", "A User");
```

[create-enterprise-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#createEnterpriseUser-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String-
[create-enterprise-user-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#createAppUser-com.box.sdk.BoxAPIConnection-java.lang.String-com.box.sdk.CreateUserParams-

Create An App User
------------------

To create an app user, call the
[`createAppUser(BoxAPIConnection api, String userName)`][create-app-user] method.
To pass additional optional parameters, use the
[`createAppUser(BoxAPIConnection api, String userName, CreateUserParams options)`][create-app-user-2] method.

<!-- sample post_users_app -->
```java
BoxUser.Info createdUserInfo = BoxUser.createAppUser(api, "A User");
```

```java
CreateUserParams params = new CreateUserParams();
params.setExternalAppUserId("An Identifier Like Login");
BoxUser.Info createdUserInfo = BoxUser.createAppUser(api, "A User", params);
```

[create-app-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#createAppUser-com.box.sdk.BoxAPIConnection-java.lang.String-
[create-app-user-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#createAppUser-com.box.sdk.BoxAPIConnection-java.lang.String-com.box.sdk.CreateUserParams-


Update User
-----------

To update a user call the [`updateInfo(BoxUser.Info fieldsToUpdate)`][update-info] method.

<!-- sample put_users_id -->
```java
BoxUser user = new BoxUser(api, "0");
BoxUser.Info info = user.new Info();
info.setName(name);
user.updateInfo(info);
```

[update-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#updateInfo-com.box.sdk.BoxUser.Info-

Delete User
-----------

To delete a user call the [`delete(boolean notifyUser, boolean force)`][delete] method.

The `notifyUser` determines whether the user should receive an email about the deletion,
and the `force` parameter will cause the user to be deleted even if they still have files
in their account.

<!-- sample delete_users_id -->
```java
BoxUser user = new BoxUser(api, "0");
user.delete(false, false);
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#delete-boolean-boolean-

Invite User
-----------

To invite an existing user to join an Enterprise call the
[`inviteUser(String enterpriseID, String userEmail)`][invite] method.

<!-- sample post_invites -->
```java
BoxUser user = new BoxUser(api, "0");
user.invite("Enterprise ID", "Invited User Login");
```

[invite]:  http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#inviteUser-java.lang.String-java.lang.String-

Get Email Aliases
-----------------

To get a user's email aliases call the [`getEmailAliases()`][get-email-aliases] method.

<!-- sample get_users_id_email_aliases -->
```java
BoxUser user = new BoxUser(api, "0");
Collection<EmailAlias> emailAliases = user.getEmailAliases();
```

[get-email-aliases]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getEmailAliases--

Add Email Alias
---------------

To add an email alias for a user, call the
[`addEmailAlias(String emailAddress)`][add-email-alias] method.

<!-- sample post_users_id_email_aliases -->
```java
BoxUser user = new BoxUser(api, "0");
user.addEmailAlias("user+alias@example.com");
```

Enterprise admins can automatically confirm the new email alias by calling the
[`addEmailAlias(String emailAddress, boolean confirm)`][add-email-alias2] method:

```java
BoxUser user = new BoxUser(api, "0");
user.addEmailAlias("user+alias@eexample.com", true);
```

[add-email-alias]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#addEmailAlias-java.lang.String-
[add-email-alias2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#addEmailAlias-java.lang.String-boolean-

Delete Email Alias
------------------

To delete a users email alias call the
[`deleteEmailAlias(String emailAliasID)`][delete-email-alias] method.

<!-- sample delete_users_id_email_aliases_id -->
```java
BoxUser user = new BoxUser(api, "0");
user.deleteEmailAlias("123");
```

[delete-email-alias]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#deleteEmailAlias-java.lang.String-

Get Enterprise Users
--------------------

To get an enterprise's users call the
[`getAllEnterpriseUsers(BoxAPIConnection api)`][get-all-enterprise-users],
[`getAllEnterpriseUsers(BoxAPIConnection api, String filterTerm, String... fields)`][get-all-enterprise-users-2], or
[`getAllEnterpriseOrExternalUsers(BoxAPIConnection api, String filterTerm, String... fields)`][get-all-enterprise-users-3] method.

<!-- sample get_users -->
```java
Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(api);
```

[get-all-enterprise-users]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseUsers-com.box.sdk.BoxAPIConnection-
[get-all-enterprise-users-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseUsers-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String...-
[get-all-enterprise-users-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseOrExternalUsers-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String...-

Get Enterprise Users (Marker Pagination)
--------------------

To get an enterprise's users call the
[`getAllEnterpriseUsers(BoxAPIConnection api, boolean usemarker, String marker)`][get-all-enterprise-users],
[`getAllEnterpriseUsers(BoxAPIConnection api, String filterTerm, boolean usemarker, String marker, String... fields)`][get-all-enterprise-users-2], or
[`getAllEnterpriseOrExternalUsers(BoxAPIConnection api, String filterTerm, boolean usemarker, String marker, String... fields)`][get-all-enterprise-users-3] method.
To do marker pagination without passing in a marker, set the marker as `null`. In order to get the next marker, you must cast the iterable to `BoxResourseIterable<BoxUser.info>`
and call `getNextMarker()` on that iterable.

<!-- sample get_users -->
```java
Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(api, true, null);

// Get marker
((BoxResourceIterable<BoxUser.Info>) users).getNextMarker();
```

[get-all-enterprise-users]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseUsers-com.box.sdk.BoxAPIConnection-
[get-all-enterprise-users-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseUsers-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String...-
[get-all-enterprise-users-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAllEnterpriseOrExternalUsers-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String...-

Get App Users By External App User ID
-------------------------------------

To get app user using external app user ID, call the
[`getAppUsersByExternalAppUserID(BoxAPIConnection api, String externalID, String... fields)`][get-app-users-by-external-app-user-id].
This method allows you to easily associate Box app users with your application's
identifiers for those users.

```java
Iterable<BoxUser.Info> users = BoxUser.getAppUsersByExternalAppUserID(api, "external_app_user_id");
```

[get-app-users-by-external-app-user-id]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAppUsersByExternalAppUserID-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String...-

Get App Users By External App User ID (Marker Pagination) 
-------------------------------------

To get app user using external app user ID, call the
[`getAppUsersByExternalAppUserID(BoxAPIConnection api, String externalID, boolean usemarker, String marker, String... fields)`][get-app-users-by-external-app-user-id].
This method allows you to easily associate Box app users with your application's
identifiers for those users. To do marker pagination without passing in a marker, set the marker as `null`. In order to get the next marker, you must cast the iterable to `BoxResourseIterable<BoxUser.info>` 
and call `getNextMarker()` on that iterable.

```java
Iterable<BoxUser.Info> users = BoxUser.getAppUsersByExternalAppUserID(api, "external_app_user_id");

// Get marker
((BoxResourceIterable<BoxUser.Info>) users).getNextMarker();
```

[get-app-users-by-external-app-user-id]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAppUsersByExternalAppUserID-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String...-

Move User's Folder
------------------

To move all of a user's content to another user, call the
[`transferContent(String destinationUserID)`][transfer-folder-to-new-user] method.

<!-- sample put_users_id_folders_id -->
```java
String sourceUserID = "11111";
String destinationUserID = "22222";
BoxUser sourceUser = new BoxUser(api, sourceUserID);
BoxFolder.Info transferredFolderInfo = sourceUser.transferContent(destinationUserID);
```

[transfer-folder-to-new-user]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#transferContent-java.lang.String-
