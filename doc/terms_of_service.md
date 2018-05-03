Terms of Service
================

Terms of Service allows Box Admins to configure a custom Terms of Service for end users to
accept/re-accept/decline for custom applications

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Create a Terms of Service](#create-a-terms-of-service)
- [Edit a Terms of Service](#edit-a-terms-of-service)
- [Get a Terms of Service](#get-a-terms-of-service)
- [Get All Terms of Services](#get-all-terms-of-services)
- [Accept or Decline a Terms of Service for New User](#accept-or-decline-a-terms-of-service-for-new-user)
- [Accept or Decline a Terms of Service for Existing User](#accept-or-decline-a-terms-of-service-for-existing-user)
- [Get User Status on a Terms of Service](#get-user-status-on-a-terms-of-service)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Create a Terms of Service
-------------------------

A terms of service can be created in an enterprise. Please note that only two can be created. One external
and one managed. If a terms of service already exists please use the update call to change the current
terms of service.

You can create a custom terms of service by calling
[`create(BoxAPIConnection api, BoxTermsOfService.TermsOfServiceStatus status, BoxTermsOfService.TermsOfServiceType type, String text)`][createTermsOfService].

```java
BoxTermsOfService.Info newTOS = BoxTermsOfService.create(
    api,
    BoxTermsOfService.TermsOfServiceStatus.ENABLED,
    BoxTermsOfService.TermsOfServiceType.EXTERNAL,
    "Terms of Service..."
);
```

[createTermsOfService]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfService.html#create-com.box.sdk.BoxAPIConnection-com.box.sdk.BoxTermsOfService.TermsOfServiceStatus-com.box.sdk.BoxTermsOfService.TermsOfServiceType-java.lang.String-

Edit a Terms of Service
-----------------------

You can update a terms of service status and text after you have created them by calling
[`updateInfo(BoxTermsOfService.Info fieldsToUpdate)`][update-terms-of-service]

```java
BoxTermsOfService termsOfService = new BoxTermsOfService(api, "tos-id");
BoxTermsOfService.Info termsOfServiceInfo = termsOfService.new Info();
termsOfServiceInfo.setStatus(BoxTermsOfService.TermsOfServiceStatus.DISABLED);
termsOfServiceInfo.setText("New Terms of Service Text");
termsOfService.updateInfo(termsOfService);
```

[update-terms-of-service]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfService.html#updateInfo-com.box.sdk.BoxTermsOfService.Info-

Get a Terms of Service
----------------------

You can retrieve a terms of service by providing the ID and calling
[`getInfo()`][getTermsOfServiceInfo].

```java
BoxTermsOfService termsOfService = new BoxTermsOfService(api, "tos-id");
BoxTermsOfService.Info tosInfo = termsOfService.getInfo();
```

[getTermsOfServiceInfo]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfService.html#getInfo--

Get All Terms of Services
-------------------------

You can also retrieve all terms of service in your enterprise by calling
[`getAllTermsOfServices(BoxAPIConnection api)`][get-all-terms-of-services1].
This will return an iterable that will page through all of the enterprises terms of services.

```java
List<BoxTermsOfService.Info> termsOfServices = BoxTermsOfService.getAllTermsOfServices(api);
for(BoxTermsOfService.Info termsOfServiceInfo : termsOfServices){
    // Do something with the terms of service.
}
```

You can also filter by managed or external terms of service by calling
[`getAllTermsOfServices(BoxAPIConnection api, BoxTermsOfService.TermsOfServiceType type)`][get-all-terms-of-services2].

[get-all-terms-of-services1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfService.html#getAllTermsOfServices-com.box.sdk.BoxAPIConnection-
[get-all-terms-of-services2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfService.html#getAllTermsOfServices-com.box.sdk.BoxAPIConnection-com.box.sdk.BoxTermsOfService.TermsOfServiceType-


Accept or Decline a Terms of Service for New User
-------------------------------------------------

For new users you can accept or decline a terms of service by calling
[`create(BoxAPIConnection api, String tosID, Boolean accepted, String userID)`][create-user-status]

```java
BoxTermsOfService.Info newUserStatus = BoxTermsOfServiceUserStatus.create(api, "tos-id", true, "user-id");
```

You can only create a new user status on a terms of service if the user has never accepted/declined a terms of service.
If they have then you will need to make the update call.

[create-user-status]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfServiceUserStatus.html#create-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.Boolean-java.lang.String-

Accept or Decline a Terms of Service for Existing User
------------------------------------------------------

For an existing user you can accept or decline a terms of service by calling
[`updateInfo(BoxTermsOfService.Info fieldsToUpdate)`][update-user-status].

```java
BoxTermsOfServiceUserStatus tosUserStatus = new BoxTermsOfServiceUserStatus(api, "tos-user-status-id");
BoxTermOfServiceUserStatus.Info tosUserStatusInfo = tosUserStatus.new Info();
tosUserStatusInfo.setStatus(newStatus);
tosUserStatus.updateInfo(tosUSerStatusInfo);
```

[update-user-status]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfServiceUserStatus.html#updateInfo-com.box.sdk.BoxTermsOfServiceUserStatus.Info-

Get User Status on a Terms of Service
-------------------------------------

You can retrieve the terms of service status for a user by calling
[`getInfo(BoxAPIConnection api, String tosID, String userID)`][get-user-status1]

```java
List<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo = BoxTermsOfServiceUserStatus.getInfo(api, "tos-id", "user-id");
for(BoxTermsOfServiceUserStatus.Info info : toUserStatusInfo){
    // Do something with the user status on terms of service.
}
```

Alternatively you can get the user status by the ID of the terms of service by
calling [`getInfo(BoxAPIConnection api, String tosID)`][get-user-status2].

```java
List<BoxTermsOfServiceUserStatus.Info> tosUserStatusInfo = BoxTermsOfServiceUserStatus.getInfo(api, "tos-id");
for(BoxTermsOfServiceUserStatus.Info info : toUserStatusInfo){
    // Do something with the user status on terms of service.
}
```

[get-user-status1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfServiceUserStatus.html#getInfo-com.box.sdk.BoxAPIConnection-java.lang.String-
[get-user-status2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxTermsOfServiceUserStatus.html#getInfo-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String-
